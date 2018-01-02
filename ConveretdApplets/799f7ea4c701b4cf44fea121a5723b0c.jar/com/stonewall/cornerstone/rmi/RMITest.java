// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import com.stonewall.cornerstone.entity.DeviceManager;
import com.stonewall.cornerstone.entity.db.DeviceManagerDbAccess;
import com.stonewall.cornerstone.entity.AlarmableEntity;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.ValidationException;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.entity.PolicyServer;
import com.stonewall.cornerstone.component.Bootstrap;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import junit.framework.TestCase;

public class RMITest extends TestCase
{
    public void testString() {
        try {
            final Destination dest = new Destination(Destination.Type.ps);
            final RMIRequest request = new RMIRequest(dest);
            final DataFactory factory = new DataFactory(new TypedDataAdaptor());
            final Target target = new Target(factory, SecurityPolicy.class.getName(), new Object[] { "hello" });
            request.setTarget(target.getRoot());
            request.setMethod(new Method(factory, "insert", new Object[] { "oh my" }).getRoot());
            System.out.println(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testInteger() {
        try {
            final Destination dest = new Destination(Destination.Type.ps);
            final RMIRequest request = new RMIRequest(dest);
            final DataFactory factory = new DataFactory(new TypedDataAdaptor());
            final Target target = new Target(factory, SecurityPolicy.class.getName(), new Object[] { new Integer(4) });
            request.setTarget(target.getRoot());
            request.setMethod(new Method(factory, "insert", new Object[] { new Integer(5) }).getRoot());
            System.out.println(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testElement() {
        try {
            final Destination dest = new Destination(Destination.Type.ps);
            final RMIRequest request = new RMIRequest(dest);
            Element test = new Element("en:test");
            test.setValue("I am a test");
            final DataFactory factory = new DataFactory(new TypedDataAdaptor());
            final Target target = new Target(factory, SecurityPolicy.class.getName(), new Object[] { test });
            request.setTarget(target.getRoot());
            test = new Element("en:test");
            test.setValue("I am a test");
            request.setMethod(new Method(factory, "insert", new Object[] { test }).getRoot());
            System.out.println(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testNoParams() {
        try {
            final Destination dest = new Destination(Destination.Type.ps);
            final RMIRequest request = new RMIRequest(dest);
            final DataFactory factory = new DataFactory(new TypedDataAdaptor());
            final Target target = new Target(factory, SecurityPolicy.class.getName(), new Object[0]);
            request.setTarget(target.getRoot());
            request.setMethod(new Method(factory, "insert", new Object[0]).getRoot());
            System.out.println(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testList() {
        try {
            final Destination dest = new Destination(Destination.Type.ps);
            final RMIRequest request = new RMIRequest(dest);
            final DataFactory factory = new DataFactory(new TypedDataAdaptor());
            final Target target = new Target(factory, SecurityPolicy.class.getName(), new Object[] { "hello" });
            request.setTarget(target.getRoot());
            final List l = new ArrayList();
            l.add("string1");
            l.add("string2");
            l.add("string3");
            request.setMethod(new Method(factory, "insert", new Object[] { l }).getRoot());
            System.out.println(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testStringInvoke() {
        long start = 0L;
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            start = System.currentTimeMillis();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, "hello");
            final String s = (String)client.invoke("stringInvoke", "oh my");
            assertEquals("hello-oh my", s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final long finish = System.currentTimeMillis();
        System.out.println("Rmi time: " + (finish - start) + " (ms)");
    }
    
    public void testIntegerInvoke() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, 4);
            final Integer i = (Integer)client.invoke("integerInvoke", 5);
            assertEquals(String.valueOf(9), String.valueOf(i));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testElementInvoke() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            IModelObject test = new Element("en:test");
            test.setValue("constructor value");
            client.setTarget(RMITestHelper.class, test);
            test = new Element("en:test");
            test.setValue("method value");
            final Element e = (Element)client.invoke("elementInvoke", test);
            assertEquals((Object)"constructor value-method value", e.getValue());
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public void testMultiParamsInvoke() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final int succeeded = (int)client.invoke("multiParams", 4, "5");
            assertTrue(succeeded == 9);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testNoParamsInvoke() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final Boolean succeeded = (Boolean)client.invoke("noParamInvoke", new Object[0]);
            assertTrue((boolean)succeeded);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testStaticInvoke() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final Boolean succeeded = (Boolean)client.invoke("staticInvoke", new Object[0]);
            assertTrue((boolean)succeeded);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testListInvoke() {
        long start = 0L;
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            start = System.currentTimeMillis();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, "hello");
            final List l = new ArrayList();
            l.add("string1");
            l.add("string2");
            l.add("string3");
            final String s = (String)client.invoke("listInvoke", l);
            assertEquals("hello-[string1, string2, string3]", s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final long finish = System.currentTimeMillis();
        System.out.println("Rmi time: " + (finish - start) + " (ms)");
    }
    
    public void testRMIException() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final Boolean succeeded = (Boolean)client.invoke("junk", new Object[0]);
            assertTrue((boolean)succeeded);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testValidationException() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final Boolean succeeded = (Boolean)client.invoke("validationException", new Object[0]);
            assertTrue((boolean)succeeded);
        }
        catch (ValidationException ve) {
            System.out.println(ve.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testOtherException() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final Boolean succeeded = (Boolean)client.invoke("otherException", new Object[0]);
            assertTrue((boolean)succeeded);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testMethodLookup() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final String s = (String)client.invoke("methodLookup", new Device());
            assertEquals(s, "method lookup 1 param");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testMethodLookup2() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Device device = new DeviceDbAccess().fetchById("19UPILY4R");
            final Alarm alarm = new Alarm();
            alarm.open(device.getReference(), Alarm.Severity.major, Alarm.Type.outOfSync, "hh");
            final AlarmableEntity ae = (AlarmableEntity)alarm.getAlarmedReference().getReferent();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final String s = (String)client.invoke("methodLookup", ae, "help");
            assertEquals(s, "method lookup interface param");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testCollection() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(RMITestHelper.class, new Object[0]);
            final List<String> s = (List<String>)client.invoke("getCollection", new Object[0]);
            assertTrue(s instanceof List);
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testDbAccess() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget(DeviceManagerDbAccess.class, new Object[0]);
            final Object[] params = { true };
            final List<DeviceManager> l = (List<DeviceManager>)client.invoke("fetchAll", params);
            System.out.println(l);
            assertFalse(l.isEmpty());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
