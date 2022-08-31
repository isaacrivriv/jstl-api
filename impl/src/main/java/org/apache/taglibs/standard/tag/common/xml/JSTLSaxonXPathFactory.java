package org.apache.taglibs.standard.tag.common.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

import net.sf.saxon.xpath.XPathEvaluator;
import net.sf.saxon.xpath.XPathFactoryImpl;

public class JSTLSaxonXPathFactory extends XPathFactoryImpl{

    private XPathVariableResolver variableResolver;
  
    private XPathFunctionResolver functionResolver;

    @Override
    public void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver) {
        this.variableResolver = xPathVariableResolver;
    }
      
    @Override
    public void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver) {
        this.functionResolver = xPathFunctionResolver;
    }
    
    @Override
    public XPath newXPath() {
        System.out.println("New Xpath Factory called");
        XPathEvaluator xpath = new JSTLSaxonXPathImpl(getConfiguration());
        xpath.setXPathFunctionResolver(functionResolver);
        xpath.setXPathVariableResolver(variableResolver);
        return xpath;
    }
    

}
