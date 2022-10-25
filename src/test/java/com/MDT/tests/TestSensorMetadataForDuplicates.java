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

    // need loop through and print unique
    @DisplayName("FindDuplicateInMetadata")
    @Test

    public void dataComparison() {


        ObjectMapper MetadataList = new ObjectMapper();


        try {


            JsonNode sensorsMetadata = MetadataList.readTree(new File("C:\\Users\\SdetArt\\IdeaProjects\\MDT\\src\\test\\resources\\sensorMetadata.json"));


            Map<String, ArrayList<Map<String, Object>>> sensorsMetadataMap = MetadataList.convertValue(sensorsMetadata, new TypeReference<Map<String, ArrayList<Map<String, Object>>>>() {});



            ArrayList<String> sensorsMetadataNames = new ArrayList<>();

            for (int j = 0; j < sensorsMetadataMap.get("sensors").toArray().length; j++) {
                for (int i = j+1; i < sensorsMetadataNames.toArray().length; i++)
                    if (sensorsMetadataNames.get(j) == (sensorsMetadataNames.get(i))) {

                        sensorsMetadataNames.add(sensorsMetadataMap.get("sensors").get(j).toString());

                    } System.out.println("sensorsMetadataNames = " + sensorsMetadataNames);


            }




        } catch (Exception e) {
            System.out.println("Json file not founded");
        }
    }
}

