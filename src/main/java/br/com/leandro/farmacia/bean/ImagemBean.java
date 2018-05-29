package br.com.leandro.farmacia.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class ImagemBean {

	@ManagedProperty("#{param.caminho}")
	private String caminho;
	private StreamedContent foto;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()) {
			Path destino = Paths.get("C:/Desenvolvimento/Uploads/branco.jpg");
			InputStream tamanho = Files.newInputStream(destino);
			foto = new DefaultStreamedContent(tamanho);
		}else {
			Path destino = Paths.get(caminho);
			InputStream tamanho = Files.newInputStream(destino);
			foto = new DefaultStreamedContent(tamanho);
		}
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
