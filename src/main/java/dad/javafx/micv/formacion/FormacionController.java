package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FormacionController {
	
	@FXML
    private HBox view;

    @FXML
    private TableView<?> formacionTable;

    @FXML
    private TableColumn<?, ?> desdeColumn;

    @FXML
    private TableColumn<?, ?> hastColumn;

    @FXML
    private TableColumn<?, ?> denominacionColumn;

    @FXML
    private TableColumn<?, ?> organizadorColumn;

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

	}
    
    @FXML
    void onAnadirButtonAction(ActionEvent event) {

    }

    @FXML
    void onEliminarButtonAction(ActionEvent event) {

    }

}
