package Piece;

import com.mycompany.ajedrez.Board;
import com.mycompany.ajedrez.PanelJuego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Piezas implements Cloneable
{

    public BufferedImage image;
    public int x, y;
    public int col, row, precol, prerow;
    public int color;
    public Piezas HittingP;

    public boolean hasMoved = false; // 🔴 CLAVE PARA ENROQUE

    public Piezas(int col, int row, int color) {
        this.col = col;
        this.row = row;
        this.color = color;
        x = getx(col);
        y = gety(row);
        storePreviousPosition();
    }

    public BufferedImage getImage(String imagePath) {
        try {
            var stream = getClass().getResourceAsStream(imagePath + ".png");
            if (stream == null) return null;
            return ImageIO.read(stream);
        } catch (IOException e) {
            return null;
        }
    }

    public int getx(int col) {
        return col * Board.SQUARE_SIZE;
    }

    public int gety(int row) {
        return row * Board.SQUARE_SIZE;
    }

    public int getcol(int x) {
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    public int getrow(int y) {
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    public void storePreviousPosition() {
        precol = col;
        prerow = row;
    }

    public void updatePosition() {

        if (HittingP != null) {
            PanelJuego.simpieces.remove(HittingP);
            HittingP = null;
        }

        x = getx(col);
        y = gety(row);

        hasMoved = true; // 🔴 IMPORTANTE
        storePreviousPosition();
    }

    public void resetPosition() {
        col = precol;
        row = prerow;
        x = getx(col);
        y = gety(row);
        HittingP = null;
    }

    public boolean iswithinBoard(int targetCol, int targetRow) {
        return targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7;
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {
        HittingP = getHittingP(targetCol, targetRow);
        if (HittingP == null) return true;
        if (HittingP.color != this.color) return true;
        HittingP = null;
        return false;
    }

    public Piezas getHittingP(int targetCol, int targetRow) {
        for (Piezas p : PanelJuego.simpieces) {
            if (p.col == targetCol && p.row == targetRow && p != this) {
                return p;
            }
        }
        return null;
    }

    public void DRAW(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }

    // SOLO mueve píxeles, NO lógica
public void setPixelPosition(int x, int y) {
    this.x = x - Board.SQUARE_SIZE / 2;
    this.y = y - Board.SQUARE_SIZE / 2;
}
@Override
public Piezas clone() {
    try {
        return (Piezas) super.clone();
    } catch (CloneNotSupportedException e) {
        return null;
    }
}

}
