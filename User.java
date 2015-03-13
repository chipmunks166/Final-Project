public class User
{
    String username, password;
    boolean privilege;
    public User(String username, String password, boolean privilege)
    {
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public boolean isAdmin()
    {
        return privilege;
    }
    
}