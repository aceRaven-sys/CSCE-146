//CSCE 146 RAVEN JAIME

// Creates a GameInfo class that stores the game name and console name
public class GameInfo { 
    private String game = "none";
    private String console = "N/A"; 
    public GameInfo()
    {
        game = "none";
        console = "N/A";
    }

    public GameInfo(String GameName, String ConsoleName)
    {
        game = GameName;
        console = ConsoleName;
    }

    public String getGame()
    {
        return this.game;
    }

    public String getConsole()
    {
        return this.console;
    }

    public String toString()
    {
        return this.game + " ||| " + this.console;
    }


}
