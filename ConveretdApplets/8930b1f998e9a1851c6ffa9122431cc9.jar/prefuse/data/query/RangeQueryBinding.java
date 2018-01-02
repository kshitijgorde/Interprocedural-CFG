// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import java.awt.event.FocusEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.BoundedRangeModel;
import prefuse.util.ui.JRangeSlider;
import javax.swing.JComponent;
import prefuse.util.TypeLib;
import javax.swing.event.ChangeListener;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.Expression;
import prefuse.data.expression.RangePredicate;
import prefuse.data.expression.Literal;
import prefuse.data.expression.ColumnExpression;
import prefuse.util.DataLib;
import prefuse.data.tuple.TupleSet;
import java.awt.event.FocusListener;
import prefuse.util.ui.ValuedRangeModel;

public class RangeQueryBinding extends DynamicQueryBinding
{
    private Class m_type;
    private Listener m_lstnr;
    private ValuedRangeModel m_model;
    private boolean m_ordinal;
    private static FocusListener s_sliderAdj;
    
    public RangeQueryBinding(final TupleSet set, final String s) {
        this(set, s, false);
    }
    
    public RangeQueryBinding(final TupleSet set, final String s, final boolean ordinal) {
        super(set, s);
        this.m_type = DataLib.inferType(set, s);
        this.m_ordinal = ordinal;
        this.m_lstnr = new Listener();
        this.initPredicate();
        this.initModel();
    }
    
    private void initPredicate() {
        this.setPredicate(new RangePredicate(new ColumnExpression(this.m_field), Literal.getLiteral(DataLib.min(this.m_tuples, this.m_field).get(this.m_field), this.m_type), Literal.getLiteral(DataLib.max(this.m_tuples, this.m_field).get(this.m_field), this.m_type)));
    }
    
    public void initModel() {
        if (this.m_model != null) {
            this.m_model.removeChangeListener(this.m_lstnr);
        }
        ValuedRangeModel model;
        if (TypeLib.isNumericType(this.m_type) && !this.m_ordinal) {
            final Number n = (Number)DataLib.min(this.m_tuples, this.m_field).get(this.m_field);
            final Number n2 = (Number)DataLib.max(this.m_tuples, this.m_field).get(this.m_field);
            model = new NumberRangeModel(n, n2, n, n2);
        }
        else {
            model = new ObjectRangeModel(DataLib.ordinalArray(this.m_tuples, this.m_field));
        }
        (this.m_model = model).addChangeListener(this.m_lstnr);
    }
    
    public ValuedRangeModel getModel() {
        return this.m_model;
    }
    
    public NumberRangeModel getNumberModel() {
        return (this.m_model instanceof NumberRangeModel) ? ((NumberRangeModel)this.m_model) : null;
    }
    
    public ObjectRangeModel getObjectModel() {
        return (this.m_model instanceof ObjectRangeModel) ? ((ObjectRangeModel)this.m_model) : null;
    }
    
    public JComponent createComponent() {
        return this.createHorizontalRangeSlider();
    }
    
    public JRangeSlider createHorizontalRangeSlider() {
        return this.createRangeSlider(1, 0);
    }
    
    public JRangeSlider createVerticalRangeSlider() {
        return this.createRangeSlider(0, 1);
    }
    
    public JRangeSlider createRangeSlider(final int n, final int n2) {
        return new JRangeSlider(this.m_model, n, n2);
    }
    
    public JSlider createSlider() {
        final JSlider slider = new JSlider(this.m_model);
        slider.addFocusListener(getSliderAdjuster());
        return slider;
    }
    
    private static synchronized FocusListener getSliderAdjuster() {
        if (RangeQueryBinding.s_sliderAdj == null) {
            RangeQueryBinding.s_sliderAdj = new SliderAdjuster();
        }
        return RangeQueryBinding.s_sliderAdj;
    }
    
    private class Listener implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            final ValuedRangeModel valuedRangeModel = (ValuedRangeModel)changeEvent.getSource();
            final Object lowValue = valuedRangeModel.getLowValue();
            final Object highValue = valuedRangeModel.getHighValue();
            final RangePredicate rangePredicate = (RangePredicate)RangeQueryBinding.this.m_query;
            rangePredicate.setLeftExpression(Literal.getLiteral(lowValue, RangeQueryBinding.this.m_type));
            rangePredicate.setRightExpression(Literal.getLiteral(highValue, RangeQueryBinding.this.m_type));
        }
    }
    
    private static class SliderAdjuster implements FocusListener
    {
        public void focusGained(final FocusEvent focusEvent) {
            ((JSlider)focusEvent.getSource()).setExtent(0);
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
    }
}
