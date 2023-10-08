package bredo.cmd.uni.link.utilities;

import bredo.cmd.uni.link.types.DebugLevel;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UniLinkProject {

    private final String projectKey;
    private final String projectName;
    private final HashMap<String, Object> projectComponents;
    private final HashMap<String, UniLinkAction> projectActions;
    private Logger logger;
    private DebugLevel debugLevel;

    public UniLinkProject(final String projectKey, final String projectName) {
        this.projectKey = projectKey;
        this.projectName = projectName;

        this.projectComponents = new HashMap<>();
        this.projectActions = new HashMap<>();

        this.logger = Logger.getLogger(getProjectName());
        this.debugLevel = DebugLevel.Necessary;
    }

    //<editor-fold desc="Components">
    public void addComponent(final String componentKey, final Object component) {
        if (componentKey == null || componentKey.isEmpty()) throw new NullPointerException("ComponentKey cannot be null!");
        if (component == null) throw new NullPointerException("Component cannot be null!");

        getProjectComponents().put(componentKey, component);
    }

    public Object getComponent(final String componentKey) {
        if (componentKey == null || componentKey.isEmpty()) throw new NullPointerException("ComponentKey cannot be null!");
        return getProjectComponents().get(componentKey);
    }

    public void removeComponent(final String componentKey) {
        if (componentKey == null || componentKey.isEmpty()) throw new NullPointerException("ComponentKey cannot be null!");
        getProjectComponents().remove(componentKey);
    }
    //</editor-fold>

    //<editor-fold desc="Actions">
    public void addAction(final String actionKey, final UniLinkAction action) {
        if (actionKey == null || actionKey.isEmpty()) throw new NullPointerException("ActionKey cannot be null!");
        if (action == null) throw new NullPointerException("Action cannot be null!");

        getProjectActions().put(actionKey, action);
    }

    public UniLinkAction getAction(final String actionKey) {
        if (actionKey == null || actionKey.isEmpty()) throw new NullPointerException("ActionKey cannot be null!");
        return getProjectActions().get(actionKey);
    }

    public void removeAction(final String actionKey) {
        if (actionKey == null || actionKey.isEmpty()) throw new NullPointerException("ActionKey cannot be null!");
        getProjectActions().remove(actionKey);
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public HashMap<String, Object> getProjectComponents() {
        return projectComponents;
    }

    public HashMap<String, UniLinkAction> getProjectActions() {
        return projectActions;
    }
    //</editor-fold>

    //<editor-fold desc="Logger">
    public void info(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.INFO, message.toString());
    }

    public void warning(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.WARNING, message.toString());
    }

    public void error(final Object... messages) {
        if (notValid(messages)) return;
        for (final Object message : messages) getLogger().log(Level.SEVERE, message.toString());
    }

    private boolean notValid(final Object... messages) {
        if (messages == null || messages.length == 0) {
            new NullPointerException("Message cannot be null or empty!").printStackTrace();
            return true;
        }
        return false;
    }

    //<editor-fold desc="Getter & Setter">
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(final Logger logger) {
        this.logger = logger;
    }
    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="Debug Level">
    public DebugLevel getDebugLevel() {
        return debugLevel;
    }

    public void setDebugLevel(final DebugLevel debugLevel) {
        this.debugLevel = debugLevel;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "projectKey= '" + projectKey + '\'' + ", projectName= '" + projectName + '\'';
    }
}
