package gadget.context;

import gadget.*;

import java.util.HashMap;
import java.util.Map;

public class GContext {
    private Map<String, ObjectPayload> mapping = new HashMap<>();

    public GContext() {
        this.init();
    }

    public void init() {
        mapping.put("cck1", new CCK1());
        mapping.put("cck2", new CCK2());
        mapping.put("cck3", new CCK3());
        mapping.put("cck4", new CCK4());
    }

    public ObjectPayload getGadget(String name) throws Exception {
        ObjectPayload op = mapping.get(name);
        if (op == null) {
            this.printAll();
            throw new Exception(String.format("gadget %s is not available", name));
        }
        return op;
    }

    public void printAll() {
        System.out.println("Available gadget:");
        this.mapping.keySet().forEach(System.out::println);
    }
}
