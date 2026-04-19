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
import co.edu.unbosque.model.enums.TipoSolicitud;
import co.edu.unbosque.model.enums.Zona;
import co.edu.unbosque.model.kit.KitDTO;
import co.edu.unbosque.model.kit.KitService;
import co.edu.unbosque.model.reports.ReporteDTO;
import co.edu.unbosque.model.reports.ReporteService;
import co.edu.unbosque.model.solicitud.SolicitudDTO;
import co.edu.unbosque.model.solicitud.SolicitudServicio;
import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.unidad.UnidadDTO;
import co.edu.unbosque.model.unidad.UnidadServicio;
import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.Zona;


public class ServicioControlador {

    private VistaPrincipal vista;
    private UnidadServicio unidadServicio;
    private VistaConsola consoleView;
	private TecnicoService tecnicoService;
	private UnidadDTO ultimaUnidad;
	private ClienteService clienteService;
	private KitService kitService;
	private SolicitudServicio solicitudServicio;
	private SolicitudDTO solicitudActual;
	private KitDTO kitEnUso;
	
	ReporteService reporteService;

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
                Zona zona = vista.getDialogoUnidad().getZona();

                unidadServicio.registrarUnidad(tipo, estado, zona);

                vista.getDialogoUnidad().mostrarMensaje("Unidad registrada");
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_BUSCAR:

            Zona zonaBuscar = vista.getDialogoUnidad().pedirZonaBusqueda();

            if (zonaBuscar == null) {
                break;
            }

            ListaEnlazada<UnidadDTO> listaUnidades = 
                unidadServicio.buscarDisponibles(zonaBuscar.name());

            if (listaUnidades != null && listaUnidades.count() > 0) {

                String mensaje = "Unidades encontradas en " + zonaBuscar + ":\n";

                for (int i = 0; i < listaUnidades.count(); i++) {

                    UnidadDTO u = listaUnidades.getValueByPos(i);

                    mensaje += "\nID: " + u.getId();
                    mensaje += "\n" + u.getTipo() + " | " + u.getEstado() + " | " + u.getZona();
                    mensaje += "\n----------------------";
                }

                vista.getDialogoUnidad().mostrarMensaje(mensaje);

            } else {
                vista.getDialogoUnidad().mostrarMensaje(
                    "No hay unidades disponibles en la zona " + zonaBuscar
                );
            }

            break;


            case Constantes.BTN_UNIDAD_LIMPIAR:
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_CERRAR:
                vista.getDialogoUnidad().setVisible(false);
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
                    vista.getDialogoUnidad().mostrarMensaje(
                        "No se pudo cambiar el estado (puede ser el mismo estado o la unidad no existe)"
                    );
                }

            } catch (Exception e) {
                vista.getDialogoUnidad().mostrarMensaje("Formato de ID inválido");
            }

            break;
                    

            
