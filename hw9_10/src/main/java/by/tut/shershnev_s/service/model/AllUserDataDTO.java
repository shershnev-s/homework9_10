package by.tut.shershnev_s.service.model;

public class AllUserDataDTO {

    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private int age;
    private String address;
    private String telephone;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getAge() {
        return age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
