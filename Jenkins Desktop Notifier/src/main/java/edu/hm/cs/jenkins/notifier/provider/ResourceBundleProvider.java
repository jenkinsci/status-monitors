package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import java.util.ResourceBundle;

/**
 * Provides an instance of {@link java.util.ResourceBundle} which contains
 * the internationalized texts for the application.
 *
 * @author Tobias Wochinger
 */
public class ResourceBundleProvider implements Provider<ResourceBundle> {

    @Override
    public ResourceBundle get() {
        return ResourceBundle.getBundle("internationalization.Texts");
    }
}
