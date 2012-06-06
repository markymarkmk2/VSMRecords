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
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author Administrator
 */
@Entity
public class XANode  implements Serializable
{
    static final long serialVersionUID = 1L;

    public static final int HASCHBITS=256;
    public static final int HASCHLEN=64;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;


    // FILE TO WHICH THIS XA BELONGS BELONGS
    @ManyToOne
    FileSystemElemNode fileNode;

    @Index
    private long ts;


    @Index
    private String hashvalue;

    @Index
    boolean reorganize;   // NODE HAS TO BE MOVED FROM HASHBLOCK BUFFER TO ORIGFILE

    @Index
    private long blockOffset;
    private int blockLen;

     // IS THERE A DEDUPED BLOCK WHICH CONTAINS OUR DATA? ( CAN BE null)
    @ManyToOne
    DedupHashBlock dedupBlock;

    private int streamInfo;
    

    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long id )
    {
        this.idx = id;
    }

    /**
     * @return the ts
     */
    public long getTs()
    {
        return ts;
    }

    /**
     * @param ts the ts to set
     */
    public void setTs( long ts )
    {
        this.ts = ts;
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

    public boolean isReorganize()
    {
        return reorganize;
    }

    public void setReorganize( boolean reorganize )
    {
        this.reorganize = reorganize;
    }

    public void setFileNode( FileSystemElemNode fileNode )
    {
        this.fileNode = fileNode;
    }

    public FileSystemElemNode getFileNode()
    {
        return fileNode;
    }

 /**
     * @return the blockOffset
     */
    public long getBlockOffset()
    {
        return blockOffset;
    }

    /**
     * @param blockOffset the blockOffset to set
     */
    public void setBlockOffset( long blockOffset )
    {
        this.blockOffset = blockOffset;
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

    public DedupHashBlock getDedupBlock()
    {
        return dedupBlock;
    }

    public void setDedupBlock( DedupHashBlock dedupBlock )
    {
        this.dedupBlock = dedupBlock;
    }

    public int getStreamInfo()
    {
        return streamInfo;
    }

    public void setStreamInfo( int streamInfo )
    {
        this.streamInfo = streamInfo;
    }


    
   

}

