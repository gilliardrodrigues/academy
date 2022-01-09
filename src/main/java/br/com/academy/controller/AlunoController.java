package br.com.academy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.dao.IDaoAluno;
import br.com.academy.model.entity.Aluno;

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
	public String removerAluno(@PathVariable("id") Integer id)
	{
		repositorio.deleteById(id);
		return "redirect:/pesqAluno";
	}
	
	@GetMapping("/filtroAlunos")
	public ModelAndView retornaViewFiltroAlunos()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/filtroAlunos");
		return mv;
	}
	
	@GetMapping("/alunosAtivos")
	public ModelAndView retornaViewAlunosAtivos()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosAtivos");
		mv.addObject("alunosAtivos", repositorio.buscaAlunosAtivos());
		return mv;
	}
	
	@GetMapping("/alunosInativos")
	public ModelAndView retornaViewAlunosInativos()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosInativos");
		mv.addObject("alunosInativos", repositorio.buscaAlunosInativos());
		return mv;
	}
	
	@GetMapping("/alunosCancelados")
	public ModelAndView retornaViewAlunosCancelados()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosCancelados");
		mv.addObject("alunosCancelados", repositorio.buscaAlunosCancelados());
		return mv;
	}
	
	@GetMapping("/alunosTrancados")
	public ModelAndView retornaViewAlunosTrancados()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosTrancados");
		mv.addObject("alunosTrancados", repositorio.buscaAlunosTrancados());
		return mv;
	}
	
	@PostMapping("salvarAluno") /* Anotação para enviar dados */
	public ModelAndView salvarAluno(@Valid Aluno aluno, BindingResult br)
	{
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

	public boolean isTelaEdicao()
	{
		return telaEdicao;
	}

	public void setTelaEdicao(boolean telaEdicao)
	{
		this.telaEdicao = telaEdicao;
	}
}
