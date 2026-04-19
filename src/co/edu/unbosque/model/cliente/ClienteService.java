package co.edu.unbosque.model.cliente;

import co.edu.unbosque.persistence.ClienteDAO;
import co.edu.unbosque.persistence.DataMapper;

public class ClienteService {
	
	private ClienteDAO clienteDAO;
	private DataMapper mapper;

	public ClienteService() {
		clienteDAO = new ClienteDAO();
		mapper = new DataMapper();
	}

	public boolean registrarCliente(ClienteDTO cliente) {
		if (cliente == null) {
			return false;
		}
		
		if (buscarCliente(cliente.getId())  == null) {
			return clienteDAO.create(mapper.toCliente(cliente));	
		} else {
			return false;
		}
	}

	public ClienteDTO buscarCliente(int id) {
		Cliente cliente= clienteDAO.read(id);
		
		if (cliente != null) {
			return mapper.toClienteDTO(cliente);
		} else {
			return null;
		}
	}

}
