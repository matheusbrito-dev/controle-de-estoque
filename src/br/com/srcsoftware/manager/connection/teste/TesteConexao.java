package br.com.srcsoftware.manager.connection.teste;

import org.junit.Test;

import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TesteConexao{

	@Test
	public void executar() throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			// Faça o que tem que faze
			Object versaoBD = hibernate.getCurrentSession().createNativeQuery( "select @@version" ).getSingleResult();
			System.out.println( versaoBD );

			Object dataBD = hibernate.getCurrentSession().createNativeQuery( "select now()" ).getSingleResult();
			System.out.println( dataBD );

			//Selecionando Categorias com SQL Nativa
			Object categoriasSQL = hibernate.getCurrentSession().createNativeQuery( "select * from categorias " ).getResultList();
			System.out.println( categoriasSQL );

			//Selecionando Categorias com HQL
			Object categoriasHQL = hibernate.getCurrentSession().createQuery( "select c from CategoriaPO c" ).getResultList();
			System.out.println( categoriasHQL );

			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();
		} finally {
			hibernate.destroy();
		}
	}

}
