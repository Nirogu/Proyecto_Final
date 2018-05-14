public class Salon {
	
	public Salon(String nombre, int cupo) {
		this.cupo = cupo;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCupo() {
		return cupo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	private int cupo;
	private String nombre;
}
