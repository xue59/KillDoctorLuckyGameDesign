package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The GuiViewImplement class represents the graphical user interface (GUI) for the game view.
 * It extends the JFrame class and implements various screens and components for displaying game
 * information.
 */
public class GuiViewImplement extends JFrame implements WorldView {
  private static final long serialVersionUID = 8609175038441759607L;
  private final JPanel welcomeScreen;
  private final JPanel addPlayerScreen;
  private final JButton buttonStartGame;
  private final JButton buttonAddPlayer;
  private final JButton buttonStartTurns;
  private JTextField playerNameInputBox;
  private JTextField playerCarryLimitBox;
  private JTextField playerInitialRoomNumberBox;
  private JRadioButton humanSelectBtn;
  private JRadioButton computerSelectBtn;
  private final JPanel gameTurnScreen;

  private final JButton moveButton;
  private final JButton lookButton;
  private final JButton pickButton;
  private final JButton petMoveButton;
  private final JButton attackButton;
  private final JButton finishTurn;

  private final JTextArea gameMessageTextArea;
  private final JPanel buttonPanel;
  private final JPanel gameOverScreen;
  private final JButton quitButton;
  private final JMenuBar topBar;
  private final JMenu topOptions;
  private final JMenuItem quitOption;
  private final JMenuItem restartOption;
  private final JMenuItem newWorldOption;
  private final JButton chooseFileButton;
  private final JButton startNewWorldButton;
  private final JPanel newWorldScreen;
  private String newFileNamePath;
  private final JTextField totalPlayerInput;
  private final JTextField totalTurnInput;
  private final JButton showPlayerInfoButton;


  /**
   * Constructs a new GuiViewImplement with the specified header.
   * Initializes various components, layouts, and screens for the game view.
   *
   * @param header The header to be displayed in the GUI.
   */
  public GuiViewImplement(String header) {
    super(header);
    ImageIcon welcomeIcon = new ImageIcon("res/img/welcome.png");
    this.setIconImage(welcomeIcon.getImage());
    this.setLayout(new BorderLayout());
    this.setSize(800, 800);
    this.setLocation(15, 15);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //add menu bar
    this.topBar = new JMenuBar();
    this.topOptions = new JMenu("Options");
    this.quitOption = new JMenuItem("Quit Game");
    this.quitOption.setActionCommand("QUIT");

    this.restartOption = new JMenuItem("Restart Same Game");
    this.restartOption.setActionCommand("RESTART");

    this.newWorldOption = new JMenuItem("Start another new World Game");
    this.newWorldOption.setActionCommand("NEW_WORLD_SETUP");

    this.topOptions.add(this.quitOption);
    this.topOptions.add(this.restartOption);
    this.topOptions.add(this.newWorldOption);

    this.topBar.add(this.topOptions);
    this.setJMenuBar(this.topBar);

    //Set welcome screen
    this.welcomeScreen = new JPanel();
    this.buttonStartGame = new JButton("Start Game!");

    //Set add player screen
    this.addPlayerScreen = new JPanel();
    this.buttonAddPlayer = new JButton("Add This Player");
    this.buttonStartTurns = new JButton("Start Turns!");
    this.buttonStartTurns.setActionCommand("START_TURNS");

    //set game map turn start screen
    this.gameTurnScreen = new JPanel();
    this.moveButton = new JButton("Move (M)");
    this.moveButton.setActionCommand("MOVE");

    this.lookButton = new JButton("Look (L)");
    this.lookButton.setActionCommand("LOOK");

    this.pickButton = new JButton("Pick (P)");
    this.pickButton.setActionCommand("PICK");

    this.attackButton = new JButton("Attack (A)");
    this.attackButton.setActionCommand("ATTACK");

    this.petMoveButton = new JButton("PetMove (T)");
    this.petMoveButton.setActionCommand("PETMOVE");

    this.finishTurn = new JButton("Finish Turn");
    this.finishTurn.setActionCommand("START_TURNS");

    this.gameMessageTextArea = new JTextArea();

    this.buttonPanel = new JPanel(new FlowLayout());
    this.showPlayerInfoButton = new JButton("Show current Player Info");
    this.showPlayerInfoButton.setActionCommand("SHOW_PLAYER_INFO");

    //Game over screen
    this.gameOverScreen = new JPanel();
    this.quitButton = new JButton("Quit Game");
    this.quitButton.setActionCommand("QUIT");

    //new world screen
    this.chooseFileButton = new JButton("Choose new world file"); // 不需要add command

    this.startNewWorldButton = new JButton("Start new World Game");
    this.startNewWorldButton.setActionCommand("START_NEW_WORLD");
    this.newWorldScreen = new JPanel();
    this.newFileNamePath = null;
    this.totalPlayerInput = new JTextField();
    this.totalTurnInput = new JTextField();

  }

