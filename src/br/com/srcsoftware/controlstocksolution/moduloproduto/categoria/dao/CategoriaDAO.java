package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class CategoriaDAO{

	public void inserir( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		CategoriaPO poInserido = (CategoriaPO) hibernate.inserir( po );

		/** Pegando o ID do Categoria Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public CategoriaPO filtrarPorId( Long id ) throws BackendException {
		return (CategoriaPO) new HibernateConnection().filtrarPorId( id, CategoriaPO.class );
	}

	public List< CategoriaPO > filtrar( CategoriaPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( CategoriaPO.class );

			//Definindo o FROM
			Root root = criteria.from( CategoriaPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getNome() != null && poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< CategoriaPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( BackendException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar", e );
		}

	}

}
