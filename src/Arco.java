public class Arco<T> {
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
		System.out.println("llegue");
		this.contador++;
	}
	
	public String toString() {
		return (this.getVerticeOrigen() + " " + this.getVerticeDestino()+ " repeticiones: " + contador);
	}
}
