package PSOnormal;

public class PSOMainThread {
	static int i = 0;
	static int ta = 0, tb = 0, tc=0;
	
	public static void main(String[] args) throws InterruptedException {
		long inicio, fim;

		inicio = System.currentTimeMillis();

		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();

		while( ta != 1 || tb != 1 || tc !=1 ) Thread.sleep(1);

		fim = System.currentTimeMillis();

		System.out.println("Threads finalizadas");
		System.out.println("Tempo de execução = " + (fim - inicio) + " milisegundos");
	}
	
	private static Runnable t1 = new Runnable() {
		public void run() {
			System.out.println("------------------OUTRO---------------------");
			ta = 0;

			System.out.println("Thread 1 iniciada ...");

			new PSOProcesso().execute();

			ta = 1;
			//System.out.println("ta = " + ta);
		}
	};
	 
	private static Runnable t2 = new Runnable() {
		public void run() {
			System.out.println("------------------OUTRO---------------------");
			tb = 0;

			System.out.println("Thread 2 iniciada ...");

			new PSOProcesso().execute();

			tb = 1;
			//System.out.println("tb = " + tb);
		}
	};
		
	private static Runnable t3 = new Runnable() {
		public void run() {
			System.out.println("------------------OUTRO---------------------");
			tc = 0;

			System.out.println("Thread 3 iniciada ...");

			new PSOProcesso().execute();

			tc = 1;
			//System.out.println("tc = " + tc);
		}
	};
}