package com.mycompany.ajedrez;

import javax.swing.JFrame;

public class AJEDREZ {

    public static void main(String[] args) {

        JFrame window = new JFrame("Ajedrez");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        PanelJuego PJ = new PanelJuego();
        window.add(PJ);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // 🔴 ESTO ES LO QUE FALTABA
        PJ.startGameThread();
    }
}
