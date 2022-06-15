
public class Arco<Integer> {
	private int verticeOrigen;
	private int verticeDestino;
	private Integer contador;
	
	
	public Arco(int verticeOrigen, int verticeDestino, Integer contador) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.contador = contador;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}
	
	public Integer getContador() {
		return contador;
	}
}
