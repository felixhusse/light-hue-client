/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.light.hue.test;

import de.fatalix.light.hue.test.model.Light;
import de.fatalix.light.hue.test.model.LightHueResponse;
import de.fatalix.light.hue.test.model.LightState;
import java.util.List;
import java.util.Map;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 *
 * @author Fatalix
 */

public interface LightHueLights {
    
    @GET("/api/{username}/lights")
    Map<String,Light> getAllLights(@Path("username") String username);
    
    @GET("/api/{username}/lights/{id}")
    Light getLight(@Path("username") String username, @Path("id") String id);
    
    @PUT("/api/{username}/lights/{id}/state")
    List<LightHueResponse> changeLight(@Path("username") String username, @Path("id") String id, @Body LightState state);
    
    @PUT("/api/{username}/lights/{id}")
    List<LightHueResponse> changeLightName(@Path("username") String username, @Path("id") String id, @Body Light light);
}
