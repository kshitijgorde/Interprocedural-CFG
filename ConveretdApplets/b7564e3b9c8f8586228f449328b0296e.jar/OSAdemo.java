import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;
import java.awt.Button;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OSAdemo extends Applet
{
    private Dimension prefSize;
    private static OSApane colorOSA;
    private Button tiltButton;
    private Button incButton;
    private Button decButton;
    private Button rotXButton;
    private Button rotYButton;
    private Button rotZButton;
    private Label planeText;
    private Label planeDesc;
    
    public OSAdemo() {
        this.prefSize = null;
    }
    
    private static ActionListener DecButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.decrease();
            }
        };
    }
    
    private static ActionListener IncButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.increase();
            }
        };
    }
    
    private static WindowAdapter LocalWindowAdapter() {
        return new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        };
    }
    
    private OSApaneListener PaneListener() {
        return new OSApaneListener() {
            public void paneNavigated(final OSApaneEvent osApaneEvent) {
                OSAdemo.this.rotXButton.setEnabled(osApaneEvent.gotPivot);
                OSAdemo.this.rotYButton.setEnabled(osApaneEvent.gotPivot);
                OSAdemo.this.rotZButton.setEnabled(osApaneEvent.gotPivot);
                OSAdemo.this.tiltButton.setEnabled(osApaneEvent.gotPivot);
                OSAdemo.this.incButton.setEnabled(OSAdemo.colorOSA.canRaise());
                OSAdemo.this.decButton.setEnabled(OSAdemo.colorOSA.canLower());
                OSAdemo.this.planeDesc.setText(OSAdemo.colorOSA.getPlaneDesc());
            }
        };
    }
    
    private static ActionListener RotXButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.rotate90(0);
            }
        };
    }
    
    private static ActionListener RotYButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.rotate90(1);
            }
        };
    }
    
    private static ActionListener RotZButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.rotate90(2);
            }
        };
    }
    
    private static ActionListener TiltButtonListener() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                OSAdemo.colorOSA.setTilt(OSAdemo.colorOSA.getTilt() ^ true);
            }
        };
    }
    
    public Dimension getPreferredSize() {
        if (this.prefSize == null) {
            this.prefSize = new Dimension(640, 480);
        }
        return this.prefSize;
    }
    
    public void init() {
        this.add("North", new Label("OSA-UCS Full-Step Set Demonstration"));
        this.setBackground(Color.white);
        (OSAdemo.colorOSA = new OSApane()).addOSApaneListener(this.PaneListener());
        this.add("Center", OSAdemo.colorOSA);
        (this.tiltButton = new Button("tilt")).addActionListener(TiltButtonListener());
        this.add("East", this.tiltButton);
        (this.incButton = new Button("increase")).addActionListener(IncButtonListener());
        this.add("East", this.incButton);
        (this.decButton = new Button("decrease")).addActionListener(DecButtonListener());
        this.add("East", this.decButton);
        this.add("East", this.planeDesc = new Label(String.valueOf(OSAdemo.colorOSA.getPlaneDesc()) + "    "));
        (this.rotXButton = new Button("rotate X +90deg")).addActionListener(RotXButtonListener());
        this.add("East", this.rotXButton);
        (this.rotYButton = new Button("rotate Y +90deg")).addActionListener(RotYButtonListener());
        this.add("East", this.rotYButton);
        (this.rotZButton = new Button("rotate Z +90deg")).addActionListener(RotZButtonListener());
        this.add("East", this.rotZButton);
        this.add("South", new Label("Copyright 1989-2000 Jim W. Lai"));
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("OSA-UCS Demo");
        final OSAdemo osAdemo = new OSAdemo();
        osAdemo.init();
        frame.add("Center", osAdemo);
        frame.pack();
        frame.show();
        frame.addWindowListener(LocalWindowAdapter());
        osAdemo.start();
    }
}
