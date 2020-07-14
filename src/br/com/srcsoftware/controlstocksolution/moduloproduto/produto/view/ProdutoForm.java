package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.controller.UnidadeMedidaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class ProdutoForm extends ActionForm{

	private String idSelecionar;
	private ProdutoPO produto;
	private ArrayList< ProdutoPO > produtos;

	/** SINGLETON */
	public ProdutoPO getProduto() {
		if ( produto == null ) {
			produto = new ProdutoPO();

		}
		return produto;
	}

	public void setProduto( ProdutoPO produto ) {
		this.produto = produto;
	}

	public ArrayList< ProdutoPO > getProdutos() {
		if ( produtos == null ) {
			produtos = new ArrayList< ProdutoPO >();

		}
		return produtos;
	}

	public void setProdutos( ArrayList< ProdutoPO > produtos ) {
		this.produtos = produtos;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public ArrayList< LabelValueBean > getComboCategorias() {
		ArrayList< LabelValueBean > options = new ArrayList<>();

		try {
			CategoriaFACADE facade = new CategoriaFACADE();
			List< CategoriaPO > categorias = facade.filtrar( null );

			/** Montando a lista da LabelValueBean para enviar ao JSP */
			for ( CategoriaPO categoriaCorrente : categorias ) {
				LabelValueBean labelValue = new LabelValueBean();

				/** Preenchendo o Label com o valor que desejo exibir na Combo */
				labelValue.setLabel( categoriaCorrente.getNome() );
				/** Preenchendo o Label com o valor que desejo atribuir ao property */
				labelValue.setValue( categoriaCorrente.getIdToString() );

				options.add( labelValue );
			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		}
		return options;
	}

	public ArrayList< LabelValueBean > getComboUnidadeMedidas() {
		ArrayList< LabelValueBean > options = new ArrayList<>();

		try {
			UnidadeMedidaFACADE facade = new UnidadeMedidaFACADE();
			List< UnidadeMedidaPO > unidadeMedidas = facade.filtrar( null );

			/** Montando a lista da LabelValueBean para enviar ao JSP */
			for ( UnidadeMedidaPO unidadeMedidaCorrente : unidadeMedidas ) {
				LabelValueBean labelValue = new LabelValueBean();

				/** Preenchendo o Label com o valor que desejo exibir na Combo */
				labelValue.setLabel( unidadeMedidaCorrente.getNome() );
				/** Preenchendo o Label com o valor que desejo atribuir ao property */
				labelValue.setValue( unidadeMedidaCorrente.getIdToString() );

				options.add( labelValue );
			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		}
		return options;
	}

	public void limparTela() {
		setIdSelecionar( null );
		setProduto( null );
		getProdutos().clear();
	}

}
