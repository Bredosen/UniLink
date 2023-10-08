package bredo.cmd.uni.link.interfaces;

import bredo.cmd.uni.link.types.DebugLevel;
import bredo.cmd.uni.link.utilities.UniLinkAction;
import bredo.cmd.uni.link.utilities.UniLinkProject;
import bredo.cmd.uni.link.handlers.UniLinkLogger;
import bredo.cmd.uni.link.managers.UniLink;

public interface UniLinkInstance {

    //<editor-fold desc="Registration">
    default void register(final String projectName) {
        if (!validate(projectName)) return;

        final UniLinkProject uniLinkProject = new UniLinkProject(getKey(), projectName);
        UniLink.getInstance().registerProject(uniLinkProject);
        UniLinkLogger.info("Successfully registered UniLink project [" + uniLinkProject + "]");
    }

    private boolean validate(final String projectName) {
        // Check if project key already exists in UniLink.
        if (UniLink.getInstance().containProjectKey(getKey())) {
            UniLinkLogger.warning("Project key already exists in UniLink!");
            return false;
        }

        // Check if project name is null or empty.
        if (projectName == null || projectName.isEmpty()) {
            UniLinkLogger.warning("Project name cannot be null or empty!");
            return false;
        }

        // Check if project name already exists in UniLink.
        if (UniLink.getInstance().containProjectName(projectName)) {
            UniLinkLogger.warning("Project name already exists in UniLink!");
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Get Key">
    default String getKey() {
        return getKey(this);
    }

    default String getKey(final Object keyFromClass) {
        return keyFromClass.getClass().getPackageName() + '.' + keyFromClass.getClass().getSimpleName();
    }
    //</editor-fold>

    //<editor-fold desc="Get UniLink Project">
    default UniLinkProject getUniLinkProject() {
        return getUniLinkProject(this);
    }

    default UniLinkProject getUniLinkProject(final Object keyFromClass) {
        return getUniLinkProjectFromKey(getKey(keyFromClass));
    }

    default UniLinkProject getUniLinkProjectFromKey(final String projectKey) {
        return UniLink.getInstance().getUniLinkProjectFromKey(projectKey);
    }

    default UniLinkProject getUniLinkProjectFromName(final String projectName) {
        return UniLink.getInstance().getUniLinkProjectFromName(projectName);
    }
    //</editor-fold>

    //<editor-fold desc="Components">
    //<editor-fold desc="Add Component">
    default void addComponent(final String componentKey, final Object component) {
        addComponentFromKey(getKey(), componentKey, component);
    }

    default void addComponentFromKey(final String projectKey, final String componentKey, final Object component) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        uniLinkProject.addComponent(componentKey, component);
    }

    default void addComponentFromName(final String projectName, final String componentKey, final Object component) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        uniLinkProject.addComponent(componentKey, component);
    }
    //</editor-fold>

    //<editor-fold desc="Get Component">
    default Object getComponent(final String componentKey) {
        return getComponentFromKey(getKey(), componentKey);
    }

    default Object getComponentFromKey(final String projectKey, final String componentKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        return uniLinkProject.getComponent(componentKey);
    }

    default Object getComponentFromName(final String projectName, final String componentKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        return uniLinkProject.getComponent(componentKey);
    }
    //</editor-fold>

    //<editor-fold desc="Remove Component">
    default void removeComponent(final String componentKey) {
        removeComponentFromKey(getKey(), componentKey);
    }

    default void removeComponentFromKey(final String projectKey, final String componentKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        uniLinkProject.removeComponent(componentKey);
    }

    default void removeComponentFromName(final String projectName, final String componentKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        uniLinkProject.removeComponent(componentKey);
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Actions">
    //<editor-fold desc="Add Action">
    default void addAction(final String actionKey, final UniLinkAction action) {
        addActionFromKey(getKey(), actionKey, action);
    }

    default void addActionFromKey(final String projectKey, final String actionKey, final UniLinkAction action) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        uniLinkProject.addAction(actionKey, action);
    }

