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
		//Coleccion coleccionLibros = new Coleccion();
		GrafoDirigido<Genero> grafoGeneros = new GrafoDirigido<>();
		
		//crearColeccionLibros(coleccionLibros);
		
		//menu(coleccionLibros);
		
		lecturaGeneros(grafoGeneros);
		
		Iterator<Genero> it = grafoGeneros.obtenerVertices();
		int sizeVertices=0;
		while(it.hasNext()) {
			System.out.println(it.next().toString());
			sizeVertices++;
			}
		System.out.println(sizeVertices);
		
		
		Iterator<Arco<Integer>> itArco = grafoGeneros.obtenerArcos();
		int sizeArcos=0;
		while(itArco.hasNext()) {
			System.out.println(itArco.next().toString());
			sizeArcos++;
			}
		System.out.println(sizeArcos);
		
		
	}
	
	public static void menu(Coleccion coleccionLibros) {
		System.out.println("Elija una opcion \n"
				+ "1-Buscar por genero \n"
				+ "2-Estadisticas de busqueda");
		String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creaci�n de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un m�todo sobre un objeto Scanner
        
        int indice=Integer.parseInt(entradaTeclado);
		switch (indice) {
		case 1: {
			menuBuscaGenero(coleccionLibros);
			break;
		}
		case 2: {
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + indice);
		}
		
	}
	
	public static void crearColeccionLibros(Coleccion coleccionLibros) {
		lecturaLibros(coleccionLibros);
	}
	
	public static void menuBuscaGenero(Coleccion coleccionLibros) {
		for (int i = 0; i < coleccionLibros.getGeneros().size(); i++) {
			System.out.println(i+1+": "+coleccionLibros.getGeneros().get(i).getGenero());
		}
		System.out.println("ingrese Genero a filtrar");
		
		String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creaci�n de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un m�todo sobre un objeto Scanner
        
        int indice=Integer.parseInt(entradaTeclado);
        String generoBuscado = coleccionLibros.getGeneros().get(indice-1).getGenero();
        
        ArrayList<Libro> generoFiltrado = coleccionLibros.getLibrosPorGenero(generoBuscado);
        
        for (Libro libro : generoFiltrado) {
			System.out.println(libro.getAutor());
		}
        escrituraLibros(generoFiltrado);
	}
	
	public static void escrituraLibros(ArrayList<Libro>libros) {
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
	
	public static void lecturaLibros(Coleccion coleccion) {
    	
    	long startime = System.currentTimeMillis();
    	
        String csvFile = "./datasets/dataset2.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
          	//para saltar la primer linea        	
        	line = br.readLine();
        	line.split(cvsSplitBy);

            while ((line=br.readLine()) != null) { 
                String[] items = line.split(cvsSplitBy);
     
                if(items.length>0) {
                	Libro libro = new Libro(items[0],items[1], Integer.parseInt(items[2]));                	
                	String[] generos = items[3].split(" ");                	
            		if(generos.length>0) {           			
                		for (int j = 0; j < generos.length; j++) {
                			
								libro.addGenero(generos[j]);								
                		}
                		for (int i = 0; i < libro.getGeneros().size(); i++) { 
                			
                			String g =libro.getGeneros().get(i);
							if(!coleccion.contieneGenero(g)) {
								
								Genero genero = new Genero(g);
								coleccion.addColeccion(genero);
								
							}						
							coleccion.addLibroPorGenero(g, libro);
							
						}
                	}            		
                }                 
            }           
            
        	long endTime = System.currentTimeMillis()- startime;
        	System.out.println(Long.toString(endTime));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void lecturaGeneros(GrafoDirigido<Genero> grafoGeneros) {

    	long startime = System.currentTimeMillis();
    	
        String csvFile = "./datasetsGeneros/dataset1.csv";
        String line = "";
        String cvsSplitBy = ",";
        
        int cont = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
          	//para saltar la primer linea        	
        	line = br.readLine();
        	line.split(cvsSplitBy);
        	
            while ((line=br.readLine()) != null) { 
                String[] filaGeneros = line.split(cvsSplitBy);
     
                if(filaGeneros.length>0) {
                	Genero genero = null;
                	for (int i = 0; i < filaGeneros.length; i++) { 
                		String generoActual = filaGeneros[i];
                		if (!grafoGeneros.contieneVertice(generoActual)) {                	
                			genero = new Genero(generoActual);
                			grafoGeneros.agregarVertice(generoActual, genero);
                			                			                			
                		} else {
                			genero = grafoGeneros.getGenero(generoActual);
                		}         
                		
                		if(filaGeneros.length > (i + 1)) {
                			String siguiente = filaGeneros[i+1];
                			if(!grafoGeneros.contieneVertice(siguiente)) {      
                			    Genero generoSiguiente = new Genero(siguiente);        			
                				              			
                				grafoGeneros.agregarVertice(siguiente, generoSiguiente);
                				               				
                			} 
                			
                			if (!grafoGeneros.existeArco(generoActual, siguiente)) { 
                				int contador = 1;
                				grafoGeneros.agregarArco(generoActual, siguiente,contador);
                				cont++;
                			}else{                  				
                				grafoGeneros.incrementarArco(generoActual, siguiente);   
                				
                				}
                   			} 
                	}
               	}		
            }
                              	                     
        	long endTime = System.currentTimeMillis()- startime;
        	System.out.println(Long.toString(endTime));
        	System.out.println(cont);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
		
	}
}

               	
                			
	      