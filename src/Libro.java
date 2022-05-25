import java.util.ArrayList;

public class Libro {
	private String titulo, autor;
	private int cant_paginas;
	private ArrayList<String> generos;
	
	public Libro(String titulo, String autor, int cant_paginas) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.cant_paginas = cant_paginas;
		this.generos = new ArrayList<>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getCant_paginas() {
		return cant_paginas;
	}

	public void setCant_paginas(int cant_paginas) {
		this.cant_paginas = cant_paginas;
	}
	
	public void addGenero(String g) {
		if(!generos.contains(g)) {
			this.generos.add(g);
		}
	}
	
	public void deleteGenero(String g) {
		if(generos.contains(g)) {
			for(int i=0; i < this.generos.size(); i++) {
				if(generos.get(i).equals(g)) {
					this.generos.remove(i);
					break;
				}
			}
		}
	}
	
	//IMPLEMENTAR EQUALS PARA LIBRO
	
	
}
