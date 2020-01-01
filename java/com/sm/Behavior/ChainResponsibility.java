package com.sm.Behavior;

/**
 * 避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止
 *
 * 个人觉得和过滤器差不多
 */
public class ChainResponsibility {
    abstract static class AbstractLogger {
        public static int INFO = 1;
        public static int DEBUG = 2;
        public static int ERROR = 3;

        protected int level;

        //责任链中的下一个元素
        protected AbstractLogger nextLogger;

        public void setNextLogger(AbstractLogger nextLogger){
            this.nextLogger = nextLogger;
        }

        public void logMessage(int level, String message){
            if(this.level <= level){
                write(message);
            }
            if(nextLogger !=null){
                nextLogger.logMessage(level, message);
            }
        }

        abstract protected void write(String message);

    }
    static class ConsoleLogger extends AbstractLogger {

        public ConsoleLogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Standard Console::Logger: " + message);
        }
    }
    static class ErrorLogger extends AbstractLogger {

        public ErrorLogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Error Console::Logger: " + message);
        }
    }
    static class FileLogger extends AbstractLogger {

        public FileLogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("File::Logger: " + message);
        }
    }

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
