package se.kth.ocean.medvind.presentation;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.WeatherPoint;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Handles communication with SMHI API.
 */
@Controller
public class WeatherController {

    /**
     * Takes a segment and fetches the weather reports associated with the location of the segment and stores them in weatherPoints.
     * @param segment, segment that is to be updated.
     */
    public void setWeather (Segment segment){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/" +
                segment.getStart_latlng().get(1) + "/lat/" +
                segment.getStart_latlng().get(0) +
                "/data.json";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl , String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<WeatherPoint> weatherPoints = new ArrayList<>();
        WeatherPoint weatherPoint;
        assert root != null;
        JsonNode timeseries = root.path("timeSeries");
        Iterator<JsonNode> iterator = timeseries.elements();
        while (iterator.hasNext()) {
            weatherPoint = new WeatherPoint();
            JsonNode time = iterator.next();
            weatherPoint.setTime(Date.from(Instant.parse(time.path("validTime").asText())));

            Iterator<JsonNode> measurements = time.path("parameters").elements();
            while (measurements.hasNext()) {
                JsonNode measurement = measurements.next();
                switch (measurement.path("name").asText()) {
                    case "t":
                        weatherPoint.setTemp(measurement.path("values").get(0).asDouble());
                        break;
                    case "wd":
                        weatherPoint.setWd(measurement.path("values").get(0).asInt());
                        break;
                    case "ws":
                        weatherPoint.setWs(measurement.path("values").get(0).asDouble());
                        break;
                    case "pmin":
                        weatherPoint.setpMin(measurement.path("values").get(0).asDouble());
                        break;
                    case "pmax":
                        weatherPoint.setpMax(measurement.path("values").get(0).asDouble());
                        break;
                }
            }
            weatherPoints.add(weatherPoint);
        }

        segment.setWeatherPoints(weatherPoints);
    }
}
