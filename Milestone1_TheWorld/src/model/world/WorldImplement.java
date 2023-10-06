package model.world;

import model.drlucky.DrLucky;
import model.room.Room;
import model.drlucky.DrLucky;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;
import java.util.Map;

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
    if (colSize<=0 || rowSize <=0){
      throw new IllegalArgumentException("Error: row or column size can not be negative or 0.");
    }

    // check if



  }

  @Override
  public List<String> getNeighborsRoomList(String roomName)
      throws IllegalArgumentException, NullPointerException {
    return null;
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
  public String GetOneRoomInfo(String RoomName)
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
}
