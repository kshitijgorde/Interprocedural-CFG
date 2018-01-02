// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.sygem.jazz3d3.Shape;
import com.sygem.jazz3d3.Vertex;
import com.sygem.jazz3d3.primitive.Cylinder3d;
import com.sygem.jazz3d3.Object3d;
import com.sygem.jazz3d3.primitive.Line3d;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import com.sygem.jazz3d3.Triangle;
import com.sygem.jazz3d3.Quad;
import com.sygem.jazz3d3.primitive.Freeform3d;

public class f
{
    public boolean char;
    public Freeform3d int;
    public Freeform3d byte;
    public Freeform3d long;
    public Freeform3d new;
    public Freeform3d try;
    public d case;
    Quad null;
    Triangle void;
    int a;
    int else;
    double for;
    double if;
    short[] goto;
    DataInputStream do;
    
    public f(final InputStream inputStream) throws IOException {
        this.char = false;
        this.if = 1.2;
        this.goto = new short[3];
        this.int = new Freeform3d(0.0, 0.0, 0.0);
        this.byte = new Freeform3d(0.0, 0.0, 0.0);
        this.long = new Freeform3d(0.0, 0.0, 0.0);
        this.new = new Freeform3d(0.0, 0.0, 0.0);
        this.try = new Freeform3d(0.0, 0.0, 0.0);
        this.do = new DataInputStream(new BufferedInputStream(inputStream));
        this.byte();
        this.goto = this.if();
        this.for = this.do.readFloat();
        for (short short1 = this.do.readShort(), n = 0; n < short1; ++n) {
            this.case();
        }
        for (short short2 = this.do.readShort(), n2 = 0; n2 < short2; ++n2) {
            this.for();
        }
        for (short short3 = this.do.readShort(), n3 = 0; n3 < short3; ++n3) {
            this.try();
        }
        for (short short4 = this.do.readShort(), n4 = 0; n4 < short4; ++n4) {
            this.a();
        }
        final short short5 = this.do.readShort();
        this.case = new d(0.0, 0.0, 0.0, short5);
        for (short n5 = 0; n5 < short5; ++n5) {
            this.a(n5);
        }
        inputStream.close();
        this.do.close();
        ((Object3d)this.int).setCulling(true);
        ((Object3d)this.int).scaleObject(0.5, 0.5, 0.5);
        this.int.prep();
        ((Object3d)this.int).prepForDisplay(true);
        ((Object3d)this.byte).setCulling(true);
        ((Object3d)this.byte).scaleObject(0.5, 0.5, 0.5);
        this.byte.prep();
        ((Object3d)this.byte).prepForDisplay(true);
        ((Object3d)this.long).scaleObject(0.5, 0.5, 0.5);
        ((Object3d)this.long).prepForDisplay(true);
        ((Object3d)this.new).scaleObject(0.5, 0.5, 0.5);
        this.new.prep();
        ((Object3d)this.new).prepForDisplay(true);
        ((Object3d)this.new).setVisible(false);
        ((Object3d)this.try).scaleObject(0.5, 0.5, 0.5);
        ((Object3d)this.try).setAmbientColour(255, 255, 255);
        ((Object3d)this.try).recreateVertexNormals();
        this.try.prep();
        ((Object3d)this.try).prepForDisplay(true);
    }
    
    private void case() throws IOException {
        final float n = 1.2f * this.do.readFloat();
        final float n2 = 1.2f * this.do.readFloat();
        final float n3 = 1.2f * this.do.readFloat();
        this.int.addPoint((double)n, (double)n2, (double)n3);
        this.byte.addPoint(n * 0.99, n2 * 0.99, n3 * 0.99);
    }
    
    private void for() throws IOException {
        final short short1 = this.do.readShort();
        final short short2 = this.do.readShort();
        final short short3 = this.do.readShort();
        final short short4 = this.do.readShort();
        this.int.addFace((int)short1, (int)short2, (int)short3, (int)short4, (int)this.goto[0], (int)this.goto[1], (int)this.goto[2], false);
        this.byte.addFace((int)short4, (int)short3, (int)short2, (int)short1, (int)this.goto[0], (int)this.goto[1], (int)this.goto[2], false);
    }
    
