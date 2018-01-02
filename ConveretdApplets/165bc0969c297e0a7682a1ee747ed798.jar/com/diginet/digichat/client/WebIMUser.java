// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Image;
import com.diginet.digichat.awt.m;

public class WebIMUser implements m, WebIMIcons
{
    public bd bdUser;
    private Image imgAudio;
    private Image imgVideo;
    
    public void setIcons(final Image imgAudio, final Image imgVideo) {
        this.imgAudio = imgAudio;
        this.imgVideo = imgVideo;
    }
    
    public Object e(final String s) {
        if ("audio".equals(s)) {
            return this.imgAudio;
        }
        if ("video".equals(s)) {
            return this.imgVideo;
        }
        return this.bdUser.e(s);
    }
    
    public String f(final String s) {
        return a5.a(c.a("Double-click here to enter to conversation with %1 through 1:1."), new String[] { this.bdUser.x() });
    }
    
    public int a(final m m, final String s) {
        return this.bdUser.a(m, s);
    }
    
    public boolean a(final String s, final Object o) {
        return true;
    }
    
    public WebIMUser(final bd bdUser) {
        this.bdUser = bdUser;
        this.imgAudio = null;
        this.imgVideo = null;
    }
}
