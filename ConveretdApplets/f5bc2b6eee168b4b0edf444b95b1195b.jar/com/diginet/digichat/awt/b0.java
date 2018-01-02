// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import com.diginet.digichat.util.p;
import com.diginet.ui.b1;

public class b0 extends b1 implements p
{
    private String a;
    private String b;
    
    public final void a(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public final String a(final Object o) {
        if (this.isEnabled()) {
            return this.a;
        }
        return this.b;
    }
    
    public b0(final String s, final Font font) {
        this(s, font, null, Color.gray);
    }
    
    public b0(final String s, final Font font, final Image image, final Color color) {
        super(s, font, image, color);
        this.a = null;
        this.b = null;
    }
}
