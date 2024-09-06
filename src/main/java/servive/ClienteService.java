package servive;

import org.springframework.stereotype.Service;

import entity.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClienteService {
    private final List<Cliente> clientes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    
    public Cliente crearCliente(Cliente cliente) {
        cliente.setId(idGenerator.getAndIncrement());
        clientes.add(cliente);
        return cliente;
    }

 
    public List<Cliente> listarClientes() {
        return clientes;
    }


    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();
    }

 
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Optional<Cliente> clienteOpt = obtenerClientePorId(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setCorreo(clienteActualizado.getCorreo());
            cliente.setTelefono(clienteActualizado.getTelefono());
            return cliente;
        }
        return null;
    }

    public boolean eliminarCliente(Long id) {
        return clientes.removeIf(cliente -> cliente.getId().equals(id));
    }
}