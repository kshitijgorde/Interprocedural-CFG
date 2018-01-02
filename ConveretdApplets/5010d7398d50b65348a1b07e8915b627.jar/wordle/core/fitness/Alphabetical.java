// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import java.util.Arrays;
import cue.lang.unicode.a;
import wordle.core.b.c;
import java.util.HashMap;
import java.awt.geom.Dimension2D;
import java.util.Map;
import java.util.Comparator;

public class Alphabetical extends AbstractPlacementStrategy implements Comparator
{
    private final Map a;
    private final Map b;
    private int c;
    
    public Alphabetical(final Dimension2D dimension2D) {
        super(dimension2D);
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = 0;
    }
    
    public final void a(final c c) {
        c.b(this.b.get(c), (this.c > 100) ? this.b(c) : (this.c() - c.g() / 2.0));
    }
    
    public final void a(final c[] array) {
        this.c = array.length;
        this.a.clear();
        for (final c c : array) {
            this.a.put(c, cue.lang.unicode.a.a().a(c.k().b).toLowerCase());
        }
        Arrays.sort(array, this);
        this.b.clear();
        final double n = this.a() / array.length;
        for (int j = 0; j < array.length; ++j) {
            this.b.put(array[j], j * n);
        }
        super.a(array);
    }
}
