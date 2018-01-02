// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.Enumeration;

final class RunArrayEnumerator implements Enumeration
{
    RunArray run;
    int runIndex;
    int runLimit;
    
    public boolean hasMoreElements() {
        return this.runIndex <= this.runLimit;
    }
    
    public Object nextElement() {
        if (this.runIndex <= this.runLimit) {
            return Array.get(this.run.values.array, this.runIndex++);
        }
        throw new NoSuchElementException("RunArrayEnumerator");
    }
    
    RunArrayEnumerator(final RunArray runArray) {
        this(runArray, 0, runArray.count);
    }
    
    RunArrayEnumerator(final RunArray run, final int n, final int n2) {
        this.run = run;
        if (n < n2) {
            this.runIndex = run.getRunAndOffset(n)[0];
            this.runLimit = run.getRunAndOffset(n2 - 1)[0];
        }
        else {
            this.runIndex = 0;
            this.runLimit = -1;
        }
    }
}
