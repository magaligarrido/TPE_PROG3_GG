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
	
	public boolean contieneGenero(String g) {
		for (Genero genero : colecciones) {
			if(g.equals(genero.getGenero())) {
				return true;
			}
		}
		return false;
	}
	
	public void addLibroPorGenero(String g, Libro l) {
		for (Genero genero : colecciones) {
			if(genero.getGenero().equals(g)) {
				genero.addLibro(l);
			}
		}
	}

	public Genero getGenero(String g) {
		Genero salida;
		for (Genero genero : colecciones) {
			if(genero.getGenero().equals(g)) {
				salida = new Genero(g);
				for (Libro l : genero.copiaLibros()) {
					salida.addLibro(l);
				}
				return salida;
			}
		}
		return null;
	}
	public ArrayList<Genero> getGeneros(){
		ArrayList<Genero> salida = new ArrayList<>();
		for (Genero genero : colecciones) {
			salida.add(genero);
		}
		return salida;
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
			if( g.getGenero().equals(genero)) {
				return g.copiaLibros();
			}
		}
	return null;
	}
}
