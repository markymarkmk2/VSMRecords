/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author Administrator
 */
@Entity

@NamedQuery(name="findDedupHashBlock",
        query="SELECT dh " +
              "FROM DedupHashBlock dh " +
              "WHERE dh.hashvalue = :hashValue")


public class DedupHashBlock  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;


    // POOL TO WHICH THIS BLOCK BELONGS
    @ManyToOne
    StoragePool pool;

    // LINK TO STORAGE NODE
    @ManyToOne
    AbstractStorageNode storageNode;

    private int blockLen;

    // HASH
    @Index(unique=true)
    private String hashvalue;

    @Override
    public String toString()
    {
        return "Idx: " + idx + " Len: " + blockLen + " " + hashvalue;
    }




    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long id )
    {
        this.idx = id;
    }


    /**
     * @return the blockLen
     */
    public int getBlockLen()
    {
        return blockLen;
    }

    /**
     * @param blockLen the blockLen to set
     */
    public void setBlockLen( int blockLen )
    {
        this.blockLen = blockLen;
    }


    /**
     * @return the hashvalue
     */
    public String getHashvalue()
    {
        return hashvalue;
    }

    /**
     * @param hashvalue the hashvalue to set
     */
    public void setHashvalue( String hashvalue )
    {
        this.hashvalue = hashvalue;
    }

    public StoragePool getPool()
    {
        return pool;
    }

    public void setPool( StoragePool pool )
    {
        this.pool = pool;
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

