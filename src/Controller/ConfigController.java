package Controller;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Created by krist on 17-10-2016.
 */
public class ConfigController {

    public static void main (String[]args){

        JSONParser parseJson = new JSONParser();


        Gson gson = new Gson();

        try {
            Object parseObj = parseJson.parse(new FileReader("resources/config.json"));



            JSONObject jsonObj = (JSONObject) parseObj;


            String dbType = (String) jsonObj.get("DB_TYPE");
            String dbHost = (String) jsonObj.get("DB_HOST");
            String dbPort = (String) jsonObj.get("DB_PORT");
            String dbNAME = (String) jsonObj.get("DB_NAME");
            String dbUSER = (String) jsonObj.get("DB_USER");
            String dbPASS = (String) jsonObj.get("DB_PASS");
            String serverPort = (String) jsonObj.get("SERVER_PORT");


            System.out.println(dbType  + "\n" +
                    dbHost + "\n" +
                    dbPort + "\n" +
                    dbNAME + "\n" +
                    dbUSER + "\n" +
                    dbPASS + "\n" +
                    serverPort);

        }catch (Exception e){
            e.printStackTrace();

        }

    }


    }

