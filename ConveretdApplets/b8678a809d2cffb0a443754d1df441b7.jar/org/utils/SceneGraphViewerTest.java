// 
// Decompiled by Procyon v0.5.30
// 

package org.utils;

import javax.vecmath.Point3f;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Node;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;

public class SceneGraphViewerTest
{
    public static void main(final String[] args) {
        final BranchGroup bg = new BranchGroup();
        final Appearance ap = new Appearance();
        final Material mat = new Material(new Color3f(0.5f, 0.2f, 0.2f), new Color3f(0.5f, 0.2f, 0.2f), new Color3f(0.5f, 0.2f, 0.2f), new Color3f(0.5f, 0.2f, 0.2f), 0.0f);
        ap.setMaterial(mat);
        final Box box = new Box(10.0f, 0.1f, 10.0f, ap);
        final Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3f(0.0f, -5.0f, 0.0f));
        final TransformGroup tg = new TransformGroup(t3d);
        tg.addChild((Node)box);
        bg.addChild((Node)tg);
        final SpotLight sl = new SpotLight();
        sl.setInfluencingBounds(Java3DDebugUtilities.BOUNDS_WHOLE_WORLD);
        sl.setEnable(true);
        sl.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        sl.setPosition(new Point3f(0.0f, 0.0f, 0.0f));
        sl.setAttenuation(new Point3f(1.0f, 0.0f, 0.0f));
        sl.setDirection(new Vector3f(0.0f, -1.0f, 0.0f));
        sl.setSpreadAngle(0.785f);
        sl.setConcentration(0.0f);
        bg.addChild((Node)sl);
        SceneGraphViewer.bootSceneGraphViewer(bg);
    }
}
