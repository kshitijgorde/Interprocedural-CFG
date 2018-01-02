import java.net.URL;
import javax.swing.ImageIcon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Toolbox
{
    static /* synthetic */ Class class$BinaryOperators;
    
    protected static ImageIcon createImageIcon(final String path) {
        final URL imgURL = ((Toolbox.class$BinaryOperators == null) ? (Toolbox.class$BinaryOperators = class$("BinaryOperators")) : Toolbox.class$BinaryOperators).getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
