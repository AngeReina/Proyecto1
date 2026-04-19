package co.edu.unbosque.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unbosque.model.base.ListaEnlazada;
import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.EstadoSolicitud;
import co.edu.unbosque.model.cliente.Cliente;
import co.edu.unbosque.model.cliente.ClienteDTO;
import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.TipoSolicitud;
import co.edu.unbosque.model.enums.Zona;
import co.edu.unbosque.model.kit.Kit;
import co.edu.unbosque.model.kit.KitDTO;
import co.edu.unbosque.model.enums.TIPO_CLIENTE;
import co.edu.unbosque.model.solicitud.Solicitud;
import co.edu.unbosque.model.solicitud.SolicitudDTO;
import co.edu.unbosque.model.tecnico.Tecnico;
import co.edu.unbosque.model.tecnico.TecnicoDTO;
import co.edu.unbosque.model.unidad.Unidad;
import co.edu.unbosque.utils.Constantes;

public class DataMapper {

	public String tecnicoToLine(Tecnico tecnico) {
		if (tecnico == null) {
			return null;
		}

		return tecnico.getId() + ";" +
			   tecnico.getNombre() + ";" +
			   tecnico.getEspecialidad() + ";" +
			   tecnico.getEstado().name() + ";" +
			   tecnico.getZona();
	}

	public Tecnico lineToTecnico(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}

		String[] parts = line.split(";");

		if (parts.length != 5) {
			return null;
		}

