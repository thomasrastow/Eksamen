package Controller;

import DTOobjects.Config;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

/**
 * Created by krist on 17-10-2016.
 */
public class ConfigController {

    public Config getConfig() {

        JSONParser parseJson = new JSONParser();

        Config config = null;

        try {
            Object parseObj = parseJson.parse(new FileReader("resources/config.json"));

            JSONObject jsonObj = (JSONObject) parseObj;

            config = new Config();

            config.setDbType((String) jsonObj.get("DB_TYPE"));
            config.setDbHost((String) jsonObj.get("DB_HOST"));
            config.setDbPort((String) jsonObj.get("DB_PORT"));
            config.setDbName((String) jsonObj.get("DB_NAME"));
            config.setDbUser((String) jsonObj.get("DB_USER"));
            config.setDbPass((String) jsonObj.get("DB_PASS"));
            config.setSrvPort((String) jsonObj.get("SERVER_PORT"));
            config.setSslKey((String) jsonObj.get("SSL_KEY"));
            config.setSslPwd((String) jsonObj.get("SSL_PWD"));

        } catch (Exception e) {
            e.printStackTrace();

        }

        return config;
    }
}

