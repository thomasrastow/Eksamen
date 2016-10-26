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
            Object parseObject = parseJson.parse(new FileReader("resources/config.json"));

            JSONObject jsonObject = (JSONObject) parseObject;

            config = new Config();

            config.setDbType((String) jsonObject.get("DB_TYPE"));
            config.setDbHost((String) jsonObject.get("DB_HOST"));
            config.setDbPort((String) jsonObject.get("DB_PORT"));
            config.setDbName((String) jsonObject.get("DB_NAME"));
            config.setDbUser((String) jsonObject.get("DB_USER"));
            config.setDbPass((String) jsonObject.get("DB_PASS"));
            config.setSrvPort((String) jsonObject.get("SERVER_PORT"));
            config.setSslKey((String) jsonObject.get("SSL_KEY"));
            config.setSslPwd((String) jsonObject.get("SSL_PWD"));
            config.setAllowOrigin((String) jsonObject.get("ALLOW_ORIGIN"));


        } catch (Exception e) {
            e.printStackTrace();

        }

        return config;
    }
}

