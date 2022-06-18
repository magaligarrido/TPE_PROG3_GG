import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<String, Genero> indicesGeneros;
	
	
	public void agregarVertice(String key, Genero genero) {
		if(indicesGeneros == null) {    //Revisar esto
			this.indicesGeneros = new HashMap<>();
		}
		this.indicesGeneros.put(key, genero);
	}

	@Override
	public void borrarVertice(int verticeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarArco(String origen, String destino, int contador) {
		Arco<Integer> a = new Arco<>(origen, destino, contador);
		this.indicesGeneros.get(origen).addArco(a);		
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean contieneVertice(String verticeNombre) {
		//recorremos todo el grafo para ver si existe el vertice
		return false;
	}

	@Override
	public boolean existeArco(String origen, String destino) {
		return this.indicesGeneros.get(origen).existeArco(destino);
	}

	public void incrementarArco(String origen, String destino) {
		indicesGeneros.get(origen).aumentarContador(destino);
	}
	
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		return null;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Genero getGenero(String genero) {
		return indicesGeneros.get(genero);	
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
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<Integer>> obtenerArcos() {
		ArrayList<Arco<Integer>> salida = new ArrayList<>();
		for (Genero g: indicesGeneros.values()) {
				salida.addAll(g.getArcos());
			}	
		Iterator<Arco<Integer>> iterator = salida.iterator();
		return iterator;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