  /**
   * Updates the screen in the GUI.
   */
  @Override
  public void updateScreen() {
    this.revalidate();
    this.repaint();
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  /**
   * Displays a general message in the game world.
   *
   * @param msg The message to be displayed.
   */
  @Override
  public void showMsg(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Message: ",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Displays an error message in the game world.
   *
   * @param errorMsg The error message to be displayed.
   */
  @Override
  public void showError(String errorMsg) {
    JOptionPane.showMessageDialog(this, errorMsg, "ERROR!!!",
        JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Adds action listeners to all buttons to the GUI.
   *
   * @param btnAction The action associated with the button.
   */
  @Override
  public void addActionButton(ButtonAction btnAction) {
    this.buttonStartGame.addActionListener(btnAction);
    this.buttonAddPlayer.addActionListener(btnAction);
    this.buttonStartTurns.addActionListener(btnAction);
    this.moveButton.addActionListener(btnAction);
    this.lookButton.addActionListener(btnAction);
    this.pickButton.addActionListener(btnAction);
    this.petMoveButton.addActionListener(btnAction);
    this.attackButton.addActionListener(btnAction);
    this.finishTurn.addActionListener(btnAction);
    this.quitButton.addActionListener(btnAction);
    this.quitOption.addActionListener(btnAction);
    this.restartOption.addActionListener(btnAction);
    this.newWorldOption.addActionListener(btnAction);
    this.chooseFileButton.addActionListener(btnAction);
    this.startNewWorldButton.addActionListener(btnAction);
    this.showPlayerInfoButton.addActionListener(btnAction);
  }

  /**
   * Adds a keyboard action to the GUI.
   *
   * @param keyboardAction The action associated with the keyboard input.
   */
  @Override
  public void addActionKeyboard(KeyboardAction keyboardAction) {
    this.addKeyListener(keyboardAction);
    this.buttonPanel.addKeyListener(keyboardAction);
    this.gameTurnScreen.addKeyListener(keyboardAction);
  }

  /**
   * Displays the welcome GUI.
   */
  @Override
  public void showWelcomeGui() {
    this.welcomeScreen.removeAll();
    this.welcomeScreen.setBackground(new Color(240, 255, 255));
    JLabel label = new JLabel();
    label.setVisible(true);
    ImageIcon welcomeImg = new ImageIcon("res/img/welcome.png");
    label.setIcon(welcomeImg);
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    JLabel label2 = new JLabel();
    label2.setVisible(true);
    ImageIcon boardGameImage = new ImageIcon("res/img/KillDrLuckyImg.png");
    label2.setIcon(boardGameImage);
    label2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    buttonStartGame.setBackground(new Color(235, 236, 207));
    buttonStartGame.setForeground(new Color(20, 89, 5));
    buttonStartGame.setFocusPainted(false);
    buttonStartGame.setFont(new Font("Arial", Font.PLAIN, 33));
    buttonStartGame.setActionCommand("ADD_PLAYER_SCREEN");

    // set grid lay out
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    welcomeScreen.setLayout(grid);
    // 第一个box welcome image
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    welcomeScreen.add(label, gbc);

    // 第二个image
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    welcomeScreen.add(label2, gbc);

    //第3个box button
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 2;
    welcomeScreen.add(buttonStartGame, gbc);
    welcomeScreen.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    //展示
    this.getContentPane().removeAll();
    this.add(this.welcomeScreen);
    this.setVisible(true);
    this.updateScreen();
  }

  /**
   * Displays information about moving in the GUI.
   *
   * @param moveResult The result of the move action.
   */
  @Override
  public void showMoveInfo(String moveResult) {
    this.gameMessageTextArea.setText(moveResult);
    this.showMsg(String.format("%s \nClick Finish Turn to next player!",
        moveResult));
    this.finishTurn.setEnabled(true);
  }

  /**
   * Displays the GUI for adding players to the game.
   *
   * @param totalRoomNumber The total number of rooms in the game.
   * @param totalPlayer     The total number of players in the game.
   * @param totalTurns      The total number of turns in the game.
   */
  @Override
  public void showAddPlayerGui(Integer totalRoomNumber, Integer totalPlayer, Integer totalTurns) {
    this.addPlayerScreen.removeAll(); //清除之前信息

    //set grid
    GridBagConstraints gbc = new GridBagConstraints(); // grid container
    gbc.insets = new Insets(0, 5, 0, 5);
    GridBagLayout grid = new GridBagLayout();
    this.addPlayerScreen.setLayout(grid);

    JLabel header = new JLabel(String.format("Total %d players needed to start game(%d turns):",
        totalPlayer, totalTurns), SwingConstants.CENTER);
    header.setFont(new Font("Arial", Font.PLAIN, 33));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.ipadx = 180;
    gbc.ipady = 18;
    this.addPlayerScreen.add(header, gbc);

    //1st input area - players name
    JLabel playerNameTxt = new JLabel("Player Name:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    this.addPlayerScreen.add(playerNameTxt, gbc);

    this.playerNameInputBox = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 2;
    this.addPlayerScreen.add(playerNameInputBox, gbc);

    //2nd input area - player carrying limit
    JLabel playerCarryLimitTxt = new JLabel("Player Carrying Limit(Positive Integer Only):");
    gbc.gridx = 0;
    gbc.gridy = 3;
    this.addPlayerScreen.add(playerCarryLimitTxt, gbc);

    this.playerCarryLimitBox = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 4;
    this.addPlayerScreen.add(playerCarryLimitBox, gbc);

    //3rd input - player's initial room number
    JLabel playerInitialRoomTxt = new JLabel(String.format(
        "Player's Initial Room Number (Integer Only 0 to %d):", totalRoomNumber));
    gbc.gridx = 0;
    gbc.gridy = 5;
    this.addPlayerScreen.add(playerInitialRoomTxt, gbc);

    this.playerInitialRoomNumberBox = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 6;
    this.addPlayerScreen.add(playerInitialRoomNumberBox, gbc);

    //4th input - choose computer or human player
    humanSelectBtn = new JRadioButton("Human Player Select");
    computerSelectBtn = new JRadioButton("*Computer Player Select");
    computerSelectBtn.setSelected(true);
    ButtonGroup group = new ButtonGroup();
    group.add(humanSelectBtn);
    group.add(computerSelectBtn); // group button together
    gbc.gridx = 0;
    gbc.gridy = 7;
    this.addPlayerScreen.add(humanSelectBtn, gbc);
    gbc.gridx = 0;
    gbc.gridy = 8;
    this.addPlayerScreen.add(computerSelectBtn, gbc);

    // 5th input - add player button row
    this.buttonAddPlayer.setActionCommand("ADD_ONE_PLAYER");
    gbc.gridx = 0;
    gbc.gridy = 9;
    this.addPlayerScreen.add(buttonAddPlayer, gbc);

    // 6th input - start game turn
    this.buttonStartTurns.setEnabled(false); // set button to be gray out
    gbc.gridx = 0;
    gbc.gridy = 10;
    this.addPlayerScreen.add(buttonStartTurns, gbc);

    //重新展示update screen & 添加screen
    this.addPlayerScreen.setVisible(true);
    this.getContentPane().removeAll();
    this.add(this.addPlayerScreen);
    this.updateScreen();
  }

  /**
   * Displays the screen for starting a turn in the GUI.
   *
   * @param turnBeginInfo Information about the beginning of the turn.
   */
  @Override
  public void showStartTurnScreen(String turnBeginInfo) {
    // 清除之前信息
    this.gameTurnScreen.removeAll();

    // 设置背景
    this.gameTurnScreen.setBackground(new Color(240, 255, 255));
    ImageIcon mapIcon = new ImageIcon("newCreatedMap.png");

    // Get the image from the ImageIcon
    Image mapImage = mapIcon.getImage();

    // Calculate the scaled width and height
    int scaledWidth = (int) (mapIcon.getIconWidth() * 2.0 / 3.0);
    int scaledHeight = (int) (mapIcon.getIconHeight() * 2.0 / 3.0);

    // Scale the image
    Image scaledMapImage =
        mapImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

    // Create a new ImageIcon with the scaled image
    ImageIcon scaledMapIcon = new ImageIcon(scaledMapImage);

    JLabel mapLabel = new JLabel();
    mapLabel.setIcon(scaledMapIcon);

    // Wrap the mapLabel in a JScrollPane
    JScrollPane mapScrollPane = new JScrollPane(mapLabel);
    mapScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    mapScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // 创建消息字符串和按钮面板
    gameMessageTextArea.setText(turnBeginInfo);
    this.gameMessageTextArea.setLineWrap(true);
    this.gameMessageTextArea.setWrapStyleWord(true);
    this.gameMessageTextArea.setEditable(false);

    // Add buttons for Move, Look, Pick, PetMove, Attack
    buttonPanel.setLayout(new GridLayout(2, 3));
    buttonPanel.add(moveButton);
    buttonPanel.add(lookButton);
    buttonPanel.add(pickButton);
    buttonPanel.add(petMoveButton);
    buttonPanel.add(attackButton);
    buttonPanel.add(finishTurn);

    JPanel buttonPanel2 = new JPanel();
    buttonPanel2.setLayout(new GridLayout(1, 1));
    buttonPanel2.add(showPlayerInfoButton);

    this.finishTurn.setEnabled(false); // disable finish turn until user input
    if (!turnBeginInfo.contains(", Attack])\n")) {
      this.attackButton.setEnabled(false);
    }
    if (turnBeginInfo.contains(", has items: []")) { // 如果当前room没有item 无法 pick
      this.pickButton.setEnabled(false);
    }

    // Combine text area and buttons in the same box
    JPanel textAreaWithButtonsPanel = new JPanel(new BorderLayout());
    textAreaWithButtonsPanel.add(new JScrollPane(gameMessageTextArea), BorderLayout.CENTER);
    textAreaWithButtonsPanel.add(buttonPanel2, BorderLayout.NORTH);
    textAreaWithButtonsPanel.add(buttonPanel, BorderLayout.SOUTH);

    // 使用 JSplitPane 将两个组件组合在一起
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        mapScrollPane, textAreaWithButtonsPanel);
    splitPane.setResizeWeight(0.75); // 设置左侧列占总宽度的比例

    // 设置 grid 布局
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    this.gameTurnScreen.setLayout(grid);

    // 将 JSplitPane 添加到布局中
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0; // 100% of the width
    gbc.weighty = 1.0; // 100% of the height
    this.gameTurnScreen.add(splitPane, gbc);

    // 重新展示并添加 screen
    this.getContentPane().removeAll();
    this.add(this.gameTurnScreen, BorderLayout.CENTER);
    this.setVisible(true);
    this.updateScreen();
  }

  /**
   * Displays the game over screen in the GUI.
   *
   * @param winnerName The name of the winner.
   */
  @Override
  public void showGameOverScreen(String winnerName) {
    // 清除之前信息
    this.gameOverScreen.removeAll();

    this.gameOverScreen.setBackground(new Color(240, 255, 255));

    // Set grid layout
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    gameOverScreen.setLayout(grid);

    // Create a header label
    JLabel header = new JLabel("", SwingConstants.CENTER);
    header.setFont(new Font("Arial", Font.PLAIN, 33));
    gbc.gridx = 0;
    gbc.gridy = 0;
    this.gameOverScreen.add(header, gbc);

    // Create a label for the image
    JLabel label = new JLabel();
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    this.gameOverScreen.add(label, gbc);

    // Create a button
    quitButton.setBackground(new Color(235, 236, 207));
    quitButton.setForeground(new Color(255, 0, 0));
    quitButton.setFocusPainted(false);
    quitButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Adjust font size
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.insets = new Insets(10, 0, 0, 0); // Adjust top margin
    this.gameOverScreen.add(quitButton, gbc);


    // Display different content based on winnerName
    if (winnerName == null) {
      header.setText("Game Over: Dr. Lucky Escaped!");
      ImageIcon gameOveImg = new ImageIcon("res/img/GameOver_escaped.png");
      label.setIcon(gameOveImg);
      label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    } else {
      header.setText(String.format("Game Winner: "
          + "Player(%s) killed Dr. Lucky!", winnerName));
      ImageIcon gameOveImg = new ImageIcon("res/img/GameOver_playerWin.png");
      label.setIcon(gameOveImg);
      label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    //展示
    gameOverScreen.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.getContentPane().removeAll();
    this.add(this.gameOverScreen);
    this.setVisible(true);
    this.updateScreen();
  }

  /**
   * Displays the screen for entering a new world input file in the GUI.
   */
  @Override
  public void showNewWorldInputFileScreen() {
    //startNewWorldButton.setEnabled(false);
    newWorldScreen.removeAll();
    this.setTitle("Set up new world");

    // layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 6, 0, 6);
    GridBagLayout grid = new GridBagLayout();
    newWorldScreen.setLayout(grid);

    JLabel header = new JLabel("Input New World File:(Valid .txt only)",
        SwingConstants.CENTER);
    header.setFont(new Font("Arial", Font.PLAIN, 33));
    header.setForeground(Color.RED);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipadx = 180;
    gbc.ipady = 18;
    newWorldScreen.add(header, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;


    JLabel label1 = new JLabel("Set Total Turns:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    newWorldScreen.add(label1, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    newWorldScreen.add(totalTurnInput, gbc);

    JLabel label2 = new JLabel("Set Total Players:");
    gbc.gridx = 0;
    gbc.gridy = 4;
    newWorldScreen.add(label2, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.insets = new Insets(0, 0, 20, 0);
    newWorldScreen.add(totalPlayerInput, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    chooseFileButton.setFocusPainted(false);
    newWorldScreen.add(chooseFileButton, gbc);
    chooseFileButton.addActionListener((ActionEvent e) -> {
      File curFolder = new File(System.getProperty("user.dir"));
      JFileChooser fileWindow = new JFileChooser();
      fileWindow.setCurrentDirectory(curFolder);
      fileWindow.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      int returnVal = fileWindow.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileWindow.getSelectedFile();
        newFileNamePath = file.getPath();
        //startNewWorldButton.setEnabled(true);
      }
    });

    gbc.gridx = 0;
    gbc.gridy = 7;
    newWorldScreen.add(startNewWorldButton, gbc);

    newWorldScreen.setSize(600, 600);
    newWorldScreen.setVisible(true);
    newWorldScreen.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.getContentPane().removeAll();
    this.add(newWorldScreen);
    this.updateScreen();
  }

  /**
   * Gets information about the new world from the input file in the GUI.
   *
   * @return An array of strings containing information about the new world.
   */
  @Override
  public String[] getNewWorldFileTurnPlayerInfo() {
    try {
      if (this.newFileNamePath == null) {
        throw new NullPointerException("You need to input a valid file name to start!");
      }
      // validating total turns
      if (this.totalTurnInput.getText().isBlank()) {
        throw new IllegalArgumentException("You need to input valid number of Turns!");
      } else {
        Integer.parseInt(totalTurnInput.getText());
      }

      // validating total players
      if (totalPlayerInput.getText().isBlank()) {
        throw new IllegalArgumentException("You need to input valid number of Players!");
      } else {
        Integer.parseInt(totalPlayerInput.getText());
      }

      return new String[]{newFileNamePath, totalTurnInput.getText(),
          totalPlayerInput.getText()};
    } catch (NumberFormatException exception) {
      this.showError("Please only input valid numbers for turn & player!");
    } catch (NullPointerException | IllegalArgumentException exception) {
      this.showError(exception.getMessage());
    }
    return null;

  }

  /**
   * Displays information about the player's surroundings in the GUI.
   *
   * @param lookResult The result of the "look around" action.
   */
  @Override
  public void showLookAroundInfo(String lookResult) {
    this.gameMessageTextArea.setText(lookResult);
    JOptionPane.showMessageDialog(this, lookResult, "Your LOOK result: ",
        JOptionPane.INFORMATION_MESSAGE);
    this.finishTurn.setEnabled(true);
  }

  /**
   * Displays information about picking up an item in the GUI.
   *
   * @param pickResult The result of the "pick" action.
   */
  @Override
  public void showPickInfo(String pickResult) {
    this.gameMessageTextArea.setText(pickResult);
    this.showMsg(String.format("%s \nPicked Highest Damage Item! "
            + "\nClick Finish Turn to next player!",
        pickResult));
    this.finishTurn.setEnabled(true);
  }

  /**
   * Displays information about attacking in the GUI.
   *
   * @param attackRes The result of the attack action.
   */
  @Override
  public void showAttackInfo(String attackRes) {
    if (attackRes != null) {
      this.gameMessageTextArea.setText(attackRes);
      this.showMsg(String.format("%s \nAttacked with Highest Damage Item"
              + "\nClick Finish Turn to next player!",
          attackRes));
    } else {
      this.gameMessageTextArea.setText("Attack failed due being seen!\n");
      this.showMsg("Attack failed due being seen! Careful next time!"
          + "\nClick Finish Turn to next player!");
    }
    this.finishTurn.setEnabled(true);

  }


  /**
   * Enables the button for starting turns in the GUI.
   */
  @Override
  public void enableStartTurnsButton() {
    this.buttonStartTurns.setEnabled(true);
  }

  /**
   * Disables or enables the "Add One Player" button in the GUI.
   *
   * @param input If true, enables the button; if false, disables the button.
   */
  @Override
  public void disableEnableAddOnePlayerButton(boolean input) {
    this.buttonAddPlayer.setEnabled(input);
  }


  /**
   * Gets input for adding a player from the GUI.
   *
   * @param totalRoomNumber   The total number of rooms in the game.
   * @param allPlayerNameList The list of all player names in the game.
   * @return An array of strings containing player input.
   */
  @Override
  public String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList) {
    try {
      String curPlayerName = this.playerNameInputBox.getText();
      Integer curPlayerLimit = Integer.parseInt(playerCarryLimitBox.getText());
      Integer curPlayerRoomNumber = Integer.parseInt(playerInitialRoomNumberBox.getText());


      if (allPlayerNameList.contains(curPlayerName)) {
        throw new IllegalArgumentException(
            "Error: Player name already exist, try a different name!\n");
      }
      if (curPlayerLimit <= 0) {
        throw new IllegalArgumentException(
            "Error: carry limit must larger than 0!\n");
      }
      if (curPlayerRoomNumber < 0 || curPlayerRoomNumber > totalRoomNumber) {
        throw new IllegalArgumentException(String.format(
            "Error: Room number must be non-zero and within %d!\n", totalRoomNumber));
      }
      return new String[]{
          curPlayerName,
          String.valueOf(curPlayerLimit),
          String.valueOf(curPlayerRoomNumber),
          String.valueOf(computerSelectBtn.isSelected()) // if selected computer is 1
      };
    } catch (IllegalArgumentException e) {
      this.showError(String.format(
          "%s \nInput Name, Limit or Room Number Invalid!!!\n"
              + "Please check your input Error!", e.getMessage()));
    }
    return null;
  }

  /**
   * Displays information about a pet's movement in the GUI.
   *
   * @param petResult The result of the pet's movement action.
   */
  @Override
  public void showPetMoveInfo(String petResult) {
    this.gameMessageTextArea.setText(petResult);
    this.showMsg(String.format("%s \nClick Finish Turn to next player!",
        petResult));
    this.finishTurn.setEnabled(true);
  }

  /**
   * Disables or enables all action buttons in the GUI.
   *
   * @param input If true, enables the buttons; if false, disables the buttons.
   */
  @Override
  public void disableEnableAllActionButtons(Boolean input) {
    this.moveButton.setEnabled(input);
    this.lookButton.setEnabled(input);
    this.pickButton.setEnabled(input);
    this.petMoveButton.setEnabled(input);
    this.attackButton.setEnabled(input);
  }

  /**
   * Gets the status of a specific button in the GUI.
   *
   * @param inputButtonName The name of the button.
   * @return True if the button is active, false otherwise.
   */
  @Override
  public boolean getBtnStatus(String inputButtonName) {
    switch (inputButtonName.toUpperCase()) {
      case "LOOK":
        return lookButton.isEnabled();
      case "MOVE":
        return moveButton.isEnabled();
      case "PICK":
        return pickButton.isEnabled();
      case "PETMOVE":
        return petMoveButton.isEnabled();
      case "ATTACK":
        return attackButton.isEnabled();
      default:
        return false;
    }
  }


}
