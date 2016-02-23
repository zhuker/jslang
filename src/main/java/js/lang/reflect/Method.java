package js.lang.reflect;

import js.util.List;

public class Method {

    public String getName() {
        throw new RuntimeException("TODO Method.getName");
    }

    public Object invoke(Object obj, Object... args) {
        throw new RuntimeException("TODO Method.invoke");
    }

    public int getModifiers() {
        throw new RuntimeException("TODO Method.getModifiers");
    }

    public Class<?> getReturnType() {
        throw new RuntimeException("TODO Method.getReturnType");
    }
    
    public Class<?>[] getParameterTypes() {
        return null;
    }

}
