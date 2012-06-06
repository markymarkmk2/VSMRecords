
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
 *  */
@Entity
public class Snapshot implements Serializable
{
    static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    private String name;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creation;


    @ManyToOne
    private StoragePool pool;

  

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

    public StoragePool getPool()
    {
        return pool;
    }

    public void setPool( StoragePool storagePool )
    {
        this.pool = storagePool;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Date getCreation()
    {
        return creation;
    }

    public void setCreation( Date creation )
    {
        this.creation = creation;
    }
    
}

