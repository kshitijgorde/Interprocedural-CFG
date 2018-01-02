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
    
    public IFFFile(final String s, final String s2) throws IOException {
        super(s, s2);
        this.openchunks = new Stack();
    }
    
    public IFFFile(final File file, final String s) throws IOException {
        super(file, s);
        this.openchunks = new Stack();
    }
    
    public void chunkSeek(final int n) throws IOException {
        this.seek(this.openchunks.peek() + 4L + n);
    }
    
    public int getChunkPointer() throws IOException {
        return (int)this.getFilePointer() - (int)(long)this.openchunks.peek() - 4;
    }
}
