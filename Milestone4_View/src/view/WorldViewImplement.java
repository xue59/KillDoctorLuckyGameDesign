package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WorldViewImplement extends JFrame implements WorldView {

  private JPanel welcomeScreen;
  private JPanel addPlayerScreen;
  private JButton buttonStartGame;
  private JButton buttonAddPlayer;
  private JButton buttonStartTurns;
  private JTextField playerNameInputBox;
  private JTextField playerCarryLimitBox;
  private JTextField playerInitialRoomNumberBox;
  private JRadioButton humanSelectBtn;
  private JRadioButton computerSelectBtn;
  private JPanel gameTurnScreen;

  private JButton moveButton;
  private JButton lookButton;
  private JButton pickButton;
  private JButton petMoveButton;
  private JButton attackButton;

  private JTextArea gameMessageTextArea;

  public WorldViewImplement(String header){
    super(header);
    ImageIcon welcomeIcon = new ImageIcon("res/img/welcome.png");
    this.setIconImage(welcomeIcon.getImage());
    this.setLayout(new BorderLayout());
    this.setSize(800, 800);
    this.setLocation(15, 15);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Set welcome screen
    this.welcomeScreen = new JPanel();
    this.buttonStartGame = new JButton("Start Game!");

    //Set add player screen
    this.addPlayerScreen = new JPanel();
    this.buttonAddPlayer = new JButton("Add This Player");
    this.buttonStartTurns= new JButton("Start Turns!");

    //set game map turn start screen
    this.gameTurnScreen = new JPanel();
    this.moveButton = new JButton("Move");
    this.lookButton = new JButton("Look");
    lookButton.setActionCommand("LOOK");
    this.pickButton = new JButton("Pick");
    this.petMoveButton = new JButton("PetMove");
    this.attackButton = new JButton("Attack");
    this.gameMessageTextArea = new JTextArea();

  }

  private void updateScreen(){
    this.revalidate();
    this.repaint();
    this.setVisible(true);
  }

  @Override
  public String showItems(Map<String, Integer> items) {
    return null;
  }

  @Override
  public void showMsg(String msg) {
    System.out.println("Message: "+msg);
    JOptionPane.showMessageDialog(this, msg, "Message: ",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showError(String errorMsg) {
    System.out.println("Error: " +errorMsg);
    JOptionPane.showMessageDialog(this, errorMsg, "ERROR!!!",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setupNewGameWindow() {

  }

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
  }

  @Override
  public void showWelcomeGui() {
    this.welcomeScreen.setBackground(new Color(240, 255, 255));
    JLabel label = new JLabel();
    label.setVisible(true);
    ImageIcon welcomeImg = new ImageIcon("res/img/welcome.png");
    label.setIcon(welcomeImg);
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    buttonStartGame.setBackground(new Color(235, 236, 207));
    buttonStartGame.setForeground(new Color(255, 0, 0));
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

    //第二个box button
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    welcomeScreen.add(buttonStartGame, gbc);
    welcomeScreen.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    //展示
    this.add(this.welcomeScreen);
    this.setVisible(true);
  }

  @Override
  public void showAddPlayerGui(Integer totalRoomNumber) {
    this.gameTurnScreen.removeAll(); //清除之前信息

    //set grid
    GridBagConstraints gbc = new GridBagConstraints(); // grid container
    gbc.insets = new Insets(0, 5, 0, 5);
    GridBagLayout grid = new GridBagLayout();
    this.addPlayerScreen.setLayout(grid);

    JLabel header = new JLabel("Total XX players needed to start game:", SwingConstants.CENTER);
    header.setFont(new Font("Arial", Font.PLAIN, 33));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.ipadx = 180;
    gbc.ipady = 18;
    this.addPlayerScreen.add(header,gbc);

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
        "Player's Initial Room Number (Integer Only 0 to %d):",totalRoomNumber));
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
    this.addPlayerScreen.add(buttonAddPlayer,gbc);

    // 6th input - start game turn
    this.buttonStartTurns.setActionCommand("START_TURNS");
    this.buttonStartTurns.setEnabled(false); // set button to be gray out
    gbc.gridx = 0;
    gbc.gridy = 10;
    this.addPlayerScreen.add(buttonStartTurns,gbc);


    //重新展示update screen & 添加screen
    this.addPlayerScreen.setVisible(true);
    this.getContentPane().removeAll();
    this.add(this.addPlayerScreen);
    this.updateScreen();
  }

  @Override
  public void showStartTurnScreen(String turnBeginInfo){
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
    Image scaledMapImage = mapImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

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
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(moveButton);
    buttonPanel.add(lookButton);
    buttonPanel.add(pickButton);
    buttonPanel.add(petMoveButton);
    buttonPanel.add(attackButton);
    if (!turnBeginInfo.contains(", Attack])\n")){
      this.attackButton.setEnabled(false);
    }


    // Combine text area and buttons in the same box
    JPanel textAreaWithButtonsPanel = new JPanel(new BorderLayout());
    textAreaWithButtonsPanel.add(new JScrollPane(gameMessageTextArea), BorderLayout.CENTER);
    textAreaWithButtonsPanel.add(buttonPanel, BorderLayout.SOUTH);

    // 使用 JSplitPane 将两个组件组合在一起
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapScrollPane, textAreaWithButtonsPanel);
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
    this.updateScreen();
  }

  @Override
  public void showLookAroundInfo(String lookResult){
    this.gameMessageTextArea.setText(lookResult);
  }
  @Override
  public void enableStartTurnsButton(){
    this.buttonStartTurns.setEnabled(true);
  }

  @Override
  public void disableAddOnePlayerButton(){
    this.buttonAddPlayer.setEnabled(false);
  }


  @Override
  public String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList){
    try{
      String curPlayerName = this.playerNameInputBox.getText();
      Integer curPlayerLimit = Integer.parseInt(playerCarryLimitBox.getText());
      Integer curPlayerRoomNumber = Integer.parseInt(playerInitialRoomNumberBox.getText());


      if (allPlayerNameList.contains(curPlayerName)){
        throw new IllegalArgumentException(
            "Error: Player name already exist, try a different name!\n");
      }
      if (curPlayerLimit <= 0){
        throw new IllegalArgumentException(
            "Error: carry limit must larger than 0!\n");
      }
      if (curPlayerRoomNumber < 0 || curPlayerRoomNumber >totalRoomNumber) {
        throw new IllegalArgumentException(String.format(
            "Error: Room number must be non-zero and within %d!\n",totalRoomNumber));
      }
      return new String[] {
          curPlayerName,
          String.valueOf(curPlayerLimit),
          String.valueOf(curPlayerRoomNumber),
          String.valueOf(computerSelectBtn.isSelected()) // if selected computer is 1
      };
    } catch (IllegalArgumentException e){
      this.showError(String.format(
          "%s !\nInput Name, Limit or Room Number Invalid!!!\n"
              + "Please check your input Error!",e.getMessage()));
    }
    return null;
  }

}
