// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.awt.Image;
import java.beans.SimpleBeanInfo;

public class b extends SimpleBeanInfo
{
    private Image if;
    private static PropertyDescriptor[] do;
    private static EventSetDescriptor[] a;
    static /* synthetic */ Class class$org$netbeans$examples$lib$timerbean$Timer;
    static /* synthetic */ Class class$java$awt$event$ActionEvent;
    static /* synthetic */ Class class$org$netbeans$examples$lib$timerbean$TimerListener;
    
    public b() {
        this.if = this.loadImage("/org/netbeans/examples/lib/timerbean/timer.gif");
    }
    
    public Image getIcon(final int n) {
        return this.if;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        return b.do;
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        return b.a;
    }
    
    public int getDefaultEventIndex() {
        return 0;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        try {
            (b.do = new PropertyDescriptor[2])[0] = new PropertyDescriptor("Delay", (b.class$org$netbeans$examples$lib$timerbean$Timer == null) ? (b.class$org$netbeans$examples$lib$timerbean$Timer = class$("org.netbeans.examples.lib.timerbean.Timer")) : b.class$org$netbeans$examples$lib$timerbean$Timer, "getDelay", "setDelay");
            b.do[1] = new PropertyDescriptor("Once Only", (b.class$org$netbeans$examples$lib$timerbean$Timer == null) ? (b.class$org$netbeans$examples$lib$timerbean$Timer = class$("org.netbeans.examples.lib.timerbean.Timer")) : b.class$org$netbeans$examples$lib$timerbean$Timer, "getOnceOnly", "setOnceOnly");
            (b.a = new EventSetDescriptor[1])[0] = new EventSetDescriptor("timer", (b.class$org$netbeans$examples$lib$timerbean$TimerListener == null) ? (b.class$org$netbeans$examples$lib$timerbean$TimerListener = class$("org.netbeans.examples.lib.timerbean.TimerListener")) : b.class$org$netbeans$examples$lib$timerbean$TimerListener, new Method[] { ((b.class$org$netbeans$examples$lib$timerbean$TimerListener == null) ? (b.class$org$netbeans$examples$lib$timerbean$TimerListener = class$("org.netbeans.examples.lib.timerbean.TimerListener")) : b.class$org$netbeans$examples$lib$timerbean$TimerListener).getMethod("onTime", (b.class$java$awt$event$ActionEvent == null) ? (b.class$java$awt$event$ActionEvent = class$("java.awt.event.ActionEvent")) : b.class$java$awt$event$ActionEvent) }, ((b.class$org$netbeans$examples$lib$timerbean$Timer == null) ? (b.class$org$netbeans$examples$lib$timerbean$Timer = class$("org.netbeans.examples.lib.timerbean.Timer")) : b.class$org$netbeans$examples$lib$timerbean$Timer).getMethod("addTimerListener", (b.class$org$netbeans$examples$lib$timerbean$TimerListener == null) ? (b.class$org$netbeans$examples$lib$timerbean$TimerListener = class$("org.netbeans.examples.lib.timerbean.TimerListener")) : b.class$org$netbeans$examples$lib$timerbean$TimerListener), ((b.class$org$netbeans$examples$lib$timerbean$Timer == null) ? (b.class$org$netbeans$examples$lib$timerbean$Timer = class$("org.netbeans.examples.lib.timerbean.Timer")) : b.class$org$netbeans$examples$lib$timerbean$Timer).getMethod("removeTimerListener", (b.class$org$netbeans$examples$lib$timerbean$TimerListener == null) ? (b.class$org$netbeans$examples$lib$timerbean$TimerListener = class$("org.netbeans.examples.lib.timerbean.TimerListener")) : b.class$org$netbeans$examples$lib$timerbean$TimerListener));
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        catch (NoSuchMethodException ex2) {
            ex2.printStackTrace();
        }
    }
}
