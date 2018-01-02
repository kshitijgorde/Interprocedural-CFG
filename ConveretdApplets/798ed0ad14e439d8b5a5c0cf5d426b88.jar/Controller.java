import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemEvent;
import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class Controller implements ActionListener, ItemListener, PropertyChangeListener
{
    protected FormulaEditorCalc I;
    private WEditorToolBar toolbar;
    protected WList Z;
    protected WList C;
    protected WList B;
    protected WList D;
    protected WList F;
    protected WButton J;
    protected WButton S;
    protected WButton A;
    protected WButton E;
    protected ColorDialog Composa;
    protected ColorDialog InsertString;
    protected FormulaToolBarFactory actionPerformed;
    
    Controller(final FormulaEditorCalc i, final FormulaToolBarFactory actionPerformed, final WEditorToolBar toolbar) {
        this.I = i;
        this.toolbar = toolbar;
        this.actionPerformed = actionPerformed;
    }
    
    public final void setFormulaEditor(final FormulaEditorCalc i) {
        this.I = i;
    }
    
    private boolean ensureColorDialog() {
        final Frame mainFrame = this.I.getMainFrame();
        if (mainFrame == null) {
            return false;
        }
        if (this.Composa == null || this.Composa.getParent() != mainFrame) {
            this.InsertString = new ColorDialogSeleccio(mainFrame);
            this.Composa = new ColorDialogTot(mainFrame);
        }
        return true;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.C) {
            this.applyFont(4);
        }
        else if (source == this.B) {
            this.applyFont(8);
        }
        else if (source == this.D) {
            final String selectedItem = this.D.getSelectedItem();
            final String substring = selectedItem.substring(0, selectedItem.length() - 1);
            final Integer n = new Integer(this.actionPerformed.getZoom());
            final Integer n2 = new Integer(substring);
            this.actionPerformed.setZoom(n2);
            this.actionPerformed.I(this.toolbar);
            this.actionPerformed.rebuild(this.toolbar, this, this.toolbar.getOrientation());
            if (this.I.context != null) {
                this.I.context.propertyChange(new PropertyChangeEvent(this.toolbar, "zoom", n, n2));
            }
            return;
        }
        if (source == this.J) {
            this.I.setDesign(this.J.isSelected());
        }
        this.I.requestFocus();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        String s = "";
        boolean dialogShown = false;
        if (source == this.S) {
            this.applyFont(1);
        }
        else if (source == this.A) {
            this.applyFont(2);
        }
        else {
            try {
                s = (String)((WButton)source).getClass().getMethod("getName", (Class<?>[])null).invoke(source, (Object[])null);
            }
            catch (Exception ex) {}
            final String actionCommand = actionEvent.getActionCommand();
            if ("__unit__".equals(actionCommand)) {
                String s2 = null;
                final FormulaToolBarFactory actionPerformed = this.actionPerformed;
                final String s3 = FormulaToolBarFactory.I.get(s);
                if (s3 != null) {
                    if (this.Z != null) {
                        final int selectedIndex = this.Z.getSelectedIndex();
                        if (selectedIndex >= 0) {
                            final FormulaToolBarFactory actionPerformed2 = this.actionPerformed;
                            final String s4 = FormulaToolBarFactory.Z.elementAt(selectedIndex);
                            final FormulaToolBarFactory actionPerformed3 = this.actionPerformed;
                            s2 = (String)FormulaToolBarFactory.I.get(s4);
                            final WList z = this.Z;
                            final FormulaToolBarFactory actionPerformed4 = this.actionPerformed;
                            z.setSelectedItem(" ", true);
                        }
                    }
                    this.I.InsertString(this.actionPerformed.I(s2, s3));
                    this.I.repaint();
                }
            }
            else if (s.equals("colors")) {
                if (!this.ensureColorDialog()) {
                    return;
                }
                if (this.F != null && this.F.getSelectedIndex() == 0) {
                    this.Composa.mostra(this.I);
                }
                else {
                    this.InsertString.mostra(this.I);
                }
                dialogShown = true;
            }
            else if (!s.equals("localcomment")) {
                this.fireAction(actionCommand);
                dialogShown = this.I.dialogShown;
            }
        }
        if (dialogShown && source instanceof MouseListener) {
            ((WButton)source).mouseExited(null);
        }
    }
    
    private void fireAction(final String s) {
        this.I.actionPerformed(new ActionEvent(this, 1001, s));
    }
    
    private void applyFont(final int n) {
        final Font defaultFont = this.I.getDefaultFont(4, null);
        String s;
        if (this.C != null) {
            s = this.C.getSelectedItem();
        }
        else {
            s = defaultFont.getName();
        }
        int n2;
        if (this.B != null) {
            n2 = Integer.parseInt(this.B.getSelectedItem());
        }
        else {
            n2 = defaultFont.getSize();
        }
        int style = defaultFont.getStyle();
        if (this.S != null) {
            if (this.S.isSelected()) {
                style |= 0x1;
            }
            else {
                style &= 0xFFFFFFFE;
            }
        }
        if (this.A != null) {
            if (this.A.isSelected()) {
                style |= 0x2;
            }
            else {
                style &= 0xFFFFFFFD;
            }
        }
        final Font font = new Font(s, style, n2);
        final boolean b = this.F != null && this.F.getSelectedIndex() == 0;
        if (!b) {
            this.I.canviaAtributSeleccio(new Attributes(font, n));
        }
        if (b) {
            this.I.setDefaultFont(4, new Font(s, style, n2));
            if (this.I.lacapsa != null) {
                this.I.lacapsa.Composa(this.I);
                this.I.resetPosition();
            }
        }
        this.I.repaint();
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("activeFormula")) {
            final FormulaEditorCalc i = (FormulaEditorCalc)propertyChangeEvent.getNewValue();
            final AppInterface context = i.context;
            final AppInterface context2 = this.I.context;
            if (context2 != null && context != null) {
                final String stringProperty = context2.getStringProperty("toolbarType", "fix");
                if (stringProperty.equals("floating") && stringProperty.equals(context.getStringProperty("toolbarType", null))) {
                    this.I.removePropertyChangeListener(this);
                    (this.I = i).addPropertyChangeListener(this);
                }
            }
        }
        else if (propertyChangeEvent.getPropertyName().equals("position")) {
            boolean b = false;
            final BoxPosition boxPosition = (BoxPosition)propertyChangeEvent.getNewValue();
            if (!this.I.isSelectionEmpty()) {
                if (this.F != null) {
                    this.F.setSelectedIndex(1, false);
                }
            }
            else if (this.F != null) {
                this.F.setSelectedIndex(0, false);
                b = true;
            }
            try {
                Attributes insertStringFormat = null;
                if (b) {
                    if (this.I.lacapsa != null) {
                        insertStringFormat = new Attributes(this.I.getDefaultFont(4, null), 15, null, this.I.lacapsa.estil, 0);
                    }
                }
                else if (boxPosition != null) {
                    insertStringFormat = this.I.getInsertStringFormat();
                }
                if (insertStringFormat != null) {
                    this.I(insertStringFormat);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    protected final void I(final Attributes attributes) {
        Font font;
        if (attributes != null) {
            if (attributes.font == null) {
                font = this.I.getDefaultFont(4, null);
            }
            else {
                font = attributes.font;
            }
            if (this.E != null) {
                this.E.setSelected((attributes.estil & 0x1) != 0x0, false);
            }
            if (attributes.color != null && this.ensureColorDialog()) {
                this.InsertString.setColor(attributes.color);
            }
        }
        else {
            font = this.I.getDefaultFont(4, null);
        }
        if (this.S != null) {
            this.S.setSelected((font.getStyle() & 0x1) == 0x1, false);
        }
        if (this.A != null) {
            this.A.setSelected((font.getStyle() & 0x2) == 0x2, false);
        }
        if (this.C != null && !this.C.getSelectedItem().equals(font.getName())) {
            this.C.setSelectedItem(font.getName(), false);
        }
        if (this.B != null && !this.B.getSelectedItem().equals(String.valueOf(font.getSize()))) {
            this.B.setSelectedItem(String.valueOf(font.getSize()), false);
        }
    }
}
