/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fatalix.light.hue.test.model;

import java.util.Map;

/**
 *
 * @author Fatalix
 */
public class LightHueConfiguration {
    public String name;
    public String mac;
    public boolean dhcp;
    public String ipaddress;
    public String netmask;
    public String gateway;
    public String proxyaddress;
    public String UTC;
    public String localtime;
    public String timezone;
    public String swversion;
    public String apiversion;
    public boolean linkbutton;
    public Map<String,LightHueWhitelist> whitelist;
}
