package edu.hm.cs.jenkins.notifier.view;


import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

/**
 * Menu which is displayed if the user performs a right click on the tray icon.
 *
 * @author Tobias Wochinger
 */
public class TrayMenu extends PopupMenu {

    private ResourceBundle bundle;

    private MenuItem about;

    private Menu settings;

    private MenuItem openSettings;

    private CheckboxMenuItem disable;

    private MenuItem exit;

    /**
     * Creates a tray menu.
     *
     * @param bundle bundle which contains the menu captions
     */
    @Inject
    public TrayMenu(final ResourceBundle bundle) {
        this.bundle = bundle;

        createAboutItem();
        createSettingsMenu();
        createExitItem();
        addComponents();
    }

    private void createAboutItem() {
        String aboutText = bundle.getString("menu.about");
        about = new MenuItem(aboutText);
    }

    private void createSettingsMenu() {
        String settingsText = bundle.getString("menu.settings");
        settings = new Menu(settingsText);

        createOpenSettingsItem();
        createDisableItem();

        settings.add(openSettings);
        settings.add(disable);
    }

    private void createDisableItem() {
        String disableText = bundle.getString("menu.item.disable");
        disable = new CheckboxMenuItem(disableText);
    }

    private void createOpenSettingsItem() {
        String openSettingsText = bundle.getString("menu.item.open_settings");
        openSettings = new MenuItem(openSettingsText);
    }

    private void createExitItem() {
        String exitText = bundle.getString("menu.exit");
        exit = new MenuItem(exitText);
    }

    private void addComponents() {
        add(about);
        addSeparator();

        add(settings);
        addSeparator();

        add(exit);
    }

    /**
     * Sets the controller to react if the user clicks on the exit item.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setExitController(@Named("exit") final ActionListener controller) {
        exit.addActionListener(controller);
    }

    /**
     * Sets the controller to react if the user clicks on the about item.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setAboutController(@Named("about") final ActionListener controller) {
        about.addActionListener(controller);
    }

    /**
     * Sets the controller to react if the user clicks on the disable item.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setDisableController(@Named("disable") final ItemListener controller) {
        disable.addItemListener(controller);
    }

    /**
     * Sets the controller to react if the user clicks on the show settings
     * item.
     *
     * @param controller controller which handles the event
     */
    @Inject
    public void setShowSettingsController(@Named("open") final ActionListener controller) {
        openSettings.addActionListener(controller);
    }
}
