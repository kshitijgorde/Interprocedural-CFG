// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Iterator;

public interface LiteralIterator extends Iterator
{
    int nextInt();
    
    boolean isIntSupported();
    
    long nextLong();
    
    boolean isLongSupported();
    
    float nextFloat();
    
    boolean isFloatSupported();
    
    double nextDouble();
    
    boolean isDoubleSupported();
    
    boolean nextBoolean();
    
    boolean isBooleanSupported();
}
