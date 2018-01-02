// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public interface ProgressCapsule
{
    public static final int PROGRESS_NOT_STARTED = -1;
    public static final int PROGRESS_FINISHED = 0;
    public static final int PROGRESS_ONGOING = 1;
    public static final int PROGRESS_ABORTED = 2;
    public static final int PROGRESS_FAILED = 3;
    
    int getMaximum();
    
    int getMinimum();
    
    int getValue();
    
    int getStatus();
}
