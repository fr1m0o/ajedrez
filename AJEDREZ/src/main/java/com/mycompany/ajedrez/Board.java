 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ajedrez;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author nicolas
 */
public class Board {
  final int MAX_COLUMN=8;
  final int MAX_ROW=8;
  public static final int SQUARE_SIZE=100;
  public static final int HALF_SQUARE_SIZE= SQUARE_SIZE/2;
  
  public void DRAW(Graphics2D G2){
     int c=0;
      for (int row = 0; row <MAX_ROW; row++) {
          for (int col = 0; col <MAX_COLUMN; col++) {
              if (c==0) {
                  G2.setColor(new Color(210,165,125));
                  c=1;
              } else {
                   G2.setColor(new Color(175,115,70));
                  c=0;
              }
              G2.fillRect(col*SQUARE_SIZE,row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
          }
          if (c ==0) {
              c=1;
          } else {
              c=0;
          }
      }
  
  
  }


} 
  
