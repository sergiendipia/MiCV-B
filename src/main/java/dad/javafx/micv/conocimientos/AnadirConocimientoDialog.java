package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.conocimientos.model.Conocimiento;
import dad.javafx.micv.conocimientos.model.Nivel;
import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

public class AnadirConocimientoDialog extends Dialog<Conocimiento> implements Initializable{

    @FXML
    private GridPane view;

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> nivelCombo;

    @FXML
    private Button nivelResetButton;

    
	public AnadirConocimientoDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conocimientos/AnadirConocimientoDialog.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nivelCombo.getItems().addAll(Nivel.values());
		
		setTitle("Nuevo conocimiento");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAnadirConocimiento(d));
		
	}
	
    private Conocimiento onAnadirConocimiento(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Conocimiento conocimiento = new Conocimiento();
    		conocimiento.setDenominacion(denominacionText.textProperty().get());
    		conocimiento.setNivel(nivelCombo.getValue());
    		return conocimiento;
    	}
    	return null;
	}


	@FXML
    void onNivelResetAction(ActionEvent event) {
		nivelCombo.valueProperty().set(null);
    }
}
