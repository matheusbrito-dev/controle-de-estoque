package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.testabackend;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class TestaBackEnd{

	public static void main( String[ ] args ) {

		try {

			/** Criando uma Categoria */
			CategoriaPO po = new CategoriaPO();
			po.setNome( "Bebidas" );
			po.setDataHoraCadrastro( LocalDateTime.now() );

			/** Inserir */
			new CategoriaFACADE().inserir( po );

			/** Filtrando todos para ver se inseriu */
			List encontrados = new CategoriaFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrando por ID para alterar */
			CategoriaPO encontrado = (CategoriaPO) new CategoriaFACADE().filtrarPorId( "1" );

			/** Alterando o Categoria encontrada */
			encontrado.setNome( "Bebidas alterada" );
			new CategoriaFACADE().alterar( encontrado );

			/** Filtrando por ID para verificar alteração e excluir */
			encontrado = (CategoriaPO) new CategoriaFACADE().filtrarPorId( "1" );

			/** Excluindo */
			new CategoriaFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new CategoriaFACADE().filtrar( null );
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
