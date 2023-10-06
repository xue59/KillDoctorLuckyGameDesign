package model.world;

import model.drlucky.DrLucky;
import model.drlucky.DrLuckyImplement;
import model.item.Item;
import model.room.Room;
import model.room.RoomImplement;

import java.util.*;

public class CreateWorldHelper {
  private int totalRooms;
  private int totalItems;
  private int rowSize;
  private int colSize;
  private String name; // World Name
  private DrLucky drLucky;
  private final Map<Integer, Set<Integer>> worldNeighborMap;
  private final List<Room> roomListRoom;
  private final List<String> itemListString;
  private final List<Item> itemListItem;
  private Integer[][] worldMapWithRmNum;

  public CreateWorldHelper() {
    this.roomListRoom = new ArrayList<>();
    this.itemListString = new ArrayList<>();
    this.itemListItem = new ArrayList<>();
    this.worldNeighborMap = new HashMap<>();
    // check it readable is null throw invalid
  }

  public CreateWorldHelper readBuildTxtFile(Readable readable) {
    Scanner inputText = new Scanner(readable);
    //    print out all the text to check
    //    while (input.hasNext()){
    //      System.out.println(input.next());
    //    }
    // check if file txt format is right and int postion in txt can not be String.
    // 1st parsing the first 3 lines, map size and name and target hp and total rooms
    int rows = Integer.parseInt(inputText.next());
    int cols = Integer.parseInt(inputText.next());
    String worldName = inputText.nextLine().trim();

    int hp = Integer.parseInt(inputText.next());
    String drLuckyName = inputText.nextLine().trim();

    int intTotalRooms = Integer.parseInt(inputText.next());

    System.out.println(String.format("%d, %d, %s", rows, cols, worldName));
    System.out.println(
        String.format("HP: %d, target name: %s, total rooms: %d", hp, drLuckyName, intTotalRooms));
    this.createRowColSizeWorldName(rows, cols, worldName);    // create row col size
    this.createDrLucky(drLuckyName, hp, (intTotalRooms - 1)); // create Dr Lucky
    // create numbers of rooms and check valid?
    if (intTotalRooms <= 0) {
      throw new IllegalArgumentException("Error: Total Rooms cannot be Negative or Zero.");
    }
    this.totalRooms = intTotalRooms;                          // create total rooms
    //以上代码 完成前三行 line 扫描 & 创建

    //Now Scan throw room list and create rooms:
    this.worldMapWithRmNum = new Integer[this.rowSize][this.colSize]; // init worldMapWithRmNum

    for (int i = 0; i < this.totalRooms; i++) {
      int topRowY = Integer.parseInt(inputText.next());
      int topColX = Integer.parseInt(inputText.next());
      int botRowY = Integer.parseInt(inputText.next());
      int botColX = Integer.parseInt(inputText.next());
      String rmName = inputText.nextLine().trim();
      this.createARoom(rmName, i, topRowY, topColX, botRowY, botColX);
    }
    System.out.println("Checking created rooms: ");
    for (Room aRoom : roomListRoom) {
      System.out.println(String.format("%d %d %d %d %s", aRoom.getTopRowY(), aRoom.getTopColX(),
          aRoom.getBotRowY(), aRoom.getBotColX(), aRoom.getRoomName()));
    }
    print2DArray(this.worldMapWithRmNum);

    return null;
  }

  // helper function parse the 1st line set row and cols and world name.
  private void createRowColSizeWorldName(int row, int col, String worldName) {
    if (row > 0 && col > 0) {
      this.rowSize = row;
      this.colSize = col;
    } else {
      throw new IllegalArgumentException("Error: row or col size cannot be negative or 0.");
    }
    if (!(worldName.isEmpty())) {
      this.name = worldName;
    } else {
      throw new IllegalArgumentException("Error: world name cannot be empty!");
    }
  }

  // create DrLucky
  private void createDrLucky(String name, int hp, int maxRoomIndex) {
    this.drLucky = new DrLuckyImplement(name, hp, maxRoomIndex);
  }

  // create rooms:
  private void createARoom(String roomName, int roomNumber, int topRowY, int topColX, int botRowY,
      int botColX) {
    //check if room already exist
    if (this.checkIfRoomAlreadyExist(roomName)) {
      throw new IllegalArgumentException(
          String.format("Error: %s room already in World.", roomName));
    }
    //check if the room size too big
    if (!(this.checkIfRoomSizeInWorld(topRowY, topColX, botRowY, botColX))) {
      throw new IllegalArgumentException(
          String.format("Erro: room size %s is large or equal to world size.", name));
    }
    //create room obj:
    Room newRoom = new RoomImplement(roomName, roomNumber, topRowY, topColX, botRowY, botColX);
    // check if rooms are overlapping exist roomListRoom:
    this.checkIfRoomOverLapAndFillWorldMap(newRoom.getTopRowY(), newRoom.getTopColX(),
        newRoom.getBotRowY(), newRoom.getBotColX(), newRoom.getRoomNumber());
    this.roomListRoom.add(newRoom); //add new created room to list
  }

  //check if room already exist in the World
  private boolean checkIfRoomAlreadyExist(String name) {
    for (Room aRoom : this.roomListRoom) {
      if (name.equals(aRoom.getRoomName())) {
        return true;
      }
    }
    return false;
  }

  //check if the room is too big
  private boolean checkIfRoomSizeInWorld(int topRowY, int topColX, int botRowY, int botColX) {
    if (topColX >= this.colSize || botColX >= this.colSize || topRowY >= this.rowSize
        || botRowY >= this.rowSize) {
      return false;
      //      throw new IllegalArgumentException(
      //          String.format("Error: room size %s is large or equal to world size.", name));
    } else {
      return true;
    }
  }

  // following function check for over lab of the room in roomListRoom
  private boolean checkIfRoomOverLapAndFillWorldMap(int topRowY, int topColX, int botRowY,
      int botColX, int rmNumber) {
    System.out.println(String.format("roomNumber: %d", rmNumber));
    for (int row = topRowY; row <= botRowY; row++) {
      for (int col = topColX; col <= botColX; col++) {
        if (Objects.isNull(this.worldMapWithRmNum[row][col])) {
          this.worldMapWithRmNum[row][col] = rmNumber;
        } else {
          throw new IllegalArgumentException(
              String.format("Error: overlap room detected! Room line: %d,", rmNumber));
        }
      }
    }
    return false;
  }

  //a function of printing 2D array to check world map with room number is correct
  private void print2DArray(Integer[][] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        if (Objects.isNull(array[i][j])) {
          System.out.print("——" + " ");
        } else {
          if (array[i][j]<10){
            System.out.print("0"+array[i][j] + " ");
          } else{
            System.out.print(array[i][j] + " ");
          }
        }
      }
      System.out.println(); // Move to the next line after each row
    }
  }

}
