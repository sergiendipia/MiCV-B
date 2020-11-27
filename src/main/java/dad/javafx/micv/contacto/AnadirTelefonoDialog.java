package dad.javafx.micv.contacto;

import dad.javafx.micv.contacto.model.Telefono;
import dad.javafx.micv.contacto.model.TipoTelefono;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AnadirTelefonoDialog extends Dialog<Telefono> {
	private Telefono telefonoAnadir = new Telefono();
    @FXML
    private VBox view;

    @FXML
    private ComboBox<TipoTelefono> anadirTipoCombo;

    @FXML
    private TextField anadirNumeroText;

    @FXML
    private Button anadirTelefonoDialogButton;

    @FXML
    private Button cancelarTelefonoDialogButton;

    @FXML
    void onAnadirDialogAction(ActionEvent event) {
    	telefonoAnadir.setNumero(anadirNumeroText.textProperty().get());
    	telefonoAnadir.setTipo(anadirTipoCombo.getValue());
    }

    @FXML
    void onCancelarDialogAction(ActionEvent event) {
    	close();
    }
	public AnadirTelefonoDialog() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/contacto/telefonoDialog"));
		
		setTitle("Login Dialog");
		getDialogPane().setContent(loader.getRoot());
		
		anadirNumeroText.textProperty().addListener((observable, oldValue, newValue) -> {
		    anadirTelefonoDialogButton.setDisable(newValue.trim().isEmpty());
		});
	}

	
}
