package dad.javafx.micv.contacto.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {
	private StringProperty direccion = new SimpleStringProperty();

	public final StringProperty direccionProperty() {
		return this.direccion;
	}
	

	public final String getDireccion() {
		return this.direccionProperty().get();
	}
	

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}
	
	
}
