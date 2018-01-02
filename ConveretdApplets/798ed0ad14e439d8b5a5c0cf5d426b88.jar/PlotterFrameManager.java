import java.util.Collection;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.awt.Window;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dialog;
import java.util.Vector;
import java.awt.Frame;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotterFrameManager
{
    protected Hashtable I;
    protected Frame add;
    protected Vector addElement;
    private int[] translate;
    static Class adjustPosition;
    
    public PlotterFrameManager() {
        this.I = new Hashtable();
        this.addElement = new Vector();
        this.translate = new int[] { 0, 0 };
    }
    
    private void createFrame(final PlotterPanel plotterPanel) {
        if (this.addElement.size() == 0) {
            final PlotterDialog plotterDialog = new PlotterDialog(this.add, plotterPanel);
            this.adjustPosition(plotterDialog);
            if (this.add.isVisible()) {
                plotterDialog.setVisible(true);
            }
        }
        for (int i = 0; i < this.addElement.size(); ++i) {
            final Object value = this.addElement.get(i);
            try {
                value.getClass().getMethod("plotterCreated", (PlotterFrameManager.adjustPosition == null) ? (PlotterFrameManager.adjustPosition = I("java.awt.Component")) : PlotterFrameManager.adjustPosition).invoke(value, plotterPanel);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void updateFrame(final AbstractBox abstractBox, final PlotterPanel plotPanel) {
        final PlotterPanel[] array = this.I.get(abstractBox);
        Container parent = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getPlotterName().equals(plotPanel.getPlotterName())) {
                parent = array[i].getParent();
                if (parent == null) {
                    this.createFrame(plotPanel);
                    return;
                }
                if (parent instanceof PlotterDialog) {
                    final PlotterDialog plotterDialog = (PlotterDialog)parent;
                    plotterDialog.setPlotPanel(plotPanel);
                    plotterDialog.reset();
                }
                else {
                    final Component[] components = array[i].getComponents();
                    for (int j = 0; j < components.length; ++j) {
                        components[j].setVisible(false);
                        if (components[j] instanceof Dialog) {
                            ((Dialog)components[j]).dispose();
                        }
                    }
                    parent.removeAll();
                    parent.add(plotPanel);
                    parent.validate();
                }
                plotPanel.reset();
            }
        }
        for (int k = 0; k < this.addElement.size(); ++k) {
            final Object value = this.addElement.get(k);
            try {
                value.getClass().getMethod("plotterUpdated", (PlotterFrameManager.adjustPosition == null) ? (PlotterFrameManager.adjustPosition = I("java.awt.Component")) : PlotterFrameManager.adjustPosition).invoke(value, parent);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void deleteFrame(final AbstractBox abstractBox, final PlotterPanel plotterPanel, final boolean b) {
        final PlotterPanel[] array = this.I.get(abstractBox);
        if (array != null) {
            final Vector vector = new Vector<PlotterPanel>();
            for (int i = 0; i < array.length; ++i) {
                if (array[i].getPlotterName().equals(plotterPanel.getPlotterName())) {
                    final Container parent = array[i].getParent();
                    if ((parent != null && parent.isVisible()) || b) {
                        parent.setVisible(false);
                        if (parent instanceof Window) {
                            ((Window)parent).dispose();
                        }
                    }
                }
                else {
                    vector.addElement(array[i]);
                }
            }
            final PlotterPanel[] plotters = new PlotterPanel[vector.size()];
            vector.copyInto(plotters);
            if (abstractBox instanceof CapsaComandes) {
                ((CapsaComandes)abstractBox).setPlotters(plotters);
            }
            this.I.remove(abstractBox);
            this.I.put(abstractBox, plotters);
        }
        for (int j = 0; j < this.addElement.size(); ++j) {
            final Object value = this.addElement.get(j);
            try {
                value.getClass().getMethod("plotterDeleted", (PlotterFrameManager.adjustPosition == null) ? (PlotterFrameManager.adjustPosition = I("java.awt.Component")) : PlotterFrameManager.adjustPosition).invoke(value, plotterPanel);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static final PlotterFrameManager newInstance() {
        return new PlotterFrameManager();
    }
    
    public final void createFrame(final AbstractBox abstractBox, PlotterPanel[] plotters) {
        final PlotterPanel[] array = this.I.get(abstractBox);
        final Vector vector = new Vector<PlotterPanel>();
        final CapsaComandes capsaComandes = (abstractBox instanceof CapsaComandes) ? ((CapsaComandes)abstractBox) : null;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < plotters.length; ++j) {
                    if (plotters[j].getPlotterName().equals(array[i].getPlotterName())) {
                        if (capsaComandes != null) {
                            plotters[j].setGroup(capsaComandes.getNumGroup());
                        }
                        this.updateFrame(abstractBox, plotters[j]);
                        array[i] = plotters[j];
                    }
                }
                vector.addElement(array[i]);
            }
        }
        for (int k = 0; k < plotters.length; ++k) {
            if (capsaComandes != null) {
                plotters[k].setGroup(capsaComandes.getNumGroup());
            }
            if (!vector.contains(plotters[k])) {
                this.createFrame(plotters[k]);
                vector.addElement(plotters[k]);
            }
        }
        plotters = new PlotterPanel[vector.size()];
        vector.copyInto(plotters);
        if (capsaComandes != null) {
            capsaComandes.setPlotters(plotters);
        }
        this.I.put(abstractBox, plotters);
    }
    
    public final void addPlotterFrameListener(final Object o) {
        this.addElement.addElement(o);
    }
    
    public final void updatesPlotterFrame(final FormulaEditorCalc formulaEditorCalc) {
        final Enumeration<AbstractBox> keys = this.I.keys();
        while (keys.hasMoreElements()) {
            final AbstractBox abstractBox = keys.nextElement();
            final PlotterPanel[] array = this.I.get(abstractBox);
            if (!formulaEditorCalc.exists(abstractBox)) {
                for (int i = 0; i < array.length; ++i) {
                    this.deleteFrame(abstractBox, array[i], false);
                }
                this.I.remove(abstractBox);
            }
            else {
                for (int j = 0; j < array.length; ++j) {
                    if (abstractBox instanceof CapsaComandes) {
                        array[j].setGroup(((CapsaComandes)abstractBox).getNumGroup());
                    }
                }
            }
        }
    }
    
    public final void setMainFrame(final Frame add) {
        this.add = add;
    }
    
    public final void add(final PlotterPanel plotterPanel, final AbstractBox abstractBox) {
        PlotterPanel[] plotters;
        if (this.I.containsKey(abstractBox)) {
            final PlotterPanel[] array = this.I.get(abstractBox);
            plotters = new PlotterPanel[array.length + 1];
            for (int i = 0; i < array.length; ++i) {
                plotters[i] = array[i];
            }
            plotters[plotters.length - 1] = plotterPanel;
            this.I.remove(abstractBox);
        }
        else {
            plotters = new PlotterPanel[] { plotterPanel };
        }
        if (abstractBox instanceof CapsaComandes) {
            ((CapsaComandes)abstractBox).setPlotters(plotters);
        }
        this.I.put(abstractBox, plotters);
    }
    
    public final void clear() {
        this.I = new Hashtable();
    }
    
    private void adjustPosition(final Dialog dialog) {
        dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - dialog.getSize().width - 50, 50);
        final Point location = dialog.getLocation();
        location.translate(this.translate[0], this.translate[1]);
        dialog.setLocation(location);
        this.translate[0] += 10;
        if (this.translate[0] == 50) {
            this.translate[0] = 0;
        }
        this.translate[1] += 50;
        if (this.translate[1] == 250) {
            this.translate[1] = 0;
        }
    }
    
    public final void newSession() {
        final Vector<PlotterPanel[]> vector = new Vector<PlotterPanel[]>(this.I.values());
        for (int i = 0; i < vector.size(); ++i) {
            final PlotterPanel[] array = vector.get(i);
            for (int j = 0; j < array.length; ++j) {
                if (array[j].getPlotComp() != null) {
                    if (array[j] instanceof Dialog3d) {
                        this.deleteFrame(((Dialog3d)array[j]).getCanvas3d().box, array[j], true);
                    }
                    else {
                        this.deleteFrame(((PlotCanvas)array[j].getPlotComp()).capsaComandes, array[j], true);
                    }
                }
            }
        }
        this.clear();
    }
    
    public final PlotterPanel[] getPlotters(final AbstractBox abstractBox) {
        return this.I.get(abstractBox);
    }
    
    private static final Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
