package DataAccess;

import java.io.*;
import java.util.logging.Logger;

public class Serializer {
    public void serialize(Object object, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + path + "\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Object deserialize(String path) {
        Object objects;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            objects = in.readObject();
            in.close();
            fileIn.close();
        } catch (EOFException e) {
            Logger.getAnonymousLogger().fine("File is finished");
            return null;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found\n");
            c.printStackTrace();
            return null;
        }
        return objects;
    }
}
