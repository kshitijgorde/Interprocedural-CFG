// 
// Decompiled by Procyon v0.5.30
// 

public class ee implements d5
{
    public void b() {
    }
    
    public long a() {
        final Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
