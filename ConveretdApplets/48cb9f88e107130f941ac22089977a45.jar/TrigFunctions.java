// 
// Decompiled by Procyon v0.5.30
// 

class TrigFunctions
{
    static final double PI = 3.14159265359;
    public static double[] sinarray;
    public static double[] cosarray;
    
    public static void CreateTrigArrays() {
        int n = 0;
        do {
            TrigFunctions.sinarray[n] = Math.sin(n * 0.017453292519944444);
            TrigFunctions.cosarray[n] = Math.cos(n * 0.017453292519944444);
        } while (++n < 360);
    }
    
    static {
        TrigFunctions.sinarray = new double[360];
        TrigFunctions.cosarray = new double[360];
    }
}