		try {
			int id = Integer.parseInt(parts[0]);
			String nombre = parts[1];
			String especialidad = parts[2];
			EstadoTecnico estado = EstadoTecnico.valueOf(parts[3]);
			String zona = parts[4];

			return new Tecnico(id, nombre, especialidad, estado, zona);
		} catch (Exception e) {
			return null;
		}
	}
	public Tecnico toTecnico(TecnicoDTO dto) {
        if (dto == null) return null;

        return new Tecnico(
                dto.getId(),
                dto.getNombre(),
                dto.getEspecialidad(),
                EstadoTecnico.valueOf(dto.getEstado().toUpperCase()),
                dto.getZona()
        );
    }

    public TecnicoDTO toTecnicoDTO(Tecnico t) {
        if (t == null) return null;

        return new TecnicoDTO(
                t.getId(),
                t.getNombre(),
                t.getEspecialidad(),
                t.getEstado().name(),
                t.getZona()
        );
    }

    public ListaEnlazada<TecnicoDTO> toTecnicoDTOList(ListaEnlazada<Tecnico> lista) {
        ListaEnlazada<TecnicoDTO> dtoList = new ListaEnlazada<>();

        for (int i = 0; i < lista.count(); i++) {
            Tecnico t = lista.getValueByPos(i);
            if (t != null) {
                dtoList.add(toTecnicoDTO(t));
            }
        }

        return dtoList;
    }



	public String unidadToLine(Unidad unidad) {
		if (unidad == null) {
			return null;
		}

		return unidad.getId() + ";" +
			unidad.getTipo().name() + ";" +
			unidad.getEstado().name() + ";" +
			unidad.getZona();
	}

	public Unidad lineToUnidad(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}

		String[] parts = line.split(";");

		if (parts.length != 4) {
			return null;
		}

		try {
			java.util.UUID id = java.util.UUID.fromString(parts[0]);
			TIPO_VEHICULO tipo = TIPO_VEHICULO.valueOf(parts[1]);
			ESTADO_UNIDAD estado = ESTADO_UNIDAD.valueOf(parts[2]);
			Zona zona = Zona.valueOf(parts[3]);

			return new Unidad(id, tipo, estado, zona);
		} catch (Exception e) {
			return null;
		}
	}


    //CLIENTE ----------------------------------------------------------------------------------------------------------------

	public String clienteToLine(Cliente cliente) {
		if (cliente == null) {
			return null;
		}

		return cliente.getId() + ";" +
		cliente.getNombre() + ";" +
		cliente.getTelefono() + ";" +
		cliente.getTipo().name();
	}

	public Cliente lineToCliente(String line) {
		if (line == null || line.trim().isEmpty()) {
			return null;
		}

		String[] parts = line.split(";");

		if (parts.length != 4) {
			return null;
		}

		try {
			int id = Integer.parseInt(parts[0]);
			String nombre = parts[1];
			String telefono = parts[2];
			TIPO_CLIENTE tipo = TIPO_CLIENTE.valueOf(parts[3]);

			return new Cliente(id, nombre, telefono, tipo);
		} catch (Exception e) {
			return null;
		}
	}
	public Cliente toCliente(ClienteDTO dto) {
        if (dto == null) return null;

        return new Cliente(
                dto.getId(),
                dto.getNombre(),
                dto.getTelefono(),
                TIPO_CLIENTE.valueOf(dto.getTipo()));
    }

    public ClienteDTO toClienteDTO(Cliente c) {
        if (c == null) return null;

        return new ClienteDTO(
                c.getId(),
                c.getNombre(),
                c.getTelefono(),
                c.getTipo().name()
        );
    }

    public ListaEnlazada<ClienteDTO> toClienteDTOList(ListaEnlazada<Cliente> lista) {
        ListaEnlazada<ClienteDTO> dtoList = new ListaEnlazada<>();

        for (int i = 0; i < lista.count(); i++) {
        	Cliente t = lista.getValueByPos(i);
            if (t != null) {
                dtoList.add(toClienteDTO(t));
            }
        }

        return dtoList;
    }
    
  //KIT ----------------------------------------------------------------------------------------------------------------

  	public String kitToLine(Kit kit) {
  		if (kit == null) {
  			return null;
  		}

  		return kit.getId().toString() + ";" +
  		kit.getTipo() + ";" +
  		kit.isNecesitaRevision();
  	}

  	public Kit lineToKit(String line) {
  		if (line == null || line.trim().isEmpty()) {
  			return null;
  		}

  		String[] parts = line.split(";");

  		if (parts.length != 3) {
  			return null;
  		}

  		try {
  			java.util.UUID id = java.util.UUID.fromString(parts[0]);
  			String tipo = parts[1];
  			boolean necesitaRevision = Boolean.parseBoolean(parts[2]);

  			return new Kit(id, tipo, necesitaRevision);
  		} catch (Exception e) {
  			return null;
  		}
  	}
  	
  	public Kit toKit(KitDTO dto) {
          if (dto == null) return null;

          return new Kit(
                  dto.getId(),
                  dto.getTipo(),
                  dto.isNecesitaRevision());
     }

      public KitDTO toKitDTO(Kit c) {
          if (c == null) return null;
          
          KitDTO dto = new KitDTO();
          dto.setId(c.getId());
          dto.setNecesitaRevision(c.isNecesitaRevision());
          dto.setTipo(c.getTipo());

          return dto;
      }

      public ListaEnlazada<KitDTO> toKitDTOList(ListaEnlazada<Kit> lista) {
          ListaEnlazada<KitDTO> dtoList = new ListaEnlazada<>();

          for (int i = 0; i < lista.count(); i++) {
        	  Kit t = lista.getValueByPos(i);
              if (t != null) {
                  dtoList.add(toKitDTO(t));
              }
          }

          return dtoList;
      }


    //SOLICITUD ---------------------------------------------------------------------------------------------------------------

	public String solicitudDtoToLine(SolicitudDTO dto) {
		if (dto == null) {
			return null;
		}

		return dto.getClienteId() + ";" +
		       dto.getId() + ";" +
		       dto.getTipo() + ";" +
		       dto.getUbicacion() + ";" +
		       dto.getTecnicoAsignado() + ";" +
		       dto.getCriterioCriticidad() + ";" +
			   dto.getDescripcionIncidente() + ";" +
			   dto.getEstado() + ";" +
			   dto.getFechaCreacion() + ";" +
			   dto.getFechaAsignacion() + ";" +
			   dto.getFechaAtencion() + ";" +
			   dto.getClienteTipo()  + ";" +
			   dto.getUnidadId().toString()
			   ;
	}
	
  	public String solicitudToLine(Solicitud solicitud) {
  		if (solicitud == null) {
  			return null;
  		}

  		return solicitud.getClienteId() + ";" +
  		solicitud.getId() + ";" +
  		solicitud.getTipo().name() + ";" +
  		solicitud.getUbicacion() + ";" +
  		solicitud.getTecnicoId() + ";" +
  		solicitud.getCriterioCriticidad().name() + ";" +
 		solicitud.getDescripcionIncidente() + ";" +
 		solicitud.getEstado().name() + ";" +
 		solicitud.getFechaCreacion() + ";" +
 		solicitud.getFechaAsignacion() + ";" +
 		solicitud.getFechaAtencion() + ";" +
 		solicitud.getClienteTipo().name() + ";" +
 		solicitud.getUnidadId().toString() ;
  	}

  	public Solicitud lineToSolicitud(String line) {
  		if (line == null || line.trim().isEmpty()) {
  			return null;
  		}

  		String[] parts = line.split(";");
			Solicitud solicitud = new Solicitud(0, 0, line, line, null, null);
			SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_FORMAT);

  			solicitud.setClienteId(Integer.parseInt(parts[0]));
  	  		solicitud.setId(Integer.parseInt(parts[1]));
  	  		solicitud.setTipo(TipoSolicitud.valueOf(parts[2]));
  	  		solicitud.setUbicacion(parts[3]);
  	  		solicitud.setTecnicoId(Integer.parseInt(parts[4]));
  	  		solicitud.setCriterioCriticidad(CriterioCriticidad.valueOf(parts[5]));
  	 		solicitud.setDescripcionIncidente(parts[6]);
  	 		solicitud.setEstado(EstadoSolicitud.valueOf(parts[7]));
  	 		solicitud.setClienteTipo(TIPO_CLIENTE.valueOf(parts[11]));
  	 		solicitud.setUnidadId(java.util.UUID.fromString(parts[12]));
  		
 		try {
  	 		solicitud.setFechaCreacion(sdf.parse(parts[8]).getTime());
  	 		solicitud.setFechaAsignacion(sdf.parse(parts[9]).getTime());
  	 		solicitud.setFechaAtencion(sdf.parse(parts[10]).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
 		
		return solicitud;
  	}
  	
  	public Solicitud toSolicitud(SolicitudDTO dto) {
          if (dto == null) return null;

           SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_FORMAT);
			
			Solicitud solicitud = new Solicitud(0, 0, "", "", null, null);
  			
			solicitud.setClienteId(dto.getClienteId());
			solicitud.setClienteTipo(TIPO_CLIENTE.valueOf(dto.getClienteTipo()));
	  		solicitud.setId(dto.getId());
	  		solicitud.setTipo(TipoSolicitud.valueOf(dto.getTipo()));
	  		solicitud.setUbicacion(dto.getUbicacion());
	  		solicitud.setTecnicoId(dto.getTecnicoAsignado());
	  		solicitud.setCriterioCriticidad(CriterioCriticidad.valueOf(dto.getCriterioCriticidad()));
	  		solicitud.setCriterioCriticidad(CriterioCriticidad.valueOf(dto.getCriterioCriticidad()));
	 		solicitud.setDescripcionIncidente(dto.getDescripcionIncidente());
	 		solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
	 		solicitud.setUnidadId(dto.getUnidadId());
	 		try {
				solicitud.setFechaCreacion(sdf.parse(dto.getFechaCreacion()).getTime());
		 		solicitud.setFechaAsignacion(sdf.parse(dto.getFechaAsignacion()).getTime());
		 		solicitud.setFechaAtencion(sdf.parse(dto.getFechaAtencion()).getTime());
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
	 		
	 		return solicitud;
     }

      public SolicitudDTO toSolicitudDTO(Solicitud model) {
          if (model == null) return null;
          
          SolicitudDTO dto = new SolicitudDTO();
          
          dto.setClienteId(model.getClienteId());
          dto.setClienteTipo(model.getClienteTipo().name());
          dto.setId(model.getId());
          dto.setTipo(model.getTipo().name());
          dto.setUbicacion(model.getUbicacion());
          dto.setTecnicoAsignado(model.getTecnicoId());
          dto.setCriterioCriticidad(model.getCriterioCriticidad().name());
          dto.setDescripcionIncidente(model.getDescripcionIncidente());
          dto.setEstado(model.getEstado().name());
          dto.setFechaCreacion(formatearFecha(model.getFechaCreacion()));
	 	  dto.setFechaAsignacion(formatearFecha(model.getFechaAsignacion()));
	 	  dto.setFechaAtencion(formatearFecha(model.getFechaAtencion()));
	 	  dto.setUnidadId(model.getUnidadId());

          return dto;
      }

      public ListaEnlazada<SolicitudDTO> toSolicitudDTOList(ListaEnlazada<Solicitud> lista) {
          ListaEnlazada<SolicitudDTO> dtoList = new ListaEnlazada<>();

          for (int i = 0; i < lista.count(); i++) {
        	  Solicitud t = lista.getValueByPos(i);
              if (t != null) {
                  dtoList.add(toSolicitudDTO(t));
              }
          }

          return dtoList;
      }
      
  	private String formatearFecha(long timestamp) {
        Date fecha = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_FORMAT);
        return sdf.format(fecha);
    }
}