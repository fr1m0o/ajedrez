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
public class Bishop extends Piezas{
    
    public Bishop(int col, int row, int color) {
        super(col, row, color);
     if (color==PanelJuego.white) {
           image= getImage("/IMAGES1/w-bishop"); 
        } else {
        image= getImage("/IMAGES1/b-bishop"); 
        }
    
    }
    @Override
public boolean canMove(int targetCol, int targetRow) {

    if (!iswithinBoard(targetCol, targetRow)) return false;

    if (Math.abs(targetCol - precol) == Math.abs(targetRow - prerow)) {
        return isValidSquare(targetCol, targetRow);
    }

    return false;
}

}
