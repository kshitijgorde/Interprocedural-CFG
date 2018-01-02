// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;

public class ABCTranscriber
{
    private static final float RANGE = 0.1f;
    public static final float RATIO = 1.0594631f;
    private TranscribedNote[] transcribedNotes;
    ODCFTranscriber transcriber;
    public static final int REEL = 0;
    public static final int JIG = 1;
    public static final int HORNPIPE = 2;
    public static final int MIDI_NOTES = 87;
    public static final int MIDI_START = 21;
    private pitch_model pitchModel;
    public static int[] NOTES_PER_BAR;
    public static int[] NOTES_PER_PART;
    private int tuneType;
    public static String[] noteNames;
    public static float[] knownFrequencies;
    public static float[] midiNotes;
    public static final int MIDI_OFFSET = 21;
    private float maxEnergy;
    private float averageEnergy;
    
    public String spell(final float frequency) {
        final float[] distance = new float[ABCTranscriber.knownFrequencies.length];
        for (int j = 0; j < ABCTranscriber.knownFrequencies.length; ++j) {
            final float difference = frequency - ABCTranscriber.knownFrequencies[j];
            distance[j] = difference * difference;
        }
        int minIndex = 0;
        float min = Float.MAX_VALUE;
        for (int i = 0; i < ABCTranscriber.knownFrequencies.length; ++i) {
            if (distance[i] < min) {
                minIndex = i;
                min = distance[i];
            }
        }
        return ABCTranscriber.noteNames[minIndex];
    }
    
    public TranscribedNote[] getTranscribedNotes() {
        return this.transcribedNotes;
    }
    
    public void setTranscribedNotes(final TranscribedNote[] transcribedNotes) {
        this.transcribedNotes = transcribedNotes;
    }
    
    public pitch_model getPitchModel() {
        return this.pitchModel;
    }
    
    public void setPitchModel(final pitch_model pitchModel) {
        this.pitchModel = pitchModel;
    }
    
    void testScale() {
        for (int i = 0; i < ABCTranscriber.knownFrequencies.length; ++i) {
            System.out.print(ABCTranscriber.noteNames[i] + "\t");
            TonePlayer.playTone(ABCTranscriber.knownFrequencies[i], 0.25f, 0.1f);
        }
    }
    
    boolean isWholeToneInterval(int n, final int[] intervals) {
        n %= 8;
        for (int i = 0; i < intervals.length; ++i) {
            if (n == intervals[i]) {
                return true;
            }
        }
        return false;
    }
    
    void makeScale(final String mode) {
        final int[] majorKeyIntervals = { 1, 2, 4, 5 };
        if (mode.equals("Major")) {
            if (this.pitchModel == pitch_model.FLUTE) {
                ABCTranscriber.knownFrequencies[0] = MattProperties.getFloat(MattProperties.getString("fundamentalNote")) / (float)Math.pow(1.0594631433486938, 12.0);
            }
            else {
                ABCTranscriber.knownFrequencies[0] = MattProperties.getFloat(MattProperties.getString("fundamentalNote"));
            }
            for (int i = 1; i < ABCTranscriber.knownFrequencies.length; ++i) {
                if (this.isWholeToneInterval(i, majorKeyIntervals)) {
                    ABCTranscriber.knownFrequencies[i] = ABCTranscriber.knownFrequencies[i - 1] * 1.0594631f * 1.0594631f;
                }
                else {
                    ABCTranscriber.knownFrequencies[i] = ABCTranscriber.knownFrequencies[i - 1] * 1.0594631f;
                }
            }
        }
        System.out.println("FREQUENCIES:");
        for (int i = 0; i < ABCTranscriber.knownFrequencies.length; ++i) {
            System.out.println(ABCTranscriber.noteNames[i] + "\t" + ABCTranscriber.knownFrequencies[i]);
        }
    }
    
    private void makeMidiNotes() {
        ABCTranscriber.midiNotes[0] = 27.5f;
        for (int i = 1; i < 87; ++i) {
            ABCTranscriber.midiNotes[i] = ABCTranscriber.midiNotes[i - 1] * 1.0594631f;
        }
    }
    
