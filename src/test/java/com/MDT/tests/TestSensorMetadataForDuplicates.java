package com.MDT.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;


public class TestSensorMetadataForDuplicates {


    @DisplayName("FindDuplicateInMetadata")
    @Test

    public void dataComparison() {


        ObjectMapper MetadataList = new ObjectMapper();


        try {


            JsonNode sensorsMetadata = MetadataList.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\sensorMetadata.json"));


            Map<String, ArrayList<Map<String, Object>>> sensorsMetadataMap = MetadataList.convertValue(sensorsMetadata, new TypeReference<Map<String, ArrayList<Map<String, Object>>>>() {
            });


            ArrayList<String> sensorsMetadataNames = new ArrayList<>();

            for (int j = 0; j < sensorsMetadataMap.get("sensors").toArray().length; j++) {
                for (int i = j + 1; i < sensorsMetadataMap.get("sensors").toArray().length; i++)
                    if (sensorsMetadataMap.get("sensors").get(j).get("name").toString().equals(sensorsMetadataMap.get("sensors").get(i).get("name").toString())) {

                        sensorsMetadataNames.add(sensorsMetadataMap.get("sensors").get(j).toString());

                    }

            }

            if(sensorsMetadataNames.size()==0){
                System.out.println("Duplicates not found");
            }else {
                System.out.println("DuplicatesInMetadata = " + sensorsMetadataNames);
            }




        } catch (Exception e) {
            System.out.println("Json file not founded");
        }
    }
}

