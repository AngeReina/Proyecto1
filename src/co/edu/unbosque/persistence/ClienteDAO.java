package co.edu.unbosque.persistence;

import co.edu.unbosque.model.cliente.Cliente;

public class ClienteDAO extends AbstractFileDAO<Cliente, Integer> {

	private DataMapper dataMapper;

	public ClienteDAO() {
		super("data/clientes.txt");
		dataMapper = new DataMapper();
		loadFromFile();
	}

	@Override
	protected String objectToLine(Cliente obj) {
		return dataMapper.clienteToLine(obj);
	}

	@Override
	protected Cliente lineToObject(String line) {
		return dataMapper.lineToCliente(line);
	}

	@Override
	protected Integer getId(Cliente obj) {
		return obj.getId();
	}

	@Override
	protected boolean compareId(Cliente obj, Integer id) {
		return obj.getId() == id;
	}

}
