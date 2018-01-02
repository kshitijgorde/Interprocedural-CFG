// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JFrame;

class WaitDialog extends JFrame
{
    private static final long serialVersionUID = 8198994292335370163L;
    private JLabel jLabel1;
    private JLabel jLabel2;
    Rectangle bounds;
    private static WaitDialog dlg;
    
    static {
        WaitDialog.dlg = new WaitDialog();
    }
    
    private WaitDialog() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(400, 150));
        this.setResizable(false);
        this.setTitle("Please wait");
        this.bounds = this.getGraphicsConfiguration().getBounds();
        this.jLabel1.setBounds(new Rectangle(15, 20, 65, 85));
        this.jLabel2.setText("Please wait, while the texture is loaded...");
        this.jLabel2.setForeground(SystemColor.textText);
        this.jLabel2.setBounds(new Rectangle(90, 30, 300, 65));
        this.jLabel2.setFont(new Font("Dialog", 1, 13));
        this.getContentPane().add(this.jLabel2, null);
        this.getContentPane().add(this.jLabel1, null);
    }
    
    public static void showDialog() {
        WaitDialog.dlg.setLocation((int)WaitDialog.dlg.bounds.getWidth() / 2 - 200, (int)WaitDialog.dlg.bounds.getHeight() / 2 - 75);
        WaitDialog.dlg.setVisible(true);
        WaitDialog.dlg.paint(WaitDialog.dlg.getGraphics());
    }
    
    public static void hideDialog() {
        WaitDialog.dlg.setVisible(false);
    }
}
