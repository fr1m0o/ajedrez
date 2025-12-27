package Piece;

import com.mycompany.ajedrez.PanelJuego;

public class ROOK extends Piezas {

    public boolean hasMoved = false;

    public ROOK(int col, int row, int color) {
        super(col, row, color);

        if (color == PanelJuego.white)
            image = getImage("/IMAGES1/w-rook");
        else
            image = getImage("/IMAGES1/b-rook");
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {

        if (!iswithinBoard(targetCol, targetRow)) return false;

        if (targetCol == precol || targetRow == prerow) {
            return isValidSquare(targetCol, targetRow);
        }

        return false;
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        hasMoved = true;
    }
}
