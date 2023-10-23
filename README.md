KillDoctorLuckyGameDesign Milestone 2 Readme: 
Author: Zack-Haowen Xue Project Github:
https://github.com/xue59/KillDoctorLuckyGameDesign/tree/Milestone2 

Milestone2_Controller

1. Instruction on how to run:
1.1: Once you clone or download the project Repo, unzip the Repo.
1.2: Open terminal under folder of /Milestone2_Controller/res 
1.3: You will see there is a ms2_Controller.jar available.
1.4: In Terminal run command: java -jar ms2_Controller.jar [map txt file directory] [totalPlayersNumber] [totalMaxTurnNumber] 
1.5: If no file map provided, it will run the default file map of mansion.txt with default game setting totalPlayers=3 and totalTurns=9. 
1.6: Once the game started you will see the Welcome Screen, you can execute following command: 0-create map graph, 1-setup all players, 2-Find info on a specific room, 3-Find info on a specific player, 4-Start the game turns, 66-quit program. 
1.7: Once in the game, follow the instruction to take turns on playing the game. 
1.8: Currently, the game would only quit when max turns reached. Once Game over, you can use m or M to return main menu. 

2. Example map txt file: 
Example Map Files .txt can be found in /res/ folder, there are 3 sample .txt map file you can choose to start and run the Jar file
mansion2023.txt - is the standard Mile Stone 1 provided Dr.Lucky's Mansion file.
customerWorld.txt - is slightly changed room names based on Dr.Lucky's Mansion.
myOwnWorldMapV1.txt - is my designed map.txt file
After each run, check the generated pictures in the same /res folder. 

3. Example Runs 
The example runs can be found in folder: /res/ExampleRun_MS2. 
There are two example run provided. I will take the example run 1 as a demonstration here: 
Line 1: indicate that the program started with user running Jar file and use mansion2023 as map txt file, with input total 3 players and total 6 turns. So 3 players would share the 6 turns, 2 turns per player to finish the game. 
Line 4-14: displayed the Main Menu for user to select. 
Line 15-64: The user is setting up the player info with player name, initial room, limit and computer or human player. Thus, finish the player setting up by confirming all player created success. Select m back to main menu. 
Line 75-16: The user started the game play, and taking turns from player #1 to #3 in an order that when players were created. For example, we can see the human1 moved to Armory success in line 91. Human2 player performed Look at turn#2. Human1 player pick the item Revolver in Armory and etc. Finally, the game ended by reaching the max turn number 6. 
Line 173: User checked player human1, and program displayed the specific Player info.
Line 197: User checked Room Parlor, and program displayed the specific Room info. 
Line 217: User created the map png file, the programed displayed the picture file saved path and directory. (The png map file can be found in same folder name: ExampleRun1_WorldMap.png.)
Line 244: User executed order 66 kill the program and Quit program. 

4. Assumptions for the MS2: 
4.1: I assume that the visibility of the game for different players would be their neighboring rooms. 
4.2: Assume that the total number of players and total turns are set when starting running the game program and those setting cannot be changed during the game. 
4.3: Assume the player can only choose from one of three commands: [Move, Pick, Look]. 
4.4: Assume each command would consume 1 turn, and each player can only take 1 command during their turn. 
4.5: Assume the turn take place with the order when players were added.
4.6: Assume the DrLucky must move after each Player's turn. 
4.7: Other intuitive and necessary assumptions that needed to run my game of killing Dr.lucky.
4.8: No duplicated item name, player name, room name and other necessary objects in the game. 

5. Limitations for MS2: 
4.1: Include all assumptions from above. 
4.2: The Game would over only when max turns reached. 
4.3: The players cannot stop the game during the game play turn. Player can quit program in Main Menu. 
4.4: Once the Game Over, user cannot play any more, and must quit and start the program again to play. 
4.5: Computer players would move randomly in the world and always pick items in the room starting with highest damage on the item as long as it has carrying capacity. 
4.6: The world map file will be generated at a fix path with fix file name. 
4.7: Any other necessary limitations that needed to run my game design of killing Dr.Lucky. 

Reference:
https://www.youtube.com/watch?v=9hsPLAB5a9s&ab_channel=fenirob
https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Graphics.html
https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/image/BufferedImage.html
https://www.jetbrains.com/idea/guide/tutorials/hello-world/packaging-the-application/
https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar
https://steamcommunity.com/sharedfiles/filedetails/?id=261218852
https://docs.oracle.com/javase/8/docs/api/java/util/Random.html 
