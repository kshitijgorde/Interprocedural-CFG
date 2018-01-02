// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import java.util.Enumeration;
import java.awt.Color;
import matt.dsp.PeakCalculator;
import java.util.Vector;
import javax.sound.sampled.AudioFormat;
import matt.dsp.FastFourierTransform;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.SourceDataLine;
import matt.dsp.FrequencyDomainCombFilter;
import matt.dsp.TimeDomainCombFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class ODCFTranscriber
{
    int numFilters;
    Clip clip;
    byte[] audioData;
    protected int frameSize;
    protected int hopSize;
    protected int sampleRate;
    private int numSamples;
    protected String inputFile;
    AudioInputStream audioInputStream;
    private float[] oldPowers;
    private float[] powers;
    private String abcTranscription;
    private float silenceThreshold;
    protected GUI gui;
    protected TranscribedNote[] transcribedNotes;
    String defaultFundamental;
    private int dynamicThresholdTime;
    TimeDomainCombFilter[] tdFilters;
    FrequencyDomainCombFilter[] fdFilters;
    private Graph frameGraph;
    private Graph signalGraph;
    private Graph odfGraph;
    private SourceDataLine line;
    float staticThreshold;
    protected float[] signal;
    private boolean isPlaying;
    
    public ODCFTranscriber() {
        this.numFilters = 12;
        this.oldPowers = new float[this.numFilters];
        this.powers = new float[this.numFilters];
        this.silenceThreshold = 1500.0f;
        this.defaultFundamental = "D";
        this.dynamicThresholdTime = 100;
        this.tdFilters = new TimeDomainCombFilter[this.numFilters];
        this.fdFilters = new FrequencyDomainCombFilter[24];
        this.line = null;
        this.staticThreshold = 0.0f;
        this.isPlaying = false;
        this.frameSize = 2048;
        this.hopSize = (int)(this.frameSize * 0.25f);
    }
    
    public String getInputFile() {
        return this.inputFile;
    }
    
    public void setInputFile(final String inputFile) {
        this.inputFile = inputFile;
    }
    
    public void transcribea() {
        new Thread() {
            public void run() {
                ODCFTranscriber.this.transcribe();
            }
        }.start();
    }
    
    public void loadAudio() {
        MattProperties.setString("fundamentalNote", "D");
        try {
            final File soundFile = new File(this.inputFile);
            Logger.log("Processing: " + soundFile.getName());
            final int iK = soundFile.getName().indexOf("[");
            if (iK > -1) {
                final int iKK = soundFile.getName().indexOf("]");
                final String fundamentalNote = soundFile.getName().substring(iK + 1, iKK);
                MattProperties.setString("fundamentalNote", fundamentalNote);
            }
            this.audioInputStream = null;
            this.audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            final AudioFormat format = this.audioInputStream.getFormat();
            this.numSamples = (int)this.audioInputStream.getFrameLength();
            Logger.log("Length of the stream in samples: " + this.numSamples);
            Logger.log("Loading the signal...");
            this.audioData = new byte[this.numSamples * 2];
            this.signal = new float[this.numSamples];
            this.audioInputStream.read(this.audioData, 0, this.numSamples * 2);
            this.sampleRate = (int)format.getSampleRate();
            final boolean bigEndian = format.isBigEndian();
            this.gui.getProgressBar().setValue(0);
            this.gui.getProgressBar().setMaximum(this.numSamples);
            for (int signalIndex = 0; signalIndex < this.numSamples; ++signalIndex) {
                this.signal[signalIndex] = (this.audioData[signalIndex * 2 + 1] << 8) + this.audioData[signalIndex * 2];
                this.gui.getProgressBar().setValue(signalIndex);
            }
            final FastFourierTransform fft = new FastFourierTransform();
            final float[] ffts = fft.fftMag(this.signal, 0, this.frameSize);
            final PitchDetector pd = new PitchDetector();
            final float freq = pd.maxPeek(ffts, this.sampleRate, this.frameSize);
            Logger.log("Removing silence at the start...");
            this.removeSilence();
            Logger.log("Graphing...");
            if (Boolean.parseBoolean("" + MattProperties.getString("drawSignalGraphs"))) {
                this.signalGraph.getDefaultSeries().setData(this.signal);
                this.signalGraph.getDefaultSeries().setGraphType(0);
                this.signalGraph.repaint();
            }
            Logger.log("Done.");
        }
        catch (Exception e) {
            Logger.log("Could not load audio file " + this.inputFile);
            e.printStackTrace();
        }
    }
    
    public void removeSilence() {
        final float average = 0.0f;
        int frame;
        int i;
        for (frame = 512, i = 0; i < this.signal.length; i += frame) {
            float frameAverage = 0.0f;
            int j;
            for (j = 0, j = 0; j < frame && j + i < this.signal.length; ++j) {
                frameAverage += Math.abs(this.signal[i + j]);
            }
            frameAverage /= j;
            if (frameAverage > this.getSilenceThreshold()) {
                break;
            }
        }
        final int newSize = this.signal.length - i;
        if (newSize > 0) {
            final float[] newSignal = new float[newSize];
            for (int k = 0; k < newSignal.length; ++k) {
                newSignal[k] = this.signal[k + i];
            }
            Logger.log("" + i / this.sampleRate + " seconds of silence removed from the start");
            this.signal = newSignal;
            this.numSamples = this.signal.length;
        }
        else {
            Logger.log("No audio found!");
            this.signal = null;
            this.numSamples = 0;
        }
    }
    
    public void transcribe() {
        final File soundFile = new File(this.inputFile);
        Logger.log("Processing: " + soundFile.getName());
        final int iK = soundFile.getName().indexOf("[");
        if (iK > -1) {
            final int iKK = soundFile.getName().indexOf("]");
            final String fundamentalNote = soundFile.getName().substring(iK + 1, iKK);
            MattProperties.setString("fundamentalNote", fundamentalNote);
        }
        this.gui.setTitle(this.getInputFile());
        this.gui.enableButtons(false);
        try {
            this.dynamicThresholdTime = Integer.parseInt(((Hashtable<K, Object>)MattProperties.instance()).get("DynamicThresholdTime").toString());
            Logger.log("Configuring filters...");
            this.configureFilters();
            final int numHops = this.numSamples / this.hopSize;
            final int odfSize = numHops - 1;
            final float[] frame = new float[this.frameSize];
            final float[] odf = new float[odfSize];
            this.signalGraph.getDefaultSeries().clearLines();
            Logger.log("Calculating harmonicity...");
            this.gui.getProgressBar().setValue(0);
            this.gui.getProgressBar().setMaximum(numHops);
            int currentSample = 0;
            int hopIndex = 0;
            int odfIndex = 0;
            for (hopIndex = 0; hopIndex < numHops; ++hopIndex) {
                this.gui.getProgressBar().setValue(hopIndex);
                if (hopIndex * this.hopSize + this.frameSize > this.numSamples) {
                    break;
                }
                for (int frameIndex = 0; frameIndex < this.frameSize; ++frameIndex) {
                    currentSample = hopIndex * this.hopSize + frameIndex;
                    frame[frameIndex] = this.signal[currentSample];
                }
                for (int i = 0; i < this.tdFilters.length; ++i) {
                    this.tdFilters[i].setFrame(frame);
                    this.powers[i] = this.tdFilters[i].calculateHarmonicity();
                    if (hopIndex > 0) {
                        final float[] array = odf;
                        final int n = odfIndex;
                        array[n] += (float)Math.pow(this.powers[i] - this.oldPowers[i], 2.0);
                    }
                    this.oldPowers[i] = this.powers[i];
                }
                if (hopIndex > 0) {
                    ++odfIndex;
                }
                if (Boolean.parseBoolean("" + MattProperties.getString("drawFrameGraphs"))) {
                    this.frameGraph.getDefaultSeries().setScale(false);
                    this.frameGraph.getDefaultSeries().setData(frame);
                    this.frameGraph.repaint();
                }
            }
            Logger.log("Calculating onsets...");
            Vector<Integer> onsetsVector = new Vector<Integer>();
            onsetsVector = (Vector<Integer>)PeakCalculator.calculatePeaks(odf, 1, odf.length, 0.0f);
            Logger.log("Calculating dynamic threshold...");
            final float[] odfThreshold = this.calculateDynamicThreshold(odf, this.dynamicThresholdTime);
            if (Boolean.parseBoolean("" + MattProperties.getString("drawODFGraphs"))) {
                this.odfGraph.clear();
                this.odfGraph.getDefaultSeries().setScale(true);
                this.odfGraph.setScalingFactor(MattProperties.getFloat("scaleODFFactor"));
                this.odfGraph.getDefaultSeries().setData(odf);
                this.odfGraph.getDefaultSeries().addHorizontalLine(this.staticThreshold);
                this.odfGraph.getDefaultSeries().setPlotPoints(true);
                final Series odfThresholdSeries = new Series(this.odfGraph);
                odfThresholdSeries.setScale(false);
                odfThresholdSeries.setData(odfThreshold);
                odfThresholdSeries.setMin(this.odfGraph.getDefaultSeries().getMin());
                odfThresholdSeries.setMax(this.odfGraph.getDefaultSeries().getMax());
                odfThresholdSeries.setGraphType(1);
                odfThresholdSeries.setSeriesColour(Color.BLUE);
                this.odfGraph.addSeries(odfThresholdSeries);
            }
            this.removeSpuriousOnsets(onsetsVector, odfThreshold, odf);
            final float[] odfSignal = new float[onsetsVector.size() + 2];
            odfSignal[0] = 0.0f;
            int odfSignalIndex = 1;
            this.gui.getProgressBar().setValue(0);
            this.gui.getProgressBar().setMaximum(onsetsVector.size());
            for (int j = 0; j < onsetsVector.size(); ++j) {
                this.gui.getProgressBar().setValue(j);
                final int index = onsetsVector.elementAt(j);
                final int signalIndex = this.odfIndexToSignal(index);
                odfSignal[odfSignalIndex++] = signalIndex;
            }
            odfSignal[odfSignal.length - 1] = this.signal.length - 1;
            if (MattProperties.getBoolean("drawODFGraphs")) {
                final Enumeration en = onsetsVector.elements();
                while (en.hasMoreElements()) {
                    final int index = en.nextElement();
                    this.odfGraph.getDefaultSeries().addVerticalLine(index);
                }
                this.odfGraph.repaint();
            }
            for (int j = 0; j < odfSignal.length; ++j) {
                this.signalGraph.getDefaultSeries().addVerticalLine2(odfSignal[j]);
            }
            this.signalGraph.repaint();
            this.transcribedNotes = this.calculateNotesUsingFFT(odfSignal, this.signal, this.sampleRate);
            final ABCTranscriber abcTranscriber = new ABCTranscriber(this);
            abcTranscriber.printScale();
            String notes = null;
            if (MattProperties.getString("searchMethod").equalsIgnoreCase("bryan")) {
                notes = abcTranscriber.convertToABC();
            }
            if (MattProperties.getString("searchMethod").equalsIgnoreCase("parsons")) {
                notes = abcTranscriber.convertToParsons();
            }
            if (MattProperties.getString("searchMethod").equalsIgnoreCase("semex")) {
                notes = abcTranscriber.convertToMidi();
            }
            if (MattProperties.getString("mode").equals("client")) {
                this.gui.getTxtABC().setText("");
                this.gui.getTxtABC().append(notes);
            }
            this.abcTranscription = notes;
            Logger.log("Notes after onset post processing:");
            this.printNotes();
            this.gui.enableButtons(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.gui.enableButtons(true);
        }
        MattProperties.setString("fundamentalNote", this.defaultFundamental);
        this.gui.getProgressBar().setValue(0);
        Logger.log("Done.");
    }
    
    private int odfIndexToSignal(final int odfIndex) {
        return odfIndex * this.hopSize + this.hopSize * 3;
    }
    
    private void removeSpuriousOnsets(final Vector onsetsVector, final float[] odfThreshold, final float[] odf) {
        final int dynamicThresholdSamples = (int)(this.sampleRate * (this.dynamicThresholdTime / 1000.0f));
        final int dynamicThresholdWidth = dynamicThresholdSamples / this.hopSize;
        for (int i = 0; i < onsetsVector.size(); ++i) {
            final int onsetIndex = onsetsVector.elementAt(i);
            final int thresholdIndex = (int)Math.floor(onsetIndex / dynamicThresholdWidth);
            if (thresholdIndex > odfThreshold.length - 1) {
                onsetsVector.remove(i--);
            }
            else if (odf[onsetIndex] < odfThreshold[thresholdIndex]) {
                onsetsVector.remove(i--);
            }
        }
    }
    
    private TranscribedNote[] calculateNotesUsingFFT(final float[] odfSignal, final float[] signal, final int sampleRate) {
        Logger.log("Calculating frequencies of " + (odfSignal.length - 1) + " notes");
        final Vector<TranscribedNote> notes = new Vector<TranscribedNote>();
        this.gui.clearFFTGraphs();
        final FastFourierTransform fft = new FastFourierTransform();
        final EnergyCalculator ec = new EnergyCalculator();
        ec.setSignal(signal);
        this.gui.getProgressBar().setValue(0);
        this.gui.getProgressBar().setMaximum(odfSignal.length);
        for (int i = 1; i < odfSignal.length; ++i) {
            this.gui.getProgressBar().setValue(i);
            final int signalStart = (int)odfSignal[i - 1];
            final int signalEnd = (int)odfSignal[i];
            final int signalLength = signalEnd - signalStart;
            final int smallestPowerOf2 = FastFourierTransform.smallestPowerOf2(signalLength);
            Logger.log("Note: " + (i - 1));
            ec.setStart(signalStart);
            ec.setEnd(signalEnd);
            final float energy = ec.calculateAverageEnergy();
            Logger.log("Energy: " + EnergyCalculator.formatEnergy(energy));
            final int fftFrameSize = (int)Math.pow(2.0, smallestPowerOf2);
            Logger.log("Performing FFT " + (i - 1) + " on frame size " + fftFrameSize);
            final float[] fftFrame = new float[fftFrameSize];
            final WindowFunction windowFunction = new WindowFunction();
            windowFunction.setWindowType(2);
            final float[] win = windowFunction.generate(fftFrameSize);
            for (int j = 0; j < fftFrameSize; ++j) {
                fftFrame[j] = signal[signalStart + j] / 32768.0f * win[j];
            }
            final float[] fftOut = fft.fftMag(fftFrame, 0, fftFrameSize);
            final PitchDetector pitchDetector = new PitchDetector();
            float frequency;
            if (MattProperties.getString("pitchDetector").equals("bryan")) {
                final float bFrequency = pitchDetector.maxBryanFrequency(fftOut, sampleRate, fftFrameSize);
                Logger.log("Frequency by Bryan's algorithm value: " + bFrequency);
                frequency = bFrequency;
            }
            else if (MattProperties.getString("pitchDetector").equals("mikel")) {
                final float mFrequency = pitchDetector.mikelsFrequency(fftOut, sampleRate, fftFrameSize);
                Logger.log("Frequency by Mikels's algorithm value: " + mFrequency);
                frequency = mFrequency;
            }
            else {
                final float hFrequency = pitchDetector.maxPeek(fftOut, sampleRate, fftFrameSize);
                Logger.log("Frequency by highest value: " + hFrequency);
                frequency = hFrequency;
            }
            final float onset = signalStart / sampleRate;
            final float duration = signalLength / sampleRate;
            final TranscribedNote newNote = new TranscribedNote(frequency, onset, duration);
            newNote.setEnergy(energy);
            notes.addElement(newNote);
            if (Boolean.parseBoolean("" + MattProperties.getString("drawFFTGraphs"))) {
                final Graph fftGraph = new Graph();
                fftGraph.setBounds(0, 0, 1000, 1000);
                fftGraph.getDefaultSeries().setScale(false);
                fftGraph.getDefaultSeries().setData(fftOut);
                MattGuiNB.instance().addFFTGraph(fftGraph, "" + newNote.getStart());
            }
            Logger.log("");
        }
        final OrnamentationFilter opp = new OrnamentationFilter(notes, sampleRate, signal);
        final TranscribedNote[] postProcessed = opp.filter();
        for (int k = 0; k < postProcessed.length; ++k) {
            final float start = postProcessed[k].getStart();
            final float sigStart = start * sampleRate;
            this.signalGraph.getDefaultSeries().addVerticalLine(sigStart);
        }
        return postProcessed;
    }
    
    public void printNotes() {
        for (int i = 0; i < this.transcribedNotes.length; ++i) {
            Logger.log(this.transcribedNotes[i]);
        }
    }
    
    public int getFrameSize() {
        return this.frameSize;
    }
    
    public void setFrameSize(final int frameSize) {
        this.frameSize = frameSize;
    }
    
    public int getHopSize() {
        return this.hopSize;
    }
    
    public void setHopSize(final int hopSize) {
        this.hopSize = hopSize;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public int getNumSamples() {
        return this.numSamples;
    }
    
    public void setNumSamples(final int numSamples) {
        this.numSamples = numSamples;
    }
    
    private void configureFilters() {
        final float ratio = 1.0594631f;
        float frequency = MattProperties.getFloat(MattProperties.getString("fundamentalNote")) / (float)Math.pow(1.0594631433486938, 12.0);
        System.out.println("FILTERS:");
        for (int i = 0; i < this.tdFilters.length; ++i) {
            (this.tdFilters[i] = new TimeDomainCombFilter()).setSampleRate(this.sampleRate);
            this.tdFilters[i].setFrequency((int)frequency);
            System.out.println(this.tdFilters[i].getFrequency() + "\t" + this.tdFilters[i].getDelay());
            frequency *= ratio;
        }
    }
    
    protected float sampleToSeconds(final int sample) {
        return sample / this.sampleRate;
    }
    
    private float[] calculateDynamicThreshold(final float[] odf, final int dynamicThresholdTime) {
        final int dynamicThresholdSamples = (int)(this.sampleRate * (dynamicThresholdTime / 1000.0f));
        final int dynamicThresholdWidth = dynamicThresholdSamples / this.hopSize;
        final int numThresholds = odf.length / dynamicThresholdWidth;
        final float[] odfThreshold = new float[numThresholds];
        final Stats odfStats = new Stats(odf);
        this.staticThreshold = odfStats.average();
        int odfThresholdIndex = 0;
        for (int i = 0; i < odf.length - dynamicThresholdWidth; i += dynamicThresholdWidth) {
            odfStats.setStart(i);
            odfStats.setEnd(i + dynamicThresholdWidth);
            odfThreshold[odfThresholdIndex] = this.staticThreshold + odfStats.standardDeviation();
            ++odfThresholdIndex;
        }
        return odfThreshold;
    }
    
    void playTranscription(final TranscribedNote[] transcribedNotes) {
        this.isPlaying = true;
        for (int i = 0; i < transcribedNotes.length && this.isPlaying; ++i) {
            TonePlayer.playTone(transcribedNotes[i].getFrequency(), transcribedNotes[i].getDuration(), 0.25f);
        }
        this.isPlaying = false;
    }
    
    void playOriginal() {
        new Thread() {
            public void run() {
                try {
                    if (ODCFTranscriber.this.line != null && ODCFTranscriber.this.line.isActive()) {
                        ODCFTranscriber.this.line.stop();
                    }
                    else {
                        final AudioFormat audioFormat = ODCFTranscriber.this.audioInputStream.getFormat();
                        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                        ODCFTranscriber.this.line = (SourceDataLine)AudioSystem.getLine(info);
                        ODCFTranscriber.this.line.open(audioFormat);
                        ODCFTranscriber.this.line.start();
                        ODCFTranscriber.this.line.write(ODCFTranscriber.this.audioData, 0, ODCFTranscriber.this.audioData.length);
                        ODCFTranscriber.this.line.drain();
                        ODCFTranscriber.this.line.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    
    void playTranscription() {
        this.playTranscription(this.transcribedNotes);
    }
    
    public GUI getGui() {
        return this.gui;
    }
    
    public void cleanup() {
        try {
            this.audioInputStream.close();
            this.audioInputStream = null;
            this.audioData = null;
            this.oldPowers = null;
            this.powers = null;
            this.transcribedNotes = null;
            this.tdFilters = null;
            this.fdFilters = null;
            this.signal = null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setGui(final GUI mattGui) {
        this.gui = mattGui;
        this.frameGraph = mattGui.getFrameGraph();
        this.signalGraph = mattGui.getSignalGraph();
        this.odfGraph = mattGui.getOdfGraph();
    }
    
    public int getDynamicThresholdTime() {
        return this.dynamicThresholdTime;
    }
    
    public void setDynamicThresholdTime(final int dynamicThresholdTime) {
        this.dynamicThresholdTime = dynamicThresholdTime;
    }
    
    public TranscribedNote[] getTranscribedNotes() {
        return this.transcribedNotes;
    }
    
    public float[] getSignal() {
        return this.signal;
    }
    
    public void setSignal(final float[] signal) {
        this.signal = signal;
        this.numSamples = signal.length;
    }
    
    public String getAbcTranscription() {
        return this.abcTranscription;
    }
    
    public void setAbcTranscription(final String abcTranscription) {
        this.abcTranscription = abcTranscription;
    }
    
    public boolean isIsPlaying() {
        return this.isPlaying;
    }
    
    public void setIsPlaying(final boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    public float getSilenceThreshold() {
        return this.silenceThreshold;
    }
    
    public void setSilenceThreshold(final float silenceThreshold) {
        this.silenceThreshold = silenceThreshold;
    }
}
