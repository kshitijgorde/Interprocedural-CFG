// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import VT_6_1_0_11.aL;
import com.hw.client.util.a;

final class c implements Runnable
{
    private final n a;
    
    private c(final n a, final byte b) {
        this.a = a;
    }
    
    public final void run() {
        final aL e;
        final int rowForPath = (e = n.a(this.a).e()).getRowForPath(this.a.O().getSelectionPath());
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.SelectNextMessageAndPlay: currently selected: " + rowForPath + "/" + e.getRowCount());
        }
        if (rowForPath != -1 && rowForPath < e.getRowCount() - 1) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VboardDesktopPane.SelectNextMessageAndPlay: updating the buttons...");
            }
            n.a(this.a, true);
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VboardDesktopPane.SelectNextMessageAndPlay: selecting row# " + (rowForPath + 1));
            }
            e.setSelectionRow(rowForPath + 1);
            e.scrollRowToVisible(rowForPath + 1);
            return;
        }
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.SelectNextMessageAndPlay: no next message, stopping");
        }
        n.a(this.a, false);
    }
    
    c(final n n) {
        this(n, (byte)0);
    }
}
