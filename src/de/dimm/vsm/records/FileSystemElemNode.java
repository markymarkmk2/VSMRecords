/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.GenericEntityManager;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author mw
 */
@Entity
public class FileSystemElemNode implements Serializable
{
    private static final long serialVersionUID = -562987599581461681L;

    // FLAGS
    public static final long FL_DATEDIR = 0x0001;

    // TODO: xattributes in database

    public static final String FT_FILE = "file";
    public static final String FT_DIR = "dir";
    public static final String FT_SYMLINK = "slink";
    public static final String FT_HARDLINK = "hlink";
    public static final String FT_OTHER = "other";

    public static final String FS_POSIX = "posx";
    public static final String FS_NTFS = "ntfs";
    public static final String FS_ES = "ethr";
    public static final String FS_AT = "atlk";
    public static final String FS_XINET = "xint";
    public static final String FS_OSX_HFS = "hfs+";
    public static final String FS_OSX_XSAN = "xsan";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    private String typ;


  
    String filesystem_type;


    // POOL IST DAS FILESYSTEM DIESES NODES
    @ManyToOne()
    StoragePool pool;

    // DAS SIND DIE VERKNUEPFUNGEN DER DATEI IN DIE STORAGE NODES
    @OneToMany(mappedBy = "fileNode", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    List<PoolNodeFileLink>links;

    // DIR-STRUKTUR
    @OneToMany(mappedBy = "parent")
    List<FileSystemElemNode>children;

    // DIR STRUKTUR
    @ManyToOne(fetch=FetchType.LAZY)
    private FileSystemElemNode parent;
  
//    // ACTUAL ATTRIBUTES IS MANY TO ONE, SO THEREFORE WE CAN DELETE FSN WITHOUT FOREIGN LKEY LOCKING
//    // IN REALITY THIS IS AN ONOTOONE
//    //@Transient
    @Index
    long attributes_idx;

    @Transient
    private FileSystemElemAttributes attributes;

    // ATTRIBUTES-STRUKTUR
    @OneToMany(mappedBy = "file",orphanRemoval=true,cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    List<FileSystemElemAttributes>history;

    // DAS SIND DIE VERKNUEPFUNGEN DER DATEI IN DIE HASHBLOCKS
    @OneToMany(mappedBy = "fileNode", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    List<HashBlock>hashBlocks;
    // DAS SIND DIE VERKNUEPFUNGEN DER DATEI IN DIE ATTRIBUTE-HASHBLOCKS
    @OneToMany(mappedBy = "fileNode", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    List<XANode>xaNodes;

    long flags;

    public FileSystemElemNode()
    {

    }


    @Override
    public String toString()
    {
        if (attributes != null)
            return attributes.getName();

        throw new RuntimeException("Uninitalized attribute");
    }


    // TESTER

    public boolean isSymbolicLink()
    {
        if (getTyp() == null)
            return false;

        return getTyp().equals(FileSystemElemNode.FT_SYMLINK);
    }

    public boolean isFile()
    {
        if (getTyp() == null)
        {
            return !isDirectory();
        }
        return getTyp().equals(FileSystemElemNode.FT_FILE);
    }

    public boolean isHardLink()
    {
        if (getFilesystem_type() == null)
            return false;

        return getTyp().equals(FileSystemElemNode.FT_HARDLINK);
    }

    public boolean isDirectory()
    {
//        if (getTyp() == null)
//        {
//            return getName().equals("/");
//        }
        return getTyp().equals(FileSystemElemNode.FT_DIR);
    }

    public static boolean isSymbolicLink(String fse_typ )
    {
        return fse_typ.equals(FileSystemElemNode.FT_SYMLINK);
    }

    public static boolean isHardLink(String fse_typ)
    {
        return fse_typ.equals(FileSystemElemNode.FT_HARDLINK);
    }

    public static boolean isDirectory(String fse_typ)
    {
        return fse_typ.equals(FileSystemElemNode.FT_DIR);
    }

    public static boolean isFile(String fse_typ)
    {
        return fse_typ.equals(FileSystemElemNode.FT_FILE);
    }


    // FACTORIES
    public static FileSystemElemNode createFileNode()
    {
        FileSystemElemNode n = new FileSystemElemNode();
        n.setTyp(FT_FILE);
        n.initLists();
        return n;
    }
    public static FileSystemElemNode createDirNode()
    {
        FileSystemElemNode n = new FileSystemElemNode();
        n.setTyp(FT_DIR);
        n.initLists();

        return n;
    }
    public static FileSystemElemNode createHardlinkNode()
    {
        FileSystemElemNode n = new FileSystemElemNode();
        n.setTyp(FT_HARDLINK);
        n.initLists();
        return n;
    }
    public static FileSystemElemNode createSymLinkNode()
    {
        FileSystemElemNode n = new FileSystemElemNode();
        n.setTyp(FT_SYMLINK);
        n.initLists();
        return n;
    }
    public static FileSystemElemNode createOtherNode()
    {
        FileSystemElemNode n = new FileSystemElemNode();
        n.setTyp(FT_OTHER);
        n.initLists();
        return n;
    }

    private void initLists()
    {
        setAttributes( new FileSystemElemAttributes());
        setChildren( new ArrayLazyList<FileSystemElemNode>());
        setHistory( new ArrayLazyList<FileSystemElemAttributes>());
        getHistory().addIfRealized(getAttributes());
        getAttributes().setFile(this);

        // SET ACTUAL TIMESTAMP
        long n = System.currentTimeMillis();
        getAttributes().setCreationDateMs(n);
        getAttributes().setModificationDateMs(n);
        getAttributes().setAccessDateMs(n);
        
        setLinks( new ArrayLazyList<PoolNodeFileLink>());
        setXaNodes(new ArrayLazyList<XANode>());
        setHashBlocks( new ArrayLazyList<HashBlock>());
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


    /**
     * @return the typ
     */
    public String getTyp()
    {
        return typ;
    }

    /**
     * @param typ the typ to set
     */
    public void setTyp( String typ )
    {
        this.typ = typ;
    }


    public void setChildren( LazyList<FileSystemElemNode> children )
    {
        this.children = children;
    }

    public LazyList<FileSystemElemNode> getChildren()
    {
        if (children instanceof LazyList)
            return (LazyList<FileSystemElemNode>)children;


        return null;
    }
    public List<FileSystemElemNode> getChildren(GenericEntityManager em)
    {
        return getChildren().getList(em);
    }

    public void setParent( FileSystemElemNode parent )
    {
        this.parent = parent;
    }

    public FileSystemElemNode getParent()
    {
        return parent;
    }

    public void setPool( StoragePool pool )
    {
        this.pool = pool;
    }

    public StoragePool getPool()
    {
        return pool;
    }

    public void setLinks( LazyList<PoolNodeFileLink> links )
    {
        this.links = links;
    }

    public LazyList<PoolNodeFileLink> getLinks()
    {
        return (LazyList<PoolNodeFileLink>)links;
    }
    public List<PoolNodeFileLink> getLinks(GenericEntityManager em)
    {
        return getLinks().getList(em);
    }
    public void addLink(GenericEntityManager em, PoolNodeFileLink l)
    {
        getLinks().add(em, l);
    }

    public ArrayList<String> listXattributes()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getFilesystem_type()
    {
        return filesystem_type;
    }

    /**
     * @param filesystem_type the filesystem_type to set
     */
    public void setFilesystem_type( String filesystem_type )
    {
        this.filesystem_type = filesystem_type;
    }

//    public long getAttributesIdx()
//    {
//        return attributesIdx;
//    }
//
//
    public FileSystemElemAttributes getAttributes()
    {
        return attributes;
    }

    public void setAttributes( FileSystemElemAttributes attributes )
    {
        this.attributes = attributes;
 //       attributesIdx = attributes.getIdx();
    }

    public String getName()
    {
        if (attributes != null)
            return attributes.getName();

        throw new RuntimeException("Uninitalized attribute");

    }

    public LazyList<FileSystemElemAttributes> getHistory()
    {
        return (LazyList<FileSystemElemAttributes>)history;
    }
    public List<FileSystemElemAttributes> getHistory(GenericEntityManager em)
    {
        return getHistory().getList(em);
    }

    public void setHistory( LazyList<FileSystemElemAttributes> history )
    {
        this.history = history;
    }

    public LazyList<HashBlock> getHashBlocks()
    {
        return (LazyList<HashBlock>)hashBlocks;
    }
    public List<HashBlock> getHashBlocks(GenericEntityManager em)
    {
        return getHashBlocks().getList(em);
    }

    public void setHashBlocks( LazyList<HashBlock> hashBlocks )
    {
        this.hashBlocks = hashBlocks;
    }


    public LazyList<XANode> getXaNodes()
    {
        return (LazyList<XANode>)xaNodes;
    }
    public List<XANode> getXaNodes(GenericEntityManager em)
    {
        return getXaNodes().getList(em);
    }

    public void setXaNodes( LazyList<XANode> xaNodes )
    {
        this.xaNodes = xaNodes;
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

    public long getFlags()
    {
        return flags;
    }
    public boolean hasFlag( long flag )
    {
        return (flags & flag) != 0;
    }
    



}

