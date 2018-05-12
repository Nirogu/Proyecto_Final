/**
 * @author LosCapos
 * Modelacion de los estudiantes del programa academico.
 * Se utilizan el nombre y el semestre que le corresponden al estudiante.
 */
public class Estudiante {
	
	/**
	 * @param nombre
	 * Metodo setter para la variable nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @param semestre
	 * Metodo setter para la variable semestre
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable semestre
	 */
	public int getSemestre() {
		return semestre;
	}
	
	/**
	 * @param nombre
	 * @param semestre
	 * Metodo constructor para la clase Estudiante
	 */
	public Estudiante(String nombre, int semestre) {
		this.nombre = nombre ;
		this.semestre = semestre ;
	}
	
	//Metodo toString de la clase Estudiante
	public String toString() {
		return(nombre + ": semestre " + semestre);
	}
	
	//Variables de la clase
	String nombre ;
	int semestre ;
}