// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing;

import java.io.File;
import org.xmodel.IModelObject;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.Creator;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.swing.image.ImageFileAssociation;
import org.xmodel.caching.IFileAssociation;
import java.util.Arrays;
import java.awt.GraphicsEnvironment;
import java.util.List;
import org.xidget.swing.chart.line.YAxisXidget;
import org.xidget.swing.chart.line.XAxisXidget;
import org.xidget.swing.xmleditor.XmlTextPaneXidget;
import org.xidget.binding.tree.TreeTagHandler;
import org.xidget.swing.tree.JTreeXidget;
import org.xidget.binding.table.TableTagHandler;
import org.xidget.swing.table.JTableXidget;
import org.xidget.swing.spinner.JSpinnerXidget;
import org.xidget.swing.slider.JSliderXidget;
import org.xidget.swing.progress.JProgressBarXidget;
import org.xidget.swing.menu.JMenuItemXidget;
import org.xidget.swing.menu.SubMenuTagHandler;
import org.xidget.swing.menu.MenuTagHandler;
import org.xidget.swing.menu.JMenuBarXidget;
import org.xidget.swing.label.JLabelTagHandler;
import org.xidget.swing.label.JLabelXidget;
import org.xidget.swing.button.AbstractButtonXidget;
import org.xidget.swing.combo.JComboBoxXidget;
import org.xidget.swing.text.JTextXidget;
import org.xidget.swing.tabs.JTabbedPaneXidget;
import org.xidget.swing.chart.line.LineChartXidget;
import org.xidget.swing.chart.pie.PieChartXidget;
import org.xidget.swing.form.JPanelXidget;
import org.xidget.swing.dialog.JDialogXidget;
import org.xidget.swing.calendar.CalendarXidget;
import org.xidget.swing.frame.JFrameXidget;
import org.xidget.config.ITagHandler;
import org.xidget.IXidget;
import org.xidget.binding.XidgetTagHandler;
import org.xidget.swing.applet.JAppletXidget;
import org.xidget.config.TagProcessor;
import javax.swing.SwingUtilities;
import org.xmodel.IDispatcher;
import org.xmodel.ModelRegistry;
import org.xidget.swing.feature.SwingFocusFeature;
import org.xidget.swing.feature.AsyncFeature;
import org.xidget.ifeature.IFocusFeature;
import org.xidget.ifeature.IAsyncFeature;
import org.xidget.IToolkit;

public class Toolkit implements IToolkit
{
    private IAsyncFeature asyncFeature;
    private IFocusFeature focusFeature;
    
