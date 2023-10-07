# KillDoctorLuckyGameDesign ReadMe:
Author: Zack-Haowen Xue
Project Github: https://github.com/xue59/KillDoctorLuckyGameDesign

MileStone1_TheWorld 
1. Instruction on how to run:
   1. Once you clone or download the project Repo, unzip the Repo. 
   2. Open terminal under folder of /Milestone1_TheWorld/res
   3. You will see there is a Milestone1_TheWorld.jar available. 
   4. You can put your own txt World build file under the same /res folder to run and build your Map
   5. In Terminal run command: java -jar Milestone1_TheWorld.jar "Absolute file path of your input Map txt file"
   6. If no file map provided, it will run the default file map of mansion.txt. 
   7. After each run you can open the Folder: /Milestone1_TheWorld/Milestone1_TheWorld/res, you will see an newCreatedMap.png created which is the World Map of your txt file!

2. Example Map Files .txt can be found in /res/ folder, there are  3 sample .txt map file you can choose to start and run the Jar file 
   1. mansion2023.txt - is the standard Mile Stone 1 provided Dr.Lucky's Mansion file. 
   2. customerWorld.txt - is slightly changed room names based on Dr.Lucky's Mansion. 
   3. myOwnWorldMapV1.txt - is my designed map.txt file 
   4. After each run, check the generated pictures in the same /res folder. 

3. Example Run: (Result can be found in /res/ExampleRunsForProject1 )
   1. Example Run 1 
      1. Line 4: The result shows just after new Mansion.txt just created, Dr Lucky have NOT yet move any steps, so he is in Room #0-Armory. 
      2. Line 5 to 25: It shows all the Room # with its neighbor Room #. (Work as a bi-directional AdjList). 
      3. Open the "AfterExampleRun1.png" in the same Folder, the picture shows this 1st run result, thus compare with the mansion.txt, we can know the Neighbor list is correct.
      4. In this game the Neighbor list is assumed can be seen from the current room. 
      5. Line 26 to 88: Those show all the room info with the items in the room, as well as the item damage amount.
      6. Line 26 to 88: Also show the room info with its neighboring room Name String for easy identification. 
   2. Example Run 2 result can be found in /res/ExampleRunsForProject1 (Also check "AfterExampleRun2.png")
      1. Line 1: The result shows Dr.Lucky moved 1 step. 
      2. In "AfterExampleRun2.png", we can see indeed DrLucky moved to room #1. (0 index based room numbers).
      3. (Rest items & neighbors are the same, etc.) 
   3. Example Run 3 result can be found in res/ExampleRunsForProject1 (Also check "AfterExampleRun3.png").
      1. Line 1: The result shows Dr.Lucky moved 2 step. 
      2. (Rest items & neighbors are the same, etc.) 
   4. Example Run 4 result can be found in /res/ExampleRunsForProject1 (Also check "AfterExampleRun4.png").
      1. Line 1: The result shows Dr.Lucky moved total 25 steps. And he is back to Room #3-Drawing Room. 
      2. Check the picture "AfterExampleRun4.png", the Dr.Lucky indeed moved to Room#3 Drawing Room. 
      3. (Rest items & neighbors are the same, etc.)

4. Enjoy! Cheers! 





Reference: 
1. https://www.youtube.com/watch?v=9hsPLAB5a9s&ab_channel=fenirob 
2. https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Graphics.html 
3. https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/image/BufferedImage.html 
4. https://www.jetbrains.com/idea/guide/tutorials/hello-world/packaging-the-application/ 
5. https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar 




