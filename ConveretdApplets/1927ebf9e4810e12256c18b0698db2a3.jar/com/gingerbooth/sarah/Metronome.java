// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class Metronome implements Runnable
{
    private static int[] pattern;
    private static int patternBeats;
    private static int now;
    private static Vector dancers;
    private static long beatLength;
    
    static {
        Metronome.pattern = new int[] { 12, 6 };
        Metronome.patternBeats = 18;
        Metronome.now = 0;
        Metronome.beatLength = 5000L;
    }
    
    public Metronome() {
        Metronome.now = 0;
        Metronome.dancers = new Vector(10, 10);
    }
    
    public static synchronized void addListener(final MetronomeEventListener metronomeEventListener) {
        Metronome.dancers.addElement(metronomeEventListener);
    }
    
    public static int getBeat() {
        return Metronome.now % Metronome.patternBeats;
    }
    
    public static long getBeatLength() {
        return Metronome.beatLength;
    }
    
    public static int getPatternBeats() {
        return Metronome.patternBeats;
    }
    
    public static int getPatternLength() {
        return Metronome.patternBeats;
    }
    
    public static int getPauseBeats() {
        return Metronome.pattern[0];
    }
    
    public static synchronized void removeListener(final MetronomeEventListener metronomeEventListener) {
        Metronome.dancers.removeElement(metronomeEventListener);
        Metronome.dancers.trimToSize();
    }
    
    public static void reset() {
        Metronome.now = 0;
    }
    
    public void run() {
        try {
            Thread.currentThread();
            Thread.sleep(Metronome.beatLength * Metronome.pattern[0]);
            Metronome.now = Metronome.pattern[0];
            while (true) {
                for (int i = 0; i < Metronome.dancers.size(); ++i) {
                    ((MetronomeEventListener)Metronome.dancers.elementAt(i)).onBeat(this);
                }
                Thread.currentThread();
                Thread.sleep(Metronome.beatLength);
                Metronome.now = (Metronome.now + 1) % Metronome.patternBeats;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public static void setBeatLength(final long beatLength) {
        Metronome.beatLength = beatLength;
    }
    
    public static void setPattern(final int[] pattern) {
        Metronome.pattern = pattern;
        Metronome.patternBeats = 0;
        for (int i = 0; i < pattern.length; ++i) {
            Metronome.patternBeats += Metronome.pattern[i];
        }
    }
}
