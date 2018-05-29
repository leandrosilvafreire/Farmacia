package br.com.leandro.farmacia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="farmacia_cidade")
public class Cidade extends GenericoEntidade{
	
	@Column(name= "nome", length=60, nullable=false)
	private String nome;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "estado", nullable=false)
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
	
	

	
	
}
