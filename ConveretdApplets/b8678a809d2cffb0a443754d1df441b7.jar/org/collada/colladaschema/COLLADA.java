// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "libraryLightsAndLibraryGeometriesAndLibraryAnimationClips", "scene", "extras" })
@XmlRootElement(name = "COLLADA")
public class COLLADA
{
    @XmlElement(required = true)
    protected Asset asset;
    @XmlElements({ @XmlElement(name = "library_materials", type = LibraryMaterials.class), @XmlElement(name = "library_force_fields", type = LibraryForceFields.class), @XmlElement(name = "library_nodes", type = LibraryNodes.class), @XmlElement(name = "library_effects", type = LibraryEffects.class), @XmlElement(name = "library_animations", type = LibraryAnimations.class), @XmlElement(name = "library_physics_scenes", type = LibraryPhysicsScenes.class), @XmlElement(name = "library_cameras", type = LibraryCameras.class), @XmlElement(name = "library_animation_clips", type = LibraryAnimationClips.class), @XmlElement(name = "library_visual_scenes", type = LibraryVisualScenes.class), @XmlElement(name = "library_images", type = LibraryImages.class), @XmlElement(name = "library_physics_materials", type = LibraryPhysicsMaterials.class), @XmlElement(name = "library_controllers", type = LibraryControllers.class), @XmlElement(name = "library_lights", type = LibraryLights.class), @XmlElement(name = "library_geometries", type = LibraryGeometries.class), @XmlElement(name = "library_physics_models", type = LibraryPhysicsModels.class) })
    protected List<Object> libraryLightsAndLibraryGeometriesAndLibraryAnimationClips;
    protected Scene scene;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
    protected String version;
    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
    protected String base;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<Object> getLibraryLightsAndLibraryGeometriesAndLibraryAnimationClips() {
        if (this.libraryLightsAndLibraryGeometriesAndLibraryAnimationClips == null) {
            this.libraryLightsAndLibraryGeometriesAndLibraryAnimationClips = new ArrayList<Object>();
        }
        return this.libraryLightsAndLibraryGeometriesAndLibraryAnimationClips;
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    public void setScene(final Scene value) {
        this.scene = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(final String value) {
        this.version = value;
    }
    
    public String getBase() {
        return this.base;
    }
    
    public void setBase(final String value) {
        this.base = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "instancePhysicsScenes", "instanceVisualScene", "extras" })
    public static class Scene
    {
        @XmlElement(name = "instance_physics_scene")
        protected List<InstanceWithExtra> instancePhysicsScenes;
        @XmlElement(name = "instance_visual_scene")
        protected InstanceWithExtra instanceVisualScene;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        
        public List<InstanceWithExtra> getInstancePhysicsScenes() {
            if (this.instancePhysicsScenes == null) {
                this.instancePhysicsScenes = new ArrayList<InstanceWithExtra>();
            }
            return this.instancePhysicsScenes;
        }
        
        public InstanceWithExtra getInstanceVisualScene() {
            return this.instanceVisualScene;
        }
        
        public void setInstanceVisualScene(final InstanceWithExtra value) {
            this.instanceVisualScene = value;
        }
        
        public List<Extra> getExtras() {
            if (this.extras == null) {
                this.extras = new ArrayList<Extra>();
            }
            return this.extras;
        }
    }
}
