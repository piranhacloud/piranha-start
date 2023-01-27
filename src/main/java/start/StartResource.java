package start;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RequestScoped
@Path("")
public class StartResource {

    @GET
    @Path("ping")
    public String ping() {
        return "Success";
    }
}
