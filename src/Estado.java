import java.util.ArrayList;

public class Estado {
	private Genero generoInicial;
	private String generoActual;
	private int ciclos;
	private int peso;
	private ArrayList<Arco<Integer>> caminoActual;
	
	public Estado(Genero generoInicial, int peso) {
		this.generoInicial = generoInicial;
		this.generoActual=generoInicial.getGenero();
		this.peso = peso;
		this.caminoActual = new ArrayList<>();
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
		this.generoActual=(generoActual);
	}
	public void agregarAlCamino(Arco<Integer> arco) {
		
		this.caminoActual.add(arco);
		this.generoActual=arco.getVerticeDestino().getGenero();
	}
	public boolean contieneArcoEnCamino(Arco<Integer> arco) {
		return this.caminoActual.contains(arco);
	}
	public void quitarUltimo() {
		this.caminoActual.remove(this.caminoActual.size()-1);
	}
	public ArrayList<Arco<Integer>> getCamino(){
		ArrayList<Arco<Integer>>salida= new ArrayList<>();
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

	public int getPeso() {
		return peso;
	}

	public void incrementarPeso(int peso) {
		this.peso=this.peso+ peso;
	}
	public void decrementarPeso(int peso) {
		this.peso=this.peso- peso;
	}
	
	public String getUltimoGenero() {
		return this.caminoActual.get(this.caminoActual.size()).getVerticeDestino().getGenero();
	}
	
	
}
