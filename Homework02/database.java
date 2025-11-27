// CSCE 146 RAVEN JAIME. Most comments are in databaseBE.java

import java.util.Scanner;

public class database {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean run = true; 
        while(run) 
        {
            System.out.println("----------------------------------------------------------------------------------------------------\nWelcome to the Game Database! Select an option.");
            System.out.println("\nEnter 1 to load the video game database\nEnter 2 to search the database\nEnter 3 to print current results to the console\nEnter 4 to print current results to file\nEnter 0 to quit\n");
            String userIn = scanner.next();
            switch(userIn)
            {
                case "1":
                try 
                {
                    System.out.println("\nLoad Database");
                    System.out.print("\nPlease enter the video game file in the working directory! (Case Sensitive and has to be accurate): ");
                    String userFile = scanner.next();
                    databaseBE.loadFile(userFile);
                    break;
                } 
                catch (Exception e) 
                {
                    System.out.println("Invalid File");
                    break;
                }
                    
                case "2":
                    System.out.println("Search Database");
                    System.out.print("\nEnter The Game Name or * for all games: ");
                    String gameName = scanner.next();
                    System.out.print("\nEnter the Game Console or * for all consoles: ");
                    String consoleName = scanner.next();
                    databaseBE.SearchFile(gameName, consoleName);
                    break;

                case "3":
                    System.out.println("\nPrint result to console");
                    databaseBE.printConsole();
                    break;
                case "4":
                    try 
                    {
                        System.out.println("\nPrint results to file");
                        System.out.print("\nEnter file name! : "); 
                        String userFile = scanner.next();
                        System.out.println("Do you want to append to the file? true or false"); // Either appends to the file or rewrites it
                        boolean append = Boolean.parseBoolean(scanner.next());
                        databaseBE.appendFile(userFile, append);
                        break;
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Invalid input");
                        break;
                    }
                    
                case "0":
                    System.out.println("Thank you for using our services!"); 
                    run = false; // Turns off the while loop
                    break;

                default:
                    System.out.println("Invalid input, try again!");  // If the user doesnt put 1 2 3 4 or 0
                    break;
            }

        }
        scanner.close();
    }


}
