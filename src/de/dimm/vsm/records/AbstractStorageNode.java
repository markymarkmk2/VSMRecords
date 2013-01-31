
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author mw
 * Stellt einen realen Speicherablageort eines FilySystemElemNodes dar
 * Subklassen können KlonesStorageNode, FS_StorageNode, FTP_StorageNode, S3_StorageNode etc sein
 */
@Entity
public class AbstractStorageNode implements Serializable
{
    static final long serialVersionUID = 1L;

    public static final String NT_FILESYSTEM = "fs";
    public static final String NT_FTP = "ftp";
    public static final String NT_S3 = "s3";

    public static final String NM_VIRGIN = "VIRG";
    public static final String NM_ONLINE = "ONLINE";
    public static final String NM_OFFLINE = "OFFLINE";
    public static final String NM_EMPTYING = "EMPTYING";
    public static final String NM_EMPTIED = "EMPTIED";
    public static final String NM_FULL = "FULL";
    public static final String NM_TEMP_OFFLINE = "TMP_OFFLINE";



    private String mountPoint;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    protected String name;
    
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    protected Date creation;


    private String nodeMode;
    private String nodeType;



    @ManyToOne
    private StoragePool pool;


    @OneToOne
    private AbstractStorageNode cloneNode;

    /**
     * @return the name
     */
    public String getMountPoint()
    {
        return mountPoint;
    }

    public void setMountPoint( String mountPoint )
    {
        this.mountPoint = mountPoint;
    }


    /**
     * @return the node_type
     */
    public String getNodeType()
    {
        return nodeType;
    }

    /**
     * @param node_type the node_type to set
     */
    public void setNodeType( String node_type )
    {
        this.nodeType = node_type;
    }

    /**
     * @return the sn_mode
     */
    public String getNodeMode()
    {
        return nodeMode;
    }

    /**
     * @param sn_mode the sn_mode to set
     */
    public void setNodeMode( String sn_mode )
    {
        this.nodeMode = sn_mode;
    }


    @Override
    public String toString()
    {
        return name;
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
  
    public boolean isOnline()
    {
        return getNodeMode().equals(NM_ONLINE);
    }
    public boolean isTempOffline()
    {
        return getNodeMode().equals(NM_TEMP_OFFLINE);
    }
    public boolean isFullOrOnline()
    {
        return getNodeMode().equals(NM_ONLINE) || getNodeMode().equals(NM_FULL);
    }
    public boolean isVirgin()
    {
        return getNodeMode().equals(NM_VIRGIN);
    }
    public boolean isFS()
    {
        return getNodeType().equals(NT_FILESYSTEM);
    }
    public boolean isFTP()
    {
        return getNodeType().equals(NT_FTP);
    }
    public boolean isS3()
    {
        return getNodeType().equals(NT_S3);
    }

    public static AbstractStorageNode createFSNode()
    {
        AbstractStorageNode n = new AbstractStorageNode();
        n.setNodeType(NT_FILESYSTEM);
        n.setCreation( new Date() );
        n.setNodeMode(NM_VIRGIN);
        return n;
    }
    public static AbstractStorageNode createFTPNode()
    {
        AbstractStorageNode n = new AbstractStorageNode();
        n.setNodeType(NT_FTP);
        n.setNodeMode(NM_VIRGIN);
        return n;
    }
    public static AbstractStorageNode createS3Node()
    {
        AbstractStorageNode n = new AbstractStorageNode();
        n.setNodeType(NT_S3);
        n.setNodeMode(NM_VIRGIN);
        return n;
    }

    public StoragePool getPool()
    {
        return pool;
    }

    public void setPool( StoragePool storagePool )
    {
        this.pool = storagePool;
    }

    public AbstractStorageNode getCloneNode()
    {
        return cloneNode;
    }

    public void setCloneNode( AbstractStorageNode cloneNode )
    {
        this.cloneNode = cloneNode;
    }


    

}