    public ABCTranscriber() {
        this.tuneType = 0;
        this.pitchModel = pitch_model.FLUTE;
    }
    
    public ABCTranscriber(final ODCFTranscriber transcriber) {
        this.tuneType = 0;
        this.transcribedNotes = transcriber.getTranscribedNotes();
        this.transcriber = transcriber;
        this.makeMidiNotes();
    }
    
    public String convertToMidi() {
        final StringBuffer ret = new StringBuffer();
        for (int i = 0; i < this.getTranscribedNotes().length; ++i) {
            final float[] distance = new float[87];
            for (int j = 0; j < 87; ++j) {
                final float difference = this.getTranscribedNotes()[i].getFrequency() - ABCTranscriber.midiNotes[j];
                distance[j] = difference * difference;
            }
            int minIndex = 0;
            float min = Float.MAX_VALUE;
            for (int k = 0; k < 87; ++k) {
                if (distance[k] < min) {
                    minIndex = k;
                    min = distance[k];
                }
            }
            ret.append("" + (minIndex + 21));
            if (i < this.getTranscribedNotes().length - 1) {
                ret.append(",");
            }
            this.getTranscribedNotes()[i].setMidiNote(minIndex + 21);
        }
        return ret.toString();
    }
    
    public String convertToParsons() {
        this.convertToMidi();
        final StringBuffer parsons = new StringBuffer();
        float previousNote = -1.0f;
        for (int i = 0; i < this.getTranscribedNotes().length; ++i) {
            final float currentNote = this.getTranscribedNotes()[i].getMidiNote();
            if (previousNote != -1.0f) {
                if (currentNote > previousNote) {
                    parsons.append("U");
                }
                else if (currentNote < previousNote) {
                    parsons.append("D");
                }
                else {
                    parsons.append("S");
                }
            }
            previousNote = currentNote;
        }
        return parsons.toString();
    }
    
