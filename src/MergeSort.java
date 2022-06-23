import java.util.Iterator;

public class MergeSort {

	private int [] arrPesos;
	private Genero[] arrDestinos;
	private int [] auxiliar;
	private Genero[]auxDestinos;
	private int size;
	
	public void sort(int[] arrPesos,Genero[] arrDestinos) {
		this.arrPesos = arrPesos;
		this.arrDestinos= arrDestinos;
		this.size= arrPesos.length;
		this.auxiliar= new int[size];
		this.auxDestinos= new Genero[size];
		mergesort(0,size-1);

		
	}
	public void mergesort(int low,int high) {
		if(low<high) {
			int middle = (low+high)/2;
			mergesort(low,middle);
			mergesort(middle+1, high);
			merge(low,middle,high);
		}
	}
	public void merge(int low, int middle, int high) {
		for(int i =low; i<=high; i++) {
			auxiliar[i]=arrPesos[i];
			auxDestinos[i]=arrDestinos[i];
		}
		int i = low;
		int j=middle+1;
		int k=low;
		
		while(i<=middle && j<=high) {
			if(auxiliar[i]>= auxiliar[j]) {
				arrPesos[k]=auxiliar[i];
				arrDestinos[k]=auxDestinos[i];
				i++;
			}else {
				arrPesos[k]= auxiliar[j];
				arrDestinos[k]=auxDestinos[j];
				j++;
			}
			k++;
		}
		while(i<=middle) {
			arrPesos[k]= auxiliar[i];
			arrDestinos[k]=auxDestinos[i];
			k++;
			i++;
		}
		while(j<=high) {
			arrPesos[k]= auxiliar[j];
			arrDestinos[k]=auxDestinos[j];
			k++;
			j++;
		}
	}
}
