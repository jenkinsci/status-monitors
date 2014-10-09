package edu.hm.cs.jenkins.notifier;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import edu.hm.cs.jenkins.notifier.controller.communication.JobController;
import edu.hm.cs.jenkins.notifier.controller.communication.LastBuildController;
import edu.hm.cs.jenkins.notifier.controller.menu.AboutController;
import edu.hm.cs.jenkins.notifier.controller.menu.DisableController;
import edu.hm.cs.jenkins.notifier.controller.menu.ExitController;
import edu.hm.cs.jenkins.notifier.controller.menu.OpenSettingsController;
import edu.hm.cs.jenkins.notifier.controller.settings.AuthenticationNeededController;
import edu.hm.cs.jenkins.notifier.controller.settings.CancelSettingsController;
import edu.hm.cs.jenkins.notifier.controller.settings.ConfirmSettingsController;
import edu.hm.cs.jenkins.notifier.controller.util.StoredCredentialsLoader;
import edu.hm.cs.jenkins.notifier.controller.util.ViewValidationUtil;
import edu.hm.cs.jenkins.notifier.controller.util.ViewedCredentialsLoader;
import edu.hm.cs.jenkins.notifier.provider.*;
import edu.hm.cs.jenkins.notifier.view.JenkinsTrayIcon;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.JenkinsValidatorFactory;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Defines the binding of classes to concrete implementations which should be
 * injected by Google Guice.
 * Loaded through {@link JenkinsDesktopNotifier}
 *
 * @author Tobias Wochinger
 */
public class BindingModule extends AbstractModule {

    @Override
    protected void configure() {
        bindWebClasses();
        bindViews();
        bindCommunicationControllers();
        bindMenuControllers();
        bindSettingsListeners();
        bindSystemResources();
        bindAwtTools();
    }

    private void bindWebClasses() {
        bind(CredentialsLoader.class).annotatedWith(Names.named("stored")).to(StoredCredentialsLoader.class);
        bind(CredentialsLoader.class).annotatedWith(Names.named("view")).to(ViewedCredentialsLoader.class);
        bind(ValidatorFactory.class).to(JenkinsValidatorFactory.class);
        bind(ValidationUtil.class).to(ViewValidationUtil.class);
        bind(ClientFactory.class).toProvider(ClientFactoryProvider.class);
    }

    private void bindViews() {
        bind(Image.class).toProvider(ImageIconProvider.class);
        bind(TrayIcon.class).to(JenkinsTrayIcon.class);
    }

    private void bindCommunicationControllers() {
        bind(new TypeLiteral<ClientCallback<List<Job>>>() { }).to(JobController.class);
        bind(new TypeLiteral<ClientCallback<Job>>() { }).to(LastBuildController.class);
    }

    private void bindMenuControllers() {
        bind(ActionListener.class).annotatedWith(Names.named("about")).to(AboutController.class);
        bind(ItemListener.class).annotatedWith(Names.named("disable")).to(DisableController.class);
        bind(ActionListener.class).annotatedWith(Names.named("open")).to(OpenSettingsController.class);
        bind(ActionListener.class).annotatedWith(Names.named("exit")).to(ExitController.class);
    }

    private void bindSettingsListeners() {
        bind(ActionListener.class).annotatedWith(Names.named("confirm_settings")).to(ConfirmSettingsController.class);
        bind(ActionListener.class).annotatedWith(Names.named("cancel_settings")).to(CancelSettingsController.class);
        bind(ActionListener.class).annotatedWith(Names.named("authentication_trigger")).
                to(AuthenticationNeededController.class);
    }

    private void bindSystemResources() {
        bind(ResourceBundle.class).toProvider(ResourceBundleProvider.class);
        bind(Preferences.class).toProvider(PreferencesProvider.class);
        bind(SystemTray.class).toProvider(SystemTrayProvider.class);
        bindAwtTools();
    }

    private void bindAwtTools() {
        bind(GraphicsConfiguration.class).toProvider(GraphicsConfigurationProvider.class);
        bind(Toolkit.class).toProvider(ToolkitProvider.class);
    }
}
