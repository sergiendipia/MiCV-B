package dad.javafx.micv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.javafx.micv.conocimientos.ConocimientosController;
import dad.javafx.micv.contacto.ContactoController;
import dad.javafx.micv.experiencia.ExperienciaController;
import dad.javafx.micv.formacion.FormacionController;
import dad.javafx.micv.model.CV;
import dad.javafx.micv.personal.PersonalController;
import dad.javafx.micv.utils.JSONUtils;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	String ruta;
	// controllers

	private PersonalController personalController = new PersonalController();
	private ContactoController contactoController = new ContactoController();
	private FormacionController formacionController = new FormacionController();
	private ExperienciaController experienciaController = new ExperienciaController();
	private ConocimientosController conocimientosController = new ConocimientosController();
	// model

	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();

	// view

	@FXML
	private BorderPane view;

	@FXML
	private Tab personalTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab experienciaTab;

	@FXML
	private Tab conocimientosTab;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactoController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientosController.getView());
		cv.addListener((o, ov, nv) -> onCVChanged(o, ov, nv));

		cv.set(new CV());

	}

	private void onCVChanged(ObservableValue<? extends CV> o, CV ov, CV nv) {

		if (ov != null) {

			personalController.personalProperty().unbind(); // desbindeo personalProperty del CV anterior
			contactoController.contactoProperty().unbind();
			formacionController.formacionProperty().unbind();
			experienciaController.experienciasProperty().unbind();
			conocimientosController.habilidadesProperty().unbind();

		}

		if (nv != null) {

			personalController.personalProperty().bind(nv.personalProperty()); // bindeo personalProperty del nuevo CV
			contactoController.contactoProperty().bind(nv.contactoProperty());
			formacionController.formacionProperty().bind(nv.formacionProperty());
			experienciaController.experienciasProperty().bind(nv.experienciasProperty());
			conocimientosController.habilidadesProperty().bind(nv.habilidadesProperty());

		}

	}

	@FXML
	void onAbrirAction(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir un currículum");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum Vitae (*.cv)", "*.cv"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
		File cvFile = fileChooser.showOpenDialog(App.getPrimaryStage());
		if (cvFile != null) {
			try {
				cv.set(JSONUtils.fromJson(cvFile, CV.class));
				ruta = cvFile.getAbsolutePath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	void onAcercaDeAction(ActionEvent event) {

	}

	@FXML
	void onGuardarAction(ActionEvent event) {
		/*
		 * Necesito comprobar que se ha abierto un archivo Y sobreescribir el archivo al
		 * pulsar guardar
		 */
		if (ruta != null) {
			File cvFile = new File(ruta);
			try {
				JSONUtils.toJson(cvFile, cv.get());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			onGuardarComoAction(event);
		}

	}

	@FXML
	void onGuardarComoAction(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Guardar un currículum");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum Vitae (*.cv)", "*.cv"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
		File cvFile = fileChooser.showSaveDialog(App.getPrimaryStage());
		if (cvFile != null) {

			try {
				JSONUtils.toJson(cvFile, cv.get());
				ruta = cvFile.getAbsolutePath();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		System.out.println("Has pulsado nuevo");
		cv.set(new CV());
		ruta = null;
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Dialogo de confirmación");
		alert.setHeaderText("Vas a cerrar el programa");
		alert.setContentText("¿Estás seguro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		} else {
			// ... user chose CANCEL or closed the dialog
		}
	}

}
