
public class PSOMain {
	public static void main(String args[]) {
		long inicio, fim;
		
		inicio = System.currentTimeMillis();
		new PSOProcesso().execute();
		System.out.println("------------------OUTRO---------------------");
		new PSOProcesso2().execute();
		System.out.println("------------------OUTRO---------------------");
		new PSOProcesso3().execute();
		
		fim = System.currentTimeMillis();
		
		System.out.println("\n\nTempo de execução = " + (fim - inicio) + " milisegundos");
	}
}