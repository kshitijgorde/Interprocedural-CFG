import java.awt.Container;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.DataOutputStream;
import java.util.zip.ZipOutputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.Reader;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.io.File;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotterPanel extends WPanel implements ActionListener
{
    private PlotterCanvas plotComp;
    protected AppInterface I;
    private File lastFile;
    public Frame mainFrame;
    private FormulaToolBarFactory formulaFactory;
    private WToolBar toolbar;
    private WPanel sponsorPanel;
    static int Z;
    private boolean first;
    private static boolean background2D;
    private static boolean background3D;
    private static int choice2D;
    private static int choice3D;
    private MessageBox saving;
    
    public PlotterPanel() {
        this.lastFile = null;
        this.first = true;
    }
    
    public PlotterPanel(final PlotCanvas plotCanvas) {
        this.lastFile = null;
        this.first = true;
        this.I(plotCanvas, plotCanvas.I);
    }
    
    protected final void I(final PlotterCanvas plotComp, final AppInterface i) {
        this.I = i;
        this.setPlotComp(plotComp);
        this.formulaFactory = FormulaToolBarFactory.getInstance(i.getResourceProvider());
        this.setLayout(new GridLayout(1, 1));
        Component component = new LightPanel(new BorderLayout());
        this.add(new WToolTipPanel(component));
        if (plotComp instanceof PlotCanvas) {
            final boolean z = ((PlotCanvas)plotComp).Z;
        }
        this.Z();
        ((LightPanel)component).setToolTipComp(this.toolbar.getToolTipComp());
        final FormulaToolBarFactory formulaFactory = this.formulaFactory;
        if (FormulaToolBarFactory.sponsorOrgImage != null) {
            (this.sponsorPanel = new WBackPanel(new FlowLayout(0, 0, 0))).setBackground(Configuration.colorBarraPlotter);
            this.sponsorPanel.add(this.formulaFactory.createItem("sponsorlogo_org", this, 0));
            ((Container)component).add(this.sponsorPanel, "North");
            final LightPanel lightPanel = new LightPanel(new BorderLayout());
            ((Container)component).add(lightPanel, "Center");
            component = lightPanel;
        }
        if (this.toolbar.getOrientation() == 1) {
            ((Container)component).add(this.toolbar, "West");
        }
        else {
            ((Container)component).add(this.toolbar, "North");
        }
        ((Container)component).add(plotComp, "Center");
        this.reset();
    }
    
    public final void reset() {
        this.initToolBar(this.toolbar);
    }
    
    protected final void Z() {
        if (this.I != null) {
            final InputStream internalResourceStream = this.I.getResourceProvider().getInternalResourceStream(this.I());
            if (internalResourceStream == null) {
                System.err.println("Cannot read " + this.I());
                this.toolbar = new WToolBar(1);
                this.toolbar.addSection("section", 2).add(new Label("ERROR, cannot load toolbar definition file"));
            }
            else {
                InputStreamReader inputStreamReader;
                try {
                    inputStreamReader = new InputStreamReader(internalResourceStream, "ASCII");
                }
                catch (UnsupportedEncodingException ex) {
                    inputStreamReader = new InputStreamReader(internalResourceStream);
                }
                this.toolbar = this.formulaFactory.newToolBar(null, 0);
                this.toolbar.itemButtonModel.padding = new Insets(2, 3, 2, 3);
                (this.toolbar = this.formulaFactory.createToolbar(inputStreamReader, this.toolbar, this)).setBackground(Configuration.colorPlotterToolBar);
            }
        }
    }
    
    protected void initToolBar(final WToolBar wToolBar) {
        if (this.getPlotComp() instanceof PlotCanvas) {
            final PlotCanvas plotCanvas = (PlotCanvas)this.getPlotComp();
            switch (plotCanvas.mouseLabelMode) {
                case 1: {
                    this.I("actionshowname", true);
                    break;
                }
                case 2: {
                    this.I("actionshowvalue", true);
                    break;
                }
                case 3: {
                    this.I("actionshowdef", true);
                    break;
                }
            }
            this.I("grid", plotCanvas.B);
            this.I("axis", plotCanvas.C);
        }
    }
    
    protected final void I(final String s, final boolean b) {
        if (this.toolbar != null) {
            final Component component = this.toolbar.getSection(0).getComponent(s);
            if (component != null && component instanceof WButton) {
                ((WButton)component).setSelected(b, false);
            }
        }
    }
    
    public final Dimension getPreferredSize() {
        final Dimension preferredSize = this.getPlotComp().getPreferredSize();
        final Insets insets = this.getInsets();
        if (this.toolbar != null) {
            if (this.toolbar.getOrientation() == 1) {
                final Dimension dimension = preferredSize;
                dimension.width += this.toolbar.getPreferredSize().width;
            }
            else {
                final Dimension dimension2 = preferredSize;
                dimension2.height += this.toolbar.getPreferredSize().height;
            }
        }
        if (this.sponsorPanel != null) {
            final Dimension dimension3 = preferredSize;
            dimension3.height += this.sponsorPanel.getPreferredSize().height;
        }
        final Dimension dimension4 = preferredSize;
        dimension4.width += insets.left + insets.right;
        final Dimension dimension5 = preferredSize;
        dimension5.height += insets.top + insets.bottom;
        return preferredSize;
    }
    
    public final void show() {
        if (this.first) {
            this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width - 60 + 10 * PlotterPanel.Z, 60 + 20 * PlotterPanel.Z);
            PlotterPanel.Z = (PlotterPanel.Z + 1) % 5;
            this.first = false;
        }
        super.show();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final Object source = actionEvent.getSource();
        if (actionCommand.equals("save")) {
            this.save();
        }
        else {
            final ActionEvent actionEvent2 = new ActionEvent(source, actionEvent.getID(), actionCommand);
            if (actionCommand.equals("sponsorlogo_org") && this.I != null) {
                this.I.actionPerformed(actionEvent2);
            }
            else if (this.getPlotComp() instanceof ActionListener) {
                ((ActionListener)this.getPlotComp()).actionPerformed(actionEvent2);
            }
        }
        if (actionCommand.equals("recompute")) {
            this.reset();
        }
    }
    
    private void save() {
        this.saveInternet();
    }
    
    private void saveInternet() {
        final MessageBox messageBox = new MessageBox(this.mainFrame, "Options", 3);
        messageBox.add("Format");
        final Choice choice = new Choice();
        choice.add("*.jpg");
        choice.add("*.png");
        if (this.getPlotComp() instanceof PlotCanvas) {
            choice.add("*.pdf");
            choice.add("*.ps");
            choice.select(PlotterPanel.choice2D);
        }
        else {
            choice.select(PlotterPanel.choice3D);
        }
        messageBox.add(choice);
        messageBox.add("Options");
        final Checkbox checkbox = new Checkbox("Save background color");
        checkbox.setState((this.getPlotComp() instanceof PlotCanvas) ? PlotterPanel.background2D : PlotterPanel.background3D);
        messageBox.add(checkbox);
        final String showDialog = messageBox.showDialog();
        final int selectedIndex = choice.getSelectedIndex();
        if (showDialog.equals("Ok")) {
            if (this.getPlotComp() instanceof PlotCanvas) {
                PlotterPanel.choice2D = selectedIndex;
                PlotterPanel.background2D = checkbox.getState();
            }
            else {
                PlotterPanel.choice3D = selectedIndex;
                PlotterPanel.background3D = checkbox.getState();
            }
            if (this.getPlotComp() instanceof PlotCanvas && (selectedIndex == 2 || selectedIndex == 3)) {
                if (selectedIndex == 2) {
                    ((PlotCanvas)this.getPlotComp()).saveVectorial("pdf", checkbox.getState());
                }
                else {
                    ((PlotCanvas)this.getPlotComp()).saveVectorial("ps", checkbox.getState());
                }
            }
            else {
                this.saveRasterImageJava1((selectedIndex == 0) ? "jpeg" : "png", checkbox.getState());
            }
        }
    }
    
    private void saveRasterImageJava1(final String s, final boolean b) {
        new PlotterPanel$SaveImageThread(this, null).saveImage((this.getPlotComp() instanceof Canvas3d) ? ((Canvas3d)this.getPlotComp()).getImageBuffer(b) : ((PlotCanvas)this.getPlotComp()).getImageBuffer(b), s);
    }
    
    public final PlotterCanvas getPlotComp() {
        return this.plotComp;
    }
    
    public final void setPlotComp(final PlotterCanvas plotComp) {
        this.plotComp = plotComp;
    }
    
    public final String getName() {
        return this.getPlotComp().getName();
    }
    
    public final String toString() {
        return this.getName() + ":" + this.plotComp.getGroup();
    }
    
    public DataInputStream getDataFromPlotter() {
        return ((PlotCanvas)this.plotComp).getData();
    }
    
    private void writeImage(final OutputStream outputStream, final int[] array, final String s) {
        final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        final DataOutputStream dataOutputStream = new DataOutputStream(zipOutputStream);
        final ZipEntry zipEntry = new ZipEntry("image");
        final Dimension size = this.getPlotComp().getSize();
        try {
            zipOutputStream.putNextEntry(new ZipEntry("image"));
            dataOutputStream.writeUTF(s);
            dataOutputStream.writeInt(size.width);
            dataOutputStream.writeInt(size.height);
            for (int i = 0; i < array.length; ++i) {
                dataOutputStream.writeInt(array[i]);
            }
            dataOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    protected String I() {
        return "toolbar_2d.ini";
    }
    
    public void removeNotify() {
        if (!(this instanceof Dialog3d) && !this.isVisible()) {
            final PlotCanvas plotCanvas = (PlotCanvas)this.plotComp;
            if (plotCanvas != null && plotCanvas.capsaComandes instanceof CapsaComandes) {
                ((FormulaEditorCalc)plotCanvas.formula).getPlotterManager().deleteFrame(plotCanvas.capsaComandes, this, false);
            }
        }
        super.removeNotify();
    }
    
    public void dispose() {
        this.formulaFactory = null;
        this.toolbar = null;
        this.sponsorPanel = null;
        this.mainFrame = null;
        this.plotComp = null;
        this.removeAll();
    }
    
    public final String getPlotterName() {
        final PlotterCanvas plotComp = this.getPlotComp();
        if (plotComp == null) {
            return "";
        }
        return plotComp.getName();
    }
    
    public final void setGroup(final int group) {
        this.plotComp.setGroup(group);
    }
    
    public final int getGroup() {
        return this.plotComp.getGroup();
    }
    
    static final void I(final PlotterPanel plotterPanel, final OutputStream outputStream, final int[] array, final String s) {
        plotterPanel.writeImage(outputStream, array, s);
    }
    
    static final MessageBox I(final PlotterPanel plotterPanel) {
        return plotterPanel.saving;
    }
    
    static final MessageBox I(final PlotterPanel plotterPanel, final MessageBox saving) {
        return plotterPanel.saving = saving;
    }
    
    static {
        PlotterPanel.Z = 0;
        PlotterPanel.background2D = false;
        PlotterPanel.background3D = false;
        PlotterPanel.choice2D = 0;
        PlotterPanel.choice3D = 0;
    }
}
