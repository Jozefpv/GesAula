# GesAula

Implementar una aplicación en Java con interfaz gráfica de usuario para gestionar grupos de alumnos de un centro educativo.

Se deberá usar el framework JavaFX, aplicar el patrón de diseño MVC, así como implementar una interfaz que sea "responsive".

## Ventana principal

La ventana principal tendrá un **Toolbar** y un **TabPane** con dos pestañas.

### Pestaña "Grupo"

En la pestaña "Grupo" (primera pestaña) se gestionarán los datos del grupo, tal y como puede verse a continuación:

![image-20211122094431861](image-20211122094431861.png)

Para introducir los criterios de calificación se usará el componente "Slider". Por ejemplo:

```xml
<Slider fx:id="examenesSlider" majorTickUnit="10.0"
			minorTickCount="9" showTickMarks="true" snapToTicks="true" />
```

>   Este ejemplo corresponde al "Slider" de "Exámenes".

### Pestaña "Alumnos"

Los alumnos que forman parte del grupo se gestionarán desde la pestaña "Alumnos".

![image-20211122094733892](image-20211122094733892.png)

El botón "Nuevo" creará un nuevo alumno con datos por defecto, tal y como se muestra a continuación:

![image-20211122094904140](image-20211122094904140.png)

El botón "Eliminar" eliminará el alumno seleccionado. Se deberá pedir confirmación, y en caso de que no haya alumno seleccionado, se mostrará un mensaje de error.

![image-20211122095002954](image-20211122095002954.png)

Al seleccionar un alumno, se podrá editar su información en el panel derecho:

![image-20211122095036465](image-20211122095036465.png)

Cuando no haya alumnos seleccionados, deberá retirarse el panel de la derecha e informar al usuario de que no hay nada seleccionado:

![image-20211122095113150](image-20211122095113150.png)

### Barra de herramientas

En la "Toolbar" se podrá crear un grupo nuevo, lo que eliminará toda la información del grupo actual, limpiándose así por completo todos los datos de la interfaz.

```java
grupo = new Grupo();
```

El botón "Guardar" permitirá guardar el grupo en un fichero. El nombre del fichero se indicará en el cuadro de texto que hay a la izquierda del botón.

```java
grupo.save(new File(ruta));
```

