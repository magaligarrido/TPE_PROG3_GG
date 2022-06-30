import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<String, Genero> indicesGeneros;

	public void agregarVertice(String key, Genero genero) {
		if (indicesGeneros == null) { // Revisar esto
			this.indicesGeneros = new HashMap<>();
		}
		this.indicesGeneros.put(key, genero);
	}

	@Override
	public void borrarVertice(int verticeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregarArco(Genero origen, Genero destino, int contador) {
		Arco<Integer> a = new Arco<Integer>(origen, destino, contador);
		this.indicesGeneros.get(origen.getGenero()).addArco(a);
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
	}

	public boolean contieneVertice(String verticeNombre) {
		if (this.indicesGeneros != null) {
			return this.indicesGeneros.containsKey(verticeNombre);
		}
		return false;
	}

	@Override
	public boolean existeArco(String origen, String destino) {
		return this.indicesGeneros.get(origen).existeArco(destino);
	}

	@Override
	public Arco<T> obtenerArco(String origen, String destino) {
		return null;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Genero> obtenerVertices() {
		ArrayList<Genero> salida = new ArrayList<>();
		for (Genero g : indicesGeneros.values()) {
			salida.add(g);
		}
		Iterator<Genero> iterator = (salida.iterator());
		return iterator;
	}

	@Override
	public ArrayList<Arco<Integer>> obtenerAdyacentes(String genero) {
		if (this.getGenero(genero).getArcos() != null) {
			ArrayList<Arco<Integer>> salida = this.getGenero(genero).getArcos();
			return salida;
		}
		return null;
	}

	@Override
	public Iterator<Arco<Integer>> obtenerArcos() {
		ArrayList<Arco<Integer>> salida = new ArrayList<>();
		for (Genero g : indicesGeneros.values()) {
			salida.addAll(g.getArcos());
		}
		Iterator<Arco<Integer>> iterator = salida.iterator();
		return iterator;
	}
	
	public int cantidadVertices() {
		return this.indicesGeneros.size();
	}
	
	public Genero getGenero(String genero) {
		return indicesGeneros.get(genero);
	}
	
	public void incrementarArco(String origen, String destino) {
		indicesGeneros.get(origen).aumentarContador(destino);
		//DELEGARLA A CLASE ARCO
	}
}
