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
    
    public IFFInputFile(final String name) throws IOException {
        super(name, "r");
        this.openchunkends = new Stack();
    }
    
    public synchronized IFFChunkInfo readChunkInfo() throws IOException {
        final IFFChunkInfo result = new IFFChunkInfo();
        final byte[] chunktype = new byte[4];
        this.read(chunktype, 0, 4);
        final long chunkbegin = this.getFilePointer();
        result.chunktype = new String(chunktype, 0);
        result.chunklength = this.readInt();
        super.openchunks.push(new Long(chunkbegin));
        this.openchunkends.push(new Long(this.getFilePointer() + result.chunklength));
        return result;
    }
    
    public synchronized IFFChunkInfo skipToChunk(final String type) throws IOException, IFFChunkNotFoundException {
        if (this.getFilePointer() >= this.openchunkends.peek()) {
            throw new IFFChunkNotFoundException("Chunk " + type + " not found at current level");
        }
        IFFChunkInfo chunkinfo;
        for (chunkinfo = this.readChunkInfo(); !chunkinfo.chunktype.equals(type); chunkinfo = this.readChunkInfo()) {
            this.closeChunk();
            if (this.getFilePointer() >= this.openchunkends.peek()) {
                throw new IFFChunkNotFoundException("Chunk " + type + " not found at current level");
            }
        }
        return chunkinfo;
    }
    
    public synchronized String readFORM() throws IOException {
        final byte[] subtype = new byte[4];
        final IFFChunkInfo formchunkinfo = this.readChunkInfo();
        if (formchunkinfo.chunktype.equals("FORM")) {
            this.read(subtype, 0, 4);
        }
        return new String(subtype, 0);
    }
    
    public synchronized void closeChunk() throws IOException {
        final long chunkend = this.openchunkends.pop() + 1L & 0xFFFFFFFFFFFFFFFEL;
        super.openchunks.pop();
        this.seek(chunkend);
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
