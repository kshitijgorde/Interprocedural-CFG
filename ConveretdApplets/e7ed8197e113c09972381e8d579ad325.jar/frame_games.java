import java.awt.Event;
import java.awt.Component;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class frame_games extends Frame implements groupboard_consts
{
    games_gui gui;
    
    frame_games(final DataInputStream dataInputStream, final DataOutputStream dataOutputStream, final boolean b, final groupboard groupboard, final games_gui gui) {
        this.setTitle("Games");
        if (null != gui) {
            this.gui = gui;
        }
        else {
            this.gui = new games_gui(b, groupboard, this);
        }
        this.add("North", this.gui);
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.gui.parent.gameswin = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
