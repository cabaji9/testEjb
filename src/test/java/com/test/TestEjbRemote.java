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
    public void testEjb() throws Exception {

        Properties prop = new Properties();

        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        prop.put(Context.PROVIDER_URL, "remote://localhost:4447");
        prop.put(Context.SECURITY_PRINCIPAL, "admin");
        prop.put(Context.SECURITY_CREDENTIALS, "admin$1234");
        prop.put("jboss.naming.client.ejb.context", true);

        Context context = new InitialContext(prop);
        EjbInterface ejbInterface = (EjbInterface) context.lookup("ejb/EjbImpl!com.test.EjbInterface");
        ejbInterface.test();

        logger.info("ejbInterface {}", ejbInterface);
        context.close();

    }

}
