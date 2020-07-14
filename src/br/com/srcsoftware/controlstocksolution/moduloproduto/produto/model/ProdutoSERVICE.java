package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.ProdutoDAO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;

/**
 * 
 * Classe que representa a camada de Negocio da produto.
 * Nesta Classe s�o implementadas todas as regras de neg�cio necess�rias
 * para garantir a consistencia dos dados contidos no PO e/ou
 * garantir o correto funcionamento do processo solicitado.
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 22:16:22
 * @version 1.0
 */

public final class ProdutoSERVICE implements Crud{
	/** Garante a aplica��o da associa��o entre o SERVICE e o DAO */
	/**
	 * Toda constante deve possuir um valor definido
	 * Os unicos lugares poss�veis de inicializa��o de uma constante
	 * s�o:
	 * - No ato da DECLA��O ex:("ProdutoDAO DAO = new ProdutoDAO()")
	 * - Na PRIMEIRA LINHA DO CONSTRUTOR;
	 * ------------ProdutoSERVICE(){
	 * ---------------DAO = new ProdutoDAO();
	 * ------------}
	 * -Em um Bloco Est�tico
	 * ------------static{
	 * ------------------DAO = new ProdutoDAO();
	 * ------------}
	 */
	private final ProdutoDAO DAO;

	public ProdutoSERVICE(){
		//Quando o FACEDE instanciar o SERVICE automaticamente ir� para o DAO
		DAO = new ProdutoDAO();
	}

	@Override
	public void inserir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transa��o */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			ProdutoPO produto = null;
			if ( PO instanceof ProdutoPO ) {
				produto = (ProdutoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			DAO.inserir( hibernate, produto );
			hibernate.commitTransacao();
			/** Fim do bloco de transa��o */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao inserir ", e );
		}

	}

	@Override
	public void alterar( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transa��o */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			ProdutoPO produto = null;
			if ( PO instanceof ProdutoPO ) {
				produto = (ProdutoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			DAO.alterar( hibernate, produto );
			hibernate.commitTransacao();
			/** Fim do bloco de transa��o */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao alterar ", e );
		}

	}

	@Override
	public void excluir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transa��o */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			ProdutoPO produto = null;
			if ( PO instanceof ProdutoPO ) {
				produto = (ProdutoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			DAO.excluir( hibernate, produto );
			hibernate.commitTransacao();
			/** Fim do bloco de transa��o */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

	@Override
	public List filtrar( final AbstractPO PO ) throws BackendException {
		try {
			ProdutoPO produto = null;

			if ( PO != null ) {

				if ( PO instanceof ProdutoPO ) {
					produto = (ProdutoPO) PO;
				} else {
					throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
				}
				
			}
			return DAO.filtrar( produto );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}
	}

	@Override
	public AbstractPO filtrarPorId( final String ID ) throws BackendException {
		try {

			if ( ID == null ) {
				throw new BackendException( "ID nulo passado como par�metro" );
			}

			return DAO.filtrarPorId( Long.valueOf( ID ) );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

}
