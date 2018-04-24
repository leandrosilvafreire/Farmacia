package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

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
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.merge(fabricante);
			if (fabricante.getCodigo() == null) {
				novo();
				Messages.addGlobalInfo("Fabricante cadastrado com sucesso.");
			} else {
				novo();
				Messages.addGlobalInfo("Fabricante atualizado com sucesso.");
			}
			fabricantes = fabricanteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o fabricante.");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();
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
			Messages.addGlobalInfo("Fabricante removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar remover o fabricante.");
		}
	}

	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}

}
