import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawField {

    int inputField = 0;
    int[][] mask = new int[][]{{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};
    int[][] powMask = new int[][]{{100000000, 10000000, 1000000}, {100000, 10000, 1000}, {100, 10, 1}};
    boolean isItCross = true;
    int width = 750;
    int height = 750;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = image.createGraphics();


    public void paintField() {
        paintBackGround();


    }

    public void paintBackGround() {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g2d.fillRect(0, 0, width, height); // Draws a solid rectangle of specified width/height, located at (x,y)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(250, 0, 250, 750);
        g2d.drawLine(500, 0, 500, 750);
        g2d.drawLine(0, 250, 750, 250);
        g2d.drawLine(0, 500, 750, 500);
    }

    public void addField(int x, int y) {
        // we have to get digit using X and Y
        // then checked it
        x = x / 250;
        y = y / 250;
        int digit = mask[y][x];
        if (inputField / (int) Math.pow(10, digit) % 10 == 0)
            if (isItCross) inputField += 1 * Math.pow(10, digit);
            else inputField += 2 * Math.pow(10, digit);
        isItCross = !isItCross;

    }

    public void cleanField() {
        inputField = 0;
        paintBackGround();

    }

    public void paintBasedOnField() {
        int c = 100000000;
        int x = 0;
        int y = 0;
        while (c > 0) {
            if (inputField / c % 10 == 1) {
                isItCross = true;
                drawFigure(x*250,y*250);
            } else if (inputField / c % 10 == 2){
                isItCross = false;
                drawFigure(x*250,y*250);
            }
            x++;
            if (x > 2){
                y++;
                x = 0;
            }
            c /= 10;
        }
    }

    public static class Pair {
        int key;
        int value;

        Pair(int a, int b) {
            key = a;
            value = b;
        }
    }

    public Pair getPairFromMatrix(int c) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (powMask[i][j] == c) return new Pair(i, j);
            }
        return new Pair(0, 0);
    }

    public void drawFigure(int x, int y) {
        if (isItCross) {
            g2d.drawLine(x / 250 * 250, y / 250 * 250, x / 250 * 250 + 250, y / 250 * 250 + 250);
            g2d.drawLine(x / 250 * 250 + 250, y / 250 * 250, x / 250 * 250, y / 250 * 250 + 250);
        } else {
            g2d.drawOval(x / 250 * 250, y / 250 * 250, 250, 250);
        }


    }

}
