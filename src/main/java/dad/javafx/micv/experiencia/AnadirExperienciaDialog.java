package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.experiencia.model.Experiencia;
import dad.javafx.micv.formacion.model.Titulo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonBar.ButtonData;

public class AnadirExperienciaDialog extends Dialog<Experiencia> implements Initializable{
	@FXML
	private GridPane view;
	
    @FXML
    private TextField denominacionText;

    @FXML
    private TextField empleadorText;

    @FXML
    private DatePicker desdeDate;

    @FXML
    private DatePicker hastaDate;
    
	public AnadirExperienciaDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/experiencia/AnadirExperienciaDialog.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setTitle("Nueva Experiencia");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAnadirTitulo(d));
		
	}
	
    private Experiencia onAnadirTitulo(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Experiencia experiencia = new Experiencia();
    		experiencia.setDenominacion(denominacionText.textProperty().get());
    		experiencia.setEmpleador(empleadorText.textProperty().get());
    		experiencia.setDesde(desdeDate.getValue());
    		experiencia.setHasta(hastaDate.getValue());
        	     	
    		return experiencia;
    	}
    	return null;
    }
}
