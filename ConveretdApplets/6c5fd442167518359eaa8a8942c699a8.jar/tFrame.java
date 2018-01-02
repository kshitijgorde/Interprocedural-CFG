import java.awt.TextComponent;
import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class tFrame extends Frame
{
    static final int ESCAPE = 27;
    componentQueue controlList;
    
    tFrame() {
        this.controlList = new componentQueue();
    }
    
    tFrame(final String s) {
        super(s);
        this.controlList = new componentQueue();
    }
    
    public Component add(final Component component) {
        super.add(component);
        this.controlList.addElement(component);
        return component;
    }
    
    public Component add(final Component component, final boolean b) {
        if (b) {
            this.add(component);
        }
        else {
            super.add(component);
        }
        return component;
    }
    
    public synchronized void remove(final Component component) {
        this.controlList.removeElement(component);
        super.remove(component);
    }
    
    public void show() {
        super.show();
        this.controlList.reset().requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        Label_0223: {
            switch (event.id) {
                case 1001: {
                    if (this.controlList.isElement(event.target)) {
                        this.controlList.setCurrent(event.target);
                        break;
                    }
                    break;
                }
                case 401: {
                    switch (event.key) {
                        case 9: {
                            for (int i = 0; i < this.controlList.size(); ++i) {
                                Component component;
                                if ((event.modifiers & 0x1) == 0x1) {
                                    component = this.controlList.prev();
                                }
                                else {
                                    component = this.controlList.next();
                                }
                                if (component.isVisible() && component.isEnabled()) {
                                    component.requestFocus();
                                    break;
                                }
                            }
                            return true;
                        }
                        case 27: {
                            this.hide();
                            return true;
                        }
                        default: {
                            if (event.target instanceof TextComponent && event.target != this.controlList.getCurrent()) {
                                this.controlList.setCurrent(event.target);
                                break Label_0223;
                            }
                            break Label_0223;
                        }
                    }
                    break;
                }
                case 201: {
                    this.hide();
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}
