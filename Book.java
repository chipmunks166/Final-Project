public class Book
{
    private String Title, authorLastName, authorFirstName;
    boolean Availability;
    public Book (String Title, String authorLastName, String authorFirstName, boolean Availability)
    {
        this.Title = Title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.Availability = Availability;
    }
    
    public String getTitle()
    {
        return Title;
    }
    
    public String getAuthorLastName()
    {
        return authorLastName;
    }
    
    public String getAuthorFirstName()
    {
        return authorFirstName;
    }
    
    public boolean getAvailability()
    {
        return Availability;
    }

    public String toString()
    {
        return Title + "\t" + authorLastName + "\t" + authorFirstName + "\t" + Availability;
    }
}