import java.util.ArrayList;
import java.util.HashMap;

public class Estado {
	private Genero generoInicial;
	private String generoActual;
	private int ciclos;
	private ArrayList<Arco<Integer>> caminoActual;
	private HashMap<String, Genero> generosVisitados;

	public Estado(Genero generoInicial) {
		this.generoInicial = generoInicial;
		this.generoActual = generoInicial.getGenero();
		this.caminoActual = new ArrayList<>();
		this.generosVisitados = new HashMap<>();
	}

	public void setCiclo() {
		this.ciclos++;
	}

	public int getCiclos() {
		return this.ciclos;
	}

	public String getGeneroActual() {
		return this.generoActual;
	}

	public void setGeneroActual(String generoActual) {
		this.generoActual = (generoActual);
	}

	public boolean isVisitado(String genero) {
		if (this.generosVisitados.get(genero) != null) {
			return true;
		}
		return false;
	}

	public void marcarVisitado(Genero genero) {
		this.generosVisitados.put(genero.getGenero(), genero);
	}

	public void quitarVisita(Genero genero) {
		this.generosVisitados.remove(genero.getGenero());
	}

	public void agregarAlCamino(Arco<Integer> arco) {

		this.caminoActual.add(arco);
		this.generoActual = arco.getVerticeDestino().getGenero();
	}

	public boolean contieneArcoEnCamino(Arco<Integer> arco) {
		return this.caminoActual.contains(arco);
	}

	public void quitarUltimo() {
		this.caminoActual.remove(this.caminoActual.size() - 1);
	}

	public ArrayList<Arco<Integer>> getCamino() {
		ArrayList<Arco<Integer>> salida = new ArrayList<>();
		for (Arco<Integer> arco : caminoActual) {
			salida.add(arco);
		}
		return salida;
	}

	public Genero getGeneroInicial() {
		return generoInicial;
	}

	public void setGeneroInicial(Genero generoInicial) {
		this.generoInicial = generoInicial;
	}

	public String getUltimoGenero() {
		return this.caminoActual.get(this.caminoActual.size()).getVerticeDestino().getGenero();
	}

	public int getSize() {
		return this.caminoActual.size();
	}

}
