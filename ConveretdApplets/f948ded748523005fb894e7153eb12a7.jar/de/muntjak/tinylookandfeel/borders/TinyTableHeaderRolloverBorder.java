// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;

public class TinyTableHeaderRolloverBorder extends TinyTableHeaderBorder
{
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.color1 == null) {
            this.color1 = Theme.tableHeaderRolloverColor[Theme.style].getColor();
            this.color2 = ColorRoutines.lighten(this.color1, 25);
        }
        graphics.setColor(this.color1);
        graphics.drawLine(n, n2 + n4 - 3, n + n3 - 1, n2 + n4 - 3);
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.setColor(this.color2);
        graphics.drawLine(n, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
    }
}
