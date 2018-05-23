import acm.program.*;
import java.util.*;
import java.io.*;

/**
 * Clase principal del programa. Permite la interaccion entre el usuario y todos los componentes del programa.
 * @author LosCapos
 */
public class Main extends ConsoleProgram {
	
	//Metodo principal del programa
	public void run() {
		println("Este programa le permitira generar y administrar horarios estudiantiles. A continuacion, se le solicitara informacion correspondiente a los salones de clase disponibles para el uso del programa academico.");
		
		while (true) {
			String eleccionSalones = readLine("Escriba la direccion del archivo del cual desea obtener la informacion de los salones. Si desea ingresar los salones a mano, escriba \"NO\": ");
			if (eleccionSalones.toUpperCase().equals("NO")) {
				inscribirSalones();
			} else {
				leerSalones(eleccionSalones);
			}
			if (listaSalones.size() > 0) {
				break;
			} else {
				println("Por favor ingrese la informacion de los salones disponibles para el programa academico");
			}
		}
		
		limiteEstudiantes = listaSalones.get(0).getCupo();
		for (Salon salon : listaSalones) {
			if(salon.getCupo() < limiteEstudiantes) limiteEstudiantes = salon.getCupo();
		}
		
		println("A continuacion se le pedira la informacion de los profesores que desea inscribir.");
		while (true) {
			String eleccionProfesores = readLine("Escriba la direccion del archivo del cual desea obtener la informacion de los profesores. Si desea ingresar los profesores a mano, escriba \"NO\": ");
			if (eleccionProfesores.toUpperCase().equals("NO")) {
				inscribirProfesores();
			} else {
				leerProfesores(eleccionProfesores);
			}
			if (listaProfesores.size() > 0) {
				break;
			} else {
				println("Por favor ingrese la informacion de los profesores disponibles en el programa academico");
			}
		}
		
		println("A continuacion se le pedira la informacion de las materias que desea agregar al programa academico.");
		while (true) {
			String eleccionMaterias = readLine("Escriba la direccion del archivo del cual desea obtener la informacion de las materias. Si desea ingresar las materias a mano, escriba \"NO\": ");
			if (eleccionMaterias.toUpperCase().equals("NO")) {
				inscribirMaterias();
			} else {
				leerMaterias(eleccionMaterias);
			}
			if(listaMaterias.size() > 0) {
				break;
			} else {
				println("Por favor ingrese la informacion de las materias disponibles para el programa academico");
			}
		}
		
		println("A continuacion se le pedira la informacion de los estudiantes que desea inscribir.");
		while (true) {
			String eleccionEstudiantes = readLine("Escriba la direccion del archivo del cual desea obtener la informacion de los estudiantes. Si desea ingresar los estudiantes a mano, escriba \"NO\": ");
			if (eleccionEstudiantes.toUpperCase().equals("NO")) {
				inscribirEstudiantes();
			} else {
				leerEstudiantes(eleccionEstudiantes);
			}
			if (listaEstudiantes.size() > 0) {
				break;
			} else {
				println("Por favor ingrese la informacion de los estudiantes inscritos en el programa academico");
			}
		}
		
		while (true) {
			String nombreSolicitado = readLine("Ingrese el nombre del estudiante del cual "
					+ "desea obtener el horario: ").toUpperCase();
			Estudiante estudianteSolicitado = buscarEstudiante(nombreSolicitado, listaEstudiantes);
			if (estudianteSolicitado == null) {
				println("El estudiante solicitado no se encuentra registrado en el programa.");
				continue;
			}
			int semestreSolicitado = estudianteSolicitado.getSemestre();
			materiasEstudiante = new ArrayList<Materia>();
			for(Materia materia : listaMaterias) {
				if (semestreSolicitado == materia.getSemestre()) {
					materiasEstudiante.add(materia);
				}
			}
			
			String nombreArchivo = readLine("Ingrese la ubicacion completa del directorio donde desea que se guarde el horario del estudiante: ") + nombreSolicitado + ".csv";
			crearTabla(materiasEstudiante, nombreArchivo);
		}
	}

