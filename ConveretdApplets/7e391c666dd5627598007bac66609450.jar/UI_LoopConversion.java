import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_LoopConversion extends JApplet
{
    private boolean _boolApplet;
    private boolean _boolLoaded;
    private Loops _loops;
    private int _index;
    private static JTabbedPane _tabMain;
    private JLabel _labelTitle;
    private LoopPanel _w2rWhile;
    private LoopPanel _w2rRepeat;
    private LoopPanel _r2wWhile;
    private LoopPanel _r2wRepeat;
    public static final int _seed;
    public static final Random _random;
    
    public UI_LoopConversion() {
        this(true);
    }
    
    public UI_LoopConversion(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        this.getContentPane().setBackground(Main.WINDOW_COLOR);
        this._index = 0;
        this._loops = new Loops();
        this.getContentPane().setLayout(null);
        (UI_LoopConversion._tabMain = new JTabbedPane()).setBounds(45, 40, 705, 515);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 100, 100);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setVisible(true);
        panel.setLayout(null);
        this._w2rWhile = new LoopPanel(this._loops.getLoop(this._index), true, false);
        this._w2rRepeat = new LoopPanel(this._loops.getLoop(this._index), false, true);
        panel.add(this._w2rWhile);
        panel.add(this._w2rRepeat);
        UI_LoopConversion._tabMain.add("<html>While&nbsp;<font size='-2'>&gt;&gt;</font>&nbsp;Repeat</html>", panel);
        UI_LoopConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        panel = new JPanel();
        panel.setBounds(0, 0, 100, 100);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setVisible(true);
        panel.setLayout(null);
        this._r2wWhile = new LoopPanel(this._loops.getLoop(this._index), false, false);
        this._r2wRepeat = new LoopPanel(this._loops.getLoop(this._index), true, true);
        panel.add(this._r2wWhile);
        panel.add(this._r2wRepeat);
        UI_LoopConversion._tabMain.add("<html>Repeat&nbsp;<font size='-2'>&gt;&gt;</font>&nbsp;While</html>", panel);
        UI_LoopConversion._tabMain.setBackgroundAt(0, Main.WINDOW_COLOR);
        this.pInitTitle(this._loops.getLoop(this._index).getTitle());
        final JButton button = new JButton("New Loop");
        button.setBounds(650, 30, 100, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_LoopConversion.this.buttonNewClick();
            }
        });
        this.getContentPane().add(button);
        this.getContentPane().add(UI_LoopConversion._tabMain);
        if (!this._boolLoaded) {}
        this._boolLoaded = true;
    }
    
    private void buttonNewClick() {
        if (this._loops == null || this._loops.getSize() < 2) {
            return;
        }
        ++this._index;
        this._index %= this._loops.getSize();
        final Loop loop = this._loops.getLoop(this._index);
        if (loop == null) {
            return;
        }
        this._w2rWhile.setLoop(loop);
        this._w2rRepeat.setLoop(loop);
        this._r2wWhile.setLoop(loop);
        this._r2wRepeat.setLoop(loop);
        this._labelTitle.setText(loop.getTitle());
        this.getContentPane().repaint();
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        (this._labelTitle = new JLabel(sTitle)).setHorizontalTextPosition(0);
        this._labelTitle.setHorizontalAlignment(0);
        this._labelTitle.setFont(new Font("Dialog", 1, 28));
        this._labelTitle.setForeground(Color.orange);
        this._labelTitle.setBounds(70, 10, 800, 50);
        this.getContentPane().add(this._labelTitle);
    }
    
    static {
        _seed = new Random().nextInt();
        _random = new Random(UI_LoopConversion._seed);
    }
}
