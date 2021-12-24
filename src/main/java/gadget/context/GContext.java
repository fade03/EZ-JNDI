package gadget.context;

import gadget.CCK1;
import gadget.ObjectPayload;

import java.util.HashMap;
import java.util.Map;

public class GContext {
    private Map<String, ObjectPayload> mapping = new HashMap<>();

    public GContext() {
        mapping.put("cck1", new CCK1());
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
