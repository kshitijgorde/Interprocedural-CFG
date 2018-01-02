// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.MidiSystem;
import abc.notation.NoteAbstract;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Instrument;
import java.util.Vector;
import javax.sound.midi.Sequencer;
import abc.notation.Tune;
import javax.sound.midi.MetaEventListener;

public class TunePlayer implements MetaEventListener
{
    private Tune m_tune;
    private Sequencer seq;
    private boolean m_isStarted;
    private Vector m_listeners;
    private int m_tempo;
    private MidiConverterInterface m_converter;
    
    public TunePlayer() {
        this.m_tune = null;
        this.seq = null;
        this.m_isStarted = false;
        this.m_listeners = null;
        this.m_tempo = 180;
        this.m_converter = null;
        this.m_converter = new BasicPositionableMidiConverter();
        this.m_listeners = new Vector();
    }
    
    public TunePlayer(final MidiConverterInterface converter) {
        this.m_tune = null;
        this.seq = null;
        this.m_isStarted = false;
        this.m_listeners = null;
        this.m_tempo = 180;
        this.m_converter = null;
        this.m_converter = converter;
        this.m_listeners = new Vector();
    }
    
    public void addListener(final TunePlayerListenerInterface listener) {
        this.m_listeners.addElement(listener);
    }
    
    public void removeListener(final TunePlayerListenerInterface listener) {
        this.m_listeners.removeElement(listener);
    }
    
    public void setTempo(final int tempo) {
        this.m_tempo = tempo;
        this.seq.setTempoInBPM(this.m_tempo);
        this.notifyForTempoChange(tempo);
    }
    
    public int getTempo() {
        return this.m_tempo;
    }
    
    public Instrument getInstrument() {
        return this.m_converter.getInstrument();
    }
    
    public void setInstrument(final Instrument instr) throws MidiUnavailableException {
        this.m_converter.setInstrument(instr);
    }
    
    public Tune getTune() {
        return this.m_tune;
    }
    
    public void play(final Tune tune) throws IllegalStateException {
        if (this.m_isStarted) {
            try {
                this.m_tune = tune;
                final Sequence sequence = this.m_converter.toMidiSequence(this.m_tune);
                this.seq.setSequence(sequence);
                this.seq.setTempoInBPM(this.m_tempo);
                this.seq.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        throw new IllegalStateException("The player hasn't been started yet !");
    }
    
    public boolean isPlaying() {
        return this.seq != null && this.seq.isRunning();
    }
    
    public void meta(final MetaMessage meta) {
        if (meta.getType() == 81 || (meta.getType() == 6 && MetaMessageWA.isTempoMessage(meta))) {
            final int expectdNotesNbPerMn = TempoMessage.getTempo(meta.getData()).getNotesNumberPerMinute();
            System.out.println("detected tempo change : " + expectdNotesNbPerMn + " current seq tempo : " + this.seq.getTempoInBPM());
            if ((int)this.seq.getTempoInBPM() != expectdNotesNbPerMn) {
                this.seq.setTempoInBPM(expectdNotesNbPerMn);
            }
            this.notifyForTempoChange((int)this.seq.getTempoInBPM());
        }
        else if (meta.getType() == 64) {
            this.notifyNotePlayedChanged(this.m_tune.getMusic().elementAt(NoteIndexMessage.getIndex(meta.getData())));
        }
        else if (MetaMessageWA.isNotationMarker(meta)) {
            final int begin = NotationMarkerMessage.getBeginOffset(meta.getData());
            final int end = NotationMarkerMessage.getEndOffset(meta.getData());
            this.notifyForPartPlayedChanged(begin, end);
        }
        else if (meta.getType() == 47) {
            this.notifyForPlayEnd();
        }
    }
    
    public void start() {
        try {
            (this.seq = MidiSystem.getSequencer()).open();
            this.seq.addMetaEventListener(this);
            this.m_isStarted = true;
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public void stopPlaying() {
        this.seq.stop();
    }
    
    public void stop() {
        this.seq.stop();
        this.seq.close();
        this.m_isStarted = false;
    }
    
    protected void notifyForTempoChange(final int newTempoValue) {
        final TempoChangeEvent event = new TempoChangeEvent(this, newTempoValue);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).tempoChanged(event);
        }
    }
    
    protected void notifyForPartPlayedChanged(final int begin, final int end) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).partPlayed(begin, end);
        }
    }
    
    protected void notifyNotePlayedChanged(final NoteAbstract note) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).notePlayed(note);
        }
    }
    
    protected void notifyForPlayBegin() {
        final PlayerStateChangeEvent e = new PlayerStateChangeEvent(this, true);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).playBegin(e);
        }
    }
    
    protected void notifyForPlayEnd() {
        final PlayerStateChangeEvent e = new PlayerStateChangeEvent(this, false);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).playEnd(e);
        }
    }
}
