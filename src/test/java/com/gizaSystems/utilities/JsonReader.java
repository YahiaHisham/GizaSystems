package com.gizaSystems.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JsonReader {
    static String classLocation = "/src/test/java/com/gizaSystems/data/jsonFiles/";
    static String fileExtension = ".json";
    static String projectLocation = "user.dir";
    static String greenBoldColor = "\u001B[32;1m";
    static String resetColor = "\u001B[0m";
    static String greenSuccessMessage = greenBoldColor + "Success! " + resetColor;

    private static String filePath;
    private static JSONObject jsonObject;

    private static void loadJson() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getValueFromJsonFile(String key, String fileName) {
        setFilePath(fileName);
        loadJson();
        Object value = jsonObject.get(key);
        System.out.println("Getting the value of " + key + "...");
        if (value != null) {
            System.out.println(greenSuccessMessage + "Successfully got the key '" + key + "' value ");
            return value.toString();
        } else {
            return "Can not find the Key '" + key + "' in the JSON file.";
        }
    }

    public static void updateValueInJsonFile(String key, Object value, String fileName) {
        setFilePath(fileName);
        loadJson();
        jsonObject.put(key, value);
        saveChanges();
        System.out.println(greenSuccessMessage + "Successfully updated the key '" + key + "' value to be '" + value + "'");
        formatJsonFile(filePath);
    }

    private static void saveChanges() {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method is to format the json file after every update to be human-readable
     *
     * @param fileName the file you want to format
     */
    public static void formatJsonFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            Map<String, Object> jsonMap = objectMapper.readValue(new File(fileName), Map.class);
            objectMapper.writeValue(new File(fileName), jsonMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFilePath(String fileName) {
        filePath = System.getProperty(projectLocation) + classLocation + fileName + fileExtension;
    }
}
