package chatapp;

import org.json.JSONObject;

public class User {
    public String username, password, phone;

    public User(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);
        obj.put("phone", phone);
        return obj;
    }
}
