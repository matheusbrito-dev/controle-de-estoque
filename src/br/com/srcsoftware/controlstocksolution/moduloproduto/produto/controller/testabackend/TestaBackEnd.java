package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.testabackend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.ProdutoFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestaBackEnd{

	public static void main( String[ ] args ) {

		try {

			/** Criando uma Produto */
			ProdutoPO po = new ProdutoPO();
			po.setNome( "Coca-cola" );
			po.setDataHoraCadrastro( LocalDateTime.now() );
			po.setPreco( new BigDecimal( "8.80" ) );
			/*po.setUnidadeMedida( "UN" );*/
			po.setCategoria( criarCategoria() );

			/** Inserir */
			new ProdutoFACADE().inserir( po );

			/** Filtrando todos para ver se inseriu */
			List encontrados = new ProdutoFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrando por ID para alterar */
			ProdutoPO encontrado = (ProdutoPO) new ProdutoFACADE().filtrarPorId( "1" );

			/** Alterando o Produto encontrada */
			encontrado.setNome( "Coca-cola alterada" );
			new ProdutoFACADE().alterar( encontrado );

			/** Filtrando por ID para verificar alteração e excluir */
			encontrado = (ProdutoPO) new ProdutoFACADE().filtrarPorId( "1" );

			/** Excluindo */
			new ProdutoFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new ProdutoFACADE().filtrar( null );
			System.out.println( encontrados );
		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}

	}

	private static CategoriaPO criarCategoria() throws BackendException {

		CategoriaPO po = new CategoriaPO();
		po.setDataHoraCadrastro( LocalDateTime.now() );
		po.setNome( "Bebidas" );

		new CategoriaFACADE().inserir( po );

		return po;
	}

}
