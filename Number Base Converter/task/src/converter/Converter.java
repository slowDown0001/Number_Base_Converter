package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Converter {

    // To return char for a value. For
    // example '2' is returned for 2.
    // 'A' is returned for 10. 'B' for 11
    static char reVal(int num) {
        if (num >= 0 && num <= 9)
            return (char) (num + 48);
        else
            return (char) (num - 10 + 65);
    }

    // To return value of a char.
    // For example, 2 is returned
    // for '2'. 10 is returned
    // for 'A', 11 for 'B'
    static int val(char c) {
        if (c >= '0' && c <= '9')
            return (int) c - '0';
        else {
            c = Character.toUpperCase(c);
            return (int) c - 'A' + 10;
        }
    }


    // Function to convert a given decimal number
    // to a base 'base' and
    static String fromDeci(int base1, BigInteger inputNum) {
        String s = "";

        // Convert input number is given
        // base by repeatedly dividing it
        // by base and taking remainder
        while (inputNum.compareTo(BigInteger.ZERO) > 0) {
            s += reVal(inputNum.remainder(BigInteger.valueOf(base1)).intValue());
            inputNum = inputNum.divide(BigInteger.valueOf(base1));
        }
        StringBuilder ix = new StringBuilder();

        // append a string into StringBuilder input1
        ix.append(s);

        // Reverse the result
        return new String(ix.reverse());
    }


    public static char[] toRadix(BigDecimal frac, int radix, int precs) {

        //Only fractional part is accepted here.

        int i;
        char[] res = new char[precs]; //For storing result.
        for (i = 0; i < precs && frac.compareTo(BigDecimal.ZERO) >= -1; i++) {
            frac = frac.multiply(BigDecimal.valueOf(radix));


            res[i] = reVal(frac.intValue());


            if (frac.compareTo(BigDecimal.ONE) >= -1) {
                frac = frac.subtract(new BigDecimal(frac.intValue()));

            }
        }

        return res;
    }


    // Function to convert a
    // number from given base
    // 'b' to decimal
    static BigInteger toDeci(String number, int base) {
        int len = number.length();
        BigInteger power = BigInteger.ONE; // Initialize
        // power of base
        BigInteger num = BigInteger.ZERO; // Initialize result
        int i;

        // Decimal equivalent is
        // str[len-1]*1 + str[len-2] *
        // base + str[len-3]*(base^2) + ...
        for (i = len - 1; i >= 0; i--) {
            // A digit in input number
            // must be less than
            // number's base
            if (val(number.charAt(i)) >= base) {
                System.out.println("Invalid Number");
                return BigInteger.valueOf(-1);
            }

            num = num.add(power.multiply(BigInteger.valueOf(val(number.charAt(i)))));
            power = power.multiply(BigInteger.valueOf(base));
        }

        return num;
    }


    //https://stackoverflow.com/questions/21058476/change-of-base-for-fractional-numbers-in-on-time
    public static BigDecimal fractionToDecimal(char[] frac, int radix) {

        BigDecimal res = BigDecimal.ZERO;
        BigDecimal fac = BigDecimal.valueOf(1.0f / radix);


        int i, p = frac.length;
        for (i = 0; i < p; i++) {
            res = res.add(fac.multiply(new BigDecimal(val(frac[i]))));


            fac = fac.divide(BigDecimal.valueOf(radix), 10, RoundingMode.CEILING);

        }
        return res.setScale(5, RoundingMode.FLOOR);
    }


}

