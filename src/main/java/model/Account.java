package model;

public class Account {
    private String username, password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTrue() {
        try {
            DataWeb web = new DataWeb(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
