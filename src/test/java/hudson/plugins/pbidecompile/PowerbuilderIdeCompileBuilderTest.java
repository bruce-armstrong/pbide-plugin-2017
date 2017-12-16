package hudson.plugins.pbidecompile;

import org.junit.Test;

import hudson.plugins.pbidecompile.PowerbuilderIdeCompileBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jonathan Zimmerman
 */
public class PowerbuilderIdeCompileBuilderTest {

    @Test
    public void shouldStripQuotedArguments() {
        final String quotedPlatform = "/p:Platform=\"Any CPU\"";
        final String strippedPlatform = "/p:Platform=Any CPU";

        String[] tokenizedArgs = PowerbuilderIdeCompileBuilder.tokenizeArgs(quotedPlatform);
        assertNotNull(tokenizedArgs);
        assertEquals(1, tokenizedArgs.length);
        assertEquals(strippedPlatform, tokenizedArgs[0]);
    }

    @Test
    public void shouldSplitArguments() {
        final String arguments = "/t:Build /p:Configuration=Debug";

        String[] tokenizedArgs = PowerbuilderIdeCompileBuilder.tokenizeArgs(arguments);
        assertNotNull(tokenizedArgs);
        assertEquals(2, tokenizedArgs.length);
        assertEquals("/t:Build", tokenizedArgs[0]);
        assertEquals("/p:Configuration=Debug", tokenizedArgs[1]);
    }

    @Test
    public void endEscapedCharacter() {
        final String oneArgumentsWithEndBackslash = "\\\\RemoteServerName\\OfficialBuilds\\Published\\";
        String[] tokenizedArgs = PowerbuilderIdeCompileBuilder.tokenizeArgs(oneArgumentsWithEndBackslash);
        assertEquals(1, tokenizedArgs.length);
        assertEquals(oneArgumentsWithEndBackslash, tokenizedArgs[0]);
    }

}
