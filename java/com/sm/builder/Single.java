package com.sm.builder;

public class Single {
    private static Single instance = null;

    /**
     * 双重锁机制获取单例
     * @return Single
     */
    public static Single getInstance(){
        if (instance == null){
            synchronized (Single.class){
                if (instance == null){
                    instance = new Single();
                }
            }
        }
        return instance;
    }

    /**
     * 通过内部类获取单例
     * @return Single
     */
    public static Single getInstanceInner(){
        return Inner.instance;
    }
    private static class Inner{
        private static final Single instance = new Single();
    }

    /**
     * 通过枚举获取单例
     */
    private enum Singleton{
        INSTANCE;
        public Singleton getInstance(){
            return INSTANCE;
        }
    }
    public static void main(String[] args) {
        Single single = getInstance();
        single = getInstanceInner();
    }
}
