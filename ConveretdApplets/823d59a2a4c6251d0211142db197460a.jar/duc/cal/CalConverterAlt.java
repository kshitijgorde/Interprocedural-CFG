// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal;

public class CalConverterAlt extends CalConverter
{
    static int[] TAYSON1789;
    static int[] MIENNAM1955;
    
    static {
        CalConverterAlt.TAYSON1789 = new int[] { 3331285, 5812896, 4371792, 3038548, 5393824, 3970480, 2643314, 5130672, 3582326, 6055088, 4614800, 3193508, 5663568, 4336544 };
        CalConverterAlt.MIENNAM1955 = new int[] { 3097971, 5526128, 3959144, 6351184, 5008032, 3583654, 5942096, 4606816, 3189476, 5678448, 4215392, 2683491, 5167440, 3824471, 6182560, 4757200, 3427797, 5917392, 4367568, 2938068, 5427792 };
    }
    
    public LunarYear getLunarYear(final int y) {
        if (y >= 1789 && y <= 1802) {
            return new LunarYear(y, CalConverterAlt.TAYSON1789[y - 1789]);
        }
        if (y >= 1955 && y <= 1975) {
            return new LunarYear(y, CalConverterAlt.MIENNAM1955[y - 1955]);
        }
        if (this.getFallbackConverter() == null) {
            return LunarYear.DUMMY;
        }
        return this.getFallbackConverter().getLunarYear(y);
    }
}
