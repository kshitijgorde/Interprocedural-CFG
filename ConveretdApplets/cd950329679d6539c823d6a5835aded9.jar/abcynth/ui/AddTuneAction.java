// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.awt.event.ActionEvent;

public class AddTuneAction extends TuneBookActionAbstract
{
    public AddTuneAction(final String name, final String description, final int shortcut) {
        this.putValue("Name", name);
        this.putValue("ShortDescription", description);
        this.putValue("MnemonicKey", new Integer(shortcut));
    }
    
    public void actionPerformed(final ActionEvent e) {
        final int hi = this.getTuneBook().getHighestReferenceNumber() + 1;
        this.getTuneBook().putTune("X:" + hi + "\n" + "T:\n" + "K:\n");
    }
}
