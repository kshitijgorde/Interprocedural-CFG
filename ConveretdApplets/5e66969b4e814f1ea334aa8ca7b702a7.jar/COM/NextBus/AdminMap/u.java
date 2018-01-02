// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.m;
import java.awt.Image;
import java.awt.Color;
import COM.NextBus.a.a;
import java.awt.image.ImageObserver;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import COM.NextBus.HttpMapClient.b;
import COM.NextBus.HttpMapClient.d;

final class u implements d
{
    private final b a;
    private final s b;
    private final y c;
    
    u(final b a, final s b, final y c) {
        (this.a = a).a(this);
        this.b = b;
        this.c = c;
    }
    
    final void a(final Graphics graphics, final Point point, final Dimension dimension) {
        final List<X> synchronizedList = Collections.synchronizedList(new ArrayList<X>());
        final int n = (int)Math.floor(-1.0 * point.x / this.b.e());
        final int n2 = (int)Math.floor(-1.0 * point.y / this.b.f());
        for (int n3 = n; n3 * this.b.e() < -1 * point.x + dimension.width; ++n3) {
            for (int n4 = n2; n4 * this.b.f() < -1 * point.y + dimension.height; ++n4) {
                synchronizedList.add(new X(this.b, n3, n4));
            }
        }
        final List<X> list = synchronizedList;
        for (int i = 0; i < list.size(); ++i) {
            final X x;
            if ((x = list.get(i)) != null) {
                final Image a;
                if ((a = this.a.a(x.g())) != null) {
                    graphics.drawImage(a, point.x + x.h(), point.y + x.i(), null);
                }
                else {
                    graphics.setColor(new Color(COM.NextBus.a.a.b));
                    graphics.fillRect(point.x + x.h(), point.y + x.i(), x.e(), x.f());
                }
            }
        }
    }
    
    public final void a(final m m) {
        this.c.a();
    }
}
