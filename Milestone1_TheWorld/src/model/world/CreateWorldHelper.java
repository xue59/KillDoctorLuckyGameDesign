package model.world;

import model.drlucky.DrLucky;
import model.drlucky.DrLuckyImplement;
import model.item.Item;
import model.item.ItemImplement;
import model.room.Room;
import model.room.RoomImplement;

import java.util.Objects;

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
  private Integer[][] worlMap2dRmIndex;

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

    //    System.out.println(String.format("%d, %d, %s", rows, cols, worldName));
    //    System.out.println(
    //        String.format("HP: %d, target name: %s, total rooms: %d", hp, drLuckyName, intTotalRooms));
    this.createRowColSizeWorldName(rows, cols, worldName);    // create row col size
    this.createDrLucky(drLuckyName, hp, (intTotalRooms - 1)); // create Dr Lucky
    // create numbers of rooms and check valid?
    if (intTotalRooms <= 0) {
      throw new IllegalArgumentException("Error: Total Rooms cannot be Negative or Zero.");
    }
    this.totalRooms = intTotalRooms;                          // create total rooms
    //以上代码 完成前三行 line 扫描 & 创建 Row, Col Size, Target 和 hp血量

    //Now Scan throw room list and create rooms:
    if (Objects.isNull(worlMap2dRmIndex)) { // 创建 worlMap2dRmIndex
      this.worlMap2dRmIndex = new Integer[this.rowSize][this.colSize]; // init worlMap2dRmIndex
    }

    for (int i = 0; i < this.totalRooms; i++) {
      int topRowY = Integer.parseInt(inputText.next());
      int topColX = Integer.parseInt(inputText.next());
      int botRowY = Integer.parseInt(inputText.next());
      int botColX = Integer.parseInt(inputText.next());
      String rmName = inputText.nextLine().trim();
      Room newRoom = (Room) this.createARoom(rmName, i, topRowY, topColX, botRowY, botColX);
      // create a new map hashset prepare for the AdjList Map<int, str()>
      this.worldNeighborMap.put(newRoom.getRoomNumber(), new HashSet<>());
    }
    //    System.out.println("Checking created rooms: ");
    //    for (Room aRoom : roomListRoom) {
    //      System.out.println(String.format("%d %d %d %d %s", aRoom.getTopRowY(), aRoom.getTopColX(),
    //          aRoom.getBotRowY(), aRoom.getBotColX(), aRoom.getRoomName()));
    //    }
    //    print2DArray(this.worlMap2dRmIndex);
    // 以上代码 完成 Room 创建 & 2d array world Map

    // Scan Items number and create Item:
    // 先确定 item number合法为正数
    int intTotalItems = Integer.parseInt(inputText.next());
    if (intTotalItems < 0 || intTotalItems == 0) {
      throw new IllegalArgumentException(
          "Error: in readBuildTxtFile item number cannot be 0 or negative");
    }
    this.totalItems = intTotalItems;
    // 再读取 txt里剩余line of items
    for (int i = 0; i < this.totalItems; i++) {
      int appearInRmNum = Integer.parseInt(inputText.next());
      int damage = Integer.parseInt(inputText.next());
      String newItemName = inputText.nextLine().trim();
      Item newItem = (Item) this.createAnItem(newItemName, damage);
      //      System.out.println(String.format("%d %d %s",appearInRmNum,damage,newItemName));
      this.addAnItemToRoom(appearInRmNum, newItem);
    }
    //Above Finish all txt file reading
    //下面代码用于检查 create Obj是否正确
    //    System.out.println("check room list and item list: ");
    //    for (Room aRoom : roomListRoom) {
    //      System.out.println(aRoom.toString());
    //    }
    //    for (Item aItem : itemListItem) {
    //      System.out.println(aItem.toString());
    //    }
    //    System.out.println(this.drLucky.toString());
    // close file input ptr
    inputText.close();

    // create world AdjMap
    this.createWorldNeighborMap();
    //    this.printWorldNeighborMap(this.worldNeighborMap);
    return this;
  }

  public World createWorld() {
    Map<Integer, Set<Integer>> newMap = new HashMap<>(this.worldNeighborMap);
    String newName = new String(this.name);
    List<Room> newRoomList = new ArrayList<>(this.roomListRoom);
    DrLucky newDr = new DrLuckyImplement(this.drLucky.getName(), this.drLucky.getCurrentHp(),
        this.totalRooms - 1);

    // Create a new 2-Darray with the same dimensions as the original for return & create world
    Integer[][] new2dArray = new Integer[this.rowSize][this.colSize];
    // Copy the values from the original array to the new array
    for (int i = 0; i < this.rowSize; i++) {
      for (int j = 0; j < this.colSize; j++) {
        new2dArray[i][j] = this.worlMap2dRmIndex[i][j];
      }
    }

    return new WorldImplement(this.rowSize, this.colSize, newName, this.totalRooms, this.totalItems,
        newRoomList, newDr, newMap, new2dArray);
  }

  // following code create world adj neighbor map

  /**
   * Following create an item and return the class Map<Integer, Set<Integer>> worldNeighborMap.
   *
   * @return void.
   */
  private void createWorldNeighborMap() {
    for (int i = 0; i < this.rowSize; i++) {
      for (int j = 0; j < this.colSize; j++) {
        if (Objects.isNull(this.worlMap2dRmIndex[i][j])) {
          continue;
        }
        // Checking room on the right.
        int curRoomIndex = this.worlMap2dRmIndex[i][j];
        int newNeighbor;
        if (j + 1 < this.colSize && Objects.nonNull(this.worlMap2dRmIndex[i][j + 1])
            && curRoomIndex != this.worlMap2dRmIndex[i][j + 1]) {
          newNeighbor = this.worlMap2dRmIndex[i][j + 1];
          try {
            this.worldNeighborMap.get(curRoomIndex).add(newNeighbor);
            this.worldNeighborMap.get(newNeighbor).add(curRoomIndex);
          } catch (NullPointerException e) {
            throw new NullPointerException(String.format(
                "Error in createWorldNeighborMap check right: curIndex-%d newNeighborIndex-%d",
                curRoomIndex, newNeighbor));
          }
        }
        // Checking room to the bottom.
        if (i + 1 < this.rowSize && Objects.nonNull(this.worlMap2dRmIndex[i + 1][j])
            && curRoomIndex != this.worlMap2dRmIndex[i + 1][j]) {
          newNeighbor = this.worlMap2dRmIndex[i + 1][j];
          try {
            this.worldNeighborMap.get(curRoomIndex).add(newNeighbor);
            this.worldNeighborMap.get(newNeighbor).add(curRoomIndex);
          } catch (NullPointerException e) {
            throw new NullPointerException(String.format(
                "Error in createWorldNeighborMap check right: curIndex-%d newNeighborIndex-%d",
                curRoomIndex, newNeighbor));
          }
        }
      }
    }
  }

  private Item createAnItem(String createdItem, int damage) {
    // item name cannot be repeated
    for (Item anItem : itemListItem) {
      if (createdItem.equals(anItem.getName())) {
        throw new IllegalArgumentException(
            String.format("Error: item duplicated for %s", createdItem));
      }
    }
    return new ItemImplement(createdItem, damage);
  }

  private void addAnItemToRoom(int roomNum, Item addedItem) {
    Objects.requireNonNull(addedItem);
    if (roomNum >= 0 && roomNum <= this.totalRooms - 1) {
      this.itemListItem.add(addedItem);
      this.roomListRoom.get(roomNum).addOneItem(addedItem);
    } else {
      throw new IllegalArgumentException(
          String.format("Error addAnItemToRoom: room number oversize! Item: %s",
              addedItem.getName()));
    }
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
  private Room createARoom(String roomName, int roomNumber, int topRowY, int topColX, int botRowY,
      int botColX) {
    //check if room already exist
    if (this.checkIfRoomAlreadyExist(roomName)) {
      throw new IllegalArgumentException(
          String.format("Error: %s room already in World.", roomName));
    }
    //check if the room size too big
    if (!(this.checkIfRoomSizeInWorld(topRowY, topColX, botRowY, botColX))) {
      throw new IllegalArgumentException(
          String.format("Error: room size %s is large or equal to world size.", name));
    }
    //create room obj:
    Room newRoom = new RoomImplement(roomName, roomNumber, topRowY, topColX, botRowY, botColX);
    // check if rooms are overlapping exist roomListRoom:
    this.checkIfRoomOverLapAndFillWorldMap(newRoom.getTopRowY(), newRoom.getTopColX(),
        newRoom.getBotRowY(), newRoom.getBotColX(), newRoom.getRoomNumber());
    this.roomListRoom.add(newRoom); //add new created room to list
    return newRoom;
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
    //    System.out.println(String.format("roomNumber: %d", rmNumber));
    for (int row = topRowY; row <= botRowY; row++) {
      for (int col = topColX; col <= botColX; col++) {
        if (Objects.isNull(this.worlMap2dRmIndex[row][col])) {
          this.worlMap2dRmIndex[row][col] = rmNumber;
        } else {
          throw new IllegalArgumentException(
              String.format("Error: overlap room detected! Room line: %d,", rmNumber));
        }
      }
    }
    return false;
  }

  //a function of printing 2D array to check world map with room number is correct
  public void print2DArray(Integer[][] array) {
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
   *
   * @param worldNeighborMap A map where keys are room numbers (Integer) and values are sets of
   *                         neighboring room numbers (Set<Integer>).
   */
  public void printWorldNeighborMap(Map<Integer, Set<Integer>> worldNeighborMap) {
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

}
