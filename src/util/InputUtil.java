package util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);


    public static String getText(String label) {
        do{
            ViewUtil.print(label + "-> ", false);
            String s = scanner.nextLine();
            if(s.isEmpty()){
                ViewUtil.printHeader("Invalid Options.");
                continue;
            }
            return s;
        }while (true);

    }

    public static Integer getInteger(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                int num = Integer.parseInt(scanner.nextLine()) ;
                if(num >= 0  ){
                    return num;
                }
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

    public static Double getDouble(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                Double num = Double.parseDouble(scanner.nextLine());
                if(num >= 0 && num <= 100){
                    return num;
                }else {
                    System.out.printf("Invalid score range ( 0-100 ) ! ");
                }
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

}
