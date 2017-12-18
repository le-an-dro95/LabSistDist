/*	Execução do Código:
 * 			1. Execute a classe Servidor;
 * 			2. Execute a classe Cliente;
 * 			3. informe os dados pedidos;
 * 				Ex: TAMANHO_ENXAME: 30;
    				MAX_ITERACAO: 100;
    				DIMENSAO: 2;
    				C1: 2.0;
    				C2: 2.0;
    				W_LIMITESUPERIOR: 1.0;
    				W_LIMITEINFERIOR: 0.0;
    				
    				Uma possível solução (varia por causa do Rand):
    				
	    				Solucao Encontrada:
	     					Melhor X: 3.0000000000073443
	     					Melhor Y: 0.5000000000211879
    				
    Por: Leandro Silva.
*/


package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException {
        
    	int TAMANHO_ENXAME; //= 30;
    	int MAX_ITERACAO; //= 100;
    	int DIMENSAO; //= 2;
    	double C1; // = 2.0;
    	double C2; // = 2.0;
    	double W_LIMITESUPERIOR; // = 1.0;
    	double W_LIMITEINFERIOR; // = 0.0;
        
    	Socket cliente = new Socket("127.0.0.1", 12342);
        System.out.println("O cliente conectou ao servidor");


        ObjectInputStream resultado = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream dados = new ObjectOutputStream(cliente.getOutputStream());


        TAMANHO_ENXAME = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do Enxame"));
        MAX_ITERACAO = Integer.parseInt(JOptionPane.showInputDialog("Maxima Iteração"));
        DIMENSAO = Integer.parseInt(JOptionPane.showInputDialog("Dimensao"));
        C1 = Double.parseDouble(JOptionPane.showInputDialog("C1"));
        C2 = Double.parseDouble(JOptionPane.showInputDialog("C2"));
        W_LIMITESUPERIOR = Double.parseDouble(JOptionPane.showInputDialog("W_LIMITESUPERIOR"));
        W_LIMITEINFERIOR = Double.parseDouble(JOptionPane.showInputDialog("W_LIMITEINFERIOR"));
        
        dados.writeInt(TAMANHO_ENXAME);
        dados.writeInt(MAX_ITERACAO);
        dados.writeInt(DIMENSAO);
        dados.writeDouble(C1);
        dados.writeDouble(C2);
        dados.writeDouble(W_LIMITESUPERIOR);
        dados.writeDouble(W_LIMITEINFERIOR);
        dados.flush();

        double melhorX = resultado.readDouble();
        double melhorY = resultado.readDouble();
       
        System.out.println("\nSolucao Encontrada:");
		System.out.println("     Melhor X: " + melhorX);
		System.out.println("     Melhor Y: " + melhorY);
		
        resultado.close();
        dados.close();
        cliente.close();
    }
}