package model.world;

import java.awt.image.BufferedImage;
import java.util.List;

public interface World {

  List<String> getNeighborsRoomList(String roomName) throws IllegalArgumentException, NullPointerException;

  public String getWorldName();
  int getTotalOfRoom();
  int getTotalOfItem();

  String getOneRoomInfo(String roomName) throws IllegalArgumentException, NullPointerException;
  void moveDrLucky();

  BufferedImage createGraphBufferedImage();

  public void printWorldNeighborMap() ;
  public void printWorld2DArray();
  public String getDrLuckyInfo();
  public void printAllRoomInfo();


}
