import java.awt.*;
import java.awt.geom.*;

/**
 * CustomerObject
 * - Merepresentasikan satu objek "pelanggan" yang digambar dan bergerak.
 */
public class CustomerObject {
    // posisi dan kecepatan horizontal pelanggan
    private int x, y;
    private int deltaX;
    // skala untuk menggambar (untuk variasi ukuran)
    private double scale;
    // ukuran dasar pelanggan sebelum diskalakan
    private static final int CUSTOMER_WIDTH = 60;
    private static final int CUSTOMER_HEIGHT = 120;

    // Konstruktor: inisialisasi posisi, kecepatan, dan skala
    public CustomerObject(int startX, int startY, int speedX, double initialScale) {
        this.x = startX;
        this.y = startY;
        this.deltaX = speedX;
        this.scale = initialScale;
    }

    /**
     * update
     * - Memindahkan pelanggan sesuai deltaX.
     * - Jika pelanggan keluar layar di salah satu sisi, maka di-wrap-around
     *   ke sisi berlawanan agar animasi terasa kontinu.
     */
    public void update(int panelWidth, int panelHeight) {
        if (deltaX != 0) {
            x += deltaX;

            int currentWidth = (int)(CUSTOMER_WIDTH * scale);
            int minX = 0 - currentWidth;
            int maxX = panelWidth + currentWidth;

            if (deltaX > 0) {
                // bergerak ke kanan
                if (x > maxX) {
                    x = minX;
                }
            } else {
                // bergerak ke kiri
                if (x + currentWidth < minX) {
                    x = maxX;
                }
            }
        }
    }

    /**
     * draw
     * - Menggambar bentuk pelanggan (kepala, rambut, baju, tangan, celana).
     * - Menggunakan transformasi skala untuk ukuran relatif.
     */
    public void draw(Graphics2D g2d) {
        Color skinColor = new Color(0xFFDBAC);
        Color defaultShirtColor = (deltaX > 0) ? new Color(0x64B5F6) : new Color(0xFF6347);
        Color shoppingShirtColor = new Color(0xFFD700);
        // jika deltaX == 0 berarti pelanggan sedang berbelanja (tidak bergerak)
        Color shirtColor = (deltaX == 0) ? shoppingShirtColor : defaultShirtColor;
        Color pantsColor = new Color(0x424242);
        Color hairColor = new Color(0x8B4513);

        // Simpan transformasi lama agar bisa dikembalikan setelah menggambar
        AffineTransform oldTransform = g2d.getTransform();

        int transformX = x;
        int transformY = y;

        // Terapkan skala global untuk pelanggan ini
        g2d.scale(scale, scale);

        // Karena kita sudah menskalakan konteks, hitung koordinat yang sesuai
        int adjustedX = (int) (transformX / scale);
        int adjustedY = (int) (transformY / scale);

        // Kepala
        g2d.setColor(skinColor);
        g2d.fillOval(adjustedX + 10, adjustedY, 40, 40);

        // Rambut
        g2d.setColor(hairColor);
        g2d.fillArc(adjustedX + 10, adjustedY - 5, 40, 25, 0, 180);

        // Baju
        g2d.setColor(shirtColor);
        g2d.fillRect(adjustedX, adjustedY + 40, 60, 50);

        // Tangan (sebagai bagian tubuh berwarna kulit)
        g2d.setColor(skinColor);
        g2d.fillRoundRect(adjustedX - 10, adjustedY + 45, 20, 30, 10, 10);
        g2d.fillRoundRect(adjustedX + 50, adjustedY + 45, 20, 30, 10, 10);

        // Celana / kaki
        g2d.setColor(pantsColor);
        g2d.fillRect(adjustedX + 5, adjustedY + 90, 20, 30);
        g2d.fillRect(adjustedX + 35, adjustedY + 90, 20, 30);

        // Kembalikan transformasi grafis asli
        g2d.setTransform(oldTransform);
    }
}