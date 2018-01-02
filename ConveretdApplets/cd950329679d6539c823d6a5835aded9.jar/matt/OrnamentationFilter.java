// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import matt.dsp.FastFourierTransform;
import java.util.Vector;

public class OrnamentationFilter
{
    private Vector<TranscribedNote> transcribedNotes;
    private int sampleRate;
    private float[] signal;
    
    public OrnamentationFilter(final Vector<TranscribedNote> transcribedNotes, final int sampleRate, final float[] signal) {
        this.transcribedNotes = transcribedNotes;
        this.sampleRate = sampleRate;
        this.signal = signal;
    }
    
    public Vector<TranscribedNote> getTranscribedNotes() {
        return this.transcribedNotes;
    }
    
    public void setTranscribedNotes(final Vector<TranscribedNote> transcribedNotes) {
        this.transcribedNotes = transcribedNotes;
    }
    
    TranscribedNote[] filter() {
        float quaver = 0.0f;
        boolean finished = false;
        final float ocahWindow = MattProperties.getFloat("ocahWindow");
        int start = 0;
        int end = 0;
        int filtered = 0;
        int inserted = 0;
        final int original = this.transcribedNotes.size();
        int after = 0;
        if (MattProperties.getString("searchMethod").equalsIgnoreCase("bryan")) {
            while (!finished) {
                float currentWindow = 0.0f;
                while (currentWindow < ocahWindow) {
                    currentWindow += this.transcribedNotes.get(end).getDuration();
                    if (++end == this.transcribedNotes.size()) {
                        finished = true;
                        break;
                    }
                    final TranscribedNote lastNote = this.transcribedNotes.get(this.transcribedNotes.size() - 1);
                    if (lastNote.getStart() + lastNote.getDuration() - this.transcribedNotes.get(end).getStart() <= ocahWindow) {
                        end = this.transcribedNotes.size();
                        finished = true;
                        break;
                    }
                }
                Logger.log("Looking at window " + start + " to " + end);
                float[] histData = new float[end - start];
                FuzzyHistogram fuzzyHistogram = new FuzzyHistogram();
                for (int i = start; i < end; ++i) {
                    final TranscribedNote note = this.transcribedNotes.elementAt(i);
                    histData[i - start] = note.getDuration();
                }
                quaver = fuzzyHistogram.calculatePeek(histData, 0.3f);
                Logger.log("Quaver length: " + quaver);
                for (int i = start; i < end; ++i) {
                    final TranscribedNote note = this.transcribedNotes.get(i);
                    note.setMultiple(calculateNearestMultiple(note.getDuration(), quaver));
                    Logger.log(i + "\t" + this.transcribedNotes.get(i));
                }
                if (MattProperties.getBoolean("ornamentationCompensation")) {
                    for (int i = start + 1; i < end; ++i) {
                        final TranscribedNote previous = this.transcribedNotes.elementAt(i - 1);
                        final TranscribedNote current = this.transcribedNotes.elementAt(i);
                        final int multiple = calculateNearestMultiple(current.getDuration(), quaver);
                        if (multiple == 0) {
                            if (i + 1 < this.transcribedNotes.size()) {
                                final TranscribedNote next = this.transcribedNotes.elementAt(i + 1);
                                next.setStart(current.getStart());
                                next.setUnmergedStart(next.getStart());
                                next.setUnmergedDuration(next.getDuration());
                                next.setDuration(next.getDuration() + current.getDuration());
                                Logger.log("Merging note: " + i + " " + current + " with next: " + (i + 1) + " " + next);
                            }
                            this.transcribedNotes.remove(i);
                            ++filtered;
                            --i;
                            --end;
                        }
                        else {
                            current.setMultiple(multiple);
                        }
                    }
                    if (calculateNearestMultiple(this.transcribedNotes.elementAt(start).getDuration(), quaver) == 0) {
                        this.transcribedNotes.elementAt(start + 1).setDuration(this.transcribedNotes.elementAt(start + 1).getDuration() + this.transcribedNotes.elementAt(start).getDuration());
                        this.transcribedNotes.remove(start);
                        --end;
                        ++filtered;
                    }
                }
                if (MattProperties.getBoolean("missedOnsetCompensation")) {
                    histData = new float[end - start];
                    fuzzyHistogram = new FuzzyHistogram();
                    for (int i = start; i < end; ++i) {
                        final TranscribedNote note = this.transcribedNotes.elementAt(i);
                        histData[i - start] = note.getDuration();
                    }
                    quaver = fuzzyHistogram.calculatePeek(histData, 0.3f);
                    int i = start;
                    while (i < end) {
                        final TranscribedNote current2 = this.transcribedNotes.elementAt(i);
                        final int multiple2 = calculateNearestMultiple(current2.getDuration(), quaver);
                        current2.setMultiple(multiple2);
                        if (multiple2 > 1) {
                            final float newDuration = current2.getDuration() / multiple2;
                            current2.setMultiple(1);
                            current2.setDuration(newDuration);
                            for (int m = 1; m < multiple2; ++m) {
                                final TranscribedNote newNote = new TranscribedNote(0.0f, current2.getStart() + m * newDuration, newDuration);
                                newNote.setSpelling("NEW");
                                newNote.setMultiple(1);
                                this.transcribedNotes.add(i + m, newNote);
                                ++inserted;
                                ++end;
                            }
                            for (int m = 0; m < multiple2; ++m) {
                                Logger.log("Recalculating energy and FFT for note: " + (m + i));
                                final TranscribedNote note2 = this.transcribedNotes.get(m + i);
                                final int signalStart = (int)(note2.getUnmergedStart() * this.sampleRate);
                                final int signalEnd = (int)((note2.getUnmergedStart() + note2.getUnmergedDuration()) * this.sampleRate);
                                final int signalLength = signalEnd - signalStart;
                                final int smallestPowerOf2 = FastFourierTransform.smallestPowerOf2(signalLength);
                                final FastFourierTransform fft = new FastFourierTransform();
                                final EnergyCalculator ec = new EnergyCalculator();
                                ec.setSignal(this.signal);
                                ec.setStart(signalStart);
                                ec.setEnd(signalEnd);
                                final float energy = ec.calculateAverageEnergy();
                                final int fftFrameSize = (int)Math.pow(2.0, smallestPowerOf2);
                                Logger.log("Performing FFT on frame size " + fftFrameSize);
                                final float[] fftFrame = new float[fftFrameSize];
                                final WindowFunction windowFunction = new WindowFunction();
                                windowFunction.setWindowType(2);
                                final float[] win = windowFunction.generate(fftFrameSize);
                                for (int j = 0; j < fftFrameSize; ++j) {
                                    fftFrame[j] = this.signal[signalStart + j] / 32768.0f * win[j];
                                }
                                final float[] fftOut = fft.fftMag(fftFrame, 0, fftFrameSize);
                                final PitchDetector pitchDetector = new PitchDetector();
                                float frequency = pitchDetector.maxPeek(fftOut, this.sampleRate, fftFrameSize);
                                Logger.log("Frequency by highest value: " + frequency);
                                frequency = pitchDetector.maxBryanFrequency(fftOut, this.sampleRate, fftFrameSize);
                                Logger.log("Frequency by Bryan's algorithm value: " + frequency);
                                note2.setFrequency(frequency);
                                note2.setEnergy(energy);
                            }
                        }
                        if (multiple2 == 0) {
                            ++i;
                        }
                        else {
                            i += multiple2;
                        }
                    }
                }
                start = end;
            }
            Logger.log("After Missed Onset Compensation");
        }
        else {
            Logger.log("Skipping onset post processing");
        }
        for (int k = 0; k < this.transcribedNotes.size(); ++k) {
            Logger.log(k + "\t" + this.transcribedNotes.get(k));
        }
        final TranscribedNote[] notes = new TranscribedNote[this.transcribedNotes.size()];
        this.transcribedNotes.copyInto(notes);
        after = this.transcribedNotes.size();
        TFLog.TFLog("filtered.txt", "" + original + "\t" + filtered + "\t" + inserted + "\t" + after);
        return notes;
    }
    
    public static int calculateNearestMultiple(final float duration, final float standard) {
        return Math.round(duration / standard);
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
}
