/**
 * Modelacion de los estudiantes del programa academico.
 * Se utilizan el nombre y el semestre que le corresponden al estudiante.
 * @author LosCapos
 */
public class Estudiante {
	
	/**
	 * Metodo setter para la variable nombre
	 * @param nombre
	 * Nombre del estudiante
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo setter para la variable semestre
	 * @param semestre
	 * Semestre del estudiante
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	/**
	 * Metodo getter para la variable nombre
	 * @return
	 * Nombre del estudiante
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo getter para la variable semestre
	 * @return
	 * Semestre del estudiante
	 */
	public int getSemestre() {
		return semestre;
	}
	
	/**
	 * Metodo constructor para la clase Estudiante
	 * @param nombre
	 * Nombre del estudiante
	 * @param semestre
	 * Semestre del estudiante
	 */
	public Estudiante(String nombre, int semestre) {
		this.nombre = nombre ;
		this.semestre = semestre ;
	}
	
	//Variables de la clase
	private String nombre ;
	private int semestre ;
}