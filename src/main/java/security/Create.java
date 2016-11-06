
package security;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("create")
public class Create {
   
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String gt() {
    return "{\"txt\" : \"TEST\"}";
  }
    
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User Create(String jsonString) throws JOSEException {
      try {
       JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
       String username = json.get("username1").getAsString();
       System.out.println(jsonString);
       String password = json.get("password1").getAsString();
     
          
      if(username!=null && password!=null && !username.isEmpty() && !password.isEmpty()){
          CreateUser(username, password);
         
      }
   
      }catch(Exception ex){
          
        }
      return null;
    }

    private User CreateUser(String userName, String password) {
   UserFacade facade = UserFacadeFactory.getInstance();
    return facade.adduser(userName, password);
    }


}
