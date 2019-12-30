package com.kunpeng.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;

import com.kunpeng.common.config.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;


/**
 * Created by xuqm on 2017/11/20.
 */

public class FileHelper {
    private static final String TAG = FileHelper.class.getSimpleName();
    private static String rootPathName = "QinQi." + Constants.getSystemConfig().getAppId();
    private static String[][] MIME_MapTable = new String[][]{{".3gp", "video/3gpp"}, {".apk", "application/vnd.android.package-archive"}, {".asf", "video/x-ms-asf"}, {".avi", "video/x-msvideo"}, {".bin", "application/octet-stream"}, {".bmp", "image/bmp"}, {".c", "text/plain"}, {".class", "application/octet-stream"}, {".conf", "text/plain"}, {".cpp", "text/plain"}, {".doc", "application/msword"}, {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}, {".xls", "application/vnd.ms-excel"}, {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}, {".exe", "application/octet-stream"}, {".gif", "image/gif"}, {".gtar", "application/x-gtar"}, {".gz", "application/x-gzip"}, {".h", "text/plain"}, {".htm", "text/html"}, {".html", "text/html"}, {".jar", "application/java-archive"}, {".java", "text/plain"}, {".jpeg", "image/jpeg"}, {".jpg", "image/jpeg"}, {".eucppic", "image/jpeg"}, {".js", "application/x-javascript"}, {".log", "text/plain"}, {".m3u", "audio/x-mpegurl"}, {".m4a", "audio/mp4a-latm"}, {".m4b", "audio/mp4a-latm"}, {".m4p", "audio/mp4a-latm"}, {".m4u", "video/vnd.mpegurl"}, {".m4v", "video/x-m4v"}, {".mov", "video/quicktime"}, {".mp2", "audio/x-mpeg"}, {".mp3", "audio/x-mpeg"}, {".mp4", "video/mp4"}, {".mpc", "application/vnd.mpohun.certificate"}, {".mpe", "video/mpeg"}, {".mpeg", "video/mpeg"}, {".mpg", "video/mpeg"}, {".mpg4", "video/mp4"}, {".mpga", "audio/mpeg"}, {".msg", "application/vnd.ms-outlook"}, {".ogg", "audio/ogg"}, {".pdf", "application/pdf"}, {".png", "image/png"}, {".pps", "application/vnd.ms-powerpoint"}, {".ppt", "application/vnd.ms-powerpoint"}, {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"}, {".prop", "text/plain"}, {".rc", "text/plain"}, {".rmvb", "audio/x-pn-realaudio"}, {".rtf", "application/rtf"}, {".sh", "text/plain"}, {".tar", "application/x-tar"}, {".tgz", "application/x-compressed"}, {".txt", "text/plain"}, {".wav", "audio/x-wav"}, {".wma", "audio/x-ms-wma"}, {".wmv", "audio/x-ms-wmv"}, {".wps", "application/vnd.ms-works"}, {".xml", "text/plain"}, {".z", "application/x-compress"}, {".zip", "application/x-zip-compressed"}, {"", "*/*"}};

    private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final String IMAGE = "image";

    public static String getRootPath() {
        StringBuffer stbPath = new StringBuffer();
        stbPath.append(Environment.getExternalStorageDirectory().getPath());
        stbPath.append(File.separator);
        stbPath.append(rootPathName);
        stbPath.append(File.separator);
        return stbPath.toString();
    }

    public static void delete(String path) {

        File file = new File(path);
        if (!file.exists()
                || !file.isFile())
            return;
        file.delete();

    }

    /**
     * @param dir
     * @return
     */
    public static long calculateDiskCacheSize(File dir) {
        long size = MIN_DISK_CACHE_SIZE;

        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
            // Target 2% of the total space.
            size = available / 50;
        } catch (IllegalArgumentException ignored) {
        }

        // Bound inside min/max size for disk cache.
        return Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
    }

    public static void delete(String destFileDir, String destFileName) {

        File dir = new File(destFileDir);
        if (!dir.exists())
            return;
        File file = new File(dir, destFileName);
        delete(file.getAbsolutePath());
    }

    /**
     * 转换文件大小
     */
    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (0 == fileS) {
            fileSizeString = "0B";
        } else if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }


