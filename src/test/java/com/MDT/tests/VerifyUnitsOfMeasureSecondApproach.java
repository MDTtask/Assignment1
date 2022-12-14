package com.MDT.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerifyUnitsOfMeasureSecondApproach {

    @DisplayName("verifyUnitsOfMeasure")
    @Test

    public void dataComparison() {


        ObjectMapper compare = new ObjectMapper();


        try {

            JsonNode allCloudSensors = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\All Cloud Sensors.json"));

            JsonNode sensorsMetadata = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\sensorMetadata.json"));


            Map<String, Map<String, ArrayList<Map<String, Object>>>> allCloudSensorsMap = compare.convertValue(allCloudSensors, new TypeReference<Map<String, Map<String, ArrayList<Map<String, Object>>>>>() {
            });

            Map<String, ArrayList<Map<String, Object>>> sensorsMetadataMap = compare.convertValue(sensorsMetadata, new TypeReference<Map<String, ArrayList<Map<String, Object>>>>() {
            });

            /**
             * //Names of uom of cloud
             */
            Map<String, String> cloudSensorNames = new HashMap<>();
            for (int i = 0; i < allCloudSensorsMap.get("data").get("activeSensors").toArray().length; i++) {

                cloudSensorNames.put(allCloudSensorsMap.get("data").get("activeSensors").get(i).get("alias").toString(), allCloudSensors.get("data").get("activeSensors").get(i).get("uom").toString().substring(1, allCloudSensors.get("data").get("activeSensors").get(i).get("uom").toString().length() - 1));

            }

            /**
             * //Names of sensors on premise
             */
            Map<String, String> sensorsMetadataNames = new HashMap<>();
            for (int j = 0; j < sensorsMetadataMap.get("sensors").toArray().length; j++) {
                sensorsMetadataNames.put(sensorsMetadataMap.get("sensors").get(j).get("name").toString(), sensorsMetadataMap.get("sensors").get(j).get("unitOfMeasure").toString());

            }
            /**     ON PREMISE - NOT IN CLOUD to Correct
             * //Sensors that are in sensorsMetadataNames but are not in cloudSensorNames
             */
            Map<String, String> UnitMissingConfiguration = new HashMap<>();
            Map<String, String> UnitConfigurationInCloud = new HashMap<>();

            for (Map.Entry<String, String> allCloudSensor : cloudSensorNames.entrySet()) {
                for (Map.Entry<String, String> sensorMethadata : sensorsMetadataNames.entrySet()) {
                    if (allCloudSensor.getKey().equals(sensorMethadata.getKey()) && !allCloudSensor.getValue().equals(sensorMethadata.getValue())) {
                        UnitMissingConfiguration.put(allCloudSensor.getKey(), allCloudSensor.getValue());
                        UnitConfigurationInCloud.put(sensorMethadata.getKey(), sensorMethadata.getValue());
                    }
                }
            }
            System.out.println("Units of measure correct" + UnitMissingConfiguration);
            System.out.println("Units of measure NOT correct" + UnitConfigurationInCloud);


        } catch (Exception e) {
            System.out.println("Json file not founded");
        }
    }
}
