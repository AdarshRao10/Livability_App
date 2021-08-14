package com.mylivability.loginactivity;

public class SectionFourHelper {

    float publicFacilitiesAvailability,publicFacilitiesExpected,publicEntertainmentUtilitiesAvailability,publicEntertaimnentUtilitiesExpected, networkSpeedAvailable,networkSpeedExpected;


    public SectionFourHelper() {
    }

    public SectionFourHelper(float publicFacilitiesAvailability, float publicFacilitiesExpected, float publicEntertainmentUtilitiesAvailability, float publicEntertaimnentUtilitiesExpected, float networkSpeedAvailable, float networkSpeedExpected) {
        this.publicFacilitiesAvailability = publicFacilitiesAvailability;
        this.publicFacilitiesExpected = publicFacilitiesExpected;
        this.publicEntertainmentUtilitiesAvailability = publicEntertainmentUtilitiesAvailability;
        this.publicEntertaimnentUtilitiesExpected = publicEntertaimnentUtilitiesExpected;
        this.networkSpeedAvailable = networkSpeedAvailable;
        this.networkSpeedExpected = networkSpeedExpected;
    }

    public float getPublicFacilitiesAvailability() {
        return publicFacilitiesAvailability;
    }

    public void setPublicFacilitiesAvailability(float publicFacilitiesAvailability) {
        this.publicFacilitiesAvailability = publicFacilitiesAvailability;
    }

    public float getPublicFacilitiesExpected() {
        return publicFacilitiesExpected;
    }

    public void setPublicFacilitiesExpected(float publicFacilitiesExpected) {
        this.publicFacilitiesExpected = publicFacilitiesExpected;
    }

    public float getPublicEntertainmentUtilitiesAvailability() {
        return publicEntertainmentUtilitiesAvailability;
    }

    public void setPublicEntertainmentUtilitiesAvailability(float publicEntertainmentUtilitiesAvailability) {
        this.publicEntertainmentUtilitiesAvailability = publicEntertainmentUtilitiesAvailability;
    }

    public float getPublicEntertaimnentUtilitiesExpected() {
        return publicEntertaimnentUtilitiesExpected;
    }

    public void setPublicEntertaimnentUtilitiesExpected(float publicEntertaimnentUtilitiesExpected) {
        this.publicEntertaimnentUtilitiesExpected = publicEntertaimnentUtilitiesExpected;
    }

    public float getNetworkSpeedAvailable() {
        return networkSpeedAvailable;
    }

    public void setNetworkSpeedAvailable(float networkSpeedAvailable) {
        this.networkSpeedAvailable = networkSpeedAvailable;
    }

    public float getNetworkSpeedExpected() {
        return networkSpeedExpected;
    }

    public void setNetworkSpeedExpected(float networkSpeedExpected) {
        this.networkSpeedExpected = networkSpeedExpected;
    }
}
