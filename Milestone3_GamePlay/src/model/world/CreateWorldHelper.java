package model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import model.drlucky.DrLucky;
import model.drlucky.DrLuckyImplement;
import model.item.Item;
import model.item.ItemImplement;
import model.pet.Pet;
import model.pet.PetImplement;
import model.room.Room;
import model.room.RoomImplement;


/**
 * The following CreateWorldHelper class is responsible for reading a text file and creating a world
 * with rooms, items, and Dr. Lucky based on the provided information.
 */
public class CreateWorldHelper {
  private final Map<Integer, Set<Integer>> worldNeighborMap;
  private final List<Room> roomListRoom;
  private final List<String> itemListString;
  private final List<Item> itemListItem;
  private int totalRooms;
  private int totalItems;
  private int rowSize;
  private int colSize;
  private String name; // World Name
  private DrLucky drLucky;
  private Integer[][] worldMap2dRmIndex;
  private Pet pet;

  /**
   * Constructs a new CreateWorldHelper instance.
   */
  public CreateWorldHelper() {
    this.roomListRoom = new ArrayList<>();
    this.itemListString = new ArrayList<>();
    this.itemListItem = new ArrayList<>();
    this.worldNeighborMap = new HashMap<>();
    // check it readable is null throw invalid
  }

  /**
   * Reads and parses a text file to build the world, including rooms, items, and Dr. Lucky.
   *
   * @param readable A {@link Readable} object representing the text file.
   * @return The CreateWorldHelper instance with the world data.
   * @throws IllegalArgumentException If there is an error in the input file format or content.
   */
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
    String petName = inputText.nextLine().trim();  // scan read to get pet name
    int intTotalRooms = Integer.parseInt(inputText.next());
    //    System.out.println(String.format("%d, %d, %s", rows, cols, worldName));
    //    System.out.println(
    //    String.format("HP:%d, targetname: %s, total rooms: %d", hp, drLuckyName, intTotalRooms));
    this.createRowColSizeWorldName(rows, cols, worldName);    // create row col size
    this.createDrLucky(drLuckyName, hp, (intTotalRooms - 1)); // create Dr Lucky
    this.pet = new PetImplement(petName); // create pet object in the map
    // create numbers of rooms and check valid?
    if (intTotalRooms <= 0) {
      throw new IllegalArgumentException("Error: Total Rooms cannot be Negative or Zero.");
    }
    this.totalRooms = intTotalRooms;                          // create total rooms

    //以上代码 完成前三行 line 扫描 & 创建 Row, Col Size, Target 和 hp血量, 以及Pet创建

    //Now Scan through room list and create rooms:
    if (Objects.isNull(worldMap2dRmIndex)) { // 创建 worldMap2dRmIndex
      this.worldMap2dRmIndex = new Integer[this.rowSize][this.colSize]; // init worldMap2dRmIndex
    }

    for (int i = 0; i < this.totalRooms; i++) {
      int topRowY = Integer.parseInt(inputText.next());
      int topColX = Integer.parseInt(inputText.next());
      int botRowY = Integer.parseInt(inputText.next());
      int botColX = Integer.parseInt(inputText.next());
      String rmName = inputText.nextLine().trim();
      Room newRoom = this.createAroom(rmName, i, topRowY, topColX, botRowY, botColX);
      // create a new map hashset prepare for the AdjList Map<int, str()>
      this.worldNeighborMap.put(newRoom.getRoomNumber(), new HashSet<>());
    }
    //    System.out.println("Checking created rooms: ");
    //    for (Room aRoom : roomListRoom) {
    //      System.out.println(String.format("%d %d %d %d %s", aRoom.getTopRowY(),
    //      aRoom.getTopColX(),aRoom.getBotRowY(), aRoom.getBotColX(), aRoom.getRoomName()));
    //    }
    //    print2dArray(this.worldMap2dRmIndex);
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
      Item newItem = this.createAnItem(newItemName, damage);
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

  /**
   * Creates a world based on the parsed data.
   *
   * @return A {@link World} instance representing the created world.
   */
  public World createWorld() {
    Map<Integer, Set<Integer>> newMap = new HashMap<>(this.worldNeighborMap);
    String newName = this.name;
    List<Room> newRoomList = new ArrayList<>(this.roomListRoom);
    DrLucky newDr = new DrLuckyImplement(this.drLucky.getName(), this.drLucky.getCurrentHp(),
        this.totalRooms - 1);
    Pet newPet = new PetImplement(this.pet.getName());

    // Create a new 2-Darray with the same dimensions as the original for return & create world
    Integer[][] new2dArray = new Integer[this.rowSize][this.colSize];
    // Copy the values from the original array to the new array
    for (int i = 0; i < this.rowSize; i++) {
      if (this.colSize >= 0)
        System.arraycopy(this.worldMap2dRmIndex[i], 0, new2dArray[i], 0, this.colSize);
    }

    return new WorldImplement(this.rowSize, this.colSize, newName, this.totalRooms, this.totalItems,
        newRoomList, newDr, newPet, newMap, new2dArray);
  }

  // following code create world adj neighbor map

