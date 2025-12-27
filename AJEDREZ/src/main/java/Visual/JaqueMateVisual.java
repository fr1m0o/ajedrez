package Visual;

import java.awt.*;

public class JaqueMateVisual {

    private boolean visible = false;
    private int winnerColor;

    // Mostrar jaque mate
    public void show(int winnerColor) {
        this.winnerColor = winnerColor;
        visible = true;
    }

    // Opcional: resetear (por si después agregás "Nueva partida")
    public void reset() {
        visible = false;
    }

    public void draw(Graphics2D g2, int width, int height) {

        if (!visible) return;

        // Fondo oscuro semi-transparente
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, width, height);

        // Texto JAQUE MATE
        g2.setFont(new Font("Arial", Font.BOLD, 72));
        g2.setColor(Color.RED);

        String text = "JAQUE MATE";
        FontMetrics fm = g2.getFontMetrics();

        int x = (width - fm.stringWidth(text)) / 2;
        int y = height / 2;

        g2.drawString(text, x, y);

        // Texto ganador
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        g2.setColor(Color.WHITE);

        String winnerText = (winnerColor == 0)
                ? "Ganan las BLANCAS"
                : "Ganan las NEGRAS";

        int wx = (width - g2.getFontMetrics().stringWidth(winnerText)) / 2;
        int wy = y + 60;

        g2.drawString(winnerText, wx, wy);
    }
}
