package com.test.impl;

import com.test.EjbInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Hyun Woo Son on 1/19/18
 **/
@Stateless
@Remote(value = EjbInterface.class)
public class EjbImpl implements EjbInterface {

    private static Logger logger = LoggerFactory.getLogger(EjbImpl.class);

    public void test() {
        logger.info("Hello world!");
    }
}
