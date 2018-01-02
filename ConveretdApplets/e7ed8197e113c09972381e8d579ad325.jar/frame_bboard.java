import java.awt.Event;
import java.awt.Component;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class frame_bboard extends Frame implements groupboard_consts
{
    bboard_gui gui;
    
    frame_bboard(final DataInputStream dataInputStream, final DataOutputStream dataOutputStream, final boolean b, final groupboard groupboard) {
        this.setTitle("Message Board");
        this.add("Center", this.gui = new bboard_gui(dataOutputStream, b, groupboard, this));
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.gui.parent.bbwin = null;
            this.gui.parent.bb = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
