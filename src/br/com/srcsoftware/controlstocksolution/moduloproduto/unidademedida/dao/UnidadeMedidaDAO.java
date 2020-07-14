package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class UnidadeMedidaDAO{

	public void inserir( HibernateConnection hibernate, UnidadeMedidaPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		UnidadeMedidaPO poInserido = (UnidadeMedidaPO) hibernate.inserir( po );

		/** Pegando o ID do UnidadeMedida Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, UnidadeMedidaPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, UnidadeMedidaPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public UnidadeMedidaPO filtrarPorId( Long id ) throws BackendException {
		return (UnidadeMedidaPO) new HibernateConnection().filtrarPorId( id, UnidadeMedidaPO.class );
	}

	public List< UnidadeMedidaPO > filtrar( UnidadeMedidaPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( UnidadeMedidaPO.class );

			//Definindo o FROM
			Root root = criteria.from( UnidadeMedidaPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getNome() != null && poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
				if ( poFiltrar.getSigla() != null && poFiltrar.getSigla().isEmpty() ) {
					Predicate siglaParam = builder.like( root.get( "sigla" ), poFiltrar.getSigla().concat( "%" ) );
					predicados.add( siglaParam );
				}
			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< UnidadeMedidaPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

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
