// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

public class Farbverwaltung
{
    private static final int[] farben;
    
    static {
        farben = new int[] { 16737744, 16706048, 173544, 6501685, 15466585, 2433023, 16026114, 12756222, 54731, 11766414, 2632067, 11600895, 16600912, 3241382, 12163588, 10093646, 6316128, 7959485, 16751052, 10066431, 6724095, 10092543, 13421721, 16750950, 16764108, 13421823, 10079487, 13434879, 16777164, 16764057 };
    }
    
    public static int getFarbeForPkNr(final int nr) {
        try {
            return Farbverwaltung.farben[nr];
        }
        catch (Exception e) {
            return (nr + 52) * 52 % 255 << 16 | (nr + 225) * 14 % 255 << 8 | (nr + 162) * 23 % 255;
        }
    }
    
    public static void main(final String[] args) {
        for (int i = 0; i < 500; ++i) {
            System.out.println("" + i + " --> " + Integer.toHexString(getFarbeForPkNr(i)).toUpperCase());
        }
    }
}
