package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.ui.app.GesaulaApp;
import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.modelos.AlumnosModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class AlumnosController implements Initializable {

	Label texto = new Label("Seleccione un alumno en la tabla de la izquierda");
	DatosController datosController = new DatosController();
	AlumnosModel model = new AlumnosModel();
	@FXML
	private TableView<Alumno> alumnosTable;

	@FXML
	private TableColumn<Alumno, String> apellidosCol, nombreCol;

	@FXML
	private TableColumn<Alumno, LocalDate> nacimientoDate;

	@FXML
	private Button nuevoButton, eliminarButton;

	@FXML
	private BorderPane splitAlumno;

	@FXML
	private SplitPane view;

	public AlumnosController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		splitAlumno.setCenter(texto);
		// binding
		alumnosTable.itemsProperty().bind(model.listaAlumnosProperty());
		nombreCol.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosCol.setCellValueFactory(v -> v.getValue().apellidosProperty());

		model.alumnoSeleccionadoProperty().bind(alumnosTable.getSelectionModel().selectedItemProperty());

		model.alumnoSeleccionadoProperty().addListener((o, ov, nv) -> {
			if (nv != null) {
				splitAlumno.setCenter(datosController.getView());
				datosController.alumnoProperty().bind(model.alumnoSeleccionadoProperty());

			} else {
				splitAlumno.setCenter(texto);
				datosController.alumnoProperty().unbind( );

			}
		});
	}

	public SplitPane getView() {
		return view;
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		if (!alumnosTable.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Eliminar alumno");
			alert.initOwner(GesaulaApp.primaryStage);
			alert.setHeaderText(" Se va a eliminar el alumno " + alumnosTable.getSelectionModel().selectedItemProperty().get().toString());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				model.getListaAlumnos().remove(alumnosTable.getSelectionModel().selectedIndexProperty().get());
			}
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Alumno nuevo = new Alumno();
		nuevo.setNombre("Sin nombre");
		nuevo.setApellidos("Sin apellidos");
		model.getListaAlumnos().add(nuevo);
		
	}

}
