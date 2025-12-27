/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import com.mycompany.ajedrez.PanelJuego;

/**
 *
 * @author nicolas
 */


public class TurnoVisual {

    private Font font = new Font("Arial", Font.BOLD, 24);

    public void draw(Graphics2D g2, int currentColor) {

        g2.setFont(font);

        if (currentColor == PanelJuego.white) {
            g2.setColor(Color.WHITE);
            g2.drawString("Turno: Blancas", 820, 50);
        } else {
            g2.setColor(Color.WHITE);
            g2.drawString("Turno: Negras", 820, 50);
        }
    }
}

