import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class ResourceCheck extends Thread
{
    mapWindowMaker mwmWorld;
    mapWindowMaker mwmUSA;
    fileLoader helpFile;
    TextField statusField;
    Thread thisThread;
    boolean ready;
    
    ResourceCheck(final mapWindowMaker mwmWorld, final mapWindowMaker mwmUSA, final fileLoader helpFile, final TextField statusField) {
        this.mwmWorld = mwmWorld;
        this.mwmUSA = mwmUSA;
        this.helpFile = helpFile;
        this.statusField = statusField;
    }
    
    public void start() {
        if (this.thisThread == null) {
            (this.thisThread = new Thread(this)).start();
        }
    }
    
    public void run() {
        MapWindow retrieveMapWindow = null;
        MapWindow retrieveMapWindow2 = null;
        String retrieveFileContent = null;
        this.ready = false;
        this.statusField.setText("Preparing pop-up windows...");
        while (this.thisThread != null) {
            if (retrieveMapWindow == null && this.mwmWorld != null) {
                retrieveMapWindow = this.mwmWorld.retrieveMapWindow();
            }
            else if (retrieveMapWindow2 == null && this.mwmUSA != null) {
                retrieveMapWindow2 = this.mwmUSA.retrieveMapWindow();
            }
            else {
                if (retrieveFileContent != null || this.helpFile == null) {
                    if (this.helpFile != null) {
                        this.helpFile.stop();
                    }
                    if (this.mwmWorld != null) {
                        this.mwmWorld.stop();
                    }
                    if (this.mwmUSA != null) {
                        this.mwmUSA.stop();
                    }
                    this.statusField.setText("Ready.");
                    this.ready = true;
                    return;
                }
                retrieveFileContent = this.helpFile.retrieveFileContent();
            }
        }
    }
    
    public boolean areReady() {
        return this.ready;
    }
}
