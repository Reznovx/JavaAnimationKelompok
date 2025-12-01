import java.awt.*;

/**
 * Cloud
 * - Merepresentasikan awan sederhana yang bergerak horizontal.
 */
public class Cloud {
    // posisi awan
    int x, y;
    // kecepatan bergerak (pixel per update)
    int speed = 1;

    public Cloud(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /**
     * update
     * - Geser awan ke kanan setiap frame.
     * - Jika keluar area panel (melebihi panelWidth) maka kembalikan ke kiri.
     */
    public void update(int panelWidth) {
        x += speed;
        if (x > panelWidth) {
            x = -150; // pos awal lagi di kiri layar untuk loop
        }
    }

    /**
     * draw
     * - Menggambar beberapa oval untuk membentuk awan.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, 60, 40);
        g2d.fillOval(x + 30, y - 20, 70, 50);
        g2d.fillOval(x + 60, y, 60, 40);
        g2d.fillOval(x + 90, y - 10, 50, 30);
    }
}