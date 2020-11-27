package dad.javafx.micv.contacto.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {
	private ListProperty<Telefono> telefonos = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	private ListProperty<Email> emails = new SimpleListProperty<Email>(FXCollections.observableArrayList());
	private ListProperty<Web> webs = new SimpleListProperty<Web>(FXCollections.observableArrayList());

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

	public final ListProperty<Email> emailsProperty() {
		return this.emails;
	}

	public final ObservableList<Email> getEmails() {
		return this.emailsProperty().get();
	}

	public final void setEmails(final ObservableList<Email> emails) {
		this.emailsProperty().set(emails);
	}

	public final ListProperty<Web> websProperty() {
		return this.webs;
	}

	public final ObservableList<Web> getWebs() {
		return this.websProperty().get();
	}

	public final void setWebs(final ObservableList<Web> webs) {
		this.websProperty().set(webs);
	}

}
