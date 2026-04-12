package co.edu.unbosque.controller;

import co.edu.unbosque.view.VistaConsola;
import co.edu.unbosque.view.IComandosVista;

public class ServicioControlador {
	
	private VistaConsola consoleView;
	
	private IComandosVista viewCmdListener = new IComandosVista() {
		
	};
	
	public ServicioControlador() {
		this.consoleView = new VistaConsola();
	}
	
	public void init() {
		consoleView.printMessage("--INIT VIEW--");
	}

}
