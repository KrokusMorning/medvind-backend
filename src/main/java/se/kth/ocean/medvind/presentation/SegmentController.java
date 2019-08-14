package se.kth.ocean.medvind.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.ocean.medvind.application.SegmentService;
import se.kth.ocean.medvind.domain.SegmentList;

/**
 * Responds to segment requests.
 */
@RestController
public class SegmentController {

    @Autowired
    SegmentService segmentService;

    @Autowired
    WeatherController weatherController;

    @Autowired
    StravaController stravaController;

    /**
     * Calls other methods to fetch segments and generate metadata.
     *
     * @return list of segments with updated metadata.
     */
    @GetMapping("/segments")
    public SegmentList segments (){
        return segmentService.setSegmentMetaData(stravaController.segmentsStarred());
    }
}
