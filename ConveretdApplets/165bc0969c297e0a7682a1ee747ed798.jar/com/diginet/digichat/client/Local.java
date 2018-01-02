// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Image;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.image.ImageObserver;
import com.diginet.digichat.common.j;

public class Local extends Location
{
    public Local(final h h, final int n, final String s) {
        super(h, n, s);
        this.a(2305843011898048512L);
    }
    
    public void checkBuddy(final Buddy buddy) {
        buddy.fKnown = true;
        j params = null;
        if (buddy.w() != -999) {
            params = (bd)super.hUser.ab.e(buddy.w());
        }
        else {
            for (int i = 0; i < super.hUser.ab.b(); ++i) {
                final bd bd;
                if ((bd = (bd)super.hUser.ab.d(i)).x().equals(buddy.x())) {
                    params = bd;
                    break;
                }
            }
        }
        final boolean fOnline = params != null;
        buddy.fOnline = fOnline;
        if (fOnline) {
            buddy.setParams(params);
        }
    }
    
    public void initBuddy(final Buddy buddy) {
        this.checkBuddy(buddy);
    }
    
    public void getProf(final BuddyProf buddyProf) {
        final Image loadImage;
        Image image;
        if (!super.hUser.cc.getBrand() || (loadImage = super.hUser.loadImage(String.valueOf(super.hUser.cc.f()).concat(String.valueOf("brandLogo.gif")), buddyProf, 300)) == null || (image = super.hUser.loadImage("brandLogo.gif", buddyProf, 300)) == null) {
            image = super.hUser.imgShield;
        }
        final Buddy buddy = buddyProf.getBuddy();
        final bo bo;
        buddyProf.setProf(buddy.imgIcon, null, image, null, null, ((bo = (bo)super.hUser.ac.e(buddy.b)) == null) ? null : a5.a(com.esial.util.c.a("Now in %1."), new String[] { bo.x() }));
    }
    
    public void callPriv(final Buddy buddy, final bf bf) {
        super.hUser.a(bf, buddy);
    }
    
    public void callInv(final Buddy buddy) {
    }
    
    public void callNote(final Buddy buddy) {
    }
    
    public void closeMess(final Buddy buddy) {
    }
}
