import java.util.ArrayList;

public class BackTracking {
	private Solucion mejorSolucion;
	
	
	public Solucion back(Genero inicial, GrafoDirigido<Genero> grafo) {
		this.mejorSolucion= null;
		int peso = 0;
		Estado estado= new Estado(inicial,peso);
		this.back(grafo, estado);
		System.out.println(estado.getCiclos());
		return this.mejorSolucion;
		
	}
	
	private void back(GrafoDirigido<Genero> grafo,Estado estado) {
		if(!grafo.getGenero(estado.getGeneroActual()).tieneAdyacentes()) {
			System.out.println(estado.getPeso());
			
			if(this.mejorSolucion==null||estado.getPeso()>this.mejorSolucion.getPeso()) {
				this.mejorSolucion= new Solucion(estado.getCamino(),estado.getPeso());
				System.out.println(this.mejorSolucion);
				
		
				
			}		
		
			//if(mejorsolucin.peso>estadocaminopeso
		}else {
			ArrayList<Arco<Integer>> adyacentes=grafo.obtenerAdyacentes(estado.getGeneroActual());
			for (Arco<Integer> arco : adyacentes) {
				
				if(!estado.contieneArcoEnCamino(arco)&& arco.getVerticeOrigen()!=estado.getGeneroInicial().getGenero() && estado.getCiclos() < 10000000) {//PODA IMPLICITA
					estado.setCiclo();
					estado.incrementarPeso(arco.getContador());
					estado.agregarAlCamino(arco);	
					estado.setGeneroActual(arco.getVerticeDestino());
		
					back(grafo,estado);
					
					estado.decrementarPeso(arco.getContador());
					estado.quitarUltimo();	
					estado.setGeneroActual(arco.getVerticeOrigen());
					
				}
			}
		}
	
		
	}
	

}
