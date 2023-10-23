import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.world.CreateWorldHelper;
import model.world.World;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the WorldImplement Test intent to test method of WorldImplement.
 */
public class Ms1WorldImplementTest {
  private World testGoodWorld1;

  /**
   * Set up before World test to test testWorld.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    //setup the test
    FileReader fileReader = new FileReader("res/testRes/TestMansionGood.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    this.testGoodWorld1 = (World) createHelper.createWorld();

    //manually set up the test benchMark, manually run separately from Driver.java

  }

  /**
   * Test a good world can be constructed from the txt file. And compare the property of the files
   * to make sure it's the same file and same World.
   */
  @Test
  public void testAgoodWorldCreatedSuccess() {
    //test total room number is correct
    assertEquals(21, this.testGoodWorld1.getTotalOfRoom());
    //test total room Item is correct
    assertEquals(20, this.testGoodWorld1.getTotalOfItem());
    //test world name is correct
    assertEquals("Doctor Lucky's Mansion", this.testGoodWorld1.getWorldName());
    //random select 3 the room info(location & name) make sure they are match
    // the 3 room expected values are achieved from manually running Driver.java separately.
    assertEquals("#8 Room: Kitchen, has items: [Crepe Pan(Damage=3), "
            +"Sharp Knife(Damage=3)]\n"
            +"Players in this room: \n"
            +"Room neighbors: Test44Good,Wine Cellar,Parlor,\n",
        this.testGoodWorld1.getOneRoomInfo("Kitchen"));
    assertEquals("#1 Room: Test22Good, has items: [Billiard Cue(Damage=2)]\n"
            +"Players in this room: \n"
            +"Room neighbors: Test11Good,Trophy Room,Test44Good,\n",
        this.testGoodWorld1.getOneRoomInfo("Test22Good"));
    assertEquals("#4 Room: Test55Good, has items: [Letter Opener(Damage=2)]\n"
            +"Players in this room: \n"
            +"Room neighbors: Test11Good,Test44Good,Wine Cellar,Foyer,\n",
        this.testGoodWorld1.getOneRoomInfo("Test55Good"));
  }

  /**
   * Test a success constructed World and check their One Neighbor & items are correct.
   */
  @Test
  public void testGoodWorldWithOneNeighborAndItems() throws FileNotFoundException {
    String greenHouseString = this.testGoodWorld1.getOneRoomInfo("Green House");
    assertEquals("#6 Room: Green House, has items: [Trowel(Damage=2), "
        +"Pinking Shears(Damage=2)]\n"
        +"Players in this room: \n"
        +"Room neighbors: Hedge Maze,\n", greenHouseString);
  }

  /**
   * Test a success constructed World and check their 3 neighbors & items are correct.
   */
  @Test
  public void testGoodWorldWithThreeNeighborsAndItems() throws FileNotFoundException {
    String dinningHall = this.testGoodWorld1.getOneRoomInfo("Trophy Room");
    assertEquals("#18 Room: Trophy Room, has items: "
        + "[Duck Decoy(Damage=3), Monkey Hand(Damage=2)]\n"
        + "Players in this room: \n"
        + "Room neighbors: Tennessee Room,Test22Good,Test44Good,Library,\n", dinningHall);
  }

