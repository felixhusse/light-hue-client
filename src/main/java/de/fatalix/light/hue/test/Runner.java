/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.light.hue.test;

import de.fatalix.light.hue.test.model.Light;
import de.fatalix.light.hue.test.model.LightHueConfiguration;
import de.fatalix.light.hue.test.model.LightHueResponse;
import de.fatalix.light.hue.test.model.LightState;
import de.fatalix.light.hue.test.model.Register;
import de.fatalix.light.hue.test.model.RegisterResponse;
import java.util.List;
import java.util.Map;
import retrofit.RestAdapter;

/**
 *
 * @author Fatalix
 */
public class Runner {
    
    public static final String username = "lightywebapp";
    
    public static void main(String[] args) throws Exception {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://lighthue.fatalix.local")
                .setLog(new LogAdaptor())
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .build();
        LightHueMain lightHueMain = restAdapter.create(LightHueMain.class);
        LightHueLights lightService = restAdapter.create(LightHueLights.class);
        
        LightHueConfiguration lightHueConfiguration = lightHueMain.getLightHueConfiguration(username);
        if (lightHueConfiguration.whitelist == null) {
            Register register = new Register("lighty-web");
            register.username = username;

            List<RegisterResponse> responses = lightHueMain.registerApp(register);
            for (RegisterResponse regResponse : responses) {
                if (regResponse.error != null) {
                    
                    throw new Exception("Register failed! " + regResponse.error.description + "("+regResponse.error.type+")");
                }
                else if (regResponse.success != null){
                    System.out.println("Register succeeded! " + regResponse.success.username);
                }
            }

        }
        Map<String,Light> lights = lightService.getAllLights(username);
        System.out.println("Lights: " + lights.size());
        
        for (String lightId : lights.keySet()) {
            Light light = lightService.getLight(username, lightId);
            System.out.println("Light: " + light.name + " On: " + light.state.on );
            if (light.name.contains("Zimmer ")) {
                
                LightState state = new LightState();
                state.on = !light.state.on;
                state.brightness = 255;
                state.saturation = 255;
                state.hue = 2500;
                
                List<LightHueResponse> lightHueResponses = lightService.changeLight(username, lightId, state);
                for (LightHueResponse resp : lightHueResponses) {
                    if (resp.success!=null) {
                        for (String key : resp.success.keySet()) {
                            System.out.println("Success Key: " + key + "-->" + resp.success.get(key));
                        }    
                    }
                    
                    if (resp.error!=null) {
                        for (String key : resp.error.keySet()) {
                            System.out.println("Error Key: " + key + "-->" + resp.error.get(key));
                        }    
                    }
                }
            }
        }        
    }
    public static class LogAdaptor implements RestAdapter.Log {
        @Override
        public void log(String message) {
            System.out.println("REST: " + message);
        }
    }
    
}
