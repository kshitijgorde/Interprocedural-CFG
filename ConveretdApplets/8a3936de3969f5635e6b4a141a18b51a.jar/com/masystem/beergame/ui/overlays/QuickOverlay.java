// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.overlays;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickOverlay extends MessageOverlay
{
    public QuickOverlay(final String s, final String s2) {
        super(s, s2, "OK");
    }
    
    @Override
    public final void onSetup() {
        super.onSetup();
        this.getRightButton().addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                QuickOverlay.this.close();
            }
        });
    }
    
    @Override
    protected final void onPerform() {
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {}
    }
    
    @Override
    protected final void onFinish() {
        this.close();
    }
}
