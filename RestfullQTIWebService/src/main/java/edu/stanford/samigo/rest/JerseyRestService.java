package edu.stanford.samigo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.*;
import edu.stanford.samigo.qti.*;

/*
 *  This Restful web service will return the JSON formatted equivalent of the QTI xml 
 *  It uses JAXB to bind the xml to beans that can be transform content to JSON
 *  To invoke: On your client, do  http://localhost:8082/JAXRS-JSONExample/rest/jsonServices/printQTI/S14-SPAN-SOPI-11
 *  
 *  @author   santos1, April 8, 2014
 */


@Path("/jsonServices")
public class JerseyRestService {
          
	@GET
	@Path("/printQTI/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public QuestestinteropType produceJSONQTI(
			@PathParam("courseId") String courseId) {

		QuestestinteropType questest = null;

		try {
			// Get a handle here on the location of the xml file to be converted
			// In this case, I'm just hard coding the location from the file
			// system
			File file = new File(
					"/Users/reynaldosantos/Downloads/stanford/qti/exported-assessment.xml");
			InputStream in = new FileInputStream(file);

			JAXBContext jaxbContext = JAXBContext
					.newInstance(QuestestinteropType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			JAXBElement<QuestestinteropType> root = jaxbUnmarshaller.unmarshal(
					new StreamSource(file), QuestestinteropType.class);
			questest = root.getValue();

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return questest;

	}

}