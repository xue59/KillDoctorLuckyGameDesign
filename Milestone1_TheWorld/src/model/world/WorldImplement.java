package model.world;

import model.drlucky.DrLucky;
import model.room.Room;
import model.drlucky.DrLucky;

import java.awt.image.BufferedImage;
import java.util.*;

public class WorldImplement implements World {
  private final int colSize;
  private final int rowSize;
  private final String name;
  private final int totalRooms;
  private final int totalItems;
  private final List<Room> roomList;
  private final DrLucky drLucky;
  private final Map<Integer, Set<Integer>> worldMapIndex;
  private final Map<Room, Set<Room>> worldMapRoom;

  public WorldImplement(int rowSize, int colSize, String name, int totalRooms, int totalItems,
      List<Room> roomList, DrLucky drLucky, Map<Integer, Set<Integer>> worldMapIndex,
      Map<Room, Set<Room>> worldMapRoom) {
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
    Objects.requireNonNull(worldMapRoom);

    //All input checked valid, starting assign:
    this.colSize = colSize;
    this.rowSize = rowSize;
    this.name = name;
    this.totalItems = totalItems;
    this.totalRooms = totalRooms;
    this.roomList = roomList;
    this.drLucky = drLucky;
    this.worldMapIndex = worldMapIndex;
    this.worldMapRoom = worldMapRoom;
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
    for (Integer anIndex : neighboringRoomIndex){
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
  public String GetOneRoomInfo(String roomName)
      throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(roomName);
    Room room = this.roomList.get(getRoomNumByNameString(roomName));

    //check find all the item in the room & append to the string
    StringBuilder roomInfo = new StringBuilder();
    roomInfo.append(room.toString()+"\n");

    //check if DrLucky in this room
    Room drLuckyRoom = this.roomList.get(drLucky.getCurrentRoomNumber());
    if(drLuckyRoom.equals(room)){ // if dr lucky in the same room append the string
      roomInfo.append(String.format("DrLucky(%s) in this room.\n", this.drLucky.getName()));
    }

    //check the rooms neighbors info
    List<String> neighbors = this.getNeighborsRoomList(roomName);
    roomInfo.append("Neighbors: ");
    for (String neighbor : neighbors){
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

  @Override
  public BufferedImage createGraphBufferedImage() {
    return null;
  }
}
