package bredo.cmd.uni.link.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class UniLinkLogger {

    private static Logger logger;

    private static boolean logMessages;

    static {
        logger = Logger.getLogger("UniLink");
        logMessages = true;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void info(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.INFO, message.toString());
    }

    public static void warning(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.WARNING, message.toString());
    }

    public static void error(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.SEVERE, message.toString());
    }

    private static boolean notValid(final Object... messages) {
        if (!isLogMessages()) return true;
        if (messages == null || messages.length == 0) {
            new NullPointerException("Message cannot be null or empty!").printStackTrace();
            return true;
        }
        return false;
    }

    public static void setLogger(final Logger logger) {
        UniLinkLogger.logger = logger;
    }

    public static boolean isLogMessages() {
        return logMessages;
    }

    public static void setLogMessages(final boolean logMessages) {
        UniLinkLogger.logMessages = logMessages;
    }
}
