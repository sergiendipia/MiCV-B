package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.conocimientos.model.Conocimiento;
import dad.javafx.micv.conocimientos.model.Nivel;
import dad.javafx.micv.contacto.AnadirTelefonoDialog;
import dad.javafx.micv.contacto.model.Contacto;
import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

public class ConocimientosController implements Initializable{
	//model
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(FXCollections.observableArrayList());
	@FXML
    private HBox view;

    @FXML
    private TableView<Conocimiento> conocimientosTable;

    @FXML
    private TableColumn<Conocimiento, String> denominacionColumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;

    @FXML
    private Button anadirConocimientoButton;

    @FXML
    private Button anadirIdiomaButton;

    @FXML
    private Button eliminarButton;

	public ConocimientosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conocimientos/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public HBox getView() {
		return view;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());


		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));
	}
    
    
    @FXML
    void onAnadirConocimientoAction(ActionEvent event) {
		AnadirConocimientoDialog dialog = new AnadirConocimientoDialog();
		Optional<Conocimiento> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getHabilidades().add(result.get());
		}
    }

    @FXML
    void onAnadirIdiomaAction(ActionEvent event) {
		AnadirConocimientoIdiomaDialog dialog = new AnadirConocimientoIdiomaDialog();
		Optional<Conocimiento> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getHabilidades().add(result.get());
		}
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Eliminar");
    	alert.setHeaderText("¿Seguro que deseas borrar este registro?");
    	//alert.setContentText();

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK
    		conocimientosTable.getItems().removeAll(conocimientosTable.getSelectionModel().getSelectedItem());
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    }

	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	

	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	

	

    
    

}
