// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Choice;

class PresetChoice extends Choice implements ItemListener, SessionListener
{
    NuApplet applet;
    int xpos;
    int ypos;
    ControlSession session;
    final String kInitialPreset = "Preset";
    
    protected PresetChoice(final NuApplet a) {
        this.applet = a;
        this.addItemListener(this);
    }
    
    public void itemStateChanged(final ItemEvent itemevent) {
        try {
            this.goPreset(itemevent);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    private void goPreset(final ItemEvent itemevent) throws Exception {
        final int index = this.getSelectedIndex();
        this.applet.println("Choice: " + itemevent.toString() + " index=" + index);
        if (index > 0) {
            try {
                final Preset p = this.session.getPreset(index - 1);
                this.applet.println("goPreset: " + p.name + " id=" + p.id);
                this.applet.sendMessage(1019, p);
            }
            catch (Exception e) {
                this.applet.report(e);
            }
        }
        else {
            this.applet.println("No preset selection made.");
        }
    }
    
    public void stateChanged(final int state, final int queuePos, final int queueLen) {
        this.setEnabled(this.session != null && this.session.controlsEnabled());
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
    }
    
    public void setSession(final ControlSession inSession) {
        this.session = inSession;
        this.removeAll();
        if (this.session == null) {
            this.setEnabled(false);
            return;
        }
        final int count = this.session.getPresetCount();
        this.add("Preset");
        for (int x = 0; x < count; ++x) {
            final Preset p = this.session.getPreset(x);
            this.add(p.name);
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension d = super.getPreferredSize();
        if (d.height < 10) {
            d.height = 16;
        }
        return d;
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
    }
}
