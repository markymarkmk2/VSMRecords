/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author Administrator
 */
@Entity
public class ArchiveJob  implements Serializable
{

    public static final String AJ_SOURCE_HF = "h";
    public static final String AJ_SOURCE_CLIENT = "c";
    public static final String AJ_SOURCE_MM = "m";

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    @Index
    private Date startTime;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)

    private Date endTime;
    @Index
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date lastAccess;

    @Index
    private boolean ok;

    @Index
    private String name;

    @ManyToOne
    private FileSystemElemNode directory;

    @Index
    private String sourceType;

    @Index
    private long sourceIdx;
    
    private long totalSize;

    // DAS SIND DIE VERKNUEPFUNGEN DER DATEIEN DIESES JOBS
    @OneToMany(mappedBy = "archiveJob", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    List<ArchiveJobFileLink>links;

    public ArchiveJob()
    {
        links = new ArrayLazyList<ArchiveJobFileLink>();
    }


    
    /**
     * @return the idx
     */
    public long getIdx()
    {
        return idx;
    }

    @Override
    public String toString()
    {
        return "ArchiveJob " + getName() + " " + getStartTime().toString() + " " + (isOk()?"ok":"nok");
    }

 
    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime()
    {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime( Date startTime )
    {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime()
    {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime( Date endTime )
    {
        this.endTime = endTime;
    }

    /**
     * @return the lastAccess
     */
    public Date getLastAccess()
    {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess( Date lastAccess )
    {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the ok
     */
    public boolean isOk()
    {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk( boolean ok )
    {
        this.ok = ok;
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
     * @return the directory
     */
    public FileSystemElemNode getDirectory()
    {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory( FileSystemElemNode directory )
    {
        this.directory = directory;
    }

    /**
     * @return the sourceType
     */
    public String getSourceType()
    {
        return sourceType;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType( String sourceType )
    {
        this.sourceType = sourceType;
    }

    /**
     * @return the sourceIdx
     */
    public long getSourceIdx()
    {
        return sourceIdx;
    }

    /**
     * @param sourceIdx the sourceIdx to set
     */
    public void setSourceIdx( long sourceIdx )
    {
        this.sourceIdx = sourceIdx;
    }

    /**
     * @return the totalSize
     */
    public long getTotalSize()
    {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize( long totalSize )
    {
        this.totalSize = totalSize;
    }

    public LazyList<ArchiveJobFileLink> getLinks()
    {
        return (LazyList<ArchiveJobFileLink>)links;
    }

    public void setLinks( LazyList<ArchiveJobFileLink> links )
    {
        this.links = links;
    }

    
    
}
