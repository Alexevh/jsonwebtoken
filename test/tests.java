
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import java.io.UnsupportedEncodingException;
import static javax.crypto.Cipher.SECRET_KEY;
import jwt.Usuario;
import jwt.jwtfactory;
import jwt.jwtfactory2;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40230691.858
 */
public class tests {
    
    @Test
    public void generarToken(){
        
        Usuario u = new Usuario();
        u.setNombre("Peter");
        u.setMail("sarasa@sarasa.com");
        u.setRol("ADMIN");
        
        String token = jwtfactory.obtenerToken(u);
        System.out.println("me llega "+token);
        String recuperado = jwtfactory.recuperarJSON(token);
        System.out.println("me llega "+recuperado);
        Claims claims = jwtfactory.recuperarJSONClaims(token);
        System.out.println("me llega "+claims);
        String  JS  = jwtfactory.recuperarJSON2(token);
        System.out.println("me llega "+JS);
        Gson gson = new Gson();
        
       
        
    }
    
        
    @Test
    public void generarTokenAuth0() throws IllegalArgumentException, UnsupportedEncodingException{
        Gson gson = new Gson();
        Usuario u = new Usuario();
        u.setNombre("Peter");
        u.setMail("sarasa@sarasa.com");
        u.setRol("ADMIN");
        
        String token = jwtfactory2.obtenerToken(u);
        System.out.println("me llega "+token);
        
        boolean valido  = jwtfactory2.tokenValido(token);
        
        
        DecodedJWT jwt = jwtfactory2.verificarToken(token);
        //String usuario = jwt.getClaim("usuario").toString();
        String resultado  = gson.toJson(jwt.getClaim("usuario"));
        
        //DecodedJWT j2 = JWT.decode(token);
         
         
          
             System.out.println("me llega "+resultado);
    }
    
}
