
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dimm.vsm.records;

import de.dimm.vsm.Utilities.OldRecSizeStr;
import de.dimm.vsm.fsengine.GenericEntityManager;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author mw
 * Stellt einen realen Speicherablageort eines FilySystemElemNodes dar
 * Subklassen können KlonesStorageNode, FS_StorageNode, FTP_StorageNode, S3_StorageNode etc sein
 */
@Entity
public class Retention implements Serializable
{

    static final long serialVersionUID = 1L;
    public static final String ARG_NAME = "name";
    //public static final String ARG_PATH = "path";
    public static final String ARG_SIZE = "size";
    public static final String ARG_CDATE = "cdate";
    public static final String ARG_MDATE = "mdate";
    public static final String ARG_ADATE = "adate";
    public static final String ARG_UID = "uid";
    public static final String ARG_GID = "gid";
    public static final String ARG_TS = "ts";
    public static final String OP_EQUAL = "eq";
    public static final String OP_BEGINS = "begins";
    public static final String OP_ENDS = "ends";
    public static final String OP_CONTAINS = "contains";
    public static final String OP_GT = "gt";
    public static final String OP_GE = "ge";
    public static final String OP_LT = "lt";
    public static final String OP_LE = "le";
    public static final String OP_BETWEEN = "between";  // ARGS ARE SEPERATED BY ; IN FIELD argValue
    public static final char BETWEEN_SEPERATOR = ';';
    public static final String AC_DELETE = "del";
    public static final String AC_MOVE = "mov";  // VALUE IS TARGET STORAGEPOOL
    public static final String AC_SCRIPT = "script";

    public static final String MD_BACKUP = "ba";
    public static final String MD_ARCHIVE = "ar";



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;
    protected String name;
    private boolean disabled;
    private boolean testmode;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    protected Date creation;
    @ManyToOne
    private StoragePool pool;
    // DIR-STRUKTUR
    @OneToMany(mappedBy = "parent")
    private List<Retention> children;
    // DIR STRUKTUR
    @ManyToOne
    private Retention parent;
    private boolean orWithPrevious;
    private boolean neg;
    private String argType;
    private String argOp;
    private String argValue;
    private String followAction;
    private String followActionParams;
    private String mode;
    
