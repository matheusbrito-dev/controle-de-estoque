package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.dao.testadao;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.dao.UnidadeMedidaDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestaDao{
	@Test
	public void executar() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Testando o componente de Conexão no contexto do UnidadeMedidaDAO */
			UnidadeMedidaDAO dao = new UnidadeMedidaDAO();

			/** Inserindo ocom base no componente */

			/** Inserindo com base no componente */
			hibernate.iniciarTransacao();

			UnidadeMedidaPO po = new UnidadeMedidaPO();
			po.setNome( "Tonelada" );
			po.setDataHoraCadrastro( LocalDateTime.now() );
			po.setSigla( "TON" );

			System.out.println( po );
			po = (UnidadeMedidaPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando com base no componente */
			hibernate.iniciarTransacao();
			po.setNome( "Toneladas alterada" );
			hibernate.alterar( po );
			System.out.println( po );
			hibernate.commitTransacao();

			/** Testando o filtrar por ID */
			AbstractPO encontrado = hibernate.filtrarPorId( Long.valueOf( "0" ), UnidadeMedidaPO.class );
			System.out.println( "XXXXXXXXXXXXXXXX" + encontrado );

			/** Testando o Filtrar */
			UnidadeMedidaPO poFiltrar = new UnidadeMedidaPO();
			poFiltrar.setNome( "Tonelada" );
			List< UnidadeMedidaPO > encontrados = dao.filtrar( poFiltrar );
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
