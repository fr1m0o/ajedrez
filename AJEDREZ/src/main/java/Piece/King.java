package Piece;

import com.mycompany.ajedrez.PanelJuego;

public class King extends Piezas {

    public boolean hasMoved = false;

    public King(int col, int row, int color) {
        super(col, row, color);

        if (color == PanelJuego.white)
            image = getImage("/IMAGES1/w-king");
        else
            image = getImage("/IMAGES1/b-king");
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {

        if (!iswithinBoard(targetCol, targetRow)) return false;

        int dx = Math.abs(targetCol - precol);
        int dy = Math.abs(targetRow - prerow);

        // Movimiento normal
        if (dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0)) {
            return isValidSquare(targetCol, targetRow)
                && !PanelJuego.isSquareAttacked(targetCol, targetRow, color);
        }

        // ================= ENROQUE =================
        if (!hasMoved && dy == 0 && dx == 2) {
            if (targetCol > precol) return canCastleShort();
            if (targetCol < precol) return canCastleLong();
        }

        return false;
    }

    private boolean canCastleShort() {

        Piezas p = PanelJuego.getPiece(precol + 3, prerow);
        if (!(p instanceof ROOK)) return false;

        ROOK rook = (ROOK) p;
        if (rook.hasMoved) return false;

        if (PanelJuego.getPiece(precol + 1, prerow) != null) return false;
        if (PanelJuego.getPiece(precol + 2, prerow) != null) return false;

        if (PanelJuego.isSquareAttacked(precol, prerow, color)) return false;
        if (PanelJuego.isSquareAttacked(precol + 1, prerow, color)) return false;
        if (PanelJuego.isSquareAttacked(precol + 2, prerow, color)) return false;

        return true;
    }

    private boolean canCastleLong() {

        Piezas p = PanelJuego.getPiece(precol - 4, prerow);
        if (!(p instanceof ROOK)) return false;

        ROOK rook = (ROOK) p;
        if (rook.hasMoved) return false;

        if (PanelJuego.getPiece(precol - 1, prerow) != null) return false;
        if (PanelJuego.getPiece(precol - 2, prerow) != null) return false;
        if (PanelJuego.getPiece(precol - 3, prerow) != null) return false;

        if (PanelJuego.isSquareAttacked(precol, prerow, color)) return false;
        if (PanelJuego.isSquareAttacked(precol - 1, prerow, color)) return false;
        if (PanelJuego.isSquareAttacked(precol - 2, prerow, color)) return false;

        return true;
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        hasMoved = true;
    }
}
