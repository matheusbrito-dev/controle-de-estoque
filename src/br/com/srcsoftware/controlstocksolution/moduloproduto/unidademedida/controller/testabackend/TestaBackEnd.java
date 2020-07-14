package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.controller.testabackend;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.controller.UnidadeMedidaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class TestaBackEnd{

	public static void main( String[ ] args ) {

		try {

			/** Criando uma UnidadeMedida */
			UnidadeMedidaPO po = new UnidadeMedidaPO();
			po.setNome( "Litros" );
			po.setDataHoraCadrastro( LocalDateTime.now() );
			po.setSigla( "L" );

			/** Inserir */
			new UnidadeMedidaFACADE().inserir( po );

			/** Filtrando todos para ver se inseriu */
			List encontrados = new UnidadeMedidaFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrando por ID para alterar */
			UnidadeMedidaPO encontrado = (UnidadeMedidaPO) new UnidadeMedidaFACADE().filtrarPorId( "1" );

			/** Alterando o UnidadeMedida encontrada */
			encontrado.setNome( "Bebidas alterada" );
			new UnidadeMedidaFACADE().alterar( encontrado );

			/** Filtrando por ID para verificar alteração e excluir */
			encontrado = (UnidadeMedidaPO) new UnidadeMedidaFACADE().filtrarPorId( "1" );

			/** Excluindo */
			new UnidadeMedidaFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new UnidadeMedidaFACADE().filtrar( null );
			System.out.println( encontrados );
		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}

	}

}
