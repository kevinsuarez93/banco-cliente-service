package ec.com.banco.cliente.infrastructure.persona.out.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.jms.IntegracionCuenta;
import ec.com.banco.cliente.infrastructure.common.exceptions.RemoteExecutionException;
import ec.com.banco.cliente.infrastructure.common.jms.JmsClient;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
import ec.com.banco.cuenta.share.ejemplo.enums.Operacion;
import jakarta.jms.JMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties(JmsCuentaProperties.class)
public class IntegracionCuentaImpl  implements IntegracionCuenta {



    private JmsClient jmsClient;
    private JmsCuentaProperties jmsCuentaProperties;

    public IntegracionCuentaImpl(JmsClient jmsClient, JmsCuentaProperties jmsCuentaProperties) {
        this.jmsClient = jmsClient;
        this.jmsCuentaProperties = jmsCuentaProperties;

    }



    @Override
    public CuentaDto obtenerCuenta(Long clienteId) throws EntidadNoEncontradaException {
        try {
            CuentaDto companiaDto = CuentaDto.builder().clienteId(clienteId).build();
            return jmsClient.sendAndWaitForResponse(companiaDto, CuentaDto.class,
                    jmsCuentaProperties.getRequestqueue(), jmsCuentaProperties.getReplyqueue(),
                    Optional.empty(), Operacion.GET_CUENTA_POR_ID.name());
        } catch (JsonProcessingException | RemoteExecutionException | JMSException e) {
            throw new EntidadNoEncontradaException(e.getMessage());
        }
    }
}
