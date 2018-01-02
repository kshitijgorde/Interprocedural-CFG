// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.SystemColor;
import ji.util.e;
import ji.util.d;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Color;
import java.awt.Insets;

public class b3
{
    private int a;
    private Insets b;
    private Color c;
    private Color d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private Color m;
    
    public b3() {
        this.a = 3;
        this.b = new Insets(0, 0, 0, 0);
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = 3;
        this.j = 0;
        this.k = 0;
        this.l = -1;
        this.m = null;
        this.j();
    }
    
    public final void a(final Color m) {
        this.m = m;
    }
    
    public final void a(final int top, final int left, final int bottom, final int right) {
        this.b.top = top;
        this.b.left = left;
        this.b.bottom = bottom;
        this.b.right = right;
    }
    
    public final Insets a() {
        return this.b;
    }
    
    public final void a(final int a) {
        if (a >= 0 && a <= 7) {
            this.a = a;
            this.j();
        }
    }
    
    public final int b() {
        return this.a;
    }
    
    public final void a(final int j, final int k) {
        this.j = j;
        this.k = k;
    }
    
    public final Rectangle a(final Component component) {
        Rectangle rectangle = new Rectangle(10, 10);
        try {
            final Dimension size = component.getSize();
            if (this.a == 3) {
                rectangle = new Rectangle(this.j + 2, this.k + 2, size.width - 4, size.height - 4);
            }
            else if (this.a == 4) {
                rectangle = new Rectangle(this.j + 1, this.k + 1, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            else if (this.a == 5) {
                rectangle = new Rectangle(this.j + 1 + this.i / 2, this.k + 1 + this.i / 2, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            else if (this.a == 0) {
                rectangle = new Rectangle(this.j, this.k, size.width, size.height);
            }
            else {
                rectangle = new Rectangle(this.j + 1, this.k + 1, size.width - 2, size.height - 2);
            }
        }
        catch (Exception ex) {}
        return rectangle;
    }
    
    public final Rectangle b(final Component component) {
        try {
            final Dimension size = component.getSize();
            if (this.a == 3) {
                return new Rectangle(this.j + 2, this.k + 2, size.width - 4, size.height - 4);
            }
            if (this.a == 4) {
                return new Rectangle(this.j + 1, this.k + 1, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            if (this.a == 5) {
                return new Rectangle(this.j + 1 + this.i / 2, this.k + 1 + this.i / 2, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            if (this.a == 0) {
                return new Rectangle(this.j, this.k, size.width, size.height);
            }
            return new Rectangle(this.j + 1, this.k + 1, size.width - 2, size.height - 2);
        }
        catch (Exception ex) {
            return new Rectangle(0, 0, 0, 0);
        }
    }
    
    public final void a(final Component component, final Graphics graphics) {
        try {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            final Dimension size = component.getSize();
            if (this.a == 3) {
                graphics.fillRect(this.j + 2, this.k + 2, size.width - 4, size.height - 4);
            }
            else if (this.a == 4) {
                graphics.fillRect(this.j + 1, this.k + 1, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            else if (this.a == 5) {
                graphics.fillRect(this.j + 1 + this.i / 2, this.k + 1 + this.i / 2, size.width - 2 - this.i, size.height - 2 - this.i);
            }
            else if (this.a == 0) {
                graphics.fillRect(this.j, this.k, size.width, size.height);
            }
            else {
                graphics.fillRect(this.j + 1, this.k + 1, size.width - 2, size.height - 2);
            }
            graphics.setColor(color);
            ji.util.d.ew();
        }
        catch (Exception ex) {}
    }
    
    public final void b(final Component component, final Graphics graphics) {
        try {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            final Dimension size = component.getSize();
            graphics.fillRect(this.j, this.k, size.width, size.height);
            graphics.setColor(color);
            ji.util.d.ew();
        }
        catch (Exception ex) {}
    }
    
    public final void c(final Component component, final Graphics graphics) {
        try {
            final Dimension size = component.getSize();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            int i;
            if (this.a == 4 || this.a == 5) {
                i = this.i;
            }
            else {
                i = 2;
            }
            if (graphics != null) {
                graphics.setColor(component.getBackground());
                graphics.fillRect(this.j, this.k, n, i);
                graphics.fillRect(this.j, this.k, i + 1, n2);
                graphics.fillRect(this.j + n - i, this.k, i + 1, n2 + 1);
                graphics.fillRect(this.j, this.k + n2 - i, n, i + 1);
            }
            ji.util.d.ew();
        }
        catch (Exception ex) {}
    }
    
    public final void a(final boolean e) {
        this.e = e;
    }
    
    public final boolean c() {
        return this.e;
    }
    
    public final void b(final boolean f) {
        this.f = f;
    }
    
    public final boolean d() {
        return this.f;
    }
    
    public final void c(final boolean g) {
        this.g = g;
    }
    
    public final boolean e() {
        return this.g;
    }
    
    public final void d(final boolean h) {
        this.h = h;
    }
    
    public final boolean f() {
        return this.h;
    }
    
    public final Color g() {
        return this.c;
    }
    
    public final void b(final Color c) {
        this.c = c;
    }
    
    public final Color h() {
        return this.d;
    }
    
    public final void c(final Color d) {
        this.d = d;
    }
    
    public final void d(final Component component, final Graphics graphics) {
        try {
            final Dimension size = component.getSize();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            Color color;
            if (this.c != null) {
                color = this.c;
            }
            else if (ji.util.e.g != null) {
                color = ji.util.e.g;
            }
            else {
                color = SystemColor.controlShadow;
            }
            Color color2;
            if (this.d != null) {
                color2 = this.d;
            }
            else if (ji.util.e.h != null) {
                color2 = ji.util.e.h;
            }
            else {
                color2 = SystemColor.controlLtHighlight;
            }
            switch (this.a) {
                case 1: {
                    graphics.setColor(color);
                    graphics.drawLine(this.j, this.k, this.j + n - 1, this.k);
                    graphics.drawLine(this.j, this.k, this.j, this.k + n2 - 1);
                    graphics.setColor(color2);
                    graphics.drawLine(this.j + n, this.k, this.j + n, this.k + n2);
                    graphics.drawLine(this.j, this.k + n2, this.j + n, this.k + n2);
                    break;
                }
                case 2: {
                    graphics.setColor(color2);
                    graphics.drawLine(this.j, this.k, this.j + n - 1, this.k);
                    graphics.drawLine(this.j, this.k, this.j, this.k + n2 - 1);
                    graphics.setColor(color);
                    graphics.drawLine(this.j + n, this.k, this.j + n, this.k + n2);
                    graphics.drawLine(this.j, this.k + n2, this.j + n, this.k + n2);
                    break;
                }
                case 6: {
                    if (this.m != null) {
                        graphics.setColor(this.m);
                    }
                    else {
                        graphics.setColor(color);
                    }
                    graphics.drawRect(this.j, this.k, n, n2);
                    break;
                }
                case 7: {
                    if (this.m != null) {
                        graphics.setColor(this.m);
                    }
                    else {
                        graphics.setColor(color);
                    }
                    graphics.drawRect(this.j, this.k, n, n2);
                    graphics.drawRect(this.j + 1, this.k + 1, n - 2, n2 - 2);
                    break;
                }
                case 3: {
                    graphics.setColor(color);
                    graphics.drawLine(this.j, this.k, this.j + n - 1, this.k);
                    graphics.setColor(color2);
                    if (this.g) {
                        graphics.drawLine(this.j - 1, this.k + 1, this.j + n - 1, this.k + 1);
                    }
                    else {
                        graphics.drawLine(this.j, this.k + 1, this.j + n - 1, this.k + 1);
                    }
                    if (this.e) {
                        graphics.drawLine(this.j + n, this.k, this.j + n, this.k);
                    }
                    if (this.h) {
                        graphics.setColor(color);
                        graphics.drawLine(this.j, this.k, this.j + n, this.k);
                    }
                    graphics.setColor(color);
                    if (this.g) {
                        graphics.drawLine(this.j, this.k + 2, this.j, this.k + n2 - 1);
                    }
                    else {
                        graphics.drawLine(this.j, this.k + 1, this.j, this.k + n2 - 1);
                    }
                    graphics.setColor(color2);
                    if (this.f) {
                        graphics.drawLine(this.j + 1, this.k + 1, this.j + 1, this.k + n2);
                    }
                    if (this.e) {
                        graphics.drawLine(this.j + 1, this.k, this.j + 1, this.k + n2 - 1);
                    }
                    else {
                        graphics.drawLine(this.j + 1, this.k + 1, this.j + 1, this.k + n2 - 1);
                    }
                    graphics.setColor(color);
                    if (this.h) {
                        graphics.drawLine(this.j + n - 1, this.k + 2, this.j + n - 1, this.k + n2 - 1);
                    }
                    else {
                        graphics.drawLine(this.j + n - 1, this.k + 1, this.j + n - 1, this.k + n2 - 1);
                    }
                    graphics.setColor(color2);
                    graphics.drawLine(this.j + n, this.k + 1, this.j + n, this.k + n2 - 1);
                    graphics.setColor(color2);
                    if (this.g) {
                        graphics.drawLine(this.j, this.k + n2, this.j + n, this.k + n2);
                    }
                    else {
                        graphics.drawLine(this.j + 1, this.k + n2, this.j + n, this.k + n2);
                    }
                    if (this.f) {
                        graphics.setColor(color);
                        graphics.drawLine(this.j + n - 1, this.k + n2, this.j + n - 1, this.k + n2);
                    }
                    graphics.setColor(color);
                    if (this.h) {
                        graphics.drawLine(this.j + 1, this.k + n2 - 1, this.j + n, this.k + n2 - 1);
                    }
                    else if (this.f) {
                        graphics.drawLine(this.j + 2, this.k + n2 - 1, this.j + n - 1, this.k + n2 - 1);
                    }
                    else {
                        graphics.drawLine(this.j + 1, this.k + n2 - 1, this.j + n - 1, this.k + n2 - 1);
                    }
                    if (this.f) {
                        graphics.drawLine(this.j, this.k + n2, this.j, this.k + n2);
                        break;
                    }
                    break;
                }
                case 4: {
                    graphics.setColor(color);
                    graphics.drawRect(this.j, this.k, n - this.i, n2 - this.i);
                    graphics.setColor(color.darker().darker());
                    graphics.fillRect(this.j + n - this.i + 1, this.k + this.i, this.i, n2 + 1 - this.i);
                    graphics.fillRect(this.j + this.i, this.k + n2 - this.i + 1, n - this.i, this.i);
                    break;
                }
                case 5: {
                    graphics.setColor(color);
                    graphics.drawRect(this.j + this.i / 2, this.k + this.i / 2, n - this.i, n2 - this.i);
                    graphics.setColor(color.darker().darker());
                    graphics.fillRect(this.j + n - this.i + 1 + this.i / 2, this.k + this.i, this.i, n2 + 1 - this.i / 2);
                    graphics.fillRect(this.j + this.i, this.k + n2 - this.i + 1 + this.i / 2, n - this.i, this.i);
                    break;
                }
                case 0: {
                    if (this.l != this.a) {
                        this.c(component, graphics);
                        break;
                    }
                    break;
                }
            }
        }
        catch (Throwable t) {}
        this.l = this.a;
    }
    
    public final void b(final int i) {
        this.i = i;
        this.j();
    }
    
    public final int i() {
        return this.i;
    }
    
    private void j() {
        switch (this.a) {
            case 0: {
                this.a(0, 0, 0, 0);
                break;
            }
            case 3: {
                this.a(2, 2, 2, 2);
                break;
            }
            case 2: {
                this.a(1, 1, 1, 1);
                break;
            }
            case 6: {
                this.a(1, 1, 1, 1);
                break;
            }
            case 7: {
                this.a(2, 2, 2, 2);
                break;
            }
            case 1: {
                this.a(1, 1, 1, 1);
                break;
            }
            case 5: {
                final int i = this.i();
                this.a(1 + i / 2, 1 + i / 2, 2 + i / 2, 2 + i / 2);
                break;
            }
            case 4: {
                final int j = this.i();
                this.a(1, 1, 1 + j, 1 + j);
                break;
            }
        }
    }
}
