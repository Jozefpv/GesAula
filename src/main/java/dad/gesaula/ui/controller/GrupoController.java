package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class GrupoController implements Initializable {

	ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();
	AlumnosController alumnosController = new AlumnosController();

	@FXML
	private Label actitudPorcent;

	@FXML
	private Slider actitudSlide;

	@FXML
	private TextField denominacionText;

	@FXML
	private Label examenesPorcent;

	@FXML
	private Slider examenesSlide;

	@FXML
	private DatePicker finDate;

	@FXML
	private DatePicker inicioDate;

	@FXML
	private Label practicasPorcent;

	@FXML
	private Slider practicasSlide;

	@FXML
	private GridPane view;

	public GrupoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		grupo.addListener((o, ov, nv) -> {

			if (ov != null) {
				alumnosController.model.listaAlumnosProperty().unbind();

				denominacionText.textProperty().unbindBidirectional(ov.denominacionProperty());
				inicioDate.valueProperty().unbindBidirectional(ov.iniCursoProperty());
				finDate.valueProperty().unbindBidirectional(ov.finCursoProperty());
				examenesSlide.valueProperty().unbindBidirectional(ov.getPesos().examenesProperty());
				practicasSlide.valueProperty().unbindBidirectional(ov.getPesos().practicasProperty());
				actitudSlide.valueProperty().unbindBidirectional(ov.getPesos().actitudProperty());
				examenesPorcent.textProperty().unbindBidirectional(ov.getPesos().examenesProperty());
				practicasPorcent.textProperty().unbindBidirectional(ov.getPesos().practicasProperty());
				actitudPorcent.textProperty().unbindBidirectional(ov.getPesos().actitudProperty());
			}

			if (nv != null) {
				alumnosController.model.listaAlumnosProperty().bind(nv.alumnosProperty());

				denominacionText.textProperty().bindBidirectional(nv.denominacionProperty());
				inicioDate.valueProperty().bindBidirectional(nv.iniCursoProperty());
				finDate.valueProperty().bindBidirectional(nv.finCursoProperty());
				examenesSlide.valueProperty().bindBidirectional(nv.getPesos().examenesProperty());
				practicasSlide.valueProperty().bindBidirectional(nv.getPesos().practicasProperty());
				actitudSlide.valueProperty().bindBidirectional(nv.getPesos().actitudProperty());
				examenesPorcent.textProperty().bind(nv.getPesos().examenesProperty().asString("%.0f%%")); //solucion a los decimales %% es para coger el caracter %
				practicasPorcent.textProperty().bindBidirectional(nv.getPesos().practicasProperty(),
						new NumberStringConverter());
				actitudPorcent.textProperty().bindBidirectional(nv.getPesos().actitudProperty(),
						new NumberStringConverter());

			}
		});
		grupo.set(new Grupo());
	}

	public GridPane getView() {
		return view;
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
