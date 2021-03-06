import java.util.ArrayList;

public class Coleccion {
	private ArrayList<Genero> colecciones;
	private Nodo root;

	public Coleccion(Nodo nodo) {
		super();
		this.colecciones = new ArrayList<>();
		this.root = nodo;
	}

	public Nodo getRoot() {
		return this.root;
	}

	public void setroot(Nodo root) {
		this.root = root;
	}

	public void addColeccion(Genero g) {
		if (!colecciones.contains(g)) {
			this.colecciones.add(g);
		}
	}

	public boolean contieneGenero(String g) {
		for (Genero genero : colecciones) {
			if (g.equals(genero.getGenero())) {
				return true;
			}
		}
		return false;
	}

	public void addLibroPorGenero(String g, Libro l) {
		for (Genero genero : colecciones) {
			if (genero.getGenero().equals(g)) {
				genero.addLibro(l);
			}
		}
	}

	public Genero getGenero(String g) {
		Genero salida;
		for (Genero genero : colecciones) {
			if (genero.getGenero().equals(g)) {
				salida = new Genero(g);
				for (Libro l : genero.getLibros()) {
					salida.addLibro(l);
				}
				return salida;
			}
		}
		return null;
	}

	public ArrayList<Genero> getGeneros() {
		ArrayList<Genero> salida = new ArrayList<>();
		for (Genero genero : colecciones) {
			salida.add(genero);
		}
		return salida;
	}

	public void deleteColeccionPorGenero(Genero g) {
		if (!this.colecciones.isEmpty()) {
			if (colecciones.contains(g)) {
				this.colecciones.remove(g);
			}
		}
	}

	public ArrayList<Libro> getLibrosPorGenero(String genero) {
		ArrayList<Libro> salida = new ArrayList<>();
		for (Genero g : colecciones) {
			if (g.getGenero().equals(genero)) {
				salida = g.getLibros();
				break;
			}
		}
		return salida;
	}
}
