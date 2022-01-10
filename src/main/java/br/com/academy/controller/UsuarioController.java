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
	@GetMapping("/cadastro")
	public ModelAndView telaCadastro()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("cadastrarUsuario")
	public ModelAndView signup(Usuario usuario)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		try
		{
			service.salvar(usuario);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@PostMapping("efetuarLogin")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, UsuarioServiceException
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if(br.hasErrors())
		{
			mv.setViewName("login/login");
		}
		Usuario usuarioEncontrado = service.autenticar(usuario.getNomeUsuario(), Util.criptografarSenha(usuario.getSenha()));
		if(usuarioEncontrado == null)
		{
			mv.addObject("msg", "Usuário não encontrado! Tente novamente.");
		}
		else
		{
			session.setAttribute("usuarioLogado", usuarioEncontrado);
			return index();
		}
		return mv;
	}
	
	@PostMapping("efetuarLogout")
	public ModelAndView logout(HttpSession session)
	{
		session.invalidate();
		return telaLogin();
	}
}
