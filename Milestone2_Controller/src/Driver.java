import controller.CmdControllerImplement;
import controller.Controller;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.world.CreateWorldHelper;
import model.world.World;

/**
 * The main driver class for running the Dr. Lucky's Mansion game simulation. It reads a text-based
 * world description file, creates the game world, and simulates Dr. Lucky's movement within the
 * mansion.
 */
public class Driver {

  /**
   * Expected to have an input file name, if no file name provided, it will run default
   * Filename=mansion2023.txt".
   *
   * @param args For receiving user input.
   */
  public static void main(String[] args) throws IOException {
    // The default file name path & name
    // CustomerMap input file path: $ContentRoot$/res/customerWorld.txt
    String fileName = String.format("res/mansion2023.txt");
    int totalPlayers = 3;
    int totalTurns = 9;

    try {
      if (args.length < 3) {
        System.out.println(String.format("Less than 3 arguments found! Staring game with "
            + "default file, %d players, %d total turns!", totalPlayers, totalTurns));
        System.out.println(
            "ex: Example run command: java -jar ms2_Controller.jar <$ModuleFileDir$/mansion2023" +
                ".txt> "
                + "{totalPlayersNumber} {totalMaxTurnNumber}");
        System.out.println(String.format("Initiating with Default File.....>>>> %s  ", fileName));
      } else {
        fileName = args[0];
        totalPlayers = Integer.parseInt(args[1]);
        totalTurns = Integer.parseInt(args[2]);
        System.out.println(String.format("Input File args Found: %s", fileName));
      }

      //build world Implement
      FileReader fileReader = new FileReader(fileName);
      CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(fileReader);
      World mainWorld = createHelper.createWorld();
      //mainWorld.printWorld2dArray(); // print 2d world
      mainWorld.setTotalAllowedPlayers(totalPlayers); //set total players
      mainWorld.setTotalAllowedTurns(totalTurns); //set total turns
      System.out.println("Starting Game in console command Line >>>>>>>>>>:");

      // starting game in console command line:
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;

      Controller consoleController = new CmdControllerImplement(input, output, mainWorld);
      consoleController.startGame();
    } catch (IOException e) {
      throw new IOException("Error: unable to open file in Driver.");
    }
  }
}





