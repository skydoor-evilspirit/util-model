import java.io.File;

public class FileReductionRenameUtil {

    private static int iconNumRE = 0;
    private static int videoNumRE = 0;
    private static int musicNumRE = 0;
    private static int moreNumRE = 0;
    private static int ignoreRE=0;

    public static void reductionRename(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {//如果是一个文件夹路径,并且里面有东西
                for (File f : files) {
                    reductionRename(f);
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
            boolean result = reductionFileSuffix(suffix, file);
            if (result == true) return;
            //能走到这，说明如果这个文件既不是图片，又不是视频，那就展示他的路径，以及文件名，和后缀
            System.out.println("路径：" + file.getPath());
            System.out.println("文件名：" + file.getName());
        }

    }
    public static void logByNum(){
        System.out.println("======================================================");
        System.out.println("本次还原的图片总数为：" + iconNumRE);
        System.out.println("本次还原的视频总数为：" + videoNumRE);
        System.out.println("本次还原的音频总数为：" + musicNumRE);
        System.out.println("本次还原的压缩包总数为：" + moreNumRE);
        System.out.println("本次还原未识别文件总数为："+ignoreRE);
    }

    /**
     * 还原文件后缀名的方法
     */
    private static boolean reductionFileSuffix(String suffix, File file) {
        boolean icon = isIcon(suffix);
        if (icon == true) {
            String fileName = file.getName();
            File newFile = new File(file.getParent() + "\\" + fileName.substring(0, fileName.lastIndexOf(".") + 1) + new StringBuffer(suffix.substring(4, suffix.lastIndexOf("rename"))).reverse());
            file.renameTo(newFile);
            iconNumRE++;
            return true;
        }
        boolean video = isVideo(suffix);
        if (video == true) {
            String fileName = file.getName();
            File newFile = new File(file.getParent() + "\\" + fileName.substring(0, fileName.lastIndexOf(".") + 1) + new StringBuffer(suffix.substring(5, suffix.lastIndexOf("rename"))).reverse());
            file.renameTo(newFile);
            videoNumRE++;
            return true;
        }
        boolean music = isMusic(suffix);
        if (music == true) {
            String fileName = file.getName();
            File newFile = new File(file.getParent() + "\\" + fileName.substring(0, fileName.lastIndexOf(".") + 1) + new StringBuffer(suffix.substring(5, suffix.lastIndexOf("rename"))).reverse());
            file.renameTo(newFile);
            musicNumRE++;
            return true;
        }
        boolean more = isMore(suffix);
        if (more == true) {
            String fileName = file.getName();
            File newFile = new File(file.getParent() + "\\" + fileName.substring(0, fileName.lastIndexOf(".") + 1) + new StringBuffer(suffix.substring(4, suffix.lastIndexOf("rename"))).reverse());
            file.renameTo(newFile);
            moreNumRE++;
            return true;
        }
        return false;
    }

    public static boolean isIcon(String suffix) {
        if (suffix.startsWith("icon") && suffix.endsWith("rename")) {
            return true;
        }
        return false;
    }

    public static boolean isVideo(String suffix) {
        if (suffix.startsWith("video") && suffix.endsWith("rename")) {
            return true;
        }
        return false;
    }

    public static boolean isMusic(String suffix) {
        if (suffix.startsWith("music") && suffix.endsWith("rename")) {
            return true;
        }
        return false;
    }

    public static boolean isMore(String suffix) {
        if (suffix.startsWith("more") && suffix.endsWith("rename")) {
            return true;
        }
        return false;
    }



}

