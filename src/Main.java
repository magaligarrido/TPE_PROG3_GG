import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Nodo raiz = null;
		Coleccion coleccionLibros = new Coleccion(raiz);
		GrafoDirigido<Genero> grafoGeneros = new GrafoDirigido<>();

		crearColeccionLibros(coleccionLibros);
		menu(coleccionLibros, grafoGeneros);
	}

	public static void buscarCiclo(String generoBuscar, GrafoDirigido<Genero> grafo) {
		
		DFS dfs = new DFS(grafo, generoBuscar);
		ArrayList<Genero> cicloGeneros = dfs.encontrarCamino();
		
		System.out.println("Ciclo encontrado a partir del genero " + generoBuscar + "\n");
		String salida = "";
		
		for (Genero genero : cicloGeneros) {
			
			if (salida.equals("")) {
				salida += genero.getGenero();
			}else
				salida += " -> " + genero.getGenero();
		}
		System.out.println(salida);
	}

	public static void buscarCaminoMasLargo(String generoBuscar, GrafoDirigido<Genero> grafo) {
		
		if (grafo.contieneVertice(generoBuscar)) {
			
			BackTracking backtracking = new BackTracking();
			Solucion sol = backtracking.back(grafo.getGenero(generoBuscar), grafo);
			
			System.out.println("La mejor solucion encontrada: " + sol);			
		} else {
			System.out.println("El genero ingresado no existe :( ");
		}
	}

	public static void buscarMayorRepeticion(String generoBuscar, GrafoDirigido<Genero> grafo) {

		if (grafo.contieneVertice(generoBuscar)) {
			Genero generoBuscado = grafo.getGenero(generoBuscar);
			ArrayList<Arco<Integer>> arcosOrdenados = generoBuscado.getArcosOrdenadosPorPeso();

			if (arcosOrdenados != null) {
				for (Arco<Integer> a : arcosOrdenados) {
					System.out.println(a.toString());
				}
			} else
				System.out.println("El vertice no tiene arcos asociados");
		} else
			System.out.println("El vertice no existe");
	}

	public static void menu(Coleccion coleccionLibros, GrafoDirigido<Genero> grafoGeneros) {
		
		System.out.println(
				"Elija una opcion \n" +
		"1-Buscar por genero \n" + 
		"2-Estadisticas de busqueda"
		);
		
		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner
		int indice = Integer.parseInt(entradaTeclado);
		
		switch (indice) {
		case 1: {
			menuParteUno(coleccionLibros);
			menu(coleccionLibros, grafoGeneros);
			break;
		}
		case 2: {
			menuParteDos(grafoGeneros);
			menu(coleccionLibros, grafoGeneros);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + indice);
		}
	}

	public static void menuParteUno(Coleccion coleccionLibros) {
		System.out.println("ingrese Genero a filtrar");

		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner
		String generoBuscado = entradaTeclado;
		
		if (coleccionLibros.getRoot().existeGenero(generoBuscado)) {
			Nodo nodoGenero = coleccionLibros.getRoot().getNodo(generoBuscado);
			ArrayList<Libro> generoFiltrado = new ArrayList<>(nodoGenero.getGenero().getLibros());
			
			for (Libro libro : generoFiltrado) {
				System.out.println(libro.getTitulo());
			}
			escrituraLibros(generoFiltrado);
		} else {
			System.out.println("No se encontro genero");
		}
	}

	public static void menuParteDos(GrafoDirigido<Genero> grafoGeneros) {
		lecturaGeneros(grafoGeneros);

		System.out.println("Ingrese genero a buscar");
		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner
		String generoBuscar = entradaTeclado;

		System.out.println(
				"Elija una opcion \n" + 
		"1-Obtener los N generos mas buscado luego de buscar por el genero " + generoBuscar + "\n" +
		"2-A partir del genero " + generoBuscar + " obtener la secuencia de generos que mas alto valor de busqueda posee \n" +
		"3-Obtener un grafo unicamente con los generos afines a " + generoBuscar
		);
		
		String indiceTeclado = "";
		Scanner indiceEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		indiceTeclado = indiceEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner

		int indice = Integer.parseInt(indiceTeclado);
		switch (indice) {
			case 1: {
				buscarMayorRepeticion(generoBuscar, grafoGeneros);
				break;
			}
			case 2: {
				buscarCaminoMasLargo(generoBuscar, grafoGeneros);
				break;
			}
			case 3: {
				buscarCiclo(generoBuscar, grafoGeneros);
				break;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + indice);
			}
		}

	}

	public static void crearColeccionLibros(Coleccion coleccionLibros) {
		lecturaLibros(coleccionLibros);
	}

	public static void escrituraLibros(ArrayList<Libro> libros) {
		BufferedWriter bw = null;
		try {
			File file = new File("./datasets/datasetBuscado.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (Libro libro : libros) {
				bw.write(libro.getTitulo());
				bw.newLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void lecturaLibros(Coleccion coleccionLibros) {
		long startime = System.currentTimeMillis();

		String csvFile = "./datasets/dataset4.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// SALTAMOS LA PRIMER LINEA QUE CONTIENE INFORMACION DE COLUMNAS
			line = br.readLine();
			line.split(cvsSplitBy);

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);

				if (items.length > 0) {
					Libro libro = new Libro(items[0], items[1], Integer.parseInt(items[2]));
					String[] generos = items[3].split(" ");

					if (generos.length > 0) {

						for (int j = 0; j < generos.length; j++) {
							libro.addGenero(generos[j]);
						}

						for (int i = 0; i < generos.length; i++) {
							String genero = generos[i];

							if (coleccionLibros.getRoot() == null) {
								Genero g = new Genero(genero);
								g.addLibro(libro);
								coleccionLibros.setroot(new Nodo(g));
							} else if (!coleccionLibros.getRoot().existeGenero(genero)) {
								Genero g = new Genero(genero);
								g.addLibro(libro);
								coleccionLibros.getRoot().addGenero(g);
							} else {
								coleccionLibros.getRoot().getNodo(genero).getGenero().addLibro(libro);
							}
						}
					}
				}
			}
			long endTime = System.currentTimeMillis() - startime;
			System.out.println(Long.toString(endTime));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lecturaGeneros(GrafoDirigido<Genero> grafoGeneros) {
		long startime = System.currentTimeMillis();

		String csvFile = "./datasetsGeneros/dataset4.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// SALTAMOS LA PRIMER LINEA QUE CONTIENE INFORMACION DE COLUMNAS
			line = br.readLine();
			line.split(cvsSplitBy);

			while ((line = br.readLine()) != null) {
				String[] filaGeneros = line.split(cvsSplitBy);

				if (filaGeneros.length > 0) {
					Genero genero = null;

					for (int i = 0; i < filaGeneros.length; i++) {
						String generoActual = filaGeneros[i];

						if (!grafoGeneros.contieneVertice(generoActual)) {
							genero = new Genero(generoActual);
							grafoGeneros.agregarVertice(generoActual, genero);
						} else {
							genero = grafoGeneros.getGenero(generoActual);
						}

						if (filaGeneros.length > (i + 1)) {
							Genero generoSiguiente = null;
							String siguiente = filaGeneros[i + 1];

							if (!grafoGeneros.contieneVertice(siguiente)) {
								generoSiguiente = new Genero(siguiente);
								grafoGeneros.agregarVertice(siguiente, generoSiguiente);
							} else {
								generoSiguiente = grafoGeneros.getGenero(siguiente);
							}

							if (!grafoGeneros.existeArco(generoActual, siguiente)) {
								int contador = 0;
								grafoGeneros.agregarArco(genero, generoSiguiente, contador);
							}
							grafoGeneros.incrementarArco(generoActual, siguiente);
						}
					}
				}
			}
			long endTime = System.currentTimeMillis() - startime;
			System.out.println("Tiempo de cargar genero en arbolBinario: " + Long.toString(endTime));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
