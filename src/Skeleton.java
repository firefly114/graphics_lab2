import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Skeleton extends JPanel implements ActionListener{
    private static int maxWidth;
    private static int maxHeight;

    Timer timer;
    // Для анімації повороту
    private double angle = 0;
    // Для анімації руху
    private double deltaX = 1;
    private int radius = 80;
    private double tx = 1;
    private double ty = 0;

    public Skeleton() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        g2d.setBackground(Color.gray);
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        BasicStroke bs1 = new BasicStroke(16, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs1);
        g2d.drawRect(20, 20, 800, 600);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


        g2d.translate(150, 100);

        g2d.translate(tx, ty);

        double points_0[][] = {
                {196, 150}, {206, 150}, {206, 230}, {196, 230}
        };
        GeneralPath stick = new GeneralPath();
        stick.moveTo(points_0[0][0], points_0[0][1]);
        for (int k = 1; k < points_0.length; k++)
            stick.lineTo(points_0[k][0], points_0[k][1]);
        stick.closePath();
        GradientPaint gp = new GradientPaint(5, 25,
                Color.BLACK, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);

        g2d.rotate(angle, stick.getBounds2D().getMaxX(),
                stick.getBounds2D().getMaxY());
        g2d.fill(stick);

        double points_1[][] = {
                {125, 150}, {285, 150}, {200, 10}
        };
        GeneralPath triangle_red = new GeneralPath();
        triangle_red.moveTo(points_1[0][0], points_1[0][1]);
        for (int k = 1; k < points_1.length; k++)
            triangle_red.lineTo(points_1[k][0], points_1[k][1]);
        triangle_red.closePath();
        g2d.setColor(Color.RED);

        g2d.fill(triangle_red);

        double points_2[][] = {
                {145, 140}, {265, 140}, {200, 30}
        };
        GeneralPath triangle_white = new GeneralPath();
        triangle_white.moveTo(points_2[0][0], points_2[0][1]);
        for (int k = 1; k < points_2.length; k++)
            triangle_white.lineTo(points_2[k][0], points_2[k][1]);
        triangle_white.closePath();
        g2d.setColor(Color.WHITE);
        g2d.fill(triangle_white);

        g2d.setColor(Color.RED);
        g2d.fillOval(191,50,20, 20);

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(191,82,20, 20);

        g2d.setColor(Color.GREEN);
        g2d.fillOval(191,115,20, 20);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Привіт, Java 2D!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(920, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Skeleton());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double radiusInSquare = Math.pow(radius, 2);
        if (tx <= 0 && ty < 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx > 0 && ty <= 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx >= 0 && ty > 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx < 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }
        angle += 0.01;
        repaint();
    }

}