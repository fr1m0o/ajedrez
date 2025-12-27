package Visual;

import Piece.*;
import com.mycompany.ajedrez.Board;
import com.mycompany.ajedrez.PanelJuego;
import java.awt.*;

public class JaqueVisual {

    private final Font font = new Font("Arial", Font.BOLD, 36);

    private boolean visible = true;
    private long lastBlinkTime = 0;
    private final int BLINK_INTERVAL = 400;

    public void draw(Graphics2D g2) {

        // ===== PARPADEO =====
        long now = System.currentTimeMillis();
        if (now - lastBlinkTime > BLINK_INTERVAL) {
            visible = !visible;
            lastBlinkTime = now;
        }

        // ===== REY BLANCO =====
        King whiteKing = getKing(PanelJuego.white);
        if (whiteKing != null &&
            PanelJuego.isSquareAttacked(whiteKing.col, whiteKing.row, PanelJuego.white)) {

            if (visible) {
                drawSquare(g2, whiteKing.col, whiteKing.row);
                drawText(g2, "JAQUE", Color.RED, 820, 120);
            }
        }

        // ===== REY NEGRO =====
        King blackKing = getKing(PanelJuego.black);
        if (blackKing != null &&
            PanelJuego.isSquareAttacked(blackKing.col, blackKing.row, PanelJuego.black)) {

            if (visible) {
                drawSquare(g2, blackKing.col, blackKing.row);
                drawText(g2, "JAQUE", Color.RED, 820, 160);
            }
        }
    }

    // ================= UTIL =================

    private King getKing(int color) {
        for (Piezas p : PanelJuego.simpieces) {
            if (p instanceof King && p.color == color) {
                return (King) p;
            }
        }
        return null;
    }

    private void drawSquare(Graphics2D g2, int col, int row) {
        g2.setColor(new Color(255, 0, 0, 120)); // rojo semi-transparente
        g2.fillRect(
            col * Board.SQUARE_SIZE,
            row * Board.SQUARE_SIZE,
            Board.SQUARE_SIZE,
            Board.SQUARE_SIZE
        );
    }

    private void drawText(Graphics2D g2, String text, Color color, int x, int y) {
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, x, y);
    }
}
