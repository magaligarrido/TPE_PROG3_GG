import java.util.ArrayList;
import java.util.Iterator;

public class Genero {
	private String genero;
	private ArrayList<Libro> libros;
	
	public Genero(String genero) {
		super();
		this.genero = genero;
		this.libros = new ArrayList<>();
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void addLibro(Libro l) {
		if(!libros.contains(l)) {
			this.libros.add(l);
		}
	}
	
	public void deleteLibro(Libro l) {
		if(libros.contains(l)) {
			for(int i=0; i<libros.size(); i++) {
				if(libros.get(i).equals(l)) {
					this.libros.remove(i);
					break;
				}
			}
		}
	}
	
	public ArrayList<Libro> copiaLibros(){
		ArrayList<Libro> libros = new ArrayList<>();
		for (Libro libro : this.libros) {
			libros.add(libro);
		}
		return libros;
	}
		
	public boolean contieneLibro(Libro l) {
		for (Libro libro : this.libros) {
			if(libro.equals(l)) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(Object o) {
		try{
			Genero genero = (Genero) o;
			if(this.getGenero().equals(genero.getGenero())) {
				for (Libro libro : genero.copiaLibros()) {
					if(!this.copiaLibros().contains(libro)) {
						return false;
					}
				}
				return true;
			}
			return false;			
		}
		catch (Exception e) {
			return false;
		}
	}
	 
	


	
	
}
