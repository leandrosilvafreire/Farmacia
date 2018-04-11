package br.com.leandro.farmacia.main;

import org.hibernate.Session;

import br.com.leandro.farmacia.util.HibernateUtil;

public class HibernateUtilTeste {
	//metodo main para teste, mas o correto é usar o junit
	public static void main(String[] args) {
		// Objeto captura a fábrica de sessão e abre a sessão
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Encerra a sessão
		sessao.close();
		// Destroi a fábrica de sessões
		HibernateUtil.getFabricaSessoes().close();
	}

}
