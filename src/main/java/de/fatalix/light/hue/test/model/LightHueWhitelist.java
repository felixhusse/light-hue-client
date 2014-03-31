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
public class LightHueWhitelist {
    @SerializedName("last use date") public String lastUseDate;
    @SerializedName("create date") public String createDate;
    public String name;
}
