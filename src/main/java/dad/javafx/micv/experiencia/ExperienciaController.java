package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ExperienciaController {
	@FXML
    private HBox view;

    @FXML
    private TableView<?> experienciaTable;

    @FXML
    private TableColumn<?, ?> desdeColumn;

    @FXML
    private TableColumn<?, ?> hastaColumn;

    @FXML
    private TableColumn<?, ?> denominacionColumn;

    @FXML
    private TableColumn<?, ?> empleadorColumn;

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

	}

    @FXML
    void onAnadirButtonAction(ActionEvent event) {

    }

    @FXML
    void onEliminarButtonAction(ActionEvent event) {

    }
}
