package dad.javafx.micv.model;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.javafx.micv.conocimientos.model.Conocimiento;
import dad.javafx.micv.contacto.model.Contacto;
import dad.javafx.micv.experiencia.model.Experiencia;
import dad.javafx.micv.formacion.model.Titulo;
import dad.javafx.micv.personal.model.Nacionalidad;
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

	public static void main(String[] args) {
		
		CV cv = new CV();
		cv.getPersonal().setNombre("Chuck");
		cv.getPersonal().setApellidos("Norris");
		cv.getPersonal().getNacionalidades().add(new Nacionalidad("estadounidense"));
		
		Gson gson = 
			FxGson.fullBuilder()
                .setPrettyPrinting()
                .create();
		
		String json = gson.toJson(cv); // convertir modelo de datos a json (marshalling)

		System.out.println(json);
		
		cv = gson.fromJson(json, CV.class); // convertir json a modelo de datos (unmarshalling) 
		
	}
	
}



