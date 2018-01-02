// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.io.IOException;
import java.awt.event.ActionEvent;

public class SaveAction extends TuneBookActionAbstract
{
    public SaveAction() {
        this.putValue("Name", "Save");
        this.putValue("ShortDescription", "Saves tunebook updates");
        this.putValue("MnemonicKey", new Integer(83));
    }
    
    public void actionPerformed(final ActionEvent e) {
        try {
            this.getTuneBook().save();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
