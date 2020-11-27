package dad.javafx.micv.contacto.model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefono {
	private StringProperty numero = new SimpleStringProperty();
	private ObjectProperty<TipoTelefono> tipo = new SimpleObjectProperty<TipoTelefono>();

	public Telefono() {
		super();
	}


	public final StringProperty numeroProperty() {
		return this.numero;
	}
	

	public final String getNumero() {
		return this.numeroProperty().get();
	}
	

	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}


	public final ObjectProperty<TipoTelefono> tipoProperty() {
		return this.tipo;
	}
	


	public final TipoTelefono getTipo() {
		return this.tipoProperty().get();
	}
	


	public final void setTipo(final TipoTelefono tipo) {
		this.tipoProperty().set(tipo);
	}
	
	
	
}
