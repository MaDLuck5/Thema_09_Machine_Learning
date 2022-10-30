package nl.bioinf.arguments_provider_demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Creation date: Jun 28, 2017
 *
 * @version 0.01
 * @autor Michiel Noback (&copy; 2017)
 */
public class ApacheCliOptionsProviderTest {

    @Test
    public void getVerbosityLevelNormal() throws Exception {
        String[] args = new String[]{"-v 1"};
        OptionsProvider op = new ApacheCliOptionsProvider(args);
        assertEquals(VerbosityLevel.STRONG_SILENT_TYPE, op.getVerbosityLevel());

        args = new String[]{"-v 2"};
        op = new ApacheCliOptionsProvider(args);
        assertEquals(VerbosityLevel.NORMAL, op.getVerbosityLevel());

        args = new String[]{"-v 3"};
        op = new ApacheCliOptionsProvider(args);
        assertEquals(VerbosityLevel.YOU_TALK_TOO_MUCH, op.getVerbosityLevel());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void getVerbosityLevelIllegalLevel() throws Exception {
        String[] args = new String[]{"-v 0"};
        OptionsProvider op = new ApacheCliOptionsProvider(args);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void getVerbosityLevelNoNumber() throws Exception {
        String[] args = new String[]{"-v a"};
        OptionsProvider op = new ApacheCliOptionsProvider(args);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void passIllegalArgsArray() throws Exception {
        String[] args = new String[]{"-t foo"};
        OptionsProvider op = new ApacheCliOptionsProvider(args);
    }

}