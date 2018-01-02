// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.util.Enumeration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public abstract class Record
{
    protected Vector Fids;
    protected String key;
    protected String service;
    protected short classId;
    protected short status;
    protected URL myUrl;
    
    public Record(final String s) throws MalformedURLException {
        this.classId = 1000;
        this.myUrl = new URL(s);
        final String file = this.myUrl.getFile();
        if (file != null) {
            int index = file.indexOf(47);
            if (index == -1) {
                index = 0;
            }
            else {
                ++index;
            }
            int n = file.indexOf(47, index + 1);
            if (n == -1) {
                n = file.length();
            }
            this.service = file.substring(index, n);
            if (n != file.length()) {
                final int n2 = n + 1;
                int n3 = file.indexOf(47, n2 + 1);
                if (n3 == -1) {
                    n3 = file.length();
                }
                this.key = file.substring(n2, n3);
            }
        }
        this.Fids = new Vector();
    }
    
    public Record() {
        this.classId = 1000;
        this.Fids = new Vector();
    }
    
    public abstract void error();
    
    public String getService() {
        return this.service;
    }
    
    public void setService(final String service) {
        this.service = service;
    }
    
    public void remove(final Object o) {
        this.Fids.removeElement(o);
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public Enumeration fids() {
        return this.Fids.elements();
    }
    
    public short getType() {
        return this.classId;
    }
    
    public void setType(final short classId) {
        this.classId = classId;
    }
    
    public boolean isFidsEmpty() {
        return this.Fids.isEmpty();
    }
    
    public short getStatus() {
        return this.status;
    }
    
    public String toString() {
        return new String("Record Type(" + this.classId + ")Srv(" + this.service + ") Key(" + this.key + ") Url(" + this.myUrl + ") Status(" + this.status + ")");
    }
}
