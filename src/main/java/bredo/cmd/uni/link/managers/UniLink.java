package bredo.cmd.uni.link.managers;

import bredo.cmd.uni.link.utilities.UniLinkAction;
import bredo.cmd.uni.link.utilities.UniLinkProject;

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

    //<editor-fold desc="Constructor">
    private final HashSet<UniLinkProject> uniLinkProjects;
    private boolean enabled;
    private boolean disabled;

    public UniLink() {
        uniLinkProjects = new HashSet<>();
        this.enabled = false;
        this.disabled = false;
    }
    //</editor-fold>

    //<editor-fold desc="Actions">
    public void enable() {
        if (isEnabled()) throw new RuntimeException("UniLink has already been enabled!");
        for (final UniLinkProject uniLinkProject : getUniLinkProjects()) {
            if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");

            final UniLinkAction uniLinkAction = uniLinkProject.getAction("enable");
            if (uniLinkAction == null) continue;

            uniLinkAction.execute();
        }
        enabled = true;
    }

    public void disable() {
        if (isDisabled()) throw new RuntimeException("UniLink has already been disabled!");
        for (final UniLinkProject uniLinkProject : getUniLinkProjects()) {
            if (uniLinkProject == null) throw new NullPointerException("UniLinkProject cannot be null!");

            final UniLinkAction uniLinkAction = uniLinkProject.getAction("disable");
            if (uniLinkAction == null) continue;

            uniLinkAction.execute();
        }
        disabled = true;
    }

    public void registerProject(final UniLinkProject uniLinkProject) {
        getUniLinkProjects().add(uniLinkProject);
    }
    //</editor-fold>

    //<editor-fold desc="Contain">
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
    //</editor-fold>

    //<editor-fold desc="Getters">

    //<editor-fold desc="UniLinkProjects">
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

    public HashSet<UniLinkProject> getUniLinkProjects() {
        return uniLinkProjects;
    }
    //</editor-fold>

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isDisabled() {
        return disabled;
    }
    //</editor-fold>
}
