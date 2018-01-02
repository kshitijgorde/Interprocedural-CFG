import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import gravity.GravityUpdateData;
import java.util.Observable;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Container;
import gravity.tools.AssertionException;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import gravity.Gravity;
import gravity.tools.LayoutValidator;
import gravity.ConstantsLibrary;
import java.awt.Button;
import java.awt.TextField;
import gravity.cosmos.CosmosCanvas;
import java.awt.Label;
import gravity.ILauncher;
import java.awt.event.KeyListener;
import java.util.Observer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JGravity extends Applet implements Observer, KeyListener, ILauncher
{
    private Label _info;
    private CosmosCanvas _screen;
    private TextField _angle;
    private Button _launch;
    private Button _newGame;
    private ConstantsLibrary _cl;
    private LayoutValidator _lv;
    private Gravity _gravity;
    private String _lastMessage;
    private static JGravity applet;
    
    public JGravity() {
        this._lastMessage = new String();
    }
    
    public void init() {
        super.init();
        this.setLayout(new BorderLayout());
        this._cl = new ConstantsLibrary();
        this.setForeground(this._cl.MAINFORECOLOR);
        this.setBackground(this._cl.MAINBACKCOLOR);
        final Container controls = this.controls();
        this._lv = new LayoutValidator(controls);
        final Label label = new Label("");
        ((Panel)controls).add(label);
        this.add("North", this._info = new Label(""));
        try {
            (this._gravity = new Gravity(this._cl)).addObserver(this);
            (this._screen = new CosmosCanvas(this._gravity.getCosmos(), this._cl, this._gravity.getPlayer().getAngleIndicator(), label, this._lv)).addKeyListener(this);
        }
        catch (AssertionException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        this.add("Center", this._screen);
        this.add("South", controls);
        this.setVisible(true);
        this.startNewGame(this._gravity.getRandomSeed(false));
    }
    
    private Container controls() {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(new Label("Angle:"));
        panel.add(this._angle = new TextField("0", 10));
        this._angle.addKeyListener(this);
        panel.add(this._launch = this.launchButton());
        panel.add(this.refreshButton());
        panel.add(this._newGame = this.newGameButton());
        panel.add(this.infoButton());
        return panel;
    }
    
    private Button launchButton() {
        final Button button = new Button("Launch!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String label = JGravity.this._launch.getLabel();
                JGravity.this._cl;
                if (label.equals("Launch!")) {
                    JGravity.this.launch();
                }
                else {
                    final String label2 = JGravity.this._launch.getLabel();
                    JGravity.this._cl;
                    if (label2.equals("Abort!")) {
                        JGravity.this.abort();
                    }
                    else {
                        final String label3 = JGravity.this._launch.getLabel();
                        JGravity.this._cl;
                        if (label3.equals("Start...")) {
                            JGravity.this.startNewGame(JGravity.this._angle.getText());
                        }
                    }
                }
            }
        });
        button.addKeyListener(this);
        return button;
    }
    
    private Button refreshButton() {
        final Button button = new Button("Refresh");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JGravity.this.refreshDisplay();
            }
        });
        button.addKeyListener(this);
        return button;
    }
    
    public void refreshDisplay() {
        this._gravity.refresh();
        this._screen.refresh();
        this._info.setText(this._lastMessage);
        this.setReady();
    }
    
    private Button newGameButton() {
        final Button button = new Button("New game");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JGravity.this.newGame(0L);
            }
        });
        button.addKeyListener(this);
        return button;
    }
    
    private Button infoButton() {
        final Button button = new Button("*");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JGravity.this.getInfos();
            }
        });
        button.addKeyListener(this);
        return button;
    }
    
    public void getInfos() {
        final String lastAngle = this._gravity.getLastAngle();
        String text = this._gravity.getRandomSeed(true);
        this._info.setText("Random seed: " + text + "  -  Last angle: " + lastAngle);
        if (this._launch.getLabel().equals("Launch!") || this._launch.getLabel().equals("Abort!")) {
            this._angle.setText(lastAngle);
            try {
                this._gravity.setAngle(new Double(this._angle.getText().trim()));
            }
            catch (NumberFormatException ex) {}
        }
        else if (this._launch.getLabel().equals("Start...")) {
            if (text.endsWith("'")) {
                text = text.substring(0, text.length() - 1);
            }
            this._angle.setText(text);
        }
        this._angle.requestFocus();
        this._angle.selectAll();
    }
    
    private synchronized void newGame(long n) {
        if (n == 0L) {
            this._gravity.newGame();
        }
        else {
            if (!this._gravity.isNewGame(n)) {
                n = Long.MAX_VALUE;
            }
            if (n == Long.MAX_VALUE) {
                this._gravity.restart();
            }
            else {
                this._gravity.newGame(n);
            }
        }
        try {
            this._screen.setCosmos(this._gravity.getCosmos(), n != Long.MAX_VALUE);
        }
        catch (AssertionException ex) {}
        this._screen.addMouseListeners(this, this._gravity.getPlayer().getAngleIndicator());
        this._screen.reset(false);
        this._launch.setLabel("Launch!");
        this._lv.validate(this._launch);
        this._angle.setText("0");
        this.setReady();
    }
    
    public void startNewGame(final String s) {
        long longValue = 0L;
        final String trim = s.trim();
        int n = trim.indexOf(".");
        String s2;
        if (n == -1) {
            s2 = trim + ".000000";
            n = s2.indexOf(".");
        }
        else {
            s2 = trim + "000000";
        }
        final String string = s2.substring(0, n) + s2.substring(n + 1, n + 6);
        try {
            longValue = new Long(string);
        }
        catch (NumberFormatException ex) {}
        this.newGame(longValue);
    }
    
    public String getAppletInfo() {
        return "Deep Space Voyager GRAVITY for Java - JGravity\n\n(Z) 2000-2001 by Simon Buenzli (zeniko@gmx.ch)";
    }
    
    public void setAngle(final double n) {
        this._angle.setText("" + (float)n);
        try {
            this._gravity.setAngle(new Double(this._angle.getText().trim()));
        }
        catch (NumberFormatException ex) {}
        this.setReady();
    }
    
    public void launch() {
        try {
            this._gravity.setAngle(new Double(this._angle.getText().trim()));
        }
        catch (NumberFormatException ex) {}
        this._launch.setLabel("Abort!");
        this._screen.reset(true);
        this._gravity.launch();
        this.setReady();
    }
    
    public void abort() {
        this._gravity.abort();
        this.setReady();
    }
    
    public void reset() {
        if (this.getState() == -1) {
            this.newGame(0L);
        }
        else {
            this.newGame(Long.MAX_VALUE);
        }
    }
    
    public void setReady() {
        this._info.setText(this._lastMessage);
        this._angle.requestFocus();
        this._angle.selectAll();
    }
    
    public int getState() {
        return this._gravity.getState();
    }
    
    public void shutDown() {
        if (this.getState() == 0 || this.getState() == 1) {
            this._gravity.shutDown();
        }
        this._angle.setText(this._gravity.getRandomSeed(false));
        this.setReady();
    }
    
    public void setInfty(final boolean infty) {
        this._gravity.setInfty(infty);
    }
    
    public void doPanic() {
        if (this.getState() == 0 && (this._gravity.getPlayer().getNumberOfProbes() > 1 || this._cl.ISINFTYMODE)) {
            this._launch.setLabel("Abort!");
            this._screen.reset(true);
        }
        this._gravity.doPanic();
        this.setReady();
    }
    
    public void setGravField(final boolean isgravfield) {
        this._info.setText("Drawing the gravitational field: " + (isgravfield ? "enabled" : "disabled") + "...");
        if (isgravfield != this._cl.ISGRAVFIELD) {
            this._cl.ISGRAVFIELD = isgravfield;
            this._screen.refresh();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 10: {
                if (!keyEvent.getComponent().equals(this._angle)) {
                    break;
                }
                switch (this.getState()) {
                    case 0: {
                        this.launch();
                        break;
                    }
                    case 1: {
                        this.abort();
                        break;
                    }
                    case -1:
                    case 2: {
                        this.startNewGame(this._angle.getText());
                        break;
                    }
                }
                break;
            }
            case 27: {
                this.shutDown();
                break;
            }
            default: {
                if (this.getState() != 0) {
                    if (this.getState() != 1) {
                        break;
                    }
                }
                try {
                    this._gravity.setAngle(new Double(this._angle.getText().trim()));
                }
                catch (NumberFormatException ex) {
                    this._gravity.setAngle(0.0);
                }
                break;
            }
        }
        if (keyEvent.getModifiers() == 2) {
            switch (keyEvent.getKeyCode()) {
                case 78: {
                    this.newGame(0L);
                    break;
                }
                case 82: {
                    this.refreshDisplay();
                    break;
                }
                case 73: {
                    this.getInfos();
                    break;
                }
                case 70: {
                    this.setInfty(!this._cl.ISINFTYMODE);
                    break;
                }
                case 80: {
                    this.doPanic();
                    break;
                }
                case 71: {
                    this.setGravField(!this._cl.ISGRAVFIELD);
                    break;
                }
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof GravityUpdateData) {
            if (!((GravityUpdateData)o).message.equals("")) {
                this._lastMessage = ((GravityUpdateData)o).message;
            }
            this._info.setText(this._lastMessage);
            switch (this.getState()) {
                case 0: {
                    this._launch.setLabel("Launch!");
                    this._lv.validate(this._launch);
                    break;
                }
                case 1: {
                    this._launch.setLabel("Abort!");
                    break;
                }
                default: {
                    this._launch.setLabel("Start...");
                    this._newGame.requestFocus();
                    break;
                }
            }
        }
        this._screen.repaint();
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("JGravity by zeniko@gmx.ch");
        JGravity.applet = new JGravity();
        frame.setSize(500, 300);
        try {
            frame.setIconImage(frame.getToolkit().getImage(JGravity.applet.getClass().getResource("jgravity.gif")));
        }
        catch (Exception ex) {}
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                JGravity.applet.shutDown();
                System.exit(0);
            }
        });
        JGravity.applet.init();
        frame.add("Center", JGravity.applet);
        frame.show();
    }
}
