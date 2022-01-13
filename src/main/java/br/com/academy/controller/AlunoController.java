package br.com.academy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.dao.IDaoAluno;
import br.com.academy.model.entity.Aluno;
import br.com.academy.model.enums.Status;

@Controller
public class AlunoController
{
	private boolean telaEdicao;
	
	@Autowired /* Anotação para fazer injeção de dependência */
	private IDaoAluno repositorio;
	
	@GetMapping("/cadAluno") /* Anotação para solicitar dados */
	public ModelAndView retornaViewCadAluno(Aluno aluno)
	{
		setTelaEdicao(false);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/cadAluno");
		mv.addObject("aluno", new Aluno());
		mv.addObject("telaEdicao", isTelaEdicao());
		return mv;
	}
	
	@GetMapping("/pesqAluno")
	public ModelAndView retornaViewPesqAluno()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/pesqAluno");
		mv.addObject("alunos", repositorio.findAll());
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@GetMapping("/cadAluno/{id}")
	public ModelAndView retornaViewEditAluno(@PathVariable("id") Integer id)
	{
		setTelaEdicao(true);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/cadAluno");
		mv.addObject("aluno", repositorio.getById(id));
		mv.addObject("telaEdicao", isTelaEdicao());
		return mv;
	}
	
	@GetMapping("/rmAluno/{id}")
	public String retornaViewAlunoRemovido(@PathVariable("id") Integer id)
	{
		repositorio.deleteById(id);
		return "redirect:/pesqAluno";
	}
	
	@GetMapping("/filtrarAlunos")
	public ModelAndView retornaViewFiltroAlunos()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/filtrarAlunos");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
				binder.registerCustomEditor(byte[].class,
					new ByteArrayMultipartFileEditor());
		}

	@PostMapping("salvarAluno") /* Anotação para enviar dados */
	public ModelAndView salvarAluno(@Valid Aluno aluno, @RequestParam("foto") MultipartFile foto, BindingResult br)
	{
		try
		{
			aluno.setFoto(foto.getBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors())
		{
			mv.setViewName("aluno/cadAluno");
			mv.addObject("aluno");
		}
		else
		{
			mv.setViewName("redirect:/pesqAluno");
			repositorio.save(aluno);
		}
		return mv;
	}
	
	@GetMapping("/imagem/{id}")
	@ResponseBody
	@Transactional
	public byte[] exibirImagem(@PathVariable("id") Integer id)
	{
		Aluno aluno = repositorio.getById(id);
		System.out.println("OLHA " + aluno);
		return aluno.getFoto();
	}
	
	@PostMapping("filtrarAlunos")
	public ModelAndView filtrarAlunos(@RequestParam(value = "status", required = false) Status status)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/pesqAluno");
		mv.addObject("alunos", repositorio.buscaAlunosFiltradoPeloStatus(status));
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@PostMapping("pesqAluno")
	public ModelAndView pesquisarAluno(@RequestParam(required=false) String nome)
	{
		ModelAndView mv = new ModelAndView();
		List<Aluno> listaAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listaAlunos = repositorio.findAll();
		}
		else
		{
			listaAlunos = repositorio.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("alunos", listaAlunos);
		mv.setViewName("aluno/pesqAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	public boolean isTelaEdicao()
	{
		return telaEdicao;
	}

	public void setTelaEdicao(boolean telaEdicao)
	{
		this.telaEdicao = telaEdicao;
	}
}
