// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import java.util.ArrayList;
import org.jfree.data.AbstractXYDataset;
import org.jfree.chart.JFreeChart;
import java.awt.Dimension;
import org.jfree.data.XYDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jboss.console.navtree.AppletBrowser;
import org.jboss.console.navtree.TreeContext;
import org.jfree.chart.ChartFrame;
import javax.management.ObjectName;
import org.jboss.console.navtree.AppletTreeAction;

public class GraphMBeanAttributeAction implements AppletTreeAction
{
    protected ObjectName targetObjectName;
    protected String attr;
    protected transient ChartFrame frame;
    protected transient MBeanXYDataset dataset;
    
    public GraphMBeanAttributeAction() {
        this.targetObjectName = null;
        this.attr = null;
        this.frame = null;
        this.dataset = null;
    }
    
    public GraphMBeanAttributeAction(final ObjectName pName, final String attr) {
        this.targetObjectName = null;
        this.attr = null;
        this.frame = null;
        this.dataset = null;
        this.targetObjectName = pName;
        this.attr = attr;
    }
    
    public void doAction(final TreeContext tc, final AppletBrowser applet) {
        try {
            if (this.frame == null) {
                this.dataset = new MBeanXYDataset();
                final JFreeChart chart = ChartFactory.createXYLineChart("JMX Attribute: " + this.attr, "count", this.attr, this.dataset, PlotOrientation.VERTICAL, true, true, false);
                final UpdateThread update = new UpdateThread(this.dataset, tc);
                final Thread thread = new Thread(update);
                thread.start();
                this.frame = new ChartFrame("JMX Attribute: " + this.attr, chart);
                this.frame.getChartPanel().setPreferredSize(new Dimension(500, 270));
                this.frame.pack();
            }
            else {
                this.dataset.clear();
            }
            this.frame.show();
            this.frame.requestFocus();
        }
        catch (Exception displayed) {
            displayed.printStackTrace();
        }
    }
    
    public class MBeanXYDataset extends AbstractXYDataset
    {
        private ArrayList data;
        
        public MBeanXYDataset() {
            this.data = new ArrayList();
        }
        
        public void clear() {
            this.data.clear();
            this.notifyListeners(new DatasetChangeEvent(this, this));
        }
        
        public void add(final Object num) {
            this.data.add(num);
            this.notifyListeners(new DatasetChangeEvent(this, this));
        }
        
        public Number getXValue(final int series, final int item) {
            return new Integer(item);
        }
        
        public Number getYValue(final int series, final int item) {
            return this.data.get(item);
        }
        
        public int getSeriesCount() {
            return 1;
        }
        
        public String getSeriesName(final int series) {
            return "y = " + GraphMBeanAttributeAction.this.attr;
        }
        
        public int getItemCount(final int series) {
            return this.data.size();
        }
    }
    
    public class UpdateThread implements Runnable
    {
        MBeanXYDataset data;
        TreeContext tc;
        
        public UpdateThread(final MBeanXYDataset data, final TreeContext tc) {
            this.data = data;
            this.tc = tc;
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        if (GraphMBeanAttributeAction.this.frame.isShowing()) {
                            final Object val = this.tc.getRemoteMBeanInvoker().getAttribute(GraphMBeanAttributeAction.this.targetObjectName, GraphMBeanAttributeAction.this.attr);
                            System.out.println("added value: " + val);
                            this.data.add(val);
                        }
                        Thread.sleep(1000L);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
}
