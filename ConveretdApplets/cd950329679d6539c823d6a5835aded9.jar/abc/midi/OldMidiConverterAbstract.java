// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import java.util.Vector;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;
import abc.notation.BarLine;
import abc.notation.RepeatBarLine;
import abc.parser.PositionableMultiNote;
import abc.notation.MultiNote;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiEvent;
import abc.parser.PositionableNote;
import abc.notation.Note;
import abc.notation.Tuplet;
import abc.notation.KeySignature;
import abc.notation.Tempo;
import javax.sound.midi.Sequence;
import abc.notation.Tune;

public abstract class OldMidiConverterAbstract implements MidiConverterInterface
{
    private static final int SEQUENCE_RESOLUTION = 48;
    
    public Sequence toMidiSequence(final Tune tune) {
        Sequence sequence = null;
        try {
            sequence = new Sequence(0.0f, 48, 1);
            final Track track = sequence.createTrack();
            long trackLengthInTicks = track.ticks();
            int lastRepeatOpen = -1;
            int repeatNumber = 1;
            boolean inWrongEnding = false;
            int i = 0;
            KeySignature tuneKey = null;
            KeySignature currentKey = null;
            final long elapsedTime = 0L;
            for (Tune.Music staff = tune.getMusic(); i < staff.size(); ++i) {
                if (!inWrongEnding) {
                    if (staff.elementAt(i) instanceof Tempo) {
                        trackLengthInTicks = this.addEventsToTrack(track, this.getMidiEventsFor(staff.elementAt(i), trackLengthInTicks));
                    }
                    else if (staff.elementAt(i) instanceof KeySignature) {
                        tuneKey = staff.elementAt(i);
                        currentKey = new KeySignature(tuneKey.getAccidentals());
                    }
                    else if (staff.elementAt(i) instanceof Tuplet) {
                        final Tuplet tuplet = staff.elementAt(i);
                        trackLengthInTicks = this.addEventsToTrack(track, this.getMidiEventsFor(tuplet, currentKey, trackLengthInTicks));
                        final Vector notes = tuplet.getNotesAsVector();
                        for (int j = 0; j < notes.size(); ++j) {
                            this.updateKey(currentKey, notes.elementAt(j));
                        }
                    }
                    else if (staff.elementAt(i) instanceof Note) {
                        final PositionableNote note = staff.elementAt(i);
                        if (!note.isRest()) {
                            final MidiEvent[] array = { new MidiEvent(new NoteIndexMessage(i), trackLengthInTicks) };
                            this.addEventsToTrack(track, array);
                            trackLengthInTicks = this.addEventsToTrack(track, this.getMidiEventsFor(note, currentKey, trackLengthInTicks));
                            this.updateKey(currentKey, note);
                        }
                        else {
                            trackLengthInTicks += this.getNoteLengthInTicks(note);
                        }
                    }
                    else if (staff.elementAt(i) instanceof MultiNote) {
                        final PositionableMultiNote multiNote = staff.elementAt(i);
                        trackLengthInTicks = this.addEventsToTrack(track, this.getMidiEventsFor(multiNote, currentKey, trackLengthInTicks));
                        final Vector notes = multiNote.getNotesAsVector();
                        for (int j = 0; j < notes.size(); ++j) {
                            this.updateKey(currentKey, notes.elementAt(j));
                        }
                    }
                }
                if (staff.elementAt(i) instanceof RepeatBarLine) {
                    final RepeatBarLine bar = staff.elementAt(i);
                    if (repeatNumber < bar.getRepeatNumber() && lastRepeatOpen != -1) {
                        ++repeatNumber;
                        i = lastRepeatOpen;
                    }
                    else {
                        inWrongEnding = (repeatNumber > bar.getRepeatNumber());
                    }
                }
                else if (staff.elementAt(i) instanceof BarLine) {
                    currentKey = new KeySignature(tuneKey.getAccidentals());
                    switch (staff.elementAt(i).getType()) {
                        case 1: {
                            lastRepeatOpen = i;
                            repeatNumber = 1;
                            break;
                        }
                        case 2: {
                            if (repeatNumber < 2 && lastRepeatOpen != -1) {
                                ++repeatNumber;
                                i = lastRepeatOpen;
                                break;
                            }
                            repeatNumber = 1;
                            lastRepeatOpen = -1;
                            break;
                        }
                    }
                }
            }
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequence;
    }
    
    private void updateKey(final KeySignature key, final Note note) {
        if (note.getAccidental() != 10) {
            key.setAccidental(note.toRootOctaveHeigth(), note.getAccidental());
        }
    }
    
    private long addEventsToTrack(final Track track, final MidiEvent[] events) {
        if (events != null) {
            for (int i = 0; i < events.length; ++i) {
                track.add(events[i]);
            }
        }
        return track.ticks();
    }
    
    public abstract MidiEvent[] getMidiEventsFor(final Note p0, final KeySignature p1, final long p2) throws InvalidMidiDataException;
    
    public abstract MidiEvent[] getMidiEventsFor(final Tuplet p0, final KeySignature p1, final long p2) throws InvalidMidiDataException;
    
    public abstract MidiEvent[] getMidiEventsFor(final Tempo p0, final long p1) throws InvalidMidiDataException;
    
    public abstract MidiEvent[] getMidiEventsFor(final MultiNote p0, final KeySignature p1, final long p2) throws InvalidMidiDataException;
    
    protected long getNoteLengthInTicks(final Note note) {
        final short noteLength = note.getDuration();
        final float numberOfQuarterNotesInThisNote = noteLength / 48.0f;
        final float lengthInTicks = 48.0f * numberOfQuarterNotesInThisNote;
        return (long)lengthInTicks;
    }
    
    protected long getNoteLengthInTicks(final MultiNote note) {
        final short longestLength = note.getLongestNote().getDuration();
        final float numberOfQuarterNotesInThisNote = longestLength / 48.0f;
        final float lengthInTicks = 48.0f * numberOfQuarterNotesInThisNote;
        return (long)lengthInTicks;
    }
    
    protected byte getMidiNoteNumber(final Note note, final KeySignature key) {
        final byte heigth = note.getHeigth();
        final byte accidental = note.getAccidental();
        byte midiNoteNumber = (byte)(heigth + 60);
        midiNoteNumber += (byte)(note.getOctaveTransposition() * 12);
        if (accidental == 10) {
            byte absoluteAccidental = 0;
            final byte heightOnOneOctave = (byte)(heigth % 12);
            absoluteAccidental = key.getAccidentalFor(heightOnOneOctave);
            switch (absoluteAccidental) {
                case -1: {
                    --midiNoteNumber;
                }
                case 1: {
                    ++midiNoteNumber;
                    break;
                }
            }
        }
        else {
            switch (accidental) {
                case -1: {
                    --midiNoteNumber;
                }
                case 1: {
                    ++midiNoteNumber;
                    break;
                }
            }
        }
        return midiNoteNumber;
    }
}
