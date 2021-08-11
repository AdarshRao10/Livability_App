package com.example.loginactivity;

public class SectionOneHelper{
    float sanitationAvailable,sanitationExpected,waterAvailability,waterExpected,electricityAvailability,electricityExpected,cost_health_public_existing,cost_health_public_expected,cost_health_private_existing,cost_health_private_expected,cost_renting_existing,cost_renting_expected;

    public SectionOneHelper() {

    }


    public SectionOneHelper(float sanitationAvailable,float sanitationExpected, float waterAvailability, float waterExpected, float electricityAvailability, float electricityExpected, float cost_health_public_existing, float cost_health_public_expected, float cost_health_private_existing, float cost_health_private_expected, float cost_renting_existing, float cost_renting_expected) {
        this.sanitationAvailable = sanitationAvailable;
        this.sanitationExpected = sanitationExpected;
        this.waterAvailability = waterAvailability;
        this.waterExpected = waterExpected;
        this.electricityAvailability = electricityAvailability;
        this.electricityExpected = electricityExpected;
        this.cost_health_public_existing = cost_health_public_existing;
        this.cost_health_public_expected = cost_health_public_expected;
        this.cost_health_private_existing = cost_health_private_existing;
        this.cost_health_private_expected = cost_health_private_expected;
        this.cost_renting_existing = cost_renting_existing;
        this.cost_renting_expected = cost_renting_expected;
    }

    public float getSanitationAvailable() {
        return sanitationAvailable;
    }

    public void setSanitationAvailable(float sanitationAvailable) {
        this.sanitationAvailable = sanitationAvailable;
    }

    public float getSanitationExpected() {
        return sanitationExpected;
    }

    public void setSanitationExpected(float sanitationExpected) {
        this.sanitationExpected = sanitationExpected;
    }

    public float getWaterAvailability() {
        return waterAvailability;
    }

    public void setWaterAvailability(float waterAvailability) {
        this.waterAvailability = waterAvailability;
    }

    public float getWaterExpected() {
        return waterExpected;
    }

    public void setWaterExpected(float waterExpected) {
        this.waterExpected = waterExpected;
    }

    public float getElectricityAvailability() {
        return electricityAvailability;
    }

    public void setElectricityAvailability(float electricityAvailability) {
        this.electricityAvailability = electricityAvailability;
    }

    public float getElectricityExpected() {
        return electricityExpected;
    }

    public void setElectricityExpected(float electricityExpected) {
        this.electricityExpected = electricityExpected;
    }

    public float getCost_health_public_existing() {
        return cost_health_public_existing;
    }

    public void setCost_health_public_existing(float cost_health_public_existing) {
        this.cost_health_public_existing = cost_health_public_existing;
    }

    public float getCost_health_public_expected() {
        return cost_health_public_expected;
    }

    public void setCost_health_public_expected(float cost_health_public_expected) {
        this.cost_health_public_expected = cost_health_public_expected;
    }

    public float getCost_health_private_existing() {
        return cost_health_private_existing;
    }

    public void setCost_health_private_existing(float cost_health_private_existing) {
        this.cost_health_private_existing = cost_health_private_existing;
    }

    public float getCost_health_private_expected() {
        return cost_health_private_expected;
    }

    public void setCost_health_private_expected(float cost_health_private_expected) {
        this.cost_health_private_expected = cost_health_private_expected;
    }

    public float getCost_renting_existing() {
        return cost_renting_existing;
    }

    public void setCost_renting_existing(float cost_renting_existing) {
        this.cost_renting_existing = cost_renting_existing;
    }

    public float getCost_renting_expected() {
        return cost_renting_expected;
    }

    public void setCost_renting_expected(float cost_renting_expected) {
        this.cost_renting_expected = cost_renting_expected;
    }
}
