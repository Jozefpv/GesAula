package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DatosController implements Initializable {

	//model
	ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();
	
	@FXML
	private TextField apellidosText,  nombreText;

	@FXML
	private DatePicker nacimientoDate;

	@FXML
	private CheckBox repiteCheck;

	@FXML
	private ComboBox<Sexo> sexoCombo;

	@FXML
	private GridPane view;

	public DatosController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		sexoCombo.getItems().addAll(Sexo.values());

		alumno.addListener((o, ov , nv) ->{
			if(ov !=null) {
				nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
				apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
				nacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
				ov.sexoProperty().unbind();
				repiteCheck.selectedProperty().unbindBidirectional(ov.repiteProperty());
			}
			if(nv!= null) {
				nombreText.textProperty().bindBidirectional(nv.nombreProperty());
				apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
				nacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
				sexoCombo.getSelectionModel().select(nv.getSexo());
				nv.sexoProperty().bind(sexoCombo.getSelectionModel().selectedItemProperty());
				repiteCheck.selectedProperty().bindBidirectional(nv.repiteProperty());
				
			}
		});
	}

	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<Alumno> alumnoProperty() {
		return this.alumno;
	}
	

	public final Alumno getAlumno() {
		return this.alumnoProperty().get();
	}
	

	public final void setAlumno(final Alumno alumno) {
		this.alumnoProperty().set(alumno);
	}
	

}
