// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Vector;

public class FuzzyHistogram
{
    float[] data;
    
    float calculatePeek(final float[] data, final float fuzz) {
        float duration = 0.0f;
        final Vector candidateLengths = new Vector();
        for (int i = 0; i < data.length; ++i) {
            boolean found = false;
            for (int j = 0; j < candidateLengths.size(); ++j) {
                final Candidate current = candidateLengths.elementAt(j);
                final float upper = current.value * (1.0f + fuzz);
                final float lower = current.value * (1.0f - fuzz);
                if (data[i] >= lower && data[i] <= upper) {
                    found = true;
                    final Candidate candidate2 = current;
                    ++candidate2.count;
                    current.value = (current.value * current.count + data[i]) / ++current.count;
                    break;
                }
            }
            if (!found) {
                final Candidate newCandidate = new Candidate();
                newCandidate.value = data[i];
                newCandidate.count = 1;
                candidateLengths.add(newCandidate);
            }
        }
        int maxIndex = 0;
        int maxValue = 0;
        Logger.log("Histogram:");
        for (int k = 0; k < candidateLengths.size(); ++k) {
            Candidate candidate = candidateLengths.elementAt(k);
            Logger.log(candidate);
            if (candidate.count > maxValue) {
                maxIndex = k;
                maxValue = candidate.count;
            }
            candidate = candidateLengths.elementAt(maxIndex);
            duration = candidate.value;
        }
        return duration;
    }
}
