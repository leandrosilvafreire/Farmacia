package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.EstadoDao;
import br.com.leandro.farmacia.entidade.Estado;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		estado = new Estado();
	}

	@SuppressWarnings("unused")
	public void salvar() {
		try {
			EstadoDao estadoDao = new EstadoDao();
			estadoDao.merge(estado);
			if (estado.getCodigo() == null) {
				novo();
				estados = estadoDao.listar();
				// Método utilizando o omnifaces
				Messages.addGlobalInfo("Estado cadastrado com sucesso!");
				// Antes do Omnifaces
				// String msg = "Leandro Silva Freire";
				// // Cria um objeto doi tipo faces message, tipo da informação detalhada e
				// resumida
				// FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
				// msg);
				//
				// // Captura a instância corrente do jsf e garda na variável contexto
				// FacesContext contexto = FacesContext.getCurrentInstance();
				// // Exbie no método a mensagem
				// contexto.addMessage(null, mensagem);
			} else {
				novo();
				estados = estadoDao.listar();
				Messages.addGlobalInfo("Estado atualizado com sucesso!");
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar cadastrar o estado!");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os estados");
			erro.printStackTrace();
		}

	}

	// Objeto serve para capturar coisas que me mandaram
	public void excluir(ActionEvent evento) {
		try {
			// Serve para pegar o componente o commandButton e pega todos os atributos o
			// objeto selecionado
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			EstadoDao estadoDao = new EstadoDao();
			estadoDao.excluir(estado);
			estados = estadoDao.listar();
			Messages.addGlobalInfo("Estado removido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao remover o estado!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}

}
