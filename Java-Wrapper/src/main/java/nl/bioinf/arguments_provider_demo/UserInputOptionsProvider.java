/*
 * Copyright (c) 2015 Michiel Noback
 * All rights reserved
 * www.bioinf.nl, www.cellingo.net
 */
package nl.bioinf.arguments_provider_demo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface specifies all properties an OptionsProvider should serve.
 *
 * @author michiel
 */
public class UserInputOptionsProvider implements OptionsProvider {
    private int userAge;
    private String userName;
    private VerbosityLevel level;

    /**
     * default constructor.
     */
    public UserInputOptionsProvider() {
        initialize();
    }

    /**
     * greedy instantiation of this object; it will try to obtain all relevant data from the ser immediately.
     */
    private void initialize() {
        Scanner scanner = new Scanner(System.in);
        fetchUserName(scanner);
        fetchUserAge(scanner);
        fetchVerbosityLevel(scanner);
    }

    /**
     * fetches and sets the verbosity level.
     * @param scanner the scanner
     */
    private void fetchVerbosityLevel(final Scanner scanner) {
        VerbosityLevel uLevel = VerbosityLevel.NORMAL;
        System.out.println("Please choose verbosity level;"
                + "\n1: Quiet, or the strong silent type\n2: normal\n3: talk too much"
                + "\n(hit enter to use default \"normal\" mode):");
        String levelStr = scanner.next();
        try {
            uLevel = VerbosityLevel.values()[Integer.parseInt(levelStr) - 1];
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("level " + levelStr + " is unknown; switching to default mode");
        }
        this.level = uLevel;
    }

    /**
     * fetches and sets the user age.
     * @param scanner the scanner
     */
    private void fetchUserAge(final Scanner scanner) {
        int uAge = 42;
        System.out.println("Please enter your age:");
        try {
            uAge = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("age could not be parsed; please try again.\nAborting.");
            //Yes this System.exit(0) is really bad coding!
            //Can you improve on this design?
            System.exit(0);
        }
        this.userAge = uAge;
    }

    /**
     * fetches and sets the user name.
     * @param scanner the scanner
     */
    private void fetchUserName(final Scanner scanner) {
        String uname = "John Doe";
        System.out.println("Please enter your user name for this session:");
        uname = scanner.next();
        this.userName = uname;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public VerbosityLevel getVerbosityLevel() {
        return level;
    }

    @Override
    public int getUserAge() {
        return userAge;
    }
}
