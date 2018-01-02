// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import java.util.Vector;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;
import abc.notation.BarLine;
import abc.notation.RepeatBarLine;
import abc.notation.MultiNote;
import abc.notation.Note;
import abc.notation.KeySignature;
import abc.notation.Tempo;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import abc.notation.Tune;
import javax.sound.midi.Instrument;

public abstract class MidiConverterAbstract implements MidiConverterInterface
{
    private static final int SEQUENCE_RESOLUTION = 48;
    protected Instrument instrument;
    
    public MidiConverterAbstract() {
        this.instrument = null;
    }
    
    public Sequence toMidiSequence(final Tune tune) {
        Sequence sequence = null;
        try {
            if (this.instrument == null) {
                this.setInstrument(MidiSystem.getSynthesizer().getAvailableInstruments()[0]);
            }
            sequence = new Sequence(0.0f, 48, 1);
            final ShortMessage sm = new ShortMessage();
            sm.setMessage(192, 0, this.instrument.getPatch().getProgram(), 0);
            final Track track = sequence.createTrack();
            track.add(new MidiEvent(sm, 0L));
            int lastRepeatOpen = -1;
            int repeatNumber = 1;
            boolean inWrongEnding = false;
            int i = 0;
            KeySignature tuneKey = null;
            KeySignature currentKey = null;
            long elapsedTime = 0L;
            for (Tune.Music staff = tune.getMusic(); i < staff.size(); ++i) {
                if (!inWrongEnding) {
                    if (staff.elementAt(i) instanceof Tempo) {
                        this.addTempoEventsFor(track, elapsedTime, this.getMidiMessagesFor(staff.elementAt(i)));
                    }
                    else if (staff.elementAt(i) instanceof KeySignature) {
                        tuneKey = staff.elementAt(i);
                        currentKey = new KeySignature(tuneKey.getAccidentals());
                    }
                    else if (staff.elementAt(i) instanceof Note && !staff.elementAt(i).isEndingTie()) {
                        final Note note = staff.elementAt(i);
                        final long noteDuration = getNoteLengthInTicks(note);
                        this.playNote(note, i, currentKey, elapsedTime, noteDuration, track);
                        elapsedTime += noteDuration;
                    }
                    else if (staff.elementAt(i) instanceof MultiNote) {
                        final MultiNote multiNote = staff.elementAt(i);
                        this.playMultiNote(multiNote, i, currentKey, elapsedTime, track);
                        elapsedTime += getNoteLengthInTicks(multiNote);
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
                if (staff.elementAt(i) instanceof BarLine) {
                    currentKey = new KeySignature(tuneKey.getAccidentals());
                }
            }
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return sequence;
    }
    
    public Instrument getInstrument() {
        return this.instrument;
    }
    
    public void setInstrument(final Instrument instr) throws MidiUnavailableException {
        MidiSystem.getSynthesizer().loadInstrument(instr);
        this.instrument = instr;
    }
    
    protected void playNote(final Note note, final int indexInScore, final KeySignature currentKey, final long timeReference, final long duration, final Track track) throws InvalidMidiDataException {
        if (!note.isRest() && !note.isEndingTie()) {
            addNoteOnEventsFor(track, timeReference, this.getNoteOneMessageFor(note, currentKey));
            this.addNoteOffEventsFor(track, timeReference + duration, this.getNoteOffMessageFor(note, currentKey));
            updateKey(currentKey, note);
        }
    }
    
    protected void playMultiNote(final MultiNote multiNote, final int indexInScore, final KeySignature currentKey, final long reference, final Track track) throws InvalidMidiDataException {
        final Vector notesVector = multiNote.getNotesAsVector();
        for (int j = 0; j < notesVector.size(); ++j) {
            final Note note = notesVector.elementAt(j);
            if (!note.isRest() && !note.isEndingTie()) {
                addNoteOnEventsFor(track, reference, this.getNoteOneMessageFor(note, currentKey));
            }
        }
        for (int j = 0; j < notesVector.size(); ++j) {
            final Note note = notesVector.elementAt(j);
            final long noteDuration = getNoteLengthInTicks(multiNote);
            if (!note.isRest() && !note.isEndingTie()) {
                this.addNoteOffEventsFor(track, reference + noteDuration, this.getNoteOffMessageFor(note, currentKey));
            }
        }
        for (int j = 0; j < notesVector.size(); ++j) {
            updateKey(currentKey, notesVector.elementAt(j));
        }
    }
    
    private static void addNoteOnEventsFor(final Track track, final long timeReference, final MidiMessage[] messages) {
        final MidiMessage myNoteOn = messages[0];
        final MidiEvent[] events = { new MidiEvent(myNoteOn, timeReference) };
        addEventsToTrack(track, events);
    }
    
    private void addTempoEventsFor(final Track track, final long timeReference, final MidiMessage[] messages) {
        for (int i = 0; i < messages.length; ++i) {
            final MidiEvent me = new MidiEvent(messages[i], timeReference);
            addEventsToTrack(track, me);
        }
    }
    
    private void addNoteOffEventsFor(final Track track, final long timeReference, final MidiMessage[] messages) {
        final MidiMessage myNoteOn = messages[0];
        final MidiEvent[] events = { new MidiEvent(myNoteOn, timeReference) };
        addEventsToTrack(track, events);
    }
    
    private static void updateKey(final KeySignature key, final Note note) {
        if (note.getAccidental() != 10) {
            key.setAccidental(note.toRootOctaveHeigth(), note.getAccidental());
        }
    }
    
    protected static long addEventsToTrack(final Track track, final MidiEvent[] events) {
        if (events != null) {
            for (int i = 0; i < events.length; ++i) {
                track.add(events[i]);
            }
        }
        return track.ticks();
    }
    
    protected static long addEventsToTrack(final Track track, final MidiEvent event) {
        track.add(event);
        return track.ticks();
    }
    
    public abstract MidiMessage[] getNoteOneMessageFor(final Note p0, final KeySignature p1) throws InvalidMidiDataException;
    
    public abstract MidiMessage[] getNoteOffMessageFor(final Note p0, final KeySignature p1) throws InvalidMidiDataException;
    
    public abstract MidiMessage[] getMidiMessagesFor(final Tempo p0) throws InvalidMidiDataException;
    
    protected static long getNoteLengthInTicks(final Note note) {
        short noteLength = note.getDuration();
        if (note.isBeginningTie() && note.getTieDefinition().getEnd() != null) {
            noteLength += ((Note)note.getTieDefinition().getEnd()).getDuration();
        }
        final float numberOfQuarterNotesInThisNote = noteLength / 48.0f;
        final float lengthInTicks = 48.0f * numberOfQuarterNotesInThisNote;
        return (long)lengthInTicks;
    }
    
    public static long getNoteLengthInTicks(final MultiNote note) {
        Note[] notes = note.toArray();
        notes = MultiNote.excludeTiesEndings(notes);
        if (notes != null) {
            return getNoteLengthInTicks(note.getShortestNote());
        }
        return 0L;
    }
    
    public static byte getMidiNoteNumber(final Note note, final KeySignature key) {
        final byte heigth = note.getStrictHeight();
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
