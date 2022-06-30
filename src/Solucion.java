import java.util.ArrayList;

public class Solucion {
	private ArrayList<Arco<Integer>> camino;

	public Solucion(ArrayList<Arco<Integer>> camino) {
		this.camino = new ArrayList<>(camino);
	}

	public Solucion() {
		this.camino = new ArrayList<>();
	}

	public int getSize() {
		return this.camino.size();
	}

	public String toString() {
		String salida = "";
		for (Arco<Integer> arco : camino) {
			salida = salida + (arco.getVerticeOrigen()) + "->";
		}
		salida = salida + "\n Tamaño del camino: " + this.getSize();
		return salida;
	}
}
