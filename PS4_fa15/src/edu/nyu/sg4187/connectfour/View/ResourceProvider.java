package edu.nyu.sg4187.connectfour.View;

import javax.swing.ImageIcon;

public class ResourceProvider {

  /**
   * Load a picture from the file by given color.
   * 
   * @param color
   * @return ImageIcon type's dots.
   */
  public ImageIcon getChess(String color) {
    String imagePath;

    if (color.equals("Red")) {
      imagePath = "images/Red.gif";

    } else if (color.equals("Green")) {
      imagePath = "images/Green.gif";
    } else {
      imagePath = "images/Win.gif";
    }

    ImageIcon dot = new ImageIcon(imagePath);
    return dot;
  }

}
