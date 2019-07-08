/*
* Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Distribution License v. 1.0, which is available at
* http://www.eclipse.org/org/documents/edl-v10.php.
*
* SPDX-License-Identifier: BSD-3-Clause
*/

package org.apache.taglibs.standard.tag.el.core;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.common.core.OutSupport;

/**
 * <p>A handler for &lt;out&gt;, which redirects the browser to a
 * new URL.
 *
 * @author Shawn Bayern
 */

public class OutTag extends OutSupport {

    //*********************************************************************
    // 'Private' state (implementation details)

    private String value_;			// stores EL-based property
    private String default_;			// stores EL-based property
    private String escapeXml_;			// stores EL-based property


    //*********************************************************************
    // Constructor

    public OutTag() {
        super();
        init();
    }


    //*********************************************************************
    // Tag logic

    // evaluates expression and chains to parent
    public int doStartTag() throws JspException {

        // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();

	// chain to the parent implementation
	return super.doStartTag();
    }


    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }


    //*********************************************************************
    // Accessor methods

    public void setValue(String value_) {
        this.value_ = value_;
    }

    public void setDefault(String default_) {
        this.default_ = default_;
    }

    public void setEscapeXml(String escapeXml_) {
        this.escapeXml_ = escapeXml_;
    }


    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)
    private void init() {
        // null implies "no expression"
	value_ = default_ = escapeXml_ = null;
    }

    /* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {
	try {
	    value = ExpressionUtil.evalNotNull(
	        "out", "value", value_, Object.class, this, pageContext);
	} catch (NullAttributeException ex) {
	    // explicitly allow 'null' for value
	    value = null;
	}
	try { 
	    def = (String) ExpressionUtil.evalNotNull(
	        "out", "default", default_, String.class, this, pageContext);
	} catch (NullAttributeException ex) {
	    // explicitly allow 'null' for def
	    def = null;
	}
	escapeXml = true;
	Boolean escape = ((Boolean) ExpressionUtil.evalNotNull(
	    "out", "escapeXml", escapeXml_, Boolean.class, this, pageContext));
	if (escape != null)
	    escapeXml = escape.booleanValue();
    }
}
