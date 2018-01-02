// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.util;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.awt.Toolkit;
import java.awt.Component;

public class ResourceLoader
{
    private static final byte[] FIRST_BYTES;
    private static final int VERSION = 3;
    public static final int DATA_INT = 4;
    public static final int DATA_IMAGE = 8;
    public static final int DATA_COLOR = 9;
    public static final int DATA_FONT = 10;
    private Component target;
    private Toolkit toolkit;
    private DataInputStream in;
    private String name;
    private int progress;
    private int recordType;
    private String recordName;
    private Object record;
    
    public ResourceLoader(final Component target, final String name, final InputStream inputStream) throws IOException {
        this.recordType = -1;
        this.recordName = null;
        this.record = null;
        this.target = target;
        this.in = new DataInputStream(inputStream);
        this.name = name;
        this.toolkit = target.getToolkit();
        for (int i = 0; i <= ResourceLoader.FIRST_BYTES.length - 1; ++i) {
            if (ResourceLoader.FIRST_BYTES[i] != this.in.read()) {
                throw new IOException("Not a resource file");
            }
        }
        if (this.in.readInt() != 3) {
            throw new IOException("Unsupported file format version");
        }
        if (!name.equals(this.in.readUTF())) {
            throw new IOException("Bad resource file type");
        }
        this.progress = this.in.readUnsignedByte();
    }
    
    public synchronized int loadNext() throws IOException, InterruptedException {
        if (this.progress == 255) {
            return -1;
        }
        this.record = null;
        this.recordType = this.in.readInt();
        this.recordName = this.in.readUTF();
        int i = this.in.readInt();
        if (this.recordType == 8) {
            final byte[] array = new byte[i];
            this.in.readFully(array);
            final Image image = this.toolkit.createImage(array);
            final MediaTracker mediaTracker = new MediaTracker(this.target);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            this.record = image;
        }
        else if (this.recordType == 9) {
            this.record = new Color(this.in.readInt());
        }
        else if (this.recordType == 10) {
            this.record = new Font(this.in.readUTF(), this.in.readInt(), this.in.readInt());
        }
        else {
            if (i < 0) {
                throw new IOException("Resource file is corrupted");
            }
            while (i > 0) {
                this.in.read();
                --i;
            }
        }
        return this.progress = this.in.readUnsignedByte();
    }
    
    public int getResourceType() {
        return this.recordType;
    }
    
    public String getResourceName() {
        return this.recordName;
    }
    
    public Object getResource() {
        return this.record;
    }
    
    public Image getImage() {
        return (this.recordType == 8) ? ((Image)this.record) : null;
    }
    
    public Color getColor() {
        return (this.recordType == 9) ? ((Color)this.record) : null;
    }
    
    public Font getFont() {
        return (this.recordType == 10) ? ((Font)this.record) : null;
    }
    
    static {
        FIRST_BYTES = "(BM Resources)".getBytes();
    }
}
