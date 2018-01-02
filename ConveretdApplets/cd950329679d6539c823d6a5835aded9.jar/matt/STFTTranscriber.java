// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import matt.dsp.FastFourierTransform;
import matt.dsp.SpectralCentroid;
import java.util.Vector;

public class STFTTranscriber extends ODCFTranscriber
{
    public void transcribe() {
        final String spellings = "";
        String unfilteredSpellings = "";
        final Vector<TranscribedNote> notes = new Vector<TranscribedNote>();
        final ABCTranscriber abcTranscriber = new ABCTranscriber(this);
        abcTranscriber.makeScale("Major");
        final WindowFunction windowFunction = new WindowFunction();
        windowFunction.setWindowType(2);
        final float[] win = windowFunction.generate(this.frameSize);
        String lastSpelling = null;
        final EnergyCalculator ec = new EnergyCalculator();
        ec.setSignal(this.signal);
        final float signalEnergy = ec.calculateAverageEnergy();
        float noteEnergy = 0.0f;
        float frequency = 0.0f;
        float lastFrequency = 0.0f;
        final SpectralCentroid sc = new SpectralCentroid();
        sc.setSampleRate(this.sampleRate);
        sc.setFrameSize(this.frameSize);
        final float[] fftFrame = new float[this.frameSize];
        final float[] stdDevs = new float[this.signal.length / this.hopSize];
        int iStdDev = 0;
        this.gui.getProgressBar().setMaximum(this.signal.length);
        this.gui.getProgressBar().setValue(0);
        for (int i = 0; i < this.signal.length - this.frameSize; i += this.hopSize) {
            this.gui.getProgressBar().setValue(i);
            for (int j = 0; j < this.frameSize; ++j) {
                if (i + j > this.signal.length) {
                    Logger.log("Got here!");
                }
                fftFrame[j] = this.signal[i + j] / 32768.0f * win[j];
            }
            final FastFourierTransform fft = new FastFourierTransform();
            final float[] fftOut = fft.fftMag(fftFrame, 0, this.frameSize);
            sc.setFftMag(fftOut);
            final float stdDev = SD.sdFast(fftOut);
            stdDevs[iStdDev++] = stdDev;
            if (Boolean.parseBoolean("" + MattProperties.getString("drawFFTGraphs"))) {
                final Graph fftGraph = new Graph();
                fftGraph.setBounds(0, 0, 1000, 1000);
                fftGraph.getDefaultSeries().setScale(false);
                fftGraph.getDefaultSeries().setData(fftOut);
                MattGuiNB.instance().addFFTGraph(fftGraph, "" + i);
            }
            ec.setStart(i);
            ec.setEnd(i + this.frameSize);
            noteEnergy = ec.calculateAverageEnergy();
            final PitchDetector pitchDetector = new PitchDetector();
            final float mFrequency = frequency = pitchDetector.mikelsFrequency(fftOut, this.sampleRate, this.frameSize);
            final String spelling = MattABCTools.stripAll(abcTranscriber.spell(frequency));
            unfilteredSpellings += spelling;
            if (lastSpelling == null) {
                lastSpelling = spelling;
                lastFrequency = frequency;
            }
            else if (!spelling.equals(lastSpelling)) {
                final TranscribedNote note = new TranscribedNote();
                note.setSpelling(lastSpelling);
                note.setEnergy(noteEnergy);
                note.setFrequency(lastFrequency);
                Logger.log("Found note: " + lastSpelling + ", energy: " + noteEnergy + ", frequency: " + lastFrequency);
                if (notes.size() == 0) {
                    note.setStart(0.0f);
                    note.setUnmergedStart(0.0f);
                    note.setDuration(this.sampleToSeconds(i));
                    note.setUnmergedDuration(this.sampleToSeconds(i));
                }
                else {
                    final TranscribedNote lastNote = notes.get(notes.size() - 1);
                    final float start = lastNote.getStart() + lastNote.getDuration();
                    note.setStart(start);
                    note.setUnmergedStart(start);
                    final float duration = this.sampleToSeconds(i) - note.getStart();
                    note.setDuration(duration);
                    note.setUnmergedDuration(duration);
                }
                lastSpelling = spelling;
                lastFrequency = frequency;
                noteEnergy = 0.0f;
                notes.add(note);
            }
        }
        if (lastSpelling != null) {
            final TranscribedNote note2 = new TranscribedNote();
            note2.setSpelling(lastSpelling);
            note2.setFrequency(lastFrequency);
            note2.setEnergy(noteEnergy);
            if (notes.size() == 0) {
                note2.setStart(0.0f);
                note2.setUnmergedStart(0.0f);
                note2.setDuration(this.sampleToSeconds(this.signal.length));
                note2.setUnmergedDuration(this.sampleToSeconds(this.signal.length));
            }
            else {
                final TranscribedNote lastNote2 = notes.get(notes.size() - 1);
                final float start2 = lastNote2.getStart() + lastNote2.getDuration();
                note2.setStart(start2);
                note2.setUnmergedStart(start2);
                final float duration2 = this.sampleToSeconds(this.signal.length) - note2.getStart();
                note2.setDuration(duration2);
                note2.setUnmergedDuration(duration2);
            }
            notes.add(note2);
        }
        final OrnamentationFilter opp = new OrnamentationFilter(notes, this.sampleRate, this.signal);
        abcTranscriber.setTranscribedNotes(this.transcribedNotes = opp.filter());
        abcTranscriber.printScale();
        String notesString = null;
        notesString = abcTranscriber.convertToABC();
        this.printNotes();
        Logger.log(unfilteredSpellings);
        this.gui.getProgressBar().setValue(this.signal.length);
        this.gui.getTxtABC().setText(notesString);
        Logger.log("Done");
    }
}
