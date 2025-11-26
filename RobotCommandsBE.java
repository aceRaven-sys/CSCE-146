import java.io.File;
import java.util.Scanner;

public class RobotCommandsBE 
{
    private static String[] board = null;
    private static LLQueue<String> commands = new LLQueue<>();
    private static String command = "";
    private static boolean keepGoing = true;
    private static int currentIndex = 0;
    private static int boardIndex = 0;
    private static int commandCount = 0;

    public static void restart() // When the user resets, this will set all the variables back. 
    {
        board = null;
        commands = new LLQueue<>();
        command = "";
        keepGoing = true;
        currentIndex = 0;
        boardIndex = 0;
        commandCount = 0;
    }

    public static void readBoard(String fileName) // Reads the board file and puts them into an array 
    {
        try 
        {
            int boardSize = 0;
            File boardFile = new File(fileName);
            Scanner scanner = new Scanner(boardFile); 
            while (scanner.hasNextLine())
            {
                boardSize += 1;
                String fileLine = scanner.nextLine();
                String[] oldBoard = board; // Used to make a new board with increased size
                board = new String[boardSize]; 
                for (int i = 0; i < boardSize - 1; i++)
                {
                    board[i] = oldBoard[i]; // Creates the new board
                }    
                board[boardSize - 1] = fileLine; // Adds the new line into the array

            }
            board[0] = changeBoardLine(0, 0); // Sets the first to be the marker O
            System.out.println("Here's the board");
            for (int i = 0; i < boardSize; i++)
                System.out.println(board[i]);
            scanner.close();

        } 
        catch (Exception e) {
            System.out.println("Board File not found, Simulation halted");
            keepGoing = false; // If file is not found, halts the program
            
        }
    }

    public static void readCommands(String fileName)
    {
        try 
        {
            File commandFile = new File(fileName); 
            Scanner scanner = new Scanner(commandFile); 
            while (scanner.hasNextLine())
            {
                commands.Enqueue(scanner.nextLine()); //Adds command to the queue
            }
            scanner.close();
        } 
        catch (Exception e) {
            System.out.println("Command File Not found, Simulation halted");
            keepGoing = false; // If file is not found, halts the program
            
        }
    }

    public static void doCommands()
    {
        if (keepGoing)
        {
            System.out.println("We will now run the simulation");
        }
        while(keepGoing && (commands.peek() != null))
        {
            commandCount += 1; 
            command = commands.Dequeue(); 
            System.out.println("\n***   Robot Command " + commandCount+ ": " + command + "   ***");
            switch (command) {
                case "Move Up":
                    moveUp();
                    break;
                case "Move Down":
                    moveDown();
                    break;
                case "Move Right":
                    moveRight();
                    break;
                case "Move Left":
                    moveLeft();
                    break;
                default:
                    System.out.println(command + "WOMP WOMP U DID WRONG"); // If the command is invalid
                    break;
            }
        }
    }

    /*The moveRight and moveLeft commands use the index of the char value of the line they are in */
    public static void moveRight() 
    {
        currentIndex += 1;
        board[boardIndex] = changeBoardLine(boardIndex, currentIndex);
        printBoard();
    }

    public static void moveLeft()
    {
        currentIndex -= 1;
        board[boardIndex] = changeBoardLine(boardIndex, currentIndex);
        printBoard();
    }

    // The up and down commands uses the index of the array that the O is in, along with the index of the char value of the line
    public static void moveDown()
    {
        board[boardIndex] = changePrev(boardIndex, currentIndex);
        if(boardIndex > board.length)
        {
            System.out.println("You CRASHED THE ROBOT UNDER THE FLOOR");
            CRASH();
        }

        boardIndex += 1;
        board[boardIndex] = changeBoardLine(boardIndex, currentIndex);
        printBoard();
    }

    public static void moveUp()
    {
        board[boardIndex] = changePrev(boardIndex, currentIndex);
        boardIndex -= 1;
        if(boardIndex < 0)
        {
            System.out.println("You CRASHED THE ROBOT IN THE ROOF");
            CRASH();
        }
        board[boardIndex] = changeBoardLine(boardIndex, currentIndex);
        printBoard();
    }

    public static String changeBoardLine(int boardLine, int indexChange) 
    {
        char[] line = board[boardLine].toCharArray(); // Creates an array of character values from the array of lines
        for (int i = 0; i < line.length; i++)
        {
            if(line[i] == 'O') // Removes the marker and replaces it with an X
                line[i] = '_';
        }
        
        
        if (line[indexChange] == 'X') // Checks if where the marker is going is an X, if so crash, if not, move the marker there
        {
            System.out.println("YOU CAN'T GO THERE! you hit a wall");
            CRASH();
        }else{
            line[indexChange] = 'O';
        }
        

        String newLine = "";
        for(int i = 0; i < line.length; i++) // Creates the new line with the changed markers into a string and returns it. The returned line will then replace the array index
        {
            newLine += line[i];
        }
        return newLine; 
    }

    public static String changePrev(int boardLine, int indexChange) //Used for the up and down to remove the O
    {
        char[] line = board[boardLine].toCharArray();
        for (int i = 0; i < line.length; i++)
        {
            if(line[i] == 'O')
                line[i] = '_';
        }
        String newLine = "";
        for(int i = 0; i < line.length; i++)
        {
            newLine += line[i];
        }
        return newLine;
    }

    public static void CRASH()
    {
        System.out.println("SYSTEM CRASHED!");
        keepGoing = false;
    }

    public static void printBoard()
    {
        if (keepGoing){
            for (int i = 0; i < board.length; i++)
                System.out.println(board[i]);
        }
        
        
    }


}
