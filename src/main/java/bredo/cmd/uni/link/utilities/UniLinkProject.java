package bredo.cmd.uni.link;

import java.util.HashMap;

public final class UniLinkProject {

    private final String projectKey;
    private final String projectName;
    private final HashMap<String, Object> projectComponents;

    public UniLinkProject(final String projectKey, final String projectName) {
        this.projectKey = projectKey;
        this.projectName = projectName;

        this.projectComponents = new HashMap<>();
    }

    public void addComponent(final String componentKey, final Object component) {
        if (componentKey == null || componentKey.isEmpty()) throw new NullPointerException("ComponentKey cannot be null!");
        if (component == null) throw new NullPointerException("Component cannot be null!");

        getProjectComponents().put(componentKey, component);
    }

    public Object getComponent(final String componentKey) {
        if (componentKey == null || componentKey.isEmpty()) throw new NullPointerException("ComponentKey cannot be null!");
        return getProjectComponents().get(componentKey);
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public HashMap<String, Object> getProjectComponents() {
        return projectComponents;
    }

    @Override
    public String toString() {
        return "projectKey= '" + projectKey + '\'' + ", projectName= '" + projectName + '\'';
    }
}
