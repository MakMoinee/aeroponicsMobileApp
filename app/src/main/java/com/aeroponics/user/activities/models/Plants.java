package com.aeroponics.user.activities.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Plants {
    private int plantID;
    private String plantType;
    private double temperature;
    private double phLevels;
    private double humidity;
    private String createdAt;

    public Plants(PlantBuilder builder) {
        this.plantID = builder.plantID;
        this.plantType = builder.plantType;
        this.temperature = builder.temperature;
        this.phLevels = builder.phLevels;
        this.humidity = builder.humidity;
        this.createdAt = builder.createdAt;
    }

    public static class PlantBuilder {
        private int plantID;
        private String plantType;
        private double temperature;
        private double phLevels;
        private double humidity;
        private String createdAt;

        public PlantBuilder() {
        }

        public PlantBuilder setPlantID(int plantID) {
            this.plantID = plantID;
            return this;
        }

        public PlantBuilder setPlantType(String plantType) {
            this.plantType = plantType;
            return this;
        }

        public PlantBuilder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public PlantBuilder setPhLevels(double phLevels) {
            this.phLevels = phLevels;
            return this;
        }

        public PlantBuilder setHumidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public PlantBuilder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Plants build() {
            return new Plants(this);
        }


    }
}
