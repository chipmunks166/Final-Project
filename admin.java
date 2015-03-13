import java.util.*;
import java.io.*;

public class admin
{
    private ArrayList<Book> bookDatabase = new ArrayList<Book>();
    private final File BOOK_LIST_PATH = new File("bookList.txt");
    private final String BOOK_LIST_TITLE = "BOOK | AUTHOR FIRST NAME | AUTHOR LAST NAME | AVAILABILITY";
    public void init()
    {
        updateDatabase();

        Options();
    }

    public void Options() 
    {
        int choice = 0;

        System.out.println("Do you want to:");
        System.out.println("(1)Sort");
        System.out.println("(2)Add book to database");
        System.out.println("(3)Change availability");
        System.out.println("(4)Remove a book from database");

        choice = Keyboard.readInt();

        if(choice == 1)
            Sort();
        else if (choice == 2)
            addBook();
        //         else if (choice == 3)
        //             changeAvailability();
        //         else if (choice == 4)
        //             removeBook();

        {
            System.out.println("Choose one of the choices above(1-5)");
            Options();
        }
    }

    public void updateDatabase() 
    {
        BufferedReader textInputStream;
        String line, tempBookName, tempAuthorFirstName, tempAuthorLastName;
        boolean tempAvailability;
        String[] updateBookDatabase;
        Book tempBook;
        
        bookDatabase.clear();

        try 
        {
            textInputStream = new BufferedReader(new FileReader(BOOK_LIST_PATH));
            while ((line = textInputStream.readLine()) != null)
            {
                try 
                {
                    if (!line.equals(BOOK_LIST_TITLE)) 
                    {
                        updateBookDatabase = line.split("\\|");
                        tempBookName = updateBookDatabase[0];
                        tempAuthorLastName = updateBookDatabase[1];
                        tempAuthorFirstName = updateBookDatabase[2];
                        tempAvailability = Boolean.parseBoolean(updateBookDatabase[3]);

                        tempBook = new Book(tempBookName, tempAuthorLastName, tempAuthorFirstName, tempAvailability);
                        bookDatabase.add(tempBook);
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

    public void Sort ()
    {     
        String result = "";
        int input = 0;

        System.out.println("Sort by:");
        System.out.println("(1)Book Title");
        System.out.println("(2)Author Last Name");
        System.out.println("(3)Return to main menu");

        input = Keyboard.readInt();

        if(input == 1)
        {
            Collections.sort(bookDatabase, new Comparator<Book>()
                {
                    public int compare(Book book1, Book book2)
                    {
                        String lastBook1 = book1.getTitle();
                        String lastBook2 = book1.getTitle();

                        if (lastBook1.compareTo(lastBook2) > 0)
                            return 1;
                        else if (lastBook1.compareTo(lastBook2) < 0)
                            return -1;
                        else
                            return 0;

                    }
                });
            System.out.println("Sort completed!");

        }
        else if(input == 2)
        {
            Collections.sort(bookDatabase, new Comparator<Book>()
                {
                    public int compare(Book book1, Book book2)
                    {
                        String lastBook1 = book1.getAuthorLastName();
                        String lastBook2 = book1.getAuthorLastName();

                        if (lastBook1.compareTo(lastBook2) > 0)
                            return 1;
                        else if (lastBook1.compareTo(lastBook2) < 0)
                            return -1;
                        else
                            return 0;

                    }
                });
            System.out.println("Sort completed!");
        }
        else if(input == 3)
        {
            Options();
        }
        else
        {
            System.out.println("Please choose one of the options above.");
        }
        for (int i = 0;i < bookDatabase.size() - 1; i++)
        {
            result += bookDatabase.get(i) + "\n";
        }
        System.out.println(result);

        Sort();
    }

    public void addBook() 
    {
        String Title, lastName, firstName;

        System.out.println("Input Title");
        Title = Keyboard.readString();
        System.out.println("Input Author Last Name");
        lastName = Keyboard.readString();
        System.out.println("Input Author First Name");
        firstName = Keyboard.readString();

        try
        {
            BufferedWriter textOutputStream = new BufferedWriter(new FileWriter(BOOK_LIST_PATH, true));

            try
            {
                textOutputStream.newLine();
                textOutputStream.write(Title + "|" + lastName + "|" + firstName + "|" + "true");

                textOutputStream.flush();
                textOutputStream.close();

                System.out.println("Book has been added to database!");
            }
            catch (IOException e)
            {
                System.out.println("Something went wrong while writing to bookDatabase.txt.");
            }
        }
        catch(IOException e)
        {
            System.out.println("Could not locate file directory");
            e.printStackTrace();
        }
    }
}