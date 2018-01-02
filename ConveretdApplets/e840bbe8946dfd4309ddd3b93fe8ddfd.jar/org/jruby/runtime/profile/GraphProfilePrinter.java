// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.io.PrintStream;

public class GraphProfilePrinter extends AbstractProfilePrinter
{
    public static MethodData currentData;
    private Invocation topInvocation;
    
    public GraphProfilePrinter(final Invocation top) {
        this.topInvocation = top;
    }
    
    public void printProfile(final PrintStream out) {
        out.printf("\nTotal time: %s\n\n", this.nanoString(this.topInvocation.getDuration()));
        out.println(" %total   %self       total        self    children                 calls  name");
        final Map<Integer, MethodData> methods = this.methodData(this.topInvocation);
        final MethodData[] sortedMethods = methods.values().toArray(new MethodData[0]);
        Arrays.sort(sortedMethods, new Comparator<MethodData>() {
            public int compare(final MethodData md1, final MethodData md2) {
                final long time1 = md1.totalTime();
                final long time2 = md2.totalTime();
                return (time1 == time2) ? 0 : ((time1 < time2) ? 1 : -1);
            }
        });
        final MethodData[] arr$ = sortedMethods;
        for (int len$ = arr$.length, i$ = 0; i$ < len$; ++i$) {
            final MethodData data = GraphProfilePrinter.currentData = arr$[i$];
            if (!this.isProfilerInvocation(data.invocations.get(0))) {
                out.println("---------------------------------------------------------------------------------------------------------");
                final int serial = data.serialNumber;
                if (serial != 0) {
                    final int[] parentSerialsInts = data.parents();
                    final Integer[] parentSerials = new Integer[parentSerialsInts.length];
                    for (int i = 0; i < parentSerialsInts.length; ++i) {
                        parentSerials[i] = parentSerialsInts[i];
                    }
                    Arrays.sort(parentSerials, new Comparator<Integer>() {
                        public int compare(final Integer parent1, final Integer parent2) {
                            final long time1 = GraphProfilePrinter.currentData.rootInvocationsFromParent(parent1).totalTime();
                            final long time2 = GraphProfilePrinter.currentData.rootInvocationsFromParent(parent2).totalTime();
                            return (time1 == time2) ? 0 : ((time1 < time2) ? -1 : 1);
                        }
                    });
                    if (parentSerials.length > 0) {
                        for (final int parentSerial : parentSerials) {
                            final String callerName = this.methodName(parentSerial);
                            final InvocationSet invs = data.rootInvocationsFromParent(parentSerial);
                            out.print("                 ");
                            this.pad(out, 10, this.nanoString(invs.totalTime()));
                            out.print("  ");
                            this.pad(out, 10, this.nanoString(invs.selfTime()));
                            out.print("  ");
                            this.pad(out, 10, this.nanoString(invs.childTime()));
                            out.print("  ");
                            this.pad(out, 20, Integer.toString(data.invocationsFromParent(parentSerial).totalCalls()) + "/" + Integer.toString(data.totalCalls()));
                            out.print("  ");
                            out.print(callerName);
                            out.println("");
                        }
                    }
                }
                final String displayName = this.methodName(serial);
                if (this.topInvocation.getDuration() == 0L) {
                    out.print("   100%    100%  ");
                }
                else {
                    out.print("  ");
                    this.pad(out, 4, Long.toString(data.totalTime() * 100L / this.topInvocation.getDuration()));
                    out.print("%   ");
                    this.pad(out, 4, Long.toString(data.selfTime() * 100L / this.topInvocation.getDuration()));
                    out.print("%  ");
                }
                this.pad(out, 10, this.nanoString(data.totalTime()));
                out.print("  ");
                this.pad(out, 10, this.nanoString(data.selfTime()));
                out.print("  ");
                this.pad(out, 10, this.nanoString(data.childTime()));
                out.print("  ");
                this.pad(out, 20, Integer.toString(data.totalCalls()));
                out.print("  ");
                out.print(displayName);
                out.println("");
                final int[] childSerialsInts = data.children();
                final Integer[] childSerials = new Integer[childSerialsInts.length];
                for (int j = 0; j < childSerialsInts.length; ++j) {
                    childSerials[j] = childSerialsInts[j];
                }
                Arrays.sort(childSerials, new Comparator<Integer>() {
                    public int compare(final Integer child1, final Integer child2) {
                        final long time1 = GraphProfilePrinter.currentData.rootInvocationsOfChild(child1).totalTime();
                        final long time2 = GraphProfilePrinter.currentData.rootInvocationsOfChild(child2).totalTime();
                        return (time1 == time2) ? 0 : ((time1 < time2) ? 1 : -1);
                    }
                });
                if (childSerials.length > 0) {
                    for (final int childSerial : childSerials) {
                        if (!this.isThisProfilerInvocation(childSerial)) {
                            final String callerName2 = this.methodName(childSerial);
                            final InvocationSet invs2 = data.rootInvocationsOfChild(childSerial);
                            out.print("                 ");
                            this.pad(out, 10, this.nanoString(invs2.totalTime()));
                            out.print("  ");
                            this.pad(out, 10, this.nanoString(invs2.selfTime()));
                            out.print("  ");
                            this.pad(out, 10, this.nanoString(invs2.childTime()));
                            out.print("  ");
                            this.pad(out, 20, Integer.toString(data.invocationsOfChild(childSerial).totalCalls()) + "/" + Integer.toString(methods.get(childSerial).totalCalls()));
                            out.print("  ");
                            out.print(callerName2);
                            out.println("");
                        }
                    }
                }
            }
        }
    }
}