    public Toolkit() {
        this.asyncFeature = new AsyncFeature();
        this.focusFeature = new SwingFocusFeature();
        ModelRegistry.getInstance().getModel().setDispatcher(new IDispatcher() {
            @Override
            public void execute(final Runnable runnable) {
                SwingUtilities.invokeLater(runnable);
            }
        });
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IAsyncFeature.class) {
            return (T)this.asyncFeature;
        }
        if (clazz == IFocusFeature.class) {
            return (T)this.focusFeature;
        }
        return null;
    }
    
    @Override
    public void configure(final TagProcessor tagProcessor) {
        tagProcessor.addHandler("applet", new XidgetTagHandler(JAppletXidget.class));
        tagProcessor.addHandler("window", new XidgetTagHandler(JFrameXidget.class));
        tagProcessor.addHandler("calendar", new XidgetTagHandler(CalendarXidget.class));
        tagProcessor.addHandler("dialog", new XidgetTagHandler(JDialogXidget.class));
        tagProcessor.addHandler("form", new XidgetTagHandler(JPanelXidget.class));
        tagProcessor.addHandler("chart", new XidgetTagHandler(PieChartXidget.class, "style", "pie"));
        tagProcessor.addHandler("chart", new XidgetTagHandler(LineChartXidget.class, "style", "line"));
        tagProcessor.addHandler("tabs", new XidgetTagHandler(JTabbedPaneXidget.class));
        tagProcessor.addHandler("text", new XidgetTagHandler(JTextXidget.class));
        tagProcessor.addHandler("combo", new XidgetTagHandler(JComboBoxXidget.class));
        tagProcessor.addHandler("button", new XidgetTagHandler(AbstractButtonXidget.class));
        tagProcessor.addHandler("label", new JLabelTagHandler(JLabelXidget.class));
        tagProcessor.addHandler("menubar", new XidgetTagHandler(JMenuBarXidget.class));
        tagProcessor.addHandler("menu", new MenuTagHandler());
        tagProcessor.addHandler("menu", new SubMenuTagHandler());
        tagProcessor.addHandler("menuItem", new XidgetTagHandler(JMenuItemXidget.class));
        tagProcessor.addHandler("password", new XidgetTagHandler(JTextXidget.class));
        tagProcessor.addHandler("progress", new XidgetTagHandler(JProgressBarXidget.class));
        tagProcessor.addHandler("separator", new XidgetTagHandler(JMenuItemXidget.class));
        tagProcessor.addHandler("slider", new XidgetTagHandler(JSliderXidget.class));
        tagProcessor.addHandler("spinner", new XidgetTagHandler(JSpinnerXidget.class));
        tagProcessor.addHandler("table", new TableTagHandler(JTableXidget.class));
        tagProcessor.addHandler("tree", new TreeTagHandler(JTreeXidget.class));
        tagProcessor.addHandler("xml", new XidgetTagHandler(XmlTextPaneXidget.class));
        tagProcessor.addHandler("axis", new XidgetTagHandler(XAxisXidget.class, "style", "horizontal"));
        tagProcessor.addHandler("axis", new XidgetTagHandler(YAxisXidget.class, "style", "vertical"));
    }
    
    @Override
    public List<String> getFonts() {
        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    }
    
    @Override
    public IFileAssociation getImageFileAssociation() {
        return new ImageFileAssociation();
    }
    
    @Override
    public Confirmation openConfirmDialog(final StatefulContext statefulContext, final String s, final Object o, final String s2, final boolean b) {
        final IXidget focus = Creator.getToolkit().getFeature(IFocusFeature.class).getFocus();
        final IWidgetCreationFeature widgetCreationFeature = focus.getFeature(IWidgetCreationFeature.class);
        if (widgetCreationFeature == null) {
            throw new IllegalArgumentException("Window xidget does not have an IWidgetCreationFeature instance: " + focus);
        }
        final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
        if (lastWidgets.length == 0) {
            throw new IllegalArgumentException("Window does not have a widget: " + focus);
        }
        switch (JOptionPane.showConfirmDialog((Component)lastWidgets[0], s2, s, b ? 1 : 0)) {
            case 0: {
                return Confirmation.yes;
            }
            case 1: {
                return Confirmation.no;
            }
            default: {
                return Confirmation.cancel;
            }
        }
    }
    
    @Override
    public void openMessageDialog(final StatefulContext statefulContext, final String s, final Object o, final String s2, final MessageType messageType) {
        final IXidget focus = Creator.getToolkit().getFeature(IFocusFeature.class).getFocus();
        final IWidgetCreationFeature widgetCreationFeature = focus.getFeature(IWidgetCreationFeature.class);
        if (widgetCreationFeature == null) {
            throw new IllegalArgumentException("Window xidget does not have an IWidgetCreationFeature instance: " + focus);
        }
        final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
        if (lastWidgets.length == 0) {
            throw new IllegalArgumentException("Window does not have a widget: " + focus);
        }
        int n = -1;
        switch (messageType) {
            case error: {
                n = 0;
                break;
            }
            case warning: {
                n = 2;
                break;
            }
            case information: {
                n = 1;
                break;
            }
        }
        if (o == null) {
            JOptionPane.showMessageDialog((Component)lastWidgets[0], s2, s, n);
        }
        else {
            JOptionPane.showMessageDialog((Component)lastWidgets[0], s2, s, n, (Icon)o);
        }
    }
    
    @Override
    public String[] openFileDialog(final StatefulContext statefulContext, final IExpression expression, final IExpression expression2, final String s, final FileDialogType fileDialogType) {
        final IXidget focus = Creator.getToolkit().getFeature(IFocusFeature.class).getFocus();
        final JFileChooser fileChooser = new JFileChooser((expression != null) ? expression.evaluateString(statefulContext) : ".");
        fileChooser.setMultiSelectionEnabled(fileDialogType == FileDialogType.openMany);
        if (fileDialogType != FileDialogType.save) {
            fileChooser.setFileSelectionMode(2);
        }
        if (expression2 != null) {
            fileChooser.setFileFilter(new ExpressionFileFilter(statefulContext, expression2, s));
        }
        final IWidgetCreationFeature widgetCreationFeature = focus.getFeature(IWidgetCreationFeature.class);
        if (widgetCreationFeature == null) {
            return new String[0];
        }
        final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
        if (lastWidgets.length == 0) {
            return new String[0];
        }
        if (((fileDialogType == FileDialogType.save) ? fileChooser.showSaveDialog((Component)lastWidgets[0]) : fileChooser.showOpenDialog((Component)lastWidgets[0])) != 0) {
            return new String[0];
        }
        if (expression != null && expression.getType(statefulContext) == IExpression.ResultType.NODES) {
            final IModelObject queryFirst = expression.queryFirst(statefulContext);
            if (queryFirst != null) {
                queryFirst.setValue(fileChooser.getCurrentDirectory());
            }
        }
        if (fileDialogType == FileDialogType.openMany) {
            final File[] selectedFiles = fileChooser.getSelectedFiles();
            final String[] array = new String[selectedFiles.length];
            for (int i = 0; i < selectedFiles.length; ++i) {
                array[i] = selectedFiles[i].getPath();
            }
            return array;
        }
        return new String[] { fileChooser.getSelectedFile().getPath() };
    }
    
    private static class ExpressionFileFilter extends FileFilter
    {
        private StatefulContext parent;
        private IExpression filter;
        private String description;
        
        public ExpressionFileFilter(final StatefulContext statefulContext, final IExpression filter, final String description) {
            this.filter = filter;
            this.parent = new StatefulContext(statefulContext);
            this.description = description;
        }
        
        @Override
        public String getDescription() {
            return this.description;
        }
        
        @Override
        public boolean accept(final File file) {
            this.parent.set("v", file.getPath());
            return this.filter.evaluateBoolean(this.parent);
        }
    }
}
