// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I8;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class $J8
{
    public static void $K8(final Window window) {
        final Toolkit toolkit = window.getToolkit();
        if (toolkit != null) {
            final Dimension screenSize = toolkit.getScreenSize();
            if (screenSize != null) {
                final Dimension size = window.getSize();
                window.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            }
        }
    }
}
