package edu.clemson.resolve.jetbrains;

import com.intellij.lang.Language;

public class RESOLVELanguage extends Language {
    public static final RESOLVELanguage INSTANCE = new RESOLVELanguage();

    private RESOLVELanguage() {
        super("RESOLVE");
    }
}
