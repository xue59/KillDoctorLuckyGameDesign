package model.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.imageio.ImageIO;
import model.drlucky.DrLucky;
import model.room.Room;

/**
 * The WorldImplement class represents the game world containing rooms and a target (DrLucky). It
 * implements the World interface, providing methods to interact with the world's attributes and
 * entities.
 */
public class WorldImplement implements World {
  private final int colSize;                          // The number of columns in the world.
  private final int rowSize;                          // The number of rows in the world.
  private final String name;                          // The name of the world.
  private final int totalRooms;                      // The total number of rooms in the world.
  private final int totalItems;                      // The total number of items in the world.
  private final List<Room> roomList;                 // The list of rooms in the world.
  private final DrLucky drLucky;                     // The character DrLucky.
  private final Map<Integer, Set<Integer>> worldMapIndex; // A map representing neighbor index.
  private final Integer[][] world2dArray;            // A 2D array representation of the world.

  /**
   * Constructs a WorldImplement object with the specified attributes.
   *
   * @param rowSize       The number of rows in the world.
   * @param colSize       The number of columns in the world.
   * @param name          The name of the world.
   * @param totalRooms    The total number of rooms in the world.
   * @param totalItems    The total number of items in the world.
   * @param roomList      The list of rooms in the world.
   * @param drLucky       The character DrLucky.
   * @param worldMapIndex A map representing room indices and their neighbors.
   * @param world2dArray  A 2D array representation of the world.
   * @throws IllegalArgumentException If rowSize or colSize is non-positive, the world name is empty
   *                                  or null, or the totalRooms or totalItems are non-positive.
   * @throws NullPointerException     If roomList, drLucky, or worldMapIndex is null.
   */
  public WorldImplement(int rowSize, int colSize, String name, int totalRooms, int totalItems,
      List<Room> roomList, DrLucky drLucky, Map<Integer, Set<Integer>> worldMapIndex,
      Integer[][] world2dArray) throws IllegalArgumentException, NullPointerException {
    // Check if rowSize and colSize are positive values.
    if (colSize <= 0 || rowSize <= 0) {
      throw new IllegalArgumentException("Error: Row or column size cannot be negative or zero.");
    }
    // Check if the world name is valid.
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Error: World name cannot be empty or null.");
    }
    // Check if the totalRooms and totalItems are positive values.
    if (totalItems <= 0 || totalRooms <= 0) {
      throw new IllegalArgumentException(
          "Error: Total rooms and items cannot be negative or zero.");
    }
    // Check that the input objects are not null.
    Objects.requireNonNull(roomList);
    Objects.requireNonNull(drLucky);
    Objects.requireNonNull(worldMapIndex);

    // Assign values to class members.
    this.colSize = colSize;
    this.rowSize = rowSize;
    this.name = name;
    this.totalItems = totalItems;
    this.totalRooms = totalRooms;
    this.roomList = roomList;
    this.drLucky = drLucky;
    this.worldMapIndex = worldMapIndex;
    this.world2dArray = world2dArray;

