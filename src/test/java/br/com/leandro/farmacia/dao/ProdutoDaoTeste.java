package br.com.leandro.farmacia.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Fabricante;
import br.com.leandro.farmacia.entidade.Produto;

public class ProdutoDaoTeste {
	
	@Test
	@Ignore
	public void salvar() {
		
		Produto produto = new Produto();
		produto.setNome("Neosaldina");
		produto.setPreco(new BigDecimal("2"));
		produto.setQuantidade(new Short("5"));
		
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.consultar(new Long("1"));
		produto.setFabricante(fabricante);
		
		ProdutoDao produtoDao = new ProdutoDao();
	    produtoDao.salvar(produto);
		
		
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDao produtoDao = new  ProdutoDao();
		List<Produto> produtos = produtoDao.listar();
		
		System.out.println("Total de registros = " + produtos.size());
		
		for (Produto produto : produtos) {
			
			System.out.println();
			System.out.println(produto.getCodigo());
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());
			System.out.println(produto.getFabricante().getNome());
			System.out.println();
			
		}
	}
	
	@Test
	@Ignore
	public void consultar() {
		Long codigo = 5L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.consultar(codigo);
		
		if(produto==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println();
			System.out.println(produto.getCodigo());
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());
			System.out.println(produto.getFabricante().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.consultar(codigo);
		
		if(produto==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			produtoDao.excluir(produto);
		}

	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoProduto = 2L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.consultar(codigoProduto);
		
		if(produto==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println();
			System.out.println("Registro Anterior!");
			System.out.println(produto.getCodigo());
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());
			System.out.println(produto.getFabricante().getNome());
			System.out.println();
			
			Long codigoFabricante = 3L;
			FabricanteDao fabricanteDao = new FabricanteDao();
			Fabricante fabricante = fabricanteDao.consultar(codigoFabricante);
			
			produto.setFabricante(fabricante);
			produto.setNome("Diclofenaco");
			produto.setPreco(new BigDecimal("26.87"));
			produto.setQuantidade(new Short("2048"));
			produtoDao.editar(produto);
			
			System.out.println();
			System.out.println("Registro Atual!");
			System.out.println(produto.getCodigo());
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());
			System.out.println(produto.getFabricante().getNome());
			System.out.println();
			
			
			
		}
	}
}
