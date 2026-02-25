package servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import modelo.Destinatario;

class ServicioEnviarEmailsTest {

    @Test
    void enviarEmail_devuelveFalse_siDatosInvalidos() {

        Logger logger = LoggerFactory.getLogger(ServicioEnviarEmails.class);
        ServicioEnviarEmails servicio = new ServicioEnviarEmails(logger);
        
        Destinatario dest = new Destinatario();
        
        //Debe devolver falso si algún parámetro es nulo
        assertFalse(servicio.enviarEmail(null, "Mensaje"));
        assertFalse(servicio.enviarEmail(dest, null));
    }

    @Test
    void enviarEmail_devuelveTrue_siDatosValidos() {

        Logger logger = LoggerFactory.getLogger(ServicioEnviarEmails.class);
        ServicioEnviarEmails servicio = new ServicioEnviarEmails(logger);

        Destinatario dest = new Destinatario();
        
        //Este es uno con datos válidos (vaya, distintos de nulo)
        boolean resultado = servicio.enviarEmail(dest, "prueba");

        assertTrue(resultado, "Si destinatario y mensaje válidos debe devolver true");
    }
}