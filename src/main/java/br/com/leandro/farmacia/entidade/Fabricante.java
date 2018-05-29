package br.com.leandro.farmacia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_fabricante")
public class Fabricante extends GenericoEntidade {

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
