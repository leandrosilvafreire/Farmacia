package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.leandro.farmacia.dao.FabricanteDao;
import br.com.leandro.farmacia.entidade.Fabricante;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void salvar() {
		try {
			// FabricanteDao fabricanteDao = new FabricanteDao();
			// fabricanteDao.merge(fabricante);

			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://localhost:8081/Farmacia/restful/fabricante");

			// Salva o fabricante
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);

			if (fabricante.getCodigo() == null) {
				// novo();
				// lista o novo fabricante
				caminho.request().post(Entity.json(json));
				Messages.addGlobalInfo("Fabricante cadastrado com sucesso.");
			} else {
				// novo();
				// atualiza na lista os novos dados do fabricante
				caminho.request().put(Entity.json(json));
				Messages.addFlashGlobalInfo("Fabricante atualizado com sucesso.");
			}
			// fabricantes = fabricanteDao.listar();
			// limpa a tela
			fabricante = new Fabricante();
			// lista um novo ou atualiza os dados do fabricante
			json = caminho.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			fabricantes = Arrays.asList(vetor);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o fabricante.");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {

			// FabricanteDao fabricanteDao = new FabricanteDao();
			// fabricantes = fabricanteDao.listar();

			// Primeiro o front-end vai chamar o serviço que vai chamar o dao
			Client cliente = ClientBuilder.newClient();// Cria um novo cliente
			// WebTarget garda o caminho e a variável target passa a url
			WebTarget caminho = cliente.target("http://localhost:8081/Farmacia/restful/fabricante");
			// dispara requisição vai chamar um json o get tem como parametro o tipo do get
			// no serviço;
			String json = caminho.request().get(String.class);

			Gson gson = new Gson();
			// Converte o resultado em um vetor de fabricantes
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			// Converte o vetor para ArrayList
			fabricantes = Arrays.asList(vetor);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os fabricantes.");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.excluir(fabricante);
			fabricantes = fabricanteDao.listar();
			/*
			 * Client cliente = ClientBuilder.newClient(); WebTarget caminho =
			 * cliente.target("http://localhost:8081/Farmacia/restful/fabricante");
			 * 
			 * Gson gson = new Gson(); String json = gson.toJson(fabricante.getCodigo());
			 * 
			 * caminho.request().delete(String.class);
			 * 
			 * fabricante = new Fabricante();
			 * 
			 * json = caminho.request().get(String.class); Fabricante[] vetor =
			 * gson.fromJson(json, Fabricante[].class); fabricantes = Arrays.asList(vetor);
			 */

			Messages.addGlobalInfo("Fabricante removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar remover o fabricante.");
		}
	}

	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}

}
