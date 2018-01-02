import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Maze3D extends Applet
{
    public boolean clearUserAttempts;
    public boolean hexagonalRooms;
    private MazeControls mazeControls;
    public MazeCanvas mazeCanvas;
    public TextField message;
    public boolean solutionDisplayed;
    public double tilt;
    private Scrollbar tiltScrollbar;
    
    public void init() {
        this.solutionDisplayed = false;
        this.setLayout(new BorderLayout());
        new Panel();
        this.hexagonalRooms = false;
        this.add("Center", this.mazeCanvas = new MazeCanvas(this));
        this.add("South", this.mazeControls = new MazeControls(this));
        this.tilt = 45.0;
        this.add("East", this.tiltScrollbar = new Scrollbar(1, (int)this.tilt, 5, 30, 60));
        (this.message = new TextField(117)).setEditable(false);
        this.add("North", this.message);
        this.resize(600, 350);
        if (this.hexagonalRooms) {
            this.message.setText("Use Home, Up Arrow, PgUp, End, Down Arrow, or PgDn to solve.");
            return;
        }
        this.message.setText("Use the arrow keys to solve.");
    }
    
    public void start() {
        this.mazeControls.enable();
        this.mazeControls.requestFocus();
    }
    
    public void stop() {
        this.mazeControls.disable();
    }
    
    public void destroy() {
        this.mazeCanvas.p.stop();
    }
    
    public boolean handleEvent(final Event event) {
        boolean b;
        if (event.target == this.tiltScrollbar) {
            if (!this.mazeCanvas.p.alreadyPainting) {
                if (this.solutionDisplayed || this.mazeCanvas.p.userHasSolved) {
                    this.message.setText("");
                }
                else if (this.hexagonalRooms) {
                    this.message.setText("Use Home, Up Arrow, PgUp, End, Down Arrow, or PgDn to solve.");
                }
                else {
                    this.message.setText("Use the arrow keys to solve.");
                }
            }
            this.tilt = 90 - this.tiltScrollbar.getValue();
            this.mazeCanvas.paint(this.mazeCanvas.getGraphics());
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public static void main(final String[] array) {
    }
}
