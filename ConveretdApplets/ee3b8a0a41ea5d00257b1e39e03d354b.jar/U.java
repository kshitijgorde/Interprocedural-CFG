import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import I.I;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class U extends Container
{
    private H add;
    private CI addMouseListener;
    private boolean addMouseMotionListener;
    private boolean getProperty;
    private boolean getSize;
    private Dimension indexOf;
    private int setBounds;
    private int setLayout;
    private int setSize;
    private int I;
    private int Z;
    private int C;
    
    public U(final CI addMouseListener, final H add) {
        this.indexOf = new Dimension();
        this.setLayout(null);
        this.addMouseMotionListener = (null != addMouseListener);
        this.getProperty = (null != add);
        if (0 <= System.getProperty(I.I.I(183)).indexOf(I.I.I(437)) || !ztmPlayer.II) {
            if (this.getProperty) {
                this.add(add);
            }
            if (this.addMouseMotionListener) {
                this.add(addMouseListener, -1);
            }
        }
        else {
            if (this.addMouseMotionListener) {
                this.add(addMouseListener);
            }
            if (this.getProperty) {
                this.add(add, -1);
            }
        }
        this.addMouseListener = addMouseListener;
        this.add = add;
        if (this.getProperty) {
            this.addMouseListener(add);
            this.addMouseMotionListener(add);
        }
        if (this.addMouseMotionListener && this.getProperty) {
            this.addMouseListener.addMouseListener(add);
            this.addMouseListener.addMouseMotionListener(add);
        }
    }
    
    public final void I(final boolean getSize) {
        this.getSize = getSize;
    }
    
    public final void setSize(final Dimension dimension) {
        this.setSize(dimension.width, dimension.height);
    }
    
    public final void setSize(int n, int n2) {
        if (n < 16) {
            n = 16;
        }
        if (n2 < 16) {
            n2 = 16;
        }
        if (this.add.Z()) {
            final H add = this.add;
            if (1 == this.add.B()) {
                this.indexOf.width = n;
                this.indexOf.height = n2 - this.add.C();
                final boolean b = false;
                this.setLayout = (b ? 1 : 0);
                this.setBounds = (b ? 1 : 0);
            }
            final H add2 = this.add;
            if (0 == this.add.B()) {
                this.indexOf.width = n;
                this.indexOf.height = n2 - this.add.C();
                this.setBounds = 0;
                this.setLayout = this.add.C();
            }
            final H add3 = this.add;
            if (2 == this.add.B()) {
                this.indexOf.width = n - this.add.C();
                this.indexOf.height = n2;
                this.setBounds = this.add.C();
                this.setLayout = 0;
            }
            final H add4 = this.add;
            if (3 == this.add.B()) {
                this.indexOf.width = n - this.add.C();
                this.indexOf.height = n2;
                final boolean b2 = false;
                this.setLayout = (b2 ? 1 : 0);
                this.setBounds = (b2 ? 1 : 0);
            }
        }
        else {
            this.indexOf.width = n;
            this.indexOf.height = n2;
            final boolean b3 = false;
            this.setLayout = (b3 ? 1 : 0);
            this.setBounds = (b3 ? 1 : 0);
        }
        if (this.indexOf.width <= 0) {
            this.indexOf.width = n;
        }
        if (this.indexOf.height <= 0) {
            this.indexOf.height = n2;
        }
        if (this.addMouseMotionListener) {
            final Dimension z = this.addMouseListener.Z();
            if (this.getSize) {
                double n3;
                if (z.width / z.height > this.indexOf.width / this.indexOf.height) {
                    n3 = this.indexOf.width / z.width;
                }
                else {
                    n3 = this.indexOf.height / z.height;
                }
                this.C = (int)(z.width * n3);
                this.Z = (int)(z.height * n3);
            }
            else {
                this.Z = z.height;
                this.C = z.width;
            }
            if (this.C < this.indexOf.width && 0 != this.C) {
                this.setSize = (this.indexOf.width - this.C) / 2;
            }
            else {
                this.setSize = 0;
            }
            if (this.Z < this.indexOf.height && 0 != this.Z) {
                this.I = (this.indexOf.height - this.Z) / 2;
            }
            else {
                this.I = 0;
            }
            this.addMouseListener.setBounds(this.setBounds + this.setSize, this.setLayout + this.I, this.C, this.Z);
        }
        if (this.getProperty) {
            this.add.setBounds(0, 0, n, n2);
        }
    }
    
    public final synchronized void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.setSize(n3, n4);
        super.setBounds(n, n2, n3, n4);
    }
    
    public final void I(final int c, final int z) {
        this.C = c;
        this.Z = z;
        this.I(false);
        this.addMouseListener.setSize(c, z);
        this.setSize(this.getSize().width, this.getSize().height);
    }
}
