package org.xdi.oxauth.model.common;

/**
 * @author Yuriy Zabrovarnyy
 * @version 0.9, 22/02/2013
 */

public enum ProgrammingLanguage {
    PYTHON("Python"),
    JAVA_SCRIPT("JavaScript");

    private final String m_value;

    private ProgrammingLanguage(String p_value) {
        m_value = p_value;
    }

    public String getValue() {
        return m_value;
    }

    public static ProgrammingLanguage fromString(String p_string) {
        for (ProgrammingLanguage v : values()) {
            if (v.getValue().equalsIgnoreCase(p_string)) {
                return v;
            }
        }
        return null;
    }
}
