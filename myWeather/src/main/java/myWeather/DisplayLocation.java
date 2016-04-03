/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Paul Merideth
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayLocation
{
    public String full;
    public String city;
    public String state;
    public String state_name;
    public String country;
    public String country_iso3166;
    public String zip;
    public String magic;
    public String wmo;
    public String latitude;
    public String longitude;
    public String elevation;
}