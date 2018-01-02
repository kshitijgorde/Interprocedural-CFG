import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class qqmOYUhlRd implements Runnable
{
    private Socket pttxonkGiE;
    public static String XprTmKtNJQ;
    private PrintWriter TmohuHxQDh;
    private BufferedReader QiFoBOxtrx;
    static boolean uvnWSEnhZB;
    static boolean PaKZShdGyb;
    static boolean SExJrDKQCh;
    
    public qqmOYUhlRd(final Socket pttxonkGiE) {
        this.pttxonkGiE = null;
        this.TmohuHxQDh = null;
        this.QiFoBOxtrx = null;
        this.pttxonkGiE = pttxonkGiE;
    }
    
    @Override
    public void run() {
        try {
            this.TmohuHxQDh = new PrintWriter(this.pttxonkGiE.getOutputStream());
            this.QiFoBOxtrx = new BufferedReader(new InputStreamReader(this.pttxonkGiE.getInputStream()));
            while (!qqmOYUhlRd.uvnWSEnhZB) {
                qqmOYUhlRd.XprTmKtNJQ = "2NJwfucw-oymn-1MG1-gmtC-nCjzN77OeP9Z";
                this.TmohuHxQDh.println(qqmOYUhlRd.XprTmKtNJQ);
                this.TmohuHxQDh.flush();
                if (!this.QiFoBOxtrx.readLine().equals("connecte")) {
                    break;
                }
                qqmOYUhlRd.uvnWSEnhZB = true;
                if (!qqmOYUhlRd.uvnWSEnhZB) {
                    continue;
                }
                qqmOYUhlRd.PaKZShdGyb = true;
                Thread.sleep(5000L);
                if (!Runescape.DGmYrzpMRh()) {
                    continue;
                }
                this.TmohuHxQDh.println("true");
                this.TmohuHxQDh.flush();
            }
        }
        catch (IOException ex) {}
        catch (InterruptedException ex2) {}
    }
    
    static {
        qqmOYUhlRd.XprTmKtNJQ = null;
        qqmOYUhlRd.uvnWSEnhZB = false;
        qqmOYUhlRd.PaKZShdGyb = (null != null);
        qqmOYUhlRd.SExJrDKQCh = false;
    }
}
