// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import EDU.oswego.cs.dl.util.concurrent.SemaphoreControlledChannel;

public class PipedChannel extends SemaphoreControlledChannel
{
    protected ObjectInputStream in_;
    protected ObjectOutputStream out_;
    protected final PipedOutputStream outp_;
    protected final PipedInputStream inp_;
    
    public PipedChannel() {
        super(1);
        try {
            this.outp_ = new PipedOutputStream();
            (this.inp_ = new PipedInputStream()).connect(this.outp_);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new Error("Cannot construct Pipe?");
        }
    }
    
    protected Object extract() {
        try {
            return this.in().readObject();
        }
        catch (InterruptedIOException ex3) {
            Thread.currentThread().interrupt();
            return null;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new Error("IO exception during take");
        }
        catch (ClassNotFoundException ex2) {
            ex2.printStackTrace();
            throw new Error("Serialization exception during take");
        }
    }
    
    protected synchronized ObjectInputStream in() {
        try {
            if (this.in_ == null) {
                this.in_ = new ObjectInputStream(this.inp_);
            }
            return this.in_;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new Error("IO exception during open");
        }
    }
    
    protected void insert(final Object o) {
        try {
            this.out().writeObject(o);
        }
        catch (InterruptedIOException ex2) {
            Thread.currentThread().interrupt();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new Error("IO exception during put");
        }
    }
    
    protected synchronized ObjectOutputStream out() {
        try {
            if (this.out_ == null) {
                this.out_ = new ObjectOutputStream(this.outp_);
            }
            return this.out_;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new Error("IO exception during open");
        }
    }
    
    public Object peek() {
        return null;
    }
}
