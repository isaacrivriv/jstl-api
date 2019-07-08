/*
* Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Distribution License v. 1.0, which is available at
* http://www.eclipse.org/org/documents/edl-v10.php.
*
* SPDX-License-Identifier: BSD-3-Clause
*/

package org.apache.taglibs.standard.lang.jstl;

/**
 *
 * <p>An expression representing an integer literal value.  The value
 * is stored internally as a long.
 * 
 * @author Nathan Abramson - Art Technology Group
 * @version $Change: 181177 $$DateTime: 2001/06/26 08:45:09 $$Author: jluehe $
 **/

public class IntegerLiteral
  extends Literal
{
  //-------------------------------------
  /**
   *
   * Constructor
   **/
  public IntegerLiteral (String pToken)
  {
    super (getValueFromToken (pToken));
  }

  //-------------------------------------
  /**
   *
   * Parses the given token into the literal value
   **/
  static Object getValueFromToken (String pToken)
  {
    return Long.valueOf(pToken);
  }

  //-------------------------------------
  // Expression methods
  //-------------------------------------
  /**
   *
   * Returns the expression in the expression language syntax
   **/
  public String getExpressionString ()
  {
    return getValue ().toString ();
  }

  //-------------------------------------
}
