// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Decoder;

public abstract class AudioDeviceBase implements AudioDevice
{
    private boolean open;
    private Decoder decoder;
    
    public AudioDeviceBase() {
        this.open = false;
        this.decoder = null;
    }
    
    public synchronized void open(final Decoder decoder) throws JavaLayerException {
        if (!this.isOpen()) {
            this.decoder = decoder;
            this.openImpl();
            this.setOpen(true);
        }
    }
    
    protected void openImpl() throws JavaLayerException {
    }
    
    protected void setOpen(final boolean open) {
        this.open = open;
    }
    
    public synchronized boolean isOpen() {
        return this.open;
    }
    
    public synchronized void close() {
        if (this.isOpen()) {
            this.closeImpl();
            this.setOpen(false);
            this.decoder = null;
        }
    }
    
    protected void closeImpl() {
    }
    
    public void write(final short[] array, final int n, final int n2) throws JavaLayerException {
        if (this.isOpen()) {
            this.writeImpl(array, n, n2);
        }
    }
    
    protected void writeImpl(final short[] array, final int n, final int n2) throws JavaLayerException {
    }
    
    public void flush() {
        if (this.isOpen()) {
            this.flushImpl();
        }
    }
    
    protected void flushImpl() {
    }
    
    protected Decoder getDecoder() {
        return this.decoder;
    }
    
    public abstract int getPosition();
}
