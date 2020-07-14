package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class ProdutoDAO{

	public void inserir( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		ProdutoPO poInserido = (ProdutoPO) hibernate.inserir( po );

		/** Pegando o ID do Produto Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public ProdutoPO filtrarPorId( Long id ) throws BackendException {
		return (ProdutoPO) new HibernateConnection().filtrarPorId( id, ProdutoPO.class );
	}

	public List< ProdutoPO > filtrar( ProdutoPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( ProdutoPO.class );

			//Definindo o FROM
			Root root = criteria.from( ProdutoPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getNome() != null && poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
				if ( poFiltrar.getPreco() != null ) {
					Predicate precoParam = builder.equal( root.get( "preco" ), poFiltrar.getPreco() );
					predicados.add( precoParam );
				}

				/** Criando um JOIN com CategoriaPO */
				Join< ProdutoPO, CategoriaPO > joinCategoria = root.join( "categoria", JoinType.INNER );
				/*Sub join do join
				 * Join< CategoriaPO, SubCategoriaPO > joinSupCategoria = joinCategoria.join( "supCategoria", JoinType.INNER );*/
				if ( poFiltrar.getCategoria().getNome() != null && !poFiltrar.getCategoria().getNome().isEmpty() ) {
					Predicate nomeCategoriaParam = builder.like( joinCategoria.get( "nome" ), poFiltrar.getCategoria().getNome().concat( "%" ) );
					predicados.add( nomeCategoriaParam );
				}

				/** Criando um JOIN com UnidadeMedidaPO */
				Join< ProdutoPO, UnidadeMedidaPO > joinUnidadeMedida = root.join( "unidadeMedida", JoinType.INNER );
				/*Sub join do join
				 * Join< UnidadeMedidaPO, SubUnidadeMedidaPO > joinSupUnidadeMedida = joinUnidadeMedida.join( "supUnidadeMedida", JoinType.INNER );*/
				if ( poFiltrar.getUnidadeMedida().getNome() != null && !poFiltrar.getUnidadeMedida().getNome().isEmpty() ) {
					Predicate nomeUnidadeMedidaParam = builder.like( joinUnidadeMedida.get( "nome" ), poFiltrar.getUnidadeMedida().getNome().concat( "%" ) );
					predicados.add( nomeUnidadeMedidaParam );
				}

			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< ProdutoPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch (

		BackendException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar", e );
		}

	}

}
