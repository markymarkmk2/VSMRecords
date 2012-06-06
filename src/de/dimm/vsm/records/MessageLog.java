
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.Index;


/**
 *
 * @author mw
 *  */
@Entity
public class MessageLog implements Serializable
{
    static final long serialVersionUID = 1L;

    public static final int ML_INFO = 1;
    public static final int ML_WARN = 2;
    public static final int ML_ERROR = 3;
    public static final int ML_DEBUG = 4;

    public static final int UID_SYSTEM = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creation;

    @Index
    int errLevel;

    @Index
    long userId;

    @Index
    String moduleName;

    @Index
    String messageId;


    String additionText;

    @Index
    String exceptionName;
    String exceptionText;
    String exceptionStack;

    // DB CREATION
    public MessageLog()
    {
    }

    // MANUAL CREATION
    public MessageLog( int errLevel, long userId, String moduleName, String messageId )
    {
        this.creation = new Date();
        this.errLevel = errLevel;
        this.userId = userId;
        this.moduleName = moduleName;
        this.messageId = db255( messageId );
    }
    // MANUAL CREATION
    public MessageLog( int errLevel, long userId, String moduleName, String messageId, String additionText )
    {
        this(errLevel, userId, moduleName, messageId);
        this.additionText = db255( additionText );
    }
    // MANUAL CREATION
    public MessageLog( int errLevel, long userId, String moduleName, String messageId, Throwable t )
    {
        this(errLevel, userId, moduleName, messageId);
        exceptionName = t.getClass().getSimpleName();
        exceptionText = db255( t.getMessage() );
        StringWriter wr = new StringWriter();
        PrintWriter pw  = new PrintWriter(wr);
        t.printStackTrace(pw);
        pw.close();
        exceptionStack = db255( wr.toString() );
        try
        {
            wr.close();
        }
        catch (IOException iOException)
        {
            // CANNOT HAPPEN
        }
    }
    // MANUAL CREATION
    public MessageLog( int errLevel, long userId, String moduleName, String messageId, String additionText, Throwable t )
    {
        this(errLevel, userId, moduleName, messageId, t);
        this.additionText = db255( additionText );
    }
    final String db255( String t )
    {
        if (t == null)
            return t;

        if (t.length() > 255)
        {
            t = t.substring(0, 255);
        }
        t = t.replace('\'', '_');
        t = t.replace('\"', '_');

        return t;
    }


    @Override
    public String toString()
    {
        return getDateString( creation ) + " " + getErrLevelName(errLevel) + " " + moduleName + ": " + messageId;
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

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss.SSS");
    public static String getDateString( Date d )
    {
        return sdf.format(d);
    }
    
    public static String getErrLevelName( int level )
    {
        switch (level )
        {
            case ML_INFO: return "Info";
            case ML_WARN: return "Warning";
            case ML_ERROR: return "Error";
            case ML_DEBUG: return "Debug";
        }
        return "?";
    }

    public Date getCreation()
    {
        return creation;
    }

    public int getErrLevel()
    {
        return errLevel;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public String getExceptionName()
    {
        return exceptionName;
    }

    public String getExceptionStack()
    {
        return exceptionStack;
    }

    public String getExceptionText()
    {
        return exceptionText;
    }

    public String getAdditionText()
    {
        return additionText;
    }
    
}