    private boolean clearFreeBlocks;

    
    @OneToMany(mappedBy = "retention",  fetch=FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})    
    private LazyList<RetentionWindow> retentionWindows;

    @OneToMany(mappedBy = "retention",  fetch=FetchType.EAGER, orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})    
    private LazyList<RetentionJob> retentionJobs;

    @Override
    public String toString()
    {
        return mode + " " + argType + " " + getSqlOpString(argOp) + " " + getNiceValue() + " " + followAction;
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

    public String getMode()
    {
        return mode;
    }

    public void setMode( String mode )
    {
        this.mode = mode;
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

    public StoragePool getPool()
    {
        return pool;
    }

    public void setPool( StoragePool storagePool )
    {
        this.pool = storagePool;
    }

    /**
     * @return the children
     */
    public LazyList<Retention> getChildren()
    {
        return (LazyList<Retention>)children;
    }
    public List<Retention> getChildren(GenericEntityManager em)
    {
        if (children == null)
            return null;
        
        return getChildren().getList(em);
    }


    /**
     * @param children the children to set
     */
    public void setChildren( LazyList<Retention> children )
    {
        this.children = children;
    }

    /**
     * @return the parent
     */
    public Retention getParent()
    {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent( Retention parent )
    {
        this.parent = parent;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * @return the orWithPrevious
     */
    public boolean isOrWithPrevious()
    {
        return orWithPrevious;
    }

    /**
     * @param orWithPrevious the orWithPrevious to set
     */
    public void setOrWithPrevious( boolean orWithPrevious )
    {
        this.orWithPrevious = orWithPrevious;
    }

    /**
     * @return the neg
     */
    public boolean isNeg()
    {
        return neg;
    }

    /**
     * @param neg the neg to set
     */
    public void setNeg( boolean neg )
    {
        this.neg = neg;
    }

    /**
     * @return the argType
     */
    public String getArgType()
    {
        return argType;
    }

    /**
     * @param argType the argType to set
     */
    public void setArgType( String argType )
    {
        this.argType = argType;
    }

    /**
     * @return the argOp
     */
    public String getArgOp()
    {
        return argOp;
    }

    /**
     * @param argOp the argOp to set
     */
    public void setArgOp( String argOp )
    {
        this.argOp = argOp;
    }

    /**
     * @return the argValue
     */
    public String getArgValue()
    {
        return argValue;
    }

    /**
     * @param argValue the argValue to set
     */
    public void setArgValue( String argValue )
    {
        this.argValue = argValue;
    }

    /**
     * @return the followAction
     */
    public String getFollowAction()
    {
        return followAction;
    }

    /**
     * @param followAction the followAction to set
     */
    public void setFollowAction( String followAction )
    {
        this.followAction = followAction;
    }

    /**
     * @return the followActionParams
     */
    public String getFollowActionParams()
    {
        return followActionParams;
    }

    /**
     * @param followActionParams the followActionParams to set
     */
    public void setFollowActionParams( String followActionParams )
    {
        this.followActionParams = followActionParams;
    }

    // THIS IS NOT DONE OFTEN, HO HASHING NEEDED
    public String getSqlFieldforArgtype()
    {
        if (getArgType().equals(Retention.ARG_TS))
        {
            return "a.ts";
        }
        if (getArgType().equals(Retention.ARG_MDATE))
        {
            return "a.modificationDateMs";
        }
        if (getArgType().equals(Retention.ARG_CDATE))
        {
            return "a.creationDateMs";
        }
        if (getArgType().equals(Retention.ARG_ADATE))
        {
            return "a.accessDateMs";
        }
        if (getArgType().equals(Retention.ARG_NAME))
        {
            return "a.name";
        }
        if (getArgType().equals(Retention.ARG_UID))
        {
            return "a.uid";
        }
        if (getArgType().equals(Retention.ARG_GID))
        {
            return "a.gid";
        }
        if (getArgType().equals(Retention.ARG_SIZE))
        {
            return "a.fsize";
        }

        return null;
    }
    // THIS IS NOT DONE OFTEN, HO HASHING NEEDED

    public static String getSqlOpString( String op )
    {
        if (op == null)
        {
            return "";
        }

        if (op.equals(Retention.OP_EQUAL))
        {
            return "=";
        }
        if (op.equals(Retention.OP_GT))
        {
            return ">";
        }
        if (op.equals(Retention.OP_GE))
        {
            return ">=";
        }
        if (op.equals(Retention.OP_LT))
        {
            return "<";
        }
        if (op.equals(Retention.OP_LE))
        {
            return "<=";
        }
        if (op.equals(Retention.OP_BETWEEN))
        {
            return "between";
        }
        if (op.equals(Retention.OP_BEGINS))
        {
            return "like";
        }
        if (op.equals(Retention.OP_ENDS))
        {
            return "like";
        }
        if (op.equals(Retention.OP_CONTAINS))
        {
            return "like";
        }

        return null;
    }

    public boolean isTestmode()
    {
        return testmode;
    }

    public static String[] getAllowedOps( String field )
    {
        if (field.equals(ARG_TS) || isDateField(field) || field.equals(ARG_SIZE))
        {
            return new String[]
                    {
                        OP_LT, OP_GT/* TODO:, OP_BETWEEN */

                    };
        }
        if (field.equals(ARG_NAME))
        {
            return new String[]
                    {
                        OP_EQUAL, OP_BEGINS, OP_CONTAINS, OP_ENDS
                    };
        }
        if (field.equals(ARG_UID) || field.equals(ARG_GID))
        {
            return new String[]
                    {
                        OP_EQUAL
                    };
        }

        return new String[]
                {
                    OP_LT, OP_GT/* TODO:, OP_BETWEEN */ };
    }

    public static boolean isDateField( String field )
    {
        String[] df =
        {
            ARG_MDATE, ARG_CDATE, ARG_ADATE
        };
        for (int i = 0; i < df.length; i++)
        {
            String string = df[i];
            if (field.equals(string))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isRelTSField( String field )
    {
        String[] df =
        {
            ARG_TS
        };
        for (int i = 0; i < df.length; i++)
        {
            String string = df[i];
            if (field.equals(string))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isSizeField( String field )
    {
        String[] df =
        {
            ARG_SIZE
        };
        for (int i = 0; i < df.length; i++)
        {
            String string = df[i];
            if (field.equals(string))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isNameField( String field )
    {
        String[] df =
        {
            ARG_NAME
        };
        for (int i = 0; i < df.length; i++)
        {
            String string = df[i];
            if (field.equals(string))
            {
                return true;
            }
        }
        return false;
    }
    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm");

    public static String getNormRelSecondsDim( long s )
    {
        String dim = "s";
        if (s > 180)
        {
            s /= 60;
            dim = "m";

            if (s > 120)
            {
                s /= 60;
                dim = "h";

                if (s > 48)
                {
                    s /= 24;
                    dim = "d";

                    if (s > 14)
                    {
                        s /= 7;
                        dim = "w";

                        if (s > 104)
                        {
                            s /= 52;
                            dim = "y";
                        }
                    }
                }
            }
        }

        return dim;
    }

    public static long getNormRelSeconds( long s )
    {
        if (s > 180)
        {
            s /= 60;

            if (s > 120)
            {
                s /= 60;

                if (s > 48)
                {
                    s /= 24;

                    if (s > 14)
                    {
                        s /= 7;

                        if (s > 104)
                        {
                            s /= 52;
                        }
                    }
                }
            }
        }

        return s;
    }

    public static long getSecondsfromNiceText( String text )
    {
        long s = -1;

        String[] arr = text.split(" ");
        if (arr.length != 2)
        {
            return s;
        }

        s = Long.parseLong(arr[0]);

        String dim = arr[1];
        if (dim.equals("s"))
        {
            return s;
        }

        s *= 60;
        if (dim.equals("m"))
        {
            return s;
        }

        s *= 60;
        if (dim.equals("h"))
        {
            return s;
        }

        s *= 24;
        if (dim.equals("d"))
        {
            return s;
        }

        s *= 7;
        if (dim.equals("w"))
        {
            return s;
        }

        s *= 52;
        if (dim.equals("y"))
        {
            return s;
        }

        return -1;
    }

    public String getNiceValue()
    {
        try
        {
            if (isDateField(argType))
            {
                return sdf.format(new Date(Long.parseLong(argValue)));
            }
            if (isRelTSField(argType))
            {
                long s = Long.parseLong(argValue) / 1000;
                return getNormRelSeconds(s) + " " + getNormRelSecondsDim(s);
            }
            if (isSizeField(argType))
            {
                long s = Long.parseLong(argValue);
                return OldRecSizeStr.format(s);
            }
        }
        catch (Exception exception)
        {
        }
        return argType;
    }

    public boolean isClearFreeBlocks() {
        return clearFreeBlocks;
    }

    public void setClearFreeBlocks( boolean clearFreeBlocks ) {
        this.clearFreeBlocks = clearFreeBlocks;
    }

    public boolean isInStartWindow( long now ) {
        if (retentionWindows == null || retentionWindows.isEmpty())
            return true;
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(now);
        long msDay = cal.get(Calendar.HOUR) * 3600 * 1000;
        msDay += cal.get(Calendar.MINUTE) * 60 * 1000;
        msDay += cal.get(Calendar.SECOND) * 1000;
        
        boolean ret = true;
        for (RetentionWindow rwin: retentionWindows) {
            if (rwin.getDisabled())
                continue;
            switch(rwin.getCycleString()) {
                case RetentionWindow.YEARLY: {
                    int weekNr = cal.get(Calendar.WEEK_OF_YEAR);
                    
                    if (weekNr < rwin.getStartWeekNumber() || 
                            weekNr >= rwin.getEndWeekNumber() )
                        ret = false;                    
                } // fallthrough for Week test
                case RetentionWindow.WEEKLY: {
                    
                    int dayNr = cal.get(Calendar.DAY_OF_WEEK);
                    if (dayNr < rwin.getStartDayNumber() || 
                            dayNr >= rwin.getEndDayNumber() )
                        ret = false;                    
                }// fallthrough for Day test                
                case RetentionWindow.DAILY: {
                    if (msDay < rwin.getStartOffsetStartMs()  || 
                            msDay >= rwin.getEndOffsetStartMs()) {
                        ret = false;
                    }
                    break;
                }
            }
        }
        return ret;
    }

    public LazyList<RetentionWindow> getRetentionWindows() {
        return retentionWindows;
    }

    public void setRetentionWindows( LazyList<RetentionWindow> retentionWindows ) {
        this.retentionWindows = retentionWindows;
    }

    public LazyList<RetentionJob> getRetentionJobs() {
        return retentionJobs;
    }

    public void setRetentionJobs(LazyList<RetentionJob> retentionJobs ) {
        this.retentionJobs = retentionJobs;
    }
    
}