    // Generate an initial map representation with DrLucky's position and save it to an image.
    this.createGraphBufferedImage();
  }

  /**
   * Retrieves the room number by its name.
   *
   * @param nameString The name of the room.
   * @return The room number corresponding to the given name.
   * @throws NullPointerException     If the input nameString is null.
   * @throws IllegalArgumentException If the room name is not found in the world.
   */
  private int getRoomNumByNameString(String nameString) {
    Objects.requireNonNull(nameString); // input cannot be null
    for (Room room : this.roomList) {
      if (room.getRoomName().equals(nameString)) {
        return room.getRoomNumber();
      }
    }
    throw new IllegalArgumentException(
        String.format("Error: Room name: %s is not in the world.", nameString));
  }

  /**
   * Gets a list of neighboring room names for a given room.
   *
   * @param roomName The name of the room.
   * @return A list of neighboring room names.
   * @throws IllegalArgumentException If the room name is not found in the world.
   * @throws NullPointerException     If the input roomName is null.
   */
  @Override
  public List<String> getNeighborsRoomList(String roomName)
      throws IllegalArgumentException, NullPointerException {
    //  need to working this code!
    int rmNumber = getRoomNumByNameString(roomName);
    Set<Integer> neighboringRoomIndex = new HashSet<>(worldMapIndex.get(rmNumber));
    // do not need to remove tcw yet.
    List<String> neighboringRoomNames = new ArrayList<>();
    for (Integer anIndex : neighboringRoomIndex) {
      Room room = this.roomList.get(anIndex);
      neighboringRoomNames.add(room.getRoomName());
    }
    return neighboringRoomNames;
  }

  /**
   * Gets the name of the world.
   *
   * @return The name of the world.
   */
  @Override
  public String getWorldName() {
    return this.name;
  }

  /**
   * Gets the total number of rooms in the world.
   *
   * @return The total number of rooms.
   */
  @Override
  public int getTotalOfRoom() {
    return this.totalRooms;
  }

  /**
   * Gets the total number of items in the world.
   *
   * @return The total number of items.
   */
  @Override
  public int getTotalOfItem() {
    return this.totalItems;
  }

  /**
   * Gets information about a specific room, including its name, items, and neighbors.
   *
   * @param roomName The name of the room.
   * @return Information about the specified room.
   * @throws IllegalArgumentException If the room name is not found in the world.
   * @throws NullPointerException     If the input roomName is null.
   */
  @Override
  public String getOneRoomInfo(String roomName)
      throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(roomName);
    Room room = this.roomList.get(getRoomNumByNameString(roomName));

    //check find all the item in the room & append to the string
    StringBuilder roomInfo = new StringBuilder();
    roomInfo.append(room.toString() + "\n");

    //check if DrLucky in this room
    Room drLuckyRoom = this.roomList.get(drLucky.getCurrentRoomNumber());
    if (drLuckyRoom.equals(room)) { // if dr lucky in the same room append the string
      roomInfo.append(String.format("DrLucky(%s) in this room.\n", this.drLucky.getName()));
    }

    //check the rooms neighbors info
    List<String> neighbors = this.getNeighborsRoomList(roomName);
    roomInfo.append("Neighbors: ");
    for (String neighbor : neighbors) {
      roomInfo.append(neighbor);
      roomInfo.append(",");
    }
    roomInfo.append("\n");

    return roomInfo.toString();
  }

  /**
   * Moves DrLucky to the next room in the world.
   */
  @Override
  public void moveDrLucky() {
    this.drLucky.moveDrLucky();
  }

  /**
   * Creates a graphical representation of the world and saves it as an image file.
   *
   * @return The generated BufferedImage representing the world.
   */
  @Override
  public BufferedImage createGraphBufferedImage() {
    int scale = 40;
    int padding = 15;
    int width = (this.colSize + 3) * scale;
    int height = (this.rowSize + 3) * scale;
    int fontSize = 22; // Adjust the font size as png size needed
    Font font = new Font("Arial", Font.BOLD, fontSize);

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = bufferedImage.createGraphics();

    g2d.setColor(Color.WHITE); //Set the background color as white
    g2d.fillRect(0, 0, width, height); //Set the background color as white
    g2d.setColor(Color.BLUE); // Set line color as Blue!
    g2d.setFont(font);

    for (Room room : roomList) {
      int rowY = room.getTopRowY() * scale + padding;
      int colX = room.getTopColX() * scale + padding;
      int roomH = (room.getBotRowY() - room.getTopRowY() + 1);
      int roomW = (room.getBotColX() - room.getTopColX() + 1);
      g2d.drawString(room.getRoomName(), colX + scale / 2, rowY + scale / 2);
      int roomNumber = room.getRoomNumber();

      // Check if DrLucky in room & display on map
      if (roomNumber == this.drLucky.getCurrentRoomNumber()) {
        String drLuckyWithHp = String.format("%s (hp:%d)", this.drLucky.getName(),
            this.drLucky.getCurrentHp());
        g2d.setColor(Color.RED); // Set line color as RED!
        g2d.drawString(drLuckyWithHp, colX + scale / 2, rowY + scale);
        g2d.setColor(Color.BLUE); // Set line back to Blue!
      }
      g2d.drawRect(colX, rowY, scale * roomW, scale * roomH);
    }
    g2d.dispose();

    // Save the BufferedImage to a file (optional)
    File output = new File("newCreatedMap.png");
    try {
      ImageIO.write(bufferedImage, "png", output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Image saved to: " + output.getAbsolutePath());

    return bufferedImage;

  }

  /**
   * Prints the 2D array representation of the world to the console.
   */
  public void printWorld2dArray() {
    Integer[][] array = this.world2dArray;
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        if (Objects.isNull(array[i][j])) {
          System.out.print("——" + " ");
        } else {
          if (array[i][j] < 10) {
            System.out.print(" " + array[i][j] + " ");
          } else {
            System.out.print(array[i][j] + " ");
          }
        }
      }
      System.out.println(); // Move to the next line after each row
    }
  }

  /**
   * Prints the room numbers and their neighboring rooms from a given worldNeighborMap.
   */
  public void printWorldNeighborMap() {
    Map<Integer, Set<Integer>> worldNeighborMap = this.worldMapIndex;
    for (Map.Entry<Integer, Set<Integer>> entry : worldNeighborMap.entrySet()) {
      Integer roomNumber = entry.getKey();
      Set<Integer> neighborSet = entry.getValue();

      System.out.print("Room " + roomNumber + " Neighbors: ");
      for (Integer neighbor : neighborSet) {
        System.out.print(neighbor + " ");
      }
      System.out.println(); // Move to the next line for the next room
    }
  }

  /**
   * Gets information about DrLucky's current room and status.
   *
   * @return Information about DrLucky.
   */
  @Override
  public String getDrLuckyInfo() {
    StringBuilder info = new StringBuilder();
    int curRoomNumber = this.drLucky.getCurrentRoomNumber();
    Room curRoom = this.roomList.get(curRoomNumber);
    info.append(
        String.format("Target name: %s(HP:%d)currently in room #%d-%s", this.drLucky.getName(),
            this.drLucky.getCurrentHp(), curRoom.getRoomNumber(), curRoom.getRoomName()));

    return info.toString();
  }

  /**
   * Print all the room info for check.
   */
  public void printAllRoomInfo() {
    for (Room room : this.roomList) {
      System.out.println(getOneRoomInfo(room.getRoomName()));
    }
  }

}
