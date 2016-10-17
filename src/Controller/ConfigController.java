package Controller;



/**
 * Created by krist on 17-10-2016.
 */
public class ConfigController {


        private String DB_TYPE;
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

    public void configParser() {

    }
    }

