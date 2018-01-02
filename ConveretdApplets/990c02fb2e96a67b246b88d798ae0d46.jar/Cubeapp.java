import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cubeapp extends Studio
{
    Cubist theArtist;
    Checkbox C1;
    Checkbox C2;
    Checkbox C3;
    Panel controlPanel;
    
    public void init() {
        this.theArtist = new Cubist();
        this.setLayout(new BorderLayout());
        (this.controlPanel = new Panel()).setLayout(new GridLayout(3, 1));
        this.controlPanel.setBackground(Color.white);
        this.C1 = new Checkbox("x", true);
        this.C2 = new Checkbox("y", true);
        this.C3 = new Checkbox("z", true);
        this.C1.addItemListener(new IL());
        this.C2.addItemListener(new IL());
        this.C3.addItemListener(new IL());
        this.controlPanel.add(this.C1);
        this.controlPanel.add(this.C2);
        this.controlPanel.add(this.C3);
        this.add("Center", super.theFlipbook = new Flipbook(this, this.theArtist, 200, 200));
        this.add("East", this.controlPanel);
        super.theFlipbook.addMouseListener(new Mouser(this));
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Quaternion Cube");
        final Cubeapp cubeapp = new Cubeapp();
        frame.addWindowListener(new WL());
        frame.add("Center", cubeapp);
        frame.setSize(230, 200);
        cubeapp.init();
        cubeapp.start();
        frame.setVisible(true);
    }
    
    class ML extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == Cubeapp.this.C1) {
                Cubeapp.this.theArtist.xrotor = (Cubeapp.this.C1.getState() ^ true);
            }
            if (mouseEvent.getSource() == Cubeapp.this.C2) {
                Cubeapp.this.theArtist.yrotor = (Cubeapp.this.C2.getState() ^ true);
            }
            if (mouseEvent.getSource() == Cubeapp.this.C3) {
                Cubeapp.this.theArtist.zrotor = (Cubeapp.this.C3.getState() ^ true);
            }
            mouseEvent.consume();
        }
    }
    
    class MX extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            mouseEvent.consume();
            Cubeapp.this.theArtist.xrotor ^= true;
        }
    }
    
    class IL implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getSource() == Cubeapp.this.C1) {
                Cubeapp.this.theArtist.xrotor ^= true;
            }
            if (itemEvent.getSource() == Cubeapp.this.C2) {
                Cubeapp.this.theArtist.yrotor ^= true;
            }
            if (itemEvent.getSource() == Cubeapp.this.C3) {
                Cubeapp.this.theArtist.zrotor ^= true;
            }
        }
    }
    
    class MY extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            mouseEvent.consume();
            Cubeapp.this.theArtist.yrotor ^= true;
        }
    }
    
    class MZ extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            mouseEvent.consume();
            Cubeapp.this.theArtist.zrotor ^= true;
        }
    }
}
