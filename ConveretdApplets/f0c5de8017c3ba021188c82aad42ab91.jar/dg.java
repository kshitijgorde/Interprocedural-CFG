// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.livehtml;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;

public class dg implements e
{
    private final /* synthetic */ String a;
    private final /* synthetic */ LHTML b;
    
    public dg(final LHTML b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    public void produce() {
        this.b.b(this.a);
    }
}
