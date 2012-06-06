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
import javax.persistence.Temporal;

/**
 *
 * @author mw
 */
@Entity
public class PoolNodeFileLink implements Serializable
{
    static final long serialVersionUID = 1L;

    public PoolNodeFileLink()
    {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idx;


    // AUFENTHALTSORT DER DATEI
    @ManyToOne
    FileSystemElemNode fileNode;

    @ManyToOne
    AbstractStorageNode storageNode;

    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date creation;

    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long idx )
    {
        this.idx = idx;
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

    public FileSystemElemNode getFileNode()
    {
        return fileNode;
    }


    public void setFileNode( FileSystemElemNode fileNode )
    {
        this.fileNode = fileNode;
    }

    public AbstractStorageNode getStorageNode()
    {
        return storageNode;
    }

    public void setStorageNode( AbstractStorageNode storageNode )
    {
        this.storageNode = storageNode;
    }


    
 }
