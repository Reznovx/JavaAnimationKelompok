import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * WarungAnimation
 * - Panel utama yang menggambar latar, warung, pohon, pedagang, dan pelanggan.
 * - Menggunakan Timer untuk animasi berkala (actionPerformed dipanggil tiap tick).
 */
public class WarungAnimation extends JPanel implements ActionListener {

    // Array pelanggan yang bergerak di latar
    private CustomerObject[] customers;
    // Pelanggan yang sedang berbelanja (tidak bergerak)
    private CustomerObject customerBerbelanja;
    // Array awan
    private Cloud[] clouds;
    // Timer untuk mengupdate animasi
    private Timer timer;

    public WarungAnimation() {
        // Inisialisasi beberapa pelanggan dengan posisi, kecepatan, dan skala berbeda
        customers = new CustomerObject[3];
        int customerY = 430;

        customers[0] = new CustomerObject(0, customerY, 2, 1.0);
        customers[1] = new CustomerObject(750, customerY, -1, 0.9);
        customers[2] = new CustomerObject(-150, customerY + 30, 3, 1.1);

        // Tentukan posisi warung dan pelanggan yang sedang berbelanja
        int warungX = 800 / 2 - 300 / 2;
        int warungY = 600 / 2 - 200;
        int shoppingCustomerX = warungX + 350;
        int shoppingCustomerY = warungY + 130;
        customerBerbelanja = new CustomerObject(shoppingCustomerX, shoppingCustomerY, 0, 1.0);

        // Inisialisasi awan
        clouds = new Cloud[3];
        clouds[0] = new Cloud(50, 50);
        clouds[1] = new Cloud(300, 80);
        clouds[2] = new Cloud(600, 40);

        // Timer 15ms per tick untuk animasi ~66 FPS (tergantung performa)
        timer = new Timer(15, this);
        timer.start();
    }

