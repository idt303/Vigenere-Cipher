import java.util.Scanner;
public class VigenereCipher {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Getting user input
        System.out.println("Please enter the text: ");
        String input = scanner.nextLine();

        String key;


        //Getting user action
        boolean isEncrypting = false;
        int choice = 0; // from int to string
        //write 1 for encrypt and 2 for decrypt
        while ((choice != 1 && choice != 2)) {
            System.out.println("Do you want to encrypt (1) or decrypt (2)?");
            choice = scanner.nextInt();
            if (choice == 1)
                isEncrypting = true;
            else if (choice == 2)
                isEncrypting = false;
            else
                System.out.println("Please enter the action you want to preform (1 for encrypt or 2 for decrypt)");
        }
        System.out.println("Please enter the key: ");
        scanner.nextLine();
        key = scanner.nextLine().toLowerCase();

        String output;

        //Performing the given action
        if (isEncrypting)
            output = encrypt(input, key);
        else
            output = decrypt(input, key);
        System.out.println("Output: ");
        System.out.println(output);
    }

    public static String encrypt(String str, String key)
    {
        //Adding 'Z' to the string if it length is shorter than the key
        for (int i = str.length(); i < key.length(); i++){
            str += "Z";
        }
        String output = "";

        int j = 0;
        // j is the index for the key
        // and i is the index for the str
        // note that j doesn't change when a special letter is obtained
        for (int i = 0; i < str.length(); i++) {
            //Special letter case. the encryption skips this kind of letters
            if (str.charAt(i) == ' ' || str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == ','
                    || str.charAt(i) == '(' || str.charAt(i) == ')') {
                output += str.charAt(i);
            }
            else {
                //Checking if the letter is a capital letter
                boolean isCapital;
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90)
                    isCapital = true;
                else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    isCapital = false;
                else
                    return "Unsupported letter error: " + str.charAt(i);

                //Adding the key to the string in the current letter
                output += isCapital ? (char) ((str.charAt(i) + key.charAt(j % key.length()) - 'A' -'a') % 26 + 'A')
                        :(char)((str.charAt(i) + key.charAt(j % key.length()) - 2 * 'a') % 26 + 'a');
                //Note that we uses key.charAt(j % key.length()) in order to repeat the key if it's shorter than the string
                j++;
            }
        }
        return output;
    }
    public static String decrypt(String str, String key)
    {

        //Adding 'Z' to the string if it length is shorter than the key
        for (int i = str.length(); i < key.length(); i++){
            str += "Z";
        }
        String output = "";
        int j = 0;
        // j is the index for the key
        // and i is the index for the str
        // note that j doesn't change when a special letter is obtained
        for (int i = 0; i < str.length(); i++) {
            //Special letter case. the encryption skips this kind of letters
            if (str.charAt(i) == ' ' || str.charAt(i) == '.' || str.charAt(i) == '!'|| str.charAt(i) == ','
                    || str.charAt(i) == '(' || str.charAt(i) == ')') {
                output += str.charAt(i);
            }
            else {
                boolean isCapital;
                //Checking if the letter is a capital letter
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90)
                    isCapital = true;
                else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    isCapital = false;
                else
                    return "Unsupported letter error: " + str.charAt(i);
                int difference = (str.charAt(i)+"").toLowerCase().charAt(0) - (key.charAt(j % key.length())+"").toLowerCase().charAt(0);
                //If we subtract a letter that is larger than the current letter
                //we want to continue backwards from z back to a
                //The (str.charAt(index)+"").toLowerCase().charAt(0) is a tricky way of using toLowerCase
                //on chars. we convert it to string by + "" then we use toLowerCase function
                //and then because we only have one letter we can convert it back to char
                // using charAt(0)
                if (difference < 0) { //The case were we go backwards from z to a
                    output += isCapital ? (char) ('Z' + difference + 1) : (char) ('z' + difference + 1);
                } else
                    output += isCapital ? (char) (difference + 'A') : (char) (difference + 'a');
                j++;
            }
        }
        return output;
    }

}
