package com.test;

import com.ec.mutualistapichincha.workflow.service.ServicioConsulta;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by Hyun Woo Son on 1/19/18
 **/
public class TestEjbRemote {

    private static Logger logger = LoggerFactory.getLogger(TestEjbRemote.class);

    @Test
    public void testEjb() throws Exception{

        Properties prop = new Properties();

        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        prop.put(Context.PROVIDER_URL, "remote://150.88.1.183:4947");
        prop.put(Context.SECURITY_PRINCIPAL, "admin");
        prop.put(Context.SECURITY_CREDENTIALS, "advance$1234");
        prop.put("jboss.naming.client.ejb.context", true);

        Context context = new InitialContext(prop);

        ServicioConsulta servicioConsulta =  (ServicioConsulta)context.lookup("mupi-workflow-ear/mupi-workflow-ejb/ServicioConsultaBean!com.ec.mutualistapichincha.workflow.service.ServicioConsulta");


        Double patrimonio = servicioConsulta.obtenerPatrimonioTecnico();

        logger.info("Patrimonio: {}",patrimonio);

        context.close();

    }

}
