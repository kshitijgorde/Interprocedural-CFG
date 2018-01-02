// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class NFDebugLogFile implements NFDebugObserver
{
    private FileOutputStream a;
    private PrintStream b;
    
    public NFDebugLogFile(final String s) {
        this.a = null;
        this.b = null;
        try {
            this.a = new FileOutputStream(s);
            this.b = new PrintStream(this.a, true);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        NFDebug.addObserver(this);
    }
    
    public void finalize() {
        try {
            this.a.flush();
            this.a.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        NFDebug.removeObserver(this);
    }
    
    public void debugMask(final long n) {
    }
    
    public void debugMessage(final String s) {
        try {
            this.a.write(s.getBytes());
            this.a.write(10);
            this.a.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void debugException(final String s, final Exception ex) {
    }
    
    public void debugWarning(final Object o) {
    }
    
    public void debugInfo(final Object o) {
    }
    
    public void setStandardOutput() {
        System.setOut(this.b);
    }
    
    public void setStandardError() {
        System.setErr(this.b);
    }
}
