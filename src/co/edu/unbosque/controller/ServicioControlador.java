package co.edu.unbosque.controller;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.tecnico.TecnicoService;
import co.edu.unbosque.view.IComandosVista;
import co.edu.unbosque.view.VistaConsola;

public class ServicioControlador {
	
	private VistaConsola consoleView;
	private TecnicoService tecnicoService;
	
	private IComandosVista viewCmdListener = new IComandosVista() {
		
	};
	
	public ServicioControlador() {
		this.consoleView = new VistaConsola();
		this.tecnicoService = new TecnicoService();
	}
	
	public void init() {
		consoleView.printMessage("--INIT VIEW--");
	}
	
	// =========================  METODOS DE TECNICO =========================
	
	public boolean registrarTecnico(int id, String nombre, String especialidad, String estado, String zona) {
		if (id <= 0 || nombre == null || nombre.trim().isEmpty()
				|| especialidad == null || especialidad.trim().isEmpty()
				|| estado == null || estado.trim().isEmpty()
				|| zona == null || zona.trim().isEmpty()) {
			return false;
		}
		
		try {
			EstadoTecnico estadoTecnico = EstadoTecnico.valueOf(estado.toUpperCase());
			Tecnico tecnico = new Tecnico(id, nombre, especialidad, estadoTecnico, zona);
			return tecnicoService.registrarTecnico(tecnico);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public Tecnico buscarTecnico(int id) {
		if (id <= 0) {
			return null;
		}
		return tecnicoService.buscarTecnico(id);
	}
	
	public ListaEnlazada<Tecnico> listarTecnicos() {
		return tecnicoService.listarTecnicos();
	}
	
	public ListaEnlazada<Tecnico> buscarTecnicosPorZona(String zona) {
		if (zona == null || zona.trim().isEmpty()) {
			return null;
		}
		return tecnicoService.buscarPorZona(zona);
	}
	
	public ListaEnlazada<Tecnico> buscarTecnicosPorEspecialidad(String especialidad) {
		if (especialidad == null || especialidad.trim().isEmpty()) {
			return null;
		}
		return tecnicoService.buscarPorEspecialidad(especialidad);
	}
	
	public Tecnico buscarTecnicoDisponible(String zona) {
		if (zona == null || zona.trim().isEmpty()) {
			return null;
		}
		return tecnicoService.buscarDisponible(zona);
	}
	
	public boolean cambiarEstadoTecnico(int id, String estado) {
		if (id <= 0 || estado == null || estado.trim().isEmpty()) {
			return false;
		}
		return tecnicoService.cambiarEstado(id, estado);
	}
	
	public boolean actualizarTecnico(int id, String nombre, String especialidad, String estado, String zona) {
		if (id <= 0 || nombre == null || nombre.trim().isEmpty()
				|| especialidad == null || especialidad.trim().isEmpty()
				|| estado == null || estado.trim().isEmpty()
				|| zona == null || zona.trim().isEmpty()) {
			return false;
		}
		
		try {
			EstadoTecnico estadoTecnico = EstadoTecnico.valueOf(estado.toUpperCase());
			Tecnico tecnico = new Tecnico(id, nombre, especialidad, estadoTecnico, zona);
			return tecnicoService.actualizarTecnico(tecnico);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	// =========================  METODOS DE SOLICITUD =========================
	
	// =========================  METODOS DE REPORTES =========================
}