    /**
     * actionPerformed
     * - Dipanggil setiap tick Timer.
     * - Update posisi semua objek animasi lalu repaint panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        for (CustomerObject customer : customers) {
            customer.update(panelWidth, panelHeight);
        }

        for (Cloud cloud : clouds) {
            cloud.update(panelWidth);
        }

        repaint(); // panggil paintComponent untuk menggambar frame baru
    }

    /**
     * paintComponent
     * - Menggambar seluruh latar, warung, pohon, pedagang, dan pelanggan.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Aktifkan antialiasing supaya gambar lebih halus
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Warna-warna yang dipakai
        Color skyBlue = new Color(0x87CEEB);
        Color grassGreen = new Color(0x7CFC00);
        Color roadGray = new Color(0x696969);
        Color brown = new Color(0x8B4513);
        Color redRoof = new Color(0xDC143C);
        Color cream = new Color(0xFFFDD0);
        Color goodsColor = new Color(0xFFA500);
        Color black = Color.BLACK;

        Color skinColor = new Color(0xFFDBAC);
        Color sellerShirt = new Color(0x008000);

        // Langit
        g2d.setColor(skyBlue);
        g2d.fillRect(0, 0, getWidth(), getHeight() / 2);

        // Gambar awan
        for (Cloud cloud : clouds) {
            cloud.draw(g2d);
        }

        // Rumput
        g2d.setColor(grassGreen);
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 4);

        // Jalan
        g2d.setColor(roadGray);
        g2d.fillRect(0, getHeight() * 3 / 4, getWidth(), getHeight() / 4);

        // Gambar pohon sederhana (batang + daun + bunga)
        Color trunkColor = new Color(0x8B7355);
        Color leafColor = new Color(0x228B22);
        Color flowerColor = new Color(0xFFDEAD);

        int treeX = getWidth() / 2 - 250;
        int treeY = getHeight() / 2 + 50;

        g2d.setColor(trunkColor);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Batang dan cabang
        g2d.drawLine(treeX + 20, treeY + 50, treeX + 20, treeY);
        g2d.drawLine(treeX + 20, treeY, treeX - 10, treeY - 40);
        g2d.drawLine(treeX + 20, treeY, treeX + 50, treeY - 20);

        g2d.setStroke(new BasicStroke(1));

        // Daun
        g2d.setColor(leafColor);
        g2d.fillOval(treeX - 45, treeY - 80, 80, 80);
        g2d.fillOval(treeX + 10, treeY - 50, 70, 70);

        // Bunga kecil pada dedaunan
        g2d.setColor(flowerColor);
        g2d.fillOval(treeX - 15, treeY - 60, 10, 10);
        g2d.fillOval(treeX + 40, treeY - 30, 10, 10);
        g2d.fillOval(treeX + 15, treeY - 70, 10, 10);


        // Gambar Pohon persis di sebelah kanan warung
        int tree2X = getWidth() / 2 + 250;
        int tree2Y = getHeight() / 2 + 5;
        g2d.setColor(trunkColor);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // Batang dan cabang
        g2d.drawLine(tree2X + 20, tree2Y + 50, tree2X + 20, tree2Y);
        g2d.drawLine(tree2X + 20, tree2Y, tree2X - 10, tree2Y - 40);
        g2d.drawLine(tree2X + 20, tree2Y, tree2X + 50, tree2Y - 20);
        g2d.setStroke(new BasicStroke(1));
        // Daun
        g2d.setColor(leafColor);
        g2d.fillOval(tree2X - 45, tree2Y - 80, 80, 80);
        g2d.fillOval(tree2X + 10, tree2Y - 50, 70, 70);
        // Bunga kecil pada dedaunan
        g2d.setColor(flowerColor);
        g2d.fillOval(tree2X - 15, tree2Y - 60, 10, 10);
        g2d.fillOval(tree2X + 40, tree2Y - 30, 10, 10);
        g2d.fillOval(tree2X + 15, tree2Y - 70, 10, 10);

        // Gambar warung (bangunan, atap, barang)
        int warungWidth = 300;
        int warungHeight = 200;
        int warungX = getWidth() / 2 - warungWidth / 2;
        int warungY = getHeight() / 2 - warungHeight;

        g2d.setColor(cream);
        g2d.fillRect(warungX, warungY, warungWidth, warungHeight);

        g2d.setColor(redRoof);
        int[] roofX = {warungX - 20, warungX + warungWidth + 20, warungX + warungWidth / 2};
        int[] roofY = {warungY, warungY, warungY - 70};
        g2d.fillPolygon(roofX, roofY, 3);

        g2d.setColor(brown);
        g2d.fillRect(warungX + 20, warungY + warungHeight - 50, warungWidth - 40, 50);

        // Barang dagangan di depan warung
        g2d.setColor(goodsColor);
        g2d.fillOval(warungX + 40, warungY + warungHeight - 70, 30, 30);
        g2d.fillRect(warungX + 90, warungY + warungHeight - 70, 40, 20);
        g2d.fillRoundRect(warungX + 150, warungY + warungHeight - 80, 50, 40, 10, 10);

        // Jendela atau area tampilan berwarna langit
        g2d.setColor(new Color(0x87CEEB));
        g2d.fillRect(warungX + warungWidth / 2 - 40, warungY + 30, 80, 100);

        // Nama warung
        g2d.setColor(black);
        g2d.setFont(new Font("Serif", Font.BOLD, 24));
        g2d.drawString("WARUNG SHAPES", warungX + 60, warungY + 25);

        // Gambar pedagang di depan warung (sederhana)
        int sellerX = warungX + warungWidth / 2 - 30;
        int sellerY = warungY + warungHeight - 120;

        g2d.setColor(skinColor);
        g2d.fillOval(sellerX + 10, sellerY, 40, 40);

        g2d.setColor(sellerShirt);
        g2d.fillRect(sellerX, sellerY + 40, 60, 60);

        g2d.setColor(skinColor);
        g2d.fillRoundRect(sellerX - 10, sellerY + 60, 20, 30, 10, 10);
        g2d.fillRoundRect(sellerX + 50, sellerY + 60, 20, 30, 10, 10);

        // Gambar pelanggan yang sedang berbelanja
        customerBerbelanja.draw((Graphics2D) g);

        // Gambar pelanggan yang sedang bergerak di latar
        for (CustomerObject customer : customers) {
            customer.draw((Graphics2D) g);
        }

        // Matahari
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(getWidth() - 100, 20, 80, 80);
    }

    /**
     * main
     * - Membuat frame dan menambahkan panel animasi.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animasi WARUNG GRAFIK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        WarungAnimation panel = new WarungAnimation();
        frame.add(panel);

        frame.setVisible(true);
    }
}