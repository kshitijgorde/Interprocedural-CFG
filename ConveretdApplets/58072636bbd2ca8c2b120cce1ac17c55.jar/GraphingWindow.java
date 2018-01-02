import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphingWindow extends JPanel
{
    private float WINDOW_FRACTION;
    private int windowHeight;
    private int windowWidth;
    private JPanel global;
    private JPanel westPanel;
    private JPanel centerPanel;
    private JFrame win;
    private GraphArea graphArea;
    private ControlPanel controlPanel;
    private GraphMenu menu;
    private JPanel bottomPanel;
    private JLabel xCoordLabel;
    private JLabel yCoordLabel;
    private boolean isAnApplet;
    private GraphingApplet applet;
    
    public static void main(final String[] array) {
        final GraphingWindow graphingWindow = new GraphingWindow(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
    }
    
    public GraphingWindow(final GraphingApplet applet) {
        this.WINDOW_FRACTION = 0.9f;
        this.enableEvents(64L);
        this.isAnApplet = true;
        this.applet = applet;
        this.setLayout(new BorderLayout());
        try {
            this.init();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        applet.getContentPane().add(this);
    }
    
    public GraphingWindow(final GraphicsConfiguration graphicsConfiguration) {
        this.WINDOW_FRACTION = 0.9f;
        this.enableEvents(64L);
        this.isAnApplet = false;
        this.setLayout(new BorderLayout());
        (this.win = new JFrame("Graphing Calculator", graphicsConfiguration)).setDefaultCloseOperation(3);
        try {
            this.init();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.win.getContentPane().add(this);
        this.win.setSize(this.windowWidth, this.windowHeight);
        this.win.show();
    }
    
    public boolean isApplet() {
        return this.isAnApplet;
    }
    
    public GraphArea getGraphArea() {
        return this.graphArea;
    }
    
    public ControlPanel getControlPanel() {
        return this.controlPanel;
    }
    
    public GraphingApplet getApplet() {
        return this.applet;
    }
    
    public void init() {
        this.windowHeight = 600;
        this.windowWidth = 800;
        (this.global = new JPanel()).setLayout(new BorderLayout());
        this.graphArea = new GraphArea();
        this.menu = new GraphMenu(this);
        (this.controlPanel = new ControlPanel(this)).initializeComponents();
        this.initGui();
        this.global.add(this.centerPanel, "Center");
        this.global.add(this.menu, "North");
        this.global.add(this.westPanel, "West");
        this.setSize(this.windowWidth, this.windowHeight);
        this.setVisible(true);
        this.add(this.global);
    }
    
    private int getWindowHeight() {
        return this.windowHeight;
    }
    
    private int getWindowWidth() {
        return this.windowWidth;
    }
    
    private void initGui() {
        this.bottomPanel = new JPanel(new GridLayout(1, 2));
        final JPanel panel = new JPanel(new GridLayout(1, 3));
        (this.xCoordLabel = new JLabel("x = N/A")).setFont(new Font("Arial", 0, 12));
        (this.yCoordLabel = new JLabel("y = N/A")).setFont(new Font("Arial", 0, 12));
        panel.add(this.xCoordLabel);
        panel.add(this.yCoordLabel);
        panel.add(new JLabel(""));
        final JLabel label = new JLabel("Copyright (C) 2004 Udesh Senaratne");
        label.setFont(new Font("Arial", 2, 12));
        this.bottomPanel.add(panel);
        this.bottomPanel.add(label);
        (this.westPanel = new JPanel(new FlowLayout())).add(this.controlPanel);
        (this.centerPanel = new JPanel(new BorderLayout())).add(this.graphArea, "Center");
        this.centerPanel.add(this.bottomPanel, "South");
        this.graphArea.addMouseMotionListener(new GraphMotionListener());
        this.graphArea.addMouseListener(new GraphClickedListener());
        this.graphArea.setCursor(new Cursor(1));
    }
    
    private class GraphMotionListener implements MouseMotionListener
    {
        double x;
        double y;
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            this.x = (mouseEvent.getPoint().getX() - GraphingWindow.this.graphArea.getGraphAreaWidth() / 2) / GraphingWindow.this.graphArea.getPixelsPerUnit() + GraphingWindow.this.graphArea.getCenter().getWidth();
            this.y = GraphingWindow.this.graphArea.getCenter().getHeight() + (GraphingWindow.this.graphArea.getGraphAreaHeight() / 2 - mouseEvent.getPoint().getY()) / GraphingWindow.this.graphArea.getPixelsPerUnit();
            this.x = this.x - this.x % 1.0 + Math.rint(this.x % 1.0 * 1000.0) / 1000.0;
            this.y = this.y - this.y % 1.0 + Math.rint(this.y % 1.0 * 1000.0) / 1000.0;
            GraphingWindow.this.xCoordLabel.setText("x = " + this.x);
            GraphingWindow.this.yCoordLabel.setText("y = " + this.y);
        }
    }
    
    private class GraphClickedListener implements MouseListener
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            GraphingWindow.this.xCoordLabel.setText("x = N/A");
            GraphingWindow.this.yCoordLabel.setText("y = N/A");
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            final int n = (int)Math.round((mouseEvent.getPoint().getX() - GraphingWindow.this.graphArea.getGraphAreaWidth() / 2) / GraphingWindow.this.graphArea.getPixelsPerUnit() + GraphingWindow.this.graphArea.getCenter().getWidth());
            final int n2 = (int)Math.round(GraphingWindow.this.graphArea.getCenter().getHeight() + (GraphingWindow.this.graphArea.getGraphAreaHeight() / 2 - mouseEvent.getPoint().getY()) / GraphingWindow.this.graphArea.getPixelsPerUnit());
            GraphingWindow.this.controlPanel.setCenter(n, n2);
            GraphingWindow.this.graphArea.setCenter(n, n2);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
    }
}
