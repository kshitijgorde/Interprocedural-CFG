import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import a.a.a.a.l;

// 
// Decompiled by Procyon v0.5.30
// 

public class PurePlayerPro extends l
{
    public static void main(final String[] array) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        JOptionPane.showMessageDialog(null, "This software can not be run as typical Java applications.\nThis software can only be run through a web page.\nPlease consult the official documentation found on our website for more information:\nhttp://www.immervision.com", "ImmerVision PURE Player PRO for Java 1.14.1.4", 0);
    }
}
