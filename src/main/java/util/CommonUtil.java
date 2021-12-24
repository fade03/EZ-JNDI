package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class CommonUtil {

    public static byte[] serialize(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public static void setFieldValue(Object obj, String field, Object value) {
        try {
            Class<?> clazz = Class.forName(obj.getClass().getName());
            Field field1 = clazz.getDeclaredField(field);
            field1.setAccessible(true);
            field1.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
