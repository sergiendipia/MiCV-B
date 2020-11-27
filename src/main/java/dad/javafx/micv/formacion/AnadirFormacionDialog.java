package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
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

public class AnadirFormacionDialog extends Dialog<Titulo> implements Initializable{
    @FXML
    private GridPane view;

    @FXML
    private TextField denominacionText;

    @FXML
    private TextField organizadorText;

    @FXML
    private DatePicker desdeDate;

    @FXML
    private DatePicker hastaDate;
    
	public AnadirFormacionDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formacion/AnadirFormacionDialog.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setTitle("Nuevo Título");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAnadirTitulo(d));
		
	}
	
    private Titulo onAnadirTitulo(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Titulo titulo = new Titulo();
    		titulo.setDenominacion(denominacionText.textProperty().get());
    		titulo.setOrganizador(organizadorText.textProperty().get());
    		titulo.setDesde(desdeDate.getValue());
    		titulo.setHasta(hastaDate.getValue());
        	     	
    		return titulo;
    	}
    	return null;
    }
}