    default void addActionFromName(final String projectName, final String actionKey, final UniLinkAction action) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        uniLinkProject.addAction(actionKey, action);
    }
    //</editor-fold>

    //<editor-fold desc="Get Action">
    default Object getAction(final String actionKey) {
        return getActionFromKey(getKey(), actionKey);
    }

    default UniLinkAction getActionFromKey(final String projectKey, final String actionKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        return uniLinkProject.getAction(actionKey);
    }

    default UniLinkAction getActionFromName(final String projectName, final String actionKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        return uniLinkProject.getAction(actionKey);
    }
    //</editor-fold>

    //<editor-fold desc="Remove Action">
    default void removeAction(final String actionKey) {
        removeActionFromKey(getKey(), actionKey);
    }

    default void removeActionFromKey(final String projectKey, final String actionKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        uniLinkProject.removeAction(actionKey);
    }

    default void removeActionFromName(final String projectName, final String actionKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        uniLinkProject.removeAction(actionKey);
    }
    //</editor-fold>

    //<editor-fold desc="Execute Action">
    default int executeAction(final String actionKey, final Object... parameters) {
        return executeActionFromKey(getKey(), actionKey, parameters);
    }

    default int executeActionFromKey(final String projectKey, final String actionKey, final Object... parameters) {
        final UniLinkAction uniLinkAction = getActionFromKey(projectKey, actionKey);
        if (uniLinkAction == null) throw new NullPointerException("UniLinkAction name= '" + actionKey + "' does not exists!");
        return uniLinkAction.execute(parameters);
    }

    default int executeActionFromName(final String projectName, final String actionKey, final Object... parameters) {
        final UniLinkAction uniLinkAction = getActionFromName(projectName, actionKey);
        if (uniLinkAction == null) throw new NullPointerException("UniLinkAction name= '" + actionKey + "' does not exists!");
        return uniLinkAction.execute(parameters);
    }
    //</editor-fold>

    //<editor-fold desc="Enable & Disable Action">
    //<editor-fold desc="Enable Action">
    default void addEnableAction(final Runnable enableAction) {
        addEnableActionFromKey(getKey(), enableAction);
    }

    default void addEnableActionFromKey(final String projectKey, final Runnable enableAction) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        addEnableActionFromProject(uniLinkProject, enableAction);
    }

    default void addEnableActionFromName(final String projectName, final Runnable enableAction) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        addEnableActionFromProject(uniLinkProject, enableAction);
    }

    default void addEnableActionFromProject(final UniLinkProject uniLinkProject, final Runnable enableAction) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (enableAction == null) throw new NullPointerException("Enable action cannot be null!");
        uniLinkProject.addAction("enable", parameters -> {
            enableAction.run();
            return 1;
        });
    }
    //</editor-fold>

    //<editor-fold desc="Disable Action">
    default void addDisableAction(final Runnable disableAction) {
        addDisableActionFromKey(getKey(), disableAction);
    }

    default void addDisableActionFromKey(final String projectKey, final Runnable disableAction) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        addDisableActionFromProject(uniLinkProject, disableAction);
    }

    default void addDisableActionFromName(final String projectName, final Runnable disableAction) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        addDisableActionFromProject(uniLinkProject, disableAction);
    }

    default void addDisableActionFromProject(final UniLinkProject uniLinkProject, final Runnable disableAction) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (disableAction == null) throw new NullPointerException("Disable action cannot be null!");
        uniLinkProject.addAction("disable", parameters -> {
            disableAction.run();
            return 1;
        });
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Log">
    //<editor-fold desc="Log Creation">
    default void logCreation() {
        logCreationFromKey(getKey());
    }

    default void logCreationFromKey(final String projectKey) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        logCreationFromProject(uniLinkProject);
    }

    default void logCreationFromName(final String projectName) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        logCreationFromProject(uniLinkProject);
    }

    default void logCreationFromProject(final UniLinkProject uniLinkProject) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        logIfFromProject(uniLinkProject, "Created instance \"" + getClass().getSimpleName() + "\"", DebugLevel.InDepth, DebugLevel.UnNecessary, DebugLevel.Minimal);
    }
    //</editor-fold>

    //<editor-fold desc="Log If">
    default void logIf(final String infoMessage, final DebugLevel... debugLevels) {
        logIfFromKey(getKey(), infoMessage, debugLevels);
    }

    default void logIfFromKey(final String projectKey, final String infoMessage, final DebugLevel... debugLevels) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
    }

    default void logIfFromName(final String projectName, final String infoMessage, final DebugLevel... debugLevels) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
    }

    default void logIfFromProject(final UniLinkProject uniLinkProject, final String infoMessage, final DebugLevel... debugLevels) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (infoMessage == null || infoMessage.isEmpty()) throw new NullPointerException("Info message cannot be null or empty!");
        if (debugLevels == null || debugLevels.length == 0) throw new NullPointerException("Debug level cannot be null or empty!");

        for (final DebugLevel debugLevel : debugLevels)
            if (uniLinkProject.getDebugLevel().equals(debugLevel)) {
                uniLinkProject.info(infoMessage);
                break;
            }
    }
    //</editor-fold>

    //<editor-fold desc="Info">
    default void info(final Object... messages) {
        infoFromKey(getKey(), messages);
    }

    default void infoFromKey(final String projectKey, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        infoFromProject(uniLinkProject, messages);
    }

    default void infoFromName(final String projectName, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        infoFromProject(uniLinkProject, messages);
    }

    default void infoFromProject(final UniLinkProject uniLinkProject, final Object... messages) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (messages == null || messages.length == 0) throw new NullPointerException("Messages cannot be null or empty!");

        uniLinkProject.info(messages);
    }

    //</editor-fold>

    //<editor-fold desc="Warning">
    default void warning(final Object... messages) {
        warningFromKey(getKey(), messages);
    }

    default void warningFromKey(final String projectKey, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        warningFromProject(uniLinkProject, messages);
    }

    default void warningFromName(final String projectName, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        warningFromProject(uniLinkProject, messages);
    }

    default void warningFromProject(final UniLinkProject uniLinkProject, final Object... messages) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (messages == null || messages.length == 0) throw new NullPointerException("Messages cannot be null or empty!");

        uniLinkProject.warning(messages);
    }

    //</editor-fold>

    //<editor-fold desc="Error">
    default void error(final Object... messages) {
        errorFromKey(getKey(), messages);
    }

    default void errorFromKey(final String projectKey, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromKey(projectKey);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject key= '" + projectKey + "' does not exists!");
        errorFromProject(uniLinkProject, messages);
    }

    default void errorFromName(final String projectName, final Object... messages) {
        final UniLinkProject uniLinkProject = getUniLinkProjectFromName(projectName);
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject name= '" + projectName + "' does not exists!");
        errorFromProject(uniLinkProject, messages);
    }

    default void errorFromProject(final UniLinkProject uniLinkProject, final Object... messages) {
        if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");
        if (messages == null || messages.length == 0) throw new NullPointerException("Messages cannot be null or empty!");

        uniLinkProject.error(messages);
    }
    //</editor-fold>
    //</editor-fold>
}
