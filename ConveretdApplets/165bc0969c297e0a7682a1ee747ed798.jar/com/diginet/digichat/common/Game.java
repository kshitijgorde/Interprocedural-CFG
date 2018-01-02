// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Image;
import com.diginet.digichat.awt.m;

public class Game extends k implements m
{
    public int nWidth;
    public int nHeight;
    public String strDir;
    public String strDesc;
    public Image imgLogo;
    public Image imgIcon;
    
    public Game(final int n, final String s) {
        super(n, s);
        this.a(1610612736L);
        this.nWidth = 800;
        this.nHeight = 600;
        final String s2 = "";
        this.strDesc = s2;
        this.strDir = s2;
        final Image image = null;
        this.imgIcon = image;
        this.imgLogo = image;
    }
    
    public String getLogo() {
        return String.valueOf(String.valueOf("Games/").concat(String.valueOf(this.strDir))).concat(String.valueOf("/gameLogo.gif"));
    }
    
    public Object e(final String s) {
        if ("icon".equals(s)) {
            return this.imgIcon;
        }
        return super.e(s);
    }
    
    public String f(final String s) {
        return a5.a(com.esial.util.c.a("Double-click here to enter a private conversation with %1."), new String[] { this.x() });
    }
}
