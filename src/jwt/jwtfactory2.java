/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 *
 * @author 40230691.858
 */
public class jwtfactory2 {

    public static String obtenerToken(Usuario u) throws IllegalArgumentException, UnsupportedEncodingException {
        String token;
        Gson gson = new Gson();
        String json = gson.toJson(u);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date expireDate = new Date(nowMillis + 10000000);

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withClaim("usuario", json)
                    .withExpiresAt(expireDate)
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return exception.getMessage();
        }
        return token;
    }

    public static DecodedJWT verificarToken(String token) throws IllegalArgumentException, UnsupportedEncodingException {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    
     public static boolean tokenValido(String token) throws IllegalArgumentException, UnsupportedEncodingException {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}