    private void try() throws IOException {
        final short short1 = this.do.readShort();
        final short short2 = this.do.readShort();
        final short short3 = this.do.readShort();
        this.int.addFace((int)short1, (int)short2, (int)short3, (int)this.goto[0], (int)this.goto[1], (int)this.goto[2], false);
        this.byte.addFace((int)short3, (int)short2, (int)short1, (int)this.goto[0], (int)this.goto[1], (int)this.goto[2], false);
    }
    
    private void byte() throws IOException {
        final float float1 = this.do.readFloat();
        final float float2 = this.do.readFloat();
        final float float3 = this.do.readFloat();
        final double n = 1.5;
        final double n2 = 0.0;
        final double n3 = 1.0;
        final double n4 = 0.0;
        final double sin = Math.sin(float1);
        final double cos = Math.cos(float1);
        final double n5 = 0.0;
        final double cos2 = Math.cos(float2);
        final double n6 = (Math.cos(float3) - cos2 * cos) / sin;
        final double sqrt = Math.sqrt(1.0 - n6 * n6 - cos2 * cos2);
        final double n7 = n * n6;
        final double n8 = n * cos2;
        final double n9 = n * -sqrt;
        final double n10 = n * sin;
        final double n11 = n * cos;
        final double n12 = n * -n5;
        final double n13 = n * n2;
        final double n14 = n * n3;
        final double n15 = n * -n4;
        ((Object3d)this.new).addVertex(0, (float)(n7 * 1.05), (float)(n8 * 1.05), (float)(n9 * 1.05));
        ((Object3d)this.new).addVertex(1, (float)(-n7 * 1.05), (float)(-n8 * 1.05), (float)(-n9 * 1.05));
        ((Object3d)this.new).addVertex(2, (float)(n10 * 1.05), (float)(n11 * 1.05), (float)(n12 * 1.05));
        ((Object3d)this.new).addVertex(3, (float)(-n10 * 1.05), (float)(-n11 * 1.05), (float)(-n12 * 1.05));
        ((Object3d)this.new).addVertex(4, (float)(n13 * 1.05), (float)(n14 * 1.05), (float)(n15 * 1.05));
        ((Object3d)this.new).addVertex(5, (float)(-n13 * 1.05), (float)(-n14 * 1.05), (float)(-n15 * 1.05));
        ((Object3d)this.new).addChild((Object3d)new Line3d(n7, n8, n9, -n7, -n8, -n9), 0.0, 0.0, 0.0);
        ((Object3d)this.new).addChild((Object3d)new Line3d(n10, n11, n12, -n10, -n11, -n12), 0.0, 0.0, 0.0);
        ((Object3d)this.new).addChild((Object3d)new Line3d(n13, n14, n15, n13, -n14, -n15), 0.0, 0.0, 0.0);
        if (Math.abs(float1 - 1.5708) < 1.0E-4 && Math.abs(float2 - 1.5708) < 1.0E-4 && Math.abs(float3 - 2.0944) < 1.0E-4) {
            this.char = true;
            ((Object3d)this.new).addVertex(6, (float)(-n7), (float)n8, (float)n9);
            ((Object3d)this.new).addVertex(7, (float)n7, (float)(-n8), (float)(-n9));
            ((Object3d)this.new).addChild((Object3d)new Line3d(-n7, n8, n9, n7, -n8, -n9), 0.0, 0.0, 0.0);
        }
    }
    
    public boolean new() {
        return this.char;
    }
    
    private short[] if() throws IOException {
        final short[] array = new short[3];
        short n = 255;
        for (int i = 0; i < 3; ++i) {
            array[i] = this.do.readShort();
            if (255 - array[i] < n) {
                n = (short)(255 - array[i]);
            }
        }
        for (int j = 0; j < 3; ++j) {
            array[j] += n;
        }
        return array;
    }
    
