// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Global;
import jagoclient.dialogs.GetFontSize;

class BoardGetFontSize extends GetFontSize
{
    GoFrame GF;
    
    public BoardGetFontSize(final GoFrame gf, final String s, final String s2, final String s3, final int n, final boolean b) {
        super(s, s2, s3, n, b);
        this.GF = gf;
    }
    
    public void doAction(final String s) {
        super.doAction(s);
        if (Global.resourceString("OK").equals(s)) {
            Global.createfonts();
            this.GF.updateall();
        }
    }
}
