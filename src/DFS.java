import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DFS {
	private GrafoDirigido<Genero> grafo;
	private String origen;
	private HashMap<String, String> colores;

	public DFS(GrafoDirigido<Genero> grafo, String origen) {
		this.origen = origen;
		this.grafo = grafo;
		this.colores = new HashMap<>();
	}

	public ArrayList<Genero> encontrarCamino() {
		Iterator<Genero> it = this.grafo.obtenerVertices();
		
		while (it.hasNext()) {
			Genero genero = it.next();
			colores.put(genero.getGenero(), "blanco");
		}
		return encontrarCaminoVisit(this.origen);
	}

	public ArrayList<Genero> encontrarCaminoVisit(String verticeActual) {
		ArrayList<Genero> salida = new ArrayList<>();
		colores.put(verticeActual, "amarillo");
		ArrayList<Arco<Integer>> adyacentes = this.grafo.obtenerAdyacentes(verticeActual);
		
		for (Arco<Integer> arco : adyacentes) {
			String verticeAdyacente = arco.getVerticeDestino().getGenero();
			
			if (colores.get(verticeAdyacente).equals("blanco")) {
				salida.addAll(encontrarCaminoVisit(verticeAdyacente));
				salida.add(this.grafo.getGenero(verticeActual));
				return salida;
			} else if (colores.get(verticeAdyacente).equals("amarillo") && (verticeAdyacente.equals(this.origen))) {
				salida.add(this.grafo.getGenero(verticeAdyacente));
				return salida;
			}
		}
		colores.put(verticeActual, "negro");
		return salida;
	}
}
