package se.kth.ocean.medvind.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import se.kth.ocean.medvind.application.SegmentService;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;


/**
 * Handles communication with Strava API.
 */
@RestController
public class StravaController {

    @Autowired
    SegmentService segmentService;

    @Autowired
    WeatherController weatherController;

    @Autowired
    RestOperations stravaRestTemplate;

    /**
     * Calls other methods to fetch segments and generate metadata.
     *
     * @return list of segments with updated metadata.
     */
    @GetMapping("/segments")
    public SegmentList segments (){
        return segmentService.setSegmentMetaData(segmentsStarred());
    }

    /**
     * Calls the Strava api to collect segments starred by authorized user.
     * @return list of Strava Segment.
     */
    private SegmentList segmentsStarred (){

        return stravaRestTemplate
                .getForObject("https://www.strava.com/api/v3/segments/starred", SegmentList.class);
    }

    /**
     * Calls the Strava api to collect segment by id.
     * @return Strava segment.
     */
    public Segment segmentById (@PathVariable long id){

        return stravaRestTemplate
                .getForObject("https://www.strava.com/api/v3/segments/" + id, Segment.class);
    }

}
