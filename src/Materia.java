/**
 * @author LosCapos
 * Modelacion de las materias del programa academico.
 * Se utilizan el dia, la hora, el semestre, y el nombre que le corresponden a la materia.
 */
public class Materia {
	
	/**
	 * @param nombre
	 * Metodo setter para la variable nombre
	 */
	public void setNombreMateria(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @param hora
	 * Metodo setter para la variable hora
	 */
	public void setHora (int hora) {
		this.hora = hora;
	}
	
	/**
	 * @param dia
	 * Metodo setter para la variable dia
	 */
	public void setDia(int dia) {
		this.dia = dia ;	
	}
	
	/**
	 * @param semestre
	 * Metodo setter para la variable semestre
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre ;
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
	 * Metodo getter para la variable hora
	 */
	public int getHora() {
		return hora ;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable dia
	 */
	public int getDia() {
		return dia ;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable hora
	 */
	public int getSemestre(){
		return semestre;
	}
	
	/**
	 * @param nombre
	 * @param dia
	 * @param hora
	 * @param semestre
	 * Metodo constructor para la clase Materia
	 */
	public Materia (String nombre, int dia , int hora , int semestre) {
		this.nombre = nombre ;
		this.dia = dia ;
		this.hora = hora ;
		this.semestre = semestre ;
	}
	
	//Metodo toString de la clase Materia
	public String toString() {
		return (nombre + ": Dia " + dia + ": Hora "+ hora + ": Semestre "+ semestre);
	}
	
	//Variables de la clase
	String nombre;
	int hora;
	int dia;
	int semestre;
	
}