// 
// Decompiled by Procyon v0.5.30
// 

package russotto.iff;

import java.io.IOException;
import java.io.File;

public class IFFOutputFile extends IFFFile
{
    public IFFOutputFile(final File file) throws IOException {
        super(file, "rw");
    }
    
    public IFFOutputFile(final File file, final String type) throws IOException {
        this(file);
        this.openChunk("FORM");
        this.write(this.getOSType(type), 0, 4);
    }
    
    public IFFOutputFile(final String name) throws IOException {
        super(name, "rw");
    }
    
    public IFFOutputFile(final String name, final String type) throws IOException {
        this(name);
        this.openChunk("FORM");
        this.write(this.getOSType(type), 0, 4);
    }
    
    private byte[] getOSType(final String s) {
        final byte[] result = new byte[4];
        s.getBytes(0, 4, result, 0);
        return result;
    }
    
    public synchronized void openChunk(final String type) throws IOException {
        this.write(this.getOSType(type), 0, 4);
        super.openchunks.push(new Long(this.getFilePointer()));
        this.writeInt(0);
    }
    
    public synchronized void closeChunk() throws IOException {
        final long currentlocation = this.getFilePointer();
        final int chunklength = this.getChunkPointer();
        final long location = super.openchunks.pop();
        this.seek(location);
        this.writeInt(chunklength);
        this.seek(currentlocation);
        if ((chunklength & 0x1) == 0x1) {
            this.writeByte(0);
        }
    }
    
    public synchronized void close() throws IOException {
        while (!super.openchunks.empty()) {
            this.closeChunk();
        }
        super.close();
    }
}
