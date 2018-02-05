package be.ds.projects.botTrader.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Utilty class that handles the printing of data for readability purposes.
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class ToStringUtil {

    public static String prettyPrintJson(final Object object) throws IOException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}