    private void a() throws IOException {
        final short short1 = this.do.readShort();
        final double x = ((Object3d)this.int).getVertex((int)short1).getX();
        final double y = ((Object3d)this.int).getVertex((int)short1).getY();
        final double z = ((Object3d)this.int).getVertex((int)short1).getZ();
        short n = this.do.readShort();
        do {
            final double x2 = ((Object3d)this.int).getVertex((int)n).getX();
            final double y2 = ((Object3d)this.int).getVertex((int)n).getY();
            final double z2 = ((Object3d)this.int).getVertex((int)n).getZ();
            ((Object3d)this.long).addChild((Object3d)new Line3d(x, y, z, x2, y2, z2), 0.0, 0.0, 0.0);
            this.a(x, y, z, x2, y2, z2);
            n = this.do.readShort();
        } while (n != -1);
    }
    
    private void a(final int n) throws IOException {
        final int[] array = { this.do.readByte(), this.do.readByte(), this.do.readByte() };
        this.case.a(array[0], array[1], array[2], 1.2f * this.do.readFloat() / 2.0f, 1.2f * this.do.readFloat() / 2.0f, 1.2f * this.do.readFloat() / 2.0f);
    }
    
    private void a(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double sqrt = Math.sqrt((n4 - n) * (n4 - n) + (n5 - n2) * (n5 - n2) + (n6 - n3) * (n6 - n3));
        final Cylinder3d cylinder3d = new Cylinder3d(6, 0.5 * (n + (n4 - n) / 2.0), 0.5 * (n2 + (n5 - n2) / 2.0), 0.5 * (n3 + (n6 - n3) / 2.0));
        ((Object3d)cylinder3d).scaleObject(0.075, sqrt, 0.075);
        final Vertex[] vertexArray = ((Object3d)cylinder3d).getVertexArray();
        for (int i = 0; i < vertexArray.length; ++i) {
            a.a.a(vertexArray[i], new b(0.0, 1.0, 0.0), new b(n4 - n, n5 - n2, n6 - n3));
        }
        final Shape[] shapeArray = ((Object3d)cylinder3d).getShapeArray();
        for (int j = 0; j < shapeArray.length; ++j) {
            final Vertex faceNormal = shapeArray[j].getFaceNormal();
            a.a.a(faceNormal, new b(0.0, 1.0, 0.0), new b(n4 - n, n5 - n2, n6 - n3));
            shapeArray[j].setFaceNormal(faceNormal);
        }
        ((Object3d)cylinder3d).recreateVertexNormals();
        ((Object3d)cylinder3d).applyFaceChanges();
        ((Object3d)this.try).addChild((Object3d)cylinder3d, 0.0, 0.0, 0.0);
    }
    
    public void a(final double n, final double n2, final double n3) {
        this.case.a(n, n2, n3);
        ((Object3d)this.int).rotateWorld(n, n2, n3);
        ((Object3d)this.byte).rotateWorld(n, n2, n3);
        ((Object3d)this.long).rotateWorld(n, n2, n3);
        ((Object3d)this.new).rotateWorld(n, n2, n3);
        ((Object3d)this.try).rotateWorld(n, n2, n3);
    }
    
