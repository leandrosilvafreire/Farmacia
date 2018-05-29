package br.com.leandro.farmacia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_usuario")
public class Usuario extends GenericoEntidade {

	@Column(name = "senha", length = 32, nullable = false)
	private String senha;

	@Column(name = "tipo", nullable = false)
	private Character tipo;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa", nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;

		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipo == 'B') {
			tipoFormatado = "Balconista";
		} else {
			tipoFormatado = "Gerente";
		}

		return tipoFormatado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";
		if (ativo == true) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
