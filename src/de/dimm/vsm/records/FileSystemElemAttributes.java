/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.eclipse.persistence.annotations.Index;


// TODO HISTORY ON FILESYSTEM???
/**
 *
 * @author mw
 */
@Entity
public class FileSystemElemAttributes implements Serializable
{
    private static final long serialVersionUID = -562987599581461682L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;
   

    @Index
    private String name;

    private long creationDateMs;
    private long modificationDateMs;
    private long accessDateMs;

    boolean deleted;
    
    @Index
    private long ts;

    private int posixMode;
    long fsize;
    long xasize;
    private int uid;
    private int gid;
    private String uidName;
    private String gidName;

    @Lob
    private String xattribute;

    private int aclinfo;


    // DIR STRUKTUR
    @ManyToOne
    private FileSystemElemNode file;

    long flags;


    public FileSystemElemAttributes()
    {
    }


    // COPY CONSTRUCTOR
    public FileSystemElemAttributes( FileSystemElemAttributes orig )
    {
        name = orig.name;
        creationDateMs = orig.creationDateMs;
        modificationDateMs = orig.modificationDateMs;
        accessDateMs = orig.accessDateMs;
        deleted = orig.deleted;
        ts = orig.ts;
        posixMode = orig.posixMode;
        fsize = orig.fsize;
        xasize = orig.xasize;
        uid = orig.uid;
        gid = orig.gid;
        uidName = orig.uidName;
        gidName = orig.gidName;
        xattribute = orig.xattribute;
        aclinfo = orig.aclinfo;
        file = orig.file;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy HH.mm.ss");
//        return "Attr " + name + " TS " + ts + " len " + fsize + " (" + sdf.format( new Date(ts)) + ")";
        return name + " len " + fsize + " " + super.toString();
    }

    public void rename_To( String string )
    {
        //throw new UnsupportedOperationException("Not yet implemented");
        setName( string );
    }




    // GETTER / SETTER

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


 
    public long getFsize()
    {
        return fsize;
    }
    public void setFsize(long s)
    {
        fsize = s;
    }

    public long getStreamSize()
    {
        return xasize;
    }

    public void setStreamSize( long xasize )
    {
        this.xasize = xasize;
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
     * @return the base_ts
     */
    public long getTs()
    {
        return ts;
    }

    /**
     * @param base_ts the base_ts to set
     */
    public void setTs( long base_ts )
    {
        this.ts = base_ts;
    }



    /**
     * @return the posix_flags
     */
    public int getPosixMode()
    {
        return posixMode;
    }

    /**
     * @param posix_flags the posix_flags to set
     */
    public void setPosixMode( int posix_flags )
    {
        this.posixMode = posix_flags;
    }



    /**
     * @return the uid
     */
    public int getUid()
    {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid( int uid )
    {
        this.uid = uid;
    }

    /**
     * @return the gid
     */
    public int getGid()
    {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid( int gid )
    {
        this.gid = gid;
    }

  

    /**
     * @return the creationDateMs
     */
    public long getCreationDateMs()
    {
        return creationDateMs;
    }

    /**
     * @param creationDateMs the creationDateMs to set
     */
    public void setCreationDateMs( long creationDateMs )
    {
        this.creationDateMs = creationDateMs;
    }

    /**
     * @return the modificationDateMs
     */
    public long getModificationDateMs()
    {
        return modificationDateMs;
    }

    /**
     * @param modificationDateMs the modificationDateMs to set
     */
    public void setModificationDateMs( long modificationDateMs )
    {
        this.modificationDateMs = modificationDateMs;
    }

    /**
     * @return the accessDateMs
     */
    public long getAccessDateMs()
    {
        return accessDateMs;
    }

    /**
     * @param accessDateMs the accessDateMs to set
     */
    public void setAccessDateMs( long accessDateMs )
    {
        this.accessDateMs = accessDateMs;
    }

    public void setFile( FileSystemElemNode parent )
    {
        this.file = parent;
    }

    public FileSystemElemNode getFile()
    {
        return file;
    }
    
  
    /**
     * @return the xattribute
     */
    public String getAclInfoData()
    {
        return xattribute;
    }

    /**
     * @param xattribute the xattribute to set
     */
    public void setAclInfoData( String xattribute )
    {
        this.xattribute = xattribute;
    }

    public int getAclinfo()
    {
        return aclinfo;
    }

    public void setAclinfo( int aclinfo )
    {
        this.aclinfo = aclinfo;
    }

    

    public void setDeleted( boolean deleted )
    {
        this.deleted = deleted;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public String getGidName()
    {
        return gidName;
    }

    public String getUidName()
    {
        return uidName;
    }

    public void setGidName( String gidName )
    {
        this.gidName = gidName;
    }

    public void setUidName( String uidName )
    {
        this.uidName = uidName;
    }

    public long getFlags()
    {
        return flags;
    }

    public void setFlags( long flags )
    {
        this.flags = flags;
    }
    public void setFlag( long flag )
    {
        this.flags |= flag;
    }
    public void clrFlag( long flag )
    {
        this.flags &= ~flags;
    }
    public boolean hasFlag( long flag )
    {
        return (flags & flag) != 0;
    }



}

