package dad.javafx.micv.conocimientos;

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

public class ConocimientosController {
	@FXML
    private HBox view;

    @FXML
    private TableView<?> conocimientosTable;

    @FXML
    private TableColumn<?, ?> denominacionColumn;

    @FXML
    private TableColumn<?, ?> nivelColumn;

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

	}
    
    
    @FXML
    void onAnadirConocimientoAction(ActionEvent event) {

    }

    @FXML
    void onAnadirIdiomaAction(ActionEvent event) {

    }

    @FXML
    void onEliminarButtonAction(ActionEvent event) {

    }

}
