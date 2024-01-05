package com.gizaSystems.apis.payloads;

public class CreatePetPayload {

    public static String createPet(int petID, String petName, String status) {
        return "{\n" +
                "  \"id\": " + petID + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }
}
