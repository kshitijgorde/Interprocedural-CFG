// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import abc.notation.MultiNote;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Track;
import abc.notation.KeySignature;
import abc.notation.Note;

public class BasicPositionableMidiConverter extends BasicMidiConverter
{
    protected void playNote(final Note note, final int indexInScore, final KeySignature currentKey, final long reference, final long duration, final Track track) throws InvalidMidiDataException {
        final MidiEvent[] array = { new MidiEvent(new NoteIndexMessage(indexInScore), reference) };
        MidiConverterAbstract.addEventsToTrack(track, array);
        super.playNote(note, indexInScore, currentKey, reference, duration, track);
    }
    
    protected void playMultiNote(final MultiNote multiNote, final int indexInScore, final KeySignature currentKey, final long reference, final Track track) throws InvalidMidiDataException {
        final MidiEvent[] array = { new MidiEvent(new NoteIndexMessage(indexInScore), reference) };
        MidiConverterAbstract.addEventsToTrack(track, array);
        super.playMultiNote(multiNote, indexInScore, currentKey, reference, track);
    }
}
