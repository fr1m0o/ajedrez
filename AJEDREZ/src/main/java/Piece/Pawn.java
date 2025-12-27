package Piece;

import com.mycompany.ajedrez.PanelJuego;

public class Pawn extends Piezas {

    public Pawn(int col, int row, int color) {
        super(col, row, color);

        if (color == PanelJuego.white) {
            image = getImage("/IMAGES1/w-pawn");
        } else {
            image = getImage("/IMAGES1/b-pawn");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {

        if (!iswithinBoard(targetCol, targetRow)) {
            return false;
        }

        int direction = (color == PanelJuego.white) ? -1 : 1;

        // ================== AVANCE 1 ==================
        if (targetCol == precol &&
            targetRow == prerow + direction &&
            getHittingP(targetCol, targetRow) == null) {

            return true;
        }

        // ================== AVANCE 2 (primer movimiento) ==================
        if (targetCol == precol &&
            targetRow == prerow + 2 * direction &&
            !hasMoved() &&
            getHittingP(targetCol, targetRow) == null &&
            getHittingP(precol, prerow + direction) == null) {

            return true;
        }

        // ================== CAPTURA DIAGONAL ==================
        if (Math.abs(targetCol - precol) == 1 &&
            targetRow == prerow + direction) {

            Piezas target = getHittingP(targetCol, targetRow);

            if (target != null && target.color != this.color) {
                HittingP = target;
                return true;
            }
        }

        return false;
    }

    // ================== UTIL ==================
    private boolean hasMoved() {
        return prerow != ((color == PanelJuego.white) ? 6 : 1);
    }
}
