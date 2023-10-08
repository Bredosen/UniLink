package bredo.cmd.uni.link;

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
}
