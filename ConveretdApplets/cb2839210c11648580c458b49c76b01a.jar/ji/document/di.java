// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import ji.v1event.c9;
import ji.v1base.bz;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import ji.util.e;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ji.util.i;
import ji.util.d;
import java.awt.Component;
import java.awt.LayoutManager;
import ji.awt.c;
import ji.v1base.jiPanel;

public class di extends jiPanel
{
    String a;
    uo b;
    uo c;
    uo d;
    uo e;
    uo f;
    uo g;
    c h;
    int i;
    private boolean j;
    boolean k;
    private ad l;
    
    public di(final String a, final ad l) {
        super(a);
        this.a = null;
        this.i = 0;
        this.j = false;
        this.k = false;
        this.l = null;
        this.l = l;
        try {
            this.a = a;
            this.setLayout(null);
            super.setBorderStyle(0);
            this.c = new uo(a, 2, 0, 627, l);
            this.d = new uo(a, 3, 1, 626, l);
            this.e = new uo(a, 6, 4, 1296, l);
            this.b = new uo(a, 1, -1, -1, l);
            this.f = new uo(a, 4, 2, 1151, l);
            this.g = new uo(a, 5, 3, 1152, l);
            this.add(this.c);
            this.add(this.d);
            this.add(this.f);
            this.add(this.g);
            if (this.e != null) {
                this.add(this.e);
            }
            this.add(this.b);
            this.c();
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public boolean a(final ad ad) {
        this.k = ji.util.i.c(115);
        if (this.k) {
            if (this.k && ji.util.i.c(118)) {
                this.f.setVisible(true);
            }
            else {
                this.f.setVisible(false);
            }
            if (this.k && ji.util.i.c(119)) {
                this.g.setVisible(true);
            }
            else {
                this.g.setVisible(false);
            }
        }
        if (ji.util.i.c(116)) {
            this.c.setVisible(true);
        }
        else {
            this.c.setVisible(false);
        }
        if (ji.util.i.c(117)) {
            this.d.setVisible(true);
        }
        else {
            this.d.setVisible(false);
        }
        if (this.e != null) {
            if (ad != null && ad.ck()) {
                this.e.setVisible(true);
            }
            else {
                this.e.setVisible(false);
            }
        }
        return true;
    }
    
    public final void a(final boolean j) {
        this.j = j;
    }
    
    private final void b() {
        if (!this.j) {
            ji.util.d.ex();
        }
    }
    
    private final void c() {
        try {
            if (this.b != null) {
                switch (this.i) {
                    case 0: {
                        this.b.a(624);
                        break;
                    }
                    case 1: {
                        this.b.a(625);
                        break;
                    }
                    case 2: {
                        this.b.a(1153);
                        break;
                    }
                    case 3: {
                        this.b.a(1154);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void releaseResources() {
        this.l = null;
        try {
            if (this.b != null) {
                this.remove(this.b);
                this.b.releaseResources();
                this.b = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.c != null) {
                this.remove(this.c);
                this.c.releaseResources();
                this.c = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.d != null) {
                this.remove(this.d);
                this.d.releaseResources();
                this.d = null;
            }
        }
        catch (Exception ex3) {}
        try {
            if (this.e != null) {
                this.remove(this.e);
                this.e.releaseResources();
                this.e = null;
            }
        }
        catch (Exception ex4) {}
        try {
            if (this.g != null) {
                this.remove(this.g);
                this.g.releaseResources();
                this.g = null;
            }
        }
        catch (Exception ex5) {}
        try {
            if (this.f != null) {
                this.remove(this.f);
                this.f.releaseResources();
                this.f = null;
            }
        }
        catch (Exception ex6) {}
        super.releaseResources();
    }
    
    public final void a(final int i) {
        try {
            if (this.i != i) {
                this.i = i;
                this.c();
                this.c.repaint();
                this.d.repaint();
                this.e.repaint();
                if (this.k) {
                    this.f.repaint();
                    this.g.repaint();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ActionListener actionListener) {
        if (this.h != null && this.h.a(actionListener)) {
            this.h.b(actionListener);
        }
    }
    
    public final void b(final ActionListener actionListener) {
        if (this.h == null) {
            this.h = new c("jiImageButton1", 2);
        }
        if (!this.h.a(actionListener)) {
            this.h.c(actionListener);
        }
    }
    
    protected final void a(final ActionEvent actionEvent) {
        if (this.h != null) {
            final c h = this.h;
            for (int b = h.b(), i = 0; i < b; ++i) {
                ((ActionListener)h.b(i)).actionPerformed(actionEvent);
            }
        }
    }
    
    private final void a(final int n, final String s) {
        this.a(new ActionEvent(this, 1001, s, n));
    }
    
    public void paintComponent(final Graphics graphics) {
        try {
            this.a(graphics);
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        try {
            super.paint(graphics);
            if (!this.isSwing()) {
                this.a(graphics);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Graphics graphics) {
        try {
            final Dimension size = this.getSize();
            if (size.width < 10) {
                return;
            }
            Color color;
            if (ji.util.e.g != null) {
                color = ji.util.e.g;
            }
            else {
                color = SystemColor.controlShadow;
            }
            Color color2;
            if (ji.util.e.h != null) {
                color2 = ji.util.e.h;
            }
            else {
                color2 = SystemColor.controlLtHighlight;
            }
            graphics.setColor(color2);
            graphics.drawLine(0, 0, 0, size.height);
            graphics.drawLine(0, 0, size.width, 0);
            graphics.setColor(color);
            graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            ji.util.e.a0();
            ji.util.e.a3();
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, size.width - 2, size.height - 2);
        }
        catch (Exception ex) {}
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
        this.e();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.e();
    }
    
    private final boolean d() {
        try {
            final Dimension size = this.getSize();
            return size.width > size.height;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    private final void e() {
        try {
            final Dimension size = this.getSize();
            if (this.d()) {
                int n = 5;
                final int n2 = 1;
                if (this.k) {
                    if (ji.util.d.b(2, this.l)) {
                        ji.util.e.a(this.f, n, n2, 20, 13);
                        n += 20;
                    }
                    if (ji.util.d.b(3, this.l)) {
                        ji.util.e.a(this.g, n, n2, 20, 13);
                        n += 20;
                    }
                }
                if (ji.util.d.b(0, this.l)) {
                    ji.util.e.a(this.c, n, n2, 20, 13);
                    n += 20;
                }
                if (ji.util.d.b(1, this.l)) {
                    ji.util.e.a(this.d, n, n2, 20, 13);
                    n += 20;
                }
                if (this.l != null && this.e != null) {
                    ji.util.e.a(this.e, n, n2, 20, 13);
                    n += 20;
                }
                ji.util.e.a(this.b, size.width - 15 - 6, n2, 15, 13);
            }
            else {
                int n3 = 1;
                final int n4 = 1;
                if (this.k) {
                    if (ji.util.d.b(2, this.l)) {
                        ji.util.e.a(this.f, n4, n3, size.width - 2, 17);
                        n3 += 17;
                    }
                    if (ji.util.d.b(3, this.l)) {
                        ji.util.e.a(this.g, n4, n3, size.width - 2, 17);
                        n3 += 17;
                    }
                }
                if (ji.util.d.b(0, this.l)) {
                    ji.util.e.a(this.c, n4, n3, size.width - 2, 17);
                    n3 += 17;
                }
                if (ji.util.d.b(1, this.l)) {
                    ji.util.e.a(this.d, n4, n3, size.width - 2, 17);
                    n3 += 17;
                }
                if (this.l != null && this.e != null) {
                    ji.util.e.a(this.e, n4, n3, size.width - 2, 17);
                    n3 += 20;
                }
                ji.util.e.a(this.b, n4, size.height - (size.width - 2), size.width - 2, 15);
            }
        }
        catch (Exception ex) {}
    }
    
    public int a() {
        return 15;
    }
    
    class uo extends bz
    {
        int a;
        adi b;
        adj c;
        adk d;
        boolean e;
        boolean f;
        boolean g;
        int h;
        ad i;
        c9 j;
        boolean k;
        
        public uo(final String s, final int a, final int h, final int n, final ad i) {
            super(s);
            this.a = 0;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = 0;
            this.i = null;
            this.j = null;
            this.i = i;
            this.a = a;
            this.h = h;
            this.setBorderStyle(0);
            this.d(true);
            this.addMouseListener(this.b = new adi(this));
            this.addFocusListener(this.c = new adj(this));
            this.addKeyListener(this.d = new adk(this));
            if (n >= 0) {
                this.a(n);
            }
            this.h();
        }
        
        private final void h() {
            if (this.j == null && this.i != null) {
                this.j = this.i.gs();
                if (this.j != null) {
                    this.a(this.i.gs());
                }
            }
        }
        
        public void a(final int n) {
            try {
                if (this.i != null) {
                    this.h();
                    final String b = ji.util.d.b(n, di.this.a);
                    super.setToolTipText(b);
                    super.b(b);
                }
            }
            catch (Exception ex) {}
        }
        
        public final void releaseResources() {
            try {
                if (this.j != null) {
                    this.b(this.j);
                }
            }
            catch (Exception ex) {}
            try {
                if (this.b != null) {
                    this.removeMouseListener(this.b);
                    this.b = null;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.c != null) {
                    this.removeFocusListener(this.c);
                    this.c = null;
                }
            }
            catch (Exception ex3) {}
            try {
                if (this.d != null) {
                    this.removeKeyListener(this.d);
                    this.d = null;
                }
            }
            catch (Exception ex4) {}
            this.i = null;
            super.releaseResources();
        }
        
        public final String getId() {
            String s = "";
            try {
                switch (this.a) {
                    case 1: {
                        s = "thumbsdelete";
                        break;
                    }
                    case 2: {
                        s = "thumbsimages";
                        break;
                    }
                    case 3: {
                        s = "thumbstext";
                        break;
                    }
                    case 4: {
                        s = "thumbsdocimages";
                        break;
                    }
                    case 5: {
                        s = "thumbsdoctext";
                        break;
                    }
                    case 6: {
                        s = "thumbsearch";
                        break;
                    }
                }
            }
            catch (Exception ex) {}
            return s;
        }
        
        public void paint(final Graphics graphics) {
            try {
                super.paint(graphics);
                if (!di.this.isSwing()) {
                    this.paintComponent(graphics);
                }
            }
            catch (Exception ex) {}
        }
        
        public void paintComponent(final Graphics graphics) {
            try {
                this.h();
                this.a(graphics, this.getSize());
                try {
                    switch (this.a) {
                        case 1: {
                            this.a(graphics);
                            break;
                        }
                        case 2: {
                            this.a(graphics, Color.black, false);
                            break;
                        }
                        case 3: {
                            this.b(graphics, Color.black, false);
                            break;
                        }
                        case 6: {
                            this.c(graphics, Color.black, false);
                            break;
                        }
                        case 4: {
                            this.a(graphics, Color.black, true);
                            break;
                        }
                        case 5: {
                            this.b(graphics, Color.black, true);
                            break;
                        }
                    }
                }
                catch (Exception ex) {}
                di.this.b();
            }
            catch (Exception ex2) {}
        }
        
        private void a(final Graphics graphics) {
            try {
                this.getSize();
                int n;
                int n4;
                int n5;
                if (di.this.d()) {
                    n = 6;
                    final int n2 = 1;
                    final int n3 = 3;
                    n4 = n2 + 2;
                    n5 = n3;
                }
                else {
                    n = 6;
                    final int n6 = 1;
                    final int n7 = 3;
                    n4 = n6 + 3;
                    n5 = n7;
                }
                graphics.setColor(Color.black);
                graphics.drawLine(n4, n5, n4 + n, n5 + n);
                graphics.drawLine(1 + n4, n5, 1 + n4 + n, n5 + n);
                graphics.drawLine(n4, n5 + n, n4 + n, n5);
                graphics.drawLine(1 + n4, n5 + n, 1 + n4 + n, n5);
            }
            catch (Exception ex) {}
        }
        
        private final void a(final Graphics graphics, final Dimension dimension) {
            Color color;
            if (ji.util.e.g != null) {
                color = ji.util.e.g;
            }
            else {
                color = SystemColor.controlShadow;
            }
            Color color2;
            if (ji.util.e.h != null) {
                color2 = ji.util.e.h;
            }
            else {
                color2 = SystemColor.controlLtHighlight;
            }
            if (this.g || di.this.i == this.h) {
                graphics.setColor(color);
            }
            else if (this.f) {
                graphics.setColor(color2);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawLine(0, 0, 0, dimension.height);
            graphics.drawLine(0, 0, dimension.width, 0);
            if (this.g || di.this.i == this.h) {
                graphics.setColor(color2);
            }
            else if (this.f) {
                graphics.setColor(color);
            }
            graphics.drawLine(dimension.width - 1, 0, dimension.width - 1, dimension.height);
            graphics.drawLine(0, dimension.height - 1, dimension.width, dimension.height - 1);
            if (this.f) {
                if (ji.util.i.c(7) && ji.util.e.t() && ji.util.d.ar()) {
                    ji.util.d.a(graphics, new Rectangle(1, 1, dimension.width - 3, dimension.height - 2), ji.util.e.a0(), ji.util.e.a3(), true);
                }
                else {
                    graphics.setColor(ji.util.e.a0());
                    graphics.fillRect(1, 1, dimension.width - 2, dimension.height - 2);
                }
            }
            else {
                graphics.setColor(this.getBackground());
                graphics.fillRect(1, 1, dimension.width - 2, dimension.height - 2);
            }
            if (this.e) {
                graphics.setColor(this.getForeground());
                ji.util.d.c(graphics, 1, 1, dimension.width - 4, dimension.height - 4);
            }
            di.this.b();
        }
        
        private void a(final Graphics graphics, final Color color, final boolean b) {
            try {
                final Dimension size = this.getSize();
                graphics.setColor(Color.black);
                int n;
                int n2;
                int n3;
                int n4;
                if (di.this.d()) {
                    n = 4;
                    n2 = 3;
                    n3 = 10;
                    n4 = size.height - 7;
                }
                else {
                    n = 3;
                    n2 = 3;
                    n3 = 8;
                    n4 = size.height - 8;
                }
                final Color color2 = graphics.getColor();
                if (b) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 2, n2 + 2, n3 - 1, n4 - 1);
                    graphics.setColor(color);
                    graphics.drawRect(n + 2, n2 + 2, n3 - 1, n4 - 1);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
                    graphics.setColor(color);
                    graphics.drawRect(n, n2, n3 - 1, n4 - 1);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
                    graphics.setColor(color);
                    graphics.drawRect(n, n2, n3, n4);
                }
                graphics.setColor(color2);
            }
            catch (Exception ex) {}
        }
        
        private void b(final Graphics graphics, final Color color, final boolean b) {
            try {
                final Dimension size = this.getSize();
                final Color color2 = graphics.getColor();
                graphics.setColor(color);
                int n;
                int n2;
                int n3;
                int n4;
                if (di.this.d()) {
                    n = 4;
                    n2 = 3;
                    n3 = 14;
                    n4 = size.height - 2;
                }
                else {
                    n = 3;
                    n2 = 4;
                    n3 = 11;
                    n4 = size.height - 4;
                }
                if (b) {
                    int n5 = 0;
                    for (int i = n2; i < n4; i += 2) {
                        graphics.drawLine(n + n5, i, n3 - 4 + n5, i);
                        ++n5;
                    }
                }
                else {
                    for (int j = n2; j < n4; j += 2) {
                        graphics.drawLine(n, j, n3, j);
                    }
                }
                graphics.setColor(color2);
            }
            catch (Exception ex) {}
        }
        
        private void c(final Graphics graphics, final Color color, final boolean b) {
            try {
                this.k = ji.util.d.a(graphics);
                final Dimension size = this.getSize();
                final Color color2 = graphics.getColor();
                final int n = 6;
                final int n2 = 2;
                final int n3 = 8;
                final int height = size.height;
                final int n4 = n + n3 - 8 - 2;
                final int n5 = n2 + (height - 8) / 2 - 2;
                final int n6 = n4 + 4;
                final int n7 = n5 + 4;
                final int n8 = n6;
                final int n9 = n7 - 1;
                final int n10 = n8 + 5;
                final int n11 = n10 + 1;
                graphics.setColor(Color.black);
                graphics.drawLine(n8, n9, n10, n11);
                graphics.drawLine(n8, n9 - 1, n10, n11 - 1);
                graphics.setColor(Color.black);
                graphics.fillOval(n4, n5, 8, 8);
                graphics.setColor(this.getBackground());
                if (this.k) {
                    graphics.fillOval(n4 + 1, n5 + 1, 6, 6);
                }
                else {
                    graphics.fillOval(n4 + 2, n5 + 2, 4, 4);
                }
                graphics.setColor(color2);
            }
            catch (Exception ex) {}
        }
        
        class adk implements KeyListener
        {
            uo a;
            
            public adk(final uo a) {
                this.a = a;
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
                try {
                    if (keyEvent.getKeyCode() == 10 && this.a != null) {
                        di.this.a(keyEvent.getModifiers(), this.a.getId());
                    }
                }
                catch (Exception ex) {}
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
            }
        }
        
        class adi implements MouseListener
        {
            uo a;
            
            public adi(final uo a) {
                this.a = a;
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                try {
                    uo.this.g = true;
                    if (this.a != null) {
                        this.a.paintComponent(this.a.getGraphics());
                    }
                }
                catch (Exception ex) {}
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                try {
                    uo.this.g = false;
                    if (this.a != null) {
                        this.a.repaint();
                    }
                    if (uo.this.f && this.a != null) {
                        di.this.a(mouseEvent.getModifiers(), this.a.getId());
                    }
                }
                catch (Exception ex) {}
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                try {
                    uo.this.f = true;
                    if (this.a != null) {
                        this.a.paintComponent(this.a.getGraphics());
                    }
                }
                catch (Exception ex) {}
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                try {
                    uo.this.f = false;
                    if (this.a != null) {
                        this.a.paintComponent(this.a.getGraphics());
                    }
                }
                catch (Exception ex) {}
            }
        }
        
        class adj implements FocusListener
        {
            uo a;
            
            public adj(final uo a) {
                this.a = a;
            }
            
            public void focusGained(final FocusEvent focusEvent) {
                uo.this.e = true;
                if (this.a != null) {
                    this.a.paintComponent(this.a.getGraphics());
                }
            }
            
            public void focusLost(final FocusEvent focusEvent) {
                uo.this.e = false;
                if (this.a != null) {
                    this.a.paintComponent(this.a.getGraphics());
                }
            }
        }
    }
}
