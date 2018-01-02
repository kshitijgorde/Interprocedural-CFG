// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.MetaMessage;
import abc.notation.Tempo;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiMessage;
import abc.notation.KeySignature;
import abc.notation.Note;

public class BasicMidiConverter extends MidiConverterAbstract
{
    public MidiMessage[] getNoteOneMessageFor(final Note note, final KeySignature key) throws InvalidMidiDataException {
        final MidiMessage[] events = { null };
        final ShortMessage myNoteOn = new ShortMessage();
        myNoteOn.setMessage(144, MidiConverterAbstract.getMidiNoteNumber(note, key), 50);
        events[0] = myNoteOn;
        return events;
    }
    
    public MidiMessage[] getNoteOffMessageFor(final Note note, final KeySignature key) throws InvalidMidiDataException {
        final ShortMessage[] events = { null };
        final ShortMessage myNoteOff = new ShortMessage();
        myNoteOff.setMessage(128, MidiConverterAbstract.getMidiNoteNumber(note, key), 50);
        events[0] = myNoteOff;
        return events;
    }
    
    public MidiMessage[] getMidiMessagesFor(final Tempo tempo) throws InvalidMidiDataException {
        final TempoMessage tempoM = new TempoMessage(tempo);
        final MidiMessage[] messages = { tempoM, new MetaMessageWA(tempoM) };
        return messages;
    }
}
