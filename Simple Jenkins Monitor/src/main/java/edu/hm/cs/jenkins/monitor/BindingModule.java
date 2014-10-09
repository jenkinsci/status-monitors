package edu.hm.cs.jenkins.monitor;

import android.widget.ArrayAdapter;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import edu.hm.cs.jenkins.monitor.controller.settings.StoredCredentialsLoader;
import edu.hm.cs.jenkins.monitor.controller.util.ExceptionHandler;
import edu.hm.cs.jenkins.monitor.controller.util.StoredCredentialsValidationUtil;
import edu.hm.cs.jenkins.monitor.controller.web.ClientFactoryProvider;
import edu.hm.cs.jenkins.monitor.controller.web.callback.BuildsCallback;
import edu.hm.cs.jenkins.monitor.controller.web.callback.JobsCallback;
import edu.hm.cs.jenkins.monitor.controller.web.callback.TriggerCallback;
import edu.hm.cs.jenkins.monitor.view.adapter.BuildAdapter;
import edu.hm.cs.jenkins.monitor.view.adapter.JobAdapter;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.monitor.view.dialog.ErrorDialog;
import edu.hm.cs.jenkins.monitor.view.dialog.ToastDialog;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.JenkinsValidatorFactory;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import java.util.List;

/**
 * Defines the binding of classes to concrete implementations which should be
 * injected by RoboGuice.
 * Loaded through /res/values/roboguice.xml.
 *
 * @author Tobias Wochinger
 */
public class BindingModule extends AbstractModule {

    @Override
    protected void configure() {
        bindAdapters();
        bindDialogs();
        bindCallbacks();
        bindWebClasses();
        bindExceptionHandler();
    }

    private void bindAdapters() {
        bind(new TypeLiteral<ArrayAdapter<Build>>() { }).to(BuildAdapter.class);
        bind(new TypeLiteral<ArrayAdapter<Job>>() { }).to(JobAdapter.class);
    }

    private void bindDialogs() {
        bind(Dialog.class).annotatedWith(Names.named("striking")).to(ErrorDialog.class);
        bind(Dialog.class).annotatedWith(Names.named("silent")).to(ToastDialog.class);
    }

    private void bindCallbacks() {
        bind(new TypeLiteral<ClientCallback<List<Job>>>() { }).to(JobsCallback.class);
        bind(new TypeLiteral<ClientCallback<Job>>() { }).to(BuildsCallback.class);
        bind(ErrorCallback.class).to(TriggerCallback.class);
    }

    private void bindWebClasses() {
        bind(ValidatorFactory.class).to(JenkinsValidatorFactory.class);
        bind(ClientFactory.class).toProvider(ClientFactoryProvider.class);
        bind(CredentialsLoader.class).to(StoredCredentialsLoader.class);
        bind(ValidationUtil.class).to(StoredCredentialsValidationUtil.class);
    }

    private void bindExceptionHandler() {
        bind(Thread.UncaughtExceptionHandler.class).to(ExceptionHandler.class);
    }
}