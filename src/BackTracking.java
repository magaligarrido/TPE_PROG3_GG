import java.util.ArrayList;

public class BackTracking {
	private Solucion mejorSolucion;

	public Solucion back(Genero inicial, GrafoDirigido<Genero> grafo) {
		this.mejorSolucion = null;		
		Estado estado = new Estado(inicial);
		
		estado.marcarVisitado(inicial);		
		this.back(grafo, estado);
		
		return this.mejorSolucion;
	}

	private void back(GrafoDirigido<Genero> grafo, Estado estado) {

		if (this.mejorSolucion == null || this.mejorSolucion.getSize() < grafo.cantidadVertices() - 1) {

			if (!grafo.getGenero(estado.getGeneroActual()).tieneAdyacentes()
					|| estado.getCamino().size() == grafo.cantidadVertices() - 1) {

				this.mejorSolucion = new Solucion(estado.getCamino());

			} else {
				ArrayList<Arco<Integer>> adyacentes = grafo.obtenerAdyacentes(estado.getGeneroActual());

				for (Arco<Integer> arco : adyacentes) {
					if (!estado.isVisitado(arco.getVerticeDestino().getGenero())
							&& arco.getVerticeDestino() != estado.getGeneroInicial()) {
						estado.agregarAlCamino(arco);
						estado.marcarVisitado(arco.getVerticeDestino());
						estado.setGeneroActual(arco.getVerticeDestino().getGenero());

						back(grafo, estado);

						estado.quitarUltimo();
						estado.quitarVisita(arco.getVerticeDestino());
						estado.setGeneroActual(arco.getVerticeOrigen().getGenero());
					}
				}
			}
		}
	}
}
