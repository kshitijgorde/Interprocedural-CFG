// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import VT_6_1_0_11.af;
import VT_6_1_0_11.an;
import VT_6_1_0_11.be;
import com.hw.client.util.a;
import java.awt.event.KeyListener;
import java.util.Vector;
import VT_6_1_0_11.bG;

public class e
{
    private bG a;
    private g b;
    private k c;
    private int d;
    private Vector e;
    
    public e(final g b) {
        this.b = b;
        this.d = -1;
        this.e = new Vector();
        this.c = new k(this);
        this.a = new bG();
        this.c.a().a(this.a);
    }
    
    public final k a() {
        return this.c;
    }
    
    public final void a(final KeyListener keyListener) {
        this.c.addKeyListener(keyListener);
    }
    
    public final boolean b() {
        return this.b.a("manage_floor");
    }
    
    public final void a(final String s) {
        com.hw.client.util.a.d("MemberPanel.giveMicrophone: Give microphone to " + s);
        this.b.a(new be((byte)4, s));
    }
    
    public final void a(final an an) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("MemberPanel.processConfEvent streamid=" + an.d());
        }
        if (this.d == -1 || an.d() <= this.d) {
            this.a((af)an);
            return;
        }
        this.e.addElement(an);
    }
    
    public final void a(final int d) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("MemberPanel.setStreamid: streamid=" + d);
        }
        this.d = d;
        while (!this.e.isEmpty() && this.e.elementAt(0).d() <= d) {
            this.a(this.e.elementAt(0));
            this.e.removeElementAt(0);
        }
    }
    
    private void a(final af af) {
        this.a.a(af);
        this.c.a().a(this.a);
    }
    
    public e() {
    }
    
    public static void a(final byte[] array, int n, final byte[] array2, int n2, final byte[] array3, int n3) {
        array3[n3++] = (byte)((array[n] & 0xF) << 2 | (array[++n] & 0x3) << 6 | (array[n] & 0xC0) >> 6);
        array3[n3++] = (byte)((array[n] & 0x3C) >> 2 | (array[++n] & 0x78) << 1);
        array3[n3++] = (byte)((array[n] & 0x7) << 3 | (array[n] & 0x80) >> 7 | (array[++n] & 0xC) << 4 | (array[n] & 0xC0) >> 5);
        array3[n3++] = (byte)((array[n] & 0x3) << 4 | (array[n] & 0x30) >> 4 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 4);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x1E) << 3);
        array3[n3++] = (byte)((array[n] & 0x1) << 4 | (array[n] & 0xE0) >> 5 | (array[++n] & 0x60) | (array[n] & 0x80) >> 4 | (array[++n] & 0x80));
        array3[n3++] = (byte)((array[--n] & 0x1F) | (array[++n] & 0x70) << 1);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x4) << 5 | (array[n] & 0xE0) >> 1);
        array3[n3++] = (byte)((array[n] & 0x3) << 3 | (array[n] & 0x18) >> 3 | (array[++n] & 0x80) >> 5 | (array[n] & 0x70) << 1);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x1E) << 3);
        array3[n3++] = (byte)((array[n] & 0x1) << 4 | (array[n] & 0xE0) >> 5 | (array[++n] & 0x60) | (array[n] & 0x80) >> 4 | (array[++n] & 0x80));
        array3[n3++] = (byte)((array[--n] & 0x1F) | (array[++n] & 0x70) << 1);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x4) << 5 | (array[n] & 0xE0) >> 1);
        array3[n3++] = (byte)((array[n] & 0x3) << 3 | (array[n] & 0x18) >> 3 | (array[++n] & 0x70) << 1 | (array[n] & 0x80) >> 5);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x1E) << 3);
        array3[n3++] = (byte)((array[n] & 0x1) << 4 | (array[n] & 0xE0) >> 5 | (array[++n] & 0x60) | (array[n] & 0x80) >> 4 | (array[++n] & 0x80));
        array3[n3++] = (byte)((array[--n] & 0x1F) | (array[++n] & 0x70) << 1);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x4) << 5 | (array[n] & 0xE0) >> 1);
        array3[n3++] = (byte)((array[n] & 0x3) << 3 | (array[n] & 0x18) >> 3 | (array[++n] & 0x70) << 1 | (array[n] & 0x80) >> 5);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x1E) << 3);
        array3[n3++] = (byte)((array[n] & 0x1) << 4 | (array[n] & 0xE0) >> 5 | (array[++n] & 0x60) | (array[n] & 0x80) >> 4 | (array[++n] & 0x80));
        array3[n3++] = (byte)((array[--n] & 0x1F) | (array[++n] & 0x70) << 1);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array[++n] & 0x4) << 5 | (array[n] & 0xE0) >> 1);
        array3[n3++] = (byte)((array[n] & 0x3) << 3 | (array[n] & 0x18) >> 3 | (array[++n] & 0x70) << 1 | (array[n] & 0x80) >> 5);
        array3[n3++] = (byte)((array[n] & 0x1) << 5 | (array[n] & 0xE) >> 1 | (array[++n] & 0x18) << 3 | (array[n] & 0xC0) >> 3);
        array3[n3++] = (byte)((array[n] & 0x7) << 1 | (array[n] & 0x20) >> 5 | (array2[n2] & 0x3) << 6 | (array2[++n2] & 0xC0) >> 2);
        array3[n3++] = (byte)((array2[--n2] & 0xC) >> 2 | (array2[++n2] & 0x3F) << 2);
        array3[n3++] = (byte)((array2[++n2] & 0x1) << 7 | (array2[n2] & 0xF8) >> 3 | (array2[++n2] & 0xC0) >> 1);
        array3[n3++] = (byte)((array2[--n2] & 0x6) >> 1 | (array2[++n2] & 0x3C) | (array2[++n2] & 0xC0));
        array3[n3++] = (byte)((array2[--n2] & 0x3) | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1);
        array3[n3++] = (byte)((array2[++n2] & 0xFE) >> 1 | (array2[++n2] & 0x80));
        array3[n3++] = (byte)((array2[--n2] & 0x1) | (array2[++n2] & 0xF) << 4 | (array2[n2] & 0x60) >> 4 | (array2[++n2] & 0x80) >> 4);
        array3[n3++] = (byte)((array2[--n2] & 0x10) >> 4 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0x1) << 7 | (array2[n2] & 0x1C) << 1 | (array2[n2] & 0xE0) >> 5 | (array2[++n2] & 0x80) >> 1);
        array3[n3++] = (byte)((array2[--n2] & 0x2) >> 1 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0xFE) >> 1 | (array2[++n2] & 0x80));
        array3[n3++] = (byte)((array2[--n2] & 0x1) | (array2[++n2] & 0xF) << 4 | (array2[n2] & 0x60) >> 4 | (array2[++n2] & 0x80) >> 4);
        array3[n3++] = (byte)((array2[--n2] & 0x10) >> 4 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0x1) << 7 | (array2[n2] & 0x1C) << 1 | (array2[n2] & 0xE0) >> 5 | (array2[++n2] & 0x80) >> 1);
        array3[n3++] = (byte)((array2[--n2] & 0x2) >> 1 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0xFE) >> 1 | (array2[++n2] & 0x80));
        array3[n3++] = (byte)((array2[--n2] & 0x1) | (array2[++n2] & 0xF) << 4 | (array2[n2] & 0x60) >> 4 | (array2[++n2] & 0x80) >> 4);
        array3[n3++] = (byte)((array2[--n2] & 0x10) >> 4 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0x1) << 7 | (array2[n2] & 0x1C) << 1 | (array2[n2] & 0xE0) >> 5 | (array2[++n2] & 0x80) >> 1);
        array3[n3++] = (byte)((array2[--n2] & 0x2) >> 1 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0xFE) >> 1 | (array2[++n2] & 0x80));
        array3[n3++] = (byte)((array2[--n2] & 0x1) | (array2[++n2] & 0xF) << 4 | (array2[n2] & 0x60) >> 4 | (array2[++n2] & 0x80) >> 4);
        array3[n3++] = (byte)((array2[--n2] & 0x10) >> 4 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3++] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
        array3[n3++] = (byte)((array2[++n2] & 0x1) << 7 | (array2[n2] & 0x1C) << 1 | (array2[n2] & 0xE0) >> 5 | (array2[++n2] & 0x80) >> 1);
        array3[n3++] = (byte)((array2[--n2] & 0x2) >> 1 | (array2[++n2] & 0xE) << 3 | (array2[n2] & 0x70) >> 3 | (array2[++n2] & 0x40) << 1);
        array3[n3] = (byte)((array2[--n2] & 0x1) << 1 | (array2[++n2] & 0x7) << 5 | (array2[n2] & 0x38) >> 1 | (array2[n2] & 0x80) >> 7);
    }
}
