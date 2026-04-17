package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.tecnico.TecnicoService;
import co.edu.unbosque.view.IComandosVista;
import co.edu.unbosque.view.VistaConsola;
import co.edu.unbosque.view.*;
import co.edu.unbosque.utils.Constantes;
import co.edu.unbosque.model.base.TIPO_VEHICULO;
import co.edu.unbosque.model.base.ESTADO_UNIDAD;
import co.edu.unbosque.model.base.unidad.UnidadDTO;

public class ServicioControlador implements IComandosVista {

    private VistaPrincipal vista;
    private UnidadServicio unidadServicio;
    public ServicioControlador() {
        unidadServicio = new UnidadServicio();
        vista = new VistaPrincipal(this); // 👈 AQUÍ conectas todo
    }

    @Override
    public void ejecutarComando(String comando) {

        switch (comando) {

            case Constantes.BTN_ABRIR_DIALOGO_UNIDAD:
                vista.abrirDialogoUnidad();
                break;

            case Constantes.BTN_UNIDAD_REGISTRAR:

                TIPO_VEHICULO tipo = (TIPO_VEHICULO) vista.getDialogoUnidad().getTipo();
                ESTADO_UNIDAD estado = (ESTADO_UNIDAD) vista.getDialogoUnidad().getEstado();
                String zona = vista.getDialogoUnidad().getZona();

                unidadServicio.registrarUnidad(tipo, estado, zona);

                vista.getDialogoUnidad().mostrarMensaje("Unidad registrada");
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_BUSCAR:

                String zonaBuscar = vista.getDialogoUnidad().getZona();

                UnidadDTO u = unidadServicio.buscarDisponible(zonaBuscar);

                if (u != null) {
                    vista.getDialogoUnidad().mostrarMensaje(
                        "Encontrada: " + u.getTipo() + " - " + u.getEstado()
                    );
                } else {
                    vista.getDialogoUnidad().mostrarMensaje("No hay unidades disponibles");
                }
                break;

            case Constantes.BTN_UNIDAD_LIMPIAR:
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_CERRAR:
                vista.getDialogoUnidad().setVisible(false);
                break;
        }
    }
}
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