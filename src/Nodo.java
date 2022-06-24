
public class Nodo {
	private Genero genero;
	private Nodo hijoIzq, hijoDer;
	private int profundidad;

	public Nodo(Genero genero) {
		this.genero = genero;
		this.hijoIzq = null;
		this.hijoDer = null;
		this.profundidad = 0;
	}

	public int getProfundidad() {
		return this.profundidad;
	}

	public void setProfundidad(int prof) {
		this.profundidad = prof;
	}

	public Genero getGenero() {
		return genero;
	}

	public Nodo getHijoIzq() {
		return this.hijoIzq;
	}

	public void setHijoIzq(Nodo hijoIzq) {
		this.hijoIzq = hijoIzq;
	}

	public Nodo getHijoDer() {
		return this.hijoDer;
	}

	public void setHijoDer(Nodo hijoDer) {
		this.hijoDer = hijoDer;
	}

	public boolean existeGenero(Genero genero) {
		int val = genero.getGenero().compareTo(this.genero.getGenero());

		if (val == 0) {
			return true;
		} else if (val < 0) {
			if (this.hijoIzq != null)
				this.hijoIzq.existeGenero(genero);
		} else if (val > 0) {
			if (this.hijoDer != null)
				this.hijoDer.existeGenero(genero);
		}
		return false;
	}

	public boolean existeGenero(String genero) {
		int val = genero.compareTo(this.genero.getGenero());

		if (val == 0) {
			return true;
		} else if (val < 0) {
			if (this.hijoIzq != null) {
				return this.hijoIzq.existeGenero(genero);
			}
		} else if (val > 0) {
			if (this.hijoDer != null) {
				return this.hijoDer.existeGenero(genero);
			}
		}

		return false;
	}

	public boolean esHoja() {
		return this.getHijoDer() == null && this.getHijoIzq() == null;
	}

	public Nodo getMenorDeMayores(Nodo nodo) {
		if (this.esHoja() || this.getHijoIzq() == null) {
			return this;
		} else {
			this.getMenorDeMayores(this.getHijoIzq());
		}
		return null;
	}

	public String toString() {
		String salida = this.genero.getGenero() + " " + this.getGenero().sizeLibros() + "\n ";
		if (this.hijoDer != null) {
			salida = salida + this.hijoDer.toString();
		}
		if (this.hijoIzq != null) {
			salida = salida + this.hijoIzq.toString();
		}
		return salida;
	}

	/*
	 * public void deleteNodo(Nodo nodo) { if (this.genero.getGenero() ==
	 * nodo.getGenero().getGenero()) { if (this.esHoja()) { // borrar vinculo con
	 * padre } else {
	 * 
	 * } } else if (genero.getGenero() < this.genero.getGenero()) { if (this.hijoIzq
	 * != null) this.hijoIzq.deleteNodo(nodo); } else if (genero.getGenero() >
	 * this.genero.getGenero()) { if (this.hijoDer != null)
	 * this.hijoDer.deleteNodo(nodo); } else return;// caso que no lo encuentre
	 * retorno sin hacer nada // si pregunto desde afuera si existe primero,en el
	 * caso de que exista estaria // recorriendo 2 veces el arbol // de esta manera
	 * en el peor de los caso que seria que no lo encuentre no hago // nada
	 * 
	 * } FIXME NO TAN NECESARIO CONTINUAR BORRADO
	 */
	private void addNodo(Nodo nodo) {
		int val = nodo.getGenero().getGenero().compareTo(this.genero.getGenero());// ver de cambiar getGenero de nodo
		// para q retorne solo un string
		if (val < 0) {

			if (this.hijoIzq != null) {
				this.hijoIzq.addNodo(nodo);
				this.setProfundidad(this.getHijoIzq().getProfundidad()+1);
			} else {
				this.hijoIzq = nodo;
			}
		} else if (val > 0) {

			if (this.hijoDer != null) {
				this.hijoDer.addNodo(nodo);
				this.setProfundidad(this.getHijoDer().getProfundidad()+1);

			} else {
				this.hijoDer = nodo;
			}
		}
	}//profundidad de mas


	public void addGenero(Genero genero) {
		int val = genero.getGenero().compareTo(this.genero.getGenero());// ver de cambiar getGenero de nodo
																		// para q retorne solo un string
		if (val < 0) {
			if (this.hijoIzq != null) {
				this.hijoIzq.addGenero(genero);
			} else {
				Nodo nodo = new Nodo(genero);
				this.hijoIzq = nodo;
			}
		} else if (val > 0) {
			if (this.hijoDer != null) {
				this.hijoDer.addGenero(genero);
			} else {
				Nodo nodo = new Nodo(genero);
				this.hijoDer = nodo;
			}
		}
	}

	public Nodo getNodo(Genero genero) {
		int val = genero.getGenero().compareTo(this.genero.getGenero());// ver de cambiar getGenero de nodo
		// para q retorne solo un string
		if (val == 0) {
			return this;
		} else if (val < 0) {
			if (this.hijoIzq != null) {
				return this.hijoIzq.getNodo(genero);
			}
		} else if (val > 0) {
			if (this.hijoDer != null) {
				return this.hijoDer.getNodo(genero);
			}
		}
		return null;

	}

	public Nodo getNodo(String genero) {

		int val = genero.compareTo(this.genero.getGenero());// ver de cambiar getGenero de nodo
		// para q retorne solo un string
		if (val == 0) {
			return this;
		} else if (val < 0) {
			if (this.hijoIzq != null) {
				return this.hijoIzq.getNodo(genero);
			}
		} else if (val > 0) {
			if (this.hijoDer != null) {
				return this.hijoDer.getNodo(genero);
			}
		}
		return null;

	}
}
