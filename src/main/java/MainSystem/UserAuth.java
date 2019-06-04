package MainSystem;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import MainSystem.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.glassfish.jersey.message.internal.HttpHeaderReader.Event.Control;


@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class UserAuth {

    @Context
    private UriInfo uriInfo;


    @GET
    @Path("hello")
    @Produces(TEXT_PLAIN)
    public Response sayHelo() {
        return Response.ok("HELO").build();
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {

        System.out.println("LOGIN I HASŁO");
        System.out.println(login);
        System.out.println(password);

        String role = authenticate(login, password);
        // Authenticate the user using the credentials provided
        if (!role.equals("")) {
            String token = issueToken(login, role);
            System.out.println(token);
            return Response.status(302).header(LOCATION, "http://localhost:8080/Parking_war_exploded/api/echo").header(AUTHORIZATION, token).header("Access-Control-Allow-Origin", "*").build();
//            return Response.temporaryRedirect(java.net.URI.create((("http://localhost:8080/Parking_war_exploded/api/users/hello")))).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(UNAUTHORIZED).build();
        }

//            System.out.println("probuje issueToken");
//            // Issue a token for the user
//            String token = issueToken(login);
//
//
////            return Response.status(307).header(LOCATION, "http://localhost:8080/Parking_war_exploded/api/echo").header("X-Authorization", token).build();
//            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
//
//        } catch (Exception e) {
//            return Response.status(UNAUTHORIZED).build();
//        }
    }

    private String authenticate(String login, String password)
//            throws Exception
    {
        System.out.println("autentykujeee");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager em = factory.createEntityManager();
        TypedQuery<User> q = em.createQuery("SELECT b FROM User b", User.class);
        List<User> users = q.getResultList();

        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u.getRole();
            }
        }
        return "";

    }

    private String issueToken(String login, String role) {
        Key key = generateKey();
        String jwtToken = key+"."+login+"."+role;
//        String jwtToken = Jwts.builder()
//                .setSubject(login)
//                .setIssuer(uriInfo.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
        return jwtToken;

    }


    public Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }


    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
