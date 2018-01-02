// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.spi;

import org.apache.log4j.helpers.LogLog;
import java.io.Writer;
import org.apache.log4j.Layout;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Serializable;

public class LocationInfo implements Serializable
{
    transient String lineNumber;
    transient String fileName;
    transient String className;
    transient String methodName;
    public String fullInfo;
    private static StringWriter sw;
    private static PrintWriter pw;
    public static final String NA = "?";
    static final long serialVersionUID = -1325822038990805636L;
    static boolean inVisualAge;
    
    public LocationInfo(final Throwable t, final String s) {
        if (t == null) {
            return;
        }
        final String string;
        synchronized (LocationInfo.sw) {
            t.printStackTrace(LocationInfo.pw);
            string = LocationInfo.sw.toString();
            LocationInfo.sw.getBuffer().setLength(0);
        }
        final int lastIndex = string.lastIndexOf(s);
        if (lastIndex == -1) {
            return;
        }
        final int index = string.indexOf(Layout.LINE_SEP, lastIndex);
        if (index == -1) {
            return;
        }
        int lastIndex2 = index + Layout.LINE_SEP_LEN;
        final int index2 = string.indexOf(Layout.LINE_SEP, lastIndex2);
        if (index2 == -1) {
            return;
        }
        if (!LocationInfo.inVisualAge) {
            lastIndex2 = string.lastIndexOf("at ", index2);
            if (lastIndex2 == -1) {
                return;
            }
            lastIndex2 += 3;
        }
        this.fullInfo = string.substring(lastIndex2, index2);
    }
    
    public String getClassName() {
        if (this.fullInfo == null) {
            return "?";
        }
        if (this.className == null) {
            final int lastIndex = this.fullInfo.lastIndexOf(40);
            if (lastIndex == -1) {
                this.className = "?";
            }
            else {
                final int lastIndex2 = this.fullInfo.lastIndexOf(46, lastIndex);
                int n = 0;
                if (LocationInfo.inVisualAge) {
                    n = this.fullInfo.lastIndexOf(32, lastIndex2) + 1;
                }
                if (lastIndex2 == -1) {
                    this.className = "?";
                }
                else {
                    this.className = this.fullInfo.substring(n, lastIndex2);
                }
            }
        }
        return this.className;
    }
    
    public String getFileName() {
        if (this.fullInfo == null) {
            return "?";
        }
        if (this.fileName == null) {
            final int lastIndex = this.fullInfo.lastIndexOf(58);
            if (lastIndex == -1) {
                this.fileName = "?";
            }
            else {
                this.fileName = this.fullInfo.substring(this.fullInfo.lastIndexOf(40, lastIndex - 1) + 1, lastIndex);
            }
        }
        return this.fileName;
    }
    
    public String getLineNumber() {
        if (this.fullInfo == null) {
            return "?";
        }
        if (this.lineNumber == null) {
            final int lastIndex = this.fullInfo.lastIndexOf(41);
            final int lastIndex2 = this.fullInfo.lastIndexOf(58, lastIndex - 1);
            if (lastIndex2 == -1) {
                this.lineNumber = "?";
            }
            else {
                this.lineNumber = this.fullInfo.substring(lastIndex2 + 1, lastIndex);
            }
        }
        return this.lineNumber;
    }
    
    public String getMethodName() {
        if (this.fullInfo == null) {
            return "?";
        }
        if (this.methodName == null) {
            final int lastIndex = this.fullInfo.lastIndexOf(40);
            final int lastIndex2 = this.fullInfo.lastIndexOf(46, lastIndex);
            if (lastIndex2 == -1) {
                this.methodName = "?";
            }
            else {
                this.methodName = this.fullInfo.substring(lastIndex2 + 1, lastIndex);
            }
        }
        return this.methodName;
    }
    
    static {
        LocationInfo.sw = new StringWriter();
        LocationInfo.pw = new PrintWriter(LocationInfo.sw);
        LocationInfo.inVisualAge = false;
        try {
            Class.forName("com.ibm.uvm.tools.DebugSupport");
            LocationInfo.inVisualAge = true;
            LogLog.debug("Detected IBM VisualAge environment.");
        }
        catch (Throwable t) {}
    }
}
