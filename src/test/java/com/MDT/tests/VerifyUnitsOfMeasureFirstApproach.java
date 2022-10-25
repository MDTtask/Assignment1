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

public class VerifyUnitsOfMeasureFirstApproach {

    @DisplayName("verifyUnitOfMeasure")
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

//
            /**     IN CLOUD - NOT ON PREMISE
             * //Sensors that are in cloudSensorNames but are not in sensorsMetadataNames
             */
            Map<String,String> NotMatchingWithCloudSensor = new HashMap<>();
            Map<String,String> MatchingWithCloudSensor = new HashMap<>();


            for (int k = 0; k < sensorsMetadataMap.get("sensors").toArray().length; k++) {
                for (int i =0; i < allCloudSensorsMap.get("data").get("activeSensors").toArray().length; i++) {
                    if (sensorsMetadataMap.get("sensors").get(k).get("name").toString().equals(allCloudSensorsMap.get("data").get("activeSensors").get(i).get("alias").toString())) {
                      if (!sensorsMetadataMap.get("sensors").get(k).get("unitOfMeasure").toString().equals(  allCloudSensorsMap.get("data").get("activeSensors").get(i).get("uom").toString())){
                          NotMatchingWithCloudSensor.put(sensorsMetadataMap.get("sensors").get(k).get("name").toString(),sensorsMetadataMap.get("sensors").get(k).get("unitOfMeasure").toString() );
                          MatchingWithCloudSensor.put(allCloudSensorsMap.get("data").get("activeSensors").get(i).get("alias").toString(),allCloudSensorsMap.get("data").get("activeSensors").get(i).get("uom").toString() );
                              }
                          }
                      }
                }

            /**
             * //This line prints which sensors are in cloud but not in metadata(on premise)
             */
            System.out.println("Incorrect unitOfMeasure MetadataConfiguration: " + NotMatchingWithCloudSensor);
            System.out.println("Correct unitOfMeasure AllCloudService: " + MatchingWithCloudSensor);

        } catch (Exception e) {
            System.out.println("Json file not founded");
        }
    }
}
