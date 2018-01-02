// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.IPathListener;
import org.xmodel.IModelListener;
import org.xmodel.xpath.expression.IContext;

class B
{
    IContext D;
    IModelListener A;
    IPathListener C;
    IExpressionListener B;
    
    @Override
    public boolean equals(final Object o) {
        final B b = (B)o;
        return ((b.D != null && this.D != null) || b.D == this.D) && (b.D == null || this.D == null || b.D.equals(this.D)) && this.A(b.A, this.A) && this.A(b.C, this.C) && this.A(b.B, this.B);
    }
    
    protected boolean A(final Object o, final Object o2) {
        return ((o != null && o2 != null) || o == o2) && (o == null || o2 == null || o.getClass().equals(o2.getClass()));
    }
    
    @Override
    public int hashCode() {
        int hashCode = -213384737;
        if (this.D != null) {
            hashCode = this.D.hashCode();
        }
        if (this.A != null) {
            hashCode ^= this.A.getClass().hashCode();
        }
        if (this.C != null) {
            hashCode ^= this.C.getClass().hashCode();
        }
        if (this.B != null) {
            hashCode ^= this.B.getClass().hashCode();
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.A != null) {
            sb.append("IModelListener=");
            sb.append(this.A.getClass().getName());
        }
        if (this.C != null) {
            sb.append("IPathListener=");
            sb.append(this.C.getClass().getName());
        }
        if (this.B != null) {
            sb.append("IExpressionListener=");
            sb.append(this.B.getClass().getName());
        }
        if (this.D != null) {
            sb.append('\n');
            sb.append("IContext=");
            sb.append(this.D);
        }
        return sb.toString();
    }
}
