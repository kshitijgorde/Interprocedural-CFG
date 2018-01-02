// 
// Decompiled by Procyon v0.5.30
// 

package roulette;

import java.util.Vector;

public class Roulette
{
    public static int rouletteChooseOne(final Vector chooseFrom) {
        double Sum = 0.0;
        double rouletteBall = 0.0;
        double currentIntervalBegin = 0.0;
        final int length = chooseFrom.size();
        int i = 0;
        Sum = 0.0;
        while (i < length) {
            Sum += chooseFrom.elementAt(i);
            ++i;
        }
        rouletteBall = Math.random() * Sum;
        currentIntervalBegin = 0.0;
        i = 0;
        do {
            currentIntervalBegin += chooseFrom.elementAt(i);
            ++i;
        } while (currentIntervalBegin < rouletteBall);
        return i - 1;
    }
    
    private static int rouletteChooseOneTest(final Vector chooseFrom, final double rouletteBall) {
        double Sum = 0.0;
        double currentIntervalBegin = 0.0;
        final int length = chooseFrom.size();
        int i = 0;
        Sum = 0.0;
        while (i < length) {
            Sum += chooseFrom.elementAt(i);
            ++i;
        }
        currentIntervalBegin = 0.0;
        i = 0;
        do {
            currentIntervalBegin += chooseFrom.elementAt(i);
            ++i;
        } while (currentIntervalBegin < rouletteBall && i < length);
        return i - 1;
    }
    
    public static void main(final String[] args) {
        final Vector list = new Vector();
        list.addElement(new Double(0.001));
        list.addElement(new Double(0.002));
        for (int i = 0; i < 30; ++i) {
            System.out.println(rouletteChooseOne(list));
        }
    }
}
