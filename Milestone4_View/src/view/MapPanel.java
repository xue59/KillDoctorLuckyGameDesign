package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class MapPanel extends JScrollPane {
  private JPanel gameMapScreen;

  public MapPanel(){
    this.setLayout(null);
    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));

    //set game map screen
    this.gameMapScreen = new JPanel();
  }

  public void showGameMap(){
    this.removeAll();
    this.gameMapScreen.setBackground(new Color(240, 255, 255));
    ImageIcon mapImg = new ImageIcon("newCreatedMap.png");

    JLabel mapLabel = new JLabel();
    mapLabel.setVisible(true);
    mapLabel.setIcon(mapImg);
    mapLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    // set grid lay out
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    gameMapScreen.setLayout(grid);
    // 第一个box map image
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gameMapScreen.add(mapLabel, gbc);


    //展示
    this.add(this.gameMapScreen);
    this.setVisible(true);
  }
}
