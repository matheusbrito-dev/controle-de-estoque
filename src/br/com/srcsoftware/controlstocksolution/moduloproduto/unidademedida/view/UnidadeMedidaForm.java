package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.view;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model.UnidadeMedidaPO;

public final class UnidadeMedidaForm extends ActionForm{

	private String idSelecionar;
	private UnidadeMedidaPO unidadeMedida;
	private ArrayList< UnidadeMedidaPO > unidadeMedidas;

	/** SINGLETON */
	public UnidadeMedidaPO getUnidadeMedida() {
		if ( unidadeMedida == null ) {
			unidadeMedida = new UnidadeMedidaPO();

		}
		return unidadeMedida;
	}

	public void setUnidadeMedida( UnidadeMedidaPO unidadeMedida ) {
		this.unidadeMedida = unidadeMedida;
	}

	public ArrayList< UnidadeMedidaPO > getUnidadeMedidas() {
		if ( unidadeMedidas == null ) {
			unidadeMedidas = new ArrayList< UnidadeMedidaPO >();

		}
		return unidadeMedidas;
	}

	public void setUnidadeMedidas( ArrayList< UnidadeMedidaPO > unidadeMedidas ) {
		this.unidadeMedidas = unidadeMedidas;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public void limparTela() {
		setIdSelecionar( null );
		setUnidadeMedida( null );
		getUnidadeMedidas().clear();
	}

}
