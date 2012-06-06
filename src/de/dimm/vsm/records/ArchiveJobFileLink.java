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

/**
 *
 * @author mw
 */
@Entity
public class ArchiveJobFileLink implements Serializable
{
    static final long serialVersionUID = 1L;

    public ArchiveJobFileLink()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idx;


    // AUFENTHALTSORT DER DATEI
    @ManyToOne
    FileSystemElemNode fileNode;

    @ManyToOne
    ArchiveJob archiveJob;


    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long idx )
    {
        this.idx = idx;
    }

   

    public FileSystemElemNode getFileNode()
    {
        return fileNode;
    }


    public void setFileNode( FileSystemElemNode fileNode )
    {
        this.fileNode = fileNode;
    }

    public ArchiveJob getArchiveJob()
    {
        return archiveJob;
    }

    public void setArchiveJob( ArchiveJob archiveJob )
    {
        this.archiveJob = archiveJob;
    }

   

    
 }
