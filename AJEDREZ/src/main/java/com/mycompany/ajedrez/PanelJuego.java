package com.mycompany.ajedrez;

import Piece.*;
import Visual.JaqueMateVisual;
import Visual.JaqueVisual;
import Visual.TurnoVisual;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelJuego extends JPanel implements Runnable {

    public static final int WIDHT = 1000;
    public static final int HEIGTH = 730;

    public static final int white = 0;
    public static final int black = 1;

    public static ArrayList<Piezas> pieces = new ArrayList<>();
    public static ArrayList<Piezas> simpieces = new ArrayList<>();

    Thread hiloJuego;
    Board board = new Board();
    Mouse mouse = new Mouse();

    Piezas activeP;
    int currentColor = white;

    boolean isCheckMate = false;

    TurnoVisual turnoVisual = new TurnoVisual();
    JaqueVisual jaqueVisual = new JaqueVisual();
    JaqueMateVisual jaqueMateVisual = new JaqueMateVisual();

    // ================= CONSTRUCTOR =================
    public PanelJuego() {
        setPreferredSize(new Dimension(WIDHT, HEIGTH));
        setBackground(Color.BLACK);

        setPieces();
        copyPieces(pieces, simpieces);

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    // ================= THREAD =================
    public void startGameThread() {
        hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    // ================= PIEZAS =================
    public void setPieces() {

        pieces.clear();

        for (int i = 0; i < 8; i++) pieces.add(new Pawn(i, 6, white));
        pieces.add(new ROOK(0, 7, white));
        pieces.add(new Knigth(1, 7, white));
        pieces.add(new Bishop(2, 7, white));
        pieces.add(new Queen(3, 7, white));
        pieces.add(new King(4, 7, white));
        pieces.add(new Bishop(5, 7, white));
        pieces.add(new Knigth(6, 7, white));
        pieces.add(new ROOK(7, 7, white));

        for (int i = 0; i < 8; i++) pieces.add(new Pawn(i, 1, black));
        pieces.add(new ROOK(0, 0, black));
        pieces.add(new Knigth(1, 0, black));
        pieces.add(new Bishop(2, 0, black));
        pieces.add(new Queen(3, 0, black));
        pieces.add(new King(4, 0, black));
        pieces.add(new Bishop(5, 0, black));
        pieces.add(new Knigth(6, 0, black));
        pieces.add(new ROOK(7, 0, black));
    }

    private void copyPieces(ArrayList<Piezas> src, ArrayList<Piezas> dst) {
        dst.clear();
        for (Piezas p : src) {
            dst.add(p.clone());
        }
    }

    public static Piezas getPiece(int col, int row) {
        for (Piezas p : simpieces) {
            if (p.col == col && p.row == row) return p;
        }
        return null;
    }

    // ================= ATAQUE =================
    public static boolean isSquareAttacked(int col, int row, int color) {

        int enemy = (color == white) ? black : white;

        for (Piezas p : simpieces) {
            if (p.color != enemy) continue;

            if (p instanceof King) {
                if (Math.abs(p.col - col) <= 1 && Math.abs(p.row - row) <= 1)
                    return true;
            } else {
                if (p.canMove(col, row))
                    return true;
            }
        }
        return false;
    }

    // ================= REY =================
    private King getKing(int color) {
        for (Piezas p : simpieces) {
            if (p instanceof King && p.color == color)
                return (King) p;
        }
        return null;
    }

    private boolean isKingInCheck(int color) {
        King king = getKing(color);
        return king != null && isSquareAttacked(king.col, king.row, color);
    }

    // ================= JAQUE MATE =================
    private boolean isCheckMate(int color) {

        if (!isKingInCheck(color)) return false;

        ArrayList<Piezas> backup = new ArrayList<>();
        for (Piezas p : simpieces) backup.add(p.clone());

        for (Piezas p : backup) {

            if (p.color != color) continue;

            for (int col = 0; col < 8; col++) {
                for (int row = 0; row < 8; row++) {

                    if (!p.canMove(col, row)) continue;

                    int oldCol = p.col;
                    int oldRow = p.row;

                    p.col = col;
                    p.row = row;

                    if (!isKingInCheck(color)) return false;

                    p.col = oldCol;
                    p.row = oldRow;
                }
            }
        }
        return true;
    }

    // ================= UPDATE =================
    private void update() {

        if (isCheckMate) return;

        if (mouse.pressed) {

            if (activeP == null) {
                for (Piezas p : simpieces) {
                    if (p.color == currentColor &&
                        p.col == mouse.x / Board.SQUARE_SIZE &&
                        p.row == mouse.y / Board.SQUARE_SIZE) {

                        activeP = p;
                        activeP.storePreviousPosition();
                        break;
                    }
                }
            }

            if (activeP != null)
                activeP.setPixelPosition(mouse.x, mouse.y);

        } else {

            if (activeP != null) {

                int targetCol = mouse.x / Board.SQUARE_SIZE;
                int targetRow = mouse.y / Board.SQUARE_SIZE;

                if (activeP.canMove(targetCol, targetRow)) {

                    activeP.col = targetCol;
                    activeP.row = targetRow;

                    if (!isKingInCheck(currentColor)) {

                        activeP.updatePosition();
                        copyPieces(simpieces, pieces);

                        currentColor = (currentColor == white) ? black : white;

                        if (isCheckMate(currentColor)) {
                            isCheckMate = true;
                            int winner = (currentColor == white) ? black : white;
                            jaqueMateVisual.show(winner);
                        }

                    } else {
                        activeP.resetPosition();
                        copyPieces(pieces, simpieces);
                    }

                } else {
                    activeP.resetPosition();
                    copyPieces(pieces, simpieces);
                }

                activeP = null;
            }
        }
    }

    // ================= DIBUJO =================
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        board.DRAW(g2);
        jaqueVisual.draw(g2);

        for (Piezas p : simpieces)
            p.DRAW(g2);

        turnoVisual.draw(g2, currentColor);
        jaqueMateVisual.draw(g2, WIDHT, HEIGTH);
    }

    // ================= LOOP =================
    @Override
    public void run() {
        while (true) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (Exception e) {
            }
        }
    }
}
