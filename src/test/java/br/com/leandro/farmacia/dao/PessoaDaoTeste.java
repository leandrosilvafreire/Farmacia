package br.com.leandro.farmacia.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Cidade;
import br.com.leandro.farmacia.entidade.Pessoa;

public class PessoaDaoTeste {
	
	@Test
	@Ignore
	public void salvar() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setBairro("Centro");
		pessoa.setCelular("(16)9.9897-2032");
		pessoa.setCep("13.152-563");
		
		Long codigo = 2L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.consultar(codigo);
		pessoa.setCidade(cidade);
		
		
		pessoa.setCpf("896.527.896-12");
		pessoa.setEmail("roberto@gmail.com");
		pessoa.setNome("Roberto Alfaiate Pereira");
		pessoa.setNumero(new Short("520"));
		pessoa.setRg("325.9874.201-5");
		pessoa.setRua("Rua  Adamastor Elizeu");
		pessoa.setTelefone("(16)3752-8956");
		
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.salvar(pessoa);
	

	}
	
	@Test
	@Ignore
	public void listar() {
		PessoaDao pessoaDao = new PessoaDao();
		List<Pessoa> pessoas = pessoaDao.listar();
		
		System.out.println("Total de registros = " + pessoas.size() + "\n");
		
		for(Pessoa pessoa : pessoas)
		{
			System.out.println();
			System.out.println(pessoa.getCodigo());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getCpf());
			System.out.println(pessoa.getRg());
			System.out.println(pessoa.getRua());
			System.out.println(pessoa.getNumero());
			System.out.println(pessoa.getBairro());
			System.out.println(pessoa.getComplemento());
			System.out.println(pessoa.getCidade().getNome());
			System.out.println(pessoa.getCidade().getEstado().getSigla());
			System.out.println(pessoa.getCep());
			System.out.println(pessoa.getCelular());
			System.out.println(pessoa.getTelefone());
			System.out.println(pessoa.getEmail());
			System.out.println();
		}
		
		
	}
	
	@Test
	@Ignore
	public void consultar() {
		Long codigo = 2L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa =  pessoaDao.consultar(codigo);
		
		if(pessoa==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println();
			System.out.println(pessoa.getCodigo());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getCpf());
			System.out.println(pessoa.getRg());
			System.out.println(pessoa.getRua());
			System.out.println(pessoa.getNumero());
			System.out.println(pessoa.getBairro());
			System.out.println(pessoa.getComplemento());
			System.out.println(pessoa.getCidade().getNome());
			System.out.println(pessoa.getCidade().getEstado().getSigla());
			System.out.println(pessoa.getCep());
			System.out.println(pessoa.getCelular());
			System.out.println(pessoa.getTelefone());
			System.out.println(pessoa.getEmail());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa =  pessoaDao.consultar(codigo);
		
		if(pessoa==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			pessoaDao.excluir(pessoa);
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoPessoa = 1L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa =  pessoaDao.consultar(codigoPessoa);
		
		if(pessoa==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println();
			System.out.println("Registro Anterior");
			System.out.println(pessoa.getCodigo());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getCpf());
			System.out.println(pessoa.getRg());
			System.out.println(pessoa.getRua());
			System.out.println(pessoa.getNumero());
			System.out.println(pessoa.getBairro());
			System.out.println(pessoa.getComplemento());
			System.out.println(pessoa.getCidade().getNome());
			System.out.println(pessoa.getCidade().getEstado().getSigla());
			System.out.println(pessoa.getCep());
			System.out.println(pessoa.getCelular());
			System.out.println(pessoa.getTelefone());
			System.out.println(pessoa.getEmail());
			System.out.println();
			pessoa.setBairro("Vila Nova");
			pessoa.setCelular("(16)9.9526-8410");
			pessoa.setCep("12.520.552");
			pessoa.setNome("Rita Santos Berenice");
			pessoa.setCpf("123.456.789-52");
			pessoa.setRg("52.968.710-2");
			pessoa.setRua("Rua  Mara Ricardo");
			pessoa.setNumero(new Short("125"));
			pessoa.setBairro("Vila Madalena");
			
			Long codigoCidade = 3L;
			CidadeDao cidadeDao = new CidadeDao();
			Cidade cidade = cidadeDao.consultar(codigoCidade);
			pessoa.setCidade(cidade);
			
			pessoa.setTelefone("(16)5623-8596");
			pessoa.setEmail("ritasantos@hotmail.com");
			
			pessoaDao.editar(pessoa);
			
			System.out.println();
			System.out.println("Registro Atual");
			System.out.println(pessoa.getCodigo());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getCpf());
			System.out.println(pessoa.getRg());
			System.out.println(pessoa.getRua());
			System.out.println(pessoa.getNumero());
			System.out.println(pessoa.getBairro());
			System.out.println(pessoa.getComplemento());
			System.out.println(pessoa.getCidade().getNome());
			System.out.println(pessoa.getCidade().getEstado().getSigla());
			System.out.println(pessoa.getCep());
			System.out.println(pessoa.getCelular());
			System.out.println(pessoa.getTelefone());
			System.out.println(pessoa.getEmail());
			System.out.println();
		}
		
	}

}
