import java.util.ArrayList;
import java.util.Iterator;

public interface Grafo<T> {

	// Agregar vertice
	public void agregarVertice(String key, Genero genero);

	// Borrar vertice
	public void borrarVertice(int verticeId);

	// Agrega arco con una etiqueta que conecta el verticeId1 con el verticeId2
	public void agregarArco(Genero origen, Genero destino, int contador);

	// Borra el arco que conecta el verticeId1 con el verticeId2
	public void borrarArco(int verticeId1, int verticeId2);

	// Verifica si existe un vertice
	public boolean contieneVertice(String verticeNombre);

	// Verifica si existe un arco entre dos vertices
	public boolean existeArco(String origen, String destino);

	// Obtener el arco que conecta el verticeId1 con el verticeId2
	public Arco<T> obtenerArco(String origen, String destino);

	// Devuelve la cantidad total de arcos en el grafo
	public int cantidadArcos();

	// Obtiene un iterador que permite recorrer todos los vertices almacenados en el
	// grafo
	public Iterator<Genero> obtenerVertices();

	// Obtiene un iterador que permite recorrer todos los vertices adyacentes
	public ArrayList<Arco<Integer>> obtenerAdyacentes(String origen);

	// Obtiene un iterador que permite recorrer todos los arcos del grafo
	public Iterator<Arco<Integer>> obtenerArcos();
	
	//devuelvo la cantidad total de vertices
	public int cantidadVertices();
}
