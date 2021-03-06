package accounts;

import dataSets.UsersDataSet;
import dbService.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private DBService dbService;
    private Map<String, UserProfile> loginToProfile;
    private Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        dbService = new DBService();
    }


    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        dbService.addUser(userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        if (loginToProfile.get(login) != null) {
            return loginToProfile.get(login);
        } else {
            UsersDataSet user = dbService.getUser(login);
            return new UserProfile(user.getName(), user.getPassword(), user.getEmail());
        }

    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
