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
public class MyWeatherData
{
    public Response response;
    public CurrentObservation current_observation;
}
