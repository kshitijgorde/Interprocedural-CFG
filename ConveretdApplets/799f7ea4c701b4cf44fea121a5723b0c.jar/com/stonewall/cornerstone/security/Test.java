// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import org.xmodel.IModelObject;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.jdom.Document;
import java.io.OutputStream;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import junit.framework.TestCase;

public class Test extends TestCase
{
    public void testEng() throws Exception {
        System.out.println("\ntestEng");
        final License a = new License(1L, 1, 0L);
        a.setName("Engineering");
        a.setCompany("Stonewall Networks, Inc.");
        a.setCapability("limits.devices", "500");
        final Document d = a.getDocument();
        final XMLOutputter printer = new XMLOutputter(Format.getPrettyFormat());
        printer.output(d, (OutputStream)System.out);
        for (int i = 0; i < 500; ++i) {
            final License l = new License(1L, i, 0L);
            System.out.println(l + " E:" + l.evaluate());
        }
    }
    
    public void testFidelity() throws Exception {
        System.out.println("\nfidelity");
        final License a = new License(1L, 1, 90L);
        a.setName("Satish Laxminarayanan");
        a.setCompany("Fidelity, Inc.");
        a.setCapability("limits.devices", "100");
        final Document d = a.getDocument();
        final XMLOutputter printer = new XMLOutputter(Format.getPrettyFormat());
        printer.output(d, (OutputStream)System.out);
    }
    
    public void testCisco() throws Exception {
        System.out.println("\ncisco");
        final License a = new License(1L, 1, 90L);
        a.setName("Alex Daltrini");
        a.setCompany("Cisco System, Latin America");
        a.setCapability("limits.devices", "100");
        final Document d = a.getDocument();
        final XMLOutputter printer = new XMLOutputter(Format.getPrettyFormat());
        printer.output(d, (OutputStream)System.out);
    }
    
    public void testLicense() throws Exception {
        System.out.println("\ntestLicense");
        for (int i = 0; i < 10; ++i) {
            final long alg = 1L;
            final int seed = i;
            final long limit = (i % 3 == 0) ? i : 0;
            final License a = new License(1L, seed, limit);
            a.setName("Engineering");
            a.setCompany("Stonewall Networks, Inc.");
            a.setCapability("limits.devices", "500");
            final License b = new License(a);
            System.out.println(String.valueOf(i) + ")");
            System.out.println("A[" + a + "]/" + a.evaluate() + "/limit:" + a.getLimit() + "/(" + a.getExpiration() + ")" + "capabilities:" + a.getCapabilities());
            System.out.println("B[" + b + "]/" + b.evaluate() + "/limit:" + b.getLimit() + "/(" + b.getExpiration() + ")" + "capabilities:" + a.getCapabilities());
            final Document d = b.getDocument();
            final License c = new License(d);
            System.out.println("C[" + c + "]/" + c.evaluate() + "/limit:" + c.getLimit() + "/(" + c.getExpiration() + ")" + "capabilities:" + a.getCapabilities() + "\n");
        }
    }
    
    public void testFA() {
        final FeatureAccess access = new FeatureAccess(FeatureAccess.Type.sitePolicyDeploy);
        final String path = access.getPermissionPath();
        System.out.println(path);
    }
    
    public void testAccessElement() {
        final FeatureAccess access = new FeatureAccess(FeatureAccess.Type.sitePolicyDeploy);
        IModelObject e = access.getAccessAsObject("SITE0");
        final ModelBuilder builder = new ModelBuilder();
        System.out.println(builder.writeModel(e, IXmlIO.Style.printable));
        e = access.getActionAsObject();
        System.out.println(builder.writeModel(e, IXmlIO.Style.printable));
    }
    
    public void testTargetPath() {
        FeatureAccess access = new FeatureAccess(FeatureAccess.Type.sitePolicyDeploy);
        String path = access.getTargetPath();
        System.out.println(path);
        access = new FeatureAccess(FeatureAccess.Type.sitePolicyDeploy);
        path = access.getTargetPath();
        System.out.println(path);
        access = new FeatureAccess(FeatureAccess.Type.sitePolicyDeploy);
        path = access.getActionPath();
        System.out.println(path);
    }
}
