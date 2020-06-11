import java.io.File;

public class Main {
    public static void main(String[] args) {

        File file = new File("F:\\迅雷下载\\临时文件夹");
//        检查所有文件夹内所有文件
//        CheckFilesUtil.check(file);
//        CheckFilesUtil.logByNum();

//        更改hash值
        AntiHash.antiHash(file,"Power");

//        更改文件夹内文件的名字
        FileRenameUtil.rename(file);
        FileRenameUtil.logByNum();

//        还原被改名的文件
//        FileReductionRenameUtil.reductionRename(file);
//        FileReductionRenameUtil.logByNum();

    }
}
