import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Random;
import javax.swing.JTabbedPane;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_NumConversion extends JApplet implements RadioEvent
{
    boolean _boolApplet;
    boolean _boolLoaded;
    private static JTabbedPane _tabMain;
    private static RadioEvent[] _panels;
    private final int _seed;
    private final Random _random;
    
    public UI_NumConversion() {
        this(true);
    }
    
    public UI_NumConversion(final boolean applet) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        this._boolApplet = applet;
        if (applet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        this.getContentPane().setBackground(Main.WINDOW_COLOR);
        this.getContentPane().setLayout(null);
        (UI_NumConversion._tabMain = new JTabbedPane()).setBounds(45, 20, 705, 515);
        (UI_NumConversion._panels = new RadioEvent[6])[0] = new Bin2DecPanel(this);
        UI_NumConversion._panels[1] = new Dec2BinPanel(this);
        UI_NumConversion._panels[2] = new Bin2HexPanel(this);
        UI_NumConversion._panels[3] = new Hex2BinPanel(this);
        UI_NumConversion._panels[4] = new Bin2OctPanel(this);
        UI_NumConversion._panels[5] = new Oct2BinPanel(this);
        UI_NumConversion._tabMain.add("Bin2Dec", (Component)UI_NumConversion._panels[0]);
        UI_NumConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        UI_NumConversion._tabMain.add("Dec2Bin", (Component)UI_NumConversion._panels[1]);
        UI_NumConversion._tabMain.setBackgroundAt(1, Main.WINDOW_COLOR);
        UI_NumConversion._tabMain.add("Bin2Hex", (Component)UI_NumConversion._panels[2]);
        UI_NumConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        UI_NumConversion._tabMain.add("Hex2Bin", (Component)UI_NumConversion._panels[3]);
        UI_NumConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        UI_NumConversion._tabMain.add("Bin2Oct", (Component)UI_NumConversion._panels[4]);
        UI_NumConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        UI_NumConversion._tabMain.add("Oct2Bin", (Component)UI_NumConversion._panels[5]);
        UI_NumConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        this.getContentPane().add(UI_NumConversion._tabMain);
        this.update();
        this._boolLoaded = true;
    }
    
    public void update() {
        final int intNumber = this._random.nextInt(254) + 1;
        for (int i = 0; i < UI_NumConversion._panels.length; ++i) {
            UI_NumConversion._panels[i].update(intNumber);
        }
    }
    
    public void update(final int newNumber) {
    }
}
