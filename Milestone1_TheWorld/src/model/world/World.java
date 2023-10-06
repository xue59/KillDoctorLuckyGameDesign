package model.world;

import java.awt.image.BufferedImage;
import java.util.List;

public interface World {

  List<String> getNeighborsRoomList(String roomName) throws IllegalArgumentException, NullPointerException;

  public String getWorldName();
  int getTotalOfRoom();
  int getTotalOfItem();

  String GetOneRoomInfo(String roomName) throws IllegalArgumentException, NullPointerException;
  void moveDrLucky();

  BufferedImage createGraphBufferedImage();


}
