package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjie
 * @date 2014/7/12
 */
public class Converter {
    public static void main(String[] args) throws Exception {
        String srcPath = "";
        String dstPath = "";

        File src = new File(srcPath);
        File dst = new File(dstPath);

        Converter converter = new Converter();
//        converter.convert(src, dst);
        converter.convertOneLine("route 0.0.0.0 0.0.0.0 vpn_gateway");
    }

//    public static final Pattern pattern = Pattern.compile("route ([0-9\\.]+) ([0-9\\.]+) (vpn|net)_gateway");
    public static final Pattern pattern = Pattern.compile("([0-9])");

    private void convert(File src, File dst) throws Exception {

        Scanner scanner = new Scanner(src);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

        }
    }

    private String convertOneLine(String line) {
        Matcher matcher = pattern.matcher(line);
        matcher.lookingAt();
        System.out.println(matcher.groupCount());
        for (int i = 0; i <= matcher.groupCount(); i++) {
            System.out.println("matched: " + matcher.group(i));
        }
        return null;
    }


}
