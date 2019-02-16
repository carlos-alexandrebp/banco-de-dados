package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreFormatacao {

	//Para o cabechalho do banco de dados
    private final static char separadorDeVar = '|';
    private final static char separadorDeCol= ':';
   
    
    

    public  PreFormatacao() {
        
    }
    
    
    
    public static void novoBDFrm(){}
    
    public static String cabecalhoBDFrm(List<String>nomeDaVariavel , List<String>Tipos)
    {
          String cabecalho = "";
          System.out.println(nomeDaVariavel.size() == Tipos.size());
            if(nomeDaVariavel.size() == Tipos.size()){
            	
            	for(int i = 0; i < (nomeDaVariavel.size() - 1); i++) { 
                    cabecalho += nomeDaVariavel.get(i)+separadorDeCol+Tipos.get(i)+separadorDeVar;
                }
                cabecalho += nomeDaVariavel.get(nomeDaVariavel.size()-1)+separadorDeCol+Tipos.get(Tipos.size()-1);
            }
            else{
                System.out.println("Erro indetifique todos os tipos das variaveis");
            }
        return cabecalho;
    }
 
    public static  String incluirDadosFrm(String TipoDasVarDoCabechalhoDoBD , String dadosAFomatar)
    {
    	String texto ="";
    	
    	// Aqui pode ser a verificacao e confirmacao das VARIAVEIS
    	//emergente
    	
    	String[] tipos = TipoDasVarDoCabechalhoDoBD.split(",");
    	String[] dados = dadosAFomatar.split(",");
    	
    	
    	
    	if(VerificarTiposDasVarBD(TipoDasVarDoCabechalhoDoBD)&& tipos.length == dados.length) {
	    	for(int i =0; i < tipos.length ;i++) {
	    	//verificacao de dados	
	    		
	    		switch (tipos[i]) {
	    			case "String":	
	    				if(i == tipos.length -1)
	    					texto += dados[i];
	    				else
	    					texto += dados[i]+",";
	    				break;
	    			case "Int":
	    				if(i == tipos.length -1)
	    					texto += dados[i];
	    				else
	    					texto += dados[i]+",";
	    				break;
	    			case"Float":
	    				if(i == tipos.length -1)
	    					texto += dados[i];
	    				else
	    					texto += dados[i]+",";
	    				break;
	    			case "Char":
	    				if(i == tipos.length -1)
	    					texto += dados[i];
	    				else
	    					texto += dados[i]+",";
	    				break;
	    			default:
	    				System.err.println("Tipo de erro desconhecido pelo programador #67393");
	    				break;
				}
	    	}
	    	
	    }
    	else {
    		texto = "null";
    		System.err.println("Tipo de var nao compativel ou erro no numero de var a escrever");
    	}
    	
    	return texto;
    }
    public static Boolean VerificarTiposDasVarBD(String TipoDasVarDoCabechalhoDoBD) {
    	
    	String[] deTipos = TipoDasVarDoCabechalhoDoBD.split(",");
    	String varAceitaveis = "String,Int,Float,Char";
    	for(int i = 0;i < deTipos.length;i++) {
    		if(!varAceitaveis.contains(deTipos[i])) {
    			System.err.println("Erro em uma variavel do tipo "+ deTipos[i]);
    			return false;
    		}
    		
    		
    	}
    	
    	return true;
    }
    
}

