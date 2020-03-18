import java.io.File;

public class Main {
    public static void main(String[] args) {

        File file = new File("G:\\迅雷下载\\上传目录");
//        CheckFilesUtil.check(file);
//        CheckFilesUtil.logByNum();

        AntiHash.antiHash(file,"Power");
//
        FileRenameUtil.rename(file);
        FileRenameUtil.logByNum();

//        FileReductionRenameUtil.reductionRename(file);
//        FileReductionRenameUtil.logByNum();

    }
}
