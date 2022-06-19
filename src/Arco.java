public class Arco<T>{
	private String verticeOrigen;
	private String verticeDestino;
	private int contador;
	
	
	public Arco(String verticeOrigen, String verticeDestino,int contador) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.contador = contador;
	}

	public String getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public String getVerticeDestino() {
		return verticeDestino;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void incrementarContador() {

		this.contador++;
		
	}
	
	public boolean equals(Object o) {
		Arco<Integer> a = (Arco<Integer>) o;
		if((a.getVerticeOrigen().equals(this.getVerticeOrigen()))&&(a.getVerticeDestino().equals(this.getVerticeDestino()))){
			return true;
		}
		return false;
	}
	
	public String toString() {
		return (this.getVerticeOrigen() + " --> " + this.getVerticeDestino()+ " -- cantidad de repeticiones: " + contador);
	}
}
