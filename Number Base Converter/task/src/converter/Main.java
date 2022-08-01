package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);
    private static String fractional;
    private static BigDecimal fraction;
    private static BigInteger integer;


    public static void main(String[] args) {
        runConverter();
    }

    public static void runConverter() {

        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String source = SCANNER.next();
            if (source.equals("/exit")) break;
            String target = SCANNER.next();

            while (true) {
                System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ", source, target);
                String number = SCANNER.next();
                if (number.equals("/back")) break;
                if (number.contains(".")) {
                    
                    fractional = number.split("\\.")[1];
                    number = number.split("\\.")[0];
                    
                    //convert to decimal
                    fraction = Converter.fractionToDecimal(fractional.toCharArray(), Integer.parseInt(source));
                    integer = number.equals("0") ? BigInteger.ZERO : Converter.toDeci(number, Integer.parseInt(source));

                    //convert from decimal
                    String fractionalResult = String.valueOf(Converter.toRadix(fraction, Integer.parseInt(target), 5));
                    String integerResult = number.equals("0") ? "0" : Converter.fromDeci(Integer.parseInt(target), integer);

                    System.out.println("Conversion result: " + integerResult + "." + fractionalResult);     
                    
                } else {
                    
                    integer = Converter.toDeci(number, Integer.parseInt(source));
                    System.out.println("Conversion result: " + Converter.fromDeci(Integer.parseInt(target), integer));
                    
                }

                System.out.println();

            }
        }


    }


}
