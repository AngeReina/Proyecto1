package co.edu.unbosque.controller;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.cliente.ClienteDTO;
import co.edu.unbosque.model.cliente.ClienteService;
import co.edu.unbosque.model.tecnico.TecnicoDTO;
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
	private ClienteService clienteService;

	private IComandosVista viewCmdListener = new IComandosVista() {

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
                    
                case Constantes.BTN_ABRIR_DIALOGO_TECNICO:
                    vista.abrirDialogoTecnico();
                    break;
                case Constantes.BTN_TECNICO_REGISTRAR:

                    TecnicoDTO dto = new TecnicoDTO(
                        vista.getDialogoTecnico().getId(),
                        vista.getDialogoTecnico().getNombre(),
                        vista.getDialogoTecnico().getEspecialidad(),
                        vista.getDialogoTecnico().getEstado(),
                        vista.getDialogoTecnico().getZona()
                    );

                    boolean registrado = registrarTecnico(dto);

                    if (registrado) {
                        vista.getDialogoTecnico().mostrarMensaje("Técnico registrado");
                        vista.getDialogoTecnico().limpiarCampos();
                    } else {
                        vista.getDialogoTecnico().mostrarMensaje("Error al registrar");
                    }
                    break;
                case Constantes.BTN_TECNICO_BUSCAR: {

                    int id = vista.getDialogoTecnico().getId();
                    String zonaTecnico = vista.getDialogoTecnico().getZona();
                    String especialidad = vista.getDialogoTecnico().getEspecialidad();

                    if (id > 0) {
                        TecnicoDTO t = buscarTecnico(id);

                        if (t != null) {
                            String mensaje = "Técnico encontrado:\n" +
                                    "ID: " + t.getId() + "\n" +
                                    "Nombre: " + t.getNombre() + "\n" +
                                    "Especialidad: " + t.getEspecialidad() + "\n" +
                                    "Estado: " + t.getEstado() + "\n" +
                                    "Zona: " + t.getZona();

                            vista.getDialogoTecnico().mostrarMensaje(mensaje);
                        } else {
                            vista.getDialogoTecnico().mostrarMensaje("No encontrado");
                        }

                    } else if (zonaTecnico != null && !zonaTecnico.trim().isEmpty()) {

                        ListaEnlazada<TecnicoDTO> lista = buscarTecnicosPorZona(zonaTecnico);

                        if (lista != null && lista.count() > 0) {

                            String mensaje = "Técnicos en la zona:\n";

                            for (int i = 0; i < lista.count(); i++) {
                                TecnicoDTO t = lista.getValueByPos(i);

                                mensaje += "\nID: " + t.getId() +
                                           " | " + t.getNombre() +
                                           " | " + t.getEspecialidad() +
                                           " | " + t.getEstado();
                            }

                            vista.getDialogoTecnico().mostrarMensaje(mensaje);

                        } else {
                            vista.getDialogoTecnico().mostrarMensaje("No hay técnicos en esa zona");
                        }

                    } else if (especialidad != null && !especialidad.trim().isEmpty()) {

                        ListaEnlazada<TecnicoDTO> lista = buscarTecnicosPorEspecialidad(especialidad);

                        if (lista != null && lista.count() > 0) {

                            String mensaje = "Técnicos con esa especialidad:\n";

                            for (int i = 0; i < lista.count(); i++) {
                                TecnicoDTO t = lista.getValueByPos(i);

                                mensaje += "\nID: " + t.getId() +
                                           " | " + t.getNombre() +
                                           " | " + t.getZona() +
                                           " | " + t.getEstado();
                            }

                            vista.getDialogoTecnico().mostrarMensaje(mensaje);

                        } else {
                            vista.getDialogoTecnico().mostrarMensaje("No hay técnicos con esa especialidad");
                        }

                    } else if (zonaTecnico != null && !zonaTecnico.trim().isEmpty()) {

                        TecnicoDTO t = buscarTecnicoDisponible(zonaTecnico);

                        if (t != null) {
                            String mensaje = "Técnico disponible:\n" +
                                    "ID: " + t.getId() + "\n" +
                                    "Nombre: " + t.getNombre() + "\n" +
                                    "Especialidad: " + t.getEspecialidad();

                            vista.getDialogoTecnico().mostrarMensaje(mensaje);
                        } else {
                            vista.getDialogoTecnico().mostrarMensaje("No hay técnicos disponibles");
                        }

                    } else {
                        vista.getDialogoTecnico().mostrarMensaje("Ingrese un criterio de búsqueda");
                    }

                    break;
                }
                case Constantes.BTN_TECNICO_LIMPIAR:
                    vista.getDialogoTecnico().limpiarCampos();
                    break;

                case Constantes.BTN_TECNICO_CERRAR:
                    vista.getDialogoTecnico().setVisible(false);
                    break;
                    

                case Constantes.BTN_ABRIR_DIALOGO_CLIENTE: {
                    vista.abrirDialogoCliente();
                    break;
                }
                    
                case Constantes.BTN_CLIENTE_REGISTRAR: {
                    ClienteDTO clienteDto = new ClienteDTO(
                            vista.getDialogoCliente().getId(),
                            vista.getDialogoCliente().getNombre(),
                            vista.getDialogoCliente().getTelefono(),
                            vista.getDialogoCliente().getTipo()
                    );

                    boolean clienteRegistrado = registrarCliente(clienteDto);

                    if (clienteRegistrado) {
                        vista.getDialogoTecnico().mostrarMensaje("Cliente registrado");
                        vista.getDialogoTecnico().limpiarCampos();
                    } else {
                        vista.getDialogoTecnico().mostrarMensaje("Error al registrar");
                    }
                    break;
                }
                case Constantes.BTN_CLIENTE_BUSCAR: {
                    int id = vista.getDialogoCliente().getId();
                    ClienteDTO t = buscarCliente(id);

                    if (t != null) {
                        String mensaje = "Cliente encontrado:\n" +
                                "ID: " + t.getId() + "\n" +
                                "Nombre: " + t.getNombre() + "\n" +
                                "Telefono: " + t.getTelefono() + "\n" +
                                "Tipo: " + t.getTipo();

                        vista.getDialogoCliente().mostrarMensaje(mensaje);
                    } else {
                        vista.getDialogoCliente().mostrarMensaje("No encontrado");
                    }
                    break;
                }
                case Constantes.BTN_CLIENTE_LIMPIAR: {
                	vista.getDialogoCliente().limpiarCampos();
                    break;
                }
                case Constantes.BTN_CLIENTE_CERRAR:
                    vista.getDialogoCliente().setVisible(false);
                    break;
            }
        }
	};

	public ServicioControlador() {
        unidadServicio = new UnidadServicio();
        vista = new VistaPrincipal(viewCmdListener);
        this.consoleView = new VistaConsola();
		this.tecnicoService = new TecnicoService();
		this.clienteService = new ClienteService();
	}

	public void init() {
		consoleView.printMessage("--INIT VIEW--");
		vista.abrirVista();
	}

	// =========================  METODOS DE TECNICO =========================

	public boolean registrarTecnico(TecnicoDTO dto) {
	    return tecnicoService.registrarTecnicoDTO(dto);
	}

	public TecnicoDTO buscarTecnico(int id) {
	    return tecnicoService.buscarTecnicoDTO(id);
	}

	public ListaEnlazada<TecnicoDTO> listarTecnicos() {
	    return tecnicoService.listarTecnicosDTO();
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorZona(String zona) {
	    return tecnicoService.buscarTecnicosPorZonaDTO(zona);
	}

	public ListaEnlazada<TecnicoDTO> buscarTecnicosPorEspecialidad(String especialidad) {
	    return tecnicoService.buscarTecnicosPorEspecialidadDTO(especialidad);
	}

	public TecnicoDTO buscarTecnicoDisponible(String zona) {
	    return tecnicoService.buscarTecnicoDisponibleDTO(zona);
	}

	public boolean actualizarTecnico(TecnicoDTO dto) {
	    return tecnicoService.actualizarTecnicoDTO(dto);
	}
	
	// =========================  METODOS DE CLIENTES =========================
	
	public boolean registrarCliente(ClienteDTO dto) {
	    return clienteService.registrarCliente(dto);
	}

	public ClienteDTO buscarCliente(int id) {
	    return clienteService.buscarCliente(id);
	}

	// =========================  METODOS DE SOLICITUD ========================

	// =========================  METODOS DE REPORTES =========================
}