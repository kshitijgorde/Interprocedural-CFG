import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.Label;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class JDialog extends Dialog implements WindowListener
{
    int height;
    int width;
    GridLayout grid;
    Panel text;
    Panel button;
    
    public JDialog(final Frame frame, final String str) {
        super(frame);
        this.height = 200;
        this.width = 300;
        this.grid = new GridLayout(0, 1);
        this.text = new Panel(this.grid);
        this.button = new Panel(new FlowLayout(1));
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.add("Center", this.text);
        this.add("South", this.button);
        this.setRows(this.showText(str));
        this.setLocation((frame.getSize().width - this.width) / 2, (frame.getSize().height - this.height) / 2);
        this.addWindowListener(this);
    }
    
    private int showText(final String data) {
        String S = "";
        boolean putLabel = false;
        int ndx = 0;
        int mode = 0;
        final StreamTokenizer st = new StreamTokenizer(new StringReader(data));
        try {
            st.ordinaryChar(39);
            st.ordinaryChars(48, 57);
            st.ordinaryChar(46);
            st.ordinaryChar(32);
            st.ordinaryChar(47);
            int tt;
            while ((tt = st.nextToken()) != -1) {
                String val;
                if (tt == -3 | tt == -2) {
                    val = ((tt == -3) ? (val = st.sval) : new Double(st.nval).toString());
                }
                else if ((char)tt == '|') {
                    mode = 1;
                    putLabel = true;
                    val = "";
                }
                else {
                    val = new Character((char)tt).toString();
                }
                final int len = this.getFontMetrics(this.getFont()).stringWidth(String.valueOf(S) + val);
                if (len > this.width - 30 | putLabel) {
                    if (mode == 0) {
                        S = "    " + S;
                    }
                    this.text.add(new Label(S, mode));
                    ++ndx;
                    if (putLabel) {
                        putLabel = false;
                    }
                    S = val;
                }
                else {
                    S = String.valueOf(S) + val;
                }
            }
            if (mode == 0) {
                S = "    " + S;
            }
            this.text.add(new Label(S, mode));
            ++ndx;
        }
        catch (IOException ex) {}
        return ndx;
    }
    
    public void setRows(final int rows) {
        this.grid.setRows(rows);
        this.setSize(this.width, this.getFontMetrics(this.getFont()).getHeight() * rows * 2);
    }
    
    public void windowClosed(final WindowEvent event) {
    }
    
    public void windowDeiconified(final WindowEvent event) {
    }
    
    public void windowIconified(final WindowEvent event) {
    }
    
    public void windowActivated(final WindowEvent event) {
    }
    
    public void windowDeactivated(final WindowEvent event) {
    }
    
    public void windowOpened(final WindowEvent event) {
    }
    
    public void windowClosing(final WindowEvent event) {
        this.dispose();
    }
    
    public void setSize(final int x, final int y) {
        super.setSize(x, y);
        this.width = x;
        this.height = y;
    }
}
