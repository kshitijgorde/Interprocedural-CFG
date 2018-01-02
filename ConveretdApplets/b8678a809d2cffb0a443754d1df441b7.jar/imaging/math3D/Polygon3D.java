// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class Polygon3D
{
    private Vector3D[] v;
    private int numVertices;
    private Vector3D normal;
    private Vector3D temp1;
    private Vector3D temp2;
    
    public Polygon3D() {
        this.numVertices = 0;
        this.v = new Vector3D[0];
    }
    
    public Polygon3D(final Vector3D v0, final Vector3D v1, final Vector3D v2) {
        this(new Vector3D[] { v0, v1, v2 });
    }
    
    public Polygon3D(final Vector3D v0, final Vector3D v1, final Vector3D v2, final Vector3D v3) {
        this(new Vector3D[] { v0, v1, v2, v3 });
    }
    
    public Polygon3D(final Vector3D[] vertices) {
        this.v = vertices;
        this.numVertices = vertices.length;
    }
    
    public void setTo(final Polygon3D polygon) {
        this.ensureCapacity(this.numVertices = polygon.numVertices);
        for (int i = 0; i < this.numVertices; ++i) {
            this.v[i].setTo(polygon.v[i]);
        }
    }
    
    protected void ensureCapacity(final int length) {
        if (this.v.length < length) {
            final Vector3D[] newV = new Vector3D[length];
            System.arraycopy(this.v, 0, newV, 0, this.v.length);
            for (int i = this.v.length; i < newV.length; ++i) {
                newV[i] = new Vector3D();
            }
            this.v = newV;
            this.numVertices = length;
        }
    }
    
    public int getNumVertices() {
        return this.numVertices;
    }
    
    public Vector3D getVertex(final int index) {
        return this.v[index];
    }
    
    public void backProject(final ViewWindow view) {
        for (int i = 0; i < this.numVertices; ++i) {
            view.backProject(this.v[i]);
        }
    }
    
    public void project(final ViewWindow view) {
        for (int i = 0; i < this.numVertices; ++i) {
            view.project(this.v[i]);
        }
    }
    
    public Vector3D calcNormal() {
        if (this.normal == null) {
            this.normal = new Vector3D();
        }
        this.temp1.setTo(this.v[2]);
        this.temp1.subtract(this.v[1]);
        this.temp2.setTo(this.v[0]);
        this.temp2.subtract(this.v[1]);
        this.normal.setToCrossProduct(this.temp1, this.temp2);
        this.normal.normalize();
        return this.normal;
    }
    
    public Vector3D getNormal() {
        return this.normal;
    }
    
    public void setNormal(final Vector3D n) {
        if (this.normal == null) {
            this.normal = new Vector3D(n);
        }
        else {
            this.normal.setTo(n);
        }
    }
    
    public boolean isFacing(final Vector3D u) {
        this.temp1.setTo(u);
        this.temp1.subtract(this.v[0]);
        return this.normal.getDotProduct(this.temp1) >= 0.0f;
    }
    
    public boolean clip(final float clipZ) {
        this.ensureCapacity(this.numVertices * 3);
        boolean isCompletelyHidden = true;
        for (int i = 0; i < this.numVertices; ++i) {
            final int next = (i + 1) % this.numVertices;
            Vector3D v1 = this.v[i];
            Vector3D v2 = this.v[next];
            if (v1.z < clipZ) {
                isCompletelyHidden = false;
            }
            if (v1.z > v2.z) {
                final Vector3D temp = v1;
                v1 = v2;
                v2 = temp;
            }
            if (v1.z < clipZ && v2.z > clipZ) {
                final float scale = (clipZ - v1.z) / (v2.z - v1.z);
                this.insertVertex(next, v1.x + scale * (v2.x - v1.x), v1.y + scale * (v2.y - v1.y), clipZ);
                ++i;
            }
        }
        if (isCompletelyHidden) {
            return false;
        }
        for (int i = this.numVertices - 1; i >= 0; --i) {
            if (this.v[i].z > clipZ) {
                this.deleteVertex(i);
            }
        }
        return this.numVertices >= 3;
    }
    
    protected void insertVertex(final int index, final float x, final float y, final float z) {
        final Vector3D newVertex = this.v[this.v.length - 1];
        newVertex.x = x;
        newVertex.y = y;
        newVertex.z = z;
        for (int i = this.v.length - 1; i > index; --i) {
            this.v[i] = this.v[i - 1];
        }
        this.v[index] = newVertex;
        ++this.numVertices;
    }
    
    protected void deleteVertex(final int index) {
        final Vector3D deleted = this.v[index];
        for (int i = index; i < this.v.length - 1; ++i) {
            this.v[i] = this.v[i + 1];
        }
        this.v[this.v.length - 1] = deleted;
        --this.numVertices;
    }
    
    public void add(final Transform3D xform) {
        Vector3D[] v;
        for (int length = (v = this.v).length, i = 0; i < length; ++i) {
            final Vector3D vector = v[i];
            vector.addRotation(xform);
            vector.add(xform.getLocation());
        }
    }
    
    public void subtract(final Transform3D xform) {
        Vector3D[] v;
        for (int length = (v = this.v).length, i = 0; i < length; ++i) {
            final Vector3D vector = v[i];
            vector.subtract(xform.getLocation());
            vector.subtractRotation(xform);
        }
    }
}
