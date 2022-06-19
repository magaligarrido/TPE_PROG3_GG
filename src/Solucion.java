import java.util.ArrayList;

public class Solucion {
	private ArrayList<Arco<Integer>> camino;
	private int peso;
	
	public Solucion(ArrayList<Arco<Integer>> camino,int peso) {
		this.peso=peso;
		this.camino= new ArrayList<>(camino);
	}
	public int getPeso() {
		return this.peso;
	}
	
	public String toString() {
		String salida="";
		for (Arco<Integer> arco : camino) {
			salida= salida+(arco.getVerticeOrigen())+"->";
		}
		salida=salida+"\n Peso del camino: "+this.peso;
		return salida;
	}
	
}
