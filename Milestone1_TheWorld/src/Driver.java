import model.world.World;
import model.world.WorldImplement;
import model.world.CreateWorldHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Driver {

  public static void main(String[] args) throws IOException {
    //manually provide the file name path & name
    String fileName = "res/mansion2023.txt";

    try {
      if (args.length < 1) {
        System.out.println("No File Args Txt File Path found! ");
        System.out.println(
            "ex: Example run command: java -jar sampleRun.jar <$ModuleFileDir$/res/myOwnWorldMapV1.txt>");
        System.out.println(String.format("Initiating with Default File.....>>>> %s  ", fileName));
      } else {
        fileName = args[0];
        System.out.println(String.format("Input File args Found: %s", fileName));

      }

      FileReader fileReader = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fileReader);
      CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
      World mainWorld = createHelper.createWorld();
//      mainWorld.printWorldNeighborMap();
//      mainWorld.printWorld2DArray();
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.moveDrLucky();
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printAllRoomInfo();
//      mainWorld.moveDrLucky();

      //After mainWorld created, add you example run below:
      // 1.Example run - Brand New World with mansion.txt
//      System.out.println("#1 Example Run with new Mansion.txt just created: ");
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printWorldNeighborMap();
//      mainWorld.printAllRoomInfo();
      // 2.Example run -
//      System.out.println("#2 Example Run with new Mansion.txt DrLucky after move by 1: ");
//      mainWorld.moveDrLucky();
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printWorldNeighborMap();
//      mainWorld.printAllRoomInfo();
//      mainWorld.createGraphBufferedImage();

      // 3.Example run -
//      System.out.println("#3 Example Run with new Mansion.txt DrLucky after move by 2: ");
//      mainWorld.moveDrLucky();
//      mainWorld.moveDrLucky();
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printWorldNeighborMap();
//      mainWorld.printAllRoomInfo();
//      mainWorld.createGraphBufferedImage();

      // 4.Example run -
//      System.out.println("#4 Example Run with new Mansion.txt DrLucky after move by 25, DrLucky should be at Room #3-Drawing Room ");
//      for (int i=1; i<=25;i++){
//        mainWorld.moveDrLucky();
//      }
//      System.out.println(mainWorld.getDrLuckyInfo());
//      mainWorld.printWorldNeighborMap();
//      mainWorld.printAllRoomInfo();
//      mainWorld.createGraphBufferedImage();


    } catch (IOException e) {
      throw new IOException("Error: unable to open file");
    }

  }
}
