package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.CategoriaDAO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * 
 * Classe que representa a camada de Negocio da categoria.
 * Nesta Classe são implementadas todas as regras de negócio necessárias
 * para garantir a consistencia dos dados contidos no PO e/ou
 * garantir o correto funcionamento do processo solicitado.
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 22:20:06
 * @version 1.0
 */
public final class CategoriaSERVICE implements Crud{
	/** Garante a aplicação da associação entre o SERVICE e o DAO */
	private final CategoriaDAO DAO;

	public CategoriaSERVICE(){
		//Quando o FACEDE instanciar o SERVICE automaticamente irá para o DAO
		DAO = new CategoriaDAO();
	}

	@Override
	public void inserir( final AbstractPO PO ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			CategoriaPO categoria = null;
			if ( PO instanceof CategoriaPO ) {
				categoria = (CategoriaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			if ( !categoria.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}

			DAO.inserir( hibernate, categoria );
			hibernate.commitTransacao();
			/** Fim do bloco de transação */

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
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			CategoriaPO categoria = null;
			if ( PO instanceof CategoriaPO ) {
				categoria = (CategoriaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			if ( !categoria.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}

			DAO.alterar( hibernate, categoria );
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
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			CategoriaPO categoria = null;
			if ( PO instanceof CategoriaPO ) {
				categoria = (CategoriaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			DAO.excluir( hibernate, categoria );
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
			CategoriaPO categoria = null;

			if ( PO != null ) {

				if ( PO instanceof CategoriaPO ) {
					categoria = (CategoriaPO) PO;
				} else {
					throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
				}
			}

			return DAO.filtrar( categoria );
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
				throw new BackendException( "ID nulo passado como parâmetro" );
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
