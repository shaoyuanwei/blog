package com.syw.blog.ptool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {

    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {

        byte[] bytes = null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {

            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;

    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object unSerialize(byte[] bytes) {

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {

            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);

            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
