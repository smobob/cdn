import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FileRename {

    public static final String[] FILTER_SUFFIX = new String[]{".jpg", ".png"};

    public static void main(String[] args) {
        recursiveTraversalFolder(System.getProperty("user.dir"));
    }

    public static void recursiveTraversalFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("Folder is empty!");
                return;
            } else {
                File newDir;
                String newName;
                File parentPath;
                for (int i = 0; i < fileArr.length; i++) {
                    File file = fileArr[i];
                    if (file.isDirectory()) {
                        System.out.println("folder:" + file.getAbsolutePath() + ", continue recursion!");
                        recursiveTraversalFolder(file.getAbsolutePath());
                    } else {
                        String fileName = file.getName();
                        if (fileName.lastIndexOf(".") < 0) {
                            continue;
                        }
                        String suffix = fileName.substring(fileName.lastIndexOf("."));
                        Set<String> needFilterSuffix = new HashSet<>(Arrays.asList(FILTER_SUFFIX));
                        if (needFilterSuffix.contains(suffix.toLowerCase())) {
                            parentPath = file.getParentFile();
                            newName = UUID.randomUUID().toString().toUpperCase() + suffix;
                            newDir = new File(parentPath + "/" + newName);
                            boolean flag = file.renameTo(newDir);
                            if (flag) {
                                System.out.println("modified:" + newDir);
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("file does not exist!");
        }
    }
}