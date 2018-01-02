// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.HashMap;
import java.util.Map;

public class Namespaces
{
    public static final Namespace cnns;
    public static final Namespace enns;
    public static final Namespace psns;
    public static final Namespace dmns;
    public static final Namespace jbns;
    public static final Namespace dspns;
    public static final Namespace rmins;
    public static final Namespace evtns;
    public static final Namespace rpns;
    public static final Namespace xsins;
    public static final Namespace diffns;
    static Map<String, Namespace> map;
    
    static {
        cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
        enns = Namespace.getNamespace("en", "http://www.stonewallnetworks.com/ns/entity");
        psns = Namespace.getNamespace("ps", "http://www.stonewallnetworks.com/ns/ps");
        dmns = Namespace.getNamespace("dm", "http://www.stonewallnetworks.com/ns/dm");
        jbns = Namespace.getNamespace("jb", "http://www.stonewallnetworks.com/ns/job");
        dspns = Namespace.getNamespace("dsp", "http://www.stonewallnetworks.com/ns/dsp");
        rmins = Namespace.getNamespace("rmi", "http://www.stonewallnetworks.com/ns/rmi");
        evtns = Namespace.getNamespace("evt", "http://www.stonewallnetworks.com/ns/event");
        rpns = Namespace.getNamespace("rp", "http://www.stonewallnetworks.com/ns/report");
        xsins = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        diffns = Namespace.getNamespace("diff", "http://www.stonewallnetworks.com/ns/diff");
        (Namespaces.map = new HashMap<String, Namespace>()).put("cn", Namespaces.cnns);
        Namespaces.map.put("en", Namespaces.enns);
        Namespaces.map.put("ps", Namespaces.psns);
        Namespaces.map.put("dm", Namespaces.dmns);
        Namespaces.map.put("jb", Namespaces.jbns);
        Namespaces.map.put("dsp", Namespaces.dspns);
        Namespaces.map.put("rmi", Namespaces.rmins);
        Namespaces.map.put("evt", Namespaces.evtns);
        Namespaces.map.put("rp", Namespaces.rpns);
        Namespaces.map.put("xsi", Namespaces.xsins);
        Namespaces.map.put("diff", Namespaces.diffns);
    }
    
    public static Namespace getNamespace(final String prefix) {
        return Namespaces.map.get(prefix);
    }
}
