// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import matt.dsp.FastFourierTransform;
import matt.dsp.PeakCalculator;
import java.util.Vector;

public class PitchDetector
{
    int maxFrameIndex(final float[] frame) {
        float maximumFrameValue = frame[0];
        int maxIndex = 0;
        for (int i = 1; i < frame.length; ++i) {
            if (frame[i] > maximumFrameValue) {
                maximumFrameValue = frame[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    float maxFreq(final int maxIndex, final int sampleRate, final int frameSize) {
        final float binWidth = sampleRate / frameSize;
        final float maximumFrequency = maxIndex * binWidth;
        return maximumFrequency;
    }
    
    float maxPeek(final float[] fftMag, final int sampleRate, final int frameSize) {
        final int maxIndex = this.maxFrameIndex(fftMag);
        return this.maxFreq(maxIndex, sampleRate, frameSize);
    }
    
    float maxFilter(final float[] fftMag, final int sampleRate, final int frameSize, final float startAt) {
        final float binWidth = sampleRate / frameSize;
        final float[] filterPower = new float[24];
        final float ratio = 1.0594631f;
        final float[] filter = new float[fftMag.length / 2];
        for (int i = 0; i < 24; ++i) {
            for (int filterIndex = 0; filterIndex < filter.length; ++filterIndex) {
                if (filterIndex * binWidth % startAt == 0.0f) {
                    filter[filterIndex] = 1.0f;
                }
            }
        }
        return 0.0f;
    }
    
    public float maxHistogram(final float[] fftMag, final int sampleRate, final int frameSize) {
        Vector peeks = new Vector();
        final float binWidth = sampleRate / frameSize;
        final int pitchPeek = Integer.parseInt("" + ((Hashtable<K, Object>)MattProperties.instance()).get("pitchPeek"));
        peeks = PeakCalculator.calculatePeaks(fftMag, pitchPeek, fftMag.length / 2, 0.5f);
        final float[] intervals = new float[peeks.size() - 1];
        for (int i = 1; i < peeks.size(); ++i) {
            intervals[i - 1] = peeks.elementAt(i) - peeks.elementAt(i - 1) * binWidth;
        }
        final SimpleHistogram histogram = new SimpleHistogram();
        histogram.setData(intervals);
        return histogram.calculatePeek();
    }
    
    public float maxBryanFrequency(final float[] fftMag, final int sampleRate, final int frameSize) {
        final PeakCalculator pk = new PeakCalculator();
        final int pitchPeek = Integer.parseInt("" + ((Hashtable<K, Object>)MattProperties.instance()).get("pitchPeek"));
        final Vector peeks = PeakCalculator.calculatePeaks(fftMag, pitchPeek, fftMag.length, 0.5f);
        final float binWidth = sampleRate / frameSize;
        float averageDerivitive = 0.0f;
        float prev = 0.0f;
        final int numPeeksToCheck = (peeks.size() <= 2) ? peeks.size() : 2;
        for (int i = 0; i < numPeeksToCheck; ++i) {
            final int inx = peeks.elementAt(i);
            final float f = inx * binWidth;
            if (i > 0) {
                final float der = f - prev;
                averageDerivitive += der;
            }
            prev = f;
            Logger.log(f);
        }
        if (peeks.size() > 1) {
            averageDerivitive /= numPeeksToCheck - 1;
            return averageDerivitive;
        }
        if (peeks.size() == 0) {
            return this.maxPeek(fftMag, sampleRate, frameSize);
        }
        final int inx2 = peeks.elementAt(0);
        final float f2 = inx2 * binWidth;
        return f2;
    }
    
    public float cepstrumFrequency(final float[] fftMag, final int sampleRate, final int frameSize) {
        final float frequency = 0.0f;
        final FastFourierTransform fft = new FastFourierTransform();
        final float[] logMag = new float[fftMag.length];
        for (int i = 0; i < fftMag.length; ++i) {
            logMag[i] = (float)Math.log(fftMag[i]);
        }
        final Graph logGraph = new Graph();
        logGraph.setBounds(0, 0, 1000, 1000);
        logGraph.getDefaultSeries().setScale(false);
        logGraph.getDefaultSeries().setData(logMag);
        MattGuiNB.instance().addFFTGraph(logGraph, "LOG GRAPH");
        final float[] cepstrum = fft.fftLogMag(fftMag);
        final Graph fftGraph = new Graph();
        fftGraph.setBounds(0, 0, 1000, 1000);
        fftGraph.getDefaultSeries().setScale(false);
        fftGraph.getDefaultSeries().setData(cepstrum);
        MattGuiNB.instance().addFFTGraph(fftGraph, "CEPSTRUM");
        final int max = this.maxFrameIndex(cepstrum);
        return frequency;
    }
    
    public float mikelsFrequency(final float[] fftMag, final int sampleRate, final int frameSize) {
        float frequency = 0.0f;
        final int pitchPeek = Integer.parseInt("" + ((Hashtable<K, Object>)MattProperties.instance()).get("pitchPeek"));
        final Vector<Integer> peeks = PeakCalculator.calculatePeaks2(fftMag, pitchPeek, fftMag.length, 0.0f);
        Collections.sort(peeks, new EnergyComparitor(fftMag));
        final int numCandidates = 5;
        final int numHarmonics = 10;
        float maxEnergy = 0.0f;
        float maxCandidate = 0.0f;
        final float binWidth = sampleRate / frameSize;
        for (int i = 0; i < 5; ++i) {
            final int candidate = peeks.elementAt(i);
            float energy = 0.0f;
            for (int j = 0; j < 10; ++j) {
                final int harmonic = candidate + j * candidate;
                final float hLow = (int)(harmonic - 2.0f);
                final float hHigh = (int)(harmonic + 2.0f);
                float maxLittleBit = -1.0f;
                for (int k = (int)hLow; k <= (int)hHigh; ++k) {
                    if (k < fftMag.length && fftMag[k] > maxLittleBit) {
                        maxLittleBit = fftMag[k];
                    }
                }
                energy += maxLittleBit;
            }
            if (energy > maxEnergy) {
                maxEnergy = energy;
                maxCandidate = candidate;
            }
        }
        frequency = maxCandidate * binWidth;
        return frequency;
    }
    
    class EnergyComparitor implements Comparator
    {
        public float[] fftMag;
        
        public EnergyComparitor(final float[] fftMag) {
            this.fftMag = fftMag;
        }
        
        public int compare(final Object o1, final Object o2) {
            final Integer i1 = (Integer)o1;
            final Integer i2 = (Integer)o2;
            if (this.fftMag[i1] == this.fftMag[i2]) {
                return 0;
            }
            if (this.fftMag[i1] < this.fftMag[i2]) {
                return 1;
            }
            return -1;
        }
    }
}
