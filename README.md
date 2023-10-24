KillDoctorLuckyGameDesign Readme
Author: Zack-Haowen Xue
Project Github: https://github.com/xue59/KillDoctorLuckyGameDesign/tree/Milestone2

Milestone2_Controller

1. Instructions on how to run:
1.1: Once you clone or download the project Repo, unzip the Repo.
1.2: Open the terminal under the folder of /Milestone2_Controller/res.
1.3: You will see that there is an ms2_Controller.jar available.
1.4: In the terminal, run the command: java -jar ms2_Controller.jar [map txt file directory] [totalPlayersNumber] [totalMaxTurnNumber].
1.5: If no map file is provided, it will run the default map file mansion.txt with default game settings: totalPlayers=3 and totalTurns=9.
1.6: Once the game starts, you will see the Welcome Screen, and you can execute the following commands: 0-create map graph, 1-setup all players, 2-Find info on a specific room, 3-Find info on a specific player, 4-Start the game turns, 66-quit the program.
1.7: Once in the game, follow the instructions to take turns and play the game.
1.8: Currently, the game will only quit when the maximum number of turns is reached. Once the game is over, you can use 'm' or 'M' to return to the main menu.

2. Example map txt file:
Example Map Files .txt can be found in the /res/ folder. There are 3 sample .txt map files you can choose to start and run the Jar file:
mansion2023.txt - the standard Milestone 1 provided Dr. Lucky's Mansion file.
customerWorld.txt - slightly changed room names based on Dr. Lucky's Mansion.
myOwnWorldMapV1.txt - my designed map.txt file.
After each run, check the generated pictures in the same /res folder.

3. Example Runs:
The example runs can be found in the folder: /res/ExampleRun_MS2.
There are two example runs provided. I will take example run 1 as a demonstration here:
Line 1: indicates that the program started with the user running the Jar file and used mansion2023 as the map.txt file, with input for a total of 3 players and a total of 6 turns. So, 3 players would share the 6 turns, with 2 turns per player to finish the game.
Line 4-14: displays the Main Menu for the user to select.
Line 15-64: The user is setting up the player info with player names, initial rooms, limits, and choosing between computer or human players. This process finishes the player setup, and the user confirms that all players were created successfully. The user selects 'm' to go back to the main menu.
Line 75-16: The user starts the gameplay and takes turns from player #1 to #3 in the order that they were created. For example, we can see that human1 moved to Armory successfully in line 91. Human2 player performed a 'Look' command at turn #2. Human1 player picked up the item 'Revolver' in Armory, and so on. Finally, the game ends by reaching the maximum turn number, 6.
Line 173: User checks player 'human1,' and the program displays the specific player info.
Line 197: User checks the room 'Parlor,' and the program displays the specific room info.
Line 217: User creates the map PNG file, and the program displays the path and directory where the picture file is saved. (The PNG map file can be found in the same folder named: ExampleRun1_WorldMap.png.)
Line 244: User executes command 66 to quit the program.

4. Assumptions for the MS2:
4.1: I assume that the visibility of the game for different players would be their neighboring rooms.
4.2: Assume that the total number of players and total turns are set when starting to run the game program, and those settings cannot be changed during the game.
4.3: Assume the player can only choose from one of three commands: [Move, Pick, Look].
4.4: Assume each command would consume 1 turn, and each player can only take 1 command during their turn.
4.5: Assume the turn takes place in the order when players were added.
4.6: Assume that Dr. Lucky must move after each player's turn.
4.7: Other intuitive and necessary assumptions that are needed to run my game of killing Dr. Lucky.
4.8: No duplicated item names, player names, room names, and other necessary objects in the game.

5. Limitations for MS2:
5.1: Include all assumptions from above.
5.2: The game will only end when the maximum number of turns is reached.
5.3: The players cannot stop the game during the gameplay turn. Players can quit the program in the Main Menu.
5.4: Once the Game Over, the user cannot play anymore and must quit and start the program again to play.
5.5: Computer players will move randomly in the world and always pick items in the room starting with the highest damage on the item as long as it has carrying capacity.
5.6: The world map file will be generated at a fixed path with a fixed file name.
5.7: Any other necessary limitations that are needed to run my game design of killing Dr. Lucky.

6. Change and Version History v2.0:
6.1: From v1.0 the milestone 1, players models are introduced.
6.2: Synchronous Controller Controller introduced along with MockWorld testing method.
6.3: Necessary changes in World and World Implementation to accommodate the Synchronous Controller introduced in this version.
6.3: The controller would run the program and play the game in a command line interface.
6.4: All other necessary reversion and changes to accommodate the new introduced feature and requirements. For more details please refer to the Project GitHub Repository for detailed commit history: https://github.com/xue59/KillDoctorLuckyGameDesign/tree/Milestone2 
6.5: Command instruction feature introduced to accommodate game play, players can give Move, Look, Pick command during the game play, as well as enquiry a player or room specific information. 
6.6: Detailed design change please refer to Milestone2_ControllerUMLPreliminaryDesignV2.pdf in the resource folder.
6.7: Mock World Molde also introduced and designed for testing purpose. 




References:
- https://www.youtube.com/watch?v=9hsPLAB5a9s&ab_channel=fenirob
- https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Graphics.html
- https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/image/BufferedImage.html
- https://www.jetbrains.com/idea/guide/tutorials/hello-world/packaging-the-application/
- https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar
- https://steamcommunity.com/sharedfiles/filedetails/?id=261218852
- https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
