package dad.gesaula.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	private StringProperty ruta = new SimpleStringProperty();
	private GrupoController grupoController = new GrupoController();
	ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

    @FXML
    private Button guardarButton;

    @FXML
    private Button nuevoButton;
    
    @FXML
    private TextField direccionText;
    
	@FXML
    private Tab alumnosTab;

    @FXML
    private Tab grupoTab;
    
    @FXML
    private BorderPane view;
	
	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grupoTab.setContent(grupoController.getView());
		alumnosTab.setContent(grupoController.alumnosController.getView());
		grupo.bindBidirectional(grupoController.grupoProperty());
		ruta.bindBidirectional(direccionText.textProperty());
		guardarButton.disableProperty().bind(ruta.isEmpty());
		
		
	}

	public BorderPane getView() {
		return view;
	}
	
    @FXML
    void onGuardarAction(ActionEvent event) throws Exception {
    	System.out.println(getRuta());
    	if(!getRuta().isEmpty()) {
    		getGrupo().save(new File(getRuta()));
        	setRuta("");
        	Grupo nuevoGrupo = new Grupo();
        	this.grupo.set(nuevoGrupo);
    	}
    	
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	Grupo nuevoGrupo = new Grupo();
    	this.grupo.set(nuevoGrupo);
    	
    }

	public final StringProperty rutaProperty() {
		return this.ruta;
	}
	

	public final String getRuta() {
		return this.rutaProperty().get();
	}
	

	public final void setRuta(final String ruta) {
		this.rutaProperty().set(ruta);
	}
	

	public final ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}
	

	public final Grupo getGrupo() {
		return this.grupoProperty().get();
	}
	

	public final void setGrupo(final Grupo grupo) {
		this.grupoProperty().set(grupo);
	}
	


}
