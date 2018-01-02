// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import com.stonewall.cornerstone.entity.Device;
import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import com.stonewall.cornerstone.remoteServer.Correlation;
import junit.framework.TestCase;

public class TestData extends TestCase
{
    public void testArray() {
        final Object[] a = { new Correlation("from", "to", "tag"), "hello there" };
        this.array(a);
    }
    
    private void array1(final Object... params) {
        this.array("Good By", params);
    }
    
    private void array(final Object... params) {
        final DataFactory f = new DataFactory(new TypedDataAdaptor());
        final List<IData> data = f.createData(params);
        System.out.println(data);
        final List<IModelObject> objects = new ArrayList<IModelObject>();
        for (final IData d : data) {
            objects.add(d.getRoot());
        }
        final List<Object> o = f.createObjects(objects);
        System.out.println(o);
    }
    
    public void testList() {
        final List<Device> devices = new ArrayList<Device>();
        devices.add(new Device());
        devices.add(new Device());
        devices.add(new Device());
        final DataFactory f = new DataFactory(new TypedDataAdaptor());
        final List<IData> data = f.createData(devices);
        System.out.println(data);
        final List<IModelObject> objects = new ArrayList<IModelObject>();
        for (final IData d : data) {
            objects.add(d.getRoot());
        }
        final List<Object> o = f.createObjects(objects);
        System.out.println(o);
    }
}
