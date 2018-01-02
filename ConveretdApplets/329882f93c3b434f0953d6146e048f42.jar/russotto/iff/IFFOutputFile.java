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
    
    public IFFOutputFile(final File file, final String s) throws IOException {
        this(file);
        this.openChunk("FORM");
        this.write(this.getOSType(s), 0, 4);
    }
    
    public IFFOutputFile(final String s) throws IOException {
        super(s, "rw");
    }
    
    public IFFOutputFile(final String s, final String s2) throws IOException {
        this(s);
        this.openChunk("FORM");
        this.write(this.getOSType(s2), 0, 4);
    }
    
    private byte[] getOSType(final String s) {
        final byte[] array = new byte[4];
        s.getBytes(0, 4, array, 0);
        return array;
    }
    
    public synchronized void openChunk(final String s) throws IOException {
        this.write(this.getOSType(s), 0, 4);
        super.openchunks.push(new Long(this.getFilePointer()));
        this.writeInt(0);
    }
    
    public synchronized void closeChunk() throws IOException {
        final long filePointer = this.getFilePointer();
        final int chunkPointer = this.getChunkPointer();
        this.seek(super.openchunks.pop());
        this.writeInt(chunkPointer);
        this.seek(filePointer);
        if ((chunkPointer & 0x1) == 0x1) {
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
