import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;

public class CSVReader {	
		
		 
	
	

    public static void main(String[] args) {
    	
    	long startime = System.currentTimeMillis();
    	int cont = 0;
    	int contlibros=0;
    	
        String csvFile = "./datasets/dataset4.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	Coleccion coleccion = new Coleccion();
        	//para saltar la primer linea
        	
        	line = br.readLine();
        	line.split(cvsSplitBy);

            while ((line=br.readLine()) != null) { 
            	contlibros ++;

                String[] items = line.split(cvsSplitBy);
               // System.out.println(items[3]);
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
							cont++;
							System.out.println(cont);
						}
                	}            		
                }                 
            }            
            
            	System.out.println(coleccion.getGeneros().size()+" iteraciones:" + cont + " libros: "+ contlibros);
//				for (Libro libro : coleccion.getLibrosPorGenero("cine")) {
//					System.out.println(libro.getAutor());
//				}
            	
				
            	long endTime = System.currentTimeMillis()- startime;
            	System.out.println(Long.toString(endTime));
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