	/**
	 * El metodo recibe del usuario la informacion de los salones existentes en el programa academico.
	 * Cada salon es anexada al ArrayList de salones, para su uso posterior.
	 */
	private void inscribirSalones() {
		int numSalones = readInt("Por favor digite el numero de salones: ");
		for (int i=1 ; i<= numSalones ; i++) {
			String nombreSalon = readLine("Digite el nombre del salon " + i + ": ").toUpperCase();
			if (verificarSalon(nombreSalon)) {
				i--;
				continue;
			}
			int cupoSalon = readInt ("Digite la maxima cantidad de estudiantes que pueden tomar clase en el salon " + i + ": ");
			listaSalones.add(new Salon(nombreSalon, cupoSalon));
		}
	}
	
	/**
	 * El metodo recibe del usuario la informacion de las materias existentes por cada semestre en el programa academico.
	 * Cada materia es anexada al ArrayList de materias totales, para su uso posterior.
	 */
	private void inscribirMaterias() {
		numeroSemestres = readInt("Ingrese el numero de semestres del programa academico: ");
		for (int i= 1 ; i<=numeroSemestres ; i++) {
			int numeroMaterias = readInt("Digite el numero de materias que tiene el semestre " + i + ": ");
			for (int j= 0 ; j < numeroMaterias ; j++) {
				String nombreMateria = readLine("Digite el nombre de la materia " + (j+1) + ": ").toUpperCase();
				int diaMateria = readInt("Digite el numero del dia de la materia " + (j+1) + ": ");
				if(diaMateria>5 || diaMateria<1) {
					println ("Por favor digite un dia de la semana valido, entre lunes y viernes, teniendo en cuenta que el lunes es el dia #1.");
					j--;
					continue;
				}
				int horaMateria = readInt("Digite la hora inicial de la materia " + (j+1) + ": ");
				if (horaMateria >= 24 || horaMateria < 0) {
					println("Por favor digite una hora del dia valida, entre 00 y 23 horas del dia");
					j--;
					continue;
				}
				int semestreMateria = i;
				if (verificarMateria(diaMateria, horaMateria, semestreMateria)) {
					j--;
					continue;
				}
				String profesorMateria = listaProfesores.get(revisarProfesor(j, diaMateria, horaMateria)%listaProfesores.size());
				Salon salonMateria = listaSalones.get(revisarSalon(j, diaMateria, horaMateria)%listaSalones.size());
				listaMaterias.add(new Materia(nombreMateria, diaMateria, horaMateria, semestreMateria, profesorMateria, salonMateria));
			}
		}
	}
	
	/**
	 * El metodo verifica que no exista una materia usando el salon dado, a la misma hora y dia que la materia que se esta intentando inscribir.
	 * @param j
	 * Ubicacion inicial en la lista de salones. Generado automaticamente
	 * @param dia
	 * Dia de la materia
	 * @param hora
	 * Hora de la materia
	 * @return
	 * Ubicacion del salon disponible, en la lista de salones
	 */
	public int revisarSalon(int j, int dia, int hora) {
		for (int i = 0; i<listaMaterias.size(); i++) {
			Materia materia = listaMaterias.get(i);
			if (materia.getSalon() == listaSalones.get(j%listaSalones.size()) && materia.getDia() == dia && materia.getHora() == hora) {
				j++;
				i = -1;
			}
		}
		return j;
	}
	
	/**
	 * El metodo verifica que no exista una materia usando el salon dado, a la misma hora y dia que la materia que se esta intentando inscribir.
	 * @param j
	 * Ubicacion inicial en la lista de profesores. Generado automaticamente
	 * @param dia
	 * Dia de la materia
	 * @param hora
	 * Hora de la materia
	 * @return
	 * Ubicacion del profesor disponible, en la lista de profesores
	 */
	public int revisarProfesor(int j, int dia, int hora) {
		for (int i = 0; i<listaMaterias.size(); i++) {
			Materia materia = listaMaterias.get(i);
			if (materia.getProfesor() == listaProfesores.get(j%listaProfesores.size()) && materia.getDia() == dia && materia.getHora() == hora) {
				j++;
				i = -1;
			}
		}
		return j;
	}
	
