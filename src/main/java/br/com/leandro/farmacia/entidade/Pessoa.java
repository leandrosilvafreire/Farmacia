package br.com.leandro.farmacia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_pessoa")
public class Pessoa extends GenericoEntidade {

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;

	@Column(name = "rg", length = 15)
	private String rg;

	@Column(name = "rua", length = 90, nullable = false)
	private String rua;

	@Column(name = "numero", nullable = false)
	private Short numero;

	@Column(name = "bairro", length = 50)
	private String bairro;

	@Column(name = "cep", length = 12)
	private String cep;

	@Column(name = "complemento", length = 60)
	private String complemento;

	@Column(name = "celular", length = 16, nullable = false)
	private String celular;

	@Column(name = "telefone", length = 16)
	private String telefone;

	@Column(name = "email", length = 60)
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cidade", nullable = false)
	private Cidade cidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
