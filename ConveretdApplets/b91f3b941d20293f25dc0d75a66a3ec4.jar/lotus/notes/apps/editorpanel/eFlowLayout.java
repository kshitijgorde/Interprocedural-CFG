// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;

public class eFlowLayout extends FlowLayout
{
    int vertGap;
    
    public eFlowLayout(final int n, final int n2, final int vertGap) {
        super(n, n2, vertGap);
        this.vertGap = 0;
        this.vertGap = vertGap;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Dimension preferredLayoutSize = super.preferredLayoutSize(container);
        if (container.size().width != 0 && preferredLayoutSize.width >= container.size().width) {
            System.out.println("setting preferred height * 2");
            final Dimension dimension = preferredLayoutSize;
            dimension.height *= 2;
            final Dimension dimension2 = preferredLayoutSize;
            dimension2.height -= this.vertGap * 2;
        }
        return preferredLayoutSize;
    }
}
