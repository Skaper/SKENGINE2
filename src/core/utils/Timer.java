package core.utils;

public class Timer {
    private static final double DIVIDER_MICRO = 1_000;
    private static final double DIVIDER_MILLIS = 1_000_000;
    private static final double DIVIDER_SECONDS = 1_000_000_000;

    public static double getNano(){ return  (double)System.nanoTime();}
    public static double getMillis(){
        return (double)System.nanoTime() / DIVIDER_MILLIS;
    }
    public static double getMicrosecond(){
        return (double)System.nanoTime() / DIVIDER_MICRO;
    }
    public static double getSeconds(){
        return (double)System.nanoTime() / DIVIDER_SECONDS;
    }
}
