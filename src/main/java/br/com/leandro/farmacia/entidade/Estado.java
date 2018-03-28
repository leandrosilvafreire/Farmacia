package br.com.leandro.farmacia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_estado")
public class Estado extends GenericDomain {

	@Column(name = "sigla", length = 2, nullable = false, unique = true)
	private String sigla;

	@Column(name = "nome", length = 25, nullable = false, unique = true)
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
