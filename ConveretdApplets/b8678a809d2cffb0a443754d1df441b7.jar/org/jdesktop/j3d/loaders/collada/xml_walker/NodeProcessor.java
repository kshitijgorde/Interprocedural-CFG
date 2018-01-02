// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Group;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.InstanceController;
import org.collada.colladaschema.InstanceGeometry;
import org.collada.colladaschema.InstanceWithExtra;
import javax.media.j3d.Transform3D;
import javax.vecmath.Matrix4d;
import org.collada.colladaschema.Matrix;
import org.collada.colladaschema.Node;
import javax.media.j3d.TransformGroup;
import java.util.ArrayList;

public class NodeProcessor extends Processor
{
    private ArrayList<Processor> children;
    private TransformGroup tg;
    
    public NodeProcessor(final Node node, final Processor parent) {
        super(node, parent);
        this.children = new ArrayList<Processor>();
        this.tg = null;
        final String id = node.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        final List<Object> list = node.getTranslatesAndMatrixesAndLookats();
        for (final Object object : list) {
            if (object instanceof Matrix) {
                final Matrix mt = (Matrix)object;
                final List<Double> m = mt.getValues();
                final double[] da = new double[m.size()];
                int i = 0;
                for (final Double d : m) {
                    da[i++] = d;
                }
                final Matrix4d m4d = new Matrix4d(da);
                final Transform3D t3d = new Transform3D(m4d);
                this.tg = new TransformGroup(t3d);
            }
        }
        final List<Node> nodes = node.getNodes();
        for (final Node nodeType : nodes) {
            this.children.add(ProcessorFactory.createProcessor(nodeType, this));
        }
        final List<InstanceWithExtra> instanceNodes = node.getInstanceNodes();
        for (final InstanceWithExtra nodeType2 : instanceNodes) {
            this.children.add(ProcessorFactory.createProcessor(nodeType2, this));
        }
        final List<InstanceGeometry> geometries = node.getInstanceGeometries();
        for (final InstanceGeometry nodeType3 : geometries) {
            this.children.add(ProcessorFactory.createProcessor(nodeType3, this));
        }
        final List<InstanceController> controllers = node.getInstanceControllers();
        for (final InstanceController nodeType4 : controllers) {
            this.children.add(ProcessorFactory.createProcessor(nodeType4, this));
        }
        final List<InstanceWithExtra> lights = node.getInstanceLights();
        for (final InstanceWithExtra nodeType5 : lights) {
            this.children.add(ProcessorFactory.createProcessor(nodeType5, this));
        }
        final List<InstanceWithExtra> cameras = node.getInstanceCameras();
        for (final InstanceWithExtra nodeType6 : cameras) {
            this.children.add(ProcessorFactory.createProcessor(nodeType6, this));
        }
    }
    
    @Override
    public void create(javax.media.j3d.Node parent) {
        if (this.tg != null) {
            if (this.tg.getParent() == null) {
                ((Group)parent).addChild((javax.media.j3d.Node)this.tg);
            }
            parent = (javax.media.j3d.Node)this.tg;
        }
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
