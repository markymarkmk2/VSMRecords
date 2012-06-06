/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.GenericEntityManager;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author mw
 * Stellt einen Mount eines virtuellen Dateisystens dar
 * Die Daten liegen dann in StorageNodes vor
 */
@Entity
public class StoragePool implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    private String name;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creation;

    /*
    @Index
    long rootDirIdx;
*/

    @OneToOne(orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    FileSystemElemNode rootDir;


    boolean landingZone;
    
    @OneToMany(mappedBy = "pool", fetch=FetchType.EAGER)
    List<AbstractStorageNode>storageNodes;

    public StoragePool()
    {
        storageNodes = new ArrayLazyList<AbstractStorageNode>();
    }

    /**
     * @return the idx
     */
    public long getIdx()
    {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public boolean isLandingZone()
    {
        return landingZone;
    }

    public void setLandingZone( boolean landingZone )
    {
        this.landingZone = landingZone;
    }
    

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the creation
     */
    public Date getCreation()
    {
        return creation;
    }

    /**
     * @param creation the creation to set
     */
    public void setCreation( Date creation )
    {
        this.creation = creation;
    }

    public FileSystemElemNode getRootDir()
    {
        return rootDir;
    }

    public void setRootDir( FileSystemElemNode rootDir )
    {
        this.rootDir = rootDir;
    }
/*
    public long getRootDirIdx()
    {
        return rootDirIdx;
    }

    public void setRootDirIdx( long rootDirIdx )
    {
        this.rootDirIdx = rootDirIdx;
    }*/



    public LazyList<AbstractStorageNode> getStorageNodes()
    {
        return (LazyList<AbstractStorageNode>)storageNodes;
    }
    public List<AbstractStorageNode> getStorageNodes(GenericEntityManager em)
    {
        return getStorageNodes().getList(em);
    }

    public void setStorageNodes( LazyList<AbstractStorageNode> storageNodes )
    {
        this.storageNodes = storageNodes;
    }

    @Override
    public String toString()
    {
        return getName();
    }

   

}
