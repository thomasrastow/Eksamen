package Controller;

import DTOobjects.User;
import DTOobjects.Session;
import ServiceImplementation.ServiceImplementation;

import java.math.BigInteger;
import java.net.HttpCookie;
import java.security.SecureRandom;

/**
 * Created by alanziberi on 21/10/2016.
 */
public class SessionController {

    ServiceImplementation serviceImpl = new ServiceImplementation();

    public Session getSession (String sessionId) {

        Session session = serviceImpl.getSession(sessionId);

        if(session != null) {
            return session;
        } else {
            return null;
        }
    }

    public boolean createSession (Session session) {

        boolean verifyRequest = serviceImpl.createSession(session);

        if (verifyRequest) {
            return true;
        } else {
            return false;
        }
    }

    public boolean clearSessions(int userId)  {

        boolean verifySession = serviceImpl.clearSessions(userId);

        if (verifySession) {
            return true;
        } else {
            return false;
        }
    }

    public String generateSessionId() {
        SecureRandom secureRandom = new SecureRandom();

        String generatedSessionId = new BigInteger(130, secureRandom).toString(32);

        return generatedSessionId;
    }
}
