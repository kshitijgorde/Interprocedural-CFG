// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.io.PrintStream;

public class FlatProfilePrinter extends AbstractProfilePrinter
{
    private static final int SERIAL_OFFSET = 0;
    private static final int SELFTIME_OFFSET = 1;
    private static final int COUNT_OFFSET = 2;
    private static final int AGGREGATETIME_OFFSET = 3;
    private final Invocation topInvocation;
    
    public FlatProfilePrinter(final Invocation top) {
        this.topInvocation = top;
    }
    
    public void printProfile(final PrintStream out) {
        out.printf("Total time: %s\n\n", this.nanoString(this.topInvocation.getDuration()));
        final Map<Integer, MethodData> serialsToMethods = this.methodData(this.topInvocation);
        final long[][] tuples = new long[serialsToMethods.size()][];
        int j = 0;
        for (final Map.Entry<Integer, MethodData> entry : serialsToMethods.entrySet()) {
            final MethodData method = entry.getValue();
            tuples[j] = new long[] { entry.getKey(), method.selfTime(), method.totalCalls(), method.totalTime() };
            ++j;
        }
        Arrays.sort(tuples, new Comparator<long[]>() {
            public int compare(final long[] o1, final long[] o2) {
                final long o1Val = o1[3];
                final long o2Val = o2[3];
                return (o2Val > o1Val) ? 1 : ((o2Val < o1Val) ? -1 : 0);
            }
        });
        out.println("     total        self    children       calls  method");
        out.println("----------------------------------------------------------------");
        int lines = 0;
        for (final long[] tuple : tuples) {
            if (tuple[3] == 0L) {
                break;
            }
            final int index = (int)tuple[0];
            if (index != 0) {
                ++lines;
                final String name = this.methodName(index);
                this.pad(out, 10, this.nanoString(tuple[3]));
                out.print("  ");
                this.pad(out, 10, this.nanoString(tuple[1]));
                out.print("  ");
                this.pad(out, 10, this.nanoString(tuple[3] - tuple[1]));
                out.print("  ");
                this.pad(out, 10, Long.toString(tuple[2]));
                out.print("  ");
                out.println(name);
            }
            if (lines == 50) {
                break;
            }
        }
    }
}
