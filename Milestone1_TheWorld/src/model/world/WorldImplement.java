package model.world;

import model.drlucky.DrLucky;
import model.room.Room;
import model.drlucky.DrLucky;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

public class WorldImplement implements World {
  private final int colSize;
  private final int rowSize;
  private final String name;
  private final int totalRooms;
  private final int totalItems;
  private final List<Room> roomList;
  private final DrLucky drLucky;
  private final Map<Integer, Set<Integer>> worldMapIndex;
  //  private final Map<Room, Set<Room>> worldMapRoom;
  private final Integer[][] world2dArray;

  public WorldImplement(int rowSize, int colSize, String name, int totalRooms, int totalItems,
      List<Room> roomList, DrLucky drLucky, Map<Integer, Set<Integer>> worldMapIndex,
      Integer[][] world2dArray) {
    // check if the rowSize and colSize are positive values
    if (colSize <= 0 || rowSize <= 0) {
      throw new IllegalArgumentException("Error: row or column size can not be negative or 0.");
    }
    // check if world name is valid
    if (name.isEmpty() || name == null) {
      throw new IllegalArgumentException("Error: World name cannot be empty or Null!");
    }
    // check amount of rooms and items are not 0
    if (totalItems <= 0 || totalRooms <= 0) {
      throw new IllegalArgumentException("Error: total room and item can not be negative or 0.");
    }
    //check rest of input object is not null
    // List<Room> roomList, DrLucky drLucky,
    // Map<Integer, Set<Integer>> worldMapIndex, Map<Room, Set<Room>> worldMapRoom
    Objects.requireNonNull(roomList);
    Objects.requireNonNull(drLucky);
    Objects.requireNonNull(worldMapIndex);
    //    Objects.requireNonNull(worldMapRoom);

    //All input checked valid, starting assign:
    this.colSize = colSize;
    this.rowSize = rowSize;
    this.name = name;
    this.totalItems = totalItems;
    this.totalRooms = totalRooms;
    this.roomList = roomList;
    this.drLucky = drLucky;
    this.worldMapIndex = worldMapIndex;
    this.world2dArray = world2dArray;

    // draw the map out with dr lucky init position 0 & appear in res dir.
    this.createGraphBufferedImage();
    //    this.worldMapRoom = worldMapRoom;
  }

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

  @Override
  public String getWorldName() {
    return this.name;
  }

  @Override
  public int getTotalOfRoom() {
    return this.totalRooms;
  }

  @Override
  public int getTotalOfItem() {
    return this.totalItems;
  }

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

  @Override
  public void moveDrLucky() {
    this.drLucky.moveDrLucky();
  }

  /**
   * create graph represent.
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

    g2d.setColor(Color.WHITE);//Set the background color as white
    g2d.fillRect(0, 0, width, height);//Set the background color as white
    g2d.setColor(Color.BLUE); // Set line color as Blue!
    g2d.setFont(font);

    for (Room room : roomList) {
      int y = room.getTopRowY() * scale + padding;
      int x = room.getTopColX() * scale + padding;
      int roomH = scale * (room.getBotRowY() - room.getTopRowY() + 1);
      int roomW = scale * (room.getBotColX() - room.getTopColX() + 1);
      g2d.drawString(room.getRoomName(), x + scale / 2, y + scale / 2);
      int roomNumber = room.getRoomNumber();
      if (roomNumber == this.drLucky.getCurrentRoomNumber()) {
        String drLuckyWithHp = String.format("%s (hp:%d)", this.drLucky.getName(),
            this.drLucky.getCurrentHp());
        g2d.setColor(Color.RED); // Set line color as RED!
        g2d.drawString(drLuckyWithHp, x + scale / 2, y + scale);
        g2d.setColor(Color.BLUE); // Set line back to Blue!
      }
      g2d.drawRect(x, y, roomW, roomH);
    }
    g2d.dispose();

    try {
      // Save the BufferedImage to a file (optional)
      File output = new File("newCreatedMap.png");
      ImageIO.write(bufferedImage, "png", output);
      System.out.println("Image saved to: " + output.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bufferedImage;

  }

  public void printWorld2DArray() {
    Integer[][] array = this.world2dArray;
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        if (Objects.isNull(array[i][j])) {
          System.out.print("——" + " ");
        } else {
          if (array[i][j] < 10) {
            System.out.print("0" + array[i][j] + " ");
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
   * Print all the room info for check
   */
  public void printAllRoomInfo() {
    for (Room aRoom : this.roomList) {
      System.out.println(getOneRoomInfo(aRoom.getRoomName()));
    }
  }

}
