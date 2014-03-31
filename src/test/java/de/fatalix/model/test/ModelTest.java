/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.model.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.fatalix.light.hue.test.model.Light;
import de.fatalix.light.hue.test.model.LightHueResponse;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Fatalix
 */

public class ModelTest {
    
    public String lightsJson = "{\n" +
                            "	\"1\": {\n" +
                            "		\"name\": \"Komische Lampe\"\n" +
                            "	},\n" +
                            "	\"2\": {\n" +
                            "		\"name\": \"WohnzimmerLampe\"\n" +
                            "	},\n" +
                            "	\"3\": {\n" +
                            "		\"name\": \"Badezimmer\"\n" +
                            "	},\n" +
                            "	\"4\": {\n" +
                            "		\"name\": \"Zimmer mitte\"\n" +
                            "	},\n" +
                            "	\"5\": {\n" +
                            "		\"name\": \"Zimmer unten\"\n" +
                            "	},\n" +
                            "	\"6\": {\n" +
                            "		\"name\": \"Zimmer oben\"\n" +
                            "	}\n" +
                            "}";
    
    public String jsonLightResponse ="[\n" +
                                    "	{\n" +
                                    "		\"success\": {\n" +
                                    "			\"/lights/4/state/on\": true\n" +
                                    "		}\n" +
                                    "	},\n" +
                                    "	{\n" +
                                    "		\"success\": {\n" +
                                    "			\"/lights/4/state/hue\": 2500\n" +
                                    "		}\n" +
                                    "	},\n" +
                                    "	{\n" +
                                    "		\"success\": {\n" +
                                    "			\"/lights/4/state/sat\": 255\n" +
                                    "		}\n" +
                                    "	},\n" +
                                    "	{\n" +
                                    "		\"success\": {\n" +
                                    "			\"/lights/4/state/bri\": 255\n" +
                                    "		}\n" +
                                    "	}\n" +
                                    "]";
    
    public String jsonLightResponseError ="[{\"error\":{\"type\":201,\"address\":\"/lights/4/state/hue\",\"description\":\"parameter, hue, is not modifiable. Device is set to off.\"}},{\"error\":{\"type\":201,\"address\":\"/lights/4/state/sat\",\"description\":\"parameter, sat, is not modifiable. Device is set to off.\"}},{\"error\":{\"type\":201,\"address\":\"/lights/4/state/bri\",\"description\":\"parameter, bri, is not modifiable. Device is set to off.\"}},{\"success\":{\"/lights/4/state/on\":false}}]";
    
    @Test
    public void testLights() {
        Gson gson = new Gson();
        Map<String,Light> lights = gson.fromJson(lightsJson, new TypeToken<Map<String,Light>>(){}.getType());
        
        Assert.assertEquals(6, lights.size());
    }
    @Test
    public void testLightResponse() {
        Gson gson = new Gson();
        List<LightHueResponse> lightHueResponses = gson.fromJson(jsonLightResponse, new TypeToken<List<LightHueResponse>>(){}.getType());
        
        Assert.assertEquals(4,lightHueResponses.size());
        Assert.assertNotNull(lightHueResponses.get(0).success);
        Assert.assertTrue(lightHueResponses.get(0).success.containsKey("/lights/4/state/on"));
        LightHueResponse response = lightHueResponses.get(0);
        Assert.assertTrue(Boolean.parseBoolean(response.success.get("/lights/4/state/on")));
    }
    
    @Test
    public void testLightResponseError() {
        Gson gson = new Gson();
        List<LightHueResponse> lightHueResponses = gson.fromJson(jsonLightResponseError, new TypeToken<List<LightHueResponse>>(){}.getType());
        Assert.assertEquals(4,lightHueResponses.size());
        LightHueResponse response = lightHueResponses.get(0);
        Assert.assertNotNull(response.error);
        Assert.assertTrue(response.error.containsKey("type"));
        Assert.assertEquals("201",response.error.get("type"));
        
    }
}
