import java.awt.Event;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.text.NumberFormat;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class EditDialog extends Dialog implements AdjustmentListener, ActionListener, ItemListener
{
    Editable elm;
    CirSim cframe;
    Button applyButton;
    Button okButton;
    EditInfo[] einfos;
    int einfocount;
    final int barmax = 1000;
    NumberFormat noCommaFormat;
    
    EditDialog(final Editable elm, final CirSim cframe) {
        super(cframe, "Edit Component", false);
        this.cframe = cframe;
        this.elm = elm;
        this.setLayout(new EditDialogLayout());
        this.einfos = new EditInfo[10];
        (this.noCommaFormat = NumberFormat.getInstance()).setMaximumFractionDigits(10);
        this.noCommaFormat.setGroupingUsed(false);
        int einfocount = 0;
        while (true) {
            this.einfos[einfocount] = this.elm.getEditInfo(einfocount);
            if (this.einfos[einfocount] == null) {
                break;
            }
            final EditInfo bar = this.einfos[einfocount];
            this.add(new Label(bar.name));
            if (bar.choice != null) {
                this.add(bar.choice);
                bar.choice.addItemListener(this);
            }
            else if (bar.checkbox != null) {
                this.add(bar.checkbox);
                bar.checkbox.addItemListener(this);
            }
            else {
                this.add(bar.textf = new TextField(this.unitString(bar), 10));
                if (bar.text != null) {
                    bar.textf.setText(bar.text);
                }
                bar.textf.addActionListener(this);
                if (bar.text == null) {
                    this.add(bar.bar = new Scrollbar(0, 50, 10, 0, 1002));
                    this.setBar(bar);
                    bar.bar.addAdjustmentListener(this);
                }
            }
            ++einfocount;
        }
        this.einfocount = einfocount;
        this.add(this.applyButton = new Button("Apply"));
        this.applyButton.addActionListener(this);
        this.add(this.okButton = new Button("OK"));
        this.okButton.addActionListener(this);
        final CirSim cframe2 = this.cframe;
        final Point locationOnScreen = CirSim.main.getLocationOnScreen();
        final Dimension size = this.getSize();
        this.setLocation(locationOnScreen.x + (this.cframe.winSize.width - size.width) / 2, locationOnScreen.y + (this.cframe.winSize.height - size.height) / 2);
    }
    
    String unitString(final EditInfo editInfo) {
        final double value = editInfo.value;
        final double abs = Math.abs(value);
        if (editInfo.dimensionless) {
            return this.noCommaFormat.format(value);
        }
        if (value == 0.0) {
            return "0";
        }
        if (abs < 1.0E-9) {
            return this.noCommaFormat.format(value * 1.0E12) + "p";
        }
        if (abs < 1.0E-6) {
            return this.noCommaFormat.format(value * 1.0E9) + "n";
        }
        if (abs < 0.001) {
            return this.noCommaFormat.format(value * 1000000.0) + "u";
        }
        if (abs < 1.0 && !editInfo.forceLargeM) {
            return this.noCommaFormat.format(value * 1000.0) + "m";
        }
        if (abs < 1000.0) {
            return this.noCommaFormat.format(value);
        }
        if (abs < 1000000.0) {
            return this.noCommaFormat.format(value * 0.001) + "k";
        }
        if (abs < 1.0E9) {
            return this.noCommaFormat.format(value * 1.0E-6) + "M";
        }
        return this.noCommaFormat.format(value * 1.0E-9) + "G";
    }
    
    double parseUnits(final EditInfo editInfo) throws ParseException {
        String s = editInfo.textf.getText().trim();
        final int length = s.length();
        final char char1 = s.charAt(length - 1);
        double n = 1.0;
        switch (char1) {
            case 80:
            case 112: {
                n = 1.0E-12;
                break;
            }
            case 78:
            case 110: {
                n = 1.0E-9;
                break;
            }
            case 85:
            case 117: {
                n = 1.0E-6;
                break;
            }
            case 109: {
                n = (editInfo.forceLargeM ? 1000000.0 : 0.001);
                break;
            }
            case 75:
            case 107: {
                n = 1000.0;
                break;
            }
            case 77: {
                n = 1000000.0;
                break;
            }
            case 71:
            case 103: {
                n = 1.0E9;
                break;
            }
        }
        if (n != 1.0) {
            s = s.substring(0, length - 1).trim();
        }
        return this.noCommaFormat.parse(s).doubleValue() * n;
    }
    
    void apply() {
        for (int i = 0; i != this.einfocount; ++i) {
            final EditInfo bar = this.einfos[i];
            if (bar.textf != null) {
                if (bar.text == null) {
                    try {
                        bar.value = this.parseUnits(bar);
                    }
                    catch (Exception ex) {}
                }
                this.elm.setEditValue(i, bar);
                if (bar.text == null) {
                    this.setBar(bar);
                }
            }
        }
        this.cframe.needAnalyze();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        for (int i = 0; i != this.einfocount; ++i) {
            final EditInfo bar = this.einfos[i];
            if (source == bar.textf) {
                if (bar.text == null) {
                    try {
                        bar.value = this.parseUnits(bar);
                    }
                    catch (Exception ex) {}
                }
                this.elm.setEditValue(i, bar);
                if (bar.text == null) {
                    this.setBar(bar);
                }
                this.cframe.needAnalyze();
            }
        }
        if (actionEvent.getSource() == this.okButton) {
            this.apply();
            final CirSim cframe = this.cframe;
            CirSim.main.requestFocus();
            this.setVisible(false);
            final CirSim cframe2 = this.cframe;
            CirSim.editDialog = null;
        }
        if (actionEvent.getSource() == this.applyButton) {
            this.apply();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        for (int i = 0; i != this.einfocount; ++i) {
            final EditInfo editInfo = this.einfos[i];
            if (editInfo.bar == source) {
                double n = editInfo.bar.getValue() / 1000.0;
                if (n < 0.0) {
                    n = 0.0;
                }
                if (n > 1.0) {
                    n = 1.0;
                }
                editInfo.value = (editInfo.maxval - editInfo.minval) * n + editInfo.minval;
                editInfo.value = Math.round(editInfo.value / editInfo.minval) * editInfo.minval;
                this.elm.setEditValue(i, editInfo);
                editInfo.textf.setText(this.unitString(editInfo));
                this.cframe.needAnalyze();
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final ItemSelectable itemSelectable = itemEvent.getItemSelectable();
        boolean b = false;
        for (int i = 0; i != this.einfocount; ++i) {
            final EditInfo editInfo = this.einfos[i];
            if (editInfo.choice == itemSelectable || editInfo.checkbox == itemSelectable) {
                this.elm.setEditValue(i, editInfo);
                if (editInfo.newDialog) {
                    b = true;
                }
                this.cframe.needAnalyze();
            }
        }
        if (b) {
            this.setVisible(false);
            final CirSim cframe = this.cframe;
            CirSim.editDialog = new EditDialog(this.elm, this.cframe);
            final CirSim cframe2 = this.cframe;
            CirSim.editDialog.show();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            final CirSim cframe = this.cframe;
            CirSim.main.requestFocus();
            this.setVisible(false);
            final CirSim cframe2 = this.cframe;
            CirSim.editDialog = null;
            return true;
        }
        return super.handleEvent(event);
    }
    
    void setBar(final EditInfo editInfo) {
        editInfo.bar.setValue((int)(1000.0 * (editInfo.value - editInfo.minval) / (editInfo.maxval - editInfo.minval)));
    }
}
