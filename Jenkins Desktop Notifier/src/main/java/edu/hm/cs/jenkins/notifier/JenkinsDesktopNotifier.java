package edu.hm.cs.jenkins.notifier;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.cs.jenkins.notifier.controller.TrayApplicationController;

/**
 * Application which is located in the system bar and informs the user with
 * a popup when a Jenkins build is finished.
 */
public final class JenkinsDesktopNotifier {

    private JenkinsDesktopNotifier() {
        //not needed
    }

    /**
     * Starts the application.
     *
     * @param args Start arguments
     */
    public static void main(final String[] args) {
        Injector injector = Guice.createInjector(new BindingModule());
        TrayApplicationController trayApplicationController = injector.getInstance(TrayApplicationController.class);
        trayApplicationController.start();
    }
}
