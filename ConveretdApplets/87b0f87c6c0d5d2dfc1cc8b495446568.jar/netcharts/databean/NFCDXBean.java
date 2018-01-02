// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import netcharts.util.NFDataBeanObserver;
import netcharts.util.NFKeyValue;
import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFDataBean;

public class NFCDXBean extends NFDataBean
{
    protected String cdxVariable;
    
    public NFCDXBean() {
        this.cdxVariable = null;
        super.name = "NFCDXBean";
        super.desc = "CDX Processing";
        super.prefix = "CdxVariable";
        super.keyword = "CDXVARIABLE";
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
        if (!nfParam.exists("CDXVARIABLE")) {
            nfParam.defineString("CDXVARIABLE", null).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        try {
            this.cdxVariable = (String)nfParam.get("CDXVARIABLE");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.debug("Exception in loadParams: " + ex.toString());
        }
    }
    
    public String getCDXVariable() {
        return this.cdxVariable;
    }
    
    public void setCDXVariable(final String cdxVariable) {
        this.cdxVariable = cdxVariable;
    }
    
    public StringBuffer getParam(final String s, StringBuffer sb) {
        if (sb == null) {
            sb = new StringBuffer();
        }
        if (super.exprParam != null && s.equals(super.exprParam)) {
            sb.append("CDXVARIABLE ");
            sb.append(this.cdxVariable);
            return sb;
        }
        return sb;
    }
    
    public Vector getParams(Vector vector) {
        if (vector == null) {
            vector = new Vector<NFKeyValue>();
        }
        if (super.exprParam != null) {
            final NFKeyValue nfKeyValue = new NFKeyValue();
            nfKeyValue.key = super.exprParam;
            nfKeyValue.value = this.getParam((String)nfKeyValue.key, null);
            vector.addElement(nfKeyValue);
        }
        return vector;
    }
    
    public int loadDataMode(final String s) {
        return 1;
    }
    
    public boolean loadData(final NFDataBeanObserver nfDataBeanObserver, final Object o) throws Exception {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.exprParam);
        sb.append(" = ");
        nfDataBeanObserver.dataBeanGetExtraParams(this);
        String s;
        try {
            s = nfDataBeanObserver.dataBeanGetExtraParams(this).get(this.cdxVariable);
        }
        catch (Exception ex) {
            s = null;
        }
        if (s != null) {
            sb.append(s);
        }
        if (!sb.toString().trim().endsWith(";")) {
            sb.append(";");
        }
        sb.append("\nUpdate;");
        nfDataBeanObserver.dataBeanLoadParams(this, o, sb.toString());
        return false;
    }
}
