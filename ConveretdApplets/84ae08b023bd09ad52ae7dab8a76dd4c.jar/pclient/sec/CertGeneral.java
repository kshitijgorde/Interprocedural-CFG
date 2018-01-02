// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

public class CertGeneral
{
    public String cn;
    public String org;
    public String unit;
    public String state;
    public String country;
    public String serial;
    public String issued;
    public String validFrom;
    public String validTo;
    public String fingerprint;
    public int version;
    public String pubkey;
    public String sigAlgName;
    public String signature;
    public String xType;
    public String xClass;
    public String xName;
    public String sCipher;
    public String sProto;
    
    public CertGeneral() {
        this.cn = "";
        this.org = "";
        this.unit = "";
        this.state = "";
        this.country = "";
        this.serial = "";
        this.issued = "";
        this.validFrom = "";
        this.validTo = "";
        this.fingerprint = "";
        this.version = -1;
        this.pubkey = "";
        this.sigAlgName = "";
        this.signature = "";
        this.xType = "";
        this.xClass = "";
        this.xName = "";
        this.sCipher = "";
        this.sProto = "";
    }
    
    public String toString() {
        return " cn=" + this.cn + " org=" + this.org + " unit=" + this.unit + " state=" + this.state + " country=" + this.country + " validFrom=" + this.validFrom + " validTo=" + this.validTo + " serial=" + this.serial + " xName " + this.xName + " xType " + this.xType + " xClass " + this.xClass + " sCipher " + this.sCipher + " sProto " + this.sProto;
    }
}
