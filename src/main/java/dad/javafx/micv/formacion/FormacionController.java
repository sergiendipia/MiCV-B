package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.contacto.AnadirTelefonoDialog;
import dad.javafx.micv.contacto.model.Contacto;
import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
import dad.javafx.micv.formacion.model.Titulo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

public class FormacionController implements Initializable {
	// model
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());

	@FXML
	private HBox view;

	@FXML
	private TableView<Titulo> formacionTable;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Titulo, String> denominacionColumn;

	@FXML
	private TableColumn<Titulo, String> organizadorColumn;

	@FXML
	private Button anadirButton;

	@FXML
	private Button eliminarButton;

	public FormacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formacion/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public HBox getView() {
		return view;
	}

	public void initialize(URL location, ResourceBundle resources) {
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());

		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		formacionTable.itemsProperty().bind(formacion);
//		formacion.addListener((o, ov, nv) -> onFormacionChanged(o, ov, nv));

	}

//	private void onFormacionChanged(ObservableValue<? extends ObservableList<Titulo>> o, ObservableList<Titulo> ov,
//			ObservableList<Titulo> nv) {
//		System.out.println("ov=" + ov + "/nv=" + nv);
//
//		if (ov != null) {
//			// unbind tabla formacion
//			formacionTable.setItems(null);
//		}
//
//		if (nv != null) {
//			// bind tabla formacion
//			formacionTable.setItems(nv);
//		}
//
//	}

	/*
	 * Existe la opción de crear una clase intermedia (formacion), y con ella
	 * podríamos cargar una lista con los objetos titulo. En base al diagrama UML,
	 * no es necesaria, por lo que podemos tener una lista de titulos directamente
	 * en el Controlador. Pero, da error, a la hora de realizar los bindeos. Sin
	 * ella, no es posible que se muestren registros en la tabla
	 */

	@FXML
	void onAnadirAction(ActionEvent event) {
		AnadirFormacionDialog dialog = new AnadirFormacionDialog();
		Optional<Titulo> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getFormacion().add(result.get());
		}
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("¿Seguro que deseas borrar este registro?");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			formacionTable.getItems().removeAll(formacionTable.getSelectionModel().getSelectedItem());
		} else {
			// ... user chose CANCEL or closed the dialog
		}
		
	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

}
