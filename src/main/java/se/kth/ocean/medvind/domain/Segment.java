package se.kth.ocean.medvind.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Segment implements Serializable {

    @JsonProperty(value = "id")
    private long id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "activity_type")
    private String activity_type;
    @JsonProperty(value = "distance")
    private float distance;
    @JsonProperty(value = "avrage_grade")
    private float avrage_grade;
    @JsonProperty(value = "maximum_grade")
    private float maximum_grade;
    @JsonProperty(value = "elevation_high")
    private float elevation_high;
    @JsonProperty(value = "elevation_low")
    private float elevation_low;
    @JsonProperty(value = "start_latlng")
    private ArrayList<Float> start_latlng;
    @JsonProperty(value = "end_latlng")
    private ArrayList<Float> end_latlng;
    @JsonProperty(value = "climb_category")
    private int climb_category;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "country")
    private String country;
    @JsonProperty(value = "total_elevation_gain")
    private float total_elevation_gain;
    @JsonProperty(value = "map")
    private PolylineMap map;
    @JsonProperty(value = "athlete_count")
    private int athlete_count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getAvrage_grade() {
        return avrage_grade;
    }

    public void setAvrage_grade(float avrage_grade) {
        this.avrage_grade = avrage_grade;
    }

    public float getMaximum_grade() {
        return maximum_grade;
    }

    public void setMaximum_grade(float maximum_grade) {
        this.maximum_grade = maximum_grade;
    }

    public float getElevation_high() {
        return elevation_high;
    }

    public void setElevation_high(float elevation_high) {
        this.elevation_high = elevation_high;
    }

    public float getElevation_low() {
        return elevation_low;
    }

    public void setElevation_low(float elevation_low) {
        this.elevation_low = elevation_low;
    }

    public ArrayList<Float> getStart_latlng() {
        return start_latlng;
    }

    public void setStart_latlng(ArrayList<Float> start_latlng) {
        this.start_latlng = start_latlng;
    }

    public ArrayList<Float> getEnd_latlng() {
        return end_latlng;
    }

    public void setEnd_latlng(ArrayList<Float> end_latlng) {
        this.end_latlng = end_latlng;
    }

    public int getClimb_category() {
        return climb_category;
    }

    public void setClimb_category(int climb_category) {
        this.climb_category = climb_category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getTotal_elevation_gain() {
        return total_elevation_gain;
    }

    public void setTotal_elevation_gain(float total_elevation_gain) {
        this.total_elevation_gain = total_elevation_gain;
    }

    public PolylineMap getMap() {
        return map;
    }

    public void setMap(PolylineMap map) {
        this.map = map;
    }

    public int getAthlete_count() {
        return athlete_count;
    }

    public void setAthlete_count(int athlete_count) {
        this.athlete_count = athlete_count;
    }
}
