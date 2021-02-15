package gvsucis;

/**
 * @author Ummayair Ahmad
 * @author Zachary Kumas (Provided Skeleton Code)
 */

public class BaseConverter {

    private static final String HEXSYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";


    /**
     * Convert a lower-case {@code char} to its corresponding integer value (assuming 'a' -> 10, 'b' -> 11, etc.)
     *
     * @param c The input {@code char}
     * @return the corresponding integer value.
     */
    public static int charToValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else {
            return (c - 'a') + 10;
        }
    }

    public static char replaceChars(int num){
        if (num >= 0 && num <= 9)
            return (char)(num + 48);
        else
            return (char)(num - 10 + 65);
    }



    public int stringToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result *= 10;
            result += s.charAt(i) - '0';

        }
        return result;
    }

    public String decimal2hex(String input) {
        int temp = stringToInt(input);
        return Integer.toHexString(temp);

    }

    /** Method that converts base 10 to base 2 **/
    public String convertDec2Binary(String input) {
        int temp = stringToInt(input);
        return Integer.toBinaryString(temp);
    }

    /** Method that converts any base to base 10  **/
    public String convertToDec(String input, int base){
      int result = 0;
      int pos = input.length();
      for(char ch : input.toCharArray()){
         int value = HEXSYMBOLS.indexOf(ch);
         result += value * pow(base,--pos);
      }
      String converted = Integer.toString(result);
      return converted;
   }

    /** Method that converts a base 10 number to any given base.  **/
    static String convert10tox(String input, int base1)
    {
        BaseConverter temp = new BaseConverter();
        int inputNum = temp.stringToInt(input);
        String s = "";
        while (inputNum > 0)
        {
            s += replaceChars(inputNum % base1);
            inputNum /= base1;
        }
        StringBuilder a = new StringBuilder();
        a.append(s);
        String converted = new String(a.reverse());
        return converted.toLowerCase();
    }

    /** Unused Helper method **/
    private static long pow(int value, int x) {
        if (x == 0) {
            return 1;
        }
        return value * pow(value, x - 1);
    }







    /**
     * Converts the given {@code input} from {@code base_in} to {@code base_out}.
     *
     * @param input    the number to be converted
     * @param base_in  the base of the given number
     * @param base_out the desired base of the output
     * @return the number {@code input} represented in {@code base_out}
     * @throws IllegalArgumentException if either (1) {@code input} is not a valid {@code base_in} number,
     *                                  (2) either {@code base_in} or {@code base_out} are not in the range {@code [2, 36]}
     */


    public static String convert(String input, int base_in, int base_out) {
        String result = "";
        BaseConverter tempObj = new BaseConverter();

        if(input == "0")
            return "0";

        if (base_in < 2 || base_in > 36) {
            throw new IllegalArgumentException("Base must be in the range [0, 36]");
        }

        if (base_out < 2 || base_out > 36) {
            throw new IllegalArgumentException("Base must be in the range [0, 36]");
        }

        if (base_in == 10 && base_out > 10) {
            return convert10tox(input,base_out);
        }

        if (base_in != 10) {
            String num = tempObj.convertToDec(input, base_in);
            if (base_out == 10) {
                return tempObj.convertToDec(input, base_in);
            }
            if (base_out > 10) {
                //return convert10tox(num,base_out);
                return convert10tox(num,base_out);
            }
            if(base_out == 2){
                return tempObj.convertDec2Binary(num);
            }
            return convert10tox(num, base_out);

        } else {
            if (base_out == 2) {
                return tempObj.convertDec2Binary(input);
            }


            return convert10tox(input, base_out);
        }

    }

    public static void main(String[] args) {
        BaseConverter temp = new BaseConverter();
        String bob = "";
        String g = "G";
        int t = temp.stringToInt(g);


        System.out.println("*******************************");

        /**Function Testing**/


        System.out.println(temp.stringToInt(g));
        System.out.println(temp.convertToDec("g",17));
        System.out.println(Integer.toBinaryString(t));
        System.out.println(convert("g",17,2));
        System.out.println(temp.convertToDec("h",17));







    }
}
