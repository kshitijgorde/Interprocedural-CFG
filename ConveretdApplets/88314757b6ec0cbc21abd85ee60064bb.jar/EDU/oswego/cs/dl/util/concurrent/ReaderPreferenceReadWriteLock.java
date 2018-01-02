// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class ReaderPreferenceReadWriteLock extends WriterPreferenceReadWriteLock
{
    protected boolean allowReader() {
        return super.activeWriter_ == null;
    }
}
