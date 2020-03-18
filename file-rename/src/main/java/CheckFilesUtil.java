import java.io.File;

public class CheckFilesUtil {
    private static String[] iconSuffixC = {"bmp", "jpg", "png", "tif", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp", "jfif", "ico", "jpeg", "pdf"};
    private static String[] videoSuffixC = {"avi", "mp4", "mkv", "mov", "3gp", "rmvb", "rm", "flv", "f4v", "wmv", "ts", "kux", "mpg","webm"};
    private static String[] musicSuffixC = {"mp3", "m4a", "wma"};
    private static String[] moreSuffixC = {"rar","7z"};
    private static int iconNumC = 0;
    private static int videoNumC = 0;
    private static int musicNumC = 0;
    private static int moreNumC = 0;
    public static int ignoreIconNumC=0;
    public static int ignoreVideoNumC=0;
    public static int ignoreMusicNumC=0;
    public static int ignoreMoreNumC=0;

    public static void check(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {//如果是一个文件夹路径,并且里面有东西
                for (File f : files) {
                    check(f);
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
            boolean iconOrVideoOrMusicOrMore = CheckFilesUtil.isIconOrVideoOrMusicOrMore(suffix);
            if (iconOrVideoOrMusicOrMore==true)return;
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
            System.out.println("执行删除文件..."+file.getName());
            file.delete();
        }
    }
    public static void logByNum(){
        System.out.println("======================================================");
        System.out.println("本次搜索到的图片总数为："+iconNumC);
        System.out.println("本次搜索到的视频总数为："+videoNumC);
        System.out.println("本次搜索到的音频总数为："+musicNumC);
        System.out.println("本次搜索到的压缩包总数为："+moreNumC);
    }

    private static boolean checkSuffixIsIcon(String suffix, File file) {
        for (String s : iconSuffixC) {
            if (s.equals(suffix)) {
                //文件是图片类型的，接下来想进行什么操作？
                iconNumC++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsVideo(String suffix, File file) {
        for (String v : videoSuffixC) {
            if (v.equals(suffix)) {
                //文件是视频类型的，接下来想进行什么操作？
                videoNumC++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsMusic(String suffix, File file) {
        for (String m : musicSuffixC) {
            if (m.equals(suffix)) {
                ///文件是音频类型的，接下来想进行什么操作？
                musicNumC++;
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuffixIsMore(String suffix, File file) {
        for (String m : moreSuffixC) {
            if (m.equals(suffix)) {
                //文件是压缩包，或者txt类型的，接下来想进行什么操作？
                moreNumC++;
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
            CheckFilesUtil.ignoreIconNumC++;
            return true;
        }
        boolean video = FileReductionRenameUtil.isVideo(suffix);
        if (video == true) {
            CheckFilesUtil.ignoreVideoNumC++;
            return true;
        }
        boolean music = FileReductionRenameUtil.isMusic(suffix);
        if (music == true) {
            CheckFilesUtil.ignoreMusicNumC++;
            return true;
        }
        boolean more = FileReductionRenameUtil.isMore(suffix);
        if (more == true) {
            CheckFilesUtil.ignoreMoreNumC++;
            return true;
        }
        return false;
    }
}
