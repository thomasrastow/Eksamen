package DTOobjects;

/**
 * Created by alanziberi on 21/10/2016.
 */
public class Session {

    private String sessionToken;
    private int userId;
    private int userType;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
