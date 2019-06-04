package MainSystem;

import MainSystem.filter.JWTTokenNeeded;

import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;


@Path("/echo")
@Produces(TEXT_PLAIN)
public class EchoEndpoint {


    @GET
    public Response echo(@QueryParam("message") String message, @Context HttpHeaders headers) {
        List<String> allHeaders = new ArrayList<>();
        for(String header : headers.getRequestHeaders().keySet()){
            allHeaders.add(header);
//            System.out.println(header);
        }
//        System.out.println(allHeaders);
        if(checkHeadersSomehow(allHeaders.get(1))){
            return Response.temporaryRedirect(java.net.URI.create((("http://localhost:8080/Parking_war_exploded/index.xhtml")))).header(AUTHORIZATION, "").build();
        }
        return Response.ok().entity(message == null ? "no message" : message).build();
    }

    private boolean checkHeadersSomehow(String token) {
//        System.out.println("TOKEEEEEEEEEEEEEEEEEEEEEEEEEEN");
//        System.out.println(token);
        return true;
    }

    @GET
    @Path("jwt")
    @JWTTokenNeeded
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
}
