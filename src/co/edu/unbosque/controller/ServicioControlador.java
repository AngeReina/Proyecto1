package co.edu.unbosque.controller;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.tecnico.TecnicoService;
import co.edu.unbosque.view.IComandosVista;
import co.edu.unbosque.view.VistaConsola;
import co.edu.unbosque.view.VistaPrincipal;
import co.edu.unbosque.utils.Constantes;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.unidad.UnidadDTO;
import co.edu.unbosque.model.unidad.UnidadServicio;


public class ServicioControlador {

    private VistaPrincipal vista;
    private UnidadServicio unidadServicio;
    private VistaConsola consoleView;
	private TecnicoService tecnicoService;
	private UnidadDTO ultimaUnidad;

	private IComandosVista viewCmdListener = new IComandosVista() {

        @Override
        public void ejecutarComando(String comando) {

            switch (comando) {

                case Constantes.BTN_ABRIR_DIALOGO_UNIDAD:
                    vista.abrirDialogoUnidad();
                    break;

                case Constantes.BTN_UNIDAD_REGISTRAR:

                    if (vista.getDialogoUnidad().datosValidos()) {

						TIPO_VEHICULO tipo = vista.getDialogoUnidad().getTipo();
						ESTADO_UNIDAD estado = vista.getDialogoUnidad().getEstado();
						String zona = vista.getDialogoUnidad().getZona();

						unidadServicio.registrarUnidad(tipo, estado, zona);

						vista.getDialogoUnidad().mostrarMensaje("Unidad registrada correctamente");
						vista.getDialogoUnidad().limpiarCampos();

					} else {
						vista.getDialogoUnidad().mostrarMensaje("Datos inválidos");
					}
					break;

                case Constantes.BTN_UNIDAD_BUSCAR:

					String zonaBuscar = vista.getDialogoUnidad().pedirZonaBusqueda();

					if (zonaBuscar == null || zonaBuscar.trim().isEmpty()) {
						vista.getDialogoUnidad().mostrarMensaje("Zona inválida");
						break;
					}

					zonaBuscar = zonaBuscar.trim().toUpperCase();

					ListaEnlazada<UnidadDTO> lista = unidadServicio.buscarDisponibles(zonaBuscar);

					if (lista != null && lista.count() > 0) {

						String mensaje = "Unidades encontradas:\n";

						for (int i = 0; i < lista.count(); i++) {

							UnidadDTO u = lista.getValueByPos(i);

							mensaje += "\nID: " + u.getId() +
									"\nTipo: " + u.getTipo() +
									" | Estado: " + u.getEstado() +
									" | Zona: " + u.getZona() + "\n";
						}

						vista.getDialogoUnidad().mostrarMensaje(mensaje);

					} else {
						vista.getDialogoUnidad().mostrarMensaje("No hay unidades en esa zona");
					}

				break;

				case Constantes.BTN_UNIDAD_CAMBIAR_ESTADO:

					Object[] datos = vista.getDialogoUnidad().pedirIdYEstado();

					if (datos == null) {
						break;
					}

					String idTexto = (String) datos[0];
					ESTADO_UNIDAD nuevoEstado = (ESTADO_UNIDAD) datos[1];

					if (idTexto.isEmpty()) {
						vista.getDialogoUnidad().mostrarMensaje("ID inválido");
						break;
					}

					try {

						java.util.UUID id = java.util.UUID.fromString(idTexto);

						boolean resultado = unidadServicio.cambiarEstado(id, nuevoEstado);

						if (resultado) {
							vista.getDialogoUnidad().mostrarMensaje("Estado actualizado correctamente");
						} else {
							vista.getDialogoUnidad().mostrarMensaje("No hubo cambios o no se encontró la unidad");
						}

					} catch (Exception e) {
						vista.getDialogoUnidad().mostrarMensaje("Formato de ID inválido");
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
	};

	public ServicioControlador() {
        unidadServicio = new UnidadServicio();
        vista = new VistaPrincipal(viewCmdListener);
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