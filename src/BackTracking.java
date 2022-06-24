import java.util.ArrayList;

public class BackTracking {
	private Solucion mejorSolucion;

	public Solucion back(Genero inicial, GrafoDirigido<Genero> grafo) {
		this.mejorSolucion = null;
		Estado estado = new Estado(inicial);
		this.back(grafo, estado);
		return this.mejorSolucion;
	}

	private void back(GrafoDirigido<Genero> grafo, Estado estado) {
	
		if (!grafo.getGenero(estado.getGeneroActual()).tieneAdyacentes()) {
			if (this.mejorSolucion == null || estado.getSize() > this.mejorSolucion.getSize()) {
				this.mejorSolucion = new Solucion(estado.getCamino());
				System.out.println(this.mejorSolucion);
			}

		} else {
			ArrayList<Arco<Integer>> adyacentes = grafo.obtenerAdyacentes(estado.getGeneroActual());
		
			for (Arco<Integer> arco : adyacentes) {				
				if (!estado.contieneArcoEnCamino(arco)&& arco.getVerticeDestino()!=estado.getGeneroInicial()) {
					estado.agregarAlCamino(arco);
					//estado.marcarVisitado(arco.getVerticeDestino());
					estado.setGeneroActual(arco.getVerticeDestino().getGenero());

					back(grafo, estado);

					estado.quitarUltimo();
					//estado.quitarVisita(arco.getVerticeDestino());
					estado.setGeneroActual(arco.getVerticeOrigen().getGenero());

				}
			}
		}

	}

}
