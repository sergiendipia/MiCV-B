package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AnadirTelefonoDialog extends Dialog<Telefono> implements Initializable {
	
    @FXML
    private GridPane view;

    @FXML
    private ComboBox<TipoTelefono> anadirTipoCombo;

    @FXML
    private TextField anadirNumeroText;

	public AnadirTelefonoDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/contacto/TelefonoDialog.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		anadirTipoCombo.getItems().addAll(TipoTelefono.values());
		
		setTitle("Login Dialog");
		setHeaderText("Introduzca el nuevo número de teléfono.");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Añadir", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAnadirTelefono(d));
		
	}

    private Telefono onAnadirTelefono(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Telefono telefono = new Telefono();
    		telefono.setNumero(anadirNumeroText.textProperty().get());
        	telefono.setTipo(anadirTipoCombo.getValue());
    		return telefono;
    	}
    	return null;
    }
	
}
