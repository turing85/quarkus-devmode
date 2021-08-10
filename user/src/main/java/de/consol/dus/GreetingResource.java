package de.consol.dus;

import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(Optional<GreetingRequest> greeting) {
        return String.format(
            "%s %s!",
            greeting.map(GreetingRequest::getSalutation).orElse("Hello there"),
            greeting.map(GreetingRequest::getName).orElse("IBM"));
    }
}