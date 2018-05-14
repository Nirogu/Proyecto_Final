/**
 * @author LosCapos
 * Modelacion de los salones del programa academico.
 * Se utilizan el nombre y el cupo de estudiantes que le corresponden al salon.
 */
public class Salon {
	
	/**
	 * @param nombre
	 * @param cupo
	 * Metodo constructor para la clase Salon
	 */
	public Salon(String nombre, int cupo) {
		this.cupo = cupo;
		this.nombre = nombre;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return
	 * Metodo getter para la variable cupo.
	 */
	public int getCupo() {
		return cupo;
	}
	
	/**
	 * @param nombre
	 * Metodo setter para la variable nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @param cupo
	 * Metodo setter para la variable cupo
	 */
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	//Variables de clase.
	private int cupo;
	private String nombre;
}