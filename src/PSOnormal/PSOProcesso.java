package PSOnormal;
import java.util.Random;
import java.util.Vector;

public class PSOProcesso {
	
	static int TAMANHO_ENXAME = 30;
	static int MAX_ITERACAO = 100;
	static int DIMENSAO = 2;
	static double C1 = 2.0;
	static double C2 = 2.0;
	static double W_LIMITESUPERIOR = 1.0;
	static double W_LIMINTEINFERIOR = 0.0;
	
	private static Vector<Particula> exame = new Vector<Particula>();
	private static double[] pMelhor = new double[TAMANHO_ENXAME];
	private static Vector<Localizacao> pMelhorLocalizacao = new Vector<Localizacao>();
	private static double gMelhor;
	private static Localizacao gMelhorLocalizacao;
	private static double[] fitnessValorLista = new double[TAMANHO_ENXAME];
	
	static Random gerador = new Random();
	
	public void execute() {
		
		iniciarEnxame();
		atualizarListaFitness();

		for(int i=0; i<TAMANHO_ENXAME; i++) {
			pMelhor[i] = fitnessValorLista[i];
			pMelhorLocalizacao.add(exame.get(i).getLocation());
		}
		
		int t = 0;
		double w;
		double err = 9999;
		
		while(t < MAX_ITERACAO && err > SetProblema.ERR_TOLERANCE) {
			// step 1 - update pBest
			for(int i=0; i<TAMANHO_ENXAME; i++) {
				if(fitnessValorLista[i] < pMelhor[i]) {
					pMelhor[i] = fitnessValorLista[i];
					pMelhorLocalizacao.set(i, exame.get(i).getLocation());
				}
			}
				
			// step 2 - update gBest
			int bestParticleIndex = getMinPos(fitnessValorLista);
			if(t == 0 || fitnessValorLista[bestParticleIndex] < gMelhor) {
				gMelhor = fitnessValorLista[bestParticleIndex];
				gMelhorLocalizacao = exame.get(bestParticleIndex).getLocation();
			}
			
			w = W_LIMITESUPERIOR - (((double) t) / MAX_ITERACAO) * (W_LIMITESUPERIOR - W_LIMINTEINFERIOR);
			
			for(int i=0; i<TAMANHO_ENXAME; i++) {
				double r1 = gerador.nextDouble();
				double r2 = gerador.nextDouble();
				
				Particula p = exame.get(i);
				
				// step 3 - update velocity
				double[] newVel = new double[DIMENSAO];
				newVel[0] = (w * p.getVelocidade().getPos()[0]) + 
							(r1 * C1) * (pMelhorLocalizacao.get(i).getLoc()[0] - p.getLocation().getLoc()[0]) +
							(r2 * C2) * (gMelhorLocalizacao.getLoc()[0] - p.getLocation().getLoc()[0]);
				newVel[1] = (w * p.getVelocidade().getPos()[1]) + 
							(r1 * C1) * (pMelhorLocalizacao.get(i).getLoc()[1] - p.getLocation().getLoc()[1]) +
							(r2 * C2) * (gMelhorLocalizacao.getLoc()[1] - p.getLocation().getLoc()[1]);
				Velocidade vel = new Velocidade(newVel);
				p.setVelocidade(vel);
				
				// step 4 - update location
				double[] newLoc = new double[DIMENSAO];
				newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
				newLoc[1] = p.getLocation().getLoc()[1] + newVel[1];
				Localizacao loc = new Localizacao(newLoc);
				p.setLocalizacao(loc);
			}
			
			err = SetProblema.evaluate(gMelhorLocalizacao) - 0; // minimizing the functions means it's getting closer to 0
			
			
			System.out.println("ITERATION " + t + ": ");
			System.out.println("     Best X: " + gMelhorLocalizacao.getLoc()[0]);
			System.out.println("     Best Y: " + gMelhorLocalizacao.getLoc()[1]);
			System.out.println("     Value: " + SetProblema.evaluate(gMelhorLocalizacao));
			
			t++;
			atualizarListaFitness();
		}
		
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best X: " + gMelhorLocalizacao.getLoc()[0]);
		System.out.println("     Best Y: " + gMelhorLocalizacao.getLoc()[1]);
		
	}
	
	public static void iniciarEnxame() {
		Particula p;
		for(int i=0; i<TAMANHO_ENXAME; i++) {
			p = new Particula();
			
			// aleatorizar a localização dentro de um espaço definido no Conjunto de Problemas.
			double[] loc = new double[DIMENSAO];
			loc[0] = SetProblema.LOC_X_BAIXO + gerador.nextDouble() * (SetProblema.LOC_X_ALTO - SetProblema.LOC_X_BAIXO);
			loc[1] = SetProblema.LOC_Y_BAIXO + gerador.nextDouble() * (SetProblema.LOC_Y_ALTO - SetProblema.LOC_Y_BAIXO);
			Localizacao localizacao = new Localizacao(loc);
			
			// aleatorizar a velocidade no intervalo definido no Conjunto de Problemas
			double[] vel = new double[DIMENSAO];
			vel[0] = SetProblema.VEL_BAIXA + gerador.nextDouble() * (SetProblema.VEL_ALTA - SetProblema.VEL_BAIXA);
			vel[1] = SetProblema.VEL_BAIXA + gerador.nextDouble() * (SetProblema.VEL_ALTA - SetProblema.VEL_BAIXA);
			Velocidade velocidade = new Velocidade(vel);
			
			p.setLocalizacao(localizacao);
			p.setVelocidade(velocidade);
			exame.add(p);
		}
	}
	
	public static int getMinPos(double[] lista) {
		int pos = 0;
		double minValor = lista[0];
		
		for(int i=0; i<lista.length; i++) {
			if(lista[i] < minValor) {
				pos = i;
				minValor = lista[i];
			}
		}
		
		return pos;
	}
	
	public static void atualizarListaFitness() {
		for(int i=0; i<TAMANHO_ENXAME; i++) {
			fitnessValorLista[i] = exame.get(i).getValorFitness();
		}
	}
}