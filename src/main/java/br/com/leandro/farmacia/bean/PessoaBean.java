package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.CidadeDao;
import br.com.leandro.farmacia.dao.EstadoDao;
import br.com.leandro.farmacia.dao.PessoaDao;
import br.com.leandro.farmacia.entidade.Cidade;
import br.com.leandro.farmacia.entidade.Estado;
import br.com.leandro.farmacia.entidade.Pessoa;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private Estado estado;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar listar as pessoas.");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			estado = new Estado();
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listarCampo("sigla");
			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar uma nova pessoa.");
			erro.printStackTrace();
		}
	}

	public void listarCidadePorEstado() {
		try {
			if (estado != null) {
				CidadeDao cidadeDao = new CidadeDao();
				cidades = cidadeDao.listarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar filtrar as cidades.");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.merge(pessoa);
			if (pessoa.getCodigo() == null) {
				novo();
				Messages.addFlashGlobalInfo("Pessoa salva com sucesso.");
			} else {
				novo();
				Messages.addFlashGlobalInfo("Pessoa atualizada com sucesso.");
			}
			pessoas = pessoaDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar a pessoa.");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.excluir(pessoa);
			pessoas = pessoaDao.listar();
			Messages.addFlashGlobalInfo("Pessoa removida com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover a pessoa.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			estado = pessoa.getCidade().getEstado();
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listarCampo("sigla");
			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.listarPorEstado(estado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar a pessoa.");
			erro.printStackTrace();
		}
	}
}
