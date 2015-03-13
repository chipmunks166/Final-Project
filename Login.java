import java.util.*;
import java.io.*;
public class Login
{
    private ArrayList<User> userDatabase = new ArrayList<User>();
    private final File USER_LIST_PATH = new File("loginInfo.txt");
    private final String USER_LIST_TITLE = "USERNAME | PASSWORD | PRIVILEGES";
    public void login() 
    {
        updateDatabase();

        logIn();
    }

    public void logIn()
    {
        String username, password;
        User targetUser = null;
        boolean isFound = false;
        int choice = 0;

        System.out.println("Choose one:");
        System.out.println("(1)Login");
        System.out.println("(2)Register User");

        choice = Keyboard.readInt();

        if(choice == 1)
        {
            System.out.println("***********LOGIN***********");
            System.out.print("Username: ");
            username = Keyboard.readString();
            System.out.print("Password: ");
            password = Keyboard.readString();

            for(int i = 0; i < userDatabase.size(); i ++)
            {
                if(username.equals(userDatabase.get(i).getUsername()) && password.equals(userDatabase.get(i).getPassword()))
                {
                    targetUser = userDatabase.get(i);
                    isFound = true;
                }            
            }

            if(isFound == true)
            {
                if(targetUser.isAdmin() == true)
                    adminLogIn();
                else
                    userLogIn();
            }

            if(isFound == false)
            {
                System.out.println("Invalid username and password");
            }
        }
        else if(choice == 2)
        {
            registerUser();
        }
    }

    public void adminLogIn()
    {
        System.out.println("Admin Logged in");
        admin admin1 = new admin();
        admin1.init();
    }

    public void userLogIn()
    {
        System.out.println("User logged in");
        //user.init();
    }

    public void registerUser()
    {
        String Username, Password, Password1;

        System.out.println("Input Username");
        Username = Keyboard.readString();
        System.out.println("Input Password");
        Password = Keyboard.readString();
        System.out.println("Reinput Password");
        Password1 = Keyboard.readString();

        if(Password == Password1)
        {
            try
            {
                BufferedWriter textOutputStream = new BufferedWriter(new FileWriter(USER_LIST_PATH, true));

                try
                {
                    textOutputStream.newLine();
                    textOutputStream.write(Username + "|" + Password + "|" + "false");

                    textOutputStream.flush();
                    textOutputStream.close();

                    System.out.println("User has been added to database!");
                }
                catch (IOException e)
                {
                    System.out.println("Something went wrong while writing to loginInfo.txt.");
                }
            }
            catch(IOException e)
            {
                System.out.println("Could not locate file directory");
                e.printStackTrace();
            }
            updateDatabase();
            logIn();
        }
        else
        {
            System.out.println("The passwords do not match. Please reinput all data.");
            registerUser();
        }
    }

    public void updateDatabase() 
    {
        BufferedReader textInputStream;
        String line, tempUsername, tempPassword;
        Boolean tempIsAdmin;
        String[] updateUserDatabase;
        User tempUser;
        
        userDatabase.clear();

        try 
        {
            textInputStream = new BufferedReader(new FileReader(USER_LIST_PATH));
            while ((line = textInputStream.readLine()) != null)
            {
                try 
                {
                    if (!line.equals(USER_LIST_TITLE)) 
                    {
                        updateUserDatabase = line.split("\\|");
                        tempUsername = updateUserDatabase[0];
                        tempPassword = updateUserDatabase[1];
                        tempIsAdmin = Boolean.parseBoolean(updateUserDatabase[2]);

                        tempUser = new User(tempUsername, tempPassword, tempIsAdmin);
                        userDatabase.add(tempUser);
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("Something went wrong with reading the file. Missing args?");
                    e.printStackTrace();
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Something went wrong with creating the textInputStream.");
            e.printStackTrace();
        }        
    }
}
