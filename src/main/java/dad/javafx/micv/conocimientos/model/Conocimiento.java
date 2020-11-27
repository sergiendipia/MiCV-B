package dad.javafx.micv.conocimientos.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Conocimiento {
	private StringProperty denominacion = new SimpleStringProperty();
	private ListProperty<Idioma> nacionalidades = new SimpleListProperty<Idioma>(FXCollections.observableArrayList());
	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	
	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	
	public final ListProperty<Idioma> nacionalidadesProperty() {
		return this.nacionalidades;
	}
	
	public final ObservableList<Idioma> getNacionalidades() {
		return this.nacionalidadesProperty().get();
	}
	
	public final void setNacionalidades(final ObservableList<Idioma> nacionalidades) {
		this.nacionalidadesProperty().set(nacionalidades);
	}
	
	
	
}
