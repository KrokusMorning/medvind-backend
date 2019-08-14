package se.kth.ocean.medvind.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestOperations;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;


/**
 * Handles communication with Strava API.
 */
@Controller
public class StravaController {

    @Autowired
    RestOperations stravaRestTemplate;

    /**
     * Calls the Strava api to collect segments starred by authorized user.
     * @return list of Strava Segment.
     */
    public SegmentList segmentsStarred (){

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
