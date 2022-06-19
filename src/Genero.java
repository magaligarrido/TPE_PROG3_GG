import java.util.ArrayList;
import java.util.Iterator;

public class Genero {
	private String genero;
	private ArrayList<Libro> libros;
	private ArrayList<Arco<Integer>> arcos;
	
	public Genero(String genero) {
		super();
		this.genero = genero;
		this.libros = new ArrayList<>();
		this.arcos = new ArrayList<>();
	}
	
	public String toString() {
		return this.getGenero();
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
	
	public void addArco(Arco<Integer> arco) {
		this.arcos.add(arco);
	}
	
	public ArrayList<Arco<Integer>> getArcos(){
		ArrayList<Arco<Integer>> salida = new ArrayList<>();
		for (Arco<Integer> arco : arcos) {
			salida.add(arco);			
		}
		
		return salida;
	}
	
	public ArrayList<Arco<Integer>> getArcosOrdenadosPorPeso(){
		ArrayList<Arco<Integer>>salida= new ArrayList<>();
		int [] arrPeso;
		String[] arrDestinos;
		arrPeso= new int[this.arcos.size()];
		arrDestinos= new String[this.arcos.size()];
		int pos=0;
		while(pos<this.arcos.size()) {
			arrPeso[pos]= this.arcos.get(pos).getContador();
			arrDestinos[pos]=this.arcos.get(pos).getVerticeDestino();
			pos++;
		}
		

		MergeSort mergeSort = new MergeSort();
		mergeSort.sort(arrPeso,arrDestinos);
		
		for (int i = 0; i < arrDestinos.length; i++) {
			Arco<Integer> arco= new Arco<>(this.getGenero(),arrDestinos[i],arrPeso[i]);
			salida.add(arco);
			
		}

	

		return salida;
	}

	public void aumentarContador(String generoDestino){
		for (Arco<Integer> arco : arcos) {
			if(arco.getVerticeDestino().equals(generoDestino)) {
				arco.incrementarContador();
			}
		}
		
	}	
	
	public boolean existeArco(String generoDestino) {
		for (Arco<Integer> arco : arcos) {
			if(arco.getVerticeDestino().equals(generoDestino)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean tieneAdyacentes() {
		if(!this.arcos.isEmpty())
			return true;
		return false;
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
