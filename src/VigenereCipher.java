import java.util.Scanner;

public class VigenereCipher {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the text: ");
        String input = scanner.nextLine();
        String key;

        boolean isEncrypting = false;
        String choice = "";
        while (!(choice.equals("encrypt") || choice.equals("decrypt"))) {
            System.out.println("Do you want to encrypt or decrypt?");
            choice = (scanner.nextLine()).toLowerCase();
            if (choice.equals("encrypt"))
                isEncrypting = true;
            else if (choice.equals("decrypt"))
                isEncrypting = false;
            else
                System.out.println("Please enter the action you want to preform (encrypt/decrypt)");
        }

        System.out.println("Please enter the key: ");
        key = scanner.nextLine().toLowerCase();

        String output;

        if (isEncrypting)
            output = encrypt(input, key);
        else
            output = decrypt(input, key);

        System.out.println("Output: ");
        System.out.println(output);
    }

    public static String encrypt(String str, String key)
    {
        for (int i = str.length(); i < key.length(); i++){
            str += "Z";
        }
        String output = "";

        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                output += " ";
            }
            else if (str.charAt(i) == '.')
                output += ".";
            else if (str.charAt(i) == '!')
                output += "!";
            else {
                boolean isCapital;
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90)
                    isCapital = true;
                else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    isCapital = false;
                else
                    return "Unsupported letter error: " + str.charAt(i);

                output += isCapital ? (char) ((str.charAt(i) + key.charAt(j % key.length()) - 2 * 'A') % 26 + 'A')
                        : (char)((str.charAt(i) + key.charAt(j % key.length()) - 2 * 'a') % 26 + 'a');
                j++;
            }
        }

        return output;
    }
    public static String decrypt(String str, String key)
    {

        for (int i = str.length(); i < key.length(); i++){
            str += "Z";
        }
        String output = "";
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                output += " ";
            else if (str.charAt(i) == '.')
                output += ".";
            else if (str.charAt(i) == '!')
                output += "!";
            else {
                int difference = (str.charAt(i)+"").toLowerCase().charAt(0) - (key.charAt(j % key.length())+"").toLowerCase().charAt(0);
                boolean isCapital;
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90)
                    isCapital = true;
                else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    isCapital = false;
                else
                    return "Unsupported letter error: " + str.charAt(i);
                if (difference < 0) {
                    output += isCapital ? (char) ('Z' + difference + 1) : (char) ('z' + difference + 1);
                } else
                    output += isCapital ? (char) (difference + 'A') : (char) (difference + 'a');
                j++;
            }
        }

        return output;
    }


}