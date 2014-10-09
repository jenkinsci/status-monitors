package edu.hm.cs.jenkins.notifier.view.settings;


import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * Actual settings view containing fields and buttons to make settings.
 *
 * @author Tobias Wochinger
 */
@Singleton
public class SettingsView extends JPanel {

    private static final int COLUMNS = 2;

    private static final int ROWS = 6;

    private static final int GAP = 15;

    private Border enabledBorder = BorderFactory.createLineBorder(Color.GRAY);

    private Border disabledBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

    private static final EmptyBorder SETTINGS_BORDER = new EmptyBorder(15, 15, 15, 15);

    private static final EmptyBorder ELEMENTS_BORDER = new EmptyBorder(0, 10, 0, 10);

    private final ResourceBundle bundle;

    private JTextField hostnameInput = new JTextField();

    private JTextField portInput = new JTextField();

    private JCheckBox authenticationNeededTrigger = new JCheckBox();

    private JTextField usernameInput = new JTextField();

    private JTextField tokenInput = new JTextField();

    private JButton confirm = new JButton();

    private JButton cancel = new JButton();

    /**
     * Creates the actual settings view.
     *
     * @param bundle bundle which contains the label
     */
    @Inject
    public SettingsView(final ResourceBundle bundle) {
        this.bundle = bundle;
        configureLayout();
        addComponents();
    }

    private void configureLayout() {
        GridLayout layoutManager = new GridLayout(ROWS, COLUMNS);
        layoutManager.setVgap(GAP);
        layoutManager.setHgap(GAP);
        setLayout(layoutManager);
        setBorder(SETTINGS_BORDER);
    }

    private void addComponents() {
        addLabel("settings.hostname");
        addTextField(hostnameInput);

        addLabel("settings.port");
        addTextField(portInput);

        addLabel("settings.authentication_needed");
        add(authenticationNeededTrigger);

        addLabel("settings.username");
        addTextField(usernameInput);

        addLabel("settings.token");
        addTextField(tokenInput);

        addButton(confirm, "settings.ok");
        addButton(cancel, "settings.cancel");
    }

    private void addLabel(final String keyOfResource) {
        String text = bundle.getString(keyOfResource);
        JLabel label = new JLabel(text);
        add(label);
    }

    private void addTextField(final JTextField field) {
        Border border = getBorder(true);
        field.setBorder(border);
        add(field);
    }

    private void addButton(final JButton button, final String keyOfResource) {
        String text = bundle.getString(keyOfResource);
        button.setText(text);
        add(button);
    }

    /**
     * Sets the controller to handle the event when the user confirms his
     * settings.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setConfirmController(@Named("confirm_settings") final ActionListener controller) {
        confirm.addActionListener(controller);
    }

    /**
     * Sets the controller to handle the event when the user cancels the
     * settings.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setCancelController(@Named("cancel_settings")
                                    final ActionListener controller) {
        cancel.addActionListener(controller);
    }

    /**
     * Sets the controller to handle the event when the user triggers the
     * checkbox in order to specify that authentication is needed or not to
     * access Jenkins.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setAuthenticationNeededController(@Named("authentication_trigger") final ActionListener controller) {
        authenticationNeededTrigger.addActionListener(controller);
    }

    /**
     * Enables / disables the username and token field.
     *
     * @param enable <code>true</code> to enable the fields,
     *               otherwise <code>false</code>
     */
    public void setCredentialFieldsEnabled(final boolean enable) {
        usernameInput.setEnabled(enable);
        tokenInput.setEnabled(enable);
        Border border = getBorder(enable);
        usernameInput.setBorder(border);
        tokenInput.setBorder(border);
    }

    private Border getBorder(final boolean enabled) {
        if (enabled) {
            return new CompoundBorder(enabledBorder, ELEMENTS_BORDER);
        } else {
            return new CompoundBorder(disabledBorder, ELEMENTS_BORDER);
        }
    }

    public String getHostname() {
        return hostnameInput.getText();
    }

    /**
     * Sets the text in the hostname input field.
     *
     * @param hostname previously saved hostname
     */
    public void setHostname(final String hostname) {
        hostnameInput.setText(hostname);
    }

    public String getPort() {
        return portInput.getText();
    }

    /**
     * Sets the text in the port input field.
     *
     * @param port previously saved port
     */
    public void setPort(final String port) {
        portInput.setText(port);
    }

    public boolean isAuthenticationNeeded() {
        return authenticationNeededTrigger.isSelected();
    }

    /**
     * Selects / deselects the authentication needed checkbox and enables /
     * disables the username and token fields.
     *
     * @param authenticationNeeded <code>true</code> to select,
     *                             <code>false</code> to deselect
     */
    public void setAuthenticationNeeded(final boolean authenticationNeeded) {
        authenticationNeededTrigger.setSelected(authenticationNeeded);
        setCredentialFieldsEnabled(authenticationNeeded);
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    /**
     * Sets the text in the username input field.
     *
     * @param username previously saved username
     */
    public void setUsername(final String username) {
        usernameInput.setText(username);
    }

    public String getToken() {
        return tokenInput.getText();
    }

    /**
     * Sets the text in the token input field.
     *
     * @param token previously saved token
     */
    public void setToken(final String token) {
        tokenInput.setText(token);
    }
}
