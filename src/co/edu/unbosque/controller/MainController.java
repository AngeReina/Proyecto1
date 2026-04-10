package co.edu.unbosque.controller;

import co.edu.unbosque.view.ConsoleView;

public class MainController {
	
	private ConsoleView consoleView;
	
	public MainController() {
		this.consoleView = new ConsoleView();
	}
	
	public void init() {
		consoleView.printMessage("--INIT VIEW--");
	}

}
