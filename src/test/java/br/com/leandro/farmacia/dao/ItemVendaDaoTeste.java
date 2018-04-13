package br.com.leandro.farmacia.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.ItemVenda;
import br.com.leandro.farmacia.entidade.Produto;
import br.com.leandro.farmacia.entidade.Venda;

public class ItemVendaDaoTeste {

	@Test
	@Ignore
	public void salvar() {
		ItemVenda itemVenda = new ItemVenda();

		Long codigoProduto = 1L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.consultar(codigoProduto);
		itemVenda.setProduto(produto);

		itemVenda.setQuantidade(new Short("1"));
		itemVenda.setPrecoParcial(new BigDecimal("1"));

		Long codigoVenda = 2L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.consultar(codigoVenda);
		itemVenda.setVenda(venda);

		ItemVendaDao itemVendaDao = new ItemVendaDao();
		itemVendaDao.salvar(itemVenda);

	}

	@Test
	@Ignore
	public void listar() {
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		List<ItemVenda> itemVendas = itemVendaDao.listar();

		System.out.println("\nTotal de registros = " + itemVendas.size());

		for (ItemVenda itemVenda : itemVendas) {
			System.out.println();
			System.out.println(
					itemVenda.getCodigo() + ". " + itemVenda.getProduto().getNome() + " - " + itemVenda.getQuantidade()
							+ " - R$" + itemVenda.getPrecoParcial() + " - R$" + itemVenda.getVenda().getPrecoTotal());
		}
	}

	@Test
	@Ignore
	public void consultar() {
		Long codigo = 4L;
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.consultar(codigo);

		if (itemVenda == null) {
			System.out.println("\nNenhum registro encontrado!");
		} else {
			System.out.println();
			System.out.println(
					itemVenda.getCodigo() + ". " + itemVenda.getProduto().getNome() + " - " + itemVenda.getQuantidade()
							+ " - R$" + itemVenda.getPrecoParcial() + " - R$" + itemVenda.getVenda().getPrecoTotal());
		}

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.consultar(codigo);
		
		if (itemVenda == null) {
			System.out.println("\nNenhum registro encontrado!");
		}else {
			itemVendaDao.excluir(itemVenda);
		}
		
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoItem = 2L;
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.consultar(codigoItem);
		
		if (itemVenda == null) {
			System.out.println("\nNenhum registro encontrado!");
		}else {
			System.out.println("Registro Anterior: ");
			System.out.println(
					itemVenda.getCodigo() + ". " + itemVenda.getProduto().getNome() + " - " + itemVenda.getQuantidade()
							+ " - R$" + itemVenda.getPrecoParcial() + " - R$" + itemVenda.getVenda().getPrecoTotal());
			
			Long codigoProduto = 2L;
			ProdutoDao produtoDao = new ProdutoDao();
			Produto produto = produtoDao.consultar(codigoProduto);
			itemVenda.setProduto(produto);
			
			itemVenda.setQuantidade(new Short("25"));
			itemVenda.setPrecoParcial(new BigDecimal("125.62"));
			
			Long codigoVenda = 2L;
			VendaDao vendaDao = new VendaDao();
			Venda venda = vendaDao.consultar(codigoVenda);
			itemVenda.setVenda(venda);
			
			itemVendaDao.editar(itemVenda);
			
			System.out.println("Registro Atual: ");
			System.out.println(
					itemVenda.getCodigo() + ". " + itemVenda.getProduto().getNome() + " - " + itemVenda.getQuantidade()
							+ " - R$" + itemVenda.getPrecoParcial() + " - R$" + itemVenda.getVenda().getPrecoTotal());
		}
		
		
	}

}
