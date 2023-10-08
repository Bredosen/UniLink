package bredo.cmd.uni.link;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class UniLinkLogger {

    private final static Logger logger;

    static {
        logger = Logger.getLogger("UniLink");

    }

    public static Logger getLogger() {
        return logger;
    }

    public static void info(final Object... messages) {
        if (messages == null || messages.length == 0) {
            new NullPointerException("Message cannot be null or empty!").printStackTrace();
            return;
        }
        for (final Object message : messages) getLogger().log(Level.INFO, message.toString());
    }

    public static void warning(final Object... messages) {
        if (messages == null || messages.length == 0) {
            new NullPointerException("Message cannot be null or empty!").printStackTrace();
            return;
        }
        for (final Object message : messages) getLogger().log(Level.WARNING, message.toString());
    }

    public static void error(final Object... messages) {
        if (messages == null || messages.length == 0) {
            new NullPointerException("Message cannot be null or empty!").printStackTrace();
            return;
        }
        for (final Object message : messages) getLogger().log(Level.SEVERE, message.toString());
    }
}
