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
            "Example run command: java -jar sampleRun.jar <$ModuleFileDir$/res/myOwnWorldMapV1.txt>");
        System.out.println(String.format("Initiating with Default File.....>>>> %s  ", fileName));
      } else {
        fileName = args[0];
        System.out.println(String.format("Input File args Found: %s", fileName));

      }

      FileReader fileReader = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fileReader);
      CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
      World mainWorld = createHelper.createWorld();
      mainWorld.printWorldNeighborMap();
      //      mainWorld.printWorld2DArray();
      System.out.println(mainWorld.getDrLuckyInfo());
      mainWorld.moveDrLucky();
      System.out.println(mainWorld.getDrLuckyInfo());
      mainWorld.printAllRoomInfo();
      mainWorld.moveDrLucky();

    } catch (IOException e) {
      throw new IOException("Error: unable to open file");
    }

  }
}
