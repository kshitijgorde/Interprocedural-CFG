// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.servlet;

import java.util.Iterator;
import java.io.File;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSessionBindingListener;

public class ChartDeleter implements HttpSessionBindingListener
{
    private List chartNames;
    
    public ChartDeleter() {
        this.chartNames = new ArrayList();
    }
    
    public void addChart(final String filename) {
        this.chartNames.add(filename);
    }
    
    public boolean isChartAvailable(final String filename) {
        return this.chartNames.contains(filename);
    }
    
    public void valueBound(final HttpSessionBindingEvent event) {
    }
    
    public void valueUnbound(final HttpSessionBindingEvent event) {
        final Iterator iter = this.chartNames.listIterator();
        while (iter.hasNext()) {
            final String filename = iter.next();
            final File file = new File(System.getProperty("java.io.tmpdir"), filename);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
