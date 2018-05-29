package br.com.leandro.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.leandro.farmacia.dao.PessoaDao;
import br.com.leandro.farmacia.dao.UsuarioDao;
import br.com.leandro.farmacia.entidade.Pessoa;
import br.com.leandro.farmacia.entidade.Usuario;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarios = usuarioDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar listar os usuários.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listarCampo("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.merge(usuario);
			if (usuario.getCodigo() == null) {
				novo();
				Messages.addFlashGlobalInfo("Usuário salvo com sucesso.");
			} else {
				novo();
				Messages.addFlashGlobalInfo("Usuário atualizado com sucesso.");
			}
			usuarios = usuarioDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar salvar o usuário.");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.excluir(usuario);
			usuarios = usuarioDao.listar();
			Messages.addFlashGlobalInfo("Usuário removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar remover o usuário.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar o usuário");
		}
	}

}
