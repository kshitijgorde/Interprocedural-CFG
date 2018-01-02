// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Switch extends Group implements FieldObserver
{
    public final Node[] defaultChoiceArray;
    public final NodeArrayField choice;
    public final IntField whichChoice;
    
    public void addChildren(final Node[] array) {
        throw new Shout3DException("Can not call addChildren on a Switch node. Use choice and whichChoice fields instead.");
    }
    
    public Switch() {
        this.defaultChoiceArray = new Node[0];
        this.choice = new NodeArrayField(this, "choice", 0, this.defaultChoiceArray);
        (this.whichChoice = new IntField(this, "whichChoice", 0, -1)).addFieldObserver(this, null);
        this.choice.addFieldObserver(this, null);
        super.children.addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (field == this.choice || field == this.whichChoice || field == super.children) {
            if (this.choice == null || this.whichChoice.getValue() < 0 || this.whichChoice.getValue() >= this.choice.getValue().length) {
                if (super.children.getValue() != super.defaultChildArray) {
                    super.children.setValue(super.defaultChildArray);
                }
            }
            else {
                final Node node = this.choice.getValue()[this.whichChoice.getValue()];
                if (super.children.getValue() == null || super.children.getValue().length != 1 || super.children.getValue()[0] != node) {
                    super.children.setValue(new Node[] { node });
                }
            }
        }
    }
    
    public void removeChildren(final Node[] array) {
        throw new Shout3DException("Can not call removeChildren on a Switch node. Use choice and whichChoice fields instead.");
    }
}
