package AddOn;

public class User {
    private int id;
    private String username;
    private double total_biaya;

    public User() {
        this.id = 0;
        this.username = "";
        this.total_biaya = 0;
    }
    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(int id, String username, double total_biaya) {
        this.id = id;
        this.username = username;
        this.total_biaya = total_biaya;
    }

    public double getTotal_biaya() {
        return total_biaya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setTotal_biaya(double total_biaya) {
        this.total_biaya = total_biaya;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
