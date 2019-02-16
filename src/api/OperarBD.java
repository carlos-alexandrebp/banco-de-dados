package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperarBD {
	
	    private final String separadoDeDiretorio = "/";
	    
	    private String localizacaoDoBD; 
	    private String nomeDoBD;
	    private final String extencaoDoBD = ".bag";
	    private String cabechalhoDoBD;
	    private String infoDoCabechalhoDoBD;
	    
	    private final char separadorDeVariaveis = ':';
	    private final char separadorDeColunas = '|';
	    
	    
	    File arq ;
	    File arqDir;

	    
	    
	    public OperarBD() {
	       this.nomeDoBD = pegarModificacaoDB("nomeDB");
	       this.localizacaoDoBD = pegarModificacaoDB("localizacao");
	    }
	    
	    
//crud BD
	    
	    public void criarDB (String nomeBD , Boolean BDformatado, List<String> nomeDaVariavel, List<String> tipos) 
	    {
	         arqDir = new File(this.localizacaoDoBD);
	         arq = new File(this.localizacaoDoBD+nomeBD+this.extencaoDoBD);
	        
	        if(!BDformatado){
	            if(!arq.exists()){
	                arqDir.mkdirs();
	                try{
	                   arq.createNewFile();
	                }catch(IOException erroIO){
	                    System.out.println("Erro ao criar o banco de dados >"+erroIO.getMessage());
	                }

	            }else{
	                System.err.println("Banco de dados existente");
	            }
	        }
//adicionar formatacao para o cabecalho do BD
	        if(BDformatado){
	            try{
	                if(!arq.exists()){
	                   arqDir.mkdirs();
	                   arq.createNewFile(); 
	                   BufferedWriter bw = new BufferedWriter(new FileWriter(arq)); 
	                   bw.write(PreFormatacao.cabecalhoBDFrm(nomeDaVariavel,tipos));
	                   bw.close();
	                }
	                else{
	                    System.err.println("Banco de dados existente");
	                }
	            }catch(IOException erroIO){
	                System.out.println("Erro 1232 >"+erroIO.getMessage());
	            }
	        }
	    }
	    
	    public void selectBD(String nomeBD){
	        arq = new File(this.localizacaoDoBD+nomeBD+this.extencaoDoBD);
	        if(arq.exists()){
	            this.nomeDoBD = nomeBD;
	            salvarModificacaoBD("nomeBD",nomeBD);
	        }
	        else{
	            System.err.println("Erro ao selecionar o BD ERRO cod>#2345");
	        }
	    }
	    
	    public void atualizarBD(){}
	    
	    public void excluirBD(String nomeBD,Boolean exclusaoCompleta){
	        File arq = new File(""+localizacaoDoBD+nomeBD+extencaoDoBD);
	        if(exclusaoCompleta){
	            if(arq.exists())
	            arq.delete();
	            else{
	            System.err.println("O BD nao existe!");
	            }
	        }
	        else{
	            if(arq.exists()){
	                arq.delete();
	                criarDB(nomeBD, false, null, null);
	                
	            }
	            else{
	                System.err.println("O BD nao existe!");
	            }
	        }
	    }
	    
	    
	    
	    
	    
	    public void incluirDadosAoDB(String salvarDados){
	
	         File arq = new File(localizacaoDoBD+nomeDoBD+extencaoDoBD);
	         
	         try{
	             BufferedWriter bw = new BufferedWriter(new FileWriter(arq,true));
	   
	             bw.append(PreFormatacao.incluirDadosFrm(getInfoDoCabechalhoDoBD(false,true), salvarDados));
	             bw.newLine();
	             bw.close();
	         }
	         catch(IOException erroIO){
	             System.out.println("Erro num : $9856 "+ erroIO.getMessage());
	         }
	    }
	    
	    public void atualizarDadosDB(){}
	    
	    public void selecionarDadosDB(){}
	    
	    public void excluirDadosBD(){}
	    
	    
	    
	    
	    
	    
	    
	    private static String pegarModificacaoDB(String procurarVar){
	        try(BufferedReader br = new BufferedReader(new FileReader("BDconfig.conf")))
	        {
	            String linha = br.readLine();
	            while( linha!= null ){
	                if(linha.contains(procurarVar))
	                {
	                    return linha.replace("="," ").split(" ")[1];
	                }
	                linha = br.readLine();
	            }
	        // tratar a execao fora do metodo usando o char se precisar
	            return "Nao achado";
	        
	        }catch(IOException erroIO){
	            System.err.println("Erro na Aplicacao!!! Erro grave > #0956212_1"+erroIO.getMessage());
	            
	        }
	            return "erro #123456";
	    }

	    private static void salvarModificacaoBD(String nomeDaVar,String valorDaVar){
	        File arq = new File("BDconfig.conf");
	        List <String> linhaALinha = new ArrayList<>();
	        
	        try(BufferedReader br = new BufferedReader(new FileReader(arq)))
	        {
	            String linha = br.readLine();
	            
	            while(linha!= null){
	                if(linha.contains(nomeDaVar))
	                {
	                     linhaALinha.add(linha.split("=")[0]+"="+valorDaVar);
	                     linha = br.readLine();
	                     
	                }
	                else{
	                    linhaALinha.add(linha);
	                    linha = br.readLine();
	                }
	            }
	        }catch(IOException erroIO){
	            System.err.println("Erro na Aplicacao!!! Erro grave > #09562_1"+erroIO.getMessage());
	        }
	            
	            for(String a:  linhaALinha){
	                       System.out.println(a);
	                }
	            
	        try{
	            try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(arq,true)); BufferedWriter bw2 = new BufferedWriter(new FileWriter(arq))) {
	                bw2.write("");
	                for(String a : linhaALinha){
	                	bw1.newLine();
	                    bw1.append(a);
	                    
	                }

	            }
	            linhaALinha.clear();
	        }catch(IOException erroIO){
	            System.err.println("Erro do tipo  > #46476_1 :"+erroIO.getMessage());
	        }
	    }


		
	    
	    
	    
	    public String getInfoDoCabechalhoDoBD(boolean nome,boolean tipo) {
	    	
	    	if(cabechalhoDoBD == null)
	    		cabechalhoDoBD = pegarModificacaoDB("cabechalhoDoBD");
	    	

	    	String texto="";
	    	List<String[]> VarMaisType = new ArrayList<>();    
			VarMaisType.add(cabechalhoDoBD.replace("|", " ").split(" "));
			
			if(nome && !tipo) {
				for(String[] a : VarMaisType) {
					for (int i = 0 ; i < a.length ;i++)
						if(i < a.length-1)
							texto += a[i].split(":")[0]+",";
						else {
							texto += a[i].split(":")[0];
						}
				}
				return texto;
			}
			if(!nome && tipo) {
				for(String[] a : VarMaisType) {
					for (int i = 0 ; i < a.length ;i++)
						if(i < a.length-1)
							texto += a[i].split(":")[1]+",";
						else {
							texto += a[i].split(":")[1];
						}
				return texto;
				}
			}
			if(nome && tipo) {
			
				for(int a = 0;a < VarMaisType.size();a++) {
					for(int i=0;i < VarMaisType.get(a).length;i++) {
						if(i < VarMaisType.get(a).length -1)
							texto += VarMaisType.get(a)[i]+",";
						else {
							texto += VarMaisType.get(a)[i];
						}
					}
				}
				return texto;
			}
			
			else {
					return cabechalhoDoBD;
				}
					
			}
}


