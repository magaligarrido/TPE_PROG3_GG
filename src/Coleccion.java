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
	
	public void deleteColeccion(Genero g) {
		if(colecciones.contains(g)) {
			
		}
	}
	
	
}
