package br.com.leandro.farmacia.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
			fabricantes = fabricanteDao.listarCampo("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar um novo produto.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			// if (produto.getCaminho() == null) {
			// Messages.addFlashGlobalError("O campo foto é obrigatório!");
			// return;
			// }
			ProdutoDao produtoDao = new ProdutoDao();
			// Crie uma váriavel temporária
			Produto produtoTemporario = produtoDao.merge(produto);
			// pega o local de origem
			Path origem = Paths.get(produto.getCaminho());
			// pega o local de destino
			Path destino = Paths.get("C:/Desenvolvimento/Uploads/" + produtoTemporario.getCodigo() + ".jpg");
			// Copia a origem para o destino
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			if (produto.getCodigo() == null) {
				novo();
				Messages.addFlashGlobalInfo("Produto salvo com sucesso.");
			} else {
				novo();
				Messages.addGlobalInfo("Produto atualizado com sucesso.");
			}
			produtos = produtoDao.listar();
			// tipo uma condição OU(OR)
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar o produto.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produto);
			// destino do arquivo
			Path destino = Paths.get("C:/Desenvolvimento/Uploads/" + produto.getCodigo() + ".jpg");
			// deleta o produto com imagem
			Files.deleteIfExists(destino);

			produtos = produtoDao.listar();
			Messages.addFlashGlobalInfo("Produto removido com sucesso.");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover o produto.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			//Quando for editar o caminho de origem e destino é o mesmo
			produto.setCaminho("C:/Desenvolvimento/Uploads/" + produto.getCodigo() + ".jpg");
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar o produto.");
			erro.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void upload(FileUploadEvent evento) {
		try {
			/*
			 * Ao inves de usar os arquivos temporarios do tomcat usar os arquivos
			 * temporarios do sistema operacional no caso do windows o appData
			 */

			// pega o arquivo que acabou de fazer o upload biblioteca do primefaces
			// UploadedFile
			UploadedFile arquivoUpload = evento.getFile();
			// cria um arquivo temporario dentro do sistema operacional, os dois parametros
			// é bom deixar null
			// nome do arquivo temporario e extensão, Path é um recurso onde vc guarda a
			// referência do arquivo,
			// como se fosse o caminho do arquivo
			Path arquivoTemporario = Files.createTempFile(null, null);
			// copia o arquivo origem (arquivoUpload) para o arquivo destino
			// (arquivoTemporario)
			// parametros inputStream entrada origem, path destino, copy formato de cópia
			// o getInputStream pega os bytes do arquivo origem
			Files.copy(arquivoUpload.getInputstream(), arquivoTemporario, StandardCopyOption.REPLACE_EXISTING);
			// produto guarda o caminho do destino
			produto.setCaminho(arquivoTemporario.toString());
			System.out.println("Upload realizado com sucesso!\n");
		} catch (IOException erro) {
			Messages.addFlashGlobalError("Erro ao tentar realizar o upload do arquivo.");
			erro.printStackTrace();
		}
	}

}
