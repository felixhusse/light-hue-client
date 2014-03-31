/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.light.hue.test;

import de.fatalix.light.hue.test.model.LightHueConfiguration;
import de.fatalix.light.hue.test.model.Register;
import de.fatalix.light.hue.test.model.RegisterResponse;
import java.util.List;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Main resources for managing your lighthue system
 * @author Fatalix
 */
public interface LightHueMain {
    
    @POST("/api")
    List<RegisterResponse> registerApp(@Body Register register);
    
    @GET("/api/{username}/config")
    LightHueConfiguration getLightHueConfiguration(@Path("username") String username);
    
    
}
