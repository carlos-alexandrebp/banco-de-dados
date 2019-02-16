package api;



import java.util.ArrayList;
import java.util.List;

import api.OperarBD;

public class Run {

	public static void main(String[] args) {
		OperarBD bd = new OperarBD();
		
		List<String> nome = new ArrayList<>();
		nome.add("nome");
		nome.add("idade");
		
		List<String> tipo = new ArrayList<>();
		tipo.add("String");
		tipo.add("String");
		
		//bd.criarDB("pessoa", true, nome, tipo);
		//bd.selectBD("pessoa");
		
		
		
		System.out.println(bd.getInfoDoCabechalhoDoBD(false,true));
		//System.out.print(PreFormatacao.incluirDadosFrm(bd.getInfoDoCabechalhoDoBD(false,true), "casa,,0"));
		
		bd.selectBD("pessoa");
		bd.incluirDadosAoDB("casa,,0");
		
	}

}
