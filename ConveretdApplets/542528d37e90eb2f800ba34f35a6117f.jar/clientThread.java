// 
// Decompiled by Procyon v0.5.30
// 

public class clientThread extends Thread
{
    private eGraffiti server;
    
    public clientThread(final eGraffiti server) {
        this.server = server;
    }
    
    public void run() {
        while (true) {
            this.server.readi();
        }
    }
}
