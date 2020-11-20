package dad.javafx.micv.personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();

	// view

	@FXML
	private GridPane view;

	@FXML
	private TextField identificacionText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private TextArea direccionText;

	@FXML
	private TextField codigoPostalText;

	@FXML
	private TextField localidadText;

	@FXML
	private ListView<Nacionalidad> nacionalidadesList;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button nuevaNacionalidadButton;

	@FXML
	private Button quitarNacionalidadButton;

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));
		
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);
		
		if (ov != null) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			// TODO desbindear el resto de propiedades
			
		}
		
		if (nv != null) {
			
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			// TODO bindear el resto de propiedades			
			
		}
		
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {

	}

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {

	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}
