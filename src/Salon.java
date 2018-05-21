/**
 * Modelacion de los salones del programa academico.
 * Se utilizan el nombre y el cupo de estudiantes que le corresponden al salon.
 * @author LosCapos
 */
public class Salon {
	
	/**
	 * Metodo constructor para la clase Salon
	 * @param nombre
	 * Nombre del salon
	 * @param cupo
	 * Cupo del salon
	 */
	public Salon(String nombre, int cupo) {
		this.cupo = cupo;
		this.nombre = nombre;
	}
	
	/**
	 * Metodo getter para la variable nombre.
	 * @return
	 * Nombre del salon
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo getter para la variable cupo.
	 * @return
	 * Cupo del salon
	 */
	public int getCupo() {
		return cupo;
	}
	
	/**
	 * Metodo setter para la variable nombre.
	 * @param nombre
	 * Nombre por definir
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo setter para la variable cupo
	 * @param cupo
	 * Cupo por definir
	 */
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	//Variables de clase.
	private int cupo;
	private String nombre;
}