/*
 * Copyright (c) 2015 Michiel Noback
 * All rights reserved
 * www.bioinf.nl, www.cellingo.net
 */
package nl.bioinf.arguments_provider_demo;

/**
 * This is the controller class for the messaging application.
 *
 * @author michiel
 */
public class MessagingController {
    private final OptionsProvider optionsProvider;

    /**
     * the constructor need an OptionsProvider to be able to do its work.
     * @param optionsPovider the options provider
     */
    public MessagingController(final OptionsProvider optionsPovider) {
        this.optionsProvider = optionsPovider;
    }

    /**
     * Starts the application logic.
     */
    public void start() {
        if (optionsProvider == null) {
            throw new IllegalStateException("No use going on without an optionsController.");
        }
        VerbosityLevel level = optionsProvider.getVerbosityLevel();
        if (level == VerbosityLevel.YOU_TALK_TOO_MUCH) {
            printUserSettings();
        }
        if (level.compareTo(VerbosityLevel.STRONG_SILENT_TYPE) > 0) {
            System.out.println("Starting all the good work now");
        }
        if (level.compareTo(VerbosityLevel.NORMAL) > 0) {
            System.out.println("still busy doing the good work");
        }
        //application logic here
        System.out.println("Done");
    }

    /**
     * prints all application settings.
     */
    private void printUserSettings() {
        System.out.println("I understand your name is " + optionsProvider.getUserName());
        int age = optionsProvider.getUserAge();
        System.out.print("Your age is " + age);

        if (age > 40) {
            System.out.println(" - wow you are really old!");
        } else if (age > 25) {
            System.out.println(" - still studying hey?");
        } else {
            System.out.println(" - nice, a young geek!");
        }
    }
}
