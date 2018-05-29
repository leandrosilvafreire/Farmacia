package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.CidadeDao;
import br.com.leandro.farmacia.dao.EstadoDao;
import br.com.leandro.farmacia.entidade.Cidade;
import br.com.leandro.farmacia.entidade.Estado;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar listar as cidades.");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			cidade = new Cidade();
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listarCampo("sigla");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar uma nova cidade.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.merge(cidade);
			if (cidade.getCodigo() == null) {
				novo();
				Messages.addGlobalInfo("Cidade cadastrada com sucesso.");
			} else {
				novo();
				Messages.addGlobalInfo("Cidade atualizada com sucesso.");
			}
			cidades = cidadeDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar a cidade.");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.excluir(cidade);
			cidades = cidadeDao.listar();
			Messages.addGlobalInfo("Cidade removida com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover a cidade.");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar a cidade.");
			erro.printStackTrace();
		}

	}

}
