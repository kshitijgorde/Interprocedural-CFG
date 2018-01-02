import java.lang.reflect.Method;
import sunw.beanbox.PropertyHookup;
import sunw.beanbox.AppletSupport;
import java.awt.Component;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.awt.Rectangle;
import java.beans.Beans;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Hashtable;
import tmp.sunw.beanbox.___Hookup_153fea77ae;
import tmp.sunw.beanbox.___Hookup_153fea7405;
import tmp.sunw.beanbox.___Hookup_153fea70c5;
import tmp.sunw.beanbox.___Hookup_153fea80f8;
import tmp.sunw.beanbox.___Hookup_153fea84cc;
import tmp.sunw.beanbox.___Hookup_153fea8c94;
import tmp.sunw.beanbox.___Hookup_153fea889e;
import tmp.sunw.beanbox.___Hookup_153fea7b5e;
import java.io.Serializable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyApplet extends Applet implements Serializable
{
    private SPlaneBean SPlaneBean1;
    private TimeDomainBean timeDomainBean1;
    private PlotCanvasBean plotCanvasBean1;
    private MyMouseBean myMouseBean1;
    private AddRootBean addRootBean1;
    private ControlBean controlBean1;
    private ___Hookup_153fea7b5e hookup0;
    private ___Hookup_153fea889e hookup1;
    private ___Hookup_153fea8c94 hookup2;
    private ___Hookup_153fea84cc hookup3;
    private ___Hookup_153fea80f8 hookup4;
    private ___Hookup_153fea70c5 hookup5;
    private ___Hookup_153fea7405 hookup6;
    private ___Hookup_153fea77ae hookup7;
    private ClassLoader myLoader;
    Hashtable propInstances;
    static /* synthetic */ Class class$java$beans$PropertyChangeListener;
    
    public MyApplet() {
        this.propInstances = new Hashtable();
        this.setLayout(null);
        try {
            this.initContents();
        }
        catch (Exception ex) {
            System.err.println(ex);
            throw new Error("trouble initializing contents: " + ex);
        }
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(581, 458);
    }
    
    private void initContents() throws ClassNotFoundException, IOException {
        this.myLoader = this.getClass().getClassLoader();
        this.propInstances = new Hashtable();
        this.setBackground(new Color(255, 255, 255));
        this.setName("panel1");
        this.SPlaneBean1 = (SPlaneBean)Beans.instantiate(this.myLoader, "SPlaneBean");
        this.timeDomainBean1 = (TimeDomainBean)Beans.instantiate(this.myLoader, "TimeDomainBean");
        this.plotCanvasBean1 = (PlotCanvasBean)Beans.instantiate(this.myLoader, "PlotCanvasBean");
        this.myMouseBean1 = (MyMouseBean)Beans.instantiate(this.myLoader, "MyMouseBean");
        this.addRootBean1 = (AddRootBean)Beans.instantiate(this.myLoader, "AddRootBean");
        this.controlBean1 = (ControlBean)Beans.instantiate(this.myLoader, "ControlBean");
        this.acquire(this.SPlaneBean1, new Rectangle(12, 14, 181, 183));
        this.acquire(this.timeDomainBean1, new Rectangle(224, 16, 347, 180));
        this.acquire(this.plotCanvasBean1, new Rectangle(16, 212, 368, 232));
        this.acquire(this.myMouseBean1, new Rectangle(413, 231, 143, 45));
        this.acquire(this.addRootBean1, new Rectangle(443, 358, 82, 21));
        this.acquire(this.controlBean1, null);
        this.addConnections();
    }
    
    private void addConnections() {
        try {
            (this.hookup0 = new ___Hookup_153fea7b5e()).setTarget(this.controlBean1);
            this.SPlaneBean1.addBodeListener((BodeListener)this.hookup0);
            (this.hookup1 = new ___Hookup_153fea889e()).setTarget(this.myMouseBean1);
            this.SPlaneBean1.addMyMouseListener((MyMouseListener)this.hookup1);
            (this.hookup2 = new ___Hookup_153fea8c94()).setTarget(this.myMouseBean1);
            this.timeDomainBean1.addMyMouseListener((MyMouseListener)this.hookup2);
            (this.hookup3 = new ___Hookup_153fea84cc()).setTarget(this.myMouseBean1);
            this.plotCanvasBean1.addMyMouseListener((MyMouseListener)this.hookup3);
            (this.hookup4 = new ___Hookup_153fea80f8()).setTarget(this.controlBean1);
            this.addRootBean1.addBodeListener((BodeListener)this.hookup4);
            (this.hookup5 = new ___Hookup_153fea70c5()).setTarget(this.plotCanvasBean1);
            this.controlBean1.addBodeListener((BodeListener)this.hookup5);
            (this.hookup6 = new ___Hookup_153fea7405()).setTarget(this.SPlaneBean1);
            this.controlBean1.addBodeListener((BodeListener)this.hookup6);
            (this.hookup7 = new ___Hookup_153fea77ae()).setTarget(this.timeDomainBean1);
            this.controlBean1.addBodeListener((BodeListener)this.hookup7);
        }
        catch (Exception ex) {
            System.err.println("Problems adding a target: " + ex);
            ex.printStackTrace();
        }
    }
    
    private void addReconnections() {
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        final Object[] array = (Object[])objectInputStream.readObject();
        this.myLoader = this.getClass().getClassLoader();
        this.propInstances = new Hashtable();
        if (!((String)array[0]).equals("MyApplet")) {
            throw new Error("Wrong data!");
        }
        this.SPlaneBean1 = (SPlaneBean)array[1];
        this.timeDomainBean1 = (TimeDomainBean)array[2];
        this.plotCanvasBean1 = (PlotCanvasBean)array[3];
        this.myMouseBean1 = (MyMouseBean)array[4];
        this.addRootBean1 = (AddRootBean)array[5];
        this.controlBean1 = (ControlBean)array[6];
        this.acquire(this.SPlaneBean1, null);
        this.acquire(this.timeDomainBean1, null);
        this.acquire(this.plotCanvasBean1, null);
        this.acquire(this.myMouseBean1, null);
        this.acquire(this.addRootBean1, null);
        this.acquire(this.controlBean1, null);
        this.addReconnections();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(new Object[] { "MyApplet", this.SPlaneBean1, this.timeDomainBean1, this.plotCanvasBean1, this.myMouseBean1, this.addRootBean1, this.controlBean1 });
    }
    
    private void acquire(final Object o, final Rectangle bounds) {
        if (!(o instanceof Component)) {
            return;
        }
        if (o instanceof Applet) {
            AppletSupport.assignStub((Applet)o, this.myLoader, (Class)o.getClass());
        }
        this.add((Component)o);
        if (bounds != null) {
            ((Component)o).setBounds(bounds);
        }
        ((Component)o).invalidate();
        if (o instanceof Applet) {
            ((Applet)o).start();
        }
    }
    
    private PropertyHookup addPropertyTarget(final Object o, final String s, final Object o2, final String s2, final String[] array) throws Exception {
        final Object[] array2 = { null };
        final Class[] array3 = { null };
        final Class clazz = (MyApplet.class$java$beans$PropertyChangeListener != null) ? MyApplet.class$java$beans$PropertyChangeListener : (MyApplet.class$java$beans$PropertyChangeListener = class$("java.beans.PropertyChangeListener"));
        PropertyHookup propertyHookup = this.propInstances.get(o);
        if (propertyHookup == null) {
            propertyHookup = new PropertyHookup(o);
            this.propInstances.put(o, propertyHookup);
            array3[0] = clazz;
            final Method method = o.getClass().getMethod("addPropertyChangeListener", (Class<?>[])array3);
            array2[0] = propertyHookup;
            method.invoke(o, array2);
        }
        propertyHookup.attach(s, o2, o2.getClass().getMethod(s2, (Class<?>[])this.getClassFromTypes(array)));
        return propertyHookup;
    }
    
    private Class[] getClassFromTypes(final String[] array) throws Exception {
        final Class[] array2 = new Class[array.length];
        for (int i = 0; i < array2.length; ++i) {
            final Class unwrapPrimitiveStringToClass = this.unwrapPrimitiveStringToClass(array[i]);
            if (unwrapPrimitiveStringToClass == null) {
                array2[i] = this.myLoader.loadClass(array[i]);
            }
            else {
                array2[i] = unwrapPrimitiveStringToClass;
            }
        }
        return array2;
    }
    
    private Class unwrapPrimitiveStringToClass(final String s) {
        if (s.equals(Byte.TYPE.getName())) {
            return Byte.TYPE;
        }
        if (s.equals(Short.TYPE.getName())) {
            return Short.TYPE;
        }
        if (s.equals(Integer.TYPE.getName())) {
            return Integer.TYPE;
        }
        if (s.equals(Long.TYPE.getName())) {
            return Long.TYPE;
        }
        if (s.equals(Double.TYPE.getName())) {
            return Double.TYPE;
        }
        if (s.equals(Float.TYPE.getName())) {
            return Float.TYPE;
        }
        if (s.equals(Character.TYPE.getName())) {
            return Character.TYPE;
        }
        if (s.equals(Boolean.TYPE.getName())) {
            return Boolean.TYPE;
        }
        if (s.equals(Void.TYPE.getName())) {
            return Void.TYPE;
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
