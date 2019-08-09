package se.kth.ocean.medvind.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

public class PolylineMap implements Serializable {

    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "polyline")
    private String polyline;
    @JsonProperty(value = "summary_polyline")
    private String summary_polyline;
    @JsonProperty(value = "resource_state")
    private int resourceState;
    private List<LatLng> decodedPolyline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getSummary_polyline() {
        return summary_polyline;
    }

    public void setSummary_polyline(String summary_polyline) {
        this.summary_polyline = summary_polyline;
    }

    public int getResourceState() {
        return resourceState;
    }

    public void setResourceState(int resourceState) {
        this.resourceState = resourceState;
    }

    public List<LatLng> getDecodedPolyline() {
        return decodedPolyline;
    }

    public void setDecodedPolyline(List<LatLng> decodedPolyline) {
        this.decodedPolyline = decodedPolyline;
    }
}
