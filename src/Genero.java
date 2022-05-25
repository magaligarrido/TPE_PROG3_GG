import java.util.ArrayList;

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
	
//	public boolean equals(Object o) {
//		Genero g = (Genero) o;
//		if(this.getGenero().equals(g.getGenero())){
//			for(int i=0; i < this.libros.size(); i++) {
//				if (g.)
//			}
//		}
//	}
//	IMPLEMENTAR COPY
	
	
}
