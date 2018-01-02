// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import abc.notation.NoteAbstract;
import java.util.EventListener;

public interface TunePlayerListenerInterface extends EventListener
{
    void playBegin(final PlayerStateChangeEvent p0);
    
    void playEnd(final PlayerStateChangeEvent p0);
    
    void tempoChanged(final TempoChangeEvent p0);
    
    void partPlayed(final int p0, final int p1);
    
    void notePlayed(final NoteAbstract p0);
}
