package uk.co.pshealth.pslink.controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import uk.co.pshealth.pslink.dto.SimpleTraceQueryDTO;
import uk.co.pshealth.pslink.dto.retrieval.RetrievalQueryDTO;


/**
 * <p>Title: psHealth RESTful API Controller to integrate PDS </p>
 * <p>Description: RESTful API controller to query PDS system</p>
 * <p>Company: psHealth</p>
 * Created by lshen on 03/05/2017.
 */

@Path("/pds/v1") 
public class PDSController {
		
	//static Logger LOG = LoggerFactory.getLogger(PDSRouteBuilder.class);
	 
	public PDSController () {
		
	}  

	/**
	 * 
	 * @param simpleTraceQueryDTO
	 * @return Spine PDS response
	 */
	@POST
	@Path("/SimpleTraceQuery")
	@Consumes({ "application/xml" })
	@Produces({ "application/xml" })
	public Response PDSSimpleTrace(SimpleTraceQueryDTO simpleTraceQueryDTO) { 
		
		return null;		
	}
	
	/**
	 * 
	 * @param RetrievalQueryDTO
	 * @return Spine PDS response
	 */
	@POST
	@Path("/RetrievalQuery")
	@Consumes({ "application/xml" })
	@Produces({ "application/xml" })
	public Response PDSRetrievalQuery(RetrievalQueryDTO RetrievalQueryDTO) {  
		
		return null;		
	}
			
	
}
