import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SimpleDrawingApp extends JFrame {

    private DrawPanel drawPanel;

    public SimpleDrawingApp() {
        setTitle("Simple Drawing App");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawPanel = new DrawPanel();
        add(drawPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleDrawingApp();
    }

    // Panel where drawing happens
    class DrawPanel extends JPanel {

        private java.util.List<Point> points = new ArrayList<>();

        public DrawPanel() {
            setBackground(Color.WHITE);

            // Mouse motion listener to draw lines while dragging
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    points.add(e.getPoint());
                    repaint();
                }
            });

            // To stop drawing when mouse is released
            addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    points.add(null); // null separates different strokes
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            for (int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                if (p1 != null && p2 != null) {
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}