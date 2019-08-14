package se.kth.ocean.medvind.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.ocean.medvind.domain.Segment;
import se.kth.ocean.medvind.domain.SegmentList;
import se.kth.ocean.medvind.domain.WeatherPoint;
import se.kth.ocean.medvind.presentation.StravaController;
import se.kth.ocean.medvind.presentation.WeatherController;

import java.util.*;


/**
 * Handles logic concerning segments.
 */
@Service
public class SegmentService {

    @Autowired
    StravaController stravaController;

    @Autowired
    WeatherController weatherController;

    /**
     * Updates a list of segments with various metadata.
     * @param segmentList, list of segments that is to get its metadata.
     * @return list of segments with updated metadata.
     */
    public SegmentList setSegmentMetaData(SegmentList segmentList){
        for(Segment segment : segmentList){
            //segment.setMap(stravaController.segmentById(segment.getId()).getMap());
            //segment.getMap().setDecodedPolyline(PolylineEncoding.decode(segment.getMap().getPolyline()));
            weatherController.setWeather(segment);
            segment.setDirectionDegrees((int) calculateBearing(segment.getStart_latlng(), segment.getEnd_latlng()));
            segment.setStartToEndDistance(calculateDistance(segment.getStart_latlng(), segment.getEnd_latlng()));
            setWeatherPointsMetaData(segment);
            sortWeatherPoints((List<WeatherPoint>) segment.getWeatherPoints());
        }

        sortSegments(segmentList);
        return segmentList;
    }

    /**
     * Calculates the bearing in degrees between two lat/lang points.
     * @param startLatlng, starting point
     * @param endLatlng, end point
     * @return a double value resembling the bearing
     */
    private double calculateBearing(List<Float> startLatlng, List<Float> endLatlng) {
        double lat2 = startLatlng.get(0);
        double lng2 = startLatlng.get(1);
        double lat1 = endLatlng.get(0);
        double lng1 = endLatlng.get(1);

        double dLon = (lng2-lng1);
        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1)*Math.sin(lat2) - Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
        double bearing = Math.toDegrees((Math.atan2(y, x)));
        bearing = (360 - ((bearing + 360) % 360));

        return bearing;
    }

    /**
     * Calculates the distance in degrees between two lat/lang points.
     *
     * @param startLatlng, starting point
     * @param endLatlng, end point
     * @return a float number resembling the distance between two points.
     */
    private float calculateDistance(List<Float> startLatlng, List<Float> endLatlng) {
        double lat1 = startLatlng.get(0);
        double lng1 = startLatlng.get(1);
        double lat2 = endLatlng.get(0);
        double lng2 = endLatlng.get(1);

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    /**
     * Iterates trough all weatherpoints of a segment and updates weatherpoint metadata
     * @param segment segment with weatherPoints to be updated.
     */
    private void setWeatherPointsMetaData(Segment segment){
        for(WeatherPoint weatherPoint : segment.getWeatherPoints()){
            calculateDirectionMatch(segment, weatherPoint);
            calculateRating(segment, weatherPoint);

        }
    }

    /**
     * Generates a score resembling how matching the wind direction is to the start/end bearing(directionDegrees).
     * @param segment, examined segment
     * @param weatherPoint, examined weatherPoint
     */
    private void calculateDirectionMatch(Segment segment, WeatherPoint weatherPoint){
        int directionMatch = 0;
        directionMatch = angleDistance(segment.getDirectionDegrees(), weatherPoint.getWd());
        directionMatch = 180 - directionMatch;
        weatherPoint.setDirectionMatch(directionMatch);
    }

    /**
     * Generates a rating of how good the conditions is for a segment.
     * @param segment, examined segment
     * @param weatherPoint, examined weatherPoint
     */
    private void calculateRating(Segment segment, WeatherPoint weatherPoint){
        double rating = 0;
        rating = weatherPoint.getWs() * weatherPoint.getDirectionMatch() * (segment.getStartToEndDistance() / segment.getDistance());
        weatherPoint.setRating(rating);
    }

    /**
     * Calculates distance between two angles
     * @param alpha angle one
     * @param beta angle two
     * @return angle distance
     */
    private static int angleDistance(int alpha, int beta) {
        int phi = Math.abs(beta - alpha) % 360;
        int distance = phi > 180 ? 360 - phi : phi;
        return distance;
    }

    private void sortWeatherPoints(List<WeatherPoint> weatherPoints){
        weatherPoints.sort(Comparator.comparing(WeatherPoint::getRating).reversed());
    }

    private void sortSegments(List<Segment> segments){
        segments.sort(Comparator.comparing(Segment::getBestWeatherPointMatch).reversed());
    }


}
