package PSOnormal;

public class PSOMain {
	public static void main(String args[]) {
		long inicio, fim;
		
		inicio = System.currentTimeMillis();
		new PSOProcesso().execute();
		
		fim = System.currentTimeMillis();
		
		System.out.println("\n\nTempo de execução = " + (fim - inicio) + " milisegundos");
	}
}