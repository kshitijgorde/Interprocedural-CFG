// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.ColoringAttributes;
import javax.vecmath.Color3f;
import org.jdesktop.j3d.loaders.collada.Collada14LoaderOptions;
import javax.media.j3d.Node;
import javax.media.j3d.Geometry;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.geometry.GeometryInfo;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigInteger;
import org.collada.colladaschema.InputLocalOffset;
import java.util.List;

public class PrimitiveProcessor extends Processor
{
    private int[] prim;
    private int[] coordinateIndices;
    private float[] coordinates;
    private int[] normalIndices;
    private float[] normals;
    private int[] texCoordinateIndices;
    private float[] texCoordinates;
    private String materialId;
    
    public PrimitiveProcessor(final Object colladaSchema, final Processor parent) {
        super(colladaSchema, parent);
        this.prim = null;
        this.coordinateIndices = null;
        this.coordinates = null;
        this.normalIndices = null;
        this.normals = null;
        this.texCoordinateIndices = null;
        this.texCoordinates = null;
        this.materialId = null;
    }
    
    protected void initiate(final List<InputLocalOffset> inputs, final List<BigInteger> pList, final String matId) {
        if (pList != null) {
            this.prim = new int[pList.size()];
            int i = 0;
            for (final BigInteger p : pList) {
                this.prim[i++] = p.intValue();
            }
        }
        int maxOffset = 0;
        for (final InputLocalOffset in : inputs) {
            maxOffset = Math.max(in.getOffset().intValue(), maxOffset);
        }
        for (final InputLocalOffset in : inputs) {
            this.logger.info("Inputs " + in.getSemantic() + " source " + in.getSource() + "  offset " + in.getOffset());
            if (in.getSemantic().equalsIgnoreCase("VERTEX")) {
                this.processVertices(in, maxOffset);
            }
            else if (in.getSemantic().equalsIgnoreCase("NORMAL")) {
                this.processNormals(in, maxOffset);
            }
            else if (in.getSemantic().equalsIgnoreCase("TEXCOORD")) {
                this.processTexCoordinates(in, maxOffset);
            }
            else {
                this.logger.warning("UNIMPLEMENTED SEMANTIC " + in.getSemantic());
            }
        }
        this.materialId = matId;
    }
    
    private void processVertices(final InputLocalOffset in, final int maxOffset) {
        final VerticesProcessor source = ElementCache.cache().getVertices(in.getSource());
        final int offset = in.getOffset().intValue();
        if (source == null) {
            this.logger.warning("Failed to get Vertices " + in.getSource());
        }
        else {
            this.coordinates = source.getFloatArray();
            final ArrayList<Integer> coordInd = new ArrayList<Integer>();
            for (int i = offset; i < this.prim.length; i += maxOffset + 1) {
                coordInd.add(this.prim[i]);
            }
            this.coordinateIndices = new int[coordInd.size()];
            int i = 0;
            for (final int index : coordInd) {
                this.coordinateIndices[i++] = index;
            }
        }
    }
    
    private void processTexCoordinates(final InputLocalOffset in, final int maxOffset) {
        final SourceProcessor source = ElementCache.cache().getSource(in.getSource());
        final int offset = in.getOffset().intValue();
        if (source == null) {
            this.logger.warning("Failed to get texcoords " + in.getSource());
        }
        else {
            this.texCoordinates = source.getFloatArray();
            final ArrayList<Integer> coordInd = new ArrayList<Integer>();
            for (int i = offset; i < this.prim.length; i += maxOffset + 1) {
                coordInd.add(this.prim[i]);
            }
            this.texCoordinateIndices = new int[coordInd.size()];
            int i = 0;
            for (final int index : coordInd) {
                this.texCoordinateIndices[i++] = index;
            }
        }
    }
    
    private void processNormals(final InputLocalOffset in, final int maxOffset) {
        final SourceProcessor source = ElementCache.cache().getSource(in.getSource());
        final int offset = in.getOffset().intValue();
        if (source == null) {
            this.logger.warning("Failed to get Normals " + in.getSource());
        }
        else {
            this.normals = source.getFloatArray();
            final ArrayList<Integer> normInd = new ArrayList<Integer>();
            for (int i = offset; i < this.prim.length; i += maxOffset + 1) {
                normInd.add(this.prim[i]);
            }
            this.normalIndices = new int[normInd.size()];
            int i = 0;
            for (final int index : normInd) {
                this.normalIndices[i++] = index;
            }
        }
    }
    
    protected Shape3D getShape3D(final int type) {
        this.logger.info("TrianglesTypeProcessor#getShape3D");
        final Shape3D s3d = new Shape3D();
        s3d.setAppearance(new Appearance());
        final GeometryInfo gi = new GeometryInfo(type);
        gi.setCoordinates(this.coordinates);
        gi.setCoordinateIndices(this.coordinateIndices);
        if (this.texCoordinates != null) {
            gi.setTextureCoordinateParams(1, 2);
            gi.setTextureCoordinates(0, this.texCoordinates);
            gi.setTextureCoordinateIndices(0, this.texCoordinateIndices);
        }
        if (this.normals != null) {
            gi.setNormals(this.normals);
            gi.setNormalIndices(this.normalIndices);
        }
        final Stripifier strip = new Stripifier();
        strip.stripify(gi);
        s3d.addGeometry((Geometry)gi.getGeometryArray());
        final String s = ElementCache.cache().getMaterial(this.materialId);
        final MaterialProcessor matProc = (MaterialProcessor)ElementCache.cache().get(s);
        if (matProc != null) {
            matProc.create((Node)s3d);
        }
        if (Collada14LoaderOptions.getInstance().isColored()) {
            final Appearance ap = new Appearance();
            ap.setColoringAttributes(new ColoringAttributes(new Color3f((float)Math.random(), (float)Math.random(), (float)Math.random()), 1));
            s3d.setAppearance(ap);
        }
        return s3d;
    }
}
