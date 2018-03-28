package br.com.leandro.farmacia.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTeste {
	//Método para teste usando o JUnit
	@Test
	public void testar() {
		// Objeto captura a fábrica de sessão e abre a sessão
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Encerra a sessão
		sessao.close();
		// Destroi a fábrica de sessões
		HibernateUtil.getFabricaSessoes().close();
	}
}
