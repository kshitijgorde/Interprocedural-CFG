// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Container;

public class g
{
    public static void if(final Container container) {
        container.setLayout(new BoxLayout(container, 1));
    }
    
    public static void a(final Container container) {
        container.setLayout(new BoxLayout(container, 0));
    }
}
