// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.HashMap;
import java.util.WeakHashMap;
import org.jruby.runtime.Frame;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.jruby.runtime.ThreadContext;
import java.util.Map;

public class SimpleSampler
{
    private static final Map<ThreadContext, Object> CURRENT;
    private static final Map<String, Integer> SAMPLES;
    private static final List<List<String>> TRACES;
    private static boolean reported;
    
    public static void registerThreadContext(final ThreadContext tc) {
        synchronized (SimpleSampler.CURRENT) {
            SimpleSampler.CURRENT.put(tc, null);
        }
    }
    
    public static void startSampleThread() {
        new Thread(new Runnable() {
            public void run() {
                runSampling();
            }
        }).start();
    }
    
    public static void report() {
        if (!SimpleSampler.reported) {
            System.err.println();
            System.err.println("Samples - ");
            final List<String> samples = new ArrayList<String>();
            samples.addAll(SimpleSampler.SAMPLES.keySet());
            Collections.sort(samples, new Comparator<String>() {
                public int compare(final String o1, final String o2) {
                    return SimpleSampler.SAMPLES.get(o2) - SimpleSampler.SAMPLES.get(o1);
                }
            });
            for (final List<String> ls : SimpleSampler.TRACES) {
                if (ls.size() > 1) {
                    System.err.println("Trace #" + System.identityHashCode(ls));
                    for (final String ss : ls) {
                        System.err.println("  " + ss);
                    }
                    System.err.println();
                }
            }
            final String BLANKS = "                                                            ";
            for (final String ss2 : samples) {
                final int len = Math.max(60 - ss2.length(), 0);
                System.err.println(" " + ss2 + BLANKS.substring(0, len) + "==> " + SimpleSampler.SAMPLES.get(ss2));
            }
            SimpleSampler.reported = true;
        }
    }
    
    private static void runSampling() {
        final long interval = Long.parseLong(System.getProperty("jruby.sampling.interval", "10"));
        final int depth = Integer.parseInt(System.getProperty("jruby.sampling.depth", "5"));
        System.err.println("[Sampling with");
        System.err.println(" - interval: " + interval);
        System.err.println(" - depth: " + depth + "]");
        synchronized (SimpleSampler.CURRENT) {
            while (!SimpleSampler.reported) {
                try {
                    SimpleSampler.CURRENT.wait(interval);
                }
                catch (InterruptedException ex) {}
                try {
                    for (final ThreadContext tc : SimpleSampler.CURRENT.keySet()) {
                        if (tc != null) {
                            final Frame[] frames = tc.createBacktrace(1, false);
                            if (frames == null) {
                                continue;
                            }
                            final List<String> trace = new ArrayList<String>(depth);
                            for (int i = Math.max(frames.length - depth, 0); i < frames.length; ++i) {
                                final Frame f = frames[i];
                                final String name = f.getKlazz() + "#" + f.getName();
                                if (!f.isBindingFrame() && !name.equals("null#null")) {
                                    trace.add(name);
                                    Integer v = SimpleSampler.SAMPLES.get(name);
                                    if (v == null) {
                                        v = 1;
                                    }
                                    else {
                                        ++v;
                                    }
                                    SimpleSampler.SAMPLES.put(name, v);
                                }
                            }
                            SimpleSampler.TRACES.add(trace);
                        }
                    }
                }
                catch (Exception e) {}
            }
        }
    }
    
    static {
        CURRENT = new WeakHashMap<ThreadContext, Object>();
        SAMPLES = new HashMap<String, Integer>();
        TRACES = new ArrayList<List<String>>();
        SimpleSampler.reported = false;
    }
}
