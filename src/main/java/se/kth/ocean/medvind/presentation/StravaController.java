package se.kth.ocean.medvind.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import se.kth.ocean.medvind.application.StravaService;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;

import javax.annotation.Resource;

@RestController
public class StravaController {

    @Autowired
    StravaService stravaService;

    @Autowired
    WeatherController weatherController;

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
