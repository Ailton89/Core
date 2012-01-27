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
package com.github.jgility.core.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.util.BeanCheckUtils;
import com.github.jgility.core.xml.AbstractXmlTeam;

/**
 * Repräsentiert ein Zusammenschluss aus mehreren {@link Person} als Team
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Team
    extends AbstractXmlTeam
{

    /**
     * Bezeichner der Eigenschaft {@link #name}
     */
    public static final String NAME_PROPERTY = "name";

    /**
     * Bezeichner der Eigenschaft {@link #members}
     */
    public static final String MEMBERS_PROPERTY = "members";

    @XmlElement
    private String name;

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IPerson> members;

    /**
     * Instanziiert ein Objekt der Klasse {@link Team} mit Standardwerten<br>
     * Leere Liste mit Mitgliedern und als Team-Namen (Default)
     */
    public Team()
    {
        this( "Default" );
    }

    /**
     * Instanziiert ein Objekt der Klasse Team auf Basis des Team-Namens
     * 
     * @param name {@link String} als Name des Teams
     */
    public Team( String name )
    {
        this.name = name;
        members = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#getName()
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#setName(java.lang.String)
     */
    @Override
    public void setName( String name )
    {
        BeanCheckUtils.checkStringNotBlank( name, "an empty-String is not allowed" );

        String formerName = this.name;
        this.name = name;
        changes.firePropertyChange( Team.NAME_PROPERTY, formerName, this.name );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#addMember(com.github.jgility.core.project.Person)
     */
    @Override
    public void addMember( IPerson person )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkObjectNotNull( person, "null-person is not allowed!" );

        List<IPerson> formerMembers = this.members;
        members.add( person );
        changes.firePropertyChange( Team.MEMBERS_PROPERTY, formerMembers, this.members );
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.ITeam#removeMember(com.github.jgility.core.project.IPerson)
     */
    @Override
    public boolean removeMember( IPerson person )
    {
        if ( ObjectUtils.notEqual( null, person ) )
        {
            boolean result;
            List<IPerson> formerMembers = this.members;
            result = members.remove( person );
            changes.firePropertyChange( Team.MEMBERS_PROPERTY, formerMembers, this.members );
            return result;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#getMembers()
     */
    @Override
    public List<IPerson> getMembers()
    {
        return Collections.unmodifiableList( new ArrayList<>( members ) );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#clearMembers()
     */
    @Override
    public void clearMembers()
    {
        List<IPerson> formerMembers = this.members;
        members.clear();
        changes.firePropertyChange( Team.MEMBERS_PROPERTY, formerMembers, this.members );
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( name );
        builder.append( members );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Team )
        {
            Team team = (Team) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( name, team.name );
            builder.append( members, team.members );
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "name", name );
        builder.append( "members", members );
        return builder.build();
    }
}
