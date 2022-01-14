package br.com.academy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.entity.Aluno;
import br.com.academy.model.entity.Usuario;
import br.com.academy.service.UsuarioService;

@Controller
public class UsuarioController
{
	@Autowired
	private UsuarioService service;

	@GetMapping("/index")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		mv.addObject("home", true);
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView telaLogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}

	@GetMapping("/signup")
	public ModelAndView telaCadastro()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/signup");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@PostMapping("cadastroUsuario")
	public ModelAndView signup(@Valid Usuario usuario, BindingResult br)
	{
		ModelAndView mv = new ModelAndView();
		if (br.hasErrors())
		{
			mv.setViewName("login/signup");
			mv.addObject("usuario");
		}
		else
		{
			mv.setViewName("redirect:/login");
			try
			{
				service.salvar(usuario);
			}
			catch (Exception e)
			{
				mv.setViewName("login/signup");
				mv.addObject("usuario");
				mv.addObject("signupExceptionMessage", e.getMessage());
			}
		}

		return mv;
	}

	/*
	 * @PostMapping("logout") public ModelAndView logout(HttpSession session) {
	 * session.invalidate(); return telaLogin(); }
	 */
}
