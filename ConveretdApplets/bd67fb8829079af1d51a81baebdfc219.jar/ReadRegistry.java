import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class ReadRegistry
{
    public native boolean WriteToRegistry(final String p0, final String p1);
    
    public native void DeleteFromRegistry();
    
    public static void main(final String[] array) {
        final ReadRegistry readRegistry = new ReadRegistry();
        final StringTokenizer stringTokenizer = new StringTokenizer(readRegistry.ReadFromRegistry(), ",");
        int n = 1;
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
            switch (n) {
                default: {
                    ++n;
                    continue;
                }
            }
        }
        readRegistry.WriteToRegistry("B4974", "W1497186462758370976147405168483");
        final String readFromRegistry = readRegistry.ReadFromRegistry();
        int n2 = 1;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(readFromRegistry, ",");
        while (stringTokenizer2.hasMoreTokens()) {
            stringTokenizer2.nextToken();
            switch (n2) {
                default: {
                    ++n2;
                    continue;
                }
            }
        }
    }
    
    public native String ReadFromRegistry();
    
    static {
        final String string = ReadSET.getEnv("windir") + "\\ReadReg.dll";
        System.out.println("<ExpolrerJar><init()> PATH : " + string);
        Runtime.getRuntime().load(string);
    }
}
