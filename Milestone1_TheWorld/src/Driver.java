import model.world.World;
import model.world.WorldImplement;
import model.world.CreateWorldHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

  public static void main(String[] args) throws IOException {
//    if (args == null) {
//      System.out.println("Error: No input, please input arguments!!!");
//      return;
//    }
//    if (args.length < 2) {
//      System.out.println("Need min 2 arguments!!!");
//      return;
//    }
    System.out.println("Starting the game in Main>>>>>>");

    //manually provide the file name path & name
    String filename = "res/mansion2023.txt";
    System.out.println(String.format("Filename: %s", filename));

    try{
      FileReader fileReader = new FileReader(filename);
      BufferedReader br = new BufferedReader(fileReader);
      CreateWorldHelper createWorld = new CreateWorldHelper().readBuildTxtFile(br);


    } catch(IOException e){
      throw new IOException("Error: unable to open file");
    }

  }
}
