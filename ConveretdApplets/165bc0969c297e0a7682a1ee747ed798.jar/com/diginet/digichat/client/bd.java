// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Image;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.j;

public class bd extends j implements m, WebIMIcons
{
    public bp a;
    public bp bpStar;
    public Image imgState;
    public Image imgFlag;
    public Image imgAudio;
    public Image imgVideo;
    
    public Object e(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.b);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.a;
        }
        if (this.bpStar != null && "star".equals(s)) {
            return this.bpStar.a;
        }
        if ("flag".equals(s)) {
            return this.imgFlag;
        }
        if ("audio".equals(s)) {
            return this.imgAudio;
        }
        if ("video".equals(s)) {
            return this.imgVideo;
        }
        if ("state".equals(s)) {
            return this.imgState;
        }
        return super.e(s);
    }
    
    public String f(final String s) {
        return a5.a(com.esial.util.c.a("Double-click here to enter a private conversation with %1."), new String[] { this.x() });
    }
    
    public void setIcons(final Image imgAudio, final Image imgVideo) {
        this.imgAudio = imgAudio;
        this.imgVideo = imgVideo;
    }
    
    public bd(final int n, final String s) {
        super(n, s);
        this.bpStar = null;
        final Image image = null;
        this.imgVideo = image;
        this.imgAudio = image;
        this.imgFlag = image;
        this.imgState = image;
    }
}
