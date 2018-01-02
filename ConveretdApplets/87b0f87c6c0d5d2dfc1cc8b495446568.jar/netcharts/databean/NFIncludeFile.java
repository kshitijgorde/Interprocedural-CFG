// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import java.io.InputStream;
import netcharts.util.NFFile;
import netcharts.util.NFHttpClient;
import netcharts.util.NFContext;
import netcharts.util.NFUtil;
import netcharts.util.NFDataBeanObserver;
import netcharts.util.NFKeyValue;
import java.util.Vector;
import netcharts.util.NFToken;
import netcharts.util.NFParam;
import java.util.Date;
import java.net.URL;
import netcharts.util.NFDataBean;

public class NFIncludeFile extends NFDataBean
{
    private String a;
    private URL b;
    private Date c;
    private StringBuffer d;
    
    public NFIncludeFile() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new StringBuffer();
        super.name = "NFIncludeFile";
        super.desc = "Include File Processing";
        super.prefix = "Include";
        super.keyword = null;
        super.debugMode |= 0x40L;
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
        if (!nfParam.exists("IncludeFile")) {
            nfParam.defineString("IncludeFile", null).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        this.a = (String)nfParam.get("IncludeFile");
        if (this.a.startsWith("\"")) {
            this.a = this.a.substring(1, this.a.length() - 1);
        }
    }
    
    public StringBuffer getParam(final String s, StringBuffer d) {
        if (d == null) {
            d = this.d;
            d.setLength(0);
        }
        if (s.equals("IncludeFile")) {
            d.append("\"");
            NFToken.doEscapes(this.a, d, false);
            d.append("\"");
            return d;
        }
        d.append("ERROR");
        return d;
    }
    
    public Vector getParams(Vector vector) {
        if (vector == null) {
            vector = new Vector<NFKeyValue>();
        }
        final NFKeyValue nfKeyValue = new NFKeyValue();
        nfKeyValue.key = "IncludeFile";
        nfKeyValue.value = this.getParam("IncludeFile", new StringBuffer());
        vector.addElement(nfKeyValue);
        return vector;
    }
    
    public int loadDataMode(final String s) {
        return 1;
    }
    
    public boolean loadData(final NFDataBeanObserver nfDataBeanObserver, final Object o) throws Exception {
        try {
            this.b = NFUtil.getFileURL(NFUtil.resolvePath(this.a, super.ctxt), super.ctxt);
            if (this.inDebugMode()) {
                this.debug("+++++++++++++++++++++++");
                this.debug("filename  = " + this.a);
                this.debug("File URL  = " + this.b);
            }
            NFHttpClient nfHttpClient = null;
            InputStream inputStream;
            if (this.b.getProtocol().equalsIgnoreCase("file") || NFContext.getUserAgentType() == 0) {
                inputStream = this.b.openStream();
            }
            else {
                nfHttpClient = new NFHttpClient(super.ctxt);
                inputStream = nfHttpClient.getContentAsInputStream(this.b);
            }
            nfDataBeanObserver.dataBeanLoadParams(this, o, inputStream);
            inputStream.close();
            if (nfHttpClient == null) {
                this.c = NFFile.getLastModified(this.b);
            }
            else {
                this.c = nfHttpClient.getLastModifiedAsDate();
            }
            if (this.c == null) {
                this.c = new Date(System.currentTimeMillis());
            }
            if (this.inDebugMode()) {
                this.debug("Processing Done");
                this.debug("-----------------------");
            }
            return false;
        }
        catch (Exception ex) {
            String s = ex.getMessage();
            if (s == null || s.length() < 1) {
                s = ex.toString();
            }
            if (s.indexOf("Max Nesting") == -1) {
                this.debug("ERROR: " + s);
            }
            throw new Exception(s);
        }
    }
    
    public boolean reloadNeeded(Date c) {
        if (c == null) {
            c = this.c;
        }
        return NFFile.modifiedSince(this.b, c);
    }
    
    public void toString(final StringBuffer sb) {
        sb.append(super.name);
        sb.append(": ");
        sb.append(this.a);
    }
}
