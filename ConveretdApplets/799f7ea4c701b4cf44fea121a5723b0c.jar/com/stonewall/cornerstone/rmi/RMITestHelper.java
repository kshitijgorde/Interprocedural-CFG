// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import com.stonewall.cornerstone.entity.AlarmableEntity;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.ValidationException;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class RMITestHelper
{
    private String s;
    private Integer i;
    
    public RMITestHelper() {
    }
    
    public RMITestHelper(final String s) {
        this.s = s;
    }
    
    public RMITestHelper(final Integer i) {
        this.i = i;
    }
    
    public RMITestHelper(final IModelObject e) {
        this.s = Xlate.get(e, (String)null);
    }
    
    public static Boolean staticInvoke() {
        System.out.println("In static invoke");
        return Boolean.TRUE;
    }
    
    public String stringInvoke(final String input) {
        System.out.println("In String invoke " + input);
        return String.valueOf(this.s) + "-" + input;
    }
    
    public Integer integerInvoke(final Integer input) {
        System.out.println("In Integer invoke " + input);
        return this.i + input;
    }
    
    public IModelObject elementInvoke(final IModelObject input) {
        final String value = Xlate.get(input, (String)null);
        System.out.println("In Element invoke " + value);
        input.setValue(String.valueOf(this.s) + "-" + value);
        return input;
    }
    
    public Boolean noParamInvoke() {
        System.out.println("In no param invoke ");
        return true;
    }
    
    public Integer multiParams(final Integer i, final String input) {
        System.out.println("In multi param invoke: i=" + i + " input=" + input);
        return i + new Integer(input);
    }
    
    public String listInvoke(final List input) {
        System.out.println("In list invoke " + input);
        return String.valueOf(this.s) + "-" + input;
    }
    
    public void validationException() throws Exception {
        final ValidationException ve = new ValidationException();
        ve.invalidState("offline", "online", new Device());
        throw ve;
    }
    
    public void otherException() throws Exception {
        final Device device = null;
        device.getId();
    }
    
    public String methodLookup(final Entity e1, final Entity e2) {
        System.out.println("in method lookup 2 params");
        return "method lookup 2 params";
    }
    
    public String methodLookup(final Entity e1) {
        System.out.println("in method lookup 1 param");
        return "method lookup 1 param";
    }
    
    public String methodLookup(final AlarmableEntity e1, final String s) {
        System.out.println("in method lookup interface param");
        return "method lookup interface param";
    }
    
    public Collection getCollection() {
        System.out.println("in get collection");
        final Map<String, String> map = new HashMap<String, String>();
        map.put("Hi", "Cam");
        map.put("There", "Jeff");
        return map.values();
    }
}
