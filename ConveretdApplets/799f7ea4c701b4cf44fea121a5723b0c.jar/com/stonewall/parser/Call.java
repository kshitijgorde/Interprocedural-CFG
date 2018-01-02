// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Namespace;
import java.util.Iterator;
import com.stonewall.parser.jdom.SAXHandler;
import org.jdom.Attribute;
import org.jdom.Element;

public class Call extends Action
{
    Call(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean condition() {
        return true;
    }
    
    @Override
    boolean apply() {
        this.processAttributes();
        try {
            Element p = this.lookupProcedure();
            p = (Element)p.clone();
            final Procedure procedure = new Procedure(p, this.context());
            return procedure.call(this.args(this.root()));
        }
        catch (Exception e) {
            Call.log.error(e + " at: " + this.id());
            return true;
        }
    }
    
    Element lookupProcedure() throws Exception {
        Element p = null;
        String pn = this.root().getAttributeValue("fn", Parser.namespace);
        if (pn == null) {
            pn = this.root().getTextTrim();
        }
        if (pn == null) {
            throw new Exception("procedure not specified");
        }
        if (pn.indexOf(46) == -1) {
            p = this.context().procedures().get(pn);
        }
        else {
            p = this.context().globalProcedures().get(pn);
        }
        if (p == null) {
            final String m = "procedure '" + pn + "' not-found";
            throw new Exception(m);
        }
        return p;
    }
    
    Dictionary<String> args(final Element p) {
        final Dictionary<String> args = new Dictionary<String>();
        for (final Attribute a : p.getAttributes()) {
            final Namespace ns = a.getNamespace();
            if (!ns.equals((Object)SAXHandler.saxns)) {
                if (ns.equals((Object)Parser.namespace)) {
                    continue;
                }
                args.put(a.getName(), a.getValue());
            }
        }
        return args;
    }
}
