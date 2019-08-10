package se.kth.ocean.medvind.application;

import com.google.maps.internal.PolylineEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;
import se.kth.ocean.medvind.presentation.StravaController;
import se.kth.ocean.medvind.presentation.WeatherController;


@Service
public class StravaService {

    @Autowired
    StravaController stravaController;

    @Autowired
    WeatherController weatherController;

    public SegmentList setMaps (SegmentList segmentList){
        for(Segment segment : segmentList){
            segment.setMap(stravaController.segmentById(segment.getId()).getMap());
            segment.getMap().setDecodedPolyline(PolylineEncoding.decode(segment.getMap().getPolyline()));
            weatherController.setWeather(segment);
        }
        return segmentList;
    }



}
