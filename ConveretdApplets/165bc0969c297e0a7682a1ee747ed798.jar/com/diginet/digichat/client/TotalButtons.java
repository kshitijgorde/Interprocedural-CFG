// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.CheckButton;
import com.diginet.digichat.awt.bv;
import com.diginet.digichat.awt.r;
import java.awt.Image;
import com.diginet.digichat.awt.bk;
import java.awt.Canvas;

public class TotalButtons
{
    public static Canvas createButton(final i i, final String s, final String s2, final int n, final int n2, final int n3) {
        final Image a;
        final Image a2;
        if (i.cc.l() && (a = i.a(String.valueOf(String.valueOf(i.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_button_up.gif")), true)) != null && (a2 = i.a(String.valueOf(String.valueOf(i.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_button_dn.gif")), true)) != null) {
            return bk.a(a, a2, null);
        }
        final r r;
        (r = new r()).resize(n, n2, false);
        r.a(i.a(s2, false, n3));
        return r;
    }
    
    public static Canvas createButton(final i i, final String s, final String s2, final int n, final int n2) {
        return createButton(i, s, s2, n, 26, n2);
    }
    
    public static Canvas createCheck(final i i, final String s, final String s2, final int n, final int n2) {
        final Image a;
        final Image a2;
        if (i.cc.l() && (a = i.a(String.valueOf(String.valueOf(i.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_unchecked.gif")), true)) != null && (a2 = i.a(String.valueOf(String.valueOf(i.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_checked.gif")), true)) != null) {
            return new bv(a, a2);
        }
        final CheckButton checkButton;
        (checkButton = new CheckButton(26, n)).resize(26, n, false);
        checkButton.a(i.a(s2, false, n2));
        return checkButton;
    }
    
    public static Canvas createCheck(final i i, final String s, final String s2, final int n) {
        return createCheck(i, s, s2, 26, n);
    }
    
    public static boolean getChecked(final Canvas canvas) {
        return (canvas instanceof CheckButton) ? ((CheckButton)canvas).getChecked() : ((bv)canvas).a();
    }
    
    public static void setChecked(final Canvas canvas, final boolean checked) {
        if (canvas instanceof CheckButton) {
            ((CheckButton)canvas).setChecked(checked);
        }
        else {
            ((bv)canvas).a(checked);
        }
    }
}
