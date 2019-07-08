/*
* Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Distribution License v. 1.0, which is available at
* http://www.eclipse.org/org/documents/edl-v10.php.
*
* SPDX-License-Identifier: BSD-3-Clause
*/

package org.apache.taglibs.standard.tag.rt.fmt;

import java.util.Locale;

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.tag.common.fmt.ParseNumberSupport;
import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;

/**
 * <p>A handler for &lt;parseNumber&gt; that supports rtexprvalue-based
 * attributes.</p>
 *
 * @author Jan Luehe
 */

public class ParseNumberTag extends ParseNumberSupport {

    //*********************************************************************
    // Accessor methods

    // 'value' attribute
    public void setValue(String value) throws JspTagException {
        this.value = value;
	this.valueSpecified = true;
    }

    // 'type' attribute
    public void setType(String type) throws JspTagException {
        this.type = type;
    }

    // 'pattern' attribute
    public void setPattern(String pattern) throws JspTagException {
        this.pattern = pattern;
    }

    // 'parseLocale' attribute
    public void setParseLocale(Object loc) throws JspTagException {
	if (loc != null) {
	    if (loc instanceof Locale) {
		this.parseLocale = (Locale) loc;
	    } else {
		if (!"".equals((String) loc)) {
		    this.parseLocale = SetLocaleSupport.parseLocale((String)
								    loc);
		}
	    }
	}
    }

    // 'integerOnly' attribute
    public void setIntegerOnly(boolean isIntegerOnly) throws JspTagException {
        this.isIntegerOnly = isIntegerOnly;
	this.integerOnlySpecified = true;
    }
}