  /**
   * Creates the world's neighbor map, establishing relationships between rooms. This method
   * iterates through the world map's cells, determining neighboring rooms to establish connections
   * in the worldNeighborMap.
   */
  private void createWorldNeighborMap() {
    for (int i = 0; i < this.rowSize; i++) {
      for (int j = 0; j < this.colSize; j++) {
        if (Objects.isNull(this.worldMap2dRmIndex[i][j])) {
          continue;
        }
        // Checking room on the right.
        int curRoomIndex = this.worldMap2dRmIndex[i][j];
        int newNeighbor;
        if (j + 1 < this.colSize && Objects.nonNull(this.worldMap2dRmIndex[i][j + 1])
            && curRoomIndex != this.worldMap2dRmIndex[i][j + 1]) {
          newNeighbor = this.worldMap2dRmIndex[i][j + 1];
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
        if (i + 1 < this.rowSize && Objects.nonNull(this.worldMap2dRmIndex[i + 1][j])
            && curRoomIndex != this.worldMap2dRmIndex[i + 1][j]) {
          newNeighbor = this.worldMap2dRmIndex[i + 1][j];
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

  /**
   * Creates an Item with the specified item name and damage value. This method checks if an item
   * with the same name already exists in the itemListItem. If a duplicate item name is found, it
   * throws an IllegalArgumentException.
   *
   * @param createdItem The name of the item to be created.
   * @param damage      The damage value associated with the item.
   * @return An instance of the created Item.
   * @throws IllegalArgumentException If an item with the same name already exists.
   */
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

  /**
   * Adds the specified item to a room with the given room number.
   *
   * @param roomNum   The room number to which the item will be added.
   * @param addedItem The Item to be added to the room.
   * @throws IllegalArgumentException If the room number is out of bounds or if the item is null.
   */
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

  /**
   * Sets the row size, column size, and world name for creating a world.
   *
   * @param row       The number of rows in the world grid.
   * @param col       The number of columns in the world grid.
   * @param worldName The name of the world.
   * @throws IllegalArgumentException If either row or col is non-positive, or if worldName is
   *                                  empty.
   */
  private void createRowColSizeWorldName(int row, int col, String worldName) {
    // Helper function parse the 1st line set row and cols and world name.
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

  /**
   * Creates a DrLucky object with the specified name, hit points (hp), and maximum room index.
   *
   * @param name         The name of DrLucky.
   * @param hp           The initial hit points of DrLucky.
   * @param maxRoomIndex The maximum room index DrLucky can occupy.
   */
  private void createDrLucky(String name, int hp, int maxRoomIndex) {
    this.drLucky = new DrLuckyImplement(name, hp, maxRoomIndex);
  }

  /**
   * Creates a room with the given parameters and adds it to the list of rooms in the world.
   *
   * @param roomName   The name of the room.
   * @param roomNumber The room number.
   * @param topRowY    The top row coordinate of the room.
   * @param topColX    The top column coordinate of the room.
   * @param botRowY    The bottom row coordinate of the room.
   * @param botColX    The bottom column coordinate of the room.
   * @return The newly created Room object.
   * @throws IllegalArgumentException If the room with the same name already exists or if the room
   *                                  size is too large.
   */
  private Room createAroom(String roomName, int roomNumber, int topRowY, int topColX, int botRowY,
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

  /**
   * Checks if a room with the given name already exists in the world.
   *
   * @param name The name of the room to check.
   * @return True if a room with the same name exists; otherwise, false.
   */
  private boolean checkIfRoomAlreadyExist(String name) {
    for (Room room : this.roomListRoom) {
      if (name.equals(room.getRoomName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the room size specified by the coordinates is within the bounds of the world.
   *
   * @param topRowY The top row coordinate of the room.
   * @param topColX The top column coordinate of the room.
   * @param botRowY The bottom row coordinate of the room.
   * @param botColX The bottom column coordinate of the room.
   * @return True if the room size is within bounds; otherwise, false.
   */
  private boolean checkIfRoomSizeInWorld(int topRowY, int topColX, int botRowY, int botColX) {
    //      throw new IllegalArgumentException(
    //          String.format("Error: room size %s is large or equal to world size.", name));
    return topColX < this.colSize && botColX < this.colSize && topRowY < this.rowSize
        && botRowY < this.rowSize;
  }

  /**
   * Checks for room overlap and fills the world map with room numbers.
   *
   * @param topRowY  The top row coordinate of the room.
   * @param topColX  The top column coordinate of the room.
   * @param botRowY  The bottom row coordinate of the room.
   * @param botColX  The bottom column coordinate of the room.
   * @param rmNumber The room number to assign.
   * @return True if there is no overlap; otherwise, false.
   * @throws IllegalArgumentException If room overlap is detected.
   */
  private boolean checkIfRoomOverLapAndFillWorldMap(int topRowY, int topColX, int botRowY,
                                                    int botColX, int rmNumber) {
    //    System.out.println(String.format("roomNumber: %d", rmNumber));
    for (int row = topRowY; row <= botRowY; row++) {
      for (int col = topColX; col <= botColX; col++) {
        if (Objects.isNull(this.worldMap2dRmIndex[row][col])) {
          this.worldMap2dRmIndex[row][col] = rmNumber;
        } else {
          throw new IllegalArgumentException(
              String.format("Error: overlap room detected! Room line: %d,", rmNumber));
        }
      }
    }
    return false;
  }

  /**
   * Prints a 2D array representing the world map with room numbers to check its correctness.
   *
   * @param array The 2D array containing room numbers.
   */
  public void print2dArray(Integer[][] array) {
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
   * @param worldNeighborMap A map where keys are room numbers Integer and values are sets.
   */
  public void printWorldNeighborMap(Map<Integer, Set<Integer>> worldNeighborMap) {
    for (Map.Entry<Integer, Set<Integer>> entry : worldNeighborMap.entrySet()) {
      Integer roomNumber = entry.getKey();
      Set<Integer> neighborSet = entry.getValue();
      System.out.print("Room " + roomNumber + " Neighbors: ");
      for (Integer neighbor : neighborSet) {
        System.out.print(neighbor + " ");
      }
      System.out.println(); // Move to the next line for the next room.
    }
  }

}
