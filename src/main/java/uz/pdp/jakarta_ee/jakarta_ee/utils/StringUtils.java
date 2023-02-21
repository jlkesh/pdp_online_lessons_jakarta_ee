package uz.pdp.jakarta_ee.jakarta_ee.utils;

import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static final Pattern validEmailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean validEmail(@NonNull String email) {
        return validEmailPattern.matcher(email).matches();
    }

    public static String fileExtension(@NonNull String originalName) {
        return originalName.substring(originalName.lastIndexOf(".") + 1);
    }
}
