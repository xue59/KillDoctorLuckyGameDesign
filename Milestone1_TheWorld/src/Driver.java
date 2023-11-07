import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.world.CreateWorldHelper;
import model.world.World;
import model.world.WorldImplement;

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
    String fileName = "mansion2023.txt";

    try {
      if (args.length < 1) {
        System.out.println("No File Args Txt File Path found! ");
        System.out.println(
            "ex: Example run command: java -jar sampleRun.jar <$ModuleFileDir$/mansion2023.txt>");
        System.out.println(String.format("Initiating with Default File.....>>>> %s  ", fileName));
      } else {
        fileName = args[0];
        System.out.println(String.format("Input File args Found: %s", fileName));
      }

      FileReader fileReader = new FileReader(fileName);
      CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(fileReader);
      World mainWorld = createHelper.createWorld();
      mainWorld.printWorld2dArray();
//      mainWorld.printWorldNeighborMap();
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printAllRoomInfo();
//      mainWorld.moveDrLucky();

            // After mainWorld created, add your example runs below:
             //Example run 1 - Brand New World with mansion.txt
//                        System.out.println("#1 Example Run with new Mansion.txt just created: ");
//                        System.out.println(mainWorld.getDrLuckyInfo());
//                        mainWorld.printWorldNeighborMap();
//                        mainWorld.printAllRoomInfo();
      //
      //      // Example run 2 -
                        System.out.println("#2 Example Run with new Mansion.txt "
                            + "DrLucky after move by 1: ");
                        mainWorld.moveDrLucky();
                        System.out.println(mainWorld.getDrLuckyInfo());
                        mainWorld.printWorldNeighborMap();
                        mainWorld.printAllRoomInfo();
                        mainWorld.createGraphBufferedImage();
      //
      //      // Example run 3 -
      //                  System.out.println("#3 Example Run with new Mansion.txt DrLucky after "
      //                      + "move by 2: ");
      //                  mainWorld.moveDrLucky();
      //                  mainWorld.moveDrLucky();
      //                  System.out.println(mainWorld.getDrLuckyInfo());
      //                  mainWorld.printWorldNeighborMap();
      //                  mainWorld.printAllRoomInfo();
      //                  mainWorld.createGraphBufferedImage();
      //
      //       //Example run 4 -
      //                  System.out.println("#4 Example Run with new Mansion.txt "
      //                      + "DrLucky after move by 25 "
      //                      + "DrLucky should be at Room #3-Drawing Room");
      //                  for (int i = 1; i <= 25; i++) {
      //                      mainWorld.moveDrLucky();
      //                  }
      //                  System.out.println(mainWorld.getDrLuckyInfo());
      //                  mainWorld.printWorldNeighborMap();
      //                  mainWorld.printAllRoomInfo();
      //                  mainWorld.createGraphBufferedImage();
      //
    } catch (IOException e) {
      throw new IOException("Error: unable to open file in Driver.");
    }
  }
}





