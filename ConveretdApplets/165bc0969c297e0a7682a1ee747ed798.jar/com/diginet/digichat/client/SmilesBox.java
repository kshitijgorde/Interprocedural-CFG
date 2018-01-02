// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.esial.util.c;
import com.diginet.digichat.util.ImagesListener;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.common.b1;
import com.diginet.digichat.awt.cq;

public class SmilesBox extends InsertBox
{
    private i iUser;
    private cq b1Smiles;
    private TextPanel txpPanel;
    
    public SmilesBox(final h iUser, final TextPanel txpPanel) {
        super(iUser, txpPanel);
        this.b1Smiles = new cq(true);
        boolean b = false;
        for (int i = 0; i < iUser.smiles.b(); ++i) {
            final b1 b2 = (b1)iUser.smiles.d(i);
            if (!b2.i(36) || iUser.i(92)) {
                b = (iUser.checkSmile(b2) || b);
                this.b1Smiles.a(b2);
            }
        }
        if (b) {
            iUser.smilesLoader.addListener(this.b1Smiles);
        }
        this.txpPanel = txpPanel;
        final String a = c.a("Select an emoticon");
        final cq b1Smiles = this.b1Smiles;
        this.iUser = iUser;
        this.addSelector(a, b1Smiles, iUser.fSmiles);
    }
    
    protected void setClose(final boolean fSmiles) {
        this.iUser.fSmiles = fSmiles;
    }
    
    protected boolean insert() {
        try {
            this.txpPanel.appendElem(((b1)this.b1Smiles.b()).c);
        }
        catch (Exception ex) {
            return false;
        }
        return this.txpPanel.incSmiles() || this.iUser.fSmiles;
    }
}
