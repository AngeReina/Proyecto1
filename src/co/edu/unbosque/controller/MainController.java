package co.edu.unbosque.controller;

import co.edu.unbosque.view.ConsoleView;
import co.edu.unbosque.view.IViewCmdListener;

public class MainController {
	
	private ConsoleView consoleView;
	
	private IViewCmdListener viewCmdListener = new IViewCmdListener() {
		
	};
	
	public MainController() {
		this.consoleView = new ConsoleView();
	}
	
	public void init() {
		consoleView.printMessage("--INIT VIEW--");
	}

}