    public String convertToABC() {
        this.calculatePitchModel();
        this.makeScale("Major");
        this.printScale();
        final StringBuffer sb = new StringBuffer();
        final float standardNote = this.calculateStandardNoteDuration();
        final EnergyCalculator ec = new EnergyCalculator();
        ec.setSignal(this.transcriber.getSignal());
        this.setMaxEnergy(ec.calculateMaxEnergy());
        this.setAverageEnergy(ec.calculateAverageEnergy());
        Logger.log("Max energy in signal: " + EnergyCalculator.formatEnergy(this.getMaxEnergy()));
        Logger.log("Average energy in signal: " + EnergyCalculator.formatEnergy(this.getAverageEnergy()));
        int quaverQ = 0;
        for (int i = 0; i < this.getTranscribedNotes().length; ++i) {
            boolean found = false;
            this.getTranscribedNotes()[i].setQuaverQ(quaverQ);
            if (this.isBreath(this.getTranscribedNotes()[i])) {
                Logger.log("Breath detected at frame: " + i);
                this.getTranscribedNotes()[i].setSpelling("z");
                if (sb.length() != 0) {
                    found = true;
                }
                else {
                    Logger.log("Ignoring breath before opening note");
                }
            }
            else {
                final String closest = this.spell(this.getTranscribedNotes()[i].getFrequency());
                found = true;
                this.getTranscribedNotes()[i].setSpelling(closest);
            }
            if (found) {
                if (sb.length() <= 0 || sb.charAt(sb.length() - 1) != 'z' || !this.getTranscribedNotes()[i].getSpelling().equals("z")) {
                    sb.append(this.getTranscribedNotes()[i].getSpelling());
                    int nearestMultiple = 0;
                    if (!this.transcribedNotes[i].getSpelling().equals("z")) {
                        nearestMultiple = this.getTranscribedNotes()[i].getMultiple();
                        if (nearestMultiple > 1) {
                            if (nearestMultiple > 3) {
                                Logger.log("Note of length " + nearestMultiple + " detected. Quantising at 3");
                                nearestMultiple = 3;
                            }
                            sb.append("" + nearestMultiple);
                        }
                    }
                    else {
                        nearestMultiple = 1;
                    }
                    quaverQ += nearestMultiple;
                    this.getTranscribedNotes()[i].setMultiple(nearestMultiple);
                    if (quaverQ % ABCTranscriber.NOTES_PER_BAR[this.tuneType] == 0) {}
                }
            }
            else {
                Logger.log("Ignoring: " + this.getTranscribedNotes()[i]);
            }
        }
        while (sb.charAt(sb.length() - 1) == 'z') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    
    private boolean isBreath(final TranscribedNote note) {
        final float threshold = this.averageEnergy * Float.parseFloat("" + ((Hashtable<K, Object>)MattProperties.instance()).get("breathThreshold"));
        if (note.getEnergy() < threshold) {
            Logger.log(note.getEnergy() + " energy is less than the threshold of " + threshold);
            return true;
        }
        if (note.getFrequency() < 100.0f) {
            Logger.log(note.getFrequency() + " frequency is less than 100hz");
            return true;
        }
        return false;
    }
    
    public int getTuneType() {
        return this.tuneType;
    }
    
    public void setTuneType(final int tuneType) {
        this.tuneType = tuneType;
    }
    
    public void printScale() {
        System.out.println("SCALE:");
        for (int i = 0; i < ABCTranscriber.noteNames.length; ++i) {
            Logger.log(ABCTranscriber.noteNames[i] + ": " + ABCTranscriber.knownFrequencies[i]);
        }
    }
    
    float calculateStandardNoteDuration() {
        float duration = 0.0f;
        final float[] histData = new float[this.getTranscribedNotes().length];
        final FuzzyHistogram fuzzyHistogram = new FuzzyHistogram();
        for (int i = 0; i < this.getTranscribedNotes().length; ++i) {
            histData[i] = this.getTranscribedNotes()[i].getDuration();
        }
        duration = fuzzyHistogram.calculatePeek(histData, 0.3f);
        return duration;
    }
    
    public float getMaxEnergy() {
        return this.maxEnergy;
    }
    
    public void setMaxEnergy(final float maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
    
    public float getAverageEnergy() {
        return this.averageEnergy;
    }
    
    public void setAverageEnergy(final float averageEnergy) {
        this.averageEnergy = averageEnergy;
    }
    
    public void calculatePitchModel() {
        int flute = 0;
        int whistle = 0;
        final float G5 = (float)(MattProperties.getFloat(MattProperties.getString("fundamentalNote")) * Math.pow(1.0594631433486938, 17.0));
        for (int i = 0; i < this.getTranscribedNotes().length; ++i) {
            if (!this.isBreath(this.getTranscribedNotes()[i])) {
                if (this.getTranscribedNotes()[i].getFrequency() < G5) {
                    ++flute;
                }
                else {
                    ++whistle;
                }
            }
        }
        this.pitchModel = ((flute >= whistle) ? pitch_model.FLUTE : pitch_model.WHISTLE);
        Logger.log("Using " + ((this.pitchModel == pitch_model.FLUTE) ? "flute " : "whistle ") + " pitch model");
    }
    
    public static void main(final String[] args) {
        final ABCTranscriber t = new ABCTranscriber();
        t.makeScale("Major");
        t.testScale();
    }
    
    static {
        ABCTranscriber.NOTES_PER_BAR = new int[] { 8, 6, 8 };
        ABCTranscriber.NOTES_PER_PART = new int[] { 64, 48, 64 };
        ABCTranscriber.noteNames = new String[] { "D,", "E,", "F,", "G,", "A,", "B,", "C", "C", "D", "E", "F", "G", "A", "B", "c", "c", "d", "e", "f", "g", "a", "b", "c'", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "c''", "d''" };
        ABCTranscriber.knownFrequencies = new float[ABCTranscriber.noteNames.length];
        ABCTranscriber.midiNotes = new float[87];
    }
    
    enum pitch_model
    {
        FLUTE, 
        WHISTLE;
    }
}
