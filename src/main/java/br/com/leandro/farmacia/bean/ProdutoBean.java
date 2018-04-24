package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.FabricanteDao;
import br.com.leandro.farmacia.dao.ProdutoDao;
import br.com.leandro.farmacia.entidade.Fabricante;
import br.com.leandro.farmacia.entidade.Produto;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtos = produtoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar listar os produtos.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			produto = new Produto();
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar um novo produto.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.merge(produto);
			if (produto.getCodigo() == null) {
				novo();
				Messages.addFlashGlobalInfo("Produto salvo com sucesso.");
			} else {
				novo();
				Messages.addGlobalInfo("Produto atualizado com sucesso.");
			}
			produtos = produtoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar o produto.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produto);
			produtos = produtoDao.listar();
			Messages.addFlashGlobalInfo("Produto removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover o produto.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar o produto.");
			erro.printStackTrace();
		}
	}

}
