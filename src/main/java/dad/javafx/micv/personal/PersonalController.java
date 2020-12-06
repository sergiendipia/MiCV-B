package dad.javafx.micv.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.personal.model.Nacionalidad;
import dad.javafx.micv.personal.model.Personal;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ListProperty<Nacionalidad> nacionalidadesCSV = new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
// Estaba usando doble	private ListProperty<Nacionalidad> nacionalidades = new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
	private ListProperty<String> paises = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ObjectProperty<Nacionalidad> seleccionado = new SimpleObjectProperty();
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
		// csv nacionalidades
		String line;
		InputStream nacionalidadesStream = getClass().getResourceAsStream("/csv/nacionalidades.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(nacionalidadesStream));
		
		try {
			while ((line = reader.readLine()) != null) {
				Nacionalidad nacionalidad = new Nacionalidad(line);
				nacionalidadesCSV.add(nacionalidad);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// csv paises
		InputStream paisesStream = getClass().getResourceAsStream("/csv/paises.csv");
		reader = new BufferedReader(new InputStreamReader(paisesStream));
		String linea;
		
		try {
			while ((linea = reader.readLine()) != null) {
				String pais = linea;
				paises.add(pais);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Cargar paises en el combo
		paisCombo.getItems().addAll(paises);
		seleccionado.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());
		
		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));
		//Bind del listview a la vista
		//nacionalidadesList.itemsProperty().bind(nacionalidades);
		//Bind de la fila selleccionada del listview
		
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);
		
		if (ov != null) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			direccionText.textProperty().unbindBidirectional(nv.direccionProperty());
			codigoPostalText.textProperty().unbindBidirectional(nv.codigoPostalProperty());
			localidadText.textProperty().unbindBidirectional(nv.localidadProperty());
			paisCombo.valueProperty().unbindBidirectional(nv.paisProperty());
			nacionalidadesList.itemsProperty().unbind();
			
			
		}
		
		if (nv != null) {
			
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			direccionText.textProperty().bindBidirectional(nv.direccionProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			localidadText.textProperty().bindBidirectional(nv.localidadProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			nacionalidadesList.itemsProperty().bind(nv.nacionalidadesProperty());
			
			
			
//			paisCombo.getSelectionModel().selectFirst();
		}
		
		quitarNacionalidadButton.disableProperty().bind(Bindings.isEmpty(getPersonal().getNacionalidades()));
		
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {

		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(nacionalidadesCSV.get(0), nacionalidadesCSV);
		
		
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");
		
		Optional<Nacionalidad> result = dialog.showAndWait();
		if (result.isPresent() && !getPersonal().getNacionalidades().contains(result.get())) {
			getPersonal().getNacionalidades().add(result.get());
		}
		
	}

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {
		getPersonal().getNacionalidades().remove(seleccionado.get());
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
