package bredo.cmd.uni.link;

import java.util.HashSet;

public final class UniLink {

    //<editor-fold desc="SINGLETON">
    private final static UniLink SINGLETON;

    static {
        SINGLETON = new UniLink();
    }

    public static UniLink getInstance() {
        return SINGLETON;
    }
    //</editor-fold>

    private final HashSet<UniLinkProject> uniLinkProjects;

    public UniLink() {
        uniLinkProjects = new HashSet<>();
    }

    public boolean containProjectName(final String projectName) {
        for (final UniLinkProject uniLinkProject : getUniLinkProjects())
            if (uniLinkProject.getProjectName().equals(projectName)) return true;
        return false;
    }

    public boolean containProjectKey(final String projectKey) {
        for (final UniLinkProject uniLinkProject : getUniLinkProjects())
            if (uniLinkProject.getProjectKey().equals(projectKey)) return true;
        return false;
    }

    public UniLinkProject getUniLinkProjectFromKey(final String projectKey) {
        for (final UniLinkProject uniLinkProject : getUniLinkProjects())
            if (uniLinkProject.getProjectKey().equals(projectKey)) return uniLinkProject;
        return null;
    }

    public UniLinkProject getUniLinkProjectFromName(final String projectName) {
        for (final UniLinkProject uniLinkProject : getUniLinkProjects())
            if (uniLinkProject.getProjectName().equals(projectName)) return uniLinkProject;
        return null;
    }

    public void registerProject(final UniLinkProject uniLinkProject) {
        getUniLinkProjects().add(uniLinkProject);
    }

    public HashSet<UniLinkProject> getUniLinkProjects() {
        return uniLinkProjects;
    }

}
