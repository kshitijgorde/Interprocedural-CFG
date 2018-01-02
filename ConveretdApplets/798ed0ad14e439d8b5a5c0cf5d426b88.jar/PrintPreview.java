import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Container;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class PrintPreview extends Dialog implements ActionListener, ItemListener, WindowListener
{
    private Choice fontSizeChoice;
    private Choice zoomChoice;
    private TextField pageField;
    private FormulaPrintPreview preview;
    private FormulaPrintManager printManager;
    private boolean init;
    private ScrollPaneWrapper scrollPane;
    private Container mainPane;
    private Formula formula;
    
    public PrintPreview(final Frame frame, final Formula formula) {
        super(frame, "Print Preview", true);
        this.init = false;
        this.formula = formula;
        this.scrollPane = ScrollPaneWrapper.newInstance();
        this.scrollPane.getScrollPane().setCursor(Formula.WAIT_CURSOR);
        (this.mainPane = new LightPanel(new BorderLayout())).add(this.scrollPane.getScrollPane(), "Center");
        this.add(new WToolTipPanel(this.mainPane));
        this.addWindowListener(this);
    }
    
    private Component createToolBar() {
        final FormulaToolBarFactory instance = FormulaToolBarFactory.getInstance(this.formula.getResourceProvider());
        final WToolBar toolBar = instance.newToolBar(null, 0);
        final WToolPane addSection = toolBar.addSection("PrintPreview", 1);
        instance.addNewItem(addSection, "print", 1, null, this);
        instance.addNewItem(addSection, "printDialog", 1, null, this);
        addSection.addSeparator();
        instance.addNewItem(addSection, "pageSetup", 1, null, this);
        instance.addNewItem(addSection, "fontSizeLabel", 0, null, this);
        this.fontSizeChoice = new Choice();
        int i;
        for (i = 8; i < 16; i += 2) {
            this.fontSizeChoice.add(String.valueOf(i));
        }
        while (i < 24) {
            this.fontSizeChoice.add(String.valueOf(i));
            i += 4;
        }
        while (i < 40) {
            this.fontSizeChoice.add(String.valueOf(i));
            i += 4;
        }
        while (i < 100) {
            this.fontSizeChoice.add(String.valueOf(i));
            i += 10;
        }
        this.fontSizeChoice.addItemListener(this);
        this.fontSizeChoice.select("" + this.preview.getFontSize());
        addSection.add(this.fontSizeChoice);
        addSection.addSeparator();
        addSection.addGlue();
        (this.zoomChoice = new Choice()).add("25%");
        this.zoomChoice.add("40%");
        this.zoomChoice.add("50%");
        this.zoomChoice.add("60%");
        this.zoomChoice.add("75%");
        this.zoomChoice.add("90%");
        this.zoomChoice.add("100%");
        this.zoomChoice.addItemListener(this);
        this.zoomChoice.select(4);
        addSection.add(this.zoomChoice);
        addSection.addGlue();
        addSection.addSeparator();
        instance.addNewItem(addSection, "previousPage", 1, null, this);
        addSection.add(this.pageField = new TextField("00/00"));
        this.pageField.addActionListener(this);
        instance.addNewItem(addSection, "nextPage", 1, null, this);
        toolBar.setBackground(Configuration.colorMainToolBar);
        this.updateUI();
        return toolBar;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if ("print".equals(actionCommand)) {
            this.preview.print();
        }
        else if ("printDialog".equals(actionCommand)) {
            if (this.printManager.showPrintDialog()) {
                this.preview.print();
            }
        }
        else if ("pageSetup".equals(actionCommand)) {
            if (this.printManager.showPageDialog()) {
                this.preview.update();
            }
        }
        else if ("previousPage".equals(actionCommand)) {
            this.preview.setCurrentPage(this.preview.getCurrentPage() - 1);
        }
        else if ("nextPage".equals(actionCommand)) {
            this.preview.setCurrentPage(this.preview.getCurrentPage() + 1);
        }
        else if (actionEvent.getSource() == this.pageField) {
            int currentPage = this.preview.getCurrentPage();
            try {
                String s = this.pageField.getText();
                final int index = s.indexOf(47);
                if (index > 0) {
                    s = s.substring(0, index);
                }
                currentPage = Integer.parseInt(s) - 1;
            }
            catch (NumberFormatException ex) {}
            this.preview.setCurrentPage(currentPage);
        }
        this.updateUI();
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.zoomChoice) {
            final String selectedItem = this.zoomChoice.getSelectedItem();
            this.preview.setZoom(Integer.parseInt(selectedItem.substring(0, selectedItem.length() - 1)) / 100.0f);
        }
        else if (source == this.fontSizeChoice) {
            this.preview.setFontSize(Integer.parseInt(this.fontSizeChoice.getSelectedItem()));
        }
        this.updateUI();
    }
    
    private void updateUI() {
        this.pageField.setText("" + (this.preview.getCurrentPage() + 1) + '/' + this.preview.getPageNumber());
    }
    
    public final void windowOpened(final WindowEvent windowEvent) {
        final Frame frame = (Frame)this.getParent();
        try {
            this.printManager = FormulaPrintManager.newInstance(frame);
        }
        catch (SecurityException ex) {
            new MessageBox(frame, "Error", "Cannot access to the printing system due to security restriction.", 1).show();
            this.dispose();
            return;
        }
        this.preview = new FormulaPrintPreview(this.formula, this.printManager);
        this.scrollPane.setView(this.preview);
        this.mainPane.add(this.createToolBar(), "North");
        this.scrollPane.getScrollPane().setCursor(null);
        this.init = true;
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public final void windowActivated(final WindowEvent windowEvent) {
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
    }
}
