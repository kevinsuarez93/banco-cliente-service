package ec.com.banco.cliente.infrastructure.persona.out.db;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.repositories.ClienteRepository;
import ec.com.banco.cliente.infrastructure.common.repositories.JPABaseRepository;
import ec.com.banco.cliente.infrastructure.persona.entities.ClienteEntity;
import ec.com.banco.cliente.infrastructure.persona.mappers.ClienteMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static ec.com.banco.cliente.infrastructure.persona.entities.QClienteEntity.clienteEntity;
@Repository
@Transactional
@Slf4j
public class ClienteRepositoryImpl extends JPABaseRepository<ClienteEntity, Long>
        implements ClienteRepository {

    private final ClienteMapper clienteMapper;

    private static final String NO_EXISTEN_REGISTROS = "No existen registros con los datos proporcionados";


    @PersistenceContext
    private final EntityManager entityManager;

    public ClienteRepositoryImpl(ClienteMapper clienteMapper, EntityManager entityManager) {
        super(ClienteEntity.class, entityManager);
        this.clienteMapper = clienteMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void crearCliente(Cliente cliente) {
        this.save(clienteMapper.domainToEntity(cliente));
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws EntidadNoEncontradaException {
        if (obtenerCliente(cliente.getClienteId()) == null) {
            throw new EntidadNoEncontradaException(
                    String.format("No existe compania con del codigo %s ", cliente.getClienteId()));
        }

        ClienteEntity entity = getQueryFactory().selectFrom(clienteEntity)
                .where(clienteEntity.clienteId.eq(cliente.getClienteId())).fetchOne();
        clienteMapper.domainToEntity(cliente, entity);
        this.save(entity);
    }

    @Override
    public Cliente obtenerCliente(Long clienteId) {
        ClienteEntity entities = getQueryFactory().selectFrom(clienteEntity)
                .where(clienteEntity.clienteId.eq(clienteId)).fetchOne();
        return clienteMapper.entityToDomain(entities);
    }

    @Override
    public void eliminarCliente(Long clienteId) throws EntidadNoEncontradaException {
        try {
            ClienteEntity entity = getReferenceById(clienteId);
            this.delete(entity);
        } catch (EntityNotFoundException e) {
            log.error(NO_EXISTEN_REGISTROS, e.getMessage());
            throw new EntidadNoEncontradaException(e.getMessage());
        }
    }
}