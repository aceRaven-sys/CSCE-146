// CSCE 146 RAVEN JAIME 

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class databaseBE {

    public static GenLL<GameInfo> GameDatabase = new GenLL<GameInfo>(); // Will Store users file
    public static GenLL<GameInfo> SearchResults = new GenLL<GameInfo>(); // Will store users search results 


    public static void loadFile(String fileName) /*  Resets and creates a new Linked List with the users file. Program reads the file, makes sure it has the 
                                                     correct format then it creates a new GameInfo class that stores the Game Name and the Console which is read 
                                                     from the file. This new class is then stored and added to the GameDatabase linked list */
    {
        try 
        {
            GameDatabase = new GenLL<GameInfo>();
            File games = new File(fileName);
            Scanner scanner = new Scanner(games);

            String line = "";

            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] lineArray = line.split("\t");
                if(lineArray.length != 2)
                    continue;
                GameInfo gameData = new GameInfo(lineArray[0], lineArray[1]);
                //System.out.println(gameData.toString());
                GameDatabase.add(gameData);
            }
            scanner.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error --- INVALID FILE. PLEASE MAKE SURE ITS IN THE WORKING DIRECTORY AND THAT YOU TYPED IT OUT PROPERLY");
        }
    }

    public static void SearchFile(String gameName, String gameConsole) /*Clears previous searchResults and we convert everything userInput and data to lowercase. 
                                                                        We go through the linked list containing the data and see if the data contains the 
                                                                        userInput value. */
    {
        SearchResults = new GenLL<GameInfo>();
        GameDatabase.reset();
        String userGame = gameName.toLowerCase();
        String userConsole = gameConsole.toLowerCase();
        boolean hasPrinted = false;

        if(!GameDatabase.hasMore()){
            System.out.println("\nWe dont have data. Please load a video game database!");
        }

        while(GameDatabase.hasMore())
            {
                GameInfo data = GameDatabase.getCurrent();
                String gameData = data.getGame().toLowerCase();
                String consoleData = data.getConsole().toLowerCase();


                if ((userGame.equals("*")) && (userConsole.equals("*")))
                {
                    // System.out.println(data.toString());
                    GameDatabase.gotoNext();
                    hasPrinted = true;
                    SearchResults.add(data);
                    continue;
                }

                if (userGame.equals("*"))
                {
                    if(consoleData.contains(userConsole))
                    {
                        // System.out.println(data.toString());
                        GameDatabase.gotoNext();
                        hasPrinted = true;
                        SearchResults.add(data);
                        continue;
                    }
                    GameDatabase.gotoNext();
                    continue;
                }

                if (userConsole.equals("*"))
                {
                    if(gameData.contains(userGame))
                    {
                        // System.out.println(data.toString());
                        GameDatabase.gotoNext();
                        hasPrinted = true;
                        SearchResults.add(data);
                        continue;
                    }
                    GameDatabase.gotoNext();
                    continue;
                }

                if (gameData.contains(userGame) && consoleData.contains(userConsole))
                {
                    // System.out.println(data.toString());
                    GameDatabase.gotoNext();
                    hasPrinted = true;
                    SearchResults.add(data);
                    continue;
                }
                
                GameDatabase.gotoNext();
            }
        while (SearchResults.hasMore())
        {
            System.out.println(SearchResults.getCurrent().toString());
            SearchResults.gotoNext();
        }
        if (!hasPrinted)
        {
            System.out.println("\nInvalid Search Prompts----------------------------------------------------------------\n");
        }
    }

    public static void printConsole() // Simply just reprints the search results
    {
        SearchResults.reset();
        if(!SearchResults.hasMore())
        {
            System.out.println("\nYou haven't searched anything yet.");
        }
        while (SearchResults.hasMore())
        {
            System.out.println(SearchResults.getCurrent().toString());
            SearchResults.gotoNext();
        }
    }

    public static void appendFile(String fileName, boolean appending) /*ASKS FOR USER FILE and if they want to append or rewrite the file. 
                                                                        IF FILE DOESNT EXIST, will create for you. The program goes through
                                                                        each SearchResult and grabs the console name and game name. It then writes
                                                                        the Game Name and console using the tab format into the users file. */
    {
        try 
        {
            SearchResults.reset();
            File usersFile = new File(fileName);
            FileWriter writer = new FileWriter(usersFile, appending);

            if (usersFile.createNewFile())
                System.out.println("File Created: " + usersFile.getName());

            while(SearchResults.hasMore())
            {
                String console = SearchResults.getCurrent().getConsole();
                String game = SearchResults.getCurrent().getGame();
                writer.write(game + "\t" + console + "\n");
                SearchResults.gotoNext();
            }
            writer.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error");
        }
    }


}
