import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class MsgDlg extends Frame
{
    private static final String DEF_BTN_TEXT = "Close";
    private static final int ROW_PAD = 2;
    private String defBtnText;
    private String body;
    private int rows;
    private int cols;
    private Component[] comps;
    private Component focus;
    
    MsgDlg(final String s, final String s2) {
        this.init(s, s2, null, this.getRowCount(s2), this.getColCount(s2), null, null);
    }
    
    MsgDlg(final String s, final String s2, final Component[] array, final Component component) {
        this.init(s, s2, null, this.getRowCount(s2), this.getColCount(s2), array, component);
    }
    
    MsgDlg(final String s, final String s2, final String s3, final Component[] array, final Component component) {
        this.init(s, s2, s3, this.getRowCount(s2), this.getColCount(s2), array, component);
    }
    
    MsgDlg(final String s, final String s2, final String s3, final int n, final Component[] array, final Component component) {
        this.init(s, s2, s3, n, this.getColCount(s2), array, component);
    }
    
    MsgDlg(final String s, final String s2, final String s3, final int n, final int n2, final Component[] array, final Component component) {
        this.init(s, s2, s3, n, n2, array, component);
    }
    
    protected void init(final String s, final String s2, final String s3, final int rows, final int cols, final Component[] comps, final Component focus) {
        final String s4 = (s == null) ? "" : s;
        this.body = ((s2 == null) ? "" : s2);
        this.defBtnText = ((s3 == null) ? "Close" : s3);
        this.rows = rows;
        this.cols = cols;
        this.comps = comps;
        this.focus = focus;
        new _MsgDlg(new Frame(), s4).setVisible(true);
    }
    
    protected int getRowCount(final String s) {
        int countTokens = new StringTokenizer(s, "\n").countTokens();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", true);
        String s2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("\n") && s2.equals("\n")) {
                ++countTokens;
            }
            s2 = nextToken;
        }
        return countTokens + 2;
    }
    
    protected int getColCount(final String s) {
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            final int length = stringTokenizer.nextToken().length();
            if (n < length) {
                n = length;
            }
        }
        return n;
    }
    
    class _MsgDlg extends Dialog implements ActionListener, WindowListener
    {
        private boolean activated;
        protected Button btnClose;
        
        public void windowOpened(final WindowEvent windowEvent) {
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
            if (!this.activated) {
                this.activated = true;
                if (MsgDlg.this.focus == null) {
                    this.btnClose.requestFocus();
                    return;
                }
                MsgDlg.this.focus.requestFocus();
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            this.setVisible(false);
            this.dispose();
        }
        
        _MsgDlg(final Frame frame, final String s) {
            super(frame, s, true);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == this.btnClose) {
                this.setVisible(false);
            }
        }
        
        public void addNotify() {
            super.addNotify();
            final TextArea textArea = new TextArea(MsgDlg.this.body, MsgDlg.this.rows, MsgDlg.this.cols);
            textArea.setEditable(false);
            this.add("Center", textArea);
            (this.btnClose = new Button(MsgDlg.this.defBtnText)).addActionListener(this);
            final Panel panel = new Panel();
            panel.setBackground(Color.gray);
            panel.setLayout(new FlowLayout(1, 5, 10));
            if (MsgDlg.this.comps != null) {
                for (int i = 0; i < MsgDlg.this.comps.length; ++i) {
                    panel.add(MsgDlg.this.comps[i]);
                }
            }
            panel.add(this.btnClose);
            this.add("South", panel);
            this.addWindowListener(this);
            this.pack();
            this.centerDlg();
        }
        
        void centerDlg() {
            final Dimension screenSize = this.getToolkit().getScreenSize();
            final Rectangle bounds = this.getBounds();
            this.setLocation((screenSize.width - bounds.width) / 2, (screenSize.height - bounds.height) / 2);
        }
    }
}
