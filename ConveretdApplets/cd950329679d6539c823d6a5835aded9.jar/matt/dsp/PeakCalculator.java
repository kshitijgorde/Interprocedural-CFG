// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

import matt.Graph;
import java.util.Vector;

public class PeakCalculator
{
    public static Vector<Integer> calculatePeaks2(final float[] data, final int border, final int howFar, final float thresholdNormal) {
        float thresholdValue = 0.0f;
        if (thresholdNormal > 0.0f) {
            for (int i = 0; i < howFar; ++i) {
                if (data[i] > thresholdValue) {
                    thresholdValue = data[i];
                }
            }
        }
        thresholdValue *= thresholdNormal;
        final Vector peaks = new Vector();
        if (howFar >= border) {
            for (int j = border; j < howFar - border; ++j) {
                boolean addPeak = true;
                if (data[j] >= thresholdValue) {
                    for (int k = 0; k < border; ++k) {
                        if (data[j] < data[j - k] || data[j] < data[j + k]) {
                            addPeak = false;
                            break;
                        }
                    }
                }
                else {
                    addPeak = false;
                }
                if (addPeak) {
                    peaks.add(new Integer(j));
                }
            }
        }
        return (Vector<Integer>)peaks;
    }
    
    public static Vector calculatePeaks(final float[] data, final int border, final int howFar, final float thresholdNormal) {
        float thresholdValue = 0.0f;
        if (thresholdNormal > 0.0f) {
            for (int i = 0; i < howFar; ++i) {
                if (data[i] > thresholdValue) {
                    thresholdValue = data[i];
                }
            }
        }
        thresholdValue *= thresholdNormal;
        final Vector peaks = new Vector();
        if (howFar >= border) {
            for (int j = border; j < howFar - border; ++j) {
                boolean addPeak = true;
                if (data[j] >= thresholdValue) {
                    for (int k = 0; k < border; ++k) {
                        if (data[j - k] <= data[j - k - 1] || data[j + k] <= data[j + k + 1]) {
                            addPeak = false;
                            break;
                        }
                    }
                }
                else {
                    addPeak = false;
                }
                if (addPeak) {
                    peaks.add(new Integer(j));
                }
            }
        }
        return peaks;
    }
    
    public static Vector<Integer> calculateTrough(final float[] data, final int border, final int howFar, final float thresholdNormal, final Graph g, final int sj) {
        final Vector<Integer> troughs = new Vector<Integer>();
        float thresholdValue = Float.MAX_VALUE;
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        if (thresholdNormal > 0.0f) {
            for (int i = 0; i < howFar; ++i) {
                if (data[i] < min) {
                    min = data[i];
                }
                else if (data[i] >= max) {
                    max = data[i];
                }
            }
        }
        final float range = max - min;
        thresholdValue = min + thresholdNormal * range;
        g.getDefaultSeries().addHorizontalLine(thresholdValue);
        System.out.println("Threshold: " + thresholdValue);
        if (howFar >= border) {
            for (int j = border; j < howFar; ++j) {
                boolean addPeak = true;
                for (int k = 0; k < border; ++k) {
                    if (data[j] > data[j - k]) {
                        addPeak = false;
                        break;
                    }
                }
                if (addPeak && data[j] <= thresholdValue) {
                    if (troughs.size() > 0 && j <= troughs.elementAt(troughs.size() - 1) + sj) {
                        troughs.set(troughs.size() - 1, new Integer(j));
                    }
                    else {
                        troughs.add(new Integer(j));
                    }
                }
            }
        }
        return troughs;
    }
}
