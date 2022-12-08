package dad.gesaula.ui.modelos;

import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlumnosModel {

	ObjectProperty<Alumno> alumnoSeleccionado = new SimpleObjectProperty<>();
	ListProperty<Alumno> listaAlumnos = new SimpleListProperty<>(FXCollections.observableArrayList());

	public final ListProperty<Alumno> listaAlumnosProperty() {
		return this.listaAlumnos;
	}

	public final ObservableList<Alumno> getListaAlumnos() {
		return this.listaAlumnosProperty().get();
	}

	public final void setListaAlumnos(final ObservableList<Alumno> listaAlumnos) {
		this.listaAlumnosProperty().set(listaAlumnos);
	}

	public final ObjectProperty<Alumno> alumnoSeleccionadoProperty() {
		return this.alumnoSeleccionado;
	}
	

	public final Alumno getAlumnoSeleccionado() {
		return this.alumnoSeleccionadoProperty().get();
	}
	

	public final void setAlumnoSeleccionado(final Alumno alumnoSeleccionado) {
		this.alumnoSeleccionadoProperty().set(alumnoSeleccionado);
	}
	

}
