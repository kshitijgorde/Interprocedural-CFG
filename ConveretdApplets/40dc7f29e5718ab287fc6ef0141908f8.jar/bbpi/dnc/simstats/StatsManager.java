// 
// Decompiled by Procyon v0.5.30
// 

package bbpi.dnc.simstats;

import java.net.MalformedURLException;
import java.net.URL;

public class StatsManager
{
    public static final String VERSION = "1.0.5.2";
    public static final int ID_SIMULADOR_HABITACAO_AJUSTE_SEU_CASO = 3;
    public static final int ID_SIMULADOR_CREDITO_PESSOAL = 11;
    public static final int ID_SIMULADOR_AUTOMOVEL_PVP = 1;
    public static final int ID_SIMULADOR_POUPANCA_FISCAL = 9;
    public static final int ID_SIMULADOR_SIPIE = 10;
    public static final int ID_SIMULADOR_RENTABILIDADE_FUNDOS_CARTEIRA = 12;
    public static final int ID_SIMULADOR_CGA = 13;
    public static final int ID_SIMULADOR_SS = 14;
    public static final int ID_SIMULADOR_PPR = 15;
    public static final String PARAM_SITEID = "siteId";
    public static final int NO_SITEID = 1000;
    private RecordSender sender;
    private boolean debug;
    private boolean enableStats;
    private boolean dontDisableOnError;
    
    public StatsManager(final URL url, final int n) {
        this(url, 1000, n, "");
    }
    
    public StatsManager(final URL url, final int n, final int n2) {
        this(url, n, n2, "");
    }
    
    public StatsManager(final URL url, final int n, final int n2, final String s) {
        this.debug = false;
        this.enableStats = true;
        this.dontDisableOnError = false;
        System.out.println("StatsManager " + getVERSION() + "; Sender " + RecordSender.getVERSION());
        try {
            final URL url2 = new URL(url.getProtocol(), url.getHost(), String.valueOf(url.getFile()) + "BPISimStats/statInsert.asp?");
            if (this.debug) {
                System.out.println(String.valueOf(url2.getHost()) + url2.getFile());
            }
            (this.sender = new RecordSender(n, n2, url2, s)).start();
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad Url. Stats OFF.");
        }
    }
    
    public void addSimulationRecord(final Record record) {
        if (!this.sender.hasErrorOcurred() || this.dontDisableOnError) {
            this.sender.addRecord(record);
        }
    }
    
    public static final String getVERSION() {
        return "1.0.5.2";
    }
    
    public boolean isDontDisableOnError() {
        return this.dontDisableOnError;
    }
    
    public void setDebug(final boolean b) {
        this.debug = b;
        this.sender.setDebug(b);
    }
    
    public void setDontDisableOnError(final boolean dontDisableOnError) {
        this.dontDisableOnError = dontDisableOnError;
    }
}
