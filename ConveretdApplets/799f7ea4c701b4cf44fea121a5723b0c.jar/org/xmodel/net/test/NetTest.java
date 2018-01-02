// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.test;

import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.ModelObject;
import org.xmodel.external.ExternalReference;
import org.xmodel.IModelObject;
import org.xmodel.net.NetworkCachingPolicy;
import org.xmodel.log.Log;

public class NetTest
{
    private Log B;
    private NetworkCachingPolicy A;
    
    public NetTest() {
        this.B = new Log("org.xmodel.net.test");
    }
    
    public void startServer(final int n) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new Error("Unresolved compilation problem: \n\tThe constructor Server(String, int, int) is undefined\n");
            }
        }, "ServerModel");
        thread.setDaemon(true);
        thread.start();
    }
    
    public IModelObject createClient(final int n) {
        final ExternalReference externalReference = new ExternalReference("client");
        externalReference.setAttribute("host", "127.0.0.1");
        externalReference.setAttribute("port", 9000);
        externalReference.setAttribute("timeout", 100000);
        externalReference.setAttribute("xpath", ".");
        if (this.A == null) {
            this.A = new NetworkCachingPolicy();
            final ModelObject modelObject = new ModelObject("annotation");
            modelObject.setAttribute("host", "127.0.0.1");
            modelObject.setAttribute("port", 9000);
            modelObject.setAttribute("timeout", 100000);
            modelObject.setAttribute("xpath", ".");
            this.A.configure(new StatefulContext(), modelObject);
        }
        externalReference.setCachingPolicy(this.A);
        externalReference.setDirty(true);
        return externalReference;
    }
    
    public void test1() throws Exception {
        this.startServer(9000);
        Thread.sleep(1000L);
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        for (int i = 0; i < 2; ++i) {
            final IModelObject client = this.createClient(9000);
            System.out.println(XPath.createExpression("common/AddressGroupTree.xml/*").queryFirst(client).getType());
            list.add(client);
        }
        for (int j = 0; j < 2; ++j) {
            ((IModelObject)list.get(j)).getChild(1).setValue("FLUFF!");
        }
    }
    
    public static void main(final String[] array) throws Exception {
        new NetTest().test1();
    }
}