// =========================  METODOS DE TECNICO =========================


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

                        TecnicoDTO t = buscarTecnicoDisponible(zonaTecnico);

                        if (t != null) {
                            String mensaje = "Técnico disponible:\n" +
                                    "ID: " + t.getId() + "\n" +
                                    "Nombre: " + t.getNombre() + "\n" +
                                    "Especialidad: " + t.getEspecialidad() + "\n" +
                                    "Estado: " + t.getEstado();

                            vista.getDialogoTecnico().mostrarMensaje(mensaje);

                        } else {
                            ListaEnlazada<TecnicoDTO> lista = buscarTecnicosPorZona(zonaTecnico);

                            if (lista != null && lista.count() > 0) {

                                String mensaje = "No hay disponibles. Técnicos en la zona:\n";

                                for (int i = 0; i < lista.count(); i++) {
                                    TecnicoDTO tec = lista.getValueByPos(i);

                                    mensaje += "\nID: " + tec.getId() +
                                               " | " + tec.getNombre() +
                                               " | " + tec.getEspecialidad() +
                                               " | " + tec.getEstado();
                                }

                                vista.getDialogoTecnico().mostrarMensaje(mensaje);

                            } else {
                                vista.getDialogoTecnico().mostrarMensaje("No hay técnicos en esa zona");
                            }
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
                    
                case Constantes.BTN_ABRIR_DIALOGO_KITS: {
                    vista.abrirDialogoKit();
                    break;
                }
                case Constantes.BTN_KITS_REGISTRAR: {
                	KitDTO kitDto = new KitDTO();
                	kitDto.setTipo(vista.getDialogoKit().getTipo());

                    boolean kitRegistrado = registrarKit(kitDto);

                    if (kitRegistrado) {
                        vista.getDialogoKit().mostrarMensaje("Kit registrado");
                        vista.getDialogoKit().limpiarCampos();
                    } else {
                        vista.getDialogoKit().mostrarMensaje("Error al registrar");
                    }
                    break;
                }
                case Constantes.BTN_KITS_REVISAR: {
                	revisarKit();
                    break;
                }
                case Constantes.BTN_KIT_LIMPIAR: {
                	vista.getDialogoKit().limpiarCampos();
                    break;
                }
                case Constantes.BTN_KIT_CERRAR: {
                	vista.getDialogoKit().setVisible(false);
                    break;
                }
                
                case Constantes.BTN_ABRIR_DIALOGO_SOLICITUD: {
                    vista.abrirDialogoSolicitud();
                    break;
                }
                
                case Constantes.BTN_SOLICITUD_REGISTRAR: {
                	SolicitudDTO solicitudDTO = new SolicitudDTO();
                	
                	ClienteDTO solicitante = clienteService.buscarCliente(vista.getDialogoSolicitud().getClienteId());
                	
                	if (solicitante != null) {
                    	
                    	solicitudDTO.setClienteId(vista.getDialogoSolicitud().getClienteId());
                    	solicitudDTO.setUbicacion(vista.getDialogoSolicitud().getUbicacion());
                    	solicitudDTO.setDescripcionIncidente(vista.getDialogoSolicitud().getDescripcion());
                    	solicitudDTO.setClienteTipo(solicitante.getTipo());
                    	solicitudDTO.setCriterioCriticidad(vista.getDialogoSolicitud().getTipo());
                    	
                    	solicitudServicio.registrarSolicitud(solicitudDTO);	
                	} else {
                		vista.getDialogoSolicitud().mostrarMensaje("No existe usuario para solicitud");
                	}
                	
                    break;
                }
                
                case Constantes.BTN_SOLICITUD_ASIGNAR: {                
                	SolicitudDTO solicitudAsignable = solicitudServicio.obtenerProximaAtencion();                
                	
                	if (solicitudAsignable != null) {
                    	UnidadDTO unidadDisponible = unidadServicio.buscarDisponible(solicitudAsignable.getUbicacion());
                    	TecnicoDTO tecnicoDisponible = tecnicoService.buscarTecnicoDisponibleDTO(solicitudAsignable.getUbicacion());
                    	
                    	if (unidadDisponible == null) {
                    		vista.getDialogoSolicitud().mostrarMensaje("No hay unidades de atencion disponibles en la zona");
                    	}
                    	else if (tecnicoDisponible == null) {
                    		vista.getDialogoSolicitud().mostrarMensaje("No hay tecnicos disponibles en la zona");
                    	} else if (kitService.existeKitDisponible()) {
                        	solicitudActual = solicitudServicio.asignarProximaSolicitud(unidadDisponible.getId(), tecnicoDisponible.getId());
                        	kitEnUso = kitService.retirarKit();
                    		vista.getDialogoSolicitud().mostrarMensaje("Solicitud asignada");
                    	} else {
                    		vista.getDialogoSolicitud().mostrarMensaje("No hay kits disponibles");
                    	}
                    	
                	} else {
                		vista.getDialogoSolicitud().mostrarMensaje("No hay solicitudes pendientes");
                	}
                	
                    break;
                }
                
                case Constantes.BTN_SOLICITUD_COMPLETAR: {                
                	solicitudServicio.marcarComoAtendida(solicitudActual); 
                	kitService.devolverKit(kitEnUso);
                	vista.getDialogoSolicitud().mostrarMensaje("Solicitud completada");
                	
                    break;
                }
                
                case Constantes.BTN_SOLICITUD_REPORTE: {                
                	SolicitudDTO[] data = solicitudServicio.listarHistorialAtendidas();
                	ReporteDTO reporteDTO = new ReporteDTO();
                	reporteDTO.setSolicitudes(data);
                	reporteService.generarReporteSolicitudes(reporteDTO);
                	vista.getDialogoSolicitud().mostrarMensaje("Reporte generado en csv");
                    break;
                }
                
                case Constantes.BTN_SOLICITUD_CERRAR: {
                	vista.getDialogoSolicitud().setVisible(false);
                    break;
                }
            }
        }
	};

	public ServicioControlador() {
        unidadServicio = new UnidadServicio();
        vista = new VistaPrincipal(viewCmdListener);
        this.consoleView = new VistaConsola();
		this.tecnicoService = new TecnicoService();
		this.clienteService = new ClienteService();
		this.kitService = new KitService();
		this.solicitudServicio = new SolicitudServicio();
		this.reporteService = new ReporteService();
	}

	public void init() {
		consoleView.printMessage("--INIT VIEW--");
		kitService.init();
		solicitudServicio.init();
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
	
	// =========================  METODOS DE KITS ========================
	
	public boolean registrarKit(KitDTO dto) {
	    return kitService.agregarKit(dto);
	}
	
	public boolean devolverKit(KitDTO dto) {
	    return kitService.devolverKit(dto);
	}
	
	public boolean revisarKit() {
	    return kitService.revisarKit();
	}
	
	public KitDTO retirarKit() {
	    return kitService.retirarKit();
	}
}