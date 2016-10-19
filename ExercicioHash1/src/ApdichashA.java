import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// IMPLEMENTE OS MÉTODOS hashCode e chaveIgualDE ACORDO COM AS NECESSIDADES DA APLICAÇÃO.
// O CÁLCULO DE HASHING A SER IMPLEMENTADO É O POLINOMIAL COM BASE 33.
// A CHAVE SERÁ DO TIPO STRING, O CEP PROCESSADO NA APLICAÇÃO.

abstract class HashEngine {
    abstract boolean chaveIgual(Object kA, Object kB);
    abstract int hashCode(Object chave);
}

class MeuHashEngine extends HashEngine {
	public boolean chaveIgual(Object kA, Object kB){
		return (String)kA==(String)kB;
	}
	
	public int hashCode(Object chave){
        long wordInt = 0;
        int contador = 1;
        String nome = String.valueOf(chave);
        for (int i = 0; i < nome.length(); i++){
            wordInt += (long) nome.charAt(i)*Math.pow(33, nome.length()-contador++);
        }
        if(wordInt<0)wordInt*=-1;
        return (int)wordInt;
	}	
} // fim classe MeuHashEngine

class RegDados {
	private String cep, rua, bairro, cidade;
	
	public RegDados(String strparam){
	    String[] values = strparam.split(";");
        cep = values[0].trim();
        rua = values[1];
        bairro = values[2];
        cidade = values[3];
	}

	public String toString(){
		return cep + " "+rua+" "+bairro+" "+cidade;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
        return rua;
	}

	public String getBairro() {
        return bairro;
	}

	public String getCidade() {
		return cidade;
	}
} // fim classe RegDados

public class ApdichashA {
	private static TADDicEA fromBaseDadosParaDic(String nomeArq, int tamArq){
		// IMPLEMENTE O CORPO DESTE MÉTODO:

        TADDicEA dic = new TADDicEA(tamArq, new MeuHashEngine());
        RegDados regDados;
        try {
            //  ESTE MÉTODO DEVE CARREGAR AS LINHAS DO ARQUIVO vix-ruas-ceps.txt UMA A UMA.
            FileReader file = new FileReader(nomeArq);
            BufferedReader file2Read = new BufferedReader(file);
            String line = file2Read.readLine();

            while(line != null){
                regDados= new RegDados(line);//  A CADA LEITURA DO ARQUIVO, A LINHA DEVE SER CONVERTIDA EM UM OBJETO DO TIPO RegDados.
                boolean insertElem = dic.insereElem(regDados.getCep(), regDados);//  O OBJETO RegDados É ENTÃO INCLUÍDO NO OBJETO DICIONÁRIO TADDicEA (OU TADDicCHAIN).
                if(!insertElem) System.out.println("Elemento "+regDados.getCep()+" nao inserido com sucesso");
                line = file2Read.readLine();
            }

            file2Read.close();
        }

        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        return dic;//  QUANDO CONCLUÍDO O PROCESSO, O MÉTODO DEVE RETORNAR UM TADDicEA (OU TADDicCHAIN) COM
        //  TODOS OS DADOS DA BASE DE DADOS DEVIDAMENTE INSERIDOS.
	} // fim fromBaseDadosParaDic
    public static void main(String[] args) {
		TADDicEA dicCEP = fromBaseDadosParaDic("vix-ruas-ceps.txt",250);
        RegDados regDados;
        Scanner teclado = new Scanner(System.in);
		String strcep;
		// Fazendo uma consulta a tabela de hashing..
		System.out.print("Entre com o CEP do logradouro, <ENTER> para encerrar: ");
		strcep = teclado.nextLine();  
		
		while(!strcep.equals("")){
            regDados = (RegDados)dicCEP.getElem(strcep);
			if(regDados != null)
				System.out.println(">> CEP encontrado: " + regDados.toString() + '\n');
			else
				System.out.println(">> CEP inexistente!\n");			
			
			System.out.print("Entre com o CEP do logradouro, <ENTER> para encerrar: ");
			strcep = teclado.nextLine();      
		} // while(!strcep...
		
		System.out.print("Aplicação encerrada!");
		teclado.close();
	} // main
} // fim classe ApdichashA

