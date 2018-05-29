package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.ClienteDao;
import br.com.leandro.farmacia.dao.PessoaDao;
import br.com.leandro.farmacia.entidade.Cliente;
import br.com.leandro.farmacia.entidade.Pessoa;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDao clienteDao = new ClienteDao();
			clientes = clienteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar listar os clientes.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listarCampo("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar um novo cliente.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.merge(cliente);
			if (cliente.getCodigo() == null) {
				novo();
				Messages.addFlashGlobalInfo("Cliente salvo com sucesso.");
			} else {
				novo();
				Messages.addFlashGlobalInfo("Cliente atualizado com sucesso.");
			}
			clientes = clienteDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar o cliente.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.excluir(cliente);
			clientes = clienteDao.listar();
			Messages.addFlashGlobalInfo("Cliente removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover o cliente");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar o cliente.");
			erro.printStackTrace();
		}

	}

}
