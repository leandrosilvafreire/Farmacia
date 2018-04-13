package br.com.leandro.farmacia.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Pessoa;
import br.com.leandro.farmacia.entidade.Usuario;

public class UsuarioDaoTeste {
	
	@Test
	@Ignore
	public void salvar() {
		
		Usuario usuario = new Usuario();
		
		usuario.setAtivo(false);
		
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.consultar(2L);
		usuario.setPessoa(pessoa);
		
		usuario.setSenha("outro");
		usuario.setTipo('G');
		
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.salvar(usuario);		
	}
	
	@Test
	@Ignore
	public void listar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Usuario> usuarios = usuarioDao.listar();
		
		System.out.println("\nTotal de registros = " + usuarios.size());
		System.out.println();
		
		for(Usuario usuario : usuarios)
		{
			
			System.out.println(usuario.getCodigo() + ". " + usuario.getPessoa().getNome() + " - " + 
					usuario.getAtivo() + " - " + usuario.getSenha() + " - " + usuario.getTipo());
		}	
	}
	
	@Test
	@Ignore
	public void consultar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.consultar(6L);
		
		if(usuario==null)
		{
			System.out.println("\nNenhum registro encontrado!\n");
		}
		else
		{
			System.out.println();
			System.out.println(usuario.getCodigo() + ". " + usuario.getPessoa().getNome() + " - " + 
					usuario.getAtivo() + " - " + usuario.getSenha() + " - " + usuario.getTipo());
		}	
	}
	
	@Test
	@Ignore
	public void excluir() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.consultar(7L);
		
		if(usuario==null)
		{
			System.out.println("\nNenhum registro encontrado!\n");
		}
		else
		{
			usuarioDao.excluir(usuario);
		}	
	}
	
	@Test
	@Ignore
	public void editar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.consultar(2L);
			
		if(usuario==null)
		{
			System.out.println("\nNenhum registro encontrado!\n");
		}
		else
		{
			System.out.println("\nRegistro Anterior: ");
			System.out.println(usuario.getCodigo() + ". " + usuario.getPessoa().getNome() + " - " + 
					usuario.getAtivo() + " - " + usuario.getSenha() + " - " + usuario.getTipo());
			
			usuario.setAtivo(false);
			
			PessoaDao pessoaDao = new PessoaDao();
			Pessoa pessoa = pessoaDao.consultar(1L);
			usuario.setPessoa(pessoa);
			
			usuario.setSenha("7545455");
			usuario.setTipo('G');
			
			usuarioDao.editar(usuario);
			
			System.out.println("\nRegistro Atual: ");
			System.out.println(usuario.getCodigo() + ". " + usuario.getPessoa().getNome() + " - " + 
					usuario.getAtivo() + " - " + usuario.getSenha() + " - " + usuario.getTipo());
		}	
		
	}

}
