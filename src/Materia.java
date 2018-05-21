/**
 * Modelacion de las materias del programa academico.
 * Se utilizan el dia, la hora, el semestre, y el nombre que le corresponden a la materia.
 * @author LosCapos
 */
public class Materia {
	
	/**
	 * Metodo setter para la variable nombre
	 * @param nombre
	 * Nombre de la materia
	 */
	public void setNombreMateria(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo setter para la variable hora
	 * @param hora
	 * Hora de la materia
	 */
	public void setHora (int hora) {
		this.hora = hora;
	}
	
	/**
	 * Metodo setter para la variable dia
	 * @param dia
	 * Dia de la materia
	 */
	public void setDia(int dia) {
		this.dia = dia;
	}
	
	/**
	 * Metodo setter para la variable semestre
	 * @param semestre
	 * Semestre de la materia
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	/**
	 * Metodo setter para la variable profesor
	 * @param profesor
	 * Profesor que dicta la materia
	 */
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	/**
	 * Metodo setter para la variable salon
	 * @param salon
	 * Salon de la materia
	 */
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
	/**
	 * Metodo getter para la variable nombre
	 * @return
	 * Nombre de la materia
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo getter para la variable hora
	 * @return
	 * Hora de la materia
	 */
	public int getHora() {
		return hora ;
	}
	
	/**
	 * Metodo getter para la variable dia
	 * @return
	 * Dia de la materia
	 */
	public int getDia() {
		return dia ;
	}
	
	/**
	 * Metodo getter para la variable semestre
	 * @return
	 * Semestre de la materia
	 */
	public int getSemestre(){
		return semestre;
	}

	/**
	 * Metodo getter para la variable profesor
	 * @return
	 * Profesor que dicta la materia
	 */
	public String getProfesor(){
		return profesor;
	}
	
	/**
	 * Metodo getter para la variable salon
	 * @return
	 * Salon de la materia
	 */
	public Salon getSalon() {
		return salon;
	}
	
	/**
	 * Metodo constructor para la clase Materia
	 * @param nombre
	 * Nombre de la materia
	 * @param dia
	 * Dia de la materia
	 * @param hora
	 * Hora de la materia
	 * @param semestre
	 * Semestre de la materia
	 * @param profesor
	 * Profesor que dicta la materia
	 * @param salon
	 * Salon de la materia
	 */
	public Materia (String nombre, int dia , int hora , int semestre, String profesor, Salon salon) {
		this.nombre = nombre;
		this.dia = dia;
		this.hora = hora;
		this.semestre = semestre;
		this.profesor = profesor;
		this.salon = salon;
	}
	
	//Variables de la clase
	String nombre;
	private int hora;
	private int dia;
	private int semestre;
	private String profesor;
	private Salon salon;
}