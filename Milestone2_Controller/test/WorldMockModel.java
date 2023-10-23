import model.world.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A mock model of the World interface for testing purposes.
 */
public class WorldMockModel implements World {
  private final StringBuilder log;
  private int totalAllowedTurns;
  private int totalAllowedPlayers;
  private int curTurn;

  /**
   * Creates the mock world model for testing purposes.
   *
   * @param outputLog StringBuilder output for testing comparison.
   */
  public WorldMockModel(StringBuilder outputLog) {
    Objects.requireNonNull(outputLog);
    this.curTurn = 1;
    this.log = outputLog;
  }

  /**
   * Gets a list of neighboring rooms for a given room name.
   *
   * @param roomName The name of the room.
   * @return A list of neighboring room names.
   * @throws IllegalArgumentException If the room name is not valid.
   * @throws NullPointerException     If the provided room name is null.
   */
  @Override
  public List<String> getNeighborsRoomList(String roomName)
      throws IllegalArgumentException, NullPointerException {
    return new ArrayList<>(List.of("ListOfNeighbors"));
  }

  /**
   * Gets the name of the world.
   *
   * @return The name of the world.
   */
  @Override
  public String getWorldName() {
    return "Dr. Lucky's Mansion";
  }

  /**
   * Gets the total number of rooms in the world.
   *
   * @return The total number of rooms.
   */
  @Override
  public int getTotalOfRoom() {
    return 21;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTotalOfItem() {
    return 20;
  }

  /**
   * Gets information about a specific room in the world.
   *
   * @param roomName The name of the room.
   * @return Information about the room.
   * @throws IllegalArgumentException If the room name is not valid.
   * @throws NullPointerException     If the provided room name is null.
   */
  @Override
  public String getOneRoomInfo(String roomName)
      throws IllegalArgumentException, NullPointerException {
    return "One Room Info";
  }

  /**
   * Moves Dr. Lucky within the world.
   */
  @Override
  public void moveDrLucky() {
    this.log.append("DrLucky moved by one!");
  }

  /**
   * Creates a graphical representation of the world as a BufferedImage.
   *
   * @return An image representation of the world.
   */
  @Override
  public BufferedImage createGraphBufferedImage() {
    this.log.append("New map graph image created!");
    return new BufferedImage(20, 20, 1);
  }

  /**
   * Prints the neighbor map of the world to the console.
   */
  @Override
  public void printWorldNeighborMap() {
    this.log.append("printWorldNeighborMap world map printed!");
  }

  /**
   * Prints a 2D array representation of the world to the console.
   */
  @Override
  public void printWorld2dArray() {
    this.log.append("printWorld2dArray world map printed!");
  }

  /**
   * Gets information about Dr. Lucky within the world.
   *
   * @return Information about Dr. Lucky.
   */
  @Override
  public String getDrLuckyInfo() {
    this.log.append("getDrLuckyInfo executed!");
    return "getDrLuckyInfo executed!";
  }

  /**
   * Prints information about all rooms in the world to the console.
   */
  @Override
  public void printAllRoomInfo() {
    this.log.append("printAllRoomInfo all room info printed!");
  }

  /**
   * Add a human or computer player into the game world.
   *
   * @param name           String of the player name.
   * @param initialRoomNum Int of player's initial room number index base 0.
   * @param checkComputer  Ture if it is a computer player, otherwise false.
   * @param limit          Player's limit for carrying number of items.
   * @throws IllegalArgumentException Due to invalid input.
   * @throws NullPointerException     Due to invalid input.
   */
  @Override
  public void addOnePlayer(String name, int initialRoomNum, boolean checkComputer, int limit)
      throws IllegalArgumentException, NullPointerException {
    this.log.append(String.format("addOnePlayer executed: name=%s, initialRoomNum=%d,"
        + " checkComputer=%b, limit=%d", name, initialRoomNum, checkComputer, limit));
  }

  /**
   * Sets the total allowed number of turns in the game.
   *
   * @param totalAllowedTurns The total number of turns allowed in the game.
   */
  @Override
  public void setTotalAllowedTurns(int totalAllowedTurns) {
    this.log.append(String.format("setTotalAllowedTurns executed: totalAllowedTurns=%d",
        totalAllowedTurns));
    this.totalAllowedTurns = totalAllowedTurns;
  }

  /**
   * Sets the total allowed number of players in the game.
   *
   * @param totalAllowedPlayers The total number of players allowed in the game.
   */
  @Override
  public void setTotalAllowedPlayers(int totalAllowedPlayers) {
    this.log.append(String.format("setTotalAllowedPlayers executed: totalAllowedPlayers=%d",
        totalAllowedPlayers));
    this.totalAllowedPlayers = totalAllowedPlayers;
  }

  /**
   * Gets the total number of players allowed in the game.
   *
   * @return The total number of players allowed in the game.
   */
  @Override
  public int getTotalAllowedPlayers() {
    this.log.append("getTotalAllowedPlayers executed!");
    return this.totalAllowedPlayers; // Change the return value as needed.
  }

  /**
   * Gets the total number of turns allowed in the game.
   *
   * @return The total number of turns allowed in the game.
   */
  @Override
  public int getTotalAllowedTurns() {
    this.log.append("getTotalAllowedTurns executed!");
    return this.totalAllowedTurns; // Change the return value as needed.
  }

  /**
   * Perform a computer player's action in the game and return the result.
   *
   * @return The result of the computer player's action.
   * @throws IllegalStateException  If the game is illegal state for the computer player's action.
   * @throws IllegalAccessException If there is an illegal access attempt during the action.
   */
  @Override
  public String cmdComputerPlayerAction() throws IllegalStateException, IllegalAccessException {
    this.log.append("cmdComputerPlayerAction executed!");
    this.curTurn++;
    return "cmdComputerPlayerActionResult"; // Change the return value as needed.
  }

  /**
   * Move the current player to the specified room.
   *
   * @param roomName The name of the room to which the player should move.
   * @throws IllegalArgumentException If the provided room name is not valid.
   * @throws IllegalAccessException   If there is an illegal access attempt during the move.
   */
  @Override
  public void cmdPlayerMove(String roomName)
      throws IllegalArgumentException, IllegalAccessException {
    this.curTurn++;
    this.log.append(String.format("cmdPlayerMove executed: roomName=%s", roomName));
  }

  /**
   * Get information about the current player's current room.
   *
   * @return Information about the current player's current room.
   * @throws IllegalArgumentException If the current player's room information is not valid.
   */
  @Override
  public String cmdPlayerLook() throws IllegalArgumentException {
    this.log.append("cmdPlayerLook executed!");
    this.curTurn++;
    return "cmdPlayerLookResult"; // Change the return value as needed.
  }

  /**
   * Allow the current player to pick up an item in the room.
   *
   * @param inputItemName The name of the item to be picked up.
   * @throws NullPointerException     If the item name is null.
   * @throws IllegalArgumentException If item name is not valid, or if picking up is not allowed.
   * @throws IllegalAccessException   If there is an illegal access attempt during item pickup.
   * @throws IllegalStateException    If item pickup is not allowed in the current state.
   */
  @Override
  public void cmdPlayerPick(String inputItemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException,
      IllegalStateException {
    this.curTurn++;
    this.log.append(String.format("cmdPlayerPick executed: inputItemName=%s", inputItemName));
  }

  /**
   * Get information about the items that a player can pick up in the current room.
   *
   * @param playerName The name of the player for which to retrieve item information.
   * @return Information about the items that the player can pick up in the room.
   */
  @Override
  public String getPlayerWhatCanPickInfo(String playerName) {
    this.log.append(String.format("getPlayerWhatCanPickInfo executed: playerName=%s", playerName));
    return "playerWhatCanPickInfoResult"; // Change the return value as needed.
  }

  /**
   * Check if the game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  @Override
  public boolean checkGameOver() {
    this.log.append(String.format("checkGameOver executed!\n"));

    if (this.curTurn > totalAllowedTurns) {
      return true;
    }
    return false;
  }

  /**
   * Gets a list of names of all players in the game world.
   *
   * @return A list of player names.
   */
  @Override
  public List<String> getAllPlayerNames() {
    this.log.append("getAllPlayerNames executed!");
    return new ArrayList<>(); // Return a list of player names or adjust as needed.
  }

  /**
   * Gets information about all players in the game world.
   *
   * @return Information about all players.
   */
  @Override
  public String getAllPlayerInfo() {
    this.log.append("getAllPlayerInfo executed!");
    return "allPlayerInfo"; // Change the return value as needed.
  }

  /**
   * Gets a list of names of all rooms in the game world.
   *
   * @return A list of room names.
   */
  @Override
  public List<String> getAllRoomNames() {
    this.log.append("getAllRoomNames executed!");
    return new ArrayList<>(); // Return a list of room names or adjust as needed.
  }

  /**
   * Gets information about a specific player and their current room.
   *
   * @param playerName The name of the player for which to retrieve information.
   * @return Information about the specified player and their current room.
   */
  @Override
  public String getOnePlayerAndRoomInfo(String playerName) {
    this.log.append(String.format("getOnePlayerAndRoomInfo executed: playerName=%s", playerName));
    return "onePlayerAndRoomInfo"; // Change the return value as needed.
  }

  /**
   * Gets the room name of the current player in the game.
   *
   * @param playerName
   * @return The room name string of where the current player is in.
   */
  @Override
  public String getOnePlayerCurrentRoomName(String playerName) {
    this.log.append(
        String.format("getOnePlayerCurrentRoomName executed: playerName=%s", playerName));
    return "currentRoomName"; // Change the return value as needed.
  }

  /**
   * Gets the name of the current player in the game.
   *
   * @return The name String of the current player.
   */
  @Override
  public String getCurrentPlayerName() {
    this.log.append("getCurrentPlayerName executed!");
    return "currentPlayerName"; // Change the return value as needed.
  }

  /**
   * Checks if the current player is a computer player.
   *
   * @return True if the current player is a computer player, false otherwise.
   */
  @Override
  public boolean isCurrentPlayerComputer() {
    this.log.append("isCurrentPlayerComputer executed!");
    return false; // Change the return value as needed.
  }

  /**
   * Gets the index of the current player.
   *
   * @return The index of the current player.
   */
  @Override
  public int getCurrentPlayerIndex() {
    this.log.append("getCurrentPlayerIndex executed!");
    return this.curTurn; // Change the return value as needed.
  }

  /**
   * Gets the current turn number in the game.
   *
   * @return The current turn number.
   */
  @Override
  public int getCurrentTurnNumber() {
    this.log.append("getCurrentTurnNumber executed!");
    return 0; // Change the return value as needed.
  }
}
