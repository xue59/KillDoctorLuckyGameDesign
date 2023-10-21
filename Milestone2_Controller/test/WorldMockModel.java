import model.world.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldMockModel implements World {
  private final Appendable output;
  private int totalAllowedTurns;

  /**
   * Create the mock world model for testing purpose.
   * @param output Appendable output for testing purpose.
   */
  public WorldMockModel(Appendable output) {
    Objects.requireNonNull(output);
    this.totalAllowedTurns = 9;
    this.output = output;
  }

  @Override
  public List<String> getNeighborsRoomList(String roomName)
      throws IllegalArgumentException, NullPointerException {
    return new ArrayList<>(List.of("Neighbors"));
  }

  @Override
  public String getWorldName() {
    return null;
  }

  @Override
  public int getTotalOfRoom() {
    return 0;
  }

  @Override
  public int getTotalOfItem() {
    return 0;
  }

  @Override
  public String getOneRoomInfo(String roomName)
      throws IllegalArgumentException, NullPointerException {
    return null;
  }

  @Override
  public void moveDrLucky() {

  }

  @Override
  public BufferedImage createGraphBufferedImage() {
    return null;
  }

  @Override
  public void printWorldNeighborMap() {

  }

  @Override
  public void printWorld2dArray() {

  }

  @Override
  public String getDrLuckyInfo() {
    return null;
  }

  @Override
  public void printAllRoomInfo() {

  }

  @Override
  public void addOnePlayer(String name, int initialRoomNum, boolean checkComputer, int limit)
      throws IllegalArgumentException, NullPointerException {

  }

  @Override
  public void setTotalAllowedTurns(int totalAllowedTurns) {

  }

  @Override
  public void setTotalAllowedPlayers(int totalAllowedPlayers) {

  }

  @Override
  public int getTotalAllowedPlayers() {
    return 0;
  }

  @Override
  public int getTotalAllowedTurns() {
    return 0;
  }

  @Override
  public String cmdComputerPlayerAction() throws IllegalStateException, IllegalAccessException {
    return null;
  }

  @Override
  public void cmdPlayerMove(String roomName)
      throws IllegalArgumentException, IllegalAccessException {

  }

  @Override
  public String cmdPlayerLook() throws IllegalArgumentException {
    return null;
  }

  @Override
  public void cmdPlayerPick(String inputItemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException,
      IllegalStateException {

  }

  @Override
  public String getPlayerWhatCanPickInfo(String playerName) {
    return null;
  }

  @Override
  public boolean checkGameOver() {
    return false;
  }

  @Override
  public List<String> getAllPlayerNames() {
    return null;
  }

  @Override
  public String getAllPlayerInfo() {
    return null;
  }

  @Override
  public List<String> getAllRoomNames() {
    return null;
  }

  @Override
  public String getOnePlayerAndRoomInfo(String playerName) {
    return null;
  }

  @Override
  public String getOnePlayerCurrentRoomName(String playerName) {
    return null;
  }

  @Override
  public String getCurrentPlayerName() {
    return null;
  }

  @Override
  public boolean isCurrentPlayerComputer() {
    return false;
  }

  @Override
  public int getCurrentPlayerIndex() {
    return 0;
  }

  @Override
  public int getCurrentTurnNumber() {
    return 0;
  }
}
