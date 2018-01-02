import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_TextFrame extends Frame
{
    private PGC owner;
    private Panel pnl_root;
    private TextArea textarea;
    private int height;
    private int width;
    
    public PGC_TextFrame(final PGC owner, final String s, final int height, final int width) {
        super(s);
        this.owner = owner;
        this.height = height;
        this.width = width;
    }
    
    public void init() {
        this.add("Center", this.pnl_root = new Panel());
        this.pnl_root.setBackground(PGC.color_panel);
        this.pnl_root.setLayout(new GridBagLayout());
        this.pnl_root.add(this.textarea = new TextArea(this.height, this.width));
        this.textarea.setEditable(false);
        this.textarea.setBackground(PGC.color_background);
        this.setResizable(false);
        this.pack();
    }
    
    public void Write(final Object o) {
        this.textarea.appendText(o.toString());
    }
    
    public void ShowTop() {
        this.textarea.select(0, 0);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            if (this.owner != null) {
                this.owner.FrameDestroy(this);
            }
            return true;
        }
        if (event.id == 1004) {
            this.textarea.requestFocus();
        }
        return false;
    }
}