	/**
	 * El metodo recibre del usuario la informacion de los estudiantes inscritos en el programa academico.
	 * Cada estudiante es anexado al ArrayList de estudiantes totales, para su uso posterior.
	 */
	private void inscribirEstudiantes () {
		int[] estudiantesSemestre = new int[numeroSemestres];
		for (int i = 0; i<numeroSemestres; i++) {
			estudiantesSemestre[i] = 0;
		}
		int numEstudiantes = readInt("Por favor digite el numero de estudiantes: ");
		for (int i=1 ; i<= numEstudiantes ; i++) {
			while (true) {
				String nombreEstudiante = readLine("Digite el nombre del estudiante " + i + ": ").toUpperCase();
				if (verificarEstudiante(nombreEstudiante)) continue;
				int semestreEstudiante = readInt ("Digite el semestre en el que el estudiante " + i + " se encuentra: ");
				if (semestreEstudiante > numeroSemestres) {
					println ("Semestre no valido. Por favor inscriba al estudiante en uno de los semestres existentes en la carrera.");
					continue;
				}
				estudiantesSemestre[semestreEstudiante-1]++;
				if(estudiantesSemestre[semestreEstudiante-1] > limiteEstudiantes) {
					println("Por favor no sobrepasar el cupo de los salones. No es posible crear otro estudiante en el semestre " + semestreEstudiante);
					continue;
				}
				listaEstudiantes.add(new Estudiante(nombreEstudiante, semestreEstudiante));
				break;
			}
		}
	}
	
	/**
	 * El metodo recibre del usuario la informacion de los profesores disponibles el programa academico.
	 * Cada estudiante es anexado al ArrayList de estudiantes totales, para su uso posterior.
	 */
	private void inscribirProfesores() {
		int numProfesores = readInt("Por favor digite el numero de profesores: ");
		for (int i=1 ; i<= numProfesores ; i++) {
			while (true) {
				String nombreProfesor = readLine("Digite el nombre del profesor " + i + ": ").toUpperCase();
				if (verificarProfesor(nombreProfesor)) continue;
				listaProfesores.add(nombreProfesor);
				break;
			}
		}
	}
	
