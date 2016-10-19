package Controller;


import DTOobjects.Book;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by krist on 17-10-2016.
 */
public class ConfigController {

    public static final String JSON_FILE="resources/config.json";

    public static void main(String[] args) throws IOExcepception 

    InputStream fis = new FileInputStream(JSON_FILE);

    JsonReader jsonReader = JsonReader.createReader(fis);



    private String DB_TYPE;
    private String DB_HOST;
    private String DB_PORT;
    private String DB_NAME;
    private String DB_USER;
    private String DB_PASS;
    private String COURSES_JSON;
    private String HASH_SALT;
    private String ENCRYPT_KEY;
    private String SERVER_PORT;
    private String DEBUG;

    public ConfigController() {




    tion{

        }
    }


    public String getDB_TYPE() {
        return DB_TYPE;
    }

    public void setDB_TYPE(String DB_TYPE) {
        this.DB_TYPE = DB_TYPE;
    }

    public String getDB_HOST() {
        return DB_HOST;
    }

    public void setDB_HOST(String DB_HOST) {
        this.DB_HOST = DB_HOST;
    }

    public String getDB_PORT() {
        return DB_PORT;
    }

    public void setDB_PORT(String DB_PORT) {
        this.DB_PORT = DB_PORT;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public void setDB_NAME(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }

    public String getDB_PASS() {
        return DB_PASS;
    }

    public void setDB_PASS(String DB_PASS) {
        this.DB_PASS = DB_PASS;
    }

    public String getCOURSES_JSON() {
        return COURSES_JSON;
    }

    public void setCOURSES_JSON(String COURSES_JSON) {
        this.COURSES_JSON = COURSES_JSON;
    }

    public String getHASH_SALT() {
        return HASH_SALT;
    }

    public void setHASH_SALT(String HASH_SALT) {
        this.HASH_SALT = HASH_SALT;
    }

    public String getENCRYPT_KEY() {
        return ENCRYPT_KEY;
    }

    public void setENCRYPT_KEY(String ENCRYPT_KEY) {
        this.ENCRYPT_KEY = ENCRYPT_KEY;
    }

    public String getSERVER_PORT() {
        return SERVER_PORT;
    }

    public void setSERVER_PORT(String SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;
    }

    public String getDEBUG() {
        return DEBUG;
    }

    public void setDEBUG(String DEBUG) {
        this.DEBUG = DEBUG;
    }

    /*  private String DB_TYPE;
            public String getDBTYPE() { return this.DB_TYPE; }
            public void setDBTYPE(String DB_TYPE) { this.DB_TYPE = DB_TYPE; }


            private String DB_HOST;
            public String getDBHOST() { return this.DB_HOST; }
            public void setDBHOST(String DB_HOST) { this.DB_HOST = DB_HOST; }

            private String DB_PORT;
            public String getDBPORT() { return this.DB_PORT; }
            public void setDBPORT(String DB_PORT) { this.DB_PORT = DB_PORT; }

            private String DB_NAME;
            public String getDBNAME() { return this.DB_NAME; }
            public void setDBNAME(String DB_NAME) { this.DB_NAME = DB_NAME; }

            private String DB_USER;
            public String getDBUSER() { return this.DB_USER; }
            public void setDBUSER(String DB_USER) { this.DB_USER = DB_USER; }

            private String DB_PASS;
            public String getDBPASS() { return this.DB_PASS; }
            public void setDBPASS(String DB_PASS) { this.DB_PASS = DB_PASS; }

            private String COURSES_JSON;
            public String getCOURSESJSON() { return this.COURSES_JSON; }
            public void setCOURSESJSON(String COURSES_JSON) { this.COURSES_JSON = COURSES_JSON; }

            private String HASH_SALT;
            public String getHASHSALT() { return this.HASH_SALT; }
            public void setHASHSALT(String HASH_SALT) { this.HASH_SALT = HASH_SALT; }

            private String ENCRYPT_KEY;
            public String getENCRYPTKEY() { return this.ENCRYPT_KEY; }

            public void setENCRYPTKEY(String ENCRYPT_KEY) { this.ENCRYPT_KEY = ENCRYPT_KEY; }

            private String SERVER_PORT;
            public String getSERVERPORT() { return this.SERVER_PORT; }
            public void setSERVERPORT(String SERVER_PORT) { this.SERVER_PORT = SERVER_PORT; }

            private String DEBUG;
            public String getDEBUG() { return this.DEBUG; }
            public void setDEBUG(String DEBUG) { this.DEBUG = DEBUG; }
    */
   // public void configParser() {

       //    public static void main(String[] args){
        //    Gson gson = new GsonBuilder().create();
        //    gson.toJson(getDB_TYPE, System.out);
            //   gson.toJson(123, System.out);
        }

            //BuffedReader br = new BuffedReader()

       // JsonObject object = Json.parse(input).asObject();
       // String dB_TYPE = object.get("DB_USER").getAsString();
        // JsonArray items;

        /*try {
            JsonReader reader = new JsonReader(new FileReader("resources/config.json"));
            Book bookAray = gson.fromJson(reader, Book[].class);
        } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
        }*/
    }
   // }

