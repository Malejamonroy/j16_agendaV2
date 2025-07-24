package com.cursogetafe.agenda.inico;

import com.cursogetafe.agenda.config.Config;
import com.cursogetafe.agenda.vista.MenuPrincipal;
import com.cursogetafe.agenda.vista.swing.VInicial;

public class Main {

	public static void main(String[] args) { //uno le puede pasar parameteos al main 
		
		String tipoVista = Config.getProp().getProperty("tipovista");
		System.out.println(tipoVista);
		
		if(tipoVista.equals("grafico"))
			new VInicial();
		else	
			new MenuPrincipal();
//		new VInicial();
		
//		//args = es un array 
//		
//		if(args.length !=1) {
//			System.err.println("debe ejecutar Main consola o Main grafico");
//		return;
//		}
//		if(args[0].equals("grafico"))
//			new VInicial();
//		else	
//			new MenuPrincipal();
////		new VInicial();
		

	}

}
