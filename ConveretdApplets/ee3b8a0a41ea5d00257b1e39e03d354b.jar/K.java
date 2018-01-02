import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class K extends Frame
{
    private static Rectangle I;
    static Rectangle Z;
    private Insets add;
    private int bottom;
    private int dispose;
    private ztmPlayer enableEvents;
    private U getBackground;
    
    K(final ztmPlayer enableEvents) {
        this.getBackground = enableEvents.z;
        this.setLayout(null);
        this.setTitle(enableEvents.M);
        this.pack();
        this.add = this.getInsets();
        this.bottom = this.add.left + this.add.right;
        this.dispose = this.add.top + this.add.bottom;
        this.setBackground(this.getBackground.getBackground());
        if (null == K.I) {
            K.Z = new Rectangle(this.add.left, this.add.top, enableEvents.N << 1, enableEvents.O << 1);
            K.I = new Rectangle(this.bottom + K.Z.width, this.dispose + K.Z.height);
            try {
                final Dimension screenSize = this.getToolkit().getScreenSize();
                K.I.x = screenSize.width - K.Z.width >> 1;
                K.I.y = screenSize.height - K.Z.height >> 1;
            }
            catch (Exception ex) {}
        }
        this.setBounds(K.I);
        this.getBackground.I(true);
        this.getBackground.setBounds(this.add.left, this.add.top, this.getSize().width - this.bottom, this.getSize().height - this.dispose);
        this.add(this.getBackground);
        this.enableEvents(-1L);
        this.setVisible(true);
        this.requestFocus();
        this.enableEvents = enableEvents;
    }
    
    public final synchronized void dispose() {
        K.I = this.getBounds();
        this.setVisible(false);
        this.enableEvents.add(this.getBackground);
        this.enableEvents.R = null;
        this.enableEvents.hide_fscreen();
        this.getBackground.I(this.enableEvents.N, this.enableEvents.O);
        this.getBackground.setBounds(0, 0, this.enableEvents.getSize().width, this.enableEvents.getSize().height);
        this.getBackground.repaint();
        super.dispose();
    }
    
    protected final void processEvent(final AWTEvent awtEvent) {
        final int id = awtEvent.getID();
        if (awtEvent instanceof WindowEvent) {
            if (201 == id) {
                this.dispose();
                return;
            }
        }
        else if (awtEvent instanceof ComponentEvent && 101 == id) {
            this.getBackground.setBounds(this.add.left, this.add.top, this.getSize().width - this.bottom, this.getSize().height - this.dispose);
            K.Z = this.enableEvents.Q.getBounds();
            return;
        }
        super.processEvent(awtEvent);
    }
}
