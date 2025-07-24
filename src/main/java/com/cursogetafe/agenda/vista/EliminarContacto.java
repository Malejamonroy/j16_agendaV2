package com.cursogetafe.agenda.vista;

import java.util.Scanner;

import com.cursogetafe.agenda.negocio.Agenda;

public class EliminarContacto {
	Agenda agenda;
	
	public EliminarContacto(Agenda agenda) {
		this.agenda = agenda;
		elimId();
		
	}

	private void elimId() {
		System.out.println("Introduce el Id contacto que va a Eliminar");
		Scanner teclado = new Scanner(System.in);
		int id = teclado.nextInt();
		
		agenda.eliminar(id);
		
		
	}
	

}