    public void a(final int n, final double n2) {
        final Vertex vertex = ((Object3d)this.new).getVertex(n);
        final e e = new e(new b(vertex.getX(), vertex.getY(), vertex.getZ()), this.a(n2));
        final b b = new b(0.0, 0.0, 0.0);
        final Vertex[] vertexArray = ((Object3d)this.int).getVertexArray();
        final Vertex[] vertexArray2 = ((Object3d)this.byte).getVertexArray();
        for (int i = 0; i < vertexArray.length; ++i) {
            b.a(vertexArray[i].getX(), vertexArray[i].getY(), vertexArray[i].getZ());
            e.a(b);
            vertexArray[i].setX(b.a);
            vertexArray[i].setY(b.do);
            vertexArray[i].setZ(b.if);
            vertexArray2[i].setX(b.a);
            vertexArray2[i].setY(b.do);
            vertexArray2[i].setZ(b.if);
        }
        final Shape[] shapeArray = ((Object3d)this.int).getShapeArray();
        for (int j = 0; j < shapeArray.length; ++j) {
            final Vertex faceNormal = shapeArray[j].getFaceNormal();
            b.a(faceNormal.getX(), faceNormal.getY(), faceNormal.getZ());
            e.a(b);
            faceNormal.setX(b.a);
            faceNormal.setY(b.do);
            faceNormal.setZ(b.if);
            shapeArray[j].setFaceNormal(faceNormal);
        }
        final Object3d[] childObjects = ((Object3d)this.long).getChildObjects();
        for (int k = 0; k < childObjects.length; ++k) {
            final Vertex[] vertexArray3 = childObjects[k].getVertexArray();
            for (int l = 0; l < vertexArray3.length; ++l) {
                b.a(vertexArray3[l].getX(), vertexArray3[l].getY(), vertexArray3[l].getZ());
                e.a(b);
                vertexArray3[l].setX(b.a);
                vertexArray3[l].setY(b.do);
                vertexArray3[l].setZ(b.if);
            }
        }
        final Object3d[] childObjects2 = ((Object3d)this.try).getChildObjects();
        for (int n3 = 0; n3 < childObjects2.length; ++n3) {
            final Vertex[] vertexArray4 = childObjects2[n3].getVertexArray();
            for (int n4 = 0; n4 < vertexArray4.length; ++n4) {
                b.a(vertexArray4[n4].getX() + childObjects2[n3].getCenter().getX(), vertexArray4[n4].getY() + childObjects2[n3].getCenter().getY(), vertexArray4[n4].getZ() + childObjects2[n3].getCenter().getZ());
                e.a(b);
                vertexArray4[n4].setX(b.a - childObjects2[n3].getCenter().getX());
                vertexArray4[n4].setY(b.do - childObjects2[n3].getCenter().getY());
                vertexArray4[n4].setZ(b.if - childObjects2[n3].getCenter().getZ());
            }
            final Shape[] shapeArray2 = childObjects2[n3].getShapeArray();
            for (int n5 = 0; n5 < shapeArray2.length; ++n5) {
                final Vertex faceNormal2 = shapeArray2[n5].getFaceNormal();
                b.a(faceNormal2.getX(), faceNormal2.getY(), faceNormal2.getZ());
                e.a(b);
                faceNormal2.setX(b.a);
                faceNormal2.setY(b.do);
                faceNormal2.setZ(b.if);
                shapeArray2[n5].setFaceNormal(faceNormal2);
            }
        }
        final Vertex[] vertexArray5 = ((Object3d)this.new).getVertexArray();
        for (int n6 = 0; n6 < vertexArray5.length; ++n6) {
            b.a(vertexArray5[n6].getX(), vertexArray5[n6].getY(), vertexArray5[n6].getZ());
            e.a(b);
            vertexArray5[n6].setX(b.a);
            vertexArray5[n6].setY(b.do);
            vertexArray5[n6].setZ(b.if);
        }
        final Object3d[] childObjects3 = ((Object3d)this.new).getChildObjects();
        for (int n7 = 0; n7 < childObjects3.length; ++n7) {
            final Vertex[] vertexArray6 = childObjects3[n7].getVertexArray();
            for (int n8 = 0; n8 < vertexArray6.length; ++n8) {
                b.a(vertexArray6[n8].getX(), vertexArray6[n8].getY(), vertexArray6[n8].getZ());
                e.a(b);
                vertexArray6[n8].setX(b.a);
                vertexArray6[n8].setY(b.do);
                vertexArray6[n8].setZ(b.if);
            }
        }
        this.case.a(e);
    }
    
    public void int() {
        final Vertex vertex = ((Object3d)this.new).getVertex(2);
        final Vertex vertex2 = ((Object3d)this.new).getVertex(4);
        double if1 = this.if(0.0, vertex2.getY(), vertex2.getZ(), 0.0, 0.0, -1.0);
        if (vertex2.getY() < 0.0) {
            if1 = -if1;
        }
        this.a(Math.toDegrees(if1), 0.0, 0.0);
        double if2 = this.if(vertex2.getX(), 0.0, vertex2.getZ(), 0.0, 0.0, -1.0);
        if (vertex2.getX() > 0.0) {
            if2 = -if2;
        }
        this.a(0.0, Math.toDegrees(if2), 0.0);
        double if3 = this.if(vertex.getX(), vertex.getY(), 0.0, 1.0, 0.0, 0.0);
        if (vertex.getY() < 0.0) {
            if3 = -if3;
        }
        this.a(0.0, 0.0, Math.toDegrees(if3));
    }
    
