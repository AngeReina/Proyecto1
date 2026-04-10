package co.edu.unbosque.view;

public class VistaPrincipal {
	
	private IComandosVista cmdListener;
	
	public VistaPrincipal(IComandosVista viewCmdListener) {
		this.cmdListener = viewCmdListener;
	}

}
