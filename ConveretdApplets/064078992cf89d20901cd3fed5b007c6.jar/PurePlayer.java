import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class PurePlayer extends ak
{
    public boolean loadpano(final String s, final boolean b) {
        return this.a(s, null, b);
    }
    
    public static void main(final String[] array) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        JOptionPane.showMessageDialog(null, "This software can not be run as typical Java applications.\nThis software can only be run through a web page.\nPlease consult the official documentation found on our website for more information:\nhttp://www.immervision.com", "ImmerVision PURE Player for Java v1.08", 0);
    }
}
