/*
 * Copyright (c) 2015 Michiel Noback
 * All rights reserved
 * www.bioinf.nl, www.cellingo.net
 */
package nl.bioinf.arguments_provider_demo;

import java.util.Arrays;

/**
 * Main class designed to work with user input provided standard CL arguments and parsed using Apache CLI. Class is
 * final because it is not designed for extension.
 *
 * @author michiel
 */
public final class CommandLineArgsRunner {

    /**
     * private constructor because this class is not supposed to be instantiated.
     */
    private CommandLineArgsRunner() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        try {
            ApacheCliOptionsProvider op = new ApacheCliOptionsProvider(args);
            if (op.helpRequested()) {
                op.printHelp();
                return;
            }
            MessagingController controller = new MessagingController(op);
            controller.start();
        } catch (IllegalStateException ex) {
            System.err.println("Something went wrong while processing your command line \""
                    + Arrays.toString(args) + "\"");
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
            ApacheCliOptionsProvider op = new ApacheCliOptionsProvider(new String[]{});
            op.printHelp();
        }
    }
}
