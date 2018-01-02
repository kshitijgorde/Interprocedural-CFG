// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import com.mindprod.fastcat.FastCat;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import com.mindprod.common11.BigDate;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public class DateSpinner extends JPanel implements ChangeListener
{
    private static final boolean DEBUGGING = false;
    private static final BigDate HIGHEST_DISPLAYABLE_DATE;
    private static final BigDate LOWEST_DISPLAYABLE_DATE;
    private BigDate maximum;
    private BigDate minimum;
    private BigDate value;
    private transient ChangeEvent changeEvent;
    private final EventListenerList listenerList;
    private final JSpinner ddSpinner;
    private final JSpinner mmSpinner;
    private final JSpinner yyyySpinner;
    private final SpinnerNumberModel ddModel;
    private final SpinnerNumberModel mmModel;
    private final SpinnerNumberModel yyyyModel;
    private int prevMM;
    private int prevYYYY;
    private int selectedDD;
    private int selectedMM;
    private int selectedYYYY;
    
    public DateSpinner() {
        this.maximum = DateSpinner.HIGHEST_DISPLAYABLE_DATE;
        this.minimum = DateSpinner.LOWEST_DISPLAYABLE_DATE;
        this.value = BigDate.localToday();
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
        this.setLayout(new FlowLayout(0, 1, 1));
        this.yyyySpinner = new JSpinner();
        this.mmSpinner = new JSpinner();
        this.ddSpinner = new JSpinner();
        (this.yyyyModel = new SpinnerNumberModel()).setStepSize(1);
        this.yyyyModel.setValue(this.value.getYYYY());
        this.yyyySpinner.setModel(this.yyyyModel);
        this.yyyySpinner.setEditor(new LZNumberEditor(this.yyyySpinner, 4));
        this.yyyySpinner.setName("yyyySpinner");
        (this.mmModel = new SpinnerNumberModel()).setStepSize(1);
        this.mmModel.setValue(this.value.getMM());
        this.mmSpinner.setModel(this.mmModel);
        this.mmSpinner.setEditor(new LZNumberEditor(this.mmSpinner, 2));
        this.mmSpinner.setName("mmSpinner");
        (this.ddModel = new SpinnerNumberModel()).setStepSize(1);
        this.ddModel.setValue(this.value.getDD());
        this.ddSpinner.setModel(this.ddModel);
        this.ddSpinner.setEditor(new LZNumberEditor(this.ddSpinner, 2));
        this.ddSpinner.setName("ddSpinner");
        this.setValue(this.value);
        this.add(this.yyyySpinner);
        this.add(this.mmSpinner);
        this.add(this.ddSpinner);
        this.validate();
    }
    
    public void addChangeListener(final ChangeListener listener) {
        this.listenerList.add(ChangeListener.class, listener);
    }
    
    public BigDate getMaximum() {
        return (BigDate)this.maximum.clone();
    }
    
    public BigDate getMinimum() {
        return (BigDate)this.minimum.clone();
    }
    
    public BigDate getValue() {
        return (BigDate)this.value.clone();
    }
    
    public void removeChangeListener(final ChangeListener listener) {
        this.listenerList.remove(ChangeListener.class, listener);
    }
    
    public void setEnabled(final boolean enabled) {
        this.yyyySpinner.setEnabled(enabled);
        this.mmSpinner.setEnabled(enabled);
        this.ddSpinner.setEnabled(enabled);
    }
    
    public void setMaximum(final BigDate maximum) {
        assert DateSpinner.LOWEST_DISPLAYABLE_DATE.compareTo(maximum) <= 0 && maximum.compareTo(DateSpinner.HIGHEST_DISPLAYABLE_DATE) <= 0 : "DateSpinner.maximum out of range";
        this.maximum = (BigDate)maximum.clone();
        this.commonStateChanged();
        this.composeToolTip();
    }
    
    public void setMinimum(final BigDate minimum) {
        assert DateSpinner.LOWEST_DISPLAYABLE_DATE.compareTo(minimum) <= 0 && minimum.compareTo(DateSpinner.HIGHEST_DISPLAYABLE_DATE) <= 0 : "DateSpinner.minimum out of range";
        this.minimum = (BigDate)minimum.clone();
        this.commonStateChanged();
        this.composeToolTip();
    }
    
    public void setValue(final BigDate value) {
        this.value = (BigDate)value.clone();
        this.selectedYYYY = value.getYYYY();
        this.selectedMM = value.getMM();
        this.selectedDD = value.getDD();
        this.commonStateChanged();
    }
    
    public void stateChanged(final ChangeEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.yyyySpinner)) {
            this.yyyyStateChanged();
        }
        else if (source.equals(this.mmSpinner)) {
            this.mmStateChanged();
        }
        else {
            if (!source.equals(this.ddSpinner)) {
                throw new IllegalArgumentException("DataSpinner Bug: Change event from unexpected source." + source);
            }
            this.ddStateChanged();
        }
    }
    
    void commonStateChanged() {
        this.yyyySpinner.removeChangeListener(this);
        this.mmSpinner.removeChangeListener(this);
        this.ddSpinner.removeChangeListener(this);
        if (BigDate.compare(this.selectedYYYY, this.selectedMM, this.selectedDD, this.minimum.getYYYY(), this.minimum.getMM(), this.minimum.getDD()) < 0) {
            this.selectedYYYY = this.minimum.getYYYY();
            this.selectedMM = this.minimum.getMM();
            this.selectedDD = this.minimum.getDD();
        }
        if (BigDate.compare(this.selectedYYYY, this.selectedMM, this.selectedDD, this.maximum.getYYYY(), this.maximum.getMM(), this.maximum.getDD()) > 0) {
            this.selectedYYYY = this.maximum.getYYYY();
            this.selectedMM = this.maximum.getMM();
            this.selectedDD = this.maximum.getDD();
        }
        final int yyyyMinimum = this.minimum.getYYYY();
        final int yyyyMaximum = this.maximum.getYYYY();
        final int mmMinimum = (this.selectedYYYY == this.minimum.getYYYY()) ? this.minimum.getMM() : 0;
        final int mmMaximum = (this.selectedYYYY == this.maximum.getYYYY()) ? this.maximum.getMM() : 13;
        final int ddMinimum = (this.selectedYYYY == this.minimum.getYYYY() && this.selectedMM == this.minimum.getMM()) ? this.minimum.getDD() : 0;
        final int ddMaximum = (this.selectedYYYY == this.maximum.getYYYY() && this.selectedMM == this.maximum.getMM()) ? this.maximum.getDD() : (BigDate.daysInMonth(this.selectedMM, this.selectedYYYY) + 1);
        this.yyyyModel.setMinimum(yyyyMinimum);
        this.yyyyModel.setMaximum(yyyyMaximum);
        this.yyyyModel.setValue(this.selectedYYYY);
        this.mmModel.setMinimum(mmMinimum);
        this.mmModel.setMaximum(mmMaximum);
        this.mmModel.setValue(this.selectedMM);
        this.ddModel.setMinimum(ddMinimum);
        this.ddModel.setMaximum(ddMaximum);
        this.ddModel.setValue(this.selectedDD);
        this.value.set(this.selectedYYYY, this.selectedMM, this.selectedDD, 0);
        this.prevYYYY = this.selectedYYYY;
        this.prevMM = this.selectedMM;
        this.yyyySpinner.addChangeListener(this);
        this.mmSpinner.addChangeListener(this);
        this.ddSpinner.addChangeListener(this);
        this.fireStateChanged();
    }
    
    void composeToolTip() {
        final FastCat sb = new FastCat(5);
        sb.append("Enter a date between ");
        sb.append(this.minimum.toString());
        sb.append(" and ");
        sb.append(this.maximum.toString());
        sb.append(" by keying the year, then the month, then the date, or by using the spinners.");
        final String tip = sb.toString();
        this.yyyySpinner.setToolTipText(tip);
        this.mmSpinner.setToolTipText(tip);
        this.ddSpinner.setToolTipText(tip);
    }
    
    void ddStateChanged() {
        this.selectedYYYY = this.yyyyModel.getNumber().intValue();
        this.selectedMM = this.mmModel.getNumber().intValue();
        this.selectedDD = this.ddModel.getNumber().intValue();
        if (this.selectedDD <= 0) {
            --this.selectedMM;
            if (this.selectedMM <= 0) {
                this.selectedMM = 12;
                --this.selectedYYYY;
            }
            this.selectedDD = BigDate.daysInMonth(this.selectedMM, this.selectedYYYY);
        }
        else if (this.selectedDD > BigDate.daysInMonth(this.selectedMM, this.selectedYYYY)) {
            this.selectedDD = 1;
            ++this.selectedMM;
            if (this.selectedMM > 12) {
                this.selectedMM = 1;
                ++this.selectedYYYY;
            }
        }
        this.commonStateChanged();
    }
    
    private void fireStateChanged() {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listeners[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    void mmStateChanged() {
        this.selectedYYYY = this.yyyyModel.getNumber().intValue();
        this.selectedMM = this.mmModel.getNumber().intValue();
        this.selectedDD = this.ddModel.getNumber().intValue();
        if (this.selectedMM <= 0) {
            --this.selectedYYYY;
            this.selectedMM = 12;
            this.selectedDD = 31;
        }
        else if (this.selectedMM > 12) {
            ++this.selectedYYYY;
            this.selectedMM = 1;
            this.selectedDD = 1;
        }
        else if (this.selectedMM > this.prevMM) {
            this.selectedDD = 1;
        }
        else if (this.selectedMM < this.prevMM) {
            this.selectedDD = BigDate.daysInMonth(this.selectedMM, this.selectedYYYY);
        }
        this.commonStateChanged();
    }
    
    void yyyyStateChanged() {
        this.selectedYYYY = this.yyyyModel.getNumber().intValue();
        this.selectedMM = this.mmModel.getNumber().intValue();
        this.selectedDD = this.ddModel.getNumber().intValue();
        if (this.selectedYYYY > this.prevYYYY) {
            this.selectedMM = 1;
            this.selectedDD = 1;
        }
        else if (this.selectedYYYY < this.prevYYYY) {
            this.selectedMM = 12;
            this.selectedDD = 31;
        }
        this.commonStateChanged();
    }
    
    static {
        HIGHEST_DISPLAYABLE_DATE = new BigDate("9999-12-31");
        LOWEST_DISPLAYABLE_DATE = new BigDate("0001-01-01");
    }
}