  /**
   * Test the input mansion txt file does not have a World Name, or world name is Null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWorldNameNullFailed() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestWorldNameNullFailed.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World worldFailed = (World) createHelper.createWorld();
  }

  /**
   * Test with input incorrect txt file and rooms overlap each other, construct World Failed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverLappedRoomWorldFailed() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestWorldOverlap.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World worldFailed = (World) createHelper.createWorld();
  }

  /**
   * Test World construct Failed with oversize room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverSizeRoomWorldFailed() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestWorldRoomOverSize.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World worldFailed = (World) createHelper.createWorld();
  }

  /**
   * Test World construct Failed with negative roomSize.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSizeRoomWorldFailed() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestWorldRoomNegativeSize.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World worldFailed = (World) createHelper.createWorld();
  }

  /**
   * Test World construct Failed with Item is outside of the RoomNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testItemOverSizeRoomNumbersWorldFailed() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestWorldItemNotInWorld.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World worldFailed = (World) createHelper.createWorld();
  }

  /**
   * Test to construct a World with an isolated Room,(no Neighbor). After running this World
   * contains an isolated room, the graph created in Test/res indicated the room Nursery #10 does
   * not have a neighbor, call method and compare.
   */
  @Test
  public void testGoodWorldWithNoNeighbors() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/TestIsolatedRoom.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    World testGoodWorldWitNoNeighbor = (World) createHelper.createWorld();
    // following assert can see the Neighbors string is empty.
    assertEquals("#10 Room: Nursery, has items: []\n"
            + "Players in this room: \n"
            + "Room neighbors: \n",
        testGoodWorldWitNoNeighbor.getOneRoomInfo("Nursery"));
  }

  /**
   * Test get a room with no item from the World.
   */
  @Test
  public void testGoodWorldAroomWithNoItem() throws FileNotFoundException {
    String roomWithNoItem = this.testGoodWorld1.getOneRoomInfo("Parlor");
    // following assert can see the Neighbors string is empty.
    assertEquals("#14 Room: Parlor, has items: []\n"
        +"Players in this room: \n"
        +"Room neighbors: Servants' Quarters,Tennessee Room,Test44Good,Kitchen,\n", roomWithNoItem);
  }

  /**
   * Test get a room with only 1 item from the World.
   */
  @Test
  public void testGoodWorldAroomWithOnlyOneItem() throws FileNotFoundException {
    String roomWithOnlyOneItem = this.testGoodWorld1.getOneRoomInfo("Servants' Quarters");
    // following assert can see the Neighbors string is empty.
    // There is only 1 BroomStick in the room.
    assertEquals("#16 Room: Servants' Quarters, has items: [Broom Stick(Damage=2)]\n"
        +"Players in this room: \n"
        +"Room neighbors: Lancaster Room,Lilac Room,Parlor,\n", roomWithOnlyOneItem);
  }

  /**
   * Test get a room with two items from the World.
   */
  @Test
  public void testGoodWorldAroomWithTwoItem() throws FileNotFoundException {
    String roomWithTwoItem = this.testGoodWorld1.getOneRoomInfo("Wine Cellar");
    // following assert can see the Neighbors string is empty.
    // There are two item in the room.
    assertEquals(
        "#19 Room: Wine Cellar, has items: [Rat Poison(Damage=2), "
            +"Piece of Rope(Damage=2)]\n"
            +"Players in this room: \n"
            +"Room neighbors: Test44Good,Test55Good,Kitchen,\n", roomWithTwoItem);
  }

  /**
   * Test DrLucky at initial 0 Room position. The following test will compare DrLucky init location,
   * should be in room #0.
   */
  @Test
  public void testDrLuckyAtInitial0RoomIndex() throws FileNotFoundException {
    String drLuckyInfo = this.testGoodWorld1.getDrLuckyInfo();
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #0-Test11Good", drLuckyInfo);
  }

  /**
   * Test DrLucky after 1 total Move. (Room number is 0-index based). The following test will
   * compare the String of DrLucky after 1 move, should be room#1.
   */
  @Test
  public void testDrLuckyAfter1Move() throws FileNotFoundException {
    this.testGoodWorld1.moveDrLucky();
    String drLuckyInfo = this.testGoodWorld1.getDrLuckyInfo();
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #1-Test22Good", drLuckyInfo);
  }

  /**
   * Test DrLucky after 3 total Moves. (Room number is 0-index based). The 3 continues move are
   * continuously executed, which should bring DrLucky to #3-Test44Good. This can also been
   * obsoleted with drawing out the PNG graph.
   */
  @Test
  public void testDrLuckyAfter3Move() throws FileNotFoundException {
    this.testGoodWorld1.moveDrLucky();
    this.testGoodWorld1.moveDrLucky();
    this.testGoodWorld1.moveDrLucky();
    String drLuckyInfo = this.testGoodWorld1.getDrLuckyInfo();
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #3-Test44Good", drLuckyInfo);
  }

  /**
   * Test DrLucky move over to the list and restart at the begin.
   */
  @Test
  public void testDrLuckyLoopBackToStart() throws FileNotFoundException {
    // initly check the dr lucky is in index 0 room.
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #0-Test11Good",
        this.testGoodWorld1.getDrLuckyInfo());
    for (int i = 1; i <= 21; i++) {
      this.testGoodWorld1.moveDrLucky();
    }
    // from above code even DrLucky already did 21 moves, but still come back to room#0.
    // choose 21 since, there are 21 rooms in the testGoodWorld1.
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #0-Test11Good",
        this.testGoodWorld1.getDrLuckyInfo());
  }

  /**
   * Test DrLucky after 6 total Moves. (Room number is 0-index based). The 3 continues move are
   * continuously executed, which should bring DrLucky to #3-Test44Good. This can also been
   * obsoleted with drawing out the PNG graph.
   */
  @Test
  public void testDrLuckyAfter6Move() throws FileNotFoundException {
    for (int i = 1; i <= 6; i++) {
      this.testGoodWorld1.moveDrLucky();
    }
    String drLuckyInfo = this.testGoodWorld1.getDrLuckyInfo();
    assertEquals("Target name: Doctor Lucky(HP:50)currently in room #6-Green House", drLuckyInfo);
  }

  /**
   * Following code will test the Graph got generated correctly and file name is expected to be
   * "newCreatedMap.png", as defined in the WorldImplement.
   */
  @Test
  public void testMapPngFileGenerated() throws FileNotFoundException {
    // Sleep for 3 seconds
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // Define the file name to be checked
    String fileName = "newCreatedMap.png";

    // Create a File object for the file to be checked
    File fileToDelete = new File(fileName);

    // Check if the file exists before deletion
    if (fileToDelete.exists()) {
      // Delete the file
      if (fileToDelete.delete()) {
        System.out.println("File deleted successfully.");
      } else {
        System.err.println("Failed to delete the file.");
      }
    } else {
      // create a new buffer image to see if it created the same png file name
      this.testGoodWorld1.createGraphBufferedImage();
      //same fileName should appear again.
      assertEquals(true, fileToDelete.exists());
    }
  }
}
