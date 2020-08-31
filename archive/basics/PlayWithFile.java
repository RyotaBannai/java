import java.io.File;

public class PlayWithFile {
  public static void main(String args[]) {
    File dir = new File("./"); // 相対パスだとファイルの一ではなく、実行する位置
    System.out.println(dir.isDirectory());

    File filelist[] = dir.listFiles();

    for (int i = 0; i < filelist.length; i++) {
      System.out.println(filelist[i]);
    }
  }
}