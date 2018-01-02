// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.notation.Tune;
import java.util.EventObject;

public class TuneChangeEvent extends EventObject
{
    public static final int TUNE_UPDATED = 0;
    public static final int TUNE_REMOVED = 1;
    public static final int TUNE_ADDED = 2;
    private Tune m_tune;
    private String m_tuneNotation;
    private int m_eventType;
    
    public TuneChangeEvent(final Object source, final int eventType, final Tune newTune, final String newTuneNotation) {
        super(source);
        this.m_tune = null;
        this.m_tuneNotation = null;
        this.m_eventType = 0;
        this.m_eventType = eventType;
        this.m_tune = newTune;
        this.m_tuneNotation = newTuneNotation;
    }
    
    public String getTuneNotation() {
        return this.m_tuneNotation;
    }
    
    public int getType() {
        return this.m_eventType;
    }
    
    public Tune getTune() {
        return this.m_tune;
    }
    
    public String toString() {
        if (this.m_eventType == 1) {
            return "Tune n°" + this.m_tune.getReferenceNumber() + " REMOVED";
        }
        if (this.m_eventType == 2) {
            return "Tune n°" + this.m_tune.getReferenceNumber() + " ADDED";
        }
        return "Tune n°" + this.m_tune.getReferenceNumber() + " UPDATED";
    }
}
