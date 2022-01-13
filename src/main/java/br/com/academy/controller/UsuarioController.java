package br.com.academy.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.exceptions.UsuarioServiceException;
import br.com.academy.model.entity.Aluno;
import br.com.academy.model.entity.Usuario;
import br.com.academy.service.UsuarioService;
import br.com.academy.util.Util;

@Controller
public class UsuarioController
{
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		mv.addObject("home", true);
		return mv;
	}
	
	@GetMapping("/")
	public ModelAndView telaLogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		mv.addObject("usuario", new Usuario());
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
		if(br.hasErrors())
		{
			mv.setViewName("login/signup");
			mv.addObject("usuario");
		}
		else
		{
			mv.setViewName("redirect:/");
			try
			{
				service.salvar(usuario);
			}
			catch (Exception e)
			{
				mv.setViewName("login/signup");
				mv.addObject("usuario");
				mv.addObject("emailExistsExceptionMessage", e.getMessage());
			}
		}
		
		return mv;
	}
	
	@PostMapping("login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, UsuarioServiceException
	{
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors())
		{
			mv.setViewName("login/login");
			mv.addObject("usuario");
		}
		Usuario usuarioEncontrado = service.autenticar(usuario.getNomeUsuario(), Util.criptografarSenha(usuario.getSenha()));
		if(usuarioEncontrado == null )
		{
			if(!(br.hasFieldErrors("nomeUsuario") || br.hasFieldErrors("senha")))
			{
				mv.addObject("msg", "Usuário ou senha inválidos! Tente novamente.");
			}
		}
		else
		{
			session.setAttribute("usuarioLogado", usuarioEncontrado);
			return index();
		}
		return mv;
	}
	
	@PostMapping("logout")
	public ModelAndView logout(HttpSession session)
	{
		session.invalidate();
		return telaLogin();
	}
}
