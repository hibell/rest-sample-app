// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.rest;

import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// tag::path[]
@Path("properties")
// end::path[]
public class PropertiesResource {

    // tag::get[]
    //@GET
    // end::get[]
    // tag::produces[]
    @Produces(MediaType.APPLICATION_JSON)
    // end::produces[]
    public Properties getProperties() {
        return System.getProperties();
    }

    @GET
    //@Path("/system")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getSystemProperties() {
        Properties properties = new Properties();
        //properties.setProperty("liberty.runtime.root", System.getenv("BPI_OL_ROOT"));
        properties.setProperty("payload", "hello-from-IBM");
		try (InputStream is = new FileInputStream("/etc/os-release")) {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return properties;
    }
}
