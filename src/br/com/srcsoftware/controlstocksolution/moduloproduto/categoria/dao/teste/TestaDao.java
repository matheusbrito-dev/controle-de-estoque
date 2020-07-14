package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.teste;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.CategoriaDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestaDao{
	@Test
	public void executar() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Testando o componente de Conexão no contexto do CategoriaDAO */
			CategoriaDAO dao = new CategoriaDAO();

			/** Inserindo ocom base no componente */

			/** Inserindo com base no componente */
			hibernate.iniciarTransacao();

			CategoriaPO po = new CategoriaPO();
			po.setNome( "Laticinios" );
			po.setDataHoraCadrastro( LocalDateTime.now() );

			System.out.println( po );
			po = (CategoriaPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando com base no componente */
			hibernate.iniciarTransacao();
			po.setNome( "Laticinios Alterado" );
			hibernate.alterar( po );
			System.out.println( po );
			hibernate.commitTransacao();

			/** Testando o filtrar por ID */
			AbstractPO encontrado = hibernate.filtrarPorId( Long.valueOf( "0" ), CategoriaPO.class );
			System.out.println( "XXXXXXXXXXXXXXXX" + encontrado );

			/** Testando o Filtrar */
			CategoriaPO poFiltrar = new CategoriaPO();
			poFiltrar.setNome( "Laticinios" );
			List< CategoriaPO > encontrados = dao.filtrar( poFiltrar );
			System.out.println( "************Filtrar" + encontrados );

			/** Excluindo baseado no Componente */
			/*hibernate.iniciarTransacao();
			hibernate.excluir( po );
			hibernate.commitTransacao();*/

		} catch ( BackendException e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();

		}
	}

}
