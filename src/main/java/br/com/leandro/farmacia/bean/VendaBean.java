package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.ProdutoDao;
import br.com.leandro.farmacia.entidade.ItemVenda;
import br.com.leandro.farmacia.entidade.Produto;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtos = produtoDao.listarCampo("codigo");
			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar carregar a tela de vendas.");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}
		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			// quantidade soma com + 1 + string e converte em short novamente, o short sempre quando soma  com
			// inteiro ele se transforma em inteiro, tendo que transformar ele em short novamente 
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			// BigDecimal não aceita operadores matemáticos, no lugar usa método
			// Nesse caso o método multiply só multiplica com BigDecimal então faz a conversão do short para Bigdecimal
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
	}
}
