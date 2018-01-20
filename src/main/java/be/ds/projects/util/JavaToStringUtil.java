package be.ds.projects.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JavaToStringUtil {

    public static String makePretty(final Object object) throws IOException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}
