/*
 * Copyright (c) 2015 Michiel Noback
 * All rights reserved
 * www.bioinf.nl, www.cellingo.net
 */
package nl.bioinf.arguments_provider_demo;

/**
 * interface that specifies which options should be provided to the tool.
 * @author michiel
 */
public interface OptionsProvider {
    /**
     * serves the name of the application user.
     * @return userName the user name
     */
    String getUserName();
    /**
     * serves the verbosity level to be used.
     * @return verbosityLevel the verbosity level
     */
    VerbosityLevel getVerbosityLevel();
    /**
     * serves the age of the application user.
     * @return userAge the user age
     */
    int getUserAge();
}
