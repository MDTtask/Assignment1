package com.MDT.tests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MDTtest{
    
@DisplayName("dataCompare")
@Test

public void dataComparison(){


    ObjectMapper compare = new ObjectMapper();


        try{

            JsonNode allCloudSensors = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\All Cloud Sensors.json"));

            JsonNode sensorsMetadata = compare.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\sensorMetadata.json"));


            Map<String, Map<String, Object>> allCloudSensorsMap = compare.convertValue(allCloudSensors, new TypeReference<Map<String, Map<String, Object>>>(){});

           //Map<String, ?> allCloudSensorsMap = compare.convertValue(allCloudSensors, new TypeReference<Map<String,?>>(){});
            Map<String, ?> sensorsMetadataMap = compare.convertValue(sensorsMetadata, new TypeReference<Map<String,?>>(){});

            /*for (String each: sensorsMetadataMap.keySet()) {
                

                for (Object value: sensorsMetadataMap.values().toArray())
                      {
                          System.out.println("value = " + value);
                          for (Object sensors: allCloudSensorsMap.values()
                               ) {
                              System.out.println(sensors);
                          }
                      }


            }*/

            for (Object entry : sensorsMetadataMap.values()){
                /*System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());*/

                for (Map.Entry<String, String> nameSensor : ((Map<String,?>) entry.)){
                    System.out.println("Key = " + nameSensor.getKey() +
                            ", Value = " + nameSensor.getValue());
                    // how read array inside a map
                }


            }



        }catch (Exception e){
            System.out.println("Json file not founded");
        }
    }
}





   /* public List<String> getKeysInJsonUsingJsonNodeFieldNames(String json, ObjectMapper mapper) throws JsonMappingException, JsonProcessingException {

        List<String> keys = new ArrayList<>();
        JsonNode jsonNode = mapper.readTree(json);
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> keys.add(e));
        return keys;*/



