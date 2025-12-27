/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piece;

import com.mycompany.ajedrez.PanelJuego;

/**
 *
 * @author nicolas
 */
public class Knigth extends Piezas{
    
    public Knigth(int col, int row, int color) {
        super(col, row, color);
    if (color==PanelJuego.white) {
           image= getImage("/IMAGES1/w-knight"); 
        } else {
        image= getImage("/IMAGES1/b-knight"); 
        }
    
    
    }
  @Override
public boolean canMove(int targetCol, int targetRow) {

    if (!iswithinBoard(targetCol, targetRow)) return false;

    int dx = Math.abs(targetCol - precol);
    int dy = Math.abs(targetRow - prerow);

    if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
        return isValidSquare(targetCol, targetRow);
    }

    return false;
}
  
}
