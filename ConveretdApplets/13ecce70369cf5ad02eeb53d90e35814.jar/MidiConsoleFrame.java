import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class MidiConsoleFrame extends Frame
{
    public MidiConsoleFrame(final MidiGuitar midiGuitar, final Color background) {
        super("MidiConsole");
        this.setVisible(true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int n = 520;
        final int n2 = 120;
        this.setLocation(screenSize.width / 2 - n / 2, screenSize.height / 2 - n2 / 2);
        this.setSize(n, n2);
        this.setBackground(background);
        this.setLayout(new BorderLayout());
        this.add("Center", midiGuitar.getMidiConsole());
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                MidiConsoleFrame.this.dispose();
            }
        });
    }
}
