// 
// Decompiled by Procyon v0.5.30
// 

package russotto.iff;

import java.io.IOException;
import java.io.File;
import java.util.Stack;

public class IFFInputFile extends IFFFile
{
    private Stack openchunkends;
    
    public IFFInputFile(final File file) throws IOException {
        super(file, "r");
        this.openchunkends = new Stack();
    }
    
    public IFFInputFile(final String s) throws IOException {
        super(s, "r");
        this.openchunkends = new Stack();
    }
    
    public synchronized IFFChunkInfo readChunkInfo() throws IOException {
        final IFFChunkInfo iffChunkInfo = new IFFChunkInfo();
        final byte[] array = new byte[4];
        this.read(array, 0, 4);
        final long filePointer = this.getFilePointer();
        iffChunkInfo.chunktype = new String(array, 0);
        iffChunkInfo.chunklength = this.readInt();
        super.openchunks.push(new Long(filePointer));
        this.openchunkends.push(new Long(this.getFilePointer() + iffChunkInfo.chunklength));
        return iffChunkInfo;
    }
    
    public synchronized IFFChunkInfo skipToChunk(final String s) throws IOException, IFFChunkNotFoundException {
        if (this.getFilePointer() >= this.openchunkends.peek()) {
            throw new IFFChunkNotFoundException("Chunk " + s + " not found at current level");
        }
        IFFChunkInfo iffChunkInfo;
        for (iffChunkInfo = this.readChunkInfo(); !iffChunkInfo.chunktype.equals(s); iffChunkInfo = this.readChunkInfo()) {
            this.closeChunk();
            if (this.getFilePointer() >= this.openchunkends.peek()) {
                throw new IFFChunkNotFoundException("Chunk " + s + " not found at current level");
            }
        }
        return iffChunkInfo;
    }
    
    public synchronized String readFORM() throws IOException {
        final byte[] array = new byte[4];
        if (this.readChunkInfo().chunktype.equals("FORM")) {
            this.read(array, 0, 4);
        }
        return new String(array, 0);
    }
    
    public synchronized void closeChunk() throws IOException {
        final long n = this.openchunkends.pop() + 1L & 0xFFFFFFFFFFFFFFFEL;
        super.openchunks.pop();
        this.seek(n);
    }
    
    public synchronized void close() throws IOException {
        while (!super.openchunks.empty()) {
            try {
                this.closeChunk();
            }
            catch (IOException ex) {}
        }
        super.close();
    }
}
