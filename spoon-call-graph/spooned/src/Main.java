package src;


public class Main {
    src.p3.C c = new src.p3.C();

    public static void main(java.lang.String[] args) {
        try {
            @java.lang.SuppressWarnings("unused")
            java.util.Vector<?> v = null;
            src.Main.m1();
            this.c.m();
        } catch (java.lang.Exception ignored) {
        }
    }

    public static void m1() throws java.lang.Exception {
        this.c.m();
        src.Main.m2();
    }

    public static void m2() throws java.lang.Exception {
        throw new java.lang.RuntimeException();
    }

    public void m(src.p3.C c) throws java.lang.Exception {
        c.toString();
    }

    public void m5(java.lang.Object c) throws java.lang.Exception {
        c.toString();
    }

    public void m6(java.lang.Object d) throws java.lang.Exception {
        d.toString();
    }
}

