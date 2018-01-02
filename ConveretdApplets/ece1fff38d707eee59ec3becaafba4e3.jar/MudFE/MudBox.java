// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.io.IOException;

public class MudBox extends CommHandler
{
    private ScrolledDisplay display;
    private static final boolean debug = false;
    private static final boolean partial_debug = false;
    Decoder decoder;
    private boolean loggedin;
    
    public void sendChar(final char s) {
        String se;
        if (s == '\r') {
            se = "\r\n";
        }
        else {
            se = "" + s;
        }
        if (super.o == null) {
            this.display.addString("Not yet connected.\n");
        }
        else {
            try {
                super.o.mywriteUTF(se);
                super.o.send();
            }
            catch (IOException e) {
                super.theframe.noconnect();
                System.err.println("In Mudbox, error writing to socket.");
                super.o = null;
            }
        }
    }
    
    public void received(final String s) {
        System.out.println("Received string called from MudBox, shouldn't be.");
    }
    
    public void received(final byte[] st) {
        this.decoder.addBytes(st);
    }
    
    public MudBox(final MudFrame t) {
        this.loggedin = false;
        super.theframe = t;
        this.display = super.theframe.defaultDisplay;
        this.decoder = new Decoder(this.display, super.theframe);
    }
    
    public boolean login(final String u1, final String p1, final String u2, final String p2) {
        try {
            super.o.mywriteUTF("Login");
            super.o.send();
            super.o.mywriteUTF("Synchronise");
            super.o.send();
            this.showText("Attempting to Synchronise.");
            for (String msg = ""; msg.indexOf("Synchronised") == -1; msg += super.qI.myreadUTF()) {
                super.qI.receive();
            }
            this.showText("Synchronised, attempting to login to the mud.");
            this.display.addString("Sending user data.");
            super.o.mywriteUTF(u1);
            super.o.send();
            super.qI.receive();
            String msg = super.qI.myreadUTF();
            if (msg.indexOf("pass:") == -1) {
                this.showText("\nUnexpected input (" + msg + "), aborting.");
                return false;
            }
            this.display.addString(".");
            super.o.mywriteUTF(p1);
            super.o.send();
            super.qI.receive();
            msg = super.qI.myreadUTF();
            if (msg.indexOf("muduser:") == -1) {
                this.showText("\nUnexpected input, aborting.");
                return false;
            }
            this.display.addString(".");
            super.o.mywriteUTF(u2);
            super.o.send();
            super.qI.receive();
            msg = super.qI.myreadUTF();
            if (msg.indexOf("mudpass:") == -1) {
                this.showText("\nUnexpected input, aborting.");
                return false;
            }
            this.display.addString(".");
            super.o.mywriteUTF(p2);
            super.o.send();
            this.display.addString("\n");
            do {
                super.qI.receive();
                msg = super.qI.myreadUTF();
                this.showText(msg);
            } while (msg.indexOf("GO!") == -1 && msg.indexOf("STOP!") == -1);
            if (msg.indexOf("GO!") != -1) {
                return this.loggedin = true;
            }
            super.qI.receive();
            final String errno = super.qI.myreadUTF();
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void sendString(final String s) {
        if (super.o == null) {
            this.display.addString("Not yet connected.\n");
        }
        else {
            try {
                super.o.mywriteUTF(s);
                super.o.send();
            }
            catch (IOException e) {
                super.o = null;
            }
        }
    }
    
    public void go() {
        if (super.exec == null) {
            super.exec = new Thread(this, "Communication Thread: MudBox : " + CommHandler.thread_no++);
        }
        if (!super.exec.isAlive()) {
            System.out.println("Starting mudbox");
        }
        super.exec.setDaemon(true);
        super.exec.start();
    }
    
    public void run() {
        try {
            while (true) {
                super.qI.receive();
                super.inbuffer = new byte[super.qI.available()];
                final int count = super.qI.read(super.inbuffer);
                if (count > 0) {
                    this.received(super.inbuffer);
                }
                else {
                    System.out.println("Count was " + count + " so scrapping message");
                }
            }
        }
        catch (IOException e) {}
        catch (ThreadDeath td) {
            System.out.println("<" + Thread.currentThread() + ">" + "MudBox has been killed.");
        }
        finally {
            System.out.println("<" + Thread.currentThread() + ">" + "MudBox has terminated");
        }
    }
    
    public void showText(final String s) {
        this.display.addString(s + "\n");
    }
}
