package com.skeleton.mvp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Developer: Click Labs
 */

public final class RootUtil {

    /**
     * Prevent instantiation
     */
    private RootUtil() {
    }

    /**
     * Checks if the device is rooted or not
     *
     * @return true if the device is rooted
     */
    public static boolean isDeviceRooted() {
        return hasTestKeys() || isSuperUserInstalled() || isSuperUser();
    }

    /**
     * Checks if the system image is signed by test-keys
     * <p>
     * The Android tree includes test-keys under build/target/product/security.
     * Building an Android OS image using make will sign all .apk files using the test-keys.
     * Since the test-keys are publicly known, anybody can sign their own .apk files with the same keys,
     * which may allow them to replace or hijack system apps built into your OS image.
     * For this reason it is critical to sign any publicly released or deployed Android OS image
     * with a special set of release-keys that only you have access to.
     * </p>
     * <p>
     * Release-Keys and Test-Keys has to do with how the kernel is signed when it is compiled.
     * Release-Keys means it was signed with an official Key from an official developer.
     * Test-Keys means it was signed with a custom key generated by a third-party developer.
     * From a security standpoint Release-Keys generally means the kernel is more secure,
     * which is not always the case.
     * </p>
     *
     * @return true if the device has if the android image contains or OS contains test-keys
     */
    private static boolean hasTestKeys() {
        /*
         * This code is for getting build.prop located in /system/build.prop
         */
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    /**
     * Checks various directories of the system for Super User apk and files
     *
     * @return true if Super user is installed
     */
    private static boolean isSuperUserInstalled() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su",
                "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks root by executing which command in /system/xbin/which
     *
     * @return true if super user is found
     */
    private static boolean isSuperUser() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return in.readLine() != null;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

}