	/**
	 * El metodo busca un estudiante por medio del nombre del mismo.
	 * @param busquedaEstudiante
	 * Nombre del estudiante solicitado
	 * @param lista
	 * Lista de estudiantes
	 * @return
	 * Estudiante solicitado
	 */
	public Estudiante buscarEstudiante(String busquedaEstudiante , ArrayList<Estudiante> lista) {
		for(Estudiante e : lista){
			if (busquedaEstudiante.equals(e.getNombre())) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * El metodo escribe un archivo csv con el horario completo del estudiante que el usuario haya solicitado
	 * @param lista
	 * Lista de las materias del estudiante solicitado
	 * @param directorio
	 * Ubicacion final del archivo
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
					if (materia.getDia() == 6) continue;
					if (materia.getHora() == hora) {
						switch (materia.getDia()) {
						case 1: materiaLunes += (materia.getNombre() + " (Salon: " + materia.getSalon().getNombre() + " - Profesor: " + materia.getProfesor() + ")"); break;
						case 2: materiaMartes += (materia.getNombre() + " (Salon: " + materia.getSalon().getNombre() + " - Profesor: " + materia.getProfesor() + ")"); break;
						case 3: materiaMiercoles += (materia.getNombre() + " (Salon: " + materia.getSalon().getNombre() + " - Profesor: " + materia.getProfesor() + ")"); break;
						case 4: materiaJueves += (materia.getNombre() + " (Salon: " + materia.getSalon().getNombre() + " - Profesor: " + materia.getProfesor() + ")"); break;
						default: materiaViernes += (materia.getNombre() + " (Salon: " + materia.getSalon().getNombre() + " - Profesor: " + materia.getProfesor() + ")"); break;
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
	
	/**
	 * El metodo revisa en la lista de estudiantes, si ya existe un estudiante con el mismo nombre del estudiante que se esta intentando crear.
	 * @param nombre
	 * Nombre del estudiante en creacion.
	 * @return
	 * True si ya existe un estudiante con el mismo nombre, y false en caso contrario.
	 */
	public boolean verificarEstudiante(String nombre) {
		for (Estudiante estudiante : listaEstudiantes) {
			if (nombre.equals(estudiante.getNombre())) {
				println("Ya existe un estudiante registrado con ese nombre. Por favor ingrese un nombre diferente.");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * El metodo revisa en la lista de materias, si ya existe una materia con el mismo horario y semestre de la materia que se esta intentando crear
	 * @param dia
	 * Dia de la materia en creacion
	 * @param hora
	 * Hora de la materia en creacion
	 * @param semestre
	 * Semestre de la materia en creacion
	 * @return
	 * True si la materia no puede crearse en esa franja horaria, y false en caso contrario
	 */
	public boolean verificarMateria(int dia, int hora, int semestre) {
		for (Materia materia : listaMaterias) {
			if (semestre == materia.getSemestre() && dia == materia.getDia() && hora == materia.getHora()) {
				println("Ya existe una materia en este semestre, que tiene lugar al mismo tiempo. Por favor cambie la informacion ingresada");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * El metodo revisa en la lista de salones, si ya existe un salon con el mismo nombre del salon que se esta intentando crear
	 * @param nombre
	 * Nombre del salon en creacion
	 * @return
	 * True si ya existe un salon con el mismo nombre, y false en caso contrario
	 */
	public boolean verificarSalon(String nombre) {
		for (Salon salon : listaSalones) {
			if (nombre.equals(salon.getNombre())) {
				println("Ya existe un salon registrado con ese nombre. Por favor ingrese un nombre diferente.");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * El metodo revisa en la lista de profesores, si ya existe un profesor con el mismo nombre del profesor que se esta intentando crear
	 * @param nombre
	 * Nombre del profesor en creacion
	 * @return
	 * True si ya existe un profesor con el mismo nombre, y false en caso contrario
	 */
	public boolean verificarProfesor(String nombre) {
		for(String profesor : listaProfesores) {
			if(profesor.equals(nombre)) {
				println("Ya existe un profesor registrado con ese nombre. Por favor ingrese un nombre diferente.");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * El metodo crea la lista de salones con la informacion obtenida del archivo especificado por el usuario.
	 * @param archivo
	 * Archivo con la informacion de los salones
	 */
	public void leerSalones(String archivo) {
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			while (true) {
				String salon = leer.readLine();
				if (salon == null) break;
				String nombre = "";
				String cupo = "";
				int i;
				for (i = 0; i<salon.length(); i++) {
					if(salon.charAt(i) == ',') break;
					nombre += salon.charAt(i);
				}
				if (verificarSalon(nombre.toUpperCase())) {
					println("Solo se ha guardado un salon con el nombre " + nombre + ".");
					continue;
				}
				for (int j = i+1; j<salon.length(); j++) {
					cupo += salon.charAt(j);
				}
				listaSalones.add(new Salon(nombre.toUpperCase(), Integer.valueOf(cupo)));
			}
			leer.close();
		} catch (IOException e) {
			println("No se ha encontrado el archivo solicitado. Por favor ingrese una ruta valida, o escriba \"NO\" para ingresar los salones a mano");
		}
	}
	
	/**
	 * El metodo crea la lista de materias con la informacion obtenida del archivo especificado por el usuario.
	 * @param archivo
	 * Archivo con la informacion de las materias
	 */
	public void leerMaterias(String archivo) {
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			int a = -1;
			while (true) {
				String materia = leer.readLine();
				if (materia == null) break;
				String nombre = "";
				String dia = "";
				String hora = "";
				String semestre = "";
				int i;
				int j;
				int k;
				for (i = 0; i<materia.length(); i++) {
					if(materia.charAt(i) == ',') break;
					nombre += materia.charAt(i);
				}
				for (j = i+1; j<materia.length(); j++) {
					if(materia.charAt(j) == ',') break;
					dia += materia.charAt(j);
				}
				if (Integer.valueOf(dia)<1 || Integer.valueOf(dia)>5) {
					println ("Por favor ingrese un dia de la semana valido, entre lunes y viernes, teniendo en cuenta que el lunes es el dia #1.");
					println("No se ha podido guardar la materia " + nombre + ".");
					continue;
				}
				for (k = j+1; k<materia.length(); k++) {
					if(materia.charAt(k) == ',') break;
					hora += materia.charAt(k);
				}
				if (Integer.valueOf(hora)<0 || Integer.valueOf(hora)>23) {
					println("Por favor digite una hora del dia valida, entre 00 y 23 horas del dia");
					println("No se ha podido guardar la materia " + nombre + ".");
				}
				for (int l = k+1; l<materia.length(); l++) {
					semestre += materia.charAt(l);
				}
				if(Integer.valueOf(semestre) > numeroSemestres) numeroSemestres = Integer.valueOf(semestre);
				if (verificarMateria(Integer.valueOf(dia), Integer.valueOf(hora), Integer.valueOf(semestre))) {
					println("No se ha guardado la materia " + nombre + ", ya que ya existe una materia dictada en ese momento");
					continue;
				}
				a++;
				String profesorMateria = listaProfesores.get(revisarProfesor(a, Integer.valueOf(dia), Integer.valueOf(hora))%listaProfesores.size());
				Salon salonMateria = listaSalones.get(revisarSalon(a, Integer.valueOf(dia), Integer.valueOf(hora))%listaSalones.size());
				listaMaterias.add(new Materia(nombre.toUpperCase(), Integer.valueOf(dia), Integer.valueOf(hora), Integer.valueOf(semestre), profesorMateria, salonMateria));
			}
			leer.close();
		} catch (IOException e) {
			println("No se ha encontrado el archivo solicitado. Por favor ingrese una ruta valida, o escriba \"NO\" para ingresar las materias a mano");
		}
	}
	
	/**
	 * El metodo crea la lista de estudiantes con la informacion obtenida del archivo especificado por el usuario.
	 * @param archivo
	 * Archivo con la informacion de los estudiantes
	 */
	public void leerEstudiantes(String archivo) {
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			int[] estudiantesSemestre = new int[numeroSemestres];
			for (int i = 0; i<numeroSemestres; i++) {
				estudiantesSemestre[i] = 0;
			}
			while (true) {
				String estudiante = leer.readLine();
				if (estudiante == null) break;
				String nombre = "";
				String semestre = "";
				int i;
				for (i = 0; i<estudiante.length(); i++) {
					if(estudiante.charAt(i) == ',') break;
					nombre += estudiante.charAt(i);
				}
				if (verificarEstudiante(nombre.toUpperCase())) {
					println("Solo se ha guardado un estudiante con el nombre " + nombre + ".");
					continue;
				}
				for (int j = i+1; j<estudiante.length(); j++) {
					semestre += estudiante.charAt(j);
				}
				estudiantesSemestre[Integer.valueOf(semestre)-1]++;
				if(estudiantesSemestre[Integer.valueOf(semestre)-1] > limiteEstudiantes) {
					println("Por favor no sobrepasar el cupo de los salones. No es posible crear otro estudiante en el semestre " + Integer.valueOf(semestre));
					continue;
				}
				listaEstudiantes.add(new Estudiante(nombre.toUpperCase(), Integer.valueOf(semestre)));
			}
			leer.close();
		} catch (IOException e) {
			println("No se ha encontrado el archivo solicitado. Por favor ingrese una ruta valida, o escriba \"NO\" para ingresar los estudiantes a mano");
		}
	}
	
	/**
	 * El metodo crea la lista de profesores con la informacion obtenida del archivo especificado por el usuario.
	 * @param archivo
	 * Archivo con la informacion de los profesores
	 */
	public void leerProfesores(String archivo) {
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			while (true) {
				String profesor = leer.readLine();
				if (profesor == null) break;
				listaProfesores.add(profesor);
			}
			leer.close();
		} catch (IOException e) {
			println("No se ha encontrado el archivo solicitado. Por favor ingrese una ruta valida, o escriba \"NO\" para ingresar los estudiantes a mano");
		}
	}
	
	//Variables de clase
	int limiteEstudiantes;
	int numeroSemestres = 0;
	ArrayList<Materia> materiasEstudiante;
	ArrayList<Materia> listaMaterias = new ArrayList<Materia>() ;
	ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
	ArrayList<Salon> listaSalones = new ArrayList<Salon>();
	ArrayList<String> listaProfesores = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
}