    @SuppressLint("WrongConstant")
    public static void openFile(Context activity, File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        activity.startActivity(intent);
    }

    @SuppressLint({"DefaultLocale"})
    public static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        } else {
            String end = fName.substring(dotIndex, fName.length()).toLowerCase();
            if (end == "") {
                return type;
            } else {
                for (int i = 0; i < MIME_MapTable.length; ++i) {
                    if (end.equals(MIME_MapTable[i][0])) {
                        type = MIME_MapTable[i][1];
                    }
                }

                return type;
            }
        }
    }


    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }


    public static String getDownloadPath(String fileName) {
        String path = getRootPath() + "download/";
        switch (getFileTypeIcon(fileName)) {
            case 0:
            case 8:
                path = path + "other/";
                break;
            case 1:
                path = path + "zip/";
                break;
            case 2:
                path = path + "mp3/";
                break;
            case 3:
            case 5:
            case 6:
            case 7:
            case 9:
            case 11:
            case 12:
                path = path + "txt/";
                break;
            case 4:
                path = path + "image/";
                break;
            case 10:
                path = path + "video/";
                break;
            case 13:
                path = path + "apk/";
                break;
        }

        return path;
    }


    /**
     * 获取文件后缀
     *
     * @param fileName 文件名
     * @return
     */
    public static String getFileType(String fileName) {
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return "";
        }
        /* 获取文件的后缀名 */
        String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        if (end == "") {
            return "";
        }
        return end;
    }

    /**
     * 根据文件名获得对应的文件类型。
     *
     * @param
     */
    @SuppressLint("DefaultLocale")
    public static int getFileTypeIcon(String fName) {
        int iconLevel = 0;

        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            iconLevel = 0;
            return iconLevel;
        }

        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") {
            iconLevel = 0;
            return iconLevel;
        }

        // 获取文件的Icon
        for (int i = 0; i < FILE_ICON_Table.length; i++) {
            for (int j = 0; j < FILE_ICON_Table[i].length; j++) {
                if (end.equals(FILE_ICON_Table[i][j])) {
                    iconLevel = i;
                }
            }
        }

        return iconLevel;
    }

    public static final String[][] FILE_ICON_Table = {
            // {后缀名，文件类型}
            {""}, // 0 -- file_default_icon
            {".7z", ".gtar", ".gz", ".jar", ".rar", ".tar", ".tgz", ".zip"}, // 1
            // --
            // file_compress_icon
            {".m3u", ".m4a", ".m4b", ".mp2", ".mp3", ".mpga", ".ogg", ".wav", ".wma", ".ra", ".mid", ".ape", ".flac", ".amr"}, // 2
            // --
            // file_audio_icon
            {".doc", ".docx"}, // 3 -- file_doc_icon
            {".bmp", ".gif", ".jpeg", ".jpg", ".png"}, // 4 -- file_image_icon
            {".pdf"}, // 5 -- file_pdf_icon
            {".pps", ".ppt", ".pptx"}, // 6 -- file_ppt_icon
            {".pst"}, // 7 -- file_pdf_icon
            {".bin", ".conf", ".class", ".exe", ".prop", "properties", ".mpc", ".sh", ".rc"}, // 8
            // --
            // file_setting_icon
            {".c", ".cpp", ".h", ".java", ".log", ".msg", ".rtf", ".txt", ".js", ".xml"}, // 9
            // --
            // file_txt_icon
            {".3gp", ".asf", ".avi", ".m4u", ".m4v", ".mov", ".mp4", ".mkv", ".mpe", ".mpeg", ".mpg", ".mpg4",
                    ".rmvb", ".rm", ".flv", ".wmv", ".vob"}, // 10 --
            // file_video_icon
            {".htm", ".html"}, // 11 -- file_web_icon
            {".xls", ".xlsx"}, // 12 -- file_xls_icon
            {".apk"}, // 13 -- apk
    };

}
