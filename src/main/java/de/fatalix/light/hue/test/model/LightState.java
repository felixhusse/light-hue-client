/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.light.hue.test.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Fatalix
 */
public class LightState {
    
    public boolean on;
    @SerializedName("bri") public int brightness;
    @SerializedName("hue") public int hue;
    @SerializedName("sat") public int saturation;
    
    public String alert;
    public String effect;
    //public boolean reachable;
    //public int transationtime;
    
}
