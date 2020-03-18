import java.io.*;

public class AntiHash {

    public static void antiHash(File file, String massage) {
        if (file == null) return;

        if (file.isDirectory()) {//是一个文件夹
            File[] files = file.listFiles();
            if (files != null) {//里面有文件
                for (File f : files) {
                    antiHash(f, massage);
                }
            }
        } else {//是一个文件
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("\r\n"+massage);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
