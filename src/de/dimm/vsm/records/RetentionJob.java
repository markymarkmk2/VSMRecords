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
 * @author Administrator
 */
@Entity
public class RetentionJob  implements Serializable
{
    static final long serialVersionUID = 1L;
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @ManyToOne
    private Retention retention;
    
    
    
    boolean finished;
    long statNodes;
    long statHashes;
    long statDedups;
    long dedupSize;
    long statAttribs;
    long statIdx;
    
    long startIdx;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date start;

    public RetentionJob() {
        start = new Date();
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
        return "";
    }
   
    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    
    
    
    public Retention getRetention() {
        return retention;
    }

    public void setRetention( Retention retention ) {
        this.retention = retention;
    }

    public Date getStart() {
        return start;
    }

    public void setStart( Date start ) {
        this.start = start;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished( boolean finished ) {
        this.finished = finished;
    }

    public long getStartIdx() {
        return startIdx;
    }

    public void setStartIdx( long startIdx ) {
        this.startIdx = startIdx;
    }
    
    
    
    
        
}
