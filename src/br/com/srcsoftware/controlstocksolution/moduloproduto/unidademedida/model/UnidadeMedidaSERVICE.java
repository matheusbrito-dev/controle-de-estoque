package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.dao.UnidadeMedidaDAO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * 
 * Classe que representa a camada de Negocio da unidadeMedida.
 * Nesta Classe s�o implementadas todas as regras de neg�cio necess�rias
 * para garantir a consistencia dos dados contidos no PO e/ou
 * garantir o correto funcionamento do processo solicitado.
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 22:20:06
 * @version 1.0
 */
public final class UnidadeMedidaSERVICE implements Crud{
	/** Garante a aplica��o da associa��o entre o SERVICE e o DAO */
	private final UnidadeMedidaDAO DAO;

	public UnidadeMedidaSERVICE(){
		//Quando o FACEDE instanciar o SERVICE automaticamente ir� para o DAO
		DAO = new UnidadeMedidaDAO();
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
			UnidadeMedidaPO unidadeMedida = null;
			if ( PO instanceof UnidadeMedidaPO ) {
				unidadeMedida = (UnidadeMedidaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			if ( !unidadeMedida.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) {
				throw new BackendException( "No nome n�o s�o permitidos caracteres num�ricos!" );
			}

			DAO.inserir( hibernate, unidadeMedida );
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
			UnidadeMedidaPO unidadeMedida = null;
			if ( PO instanceof UnidadeMedidaPO ) {
				unidadeMedida = (UnidadeMedidaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			if ( !unidadeMedida.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) {
				throw new BackendException( "No nome n�o s�o permitidos caracteres num�ricos!" );
			}

			DAO.alterar( hibernate, unidadeMedida );
			hibernate.commitTransacao();

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
			UnidadeMedidaPO unidadeMedida = null;
			if ( PO instanceof UnidadeMedidaPO ) {
				unidadeMedida = (UnidadeMedidaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
			}

			DAO.excluir( hibernate, unidadeMedida );
			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

	@Override
	public List filtrar( final AbstractPO PO ) throws BackendException {
		try {
			UnidadeMedidaPO unidadeMedida = null;

			if ( PO != null ) {

				if ( PO instanceof UnidadeMedidaPO ) {
					unidadeMedida = (UnidadeMedidaPO) PO;
				} else {
					throw new BackendException( "O Objeto PO passado n�o condiz com o contexto!" );
				}
			}

			return DAO.filtrar( unidadeMedida );
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

			System.out.println( "SERVICE: filtrando por id" );

			return DAO.filtrarPorId( Long.valueOf( ID ) );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

}
