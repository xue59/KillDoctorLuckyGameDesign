KillDoctorLuckyGameDesign README
Author: Zack-Haowen Xue
Project Github: https://github.com/xue59/KillDoctorLuckyGameDesign/tree/Milestone4

Milestone4_GamePlay with GUI & View

1. Instructions on how to run:
   **GUI interface:**
    1. Example command: java -jar ms4_GameView.jar mansion2023Pet.txt 2 4 gui
    3. Once started the GUI interface, top left corner to the JMenu Bar to re-start, reload a
       World or quit the game.
    4. In welcome screen, click Start Game to play.
    5. You need to follow the GUI instruction to add correct amount of players to play.
    6. Once into the Game world, left side is the Board Game map for you to view, right side is
       the game status and additional information.
    7. You can control the actions by keyboard: A, P, T, M, L or use the button to control.
    8. Once game over, click the quit to quit game.
    
   **Console Text based interface:**
   1.1 Once you clone or download the project Repo, unzip the Repo.  
   1.2 Open the terminal under the folder of /Milestone3_GamePlay/res
   1.3 Find the jar file called ms3_GamePlay.jar.
   1.4 In the terminal under the res folder, run the command: java -jar ms4_GameView.jar.jar [map
   txt file directory] [totalPlayersNumber] [totalMaxTurnNumber] [gui or console]
   1.5: Please provide the map txt file, total player and max turn exact as above pattern, for
   example: java -jar ms3_GamePlay.jar mansion2023Pet.txt 2 99 this would start with 2 players
   and 99 turns.
   1.6: Once the game starts, you will see the Welcome Screen, and you can execute the following
   commands: 0-create map graph, 1-setup all players, 2-Find info on a specific room, 3-Find
   info on a specific player, 4-Start the game turns, 66-quit the program.
   1.7: Once in the game, follow the instructions to take turns and play the game.
   1.8: Currently, the game will over when the maximum number of turns is reached or target, Dr.
   Lucky, got completely killed. Once the game is over, you can use 'm' or 'M' to return to the
   main menu.
   1.9: To exit the game program, in main menu execute 66 to kill the program. Enjoy the game!


2. Example map txt file:
   a. Example Map Files .txt can be found in the /res/ folder. There are 3 sample .txt map files
   you can choose to start and run the Jar file:
   b. mansion2023Pet.txt - the standard Milestone 1 provided Dr. Lucky's Mansion file with Pet
   added and modified item damage, a few item damage are enhanced to make it easy on killing Dr.
   Lucky.
   c. customerWorld.txt - slightly changed room names based on Dr. Lucky's Mansion.
   d. myOwnWorldMapV2Pet.txt - my designed map.txt file with pet added. 
   e. After each run, check the generated pictures in the same /res folder.


3. MS4 GUI Example run with explanations:
   Please refer to the "res/ExampleRunMS4.pdf"  to see each screen of the game GUI with real 
   game play result. The screenshot and text explanation should be clear enough in the 
   ExampleRunMS4.pdf.

4. Assumptions up to MS4:
   4.1: I assume that the visibility of the game for different players would be their neighboring
   rooms. Thus, players in neighboring room can see each other with out pet effect. If players can
   be seen each other they can not execute the attack or the attack will be failed.
   4.2: Assume that the total number of players and total turns are set when starting to run the
   game program, and those settings cannot be changed during the game.
   4.3: Assume the player can only choose from one of 5
   commands: [Move, Pick, Look, PetMove, Attack].
   4.4: Assume each command would consume 1 turn, and each player can only take 1 command during
   their turn.
   4.5: Assume the turn takes place in the order when players were added.
   4.6: Assume that Dr. Lucky must move after each player's turn.
   4.7: Other intuitive and necessary assumptions that are needed to run my game of killing Dr.
   Lucky.
   4.8: No duplicated item names, player names, room names, and other necessary objects in the game.
   4.9: In real game play the Pet will not move in a DFS pattern, due to hard to play and
   validation. But DFS for pet moving feature is programmed for extra credit and testing purpose.
   The DFS implementation can be found in the source code.
   4.10 Assume that when Dr. Lucky in are not in the room with players, the player cannot execute
   attempt of attack Dr. Lucky. The system will not give the option for customers to attempt kill if
   target is not in the same room with the player.
   4.11 Assume the user can properly read and parse the English word game description on the command
   line and make rational choices base on the game rule.
   4.12 Assume the items in the game can all be consumed once, once used it will remove from the
   game. Reuse the same item will cause error/warnings to the user.
   4.13 Assume the Pet effect will be acting as a cloud in a single room, if a pet present in a room
   this room’s item and players will not be seen from outside, but inside players can see each
   other.
   4.14. Assume if the Pet in neighboring room, and player execute look around command will still
   see the room name as its neighbor, but will not be able seen its inside, the player inside will
   not be seen, then the player inside room with pet can still kill Dr. Lucky if that is possible.
   4.15 Assume user can follow the basic instruction on the GUI interface.
   4.16 In GUI user interface, to optimize the user player strategy, when they pick they will 
   always automatically pick the highest damage item in the room. 
   4.17 In GUI user interface, to optimize the user player strategy, when they attack they will
   always automatically use the highest damage item to attack Dr Lucky, if they choose to attack. 
   4.18 In GUI, once attack failed, the user can not redo their turn. So assume the user can 
   read the map and make rational decision on attempt to kill. 
   4.19 In GUI, assume user would have a valid map in the res directory in order to start a new 
   game.


