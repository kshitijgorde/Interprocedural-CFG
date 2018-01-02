// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Date;
import java.util.Vector;

public class NFDataBean implements Cloneable
{
    public static final int LOAD_NONE = 0;
    public static final int LOAD_NOW = 1;
    public static final int LOAD_LATER = 2;
    protected String name;
    protected String desc;
    protected String prefix;
    protected String keyword;
    protected long debugMode;
    protected String exprParam;
    protected NFContext ctxt;
    
    public NFDataBean() {
        this.name = "NFDataBean";
        this.desc = "Generic DataBean";
        this.prefix = "Data";
        this.keyword = "DATA";
        this.debugMode = 128L;
        this.exprParam = null;
        this.ctxt = NFContext.getDefault();
    }
    
    protected boolean inDebugMode() {
        return NFDebug.enabled(this.debugMode);
    }
    
    protected void debug(final String s) {
        if (this.inDebugMode()) {
            NFDebug.print(this.name + ": " + s);
        }
    }
    
    protected void statusMsg(final String s, final String s2) {
        NFDebug.print(s + " " + s2);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getKeyword() {
        return this.keyword;
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
    }
    
    public String getExprParam() {
        return this.exprParam;
    }
    
    public void setExprParam(final String exprParam) {
        this.exprParam = exprParam;
    }
    
    public StringBuffer getParam(final String s, final StringBuffer sb) {
        return sb;
    }
    
    public Vector getParams(final Vector vector) {
        return vector;
    }
    
    public void setContext(final NFContext ctxt) {
        if (ctxt != null) {
            this.ctxt = ctxt;
        }
    }
    
    public NFContext getContext() {
        return this.ctxt;
    }
    
    public int loadDataMode(final String s) {
        if (s.startsWith(this.prefix)) {
            return 0;
        }
        if (this.exprParam != null && this.exprParam.equals(s)) {
            return 2;
        }
        return 0;
    }
    
    public boolean loadData(final NFDataBeanObserver nfDataBeanObserver, final Object o) throws Exception {
        return false;
    }
    
    public void close() {
    }
    
    public boolean reloadNeeded(final Date date) {
        return true;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.toString(sb);
        return sb.toString();
    }
    
    public void toString(final StringBuffer sb) {
        sb.append(this.name);
        sb.append(": ");
        if (this.exprParam != null) {
            sb.append(this.exprParam);
        }
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (Exception ex) {
            NFDebug.print("NFDataBean.clone: " + ex);
        }
        return clone;
    }
}
