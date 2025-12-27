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
public class Queen extends Piezas{
    
    public Queen(int col, int row, int color) {
        super(col, row, color);
     if (color==PanelJuego.white) {
           image= getImage("/IMAGES1/w-queen"); 
        } else {
        image= getImage("/IMAGES1/b-queen"); 
        }
    
    }
    @Override
public boolean canMove(int targetCol, int targetRow) {

    if (!iswithinBoard(targetCol, targetRow)) return false;

    boolean straight = targetCol == precol || targetRow == prerow;
    boolean diagonal = Math.abs(targetCol - precol) == Math.abs(targetRow - prerow);

    if (straight || diagonal) {
        return isValidSquare(targetCol, targetRow);
    }

    return false;
}

}
