// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.util.Enumeration;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Appender;
import java.util.Vector;
import org.apache.log4j.spi.AppenderAttachable;

public class AppenderAttachableImpl implements AppenderAttachable
{
    protected Vector appenderList;
    
    public void addAppender(final Appender appender) {
        if (appender == null) {
            return;
        }
        if (this.appenderList == null) {
            this.appenderList = new Vector(1);
        }
        if (!this.appenderList.contains(appender)) {
            this.appenderList.addElement(appender);
        }
    }
    
    public int appendLoopOnAppenders(final LoggingEvent loggingEvent) {
        int size = 0;
        if (this.appenderList != null) {
            size = this.appenderList.size();
            for (int i = 0; i < size; ++i) {
                ((Appender)this.appenderList.elementAt(i)).doAppend(loggingEvent);
            }
        }
        return size;
    }
    
    public Enumeration getAllAppenders() {
        if (this.appenderList == null) {
            return null;
        }
        return this.appenderList.elements();
    }
    
    public Appender getAppender(final String s) {
        if (this.appenderList == null || s == null) {
            return null;
        }
        for (int size = this.appenderList.size(), i = 0; i < size; ++i) {
            final Appender appender = this.appenderList.elementAt(i);
            if (s.equals(appender.getName())) {
                return appender;
            }
        }
        return null;
    }
    
    public boolean isAttached(final Appender appender) {
        if (this.appenderList == null || appender == null) {
            return false;
        }
        for (int size = this.appenderList.size(), i = 0; i < size; ++i) {
            if (this.appenderList.elementAt(i) == appender) {
                return true;
            }
        }
        return false;
    }
    
    public void removeAllAppenders() {
        if (this.appenderList != null) {
            for (int size = this.appenderList.size(), i = 0; i < size; ++i) {
                ((Appender)this.appenderList.elementAt(i)).close();
            }
            this.appenderList.removeAllElements();
            this.appenderList = null;
        }
    }
    
    public void removeAppender(final Appender appender) {
        if (appender == null || this.appenderList == null) {
            return;
        }
        this.appenderList.removeElement(appender);
    }
    
    public void removeAppender(final String s) {
        if (s == null || this.appenderList == null) {
            return;
        }
        for (int size = this.appenderList.size(), i = 0; i < size; ++i) {
            if (s.equals(((Appender)this.appenderList.elementAt(i)).getName())) {
                this.appenderList.removeElementAt(i);
                break;
            }
        }
    }
}
