import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_bm extends JInternalFrame
{
    public rp_bm(final String s, final boolean b) {
        super(s, b);
        this.setClosable(true);
        this.setDefaultCloseOperation(0);
        this.getContentPane().setLayout(new BorderLayout());
    }
}
