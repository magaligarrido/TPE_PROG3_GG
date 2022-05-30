import java.util.ArrayList;

public class Coleccion {
	private ArrayList<Genero> colecciones;

	public Coleccion() {
		super();
		this.colecciones = new ArrayList<>();
	}
	
	public void addColeccion(Genero g) {
		if(!colecciones.contains(g)) {
			this.colecciones.add(g);
		}
	}
	
	public void deleteColeccionPorGenero(Genero g) {
		if(!this.colecciones.isEmpty()) {
			if(colecciones.contains(g)) {
				this.colecciones.remove(g);
			}
		}
	}
	
	//No se si estara bien jajaja no encuentro fallas en mi logica
	public ArrayList<Libro> getLibrosPorGenero(String genero){
		for (Genero g : colecciones) {
			if( g.getGenero() == genero) {
				return g.getLibros();
			}
		}
	return null;
	}
}
