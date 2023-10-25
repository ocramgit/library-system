public class Person {
    private String name;
    boolean isMember;
    private String username;
    private String password;

    public Person(String name) {
        setName(name);
    }

    public Person(String name, String username, String password) {
        setName(name);
        setUsername(username);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMember() {
        return isMember;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
