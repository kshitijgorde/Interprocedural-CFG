import I.I;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class FI implements ActionListener
{
    private final ztmPlayer getActionCommand;
    
    FI(final ztmPlayer getActionCommand) {
        this.getActionCommand = getActionCommand;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        switch (Integer.parseInt(actionEvent.getActionCommand())) {
            case 1: {
                this.getActionCommand.toggle_playback();
                break;
            }
            case 2: {
                this.getActionCommand.stop_playback();
                break;
            }
            case 3: {
                this.getActionCommand.toggle_mute();
                break;
            }
            case 4: {
                this.getActionCommand.toggle_fscreen();
                break;
            }
            case 9: {
                this.getActionCommand.getAppletContext().showDocument(ztmPlayer.I, I.I(462));
                break;
            }
        }
    }
}
