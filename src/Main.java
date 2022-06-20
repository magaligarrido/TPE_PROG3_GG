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

		// crearColeccionLibros(coleccionLibros);
		crearColeccionLibros(coleccionLibros);

		// menu(coleccionLibros, grafoGeneros);
		menu(coleccionLibros, grafoGeneros);

	}

	public static ArrayList<Arco<Integer>> buscarMayorRepeticion(String generoBuscar, GrafoDirigido<Genero> grafo) {

		if (grafo.contieneVertice(generoBuscar)) {
			Genero generoBuscado = grafo.getGenero(generoBuscar);
			ArrayList<Arco<Integer>> arcosOrdenados = generoBuscado.getArcosOrdenadosPorPeso();

			return arcosOrdenados;

		}
		return null;
	}



	public static void menu(Coleccion coleccionLibros, GrafoDirigido<Genero> grafoGeneros) {
		System.out.println("Elija una opcion \n" + "1-Buscar por genero \n" + "2-Estadisticas de busqueda");
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

	public static void crearColeccionLibros(Coleccion coleccionLibros) {
		lecturaLibros(coleccionLibros);
	}




	public static void menuParteUno(Coleccion coleccionLibros) {

		System.out.println("ingrese Genero a filtrar");

		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner

		String generoBuscado = entradaTeclado;
		if(coleccionLibros.getRoot().existeGenero(generoBuscado)) {
			ArrayList<Libro> generoFiltrado = new ArrayList<>(coleccionLibros.getRoot().getNodo(generoBuscado).getGenero().copiaLibros());

			for (Libro libro : generoFiltrado) {
				System.out.println(libro.getAutor());
			}
			escrituraLibros(generoFiltrado);
		}else {
			System.out.println("No se encontro genero");
		}

		
	}

	public static void menuParteDos(GrafoDirigido<Genero> grafoGeneros) {
		lecturaGeneros(grafoGeneros);

		Iterator<Genero> it = grafoGeneros.obtenerVertices();
		int sizeVertices = 0;
		while (it.hasNext()) {
			System.out.println(it.next().toString());
			sizeVertices++;
		}
		System.out.println(sizeVertices);

		Iterator<Arco<Integer>> itArco = grafoGeneros.obtenerArcos();
		int sizeArcos = 0;
		while (itArco.hasNext()) {
			System.out.println(itArco.next().toString());
			sizeArcos++;
		}
		System.out.println(sizeArcos);

		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner

		String generoBuscar = entradaTeclado;
		// inciso 1
		/*
		 * ArrayList<Arco<Integer>>
		 * generosMasBuscados=buscarMayorRepeticion(generoBuscar,grafoGeneros);
		 * for(Arco<Integer> a: generosMasBuscados) { System.out.println(a.toString());
		 * }
		 * 
		 * 
		 * // inciso 2
		 * 
		 * if (grafoGeneros.contieneVertice(generoBuscar)) { Genero generoInicial =
		 * grafoGeneros.getGenero(generoBuscar); BackTracking backtracking = new
		 * BackTracking();
		 * 
		 * Solucion sol = backtracking.back(generoInicial, grafoGeneros);
		 * System.out.println(sol); } System.out.println("salio");
		 */

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

		String csvFile = "./datasets/dataset2.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// para saltar la primer linea
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

		String csvFile = "./datasetsGeneros/dataset2.csv";
		String line = "";
		String cvsSplitBy = ",";

		int cont = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// para saltar la primer linea
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
						// FIXME
						// TODO
						// ASD
						if (filaGeneros.length > (i + 1)) {
							String siguiente = filaGeneros[i + 1];
							if (!grafoGeneros.contieneVertice(siguiente)) {
								Genero generoSiguiente = new Genero(siguiente);

								grafoGeneros.agregarVertice(siguiente, generoSiguiente);

							}

							if (!grafoGeneros.existeArco(generoActual, siguiente)) {
								int contador = 0;
								grafoGeneros.agregarArco(generoActual, siguiente, contador);
								cont++;
							}
							grafoGeneros.incrementarArco(generoActual, siguiente);

						}
					}
				}
			}

			long endTime = System.currentTimeMillis() - startime;
			System.out.println(Long.toString(endTime));
			System.out.println(cont);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
