package dad.javafx.micv.model;

import dad.javafx.micv.conocimientos.model.Conocimiento;
import dad.javafx.micv.contacto.model.Contacto;
import dad.javafx.micv.experiencia.model.Experiencia;
import dad.javafx.micv.formacion.model.Titulo;
import dad.javafx.micv.personal.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>(new Contacto());
	private ObjectProperty<Titulo> formacion = new SimpleObjectProperty<Titulo>(new Titulo());
	private ObjectProperty<Experiencia> experiencia = new SimpleObjectProperty<Experiencia>(new Experiencia());
	private ObjectProperty<Conocimiento> conocimiento = new SimpleObjectProperty<Conocimiento>(new Conocimiento());

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

	public final ObjectProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	public final Titulo getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final Titulo formacion) {
		this.formacionProperty().set(formacion);
	}

	public final ObjectProperty<Experiencia> experienciaProperty() {
		return this.experiencia;
	}

	public final Experiencia getExperiencia() {
		return this.experienciaProperty().get();
	}

	public final void setExperiencia(final Experiencia experiencia) {
		this.experienciaProperty().set(experiencia);
	}

	public final ObjectProperty<Conocimiento> conocimientoProperty() {
		return this.conocimiento;
	}

	public final Conocimiento getConocimiento() {
		return this.conocimientoProperty().get();
	}

	public final void setConocimiento(final Conocimiento conocimiento) {
		this.conocimientoProperty().set(conocimiento);
	}

}
