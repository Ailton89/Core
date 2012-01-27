/*
 * 
 * Copyright (c) 2011 by Jgility Development Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Karsten Schulz
 *     Christoph Viebig
 *
 */
package com.github.jgility.core.requirement;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.util.BeanCheckUtils;
import com.github.jgility.core.util.CalendarUtils;
import com.github.jgility.core.xml.AbstractXmlProductStroy;

/**
 * Konkrete Klasse für grobe Erfassung einer Anforderung. Implementiert das Marker-Interface
 * {@link IProductRequirement}
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlType( propOrder = { "id", "title", "description", "createDate", "estimated", "priority",
    "requester", "requirementKind" } )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProductStory
    extends AbstractXmlProductStroy
{

    /**
     * Bezeichner der Eigenschaft {@link #id}
     */
    public static final String ID_PROPERTY = "id";

    /**
     * Bezeichner der Eigenschaft {@link #title}
     */
    public static final String TITLE_PROPERTY = "title";

    /**
     * Bezeichner der Eigenschaft {@link #description}
     */
    public static final String DESCRIPTION_PROPERTY = "description";

    /**
     * Bezeichner der Eigenschaft {@link #priority}
     */
    public static final String PRIORITY_PROPERTY = "priority";

    /**
     * Bezeichner der Eigenschaft {@link #requester}
     */
    public static final String REQUESTER_PROPERTY = "requester";

    /**
     * Bezeichner der Eigenschaft {@link #requirementKind}
     */
    public static final String REQUIREMENT_KIND_PROPERTY = "requirementKind";

    /**
     * Bezeichner der Eigenschaft {@link #estimated}
     */
    public static final String ESTIMATED_PROPERTY = "estimated";

    @XmlElement
    private int id;

    @XmlElement
    private String title;

    @XmlElement
    private String description;

    @XmlElement
    private final Calendar createDate;

    @XmlElement
    private float estimated;

    @XmlElement
    private Priority priority;

    @XmlElement
    private String requester;

    @XmlElement
    private RequirementKind requirementKind;

    /**
     * Instanziiert ein Objekt der Klasse {@link ProductStory} und initialisiert "Standard"-Werte
     * als Start-werte.<br>
     * <code>id=0</code><br>
     * <code>title=Default</code><br>
     * <code>description=Default</code><br>
     * <code>estimated=0</code> <br>
     * <code>priority=MINOR</code><br>
     * <code>requester=Nobody</code><br>
     * <code>requirementKind=USER_STORY</code>
     */
    public ProductStory()
    {
        this( 0, "Default", "Default", 0, Priority.MINOR, "Nobody", RequirementKind.USER_STORY );
    }

    /**
     * Instanziiert auf Basis der Parameter ein Objekt der Klasse {@link ProductStory}
     * 
     * @param id ein-eindeutige Anforderungsnummer
     * @param title Titel der Anforderung
     * @param description Beschreibung der Anforderung
     * @param estimated Geschätzte Zeit für Problemlösung der Anforderung
     * @param priority Priorität der Anforderung
     * @param requester Anforderungsstelle der Anforderung
     * @param requirementKind Anforderungsart
     * @throws IllegalArgumentException wenn einer der Parameter keinen gültigen Bereich unterliegt
     */
    public ProductStory( int id, String title, String description, float estimated,
                         Priority priority, String requester, RequirementKind requirementKind )
        throws IllegalArgumentException
    {
        setID( id );
        setDescription( description );
        setEstimated( estimated );
        setPriority( priority );
        setRequester( requester );
        setRequirementKind( requirementKind );
        setTitle( title );
        createDate = Calendar.getInstance();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getID()
     */
    @Override
    public int getID()
    {
        return id;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setID(int id)
     */
    @Override
    public void setID( int id )
        throws IllegalArgumentException
    {
        if ( 0 == this.id )
        {
            if ( 0 <= id )
            {
                int formerId = this.id;
                this.id = id;
                changes.firePropertyChange( ProductStory.ID_PROPERTY, formerId, this.id );
            }
            else
            {
                throw new IllegalArgumentException( "neative id is not allowed" );
            }
        }
        else
        {
            throw new IllegalArgumentException( "change of initialize requirements-id is "
                + "not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getTitle()
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setTitle(java.lang.String)
     */
    @Override
    public void setTitle( String title )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkStringNotBlank( title, "title has to be not blank!" );

        String formerTitle = this.title;
        this.title = title;
        changes.firePropertyChange( ProductStory.TITLE_PROPERTY, formerTitle, title );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setDescription(java.lang.String)
     */
    @Override
    public void setDescription( String description )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkObjectNotNull( description, "description has to be not null!" );

        String formerDescription = this.description;
        this.description = description;
        changes.firePropertyChange( ProductStory.DESCRIPTION_PROPERTY, formerDescription,
                                    this.description );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getCreateDate()
     */
    @Override
    public Calendar getCreateDate()
    {
        return createDate;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getPriority()
     */
    @Override
    public Priority getPriority()
    {
        return priority;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IRequirement#setPriority(com.github.jgility.core.requirement
     * .Priority)
     */
    @Override
    public void setPriority( Priority priority )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkObjectNotNull( priority, "priority has to be not null!" );

        Priority formerPriority = this.priority;
        this.priority = priority;
        changes.firePropertyChange( ProductStory.PRIORITY_PROPERTY, formerPriority, this.priority );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getRequester()
     */
    @Override
    public String getRequester()
    {
        return requester;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setRequester(java.lang.String)
     */
    @Override
    public void setRequester( String requester )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkStringNotBlank( requester, "requester has to be not blank!" );

        String formerRequester = this.requester;
        this.requester = requester;
        changes.firePropertyChange( ProductStory.REQUESTER_PROPERTY, formerRequester,
                                    this.requester );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getRequirementKind()
     */
    @Override
    public RequirementKind getRequirementKind()
    {
        return requirementKind;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IRequirement#setRequirementKind(com.github.jgility.core
     * .requirement.RequirementKind)
     */
    @Override
    public void setRequirementKind( RequirementKind requirementKind )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkObjectNotNull( requirementKind,
                                           "kind of requirement has to be not null!" );

        RequirementKind formerRequirementKind = this.requirementKind;
        this.requirementKind = requirementKind;
        changes.firePropertyChange( ProductStory.REQUIREMENT_KIND_PROPERTY, formerRequirementKind,
                                    this.requirementKind );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#getEstimated()
     */
    @Override
    public float getEstimated()
    {
        return estimated;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#getEffective()
     */
    @Override
    public float getEffective()
    {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#setEstimated(float estimated)
     */
    @Override
    public void setEstimated( float estimated )
        throws IllegalArgumentException
    {
        if ( 0.0f <= estimated )
        {
            float formerEstimated = this.estimated;
            this.estimated = estimated;
            changes.firePropertyChange( ProductStory.ESTIMATED_PROPERTY,
                                        Float.valueOf( formerEstimated ),
                                        Float.valueOf( this.estimated ) );
        }
        else
        {
            throw new IllegalArgumentException( "negativ estimate is not allowed!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "id", id );
        builder.append( "title", title );
        builder.append( "description", description );
        builder.append( "createDate", CalendarUtils.calendarOutput( createDate ) );
        builder.append( "estimated", estimated );
        builder.append( "priority", priority );
        builder.append( "requester", requester );
        builder.append( "requirementKind", requirementKind );
        return builder.build();
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( id );
        builder.append( title );
        builder.append( description );
        builder.append( createDate );
        builder.append( estimated );
        builder.append( priority );
        builder.append( requester );
        builder.append( requirementKind );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof ProductStory )
        {
            ProductStory story = (ProductStory) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( id, story.id );
            builder.append( title, story.title );
            builder.append( description, story.description );
            builder.append( createDate, story.createDate );
            builder.append( estimated, story.estimated );
            builder.append( priority, story.priority );
            builder.append( requester, story.requester );
            builder.append( requirementKind, story.requirementKind );
            return builder.isEquals();
        }
        return false;
    }
}
