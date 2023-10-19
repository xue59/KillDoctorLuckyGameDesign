package controller;

import model.world.World;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WorldConsoleControllerImplement implements WorldConsoleController{
  private World world;
  private final Scanner scanner;
  private final Appendable output;
  private final int totalAllowedPlayers;
  private final int getTotalAllowedTurns;

  public WorldConsoleControllerImplement(Readable input, Appendable output, World world){
    this.scanner = new Scanner(input);
    this.output = output;
    this.world = world;
    this.totalAllowedPlayers = world.getTotalAllowedPlayers();
    this.getTotalAllowedTurns = world.getTotalAllowedTurns();
  }

  /**
   *
   */
  @Override
  public void startGame() throws IOException {
    while (true){
      this.output.append(String.format("Welcome to the Game World of %s:\n",
          world.getWorldName()));
      this.displayMainMenuOptions();
      int select = 0;
      try {
        select = Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException exception) {
        this.output.append("Error: please only enter an integer.\n");
      }

      switch (select){
        case 0:
          this.createWorldMapPNG();
          break;
        case 1:
          this.addAllPlayers();
          break;
        case 2:
          this.loopToDisplayOneRoomInfo();
          break;
        case 3:
          if(world.getAllPlayerNames().size() ==0){
            this.output.append("No players added, try add players first.\n");
          }else{
            this.loopToDisplayOnePlayerInfo();
          }
          break;


        default:
          this.output.append("Please select from Main Menu:(0-4)");
      }
    }
  }

  private void displayMainMenuOptions() throws IOException {
    this.output.append("Main Menu: \n");
    this.output.append("(Select following operation (integer only)!)\n");
    this.output.append
        ( "0-Create a graphical representation of the world map PNG.\n"
        + "1-Setup game by adding all "+totalAllowedPlayers+" players.\n"
        + "2-Find a Room (Display information about specified room in the world).\n"
        + "3-Find a Player (Display information about specified player in the world).\n");
  }

  private void loopToSelectMainMenu() throws IOException {
    try {
      output.append("Enter 'm' or 'M' to go back to Main Menu: ");
      String userInput = scanner.nextLine().trim();

      if (userInput.equalsIgnoreCase("m")) {
        // Go back to the main menu
        startGame();
      } else {
        // Invalid input, ask again
        output.append("Invalid input. Please enter 'm' or 'M' to go back to the main menu.\n");
        loopToSelectMainMenu(); // Recursive call to ask again
      }
    } catch (IOException e) {
      // Handle IOException if needed
      e.printStackTrace();
    }
  }

  private void loopToDisplayOneRoomInfo() throws IOException {
    String inputName=null;
    try{
      this.output.append(String.format("All %d room in the %s:\n%s", world.getTotalOfRoom(),
          world.getWorldName(), world.getAllRoomNames()));
      this.output.append("\nWhich room do you want to check? "
          + "(Enter the exact room name from above list.)\n");
      inputName = this.scanner.nextLine().trim();
      this.output.append(world.getOneRoomInfo(inputName) + "\n");
      loopToSelectMainMenu();
    } catch (IllegalArgumentException e){
      this.output.append(String.format("No room name found for: %s\n", inputName));
      this.output.append("Check your room name typo again, room names are case sensitive.\n");
      loopToDisplayOneRoomInfo();
    }catch (NullPointerException e){
      this.output.append("Room name cannot be Null.\n");
      loopToDisplayOneRoomInfo();
    }
  }
  private void loopToDisplayOnePlayerInfo() throws IOException {
    String inputName = null;
    List<String> playerNameList = world.getAllPlayerNames();
    try {
      this.output.append(String.format("All %d players in the %s:\n%s", playerNameList.size(),
          world.getWorldName(), playerNameList));
      this.output.append("\nWhich player do you want to check? "
          + "(Enter the exact player name from the above list.)\n");
      inputName = this.scanner.nextLine().trim();
      this.output.append(world.getOnePlayerAndRoomInfo(inputName) + "\n");
      loopToSelectMainMenu();
    } catch (IllegalArgumentException e) {
      this.output.append(String.format("No player found for: %s\n", inputName));
      this.output.append("Check your player name typo again. Player names are case sensitive.\n");
      loopToDisplayOnePlayerInfo();
    } catch (NullPointerException e) {
      this.output.append("Player name cannot be null.\n");
      loopToDisplayOnePlayerInfo();
    }
  }


  private void createWorldMapPNG() throws IOException {
    this.world.createGraphBufferedImage();
    this.output.append("The world map png created in above directory.\n");
    loopToSelectMainMenu();
  }


  private void addAllPlayers() throws IOException {
    int count = 1;
    while(world.getAllPlayerNames().size() < world.getTotalAllowedPlayers()){
      this.output.append(String.format("Enter information for No.%d player: \n", count));
      addOnePlayer();
      count++;
    }
    if(world.getAllPlayerNames().size() == world.getTotalAllowedPlayers()){
      this.world.createGraphBufferedImage();
      this.output.append(String.format("\nAll %d players added successfully! They are:\n",
          world.getTotalAllowedPlayers()));
      this.output.append(world.getAllPlayerInfo());
    }
    loopToSelectMainMenu();
  }

  private void addOnePlayer() throws IOException {
    try{
      String playerName;
      int playerLimit;
      int playerInitialRoom;
      boolean isComputer;
      this.output.append("Give a name for this Player:\n");
      playerName = scanner.nextLine().trim(); // get player name

      this.output.append(String.format("Set Player: %s's carrying limit(Integer only!):\n",
          playerName));
      playerLimit = Integer.parseInt(scanner.nextLine().trim()); // get player limit

      this.output.append(String.format("Set Player: %s's initial room index (0 to %d):\n",
          playerName, world.getTotalOfRoom()-1));
      playerInitialRoom = Integer.parseInt(scanner.nextLine().trim()); // get player limit

      this.output.append(String.format("Set Player: is this %s a computer player?:(Y/N)\n",
          playerName));
      // if user input Y for isComputer True; N for isComputer False.
      isComputer = this.checkInputStringTrueFalse(scanner.nextLine().trim());

      // call world method to create the player & add into game world
      this.world.addOnePlayer(playerName, playerInitialRoom, isComputer, playerLimit);

      this.output.append(String.format("Player: %s (limit: %d) successfully added into Room #%d\n",
          playerName, playerLimit, playerInitialRoom));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NumberFormatException e){
      output.append("Please enter an integer for limit and room index! \n");
      output.append(String.format("\nNow, re-add No.%d player from beginning: \n",
          this.world.getAllPlayerNames().size()+1));
      this.addOnePlayer();
    } catch (IllegalArgumentException e){
      output.append(e.getMessage());
      output.append(String.format("\nNow, re-add No.%d player from beginning: \n",
          this.world.getAllPlayerNames().size()+1));
      this.addOnePlayer();
    }
  }
  private boolean checkInputStringTrueFalse(String in) {
    if (in != null) {
      in = in.toLowerCase(); // Convert the input to lowercase for case-insensitivity
      if (in.equals("yes") || in.equals("y")) {
        return true;
      } else if (in.equals("no") || in.equals("n")) {
        return false;
      }
    }
    return false; // Default to false if input doesn't match "yes" or "no"
  }



}
