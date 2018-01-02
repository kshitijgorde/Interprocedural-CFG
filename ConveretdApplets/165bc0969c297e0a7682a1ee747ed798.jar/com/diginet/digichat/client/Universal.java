// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.j;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.e;

public class Universal extends Location
{
    public int nName;
    public int nBack;
    public int nIcon;
    public String strDir;
    public String strArch;
    public String strFile;
    public String strSite;
    public String strLogin;
    public e ePass;
    
    public Universal(final h h, final int n, final String s) {
        super(h, n, s);
    }
    
    public void initBuddy(final Buddy buddy) {
        final v v = new v(67371266, 1);
        v.a(0, 0, this.w());
        v.a(0, 0, buddy.x());
        v.a(0, 1, buddy.strLogin);
        v.a(0, 0, buddy.ePass);
        super.hUser.an(v);
    }
    
    private void checkBuddy(final Buddy buddy, final boolean b) {
        final v v = new v(67371267, 1);
        v.a(0, 62, b);
        v.a(0, 0, this.w());
        v.a(0, 0, buddy.x());
        super.hUser.an(v);
    }
    
    public void checkBuddy(final Buddy buddy) {
        this.checkBuddy(buddy, true);
    }
    
    public void getProf(final BuddyProf bdProf) {
        final Buddy buddy;
        (buddy = bdProf.getBuddy()).bdProf = bdProf;
        this.checkBuddy(buddy, false);
    }
    
    public void callPriv(final Buddy buddy, final bf bf) {
        bh bhPriv;
        if ((bhPriv = buddy.bhPriv) == null) {
            bhPriv = (buddy.bhPriv = super.hUser.createPriv(buddy, false));
        }
        if (bf != null) {
            bhPriv.a(bf);
        }
        bhPriv.setVisible(true);
    }
    
    public void callInv(final Buddy buddy) {
        bh bhInv;
        if ((bhInv = buddy.bhInv) == null) {
            bhInv = (buddy.bhInv = super.hUser.createPriv(buddy, true));
        }
        bhInv.setVisible(true);
    }
    
    public void callNote(final Buddy buddy) {
        super.hUser.sendMess(null, buddy, 0L, true);
    }
    
    private void closeBuddy(final Buddy buddy, final boolean b) {
        final v v = new v(67371271, 1);
        v.a(0, 62, b);
        v.a(0, 0, this.w());
        v.a(0, 0, buddy.x());
        super.hUser.an(v);
    }
    
    public void closeMess(final Buddy buddy) {
        this.closeBuddy(buddy, true);
    }
    
    public void closeBuddy(final Buddy buddy) {
        super.closeBuddy(buddy);
        this.closeBuddy(buddy, false);
    }
    
    public String getLogo() {
        return String.valueOf(String.valueOf("Locations/").concat(String.valueOf(this.strDir))).concat(String.valueOf("/locationLogo.gif"));
    }
}
