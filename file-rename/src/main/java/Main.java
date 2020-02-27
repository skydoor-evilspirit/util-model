import java.io.File;

public class Main {
    public static void main(String[] args) {

        File file = new File("G:\\迅雷下载\\美女与野兽");
//        CheckFilesUtil.check(file);
//        CheckFilesUtil.logByNum();
//
        FileRenameUtil.rename(file);
        FileRenameUtil.logByNum();
//
//        FileReductionRenameUtil.reductionRename(file);
//        FileReductionRenameUtil.logByNum();

    }
}
