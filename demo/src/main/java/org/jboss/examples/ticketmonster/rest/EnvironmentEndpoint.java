package org.jboss.examples.ticketmonster.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.examples.ticketmonster.rest.dto.EnvironmentDTO;
import org.jboss.resteasy.annotations.cache.NoCache;

/**
 * 
 */
@Stateless
@Path("/environment")
@NoCache
public class EnvironmentEndpoint
{

	private static final String PROPERTIES_FILE_NAME = "app.properties";
	
   @GET
   @Path("/versionandhost")
   @Produces("application/json")
   public Response getVersionAndHost()
   {
	   StringBuilder sb = new StringBuilder();
	   
	   
	   sb.append("Ticket Monster ");
	   
	   InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
	   Properties properties = new Properties();
		
		try {
			properties.load(is);
			
			String version = properties.getProperty("version");
			
			sb.append(version);
			
		} catch (IOException e) {
			
		}
		finally {
			try {
				is.close();
			}
			catch(Exception e) {
				
			}
		}
	   
	   try {
		   String host = InetAddress.getLocalHost().getHostAddress();
		   
		   if(host != null) {
			   sb.append("<br>Served by " + host);
		   }
	   }
	   catch(Exception e) {}
	   
      return Response.ok(new EnvironmentDTO(sb.toString())).build();
   }

}
