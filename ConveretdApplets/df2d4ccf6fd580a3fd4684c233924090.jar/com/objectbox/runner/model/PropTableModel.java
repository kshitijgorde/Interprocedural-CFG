// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Properties;
import com.objectbox.runner.beans.AbstrTableModel;

public class PropTableModel extends AbstrTableModel
{
    private String[] h;
    private int nrows;
    private Properties props;
    private Properties param;
    private Properties jbprops;
    private JBProperties jbee_properties;
    private Vector dataVector;
    
    public PropTableModel() {
        this.h = new String[] { "Name", "Value" };
        this.nrows = 0;
        this.props = null;
        this.param = null;
        this.jbprops = null;
        this.jbee_properties = null;
        this.dataVector = new Vector();
    }
    
    public void delete(final int n) {
        final Vector<String> vector = this.dataVector.elementAt(n - 1);
        final String s = vector.elementAt(2);
        if (s.compareTo("jbprops") == 0) {
            return;
        }
        this.dataVector.removeElement(vector);
        if (s.compareTo("param") == 0) {
            this.param.remove(vector.elementAt(0));
        }
        if (s.compareTo("props") == 0) {
            this.props.remove(vector.elementAt(0));
        }
    }
    
    public String getCell(final int n, final int n2) {
        return this.dataVector.elementAt(n2 - 1).elementAt(n);
    }
    
    public JBProperties getData() {
        return this.jbee_properties;
    }
    
    public String[] getHeaders() {
        return this.h;
    }
    
    public int getNumberOfColumns() {
        return this.getHeaders().length;
    }
    
    public int getNumberOfRows() {
        return this.dataVector.size() + 1;
    }
    
    public void init(final String s) {
        for (int i = 0; i < 10; ++i) {}
    }
    
    public void newParameter(final String s, final String s2) {
        ((Hashtable<String, String>)this.param).put(s, s2);
        this.setData(this.jbee_properties);
    }
    
    public void newProperty(final String s, final String s2) {
        ((Hashtable<String, String>)this.props).put(s, s2);
        this.setData(this.jbee_properties);
    }
    
    public void setCell(final int n, final int n2, final String s) {
        final Vector<String> vector = this.dataVector.elementAt(n2 - 1);
        vector.setElementAt(s, n);
        final String element = vector.elementAt(0);
        vector.elementAt(1);
        final String element2 = vector.elementAt(2);
        if (element2.equals("props")) {
            ((Hashtable<String, String>)this.props).put(element, s);
        }
        else if (element2.equals("param")) {
            ((Hashtable<String, String>)this.param).put(element, s);
        }
        else if (element2.equals("jbprops")) {
            ((Hashtable<String, String>)this.jbprops).put(element, s);
        }
    }
    
    public void setData(final JBProperties jbee_properties) {
        this.jbee_properties = jbee_properties;
        this.props = jbee_properties.getProps();
        this.param = jbee_properties.getParameters();
        this.jbprops = jbee_properties.getJBeeProps();
        this.dataVector.removeAllElements();
        final Enumeration<?> propertyNames = this.jbprops.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final Object nextElement = propertyNames.nextElement();
            final Vector<String> vector = new Vector<String>();
            vector.addElement((String)nextElement);
            vector.addElement(((Hashtable<K, String>)this.jbprops).get(nextElement));
            vector.addElement("jbprops");
            this.dataVector.addElement(vector);
        }
        final Enumeration<?> propertyNames2 = this.props.propertyNames();
        while (propertyNames2.hasMoreElements()) {
            final Object nextElement2 = propertyNames2.nextElement();
            final Vector<String> vector2 = new Vector<String>();
            vector2.addElement((String)nextElement2);
            vector2.addElement(((Hashtable<K, String>)this.props).get(nextElement2));
            vector2.addElement("props");
            this.dataVector.addElement(vector2);
        }
        final Enumeration<?> propertyNames3 = this.param.propertyNames();
        while (propertyNames3.hasMoreElements()) {
            final Object nextElement3 = propertyNames3.nextElement();
            final Vector<String> vector3 = new Vector<String>();
            vector3.addElement((String)nextElement3);
            vector3.addElement(((Hashtable<K, String>)this.param).get(nextElement3));
            vector3.addElement("param");
            this.dataVector.addElement(vector3);
        }
    }
    
    public void setHeaders(final String[] h) {
        this.h = h;
    }
}
