// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.notation.Fraction;

public class AbcToolkit
{
    public static byte convertToRepeatBarLine(final String barLine) {
        if (barLine.equals("[1")) {
            return 1;
        }
        if (barLine.equals("[2")) {
            return 2;
        }
        if (barLine.equals("|1")) {
            return 1;
        }
        if (barLine.equals(":|2")) {
            return 2;
        }
        return -1;
    }
    
    public static byte convertBrokenRhythm(final String brokenRhythm) {
        final byte br = (byte)brokenRhythm.length();
        if (brokenRhythm.equals("<")) {
            return (byte)(-br);
        }
        if (brokenRhythm.equals(">")) {
            return br;
        }
        return 0;
    }
    
    public static byte convertToOctaveTransposition(final String octave) {
        if (octave.charAt(0) == '\'') {
            return (byte)octave.length();
        }
        if (octave.charAt(0) == ',') {
            return (byte)(-octave.length());
        }
        return 0;
    }
    
    public static DurationDescription getAbsoluteDurationFor(final Fraction relativeDuration, final short defaultDuration) throws IllegalArgumentException {
        int absoluteDuration = -1;
        byte dotsNumber = 0;
        if (defaultDuration != 96 && defaultDuration != 48 && defaultDuration != 24 && defaultDuration != 12) {
            throw new IllegalArgumentException("Invalid default note duration " + defaultDuration);
        }
        absoluteDuration = defaultDuration * relativeDuration.getNumerator();
        absoluteDuration /= relativeDuration.getDenominator();
        int remainingDurTmp = 0;
        if (absoluteDuration >= 384) {
            throw new IllegalArgumentException("Cannot calculate the dots for " + relativeDuration + " with a default duration equals to " + defaultDuration + " : absolute note length was equal to " + absoluteDuration);
        }
        if (absoluteDuration >= 192) {
            remainingDurTmp = absoluteDuration - 192;
            absoluteDuration = 192;
        }
        else if (absoluteDuration >= 96) {
            remainingDurTmp = absoluteDuration - 96;
            absoluteDuration = 96;
        }
        else if (absoluteDuration >= 48) {
            remainingDurTmp = absoluteDuration - 48;
            absoluteDuration = 48;
        }
        else if (absoluteDuration >= 24) {
            remainingDurTmp = absoluteDuration - 24;
            absoluteDuration = 24;
        }
        else if (absoluteDuration >= 12) {
            remainingDurTmp = absoluteDuration - 12;
            absoluteDuration = 12;
        }
        else if (absoluteDuration >= 6) {
            remainingDurTmp = absoluteDuration - 6;
            absoluteDuration = 6;
        }
        else if (absoluteDuration >= 3) {
            remainingDurTmp = absoluteDuration - 3;
            absoluteDuration = 3;
        }
        if (remainingDurTmp != 0) {
            int durationRepresentedByDots = 0;
            int currentDur = absoluteDuration;
            while (durationRepresentedByDots != remainingDurTmp) {
                ++dotsNumber;
                currentDur /= 2;
                durationRepresentedByDots += currentDur;
                if (durationRepresentedByDots > remainingDurTmp) {
                    throw new IllegalArgumentException("Cannot calculate the dots for " + relativeDuration + " with a default duration equals to " + defaultDuration + " : absolute note length was equal to " + absoluteDuration);
                }
            }
        }
        return new DurationDescription((short)absoluteDuration, dotsNumber);
    }
    
    public static class DurationDescription
    {
        private short m_strictDuration;
        private byte m_dotsNb;
        
        DurationDescription(final short noteDuration, final byte dotsNb) {
            this.m_strictDuration = noteDuration;
            this.m_dotsNb = dotsNb;
        }
        
        public short getStrictDuration() {
            return this.m_strictDuration;
        }
        
        public byte countDots() {
            return this.m_dotsNb;
        }
    }
}
