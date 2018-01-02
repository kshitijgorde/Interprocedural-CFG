// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.Instrument;
import abc.parser.PositionableMultiNote;
import abc.notation.MultiNote;
import abc.notation.Tempo;
import java.util.Vector;
import scanner.PositionableInCharStream;
import abc.parser.PositionableNote;
import abc.notation.Tuplet;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiEvent;
import abc.notation.KeySignature;
import abc.notation.Note;

public class OldBasicMidiConverter extends OldMidiConverterAbstract
{
    public MidiEvent[] getMidiEventsFor(final Note note, final KeySignature key, final long elapsedTime) throws InvalidMidiDataException {
        final long noteLength = this.getNoteLengthInTicks(note);
        MidiEvent[] events = null;
        if (!note.isRest()) {
            events = new MidiEvent[2];
            final ShortMessage myNoteOn = new ShortMessage();
            myNoteOn.setMessage(144, this.getMidiNoteNumber(note, key), 50);
            events[0] = new MidiEvent(myNoteOn, elapsedTime);
            final ShortMessage myNoteOff = new ShortMessage();
            myNoteOff.setMessage(128, this.getMidiNoteNumber(note, key), 50);
            events[1] = new MidiEvent(myNoteOff, elapsedTime + noteLength);
        }
        return events;
    }
    
    public MidiEvent[] getMidiEventsFor(final Tuplet tuplet, final KeySignature key, long elapsedTime) throws InvalidMidiDataException {
        final float totalTupletLength = tuplet.getTotalRelativeLength();
        final Vector tupletAsVector = tuplet.getNotesAsVector();
        final int notesNb = tupletAsVector.size();
        final MidiEvent[] events = new MidiEvent[3 * notesNb];
        for (int j = 0; j < tupletAsVector.size(); ++j) {
            Note note = null;
            note = tupletAsVector.elementAt(j);
            long noteLength = this.getNoteLengthInTicks(note);
            noteLength = (long)(noteLength * totalTupletLength / notesNb);
            if (!note.isRest()) {
                final ShortMessage myNoteOn = new ShortMessage();
                myNoteOn.setMessage(144, this.getMidiNoteNumber(note, key), 50);
                events[3 * j] = new MidiEvent(myNoteOn, elapsedTime);
                events[3 * j + 1] = new MidiEvent(new NotationMarkerMessage((PositionableInCharStream)note), elapsedTime);
                final ShortMessage myNoteOff = new ShortMessage();
                myNoteOff.setMessage(128, this.getMidiNoteNumber(note, key), 50);
                elapsedTime += noteLength;
                events[3 * j + 2] = new MidiEvent(myNoteOff, elapsedTime);
            }
        }
        return events;
    }
    
    public MidiEvent[] getMidiEventsFor(final Tempo tempo, final long lastPosInTicks) throws InvalidMidiDataException {
        final TempoMessage mt = new TempoMessage(tempo);
        final MidiEvent me = new MidiEvent(mt, lastPosInTicks);
        final MidiEvent[] events = null;
        return events;
    }
    
    public MidiEvent[] getMidiEventsFor(final MultiNote notes, final KeySignature key, final long elapsedTime) throws InvalidMidiDataException {
        final Vector notesVector = notes.getNotesAsVector();
        final MidiEvent[] events = new MidiEvent[2 * notesVector.size() + 1];
        for (int j = 0; j < notesVector.size(); ++j) {
            final Note note = notesVector.elementAt(j);
            final float noteLength = this.getNoteLengthInTicks(note);
            if (!note.isRest()) {
                final ShortMessage myNoteOn = new ShortMessage();
                myNoteOn.setMessage(144, this.getMidiNoteNumber(note, key), 50);
                events[j] = new MidiEvent(myNoteOn, elapsedTime);
            }
        }
        events[notesVector.size()] = new MidiEvent(new NotationMarkerMessage((PositionableInCharStream)notes), elapsedTime);
        for (int j = 0; j < notesVector.size(); ++j) {
            final Note note = notesVector.elementAt(j);
            final long noteLength2 = this.getNoteLengthInTicks(note);
            if (!note.isRest()) {
                final ShortMessage myNoteOff = new ShortMessage();
                myNoteOff.setMessage(128, this.getMidiNoteNumber(note, key), 50);
                events[notesVector.size() + j + 1] = new MidiEvent(myNoteOff, elapsedTime + noteLength2);
            }
        }
        return events;
    }
    
    public Instrument getInstrument() {
        return null;
    }
    
    public void setInstrument(final Instrument instr) {
        throw new RuntimeException("Method not implemented");
    }
}