5. Limitations up to MS4:
   5.1: Include all assumptions from above.
   5.2: The game will only end when the maximum number of turns is reached.
   5.3: The players cannot stop the game during the gameplay turn. Players can quit the program in
   the Main Menu.
   5.4: Once the Game Over, the user cannot play anymore and must quit and start the program again
   to play.
   5.5: Computer players will move randomly in the world and always pick items in the room starting
   with the highest damage on the item as long as it has carrying capacity.
   5.6: The world map file will be generated at a fixed path with a fixed file name base on given.
   5.7: Any other necessary limitations that are needed to run my game design of killing Dr. Lucky.
   5.8: Computer player will be able to attempt attack target, and if it knows it will be seen it
   will not attack the target. When computer attacks, it will always try to use its highest damage
   item, it no items available, it will just use the basic poking attack.
   5.9: Computer player will act command with following priority in order: attempt attack, pick,
   move, and look around. The computer player will not move pet, as it’s not smart enough yet.
   5.10: At beginning of each player’s turn, the system will remind the current player their room
   info but will not show neighbor room info(such as items, players, those neighbor info need to be
   get by look around command). But the customer will tell the user if the Dr. Lucky is in their
   current room or not. This will make the game more playable and user-friendly.
   5.11: If the attack failed due to item name input error or used item, the system will remind the
   player try to use another item and try attack again.
   5.12Pet remain stationary for most of the time unless move, or it can be activated for DFS move,
   this will increase the game difficulty, and hard to valid on command line interface thus not
   recommended in game play. But can be tested with test cases and unit test.  
   5.13: If the attack failed due to be seen by others, the system will count this turn and pass,
   let player know attack failed but will not remove item since the item did not take effect. The
   player may use the item again next time.
   5.14. Including the assumption from GUI sections. 
   5.15. User need to input correct jar command in terminal to start the GUI game. 
   5.16. If the argument is not enough, it will always start the Console game.  
   5.17. There is no automated testing for GUI interface, to truly test the GUI, a human player 
   need to eyeball the result from the GUI screen. Thus mock GUI view model and mock GUI 
   controller is introduced to testing the class feature at the best they can.  


6. Change and Version History v4.0:
   6.1: From v1.0 the milestone 1, players models are introduced.
   6.2: Synchronous Controller introduced along with MockWorld testing method.
   6.3: Necessary changes in World and World Implementation to accommodate the Synchronous
   Controller introduced in this version.
   6.3: The controller would run the program and play the game in a command line interface.
   6.4: All other necessary reversion and changes to accommodate the new introduced feature and
   requirements. For more details please refer to the Project GitHub Repository for detailed commit
   history: https://github.com/xue59/KillDoctorLuckyGameDesign/tree/Milestone3
   6.5: Command instruction feature introduced to accommodate game play, players can give Move,
   Look, Pick, Attack, PetMove command during the game play, as well as enquiry a player or room
   specific information.
   6.6: Detailed design change please refer to Milestone3_PreliminaryDesignV3.pdf in
   the resource folder.
   6.7: Mock World Model also introduced and designed for testing purpose.
   6.8: In v3.0 game play is completed, the pet feature is added into the game.
   6.9: Pet will affect the visibility, more detail see above assumption and limitation.
   6.10: Pet can be moved by players, this count as a turn.
   6.11: Pet remain stationary for most of the time unless move, or it can be activated for DFS
   move, this will increase the game difficulty, and hard to valid on command line interface thus
   not recommended in game play. But can be tested with test cases and unit test.  
   6.12: Attack command introduced in the game, see rule in above assumption and limitation.
   6.13: Add supporting for GUI Game play. 
   6.14: Add View for GUI and console. 
   6.15: Add Mock View model and GUI controller model for testing purpose. 

**References:**
- https://www.youtube.com/watch?v=9hsPLAB5a9s&ab_channel=fenirob
- https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Graphics.html
- https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/image/BufferedImage.html
- https://www.jetbrains.com/idea/guide/tutorials/hello-world/packaging-the-application/
- https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar
- https://steamcommunity.com/sharedfiles/filedetails/?id=261218852
- https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
- https://store.steampowered.com/app/945360/Among_Us/ 
- https://www.shutterstock.com/zh/image-vector/cartoon-knight-running-fierce-dragon-castle-74602597
