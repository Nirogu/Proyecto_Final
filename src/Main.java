import acm.program.*;
import java.util.*;
import java.io.*;

/**
 * @author LosCapos
 * Clase principal del programa. Permite la interaccion entre el usuario y todos los componentes del programa.
 */
public class Main extends ConsoleProgram {
	
	private static final long serialVersionUID = 1L;
	
	//Metodo principal del programa
	public void run() {
		println("Este programa le permitira generar y administrar horarios estudiantiles. A continuacion, se le solicitara informacion correspondiente a las materias del programa academico.");
		inscribirMaterias();
		println(listaMaterias);
		println("A continuacion se le pedira la informacion de los estudiantes que desea inscribir.");
		inscribirEstudiantes();
		println(listaEstudiantes);
		while (true) {
			String estudianteSolicitado = readLine("Ingrese el nombre del estudiante del cual "
					+ "desea obtener el horario: ").toUpperCase();
			int semestreSolicitado = buscarEstudiante (estudianteSolicitado, listaEstudiantes) ;
			if (semestreSolicitado == -1) {
				println("El estudiante solicitado no se encuentra registrado en el programa.");
				continue;
			}
			materiasEstudiante = new ArrayList<Materia>();
			for(Materia materia : listaMaterias) {
				if (semestreSolicitado == materia.getSemestre()) {
					materiasEstudiante.add(materia);
				}
			}
			String nombreArchivo = readLine("Ingrese la ubicacion completa del directorio donde desea que se guarde el horario del estudiante: ") + estudianteSolicitado + ".csv";
			crearTabla(materiasEstudiante, nombreArchivo);
		}
	}
	
	/**
	 * El metodo recibe del usuario la información de las materias existentes por cada semestre en el programa academico.
	 * Cada materia es anexada al ArrayList de materias totales, para su uso posterior.
	 */
	private void inscribirMaterias() {
		numeroSemestres = readInt("Ingrese el numero de semestres del programa academico: ");
		for (int i= 1 ; i<=numeroSemestres ; i++) {
			int numeroMaterias = readInt("Digite el numero de materias que tiene el semestre " + i + ": ");
			for (int j= 1 ; j<= numeroMaterias ; j++) {
				String nombreMateria = readLine("Digite el nombre de la materia " + j + ": ").toUpperCase();
				int diaMateria = readInt("Digite el numero del dia de la materia " + j + ": ");
				if(diaMateria>5 || diaMateria<1) {
					println ("Por favor digite un dia de la semana valido, teniendo en cuenta que el lunes es el dia #1.");
					j--;
					continue;
				}
				int horaMateria = readInt("Digite la hora inicial de la materia " + j + ": ");
				int semestreMateria = i;
				listaMaterias.add(new Materia(nombreMateria, diaMateria, horaMateria, semestreMateria));
			}
		}
	}
	
	/**
	 * El metodo recibre del usuario la informacion de los estudiantes inscritos en el programa academico.
	 * Cada estudiante es anexado al ArrayList de estudiantes totales, para su uso posterior.
	 */
	private void inscribirEstudiantes () {
		int numestudiantes = readInt("Por favor digite el numero de estudiantes: ");
		for (int i=1 ; i<= numestudiantes ; i++) {
			while (true) {
				String nombreEstudiante = readLine("Digite el nombre del estudiante  " + i + ": ").toUpperCase();
				int semestreEstudiante = readInt ("Digite el semestre en el que el estudiante " + i + " se encuentra: ");
				if (semestreEstudiante > numeroSemestres) {
					println ("Semestre no valido. Por favor inscriba al estudiante en uno de los semestres existentes en la carrera.");
					continue;
				}
				listaEstudiantes.add(new Estudiante(nombreEstudiante, semestreEstudiante));
				break;
			}
		}
		
	}
	
	/**
	 * @param busquedaEstudiante
	 * @param lista
	 * @return
	 * El metodo busca un estudiante por medio del nombre del mismo, y retorna el semestre en el que se encuentra
	 */
	public int buscarEstudiante(String busquedaEstudiante , ArrayList<Estudiante> lista) {
		for(Estudiante e : lista){
			if (busquedaEstudiante.equals(e.getNombre())) {
				return e.getSemestre() ;
			}
		}
		return -1;
	}
	
	/**
	 * @param lista
	 * @param directorio
	 * El metodo escribe un archivo csv con el horario completo del estudiante que el usuario haya solicitado
	 */
	public void crearTabla (ArrayList<Materia> lista, String directorio) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(directorio));
			String tabla = "Hora,Lunes,Martes,Miercoles,Jueves,Viernes\n";
			ArrayList<Integer> listaHoras = new ArrayList<Integer>();
			for (Materia materia : lista) {
				Integer hora = Integer.valueOf(materia.getHora());
				if (!listaHoras.contains(hora))listaHoras.add(hora);
			}
			Collections.sort(listaHoras);
			for (int i = 0; i<listaHoras.size(); i++) {
				int hora = listaHoras.get(i).intValue();
				String materiaLunes = "";
				String materiaMartes = "";
				String materiaMiercoles = "";
				String materiaJueves = "";
				String materiaViernes = "";
				for (Materia materia : lista) {
					if (materia.getHora() == hora) {
						switch (materia.getDia()) {
						case 1: materiaLunes += materia.getNombre(); break;
						case 2: materiaMartes += materia.getNombre(); break;
						case 3: materiaMiercoles += materia.getNombre(); break;
						case 4: materiaJueves += materia.getNombre(); break;
						default: materiaViernes += materia.getNombre(); break;
						}
					}
				}
				String fila = hora + "," + materiaLunes + "," + materiaMartes + "," + materiaMiercoles + "," + materiaJueves + "," + materiaViernes;
				fila += "\n";
				tabla += fila;
			}
			escritor.print(tabla);
			escritor.close();
			println("El horario del estudiante se ha generado con exito.");
		} catch (IOException e) {
			println("El archivo no ha podido generarse correctamente");
		}
	}
	
	int numeroSemestres;
	ArrayList<Materia> materiasEstudiante;
	ArrayList<Materia> listaMaterias = new ArrayList<Materia>() ;
	ArrayList<Estudiante>listaEstudiantes = new ArrayList<Estudiante>();
}