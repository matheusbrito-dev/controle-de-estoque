package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.testa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.ProdutoDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestaDAO{

	@Test
	public void executar() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Testando o componente de Conexão no contexto do ProdutoDAO */
			ProdutoDAO dao = new ProdutoDAO();

			/** Inserindo ocom base no componente */

			/** Inserindo com base no componente */
			hibernate.iniciarTransacao();

			ProdutoPO po = new ProdutoPO();
			po.setDataHoraCadrastro( LocalDateTime.now() );
			po.setNome( "Coca-cola" );
			po.setPreco( new BigDecimal( "8.90" ) );
			po.setCategoria( criarCategoria() );

			System.out.println( po );
			po = (ProdutoPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando com base no componente */
			hibernate.iniciarTransacao();
			po.setNome( "Coca-cola Alterada" );
			hibernate.alterar( po );
			System.out.println( po );
			hibernate.commitTransacao();

			/** Testando o filtrar por ID */
			AbstractPO encontrado = hibernate.filtrarPorId( Long.valueOf( "0" ), ProdutoPO.class );
			System.out.println( "XXXXXXXXXXXXXXXX" + encontrado );

			/** Testando o Filtrar */
			ProdutoPO poFiltrar = new ProdutoPO();
			poFiltrar.setNome( "Coca-cola" );
			List< ProdutoPO > encontrados = dao.filtrar( poFiltrar );
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

	private CategoriaPO criarCategoria() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		/** Inserindo com base no componente */
		hibernate.iniciarTransacao();

		CategoriaPO po = new CategoriaPO();
		po.setNome( "Coca-cola" );
		po.setDataHoraCadrastro( LocalDateTime.now() );

		System.out.println( po );
		po = (CategoriaPO) hibernate.inserir( po );
		System.out.println( po );

		hibernate.commitTransacao();

		return po;
	}
}
