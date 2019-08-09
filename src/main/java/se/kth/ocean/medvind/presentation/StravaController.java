package se.kth.ocean.medvind.presentation;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import se.kth.ocean.medvind.application.StravaService;
import se.kth.ocean.medvind.domain.AthleteProfile;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class StravaController {

    @Autowired
    StravaService stravaService;

    @Resource(name = "stravaRestTemplate")
    private RestOperations stravaRestTemplate;

    @GetMapping("/segments")
    public SegmentList segments (){

        return stravaService.setMaps(segmentsStarred());
    }

    @GetMapping("/segments/starred")
    public SegmentList segmentsStarred (){

        return stravaRestTemplate
                .getForObject("https://www.strava.com/api/v3/segments/starred", SegmentList.class);
    }

    @GetMapping("/segments/{id}")
    public Segment segmentById (@PathVariable long id){

        return stravaRestTemplate
                .getForObject("https://www.strava.com/api/v3/segments/" + id, Segment.class);
    }

    public void setStravaRestTemplate(OAuth2RestTemplate stravaRestTemplate) {
        this.stravaRestTemplate = stravaRestTemplate;
    }

}
