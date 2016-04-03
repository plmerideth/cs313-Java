/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Paul Merideth
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentObservation
{
    public Image image;
    public DisplayLocation display_location;
    public ObservationLocation observation_location;
    public Estimated estimated;
    public String station_id;
    public String observation_time;
    public String observation_time_rfc822;
    public String observation_epoch;
    public String local_time_rfc822;
    public String local_epoch;
    public String local_tz_short;
    public String local_tz_long;
    public String local_tz_offset;
    public String weather;
    public String temperature_string;
    public double temp_f;
    public double temp_c;
    public String relative_humidity;
    public String wind_string;
    public String wind_dir;
    public int wind_degrees;
    public double wind_mph;
    public String wind_gust_mph;
    public double wind_kph;
    public String wind_gust_kph;
    public String pressure_mb;
    public String pressure_in;
    public String pressure_trend;
    public String dewpoint_string;
    public int dewpoint_f;
    public int dewpoint_c;
    public String heat_index_string;
    public String heat_index_f;
    public String heat_index_c;
    public String windchill_string;
    public String windchill_f;
    public String windchill_c;
    public String feelslike_string;
    public String feelslike_f;
    public String feelslike_c;
    public String visibility_mi;
    public String visibility_km;
    public String solarradiation;    
    @JsonProperty("UV")    
    public String uV;
    public String precip_1hr_string;
    public String precip_1hr_in;
    public String precip_1hr_metric;
    public String precip_today_string;
    public String precip_today_in;
    public String precip_today_metric;
    public String icon;
    public String icon_url;
    public String forecast_url;
    public String history_url;
    public String ob_url;
    public String nowcast;
}
