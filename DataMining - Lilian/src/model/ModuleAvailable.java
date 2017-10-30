package model;

import java.lang.reflect.Constructor;

public class ModuleAvailable<T> {

   
    private String name;

    private Constructor<T> constructor;

    public ModuleAvailable() {
    }

    public Constructor<T> getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    public String getName() {
        return name;
    }

      public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
