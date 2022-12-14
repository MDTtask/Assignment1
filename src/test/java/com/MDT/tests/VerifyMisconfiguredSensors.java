package com.MDT.tests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class VerifyMisconfiguredSensors {

    @DisplayName("verifySensors")
    @Test

    public void dataComparison() {


        ObjectMapper compare = new ObjectMapper();


        try {

            JsonNode allCloudSensors = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\All Cloud Sensors.json"));//
            JsonNode sensorsMetadata = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\sensorMetadata.json"));


            Map<String, Map<String, ArrayList<Map<String, Object>>>> allCloudSensorsMap = compare.convertValue(allCloudSensors, new TypeReference<Map<String, Map<String, ArrayList<Map<String, Object>>>>>() {
            });

            Map<String, ArrayList<Map<String, Object>>> sensorsMetadataMap = compare.convertValue(sensorsMetadata, new TypeReference<Map<String, ArrayList<Map<String, Object>>>>() {
            });

            /**
             * //Alias of sensors of cloud
             */
            ArrayList<String> cloudSensorNames = new ArrayList<>();
            for (int i = 0; i < allCloudSensorsMap.get("data").get("activeSensors").toArray().length; i++) {
                cloudSensorNames.add(allCloudSensorsMap.get("data").get("activeSensors").get(i).get("alias").toString());
            }
//
            /**
             * //Names of sensors on premise
             */
            ArrayList<String> sensorsMetadataNames = new ArrayList<>();
            for (int j = 0; j < sensorsMetadataMap.get("sensors").toArray().length; j++) {
                sensorsMetadataNames.add(sensorsMetadataMap.get("sensors").get(j).get("name").toString());
            }
//
            /**     IN CLOUD - NOT ON PREMISE
             * //Sensors that are in cloudSensorNames but are not in sensorsMetadataNames
             */
            ArrayList<String> notMatchingSensors = new ArrayList<>();
            for (int k = 0; k < cloudSensorNames.toArray().length; k++) {
                if (!sensorsMetadataNames.equals(cloudSensorNames.get(k))) {
                    notMatchingSensors.add(cloudSensorNames.get(k));
                }
            }
            /**
             * //This line prints which sensors are in cloud but not in metadata(on premise)
             */
            System.out.println("Metadata not registers this sensors: " + notMatchingSensors);

        } catch (Exception e) {
            System.out.println("Json file not founded");
        }
    }
}
