// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import com.stonewall.parser.Tuple;
import com.stonewall.parser.interpreter.Event;
import org.jdom.Text;
import org.jdom.Element;
import org.jdom.Attribute;
import java.util.ArrayList;
import org.xmodel.log.Log;
import com.stonewall.parser.Function;

class EvXPath implements Function
{
    static Log log;
    
    static {
        EvXPath.log = Builtin.log;
    }
    
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String p = this.path(args);
        final String nomatch = "no-match";
        final List<String> list = new ArrayList<String>();
        try {
            final Object model = this.node(ctx, args);
            for (final Object node : ctx.interpreter().xpath(p).selectNodes(model)) {
                if (node instanceof Attribute) {
                    list.add(((Attribute)node).getValue());
                }
                else if (node instanceof Element) {
                    list.add(((Element)node).getName());
                }
                else if (node instanceof Text) {
                    list.add(((Text)node).getText());
                }
                else if (node instanceof String) {
                    list.add((String)node);
                }
                else if (node instanceof Integer) {
                    list.add(String.valueOf(node));
                }
                else {
                    if (!(node instanceof Boolean)) {
                        continue;
                    }
                    list.add(String.valueOf(node));
                }
            }
            if (list.isEmpty()) {
                final String msg = "path (" + p + ") not matched";
                EvXPath.log.debug(msg);
                ctx.raiseEvent(Event.Severity.error, msg);
                return "no-match";
            }
            return (list.size() == 1) ? list.get(0) : Tuple.join(list);
        }
        catch (Exception e) {
            final String msg = "path (" + p + ") evaluation raised exception";
            EvXPath.log.debug(msg, e);
            ctx.raiseEvent(Event.Severity.error, msg, e);
            return "no-match";
        }
    }
    
    Object node(final Context ctx, final String[] args) throws Exception {
        return (args.length > 2 && args[1] != null) ? this.referenced(ctx, args[1]) : ctx.interpreter().model();
    }
    
    String path(final String[] args) {
        return (args.length > 2) ? args[2] : args[1];
    }
    
    Object referenced(final Context ctx, final String r) throws Exception {
        final Element node = ctx.interpreter().nodeReferences().get(r);
        if (node == null) {
            final String msg = "referenced (context) node (" + r + ") not-found";
            EvXPath.log.debug(msg);
            ctx.raiseEvent(Event.Severity.error, msg);
            throw new IllegalArgumentException(msg);
        }
        return node;
    }
}