    public void do() {
        final Vertex vertex = ((Object3d)this.new).getVertex(2);
        final Vertex vertex2 = ((Object3d)this.new).getVertex(4);
        double if1 = this.if(0.0, vertex.getY(), vertex.getZ(), 0.0, 0.0, -1.0);
        if (vertex.getY() < 0.0) {
            if1 = -if1;
        }
        this.a(Math.toDegrees(if1), 0.0, 0.0);
        double if2 = this.if(vertex.getX(), 0.0, vertex.getZ(), 0.0, 0.0, -1.0);
        if (vertex.getX() > 0.0) {
            if2 = -if2;
        }
        this.a(0.0, Math.toDegrees(if2), 0.0);
        double if3 = this.if(vertex2.getX(), vertex2.getY(), 0.0, 0.0, 1.0, 0.0);
        if (vertex2.getX() > 0.0) {
            if3 = -if3;
        }
        this.a(0.0, 0.0, Math.toDegrees(if3));
    }
    
    public void char() {
        final Vertex vertex = ((Object3d)this.new).getVertex(0);
        final Vertex vertex2 = ((Object3d)this.new).getVertex(4);
        double if1 = this.if(0.0, vertex.getY(), vertex.getZ(), 0.0, 0.0, -1.0);
        if (vertex.getY() < 0.0) {
            if1 = -if1;
        }
        this.a(Math.toDegrees(if1), 0.0, 0.0);
        double if2 = this.if(vertex.getX(), 0.0, vertex.getZ(), 0.0, 0.0, -1.0);
        if (vertex.getX() > 0.0) {
            if2 = -if2;
        }
        this.a(0.0, Math.toDegrees(if2), 0.0);
        double if3 = this.if(vertex2.getX(), vertex2.getY(), 0.0, 0.0, 1.0, 0.0);
        if (vertex2.getX() > 0.0) {
            if3 = -if3;
        }
        this.a(0.0, 0.0, Math.toDegrees(if3));
    }
    
    public void a(final boolean b) {
        double if1 = this.if;
        if (!b) {
            if1 = 1.0 / this.if;
        }
        ((Object3d)this.int).scaleObject(if1, if1, if1);
        ((Object3d)this.byte).scaleObject(if1, if1, if1);
        ((Object3d)this.new).scaleObject(if1, if1, if1);
        ((Object3d)this.try).scaleObject(if1, if1, if1);
        final Object3d[] childObjects = ((Object3d)this.try).getChildObjects();
        for (int i = 0; i < childObjects.length; ++i) {
            final Vertex center = childObjects[i].getCenter();
            childObjects[i].setPosition(center.getX() * if1, center.getY() * if1, center.getZ() * if1);
        }
        ((Object3d)this.long).scaleObject(if1, if1, if1);
        this.case.a(if1);
    }
    
    public void if(final float n) {
        ((Object3d)this.int).scaleObject((double)n, (double)n, (double)n);
        ((Object3d)this.byte).scaleObject((double)n, (double)n, (double)n);
        ((Object3d)this.new).scaleObject((double)n, (double)n, (double)n);
        ((Object3d)this.try).scaleObject((double)n, (double)n, (double)n);
        final Object3d[] childObjects = ((Object3d)this.try).getChildObjects();
        for (int i = 0; i < childObjects.length; ++i) {
            final Vertex center = childObjects[i].getCenter();
            childObjects[i].setPosition(center.getX() * n, center.getY() * n, center.getZ() * n);
        }
        ((Object3d)this.long).scaleObject((double)n, (double)n, (double)n);
        this.case.a(n);
    }
    
    public void if(final boolean b) {
        if (b) {
            this.case.a();
        }
        else {
            this.case.if();
        }
    }
    
    public void a(final float n) {
        if (n > 1.0f) {
            this.case.a();
        }
        else {
            this.case.if();
        }
    }
    
    double if(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return Math.acos((n * n4 + n2 * n5 + n3 * n6) / (Math.sqrt(n * n + n2 * n2 + n3 * n3) * Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6)));
    }
    
    double[] do(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return new double[] { n2 * n6 - n3 * n5, n3 * n4 - n * n6, n * n5 - n2 * n4 };
    }
    
    double a(final double n) {
        return n / 180.0 * 3.141592653589793;
    }
}
