import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class DFS {
	private GrafoDirigido<Genero> grafo;
	private String origen;
	private HashMap<String,String> colores;
	
	public DFS(GrafoDirigido<Genero> grafo, String origen){
		this.origen = origen;
		this.grafo = grafo;
		this.colores = new HashMap<>();
	}
	
	public ArrayList<Genero> encontrarCamino(){
		Iterator<Genero> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Genero genero = it.next();
			colores.put(genero.getGenero(), "blanco");
		}
		return encontrarCaminoVisit(this.origen);
	}
	
	public ArrayList<Genero> encontrarCaminoVisit(String verticeActual){
		colores.put(verticeActual, "amarillo");
		
		ArrayList<Genero> salida = new ArrayList<>();
		if(verticeActual == this.destino) {
			ArrayList<Genero> caminoUnico = new ArrayList<>();
			caminoUnico.add(verticeActual);
			
		}
		
		Iterator<Integer> it = this.grafo.obtenerAdyacentes(verticeActual)
		while(it.next()) {
			String adyacente = it.next();
			if (colores.get(adyacente).equals("blanco")) {
				ArrayList<Genero> camino = encontrarCaminoVisit(adyacente);
			for(ArrayList)
			}
		}
	}
}
