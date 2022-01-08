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
	@Autowired /* Anotação para fazer injeção de dependência */
	private IDaoAluno repositorio;
	
	@GetMapping("/cadAluno") /* Anotação para solicitar dados */
	public ModelAndView retornaViewCadAluno(Aluno aluno)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/cadAluno");
		mv.addObject("aluno", new Aluno());
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
	
	@GetMapping("/altAluno/{id}")
	public ModelAndView retornaViewAlterar(@PathVariable("id") Integer id)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/altAluno");
		mv.addObject("aluno", repositorio.getById(id));
		return mv;
	}
	
	@GetMapping("/rmAluno/{id}")
	public String removerAluno(@PathVariable("id") Integer id)
	{
		repositorio.deleteById(id);
		return "redirect:/pesqAluno";
	}
	
	@PostMapping("cadastrarAluno") /* Anotação para enviar dados */
	public ModelAndView cadastrarAluno(@Valid Aluno aluno, BindingResult br)
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
	
	@PostMapping("alterarAluno")
	public ModelAndView alterarAluno(Aluno aluno)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/pesqAluno");
		repositorio.save(aluno);
		return mv;
	}
}
