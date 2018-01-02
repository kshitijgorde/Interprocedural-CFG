// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Frame;
import com.diginet.digichat.util.ScaleIcon;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.common.e;
import java.awt.Image;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.j;

public class Buddy extends j implements m
{
    public boolean fKnown;
    public boolean fOnline;
    public boolean fBuddy;
    public boolean fLocation;
    public boolean[] fLoc;
    public String strKey;
    public String strLoc;
    public String strLogin;
    public Image imgIcon;
    public e ePass;
    public bh bhPriv;
    public bh bhInv;
    public BuddyProf bdProf;
    public Location location;
    
    private Image getIcon(final int n) {
        final bp icon;
        return ((icon = this.location.hUser.getIcon(n)) == null) ? null : icon.a;
    }
    
    public Buddy(final int n, final String strKey, final Location location, final String strLogin, final e ePass, final boolean fBuddy, final boolean fLocation, final boolean[] fLoc) {
        super(n, strKey);
        this.strLoc = null;
        this.strKey = strKey;
        final int index;
        if ((index = strKey.indexOf(10)) >= 0) {
            this.d(strKey.substring(0, index));
            this.strLoc = strKey.substring(index + 1);
        }
        this.location = location;
        this.strLogin = strLogin;
        this.ePass = ePass;
        this.fBuddy = fBuddy;
        this.fLocation = fLocation;
        this.fLoc = fLoc;
        final bh bh = null;
        this.bhInv = bh;
        this.bhPriv = bh;
        this.bdProf = null;
        this.imgIcon = null;
        super.clrName = null;
        super.clrBack = null;
    }
    
    public int locId() {
        return (this.location == null) ? -1 : this.location.w();
    }
    
    public String getName() {
        final StringBuffer sb = new StringBuffer(this.x());
        if (this.fLoc[0]) {
            sb.append(" (");
            sb.append((this.strLoc == null) ? DigiChatAppletAbstract.OEM_DigiChat : this.strLoc);
            sb.append(')');
        }
        return sb.toString();
    }
    
    public void setParams(final j j) {
        this.h(j.w());
        super.b = j.b;
        super.c = j.c;
        super.clrName = j.clrName;
        super.clrBack = j.clrBack;
        this.imgIcon = this.getIcon(j.a);
        this.aaa(j.y(), j.yyy());
    }
    
    public static Image getImage(final v v) {
        Image image = null;
        final byte[] d;
        if ((d = v.d()) != null) {
            try {
                final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(d));
                final char char1 = dataInputStream.readChar();
                final char char2 = dataInputStream.readChar();
                final int[] array = new int[char1 * char2];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = dataInputStream.readInt();
                }
                dataInputStream.close();
                image = DigiChatAppletAbstract.applet.createImage(new MemoryImageSource(char1, char2, array, 0, char1));
            }
            catch (Exception ex) {}
        }
        return image;
    }
    
    private boolean isDisable(final int n, final boolean b) {
        return b || !this.location.i(n);
    }
    
    private void disposeBuddy(final boolean b) {
        if (this.bdProf != null && this.isDisable(30, b)) {
            this.bdProf.dispose();
        }
        if (this.bhPriv != null && this.isDisable(28, b)) {
            this.bhPriv.dispose();
        }
        if (this.bhInv != null && this.isDisable(26, b)) {
            this.bhInv.dispose();
        }
    }
    
    public void setParams(final v v) {
        final boolean e = v.e(0, 63);
        this.fKnown = e;
        if (e && (this.fOnline = v.e(0, 62))) {
            final Universal universal = (Universal)this.location;
            int n;
            super.clrName = (((n = v.a(0, 1)) == 0 && (n = universal.nName) == 0) ? null : new Color(n & 0xFFFFFF));
            int n2;
            super.clrBack = (((n2 = v.a(0, 2)) == 0 && (n2 = universal.nBack) == 0) ? null : new Color(n2 & 0xFFFFFF));
            if ((this.imgIcon = ScaleIcon.scale(getImage(v))) == null) {
                this.imgIcon = this.getIcon(universal.nIcon);
            }
        }
        else {
            this.imgIcon = null;
            final Color color = null;
            super.clrBack = color;
            super.clrName = color;
            this.disposeBuddy(!this.fKnown);
            if (!this.fKnown) {
                this.fOnline = false;
                if (v.e(0, 62)) {
                    this.location = null;
                }
            }
        }
    }
    
    public void initBuddy() {
        this.location.initBuddy(this);
    }
    
    public void checkBuddy() {
        this.location.checkBuddy(this);
    }
    
    public void callProf(final Frame frame, final i i) {
        if (this.bdProf == null) {
            new BuddyProf(frame, i, this);
        }
        else if (this.bdProf.isVisible()) {
            this.bdProf.toFront();
        }
    }
    
    public void callPriv(final bf bf) {
        this.location.callPriv(this, bf);
    }
    
    public void closeMess() {
        this.location.closeMess(this);
    }
    
    public void closeBuddy() {
        this.disposeBuddy(true);
        if (this.location != null && this.location.i(61)) {
            this.location.closeBuddy(this);
        }
    }
    
    public boolean isCheckable() {
        return this.location != null && this.location.i(61) && this.location.i(16);
    }
    
    private boolean isEq(final Object o, final Object o2) {
        return (o == null) ? (o2 == null) : o.equals(o2);
    }
    
    public boolean isEq(final Buddy buddy) {
        return this.isEq(buddy.strLogin, this.strLogin) && this.isEq(buddy.ePass, this.ePass);
    }
    
    public boolean notEq(final Buddy buddy) {
        return buddy.fBuddy != this.fBuddy || buddy.fLocation != this.fLocation || buddy.location != this.location || !this.isEq(buddy);
    }
    
    public Object e(final String s) {
        if ("icon".equals(s)) {
            return this.imgIcon;
        }
        if ("name".equals(s)) {
            return this.getName();
        }
        return super.e(s);
    }
}
