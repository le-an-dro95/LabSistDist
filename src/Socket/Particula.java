package Socket;

public class Particula {
	private double fitnessValor;
	private Velocidade velocidade;
	private Localizacao localizacao;
	
	public Particula() {
		super();
	}

	public Particula(double fitnessValor, Velocidade velocidade, Localizacao localizacao) {
		super();
		this.fitnessValor = fitnessValor;
		this.velocidade = velocidade;
		this.localizacao = localizacao;
	}

	public Velocidade getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Velocidade velocidade) {
		this.velocidade = velocidade;
	}

	public Localizacao getLocation() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao location) {
		this.localizacao = location;
	}

	public double getValorFitness() {
		fitnessValor = SetProblema.evaluate(localizacao);
		return fitnessValor;
	}
}