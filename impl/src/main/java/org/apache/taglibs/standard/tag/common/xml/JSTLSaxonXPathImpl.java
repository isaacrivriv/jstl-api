package org.apache.taglibs.standard.tag.common.xml;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.Configuration;
import net.sf.saxon.expr.parser.ContextItemStaticInfo;
import net.sf.saxon.expr.parser.ExpressionTool;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.instruct.Executable;
import net.sf.saxon.expr.instruct.SlotManager;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.type.Type;
import net.sf.saxon.xpath.XPathEvaluator;
import net.sf.saxon.xpath.XPathExpressionImpl;

public class JSTLSaxonXPathImpl extends XPathEvaluator {

    public JSTLSaxonXPathImpl(Configuration config) {
        super(config);
        System.out.println("Called new custom jstl xpath");
    }

    public XPathExpression compile(String expr) throws XPathExpressionException {
        if (expr == null)
          throw new NullPointerException("expr");  
        try {
                
                   
          Executable exec = new Executable(getConfiguration());
          exec.setSchemaAware(getStaticContext().getPackageData().isSchemaAware());
          Expression exp = ExpressionTool.make(expr, (StaticContext)getStaticContext(), 0, -1, null);
          ExpressionVisitor visitor = ExpressionVisitor.make((StaticContext)getStaticContext());
          ContextItemStaticInfo contextItemType = getConfiguration().makeContextItemStaticInfo(Type.ITEM_TYPE, true);
          exp = exp.typeCheck(visitor, contextItemType).optimize(visitor, contextItemType);
          SlotManager map = getStaticContext().getConfiguration().makeSlotManager();
          ExpressionTool.allocateSlots(exp, 0, map);
          JSTLSaxonXPathExpressionImpl xpe = new JSTLSaxonXPathExpressionImpl(exp, exec);
          xpe.setStackFrameMap(map);
          return xpe;
        } catch (XPathException e) {
          throw new XPathExpressionException(e);
        } 
      }

    public Object evaluate(String expr, Object node, QName qName) throws XPathExpressionException {
        System.out.println("Called new custom jstl xpath evaluate");
        JSTLSaxonXPathExpressionImpl exp = (JSTLSaxonXPathExpressionImpl)compile(expr);
        return exp.evaluate(node, qName);
    }
    
}
