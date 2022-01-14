package br.com.academy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.entity.Aluno;
import br.com.academy.model.enums.Status;
import br.com.academy.service.AlunoService;
import br.com.academy.util.Util;

@Controller
public class AlunoController
{
	private boolean telaEdicao;
	
	@Autowired 
	private AlunoService service;
	
	@GetMapping("/cadAluno")
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
		mv.addObject("alunos", service.buscarTodos());
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@GetMapping("/cadAluno/{id}")
	public ModelAndView retornaViewEditAluno(@PathVariable("id") Integer id)
	{
		setTelaEdicao(true);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/cadAluno");
		mv.addObject("aluno", service.buscarAlunoPeloId(id));
		mv.addObject("telaEdicao", isTelaEdicao());
		return mv;
	}
	
	@GetMapping("/rmAluno/{id}")
	public String retornaViewAlunoRemovido(@PathVariable("id") Integer id)
	{
		service.excluir(id);
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
	
	@PostMapping("salvarAluno")
	public ModelAndView salvarAluno(@RequestParam("arquivoFoto") MultipartFile arquivo, @Valid Aluno aluno, BindingResult br)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			aluno.setFoto(Util.converteImagemParaString(arquivo));
		}
		catch(Exception e)
		{
			br.addError(new ObjectError("msgErroImportarArquivo", e.getMessage()));
		}
		if(br.hasErrors())
		{
			mv.setViewName("aluno/cadAluno");
			mv.addObject("aluno");
		}
		else
		{
			mv.setViewName("redirect:/pesqAluno");
			service.salvar(aluno);
		}
		return mv;
	}
	
	@PostMapping("filtrarAlunos")
	public ModelAndView filtrarAlunos(@RequestParam(value = "status", required = false) Status status)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/pesqAluno");
		mv.addObject("alunos", service.buscarAlunosFiltradoPeloStatus(status));
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@PostMapping("pesqAluno")
	public ModelAndView pesquisarAluno(@RequestParam(required=false) String nome)
	{
		ModelAndView mv = new ModelAndView();
		List<Aluno> listaAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listaAlunos = service.buscarTodos();
		}
		else
		{
			listaAlunos = service.buscarAlunosPeloNome(nome);
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
