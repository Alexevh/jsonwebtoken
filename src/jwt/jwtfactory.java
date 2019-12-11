/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 40230691.858
 */
public class jwtfactory {

    public static String obtenerToken(Usuario u) {
        
        

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date expireDate = new Date(nowMillis +10000000) ;
        
        
     
        
        Gson gson = new Gson();
        String json = gson.toJson(u);
        
        
        //Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject(json)             
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, "LaConchaedetuputamadreylareputisimamadrequeterecontramilpariohijodeputaperolaconchadelalorasorete")
                .compact();

        return compactJws;
    }

    public static String recuperarJSON(String json) {
        try {

            String resultado = Jwts.parser().setSigningKey("LaConchaedetuputamadreylareputisimamadrequeterecontramilpariohijodeputaperolaconchadelalorasorete").parseClaimsJws(json).toString();

            //OK, we can trust this JWT
            return resultado;
        } catch (JwtException e) {

           return e.getMessage();
        }
    }
    
      public static Claims recuperarJSONClaims(String json) {
        try {

             Claims resultado = Jwts.parser().setSigningKey("LaConchaedetuputamadreylareputisimamadrequeterecontramilpariohijodeputaperolaconchadelalorasorete").parseClaimsJws(json).getBody();

            //OK, we can trust this JWT
            return resultado;
        } catch (JwtException e) {

           return null;
        }
    }

      
       public static String recuperarJSON2(String json) {
        try {
            Gson gson = new Gson();
           
             Claims res = Jwts.parser().setSigningKey("LaConchaedetuputamadreylareputisimamadrequeterecontramilpariohijodeputaperolaconchadelalorasorete").parseClaimsJws(json).getBody();
             
              String resultado  = gson.toJson(res);
            //OK, we can trust this JWT
            return resultado;
        } catch (JwtException e) {

           return null;
        }
    } 
      
}
