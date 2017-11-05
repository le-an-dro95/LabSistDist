// Este é o problema a ser resolvido
// para encontrar um x e um y que minimizem a função abaixo:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// Onde 1 <= x <= 4, and -1 <= y <= 1

// você pode modificar a função depende das suas necessidades
// se o seu espaço de problemas for maior do que o espaço bidimensional
// você precisa introduzir uma nova variável (diferente de x e y)

public class SetProblema {
	public static final double LOC_X_BAIXO = 1;
	public static final double LOC_X_ALTO = 4;
	public static final double LOC_Y_BAIXO = -1;
	public static final double LOC_Y_ALTO = 1;
	public static final double VEL_BAIXA = -1;
	public static final double VEL_ALTA = 1;
	
	public static final double ERR_TOLERANCE = 1E-20; // Quanto menor a tolerância, mais preciso o resultado,
													  // mas o número de iterações é aumentado
	
	public static double evaluate(Localizacao localizacao) {
		double resultado = 0;
		double x = localizacao.getLoc()[0]; // a parte "x" da localização
		double y = localizacao.getLoc()[1]; // a parte "y" da localização
		
		resultado = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + 
				Math.pow(2.25 - x + x * Math.pow(y, 2), 2) + 
				Math.pow(1.5 - x + x * y, 2);
		
		return resultado;
	}
}