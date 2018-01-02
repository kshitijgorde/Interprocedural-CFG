// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Vector;
import org.apache.xmlrpc.XmlRpcServer;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import org.jdom.Element;
import java.util.Map;
import java.util.Set;
import org.apache.xmlrpc.XmlRpcHandler;

class RpcEmulation extends Emulation implements XmlRpcHandler
{
    final Set<String> classes;
    final Map<String, RPC> map;
    
    RpcEmulation(final Element root) {
        super(root.getParentElement());
        this.classes = new HashSet<String>();
        this.map = new HashMap<String, RPC>();
        for (final Object o : root.getChildren("rpc", RpcEmulation.cnns)) {
            final RPC rpc = new RPC((Element)o);
            this.map.put(rpc.signature(), rpc);
            this.classes.add(rpc.classname);
        }
    }
    
    @Override
    void start(final Simulator simulator) {
        super.start(simulator);
        try {
            final InputStream istr = simulator.getChannel().socket().getInputStream();
            this.processRequest(istr);
        }
        catch (Exception e) {
            RpcEmulation.log.error("Start Failed", e);
            simulator.close();
        }
    }
    
    @Override
    boolean nonblocking() {
        return false;
    }
    
    void skipHeader(final InputStream istr) throws IOException {
        int nl = 0;
        do {
            switch (istr.read()) {
                case -1: {}
                case 13: {
                    continue;
                }
                case 10: {
                    ++nl;
                    continue;
                }
                default: {
                    nl = 0;
                    continue;
                }
            }
        } while (nl <= 1);
    }
    
    void processRequest(final InputStream istr) throws IOException {
        this.skipHeader(istr);
        final XmlRpcServer rpc = new XmlRpcServer();
        for (final String c : this.classes) {
            rpc.addHandler(c, (Object)this);
        }
        this.write(new String(rpc.execute(istr)));
        this.simulator.close();
    }
    
    public Object execute(final String method, final Vector params) {
        RpcEmulation.log.info("RPC method: " + method);
        String result = "<method-undefined/>";
        RPC rpc = this.map.get(method);
        if (rpc != null) {
            result = rpc.execute();
        }
        else {
            rpc = this.map.get(String.valueOf(method.split("\\.")[0]) + ".ANY");
            if (rpc != null) {
                result = rpc.execute();
            }
        }
        final EncodedString ec = new EncodedString(result);
        ec.setProperties(this.properties);
        result = ec.toString();
        RpcEmulation.log.info("Replacing variables [" + this.properties + "]");
        RpcEmulation.log.info("RPC returning: " + result);
        return result;
    }
}
