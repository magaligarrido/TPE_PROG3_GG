import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Coleccion coleccion = new Coleccion();

		lectura(coleccion);
		
		for (int i = 0; i < coleccion.getGeneros().size(); i++) {
			System.out.println(i+1+": "+coleccion.getGeneros().get(i).getGenero());
		}
		System.out.println("ingrese Genero a filtrar");
		
		String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        
        int indice=Integer.parseInt(entradaTeclado);
        String generoBuscado = coleccion.getGeneros().get(indice-1).getGenero();
        
        ArrayList<Libro> generoFiltrado = coleccion.getLibrosPorGenero(generoBuscado);
        
        for (Libro libro : generoFiltrado) {
			System.out.println(libro.getAutor());
		}
        escritura(generoFiltrado);
		

	}
	
	public static void escritura(ArrayList<Libro>libros) {
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
	
	public static void lectura(Coleccion coleccion) {
    	
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
        	//System.out.println(Long.toString(endTime));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
