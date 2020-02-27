import java.io.File;

public class FileRenameUtil {

    private static String[] iconSuffixR = {"bmp", "jpg", "png", "tif", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp", "jfif", "ico", "jpeg", "pdf"};
    private static String[] videoSuffixR = {"avi", "mp4", "mkv", "mov", "3gp", "rmvb", "rm", "flv", "f4v", "wmv", "ts", "kux", "mpg"};
    private static String[] musicSuffixR = {"mp3", "m4a", "wma"};
    private static String[] moreSuffixR = {"rar", "7z","zip"};
    private static int iconNumR = 0;
    private static int videoNumR = 0;
    private static int musicNumR = 0;
    private static int moreNumR = 0;
    private static int ignoreIconNumR = 0;
    private static int ignoreVideoNumR = 0;
    private static int ignoreMusicNumR = 0;
    private static int ignoreMoreNumR = 0;

    public static void rename(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {//如果是一个文件夹路径,并且里面有东西
                for (File f : files) {
                    rename(f);
                }
            }
        } else {//如果是一个文件路径
            String fileName = file.getName();
            if (fileName.lastIndexOf(".") == -1) {//文件没后缀,删除文件
                file.delete();
                return;
            }
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            //判断文件后缀是torrent格式吗？是的话删除
            if ("torrent".equals(suffix) || "url".equals(suffix)) {
                file.delete();
                return;
            }
            //判断文件后缀是不是已经修改过的后缀，如果是，则跳过
            boolean iconOrVideoOrMusicOrMore = FileRenameUtil.isIconOrVideoOrMusicOrMore(suffix);
            if (iconOrVideoOrMusicOrMore == true) return;
            //判断文件后缀是图片格式吗？如果是，改名
            boolean icon = checkSuffixIsIcon(suffix, file);
            if (icon == true) return;
            //判断文件后缀是视频格式吗？如果是，改名
            boolean video = checkSuffixIsVideo(suffix, file);
            if (video == true) return;
            //判断文件后缀是不是一些音频，如果是，则跳过
            boolean music = checkSuffixIsMusic(suffix, file);
            if (music == true) return;
            //判断文件是不是一些压缩包，txt等多余格式的，如果是，则跳过
            boolean more = checkSuffixIsMore(suffix, file);
            if (more == true) return;
            //能走到这，说明如果这个文件既不是图片，又不是视频，那就展示他的路径，以及文件名，和后缀
            System.out.println("路径：" + file.getPath());
            System.out.println("文件名：" + file.getName());
        }
    }

    public static void logByNum() {
        System.out.println("======================================================");
        System.out.println("本次更改的图片总数为：" + iconNumR);
        System.out.println("本次更改的视频总数为：" + videoNumR);
        System.out.println("本次更改的音频总数为：" + musicNumR);
        System.out.println("本次更改的压缩包总数为：" + moreNumR);
        System.out.println("======================================================");
        System.out.println("本次忽略的图片总数为：" + ignoreIconNumR);
        System.out.println("本次忽略的视频总数为：" + ignoreVideoNumR);
        System.out.println("本次忽略的音频总数为：" + ignoreMusicNumR);
        System.out.println("本次忽略的压缩包总数为：" + ignoreMoreNumR);
    }

    private static boolean checkSuffixIsIcon(String suffix, File file) {
        for (String s : iconSuffixR) {
            if (s.equals(suffix)) {
                //这个文件就是这个后缀的，改名字
                file.renameTo(new File(file.getParent() + "\\" + new StringBuffer(file.getName().substring(0, file.getName().lastIndexOf("."))).reverse().toString() + ".icon" + new StringBuffer(suffix).reverse().toString() + "rename"));
                iconNumR++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsVideo(String suffix, File file) {
        for (String v : videoSuffixR) {
            if (v.equals(suffix)) {
                file.renameTo(new File(file.getParent() + "\\" +new StringBuffer(file.getName().substring(0, file.getName().lastIndexOf("."))).reverse().toString() + ".video" + new StringBuffer(suffix).reverse().toString() + "rename"));
                videoNumR++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsMusic(String suffix, File file) {
        for (String m : musicSuffixR) {
            if (m.equals(suffix)) {
                //这个文件就是这个后缀的，改名字
                file.renameTo(new File(file.getParent() + "\\" +new StringBuffer(file.getName().substring(0, file.getName().lastIndexOf("."))).reverse().toString() + ".music" + new StringBuffer(suffix).reverse().toString() + "rename"));
                musicNumR++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsMore(String suffix, File file) {
        for (String m : moreSuffixR) {
            if (m.equals(suffix)) {
                //这个文件就是这个后缀的，跳过
                file.renameTo(new File(file.getParent() + "\\" + new StringBuffer(file.getName().substring(0, file.getName().lastIndexOf("."))).reverse().toString() + ".more" + new StringBuffer(suffix).reverse().toString() + "rename"));
                moreNumR++;
                return true;
            }
        }
        return false;
    }

    /**
     * 检查并计数，文件是不是已经是被修改过的文件了
     */
    public static boolean isIconOrVideoOrMusicOrMore(String suffix) {
        boolean icon = FileReductionRenameUtil.isIcon(suffix);
        if (icon == true) {
            FileRenameUtil.ignoreIconNumR++;
            return true;
        }
        boolean video = FileReductionRenameUtil.isVideo(suffix);
        if (video == true) {
            FileRenameUtil.ignoreVideoNumR++;
            return true;
        }
        boolean music = FileReductionRenameUtil.isMusic(suffix);
        if (music == true) {
            FileRenameUtil.ignoreMusicNumR++;
            return true;
        }
        boolean more = FileReductionRenameUtil.isMore(suffix);
        if (more == true) {
            FileRenameUtil.ignoreMoreNumR++;
            return true;
        }
        return false;
    }
}
