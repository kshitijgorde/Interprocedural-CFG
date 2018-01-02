// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import netcharts.util.NFParamServ;
import netcharts.util.NFDataBeanObserver;
import netcharts.util.NFKeyValue;
import netcharts.util.NFParamDef;
import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFDataBean;

public class NFParamBean extends NFDataBean
{
    private String a;
    private int b;
    private String c;
    private StringBuffer d;
    
    public NFParamBean() {
        this.a = null;
        this.b = -1;
        this.c = null;
        this.d = new StringBuffer();
        super.name = "NFParamBean";
        super.desc = "Parameter Server Processing";
        super.prefix = "Param";
        super.keyword = "SERVER";
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
        if (!nfParam.exists("ParamServer")) {
            final Vector<NFParamDef> vector = new Vector<NFParamDef>();
            vector.addElement(nfParam.defineString("ParamServerHost", ""));
            vector.addElement(nfParam.defineNumber("ParamServerPort", new Integer(80)));
            vector.addElement(nfParam.defineString("ParamServerArgs", ""));
            nfParam.defineTuple("ParamServer", vector).dataBean = this;
        }
        if (!nfParam.exists("SERVER")) {
            final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
            vector2.addElement(nfParam.defineString("SERVERHost", ""));
            vector2.addElement(nfParam.defineNumber("SERVERPort", new Integer(80)));
            vector2.addElement(nfParam.defineString("SERVERArgs", ""));
            nfParam.defineTuple("SERVER", vector2).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        Vector vector;
        if (super.exprParam.equals("ParamServer")) {
            vector = (Vector)nfParam.get("ParamServer");
        }
        else {
            vector = (Vector)nfParam.get("SERVER");
        }
        if (vector == null || vector.size() == 0) {
            throw new Exception("No server definition found");
        }
        this.a = vector.elementAt(0);
        this.b = ((Number)vector.elementAt(1)).intValue();
        this.c = vector.elementAt(2);
    }
    
    public StringBuffer getParam(final String s, StringBuffer d) {
        if (d == null) {
            d = this.d;
            d.setLength(0);
        }
        if (s.equals(super.exprParam)) {
            d.append("(\"");
            d.append(this.a);
            d.append("\",");
            d.append(this.b);
            d.append(",\"");
            d.append(this.c);
            d.append("\")");
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
        nfKeyValue.key = super.exprParam;
        nfKeyValue.value = this.getParam((String)nfKeyValue.key, new StringBuffer());
        vector.addElement(nfKeyValue);
        return vector;
    }
    
    public int loadDataMode(final String s) {
        return 2;
    }
    
    public boolean loadData(final NFDataBeanObserver nfDataBeanObserver, final Object o) throws Exception {
        String s = this.c;
        if (!super.exprParam.equals("ParamServer")) {
            s = super.exprParam;
        }
        this.debug("Connecting to " + this.a + ":" + this.b + "/" + s);
        nfDataBeanObserver.dataBeanLoadParams(this, null, new NFParamServ(this.a, this.b, s));
        return false;
    }
}
