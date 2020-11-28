package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.contacto.model.Contacto;
import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.experiencia.model.Experiencia;
import dad.javafx.micv.formacion.AnadirFormacionDialog;
import dad.javafx.micv.formacion.model.Titulo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;

public class ExperienciaController implements Initializable{
	//model
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>(FXCollections.observableArrayList());
	
	@FXML
    private HBox view;

    @FXML
    private TableView<Experiencia> experienciaTable;

    @FXML
    private TableColumn<Experiencia, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Experiencia, LocalDate> hastaColumn;

    @FXML
    private TableColumn<Experiencia, String> denominacionColumn;

    @FXML
    private TableColumn<Experiencia, String> empleadorColumn;

    @FXML
    private Button anadirButton;

    @FXML
    private Button eliminarButton;
    
	public ExperienciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/experiencia/ExperienciaView.fxml"));
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
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());

		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}

    @FXML
    void onAnadirAction(ActionEvent event) {
		AnadirExperienciaDialog dialog = new AnadirExperienciaDialog();
		Optional<Experiencia> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getExperiencias().add(result.get());
		}
    }
    

    @FXML
    void onEliminarAction(ActionEvent event) {
    	experienciaTable.getItems().removeAll(experienciaTable.getSelectionModel().getSelectedItem());
    }

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	

	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
	


	
    
}
