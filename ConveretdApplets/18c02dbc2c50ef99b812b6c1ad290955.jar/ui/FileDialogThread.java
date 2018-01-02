// 
// Decompiled by Procyon v0.5.30
// 

package ui;

import main.AppletHandler;

public class FileDialogThread implements Runnable
{
    String remoteDir;
    boolean blOverwrite;
    AppletHandler applet;
    
    public FileDialogThread(final AppletHandler applet, final String remoteDir, final boolean blOverwrite) {
        this.applet = applet;
        this.remoteDir = remoteDir;
        this.blOverwrite = blOverwrite;
    }
    
    public void run() {
        new FileDialog(this.applet, this.remoteDir, this.blOverwrite);
    }
}
