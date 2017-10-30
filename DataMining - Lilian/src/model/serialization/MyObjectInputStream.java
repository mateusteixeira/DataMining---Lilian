package model.serialization;

import java.net.URLClassLoader;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;

public class MyObjectInputStream extends java.io.ObjectInputStream {

    URLClassLoader myLoader = null;

    public MyObjectInputStream(URLClassLoader newLoader, InputStream theStream) throws IOException, StreamCorruptedException {
        super(theStream);
        myLoader = newLoader;
    }

    @Override
    protected Class resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        //System.out.println("Hey, I'm in my resolveClass");
        Class theClass = null;
        try {
            theClass = Class.forName(osc.getName());
        } catch(ClassNotFoundException e){
            theClass = Class.forName(osc.getName(), true, myLoader);
        } catch (Exception e) {
            System.err.println("An Error in my ResolveClass:");
            e.printStackTrace();
        }
        return  theClass;
    }
}
