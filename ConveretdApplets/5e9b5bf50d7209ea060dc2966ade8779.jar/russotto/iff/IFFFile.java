// 
// Decompiled by Procyon v0.5.30
// 

package russotto.iff;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.io.RandomAccessFile;

public class IFFFile extends RandomAccessFile
{
    protected Stack openchunks;
    
    public IFFFile(final String name, final String mode) throws IOException {
        super(name, mode);
        this.openchunks = new Stack();
    }
    
    public IFFFile(final File file, final String mode) throws IOException {
        super(file, mode);
        this.openchunks = new Stack();
    }
    
    public void chunkSeek(final int offset) throws IOException {
        this.seek(this.openchunks.peek() + 4L + offset);
    }
    
    public int getChunkPointer() throws IOException {
        return (int)this.getFilePointer() - (int)(long)this.openchunks.peek() - 4;
    }
}
