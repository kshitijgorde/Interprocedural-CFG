// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

public class ActionSwitch extends CompositeAction
{
    private int m_switchVal;
    
    public ActionSwitch() {
        this.m_switchVal = 0;
    }
    
    public ActionSwitch(final Action[] array, final int switchValue) {
        for (int i = 0; i < array.length; ++i) {
            this.m_actions.add(array[i]);
        }
        this.setSwitchValue(switchValue);
    }
    
    public void run(final double n) {
        if (this.m_actions.size() > 0) {
            this.get(this.getSwitchValue()).run(n);
        }
    }
    
    public int getSwitchValue() {
        return this.m_switchVal;
    }
    
    public void setSwitchValue(final int switchVal) {
        if (switchVal < 0 || switchVal >= this.size()) {
            throw new IllegalArgumentException("Switch value out of legal range");
        }
        this.m_switchVal = switchVal;
    }
}
