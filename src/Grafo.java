import java.util.Iterator;

public interface Grafo<T> {

	//Agregar vertice
	public void agregarVertice(String genero);
	
	// Borrar vertice
	public void borrarVertice(String genero);
	
	//Agrega arco con una etiqueta que representa el contador de
	// verticeId1 con el verticeId2
	public void agregarArco(String genero1, String genero2, Integer contador);
	
	// Borra el arco que conecta el verticeId1 con el verticeId2
	public void borrarArco(String genero1, String genero2);
	
	//Verifica si existe un vertice
	public boolean contieneVertice(String genero);
	
	//Verifica si existe un arco entre dos vertices
	public boolean existeArco(String genero1, String genero2);
	
	//Obtener el arco que conecta el verticeId1 con el verticeId2
	public Arco<Integer> obtenerArco(String genero1, String genero2);
	
	//Devuelve la cantidad total de arcos en el grafo
	public int cantidadArcos();
	
	//Obtiene un iterador que permite recorrer todos los vertices 
	// almacenados en el grafo
	public Iterator<String> obtenerVertices();
	
	//Obtiene un iterador que permite recorrer todos los vertices adyacentes
	public Iterator<String> obtenerAdyacentes(String genero);
	
	//Obtiene un iterador que permite recorrer todos los arcos del grafo
	public Iterator<Arco<Integer>> obtenerArcos();
	
	//Obtiene un iterador que permite recorres todos los arcos que 
	// parten desde verticeId
	public Iterator<Arco<Integer>> obtenerArcos(String genero);
}
