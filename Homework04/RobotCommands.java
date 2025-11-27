/* Raven Jaime CSCE 146 */

import java.util.Scanner;

public class RobotCommands {
    public static void main(String[] args) {
        boolean continueSim = true;
        String boardFile = "";
        String commandFile = "";
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println("Welcome to the Robot Command Simulation Center");
        while (continueSim)
        {
            try 
            {
                RobotCommandsBE.restart(); // Resets all the variables in the backend
                System.out.println("Please enter the file name of the board!"); 
                boardFile = scanner.next();
                System.out.println("Please enter the file name of the commands");
                commandFile = scanner.next();
                runSim(boardFile, commandFile);

                System.out.println("Do you wish to run another simulation? (Type yes or no)");
                userInput = scanner.next();
                if (userInput.equals("no"))
                {
                    continueSim = false;
                }
            } 
            catch (Exception e) 
            {
                //System.out.println("Invalid File Name 123");
            }
        }
        scanner.close();
        System.out.println("Thank you for using our services");
        
        
    }

    public static void runSim(String bF, String cF)
    {
        RobotCommandsBE.readBoard(bF);
        RobotCommandsBE.readCommands(cF);
        RobotCommandsBE.doCommands();
    }
}
