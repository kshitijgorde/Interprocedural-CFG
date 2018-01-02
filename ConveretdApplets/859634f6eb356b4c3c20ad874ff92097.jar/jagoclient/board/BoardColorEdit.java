// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Global;
import java.awt.Color;
import java.awt.Frame;
import jagoclient.dialogs.ColorEdit;

class BoardColorEdit extends ColorEdit
{
    GoFrame GF;
    
    public BoardColorEdit(final GoFrame gf, final String s, final int n, final int n2, final int n3) {
        super(gf, s, n, n2, n3, true);
        this.GF = gf;
        this.show();
    }
    
    public BoardColorEdit(final GoFrame gf, final String s, final Color color) {
        super(gf, s, color.getRed(), color.getGreen(), color.getBlue(), true);
        this.GF = gf;
        this.show();
    }
    
    public void doAction(final String s) {
        super.doAction(s);
        if (Global.resourceString("OK").equals(s)) {
            this.GF.updateall();
        }
    }
}
