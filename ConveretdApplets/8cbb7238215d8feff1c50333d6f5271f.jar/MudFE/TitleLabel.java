// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Font;
import java.awt.Label;

class TitleLabel extends Label
{
    TitleLabel(final String s) {
        super(s);
        this.setFont(new Font("Dialog", 1, 14));
    }
    
    TitleLabel(final String s, final int f) {
        super(s);
        this.setFont(new Font("Dialog", 1, f));
    }
}
