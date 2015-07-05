import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class StackThemUp {

    static String readLn(){  // utility function to read from stdin,

        int maxLength = 1<<20;
        // Provided by Programming-challenges, edit for style only
        byte line[] = new byte [maxLength];
        int length = 0;
        int input = -1;
        try{
            while (length < maxLength){//Read untill maxlength
                input = System.in.read();
                if ((input < 0) || (input == '\n') || (input == '\r')) break; //or untill end of line ninput
                line [length++] += input;
            }

            if ((input < 0) && (length == 0)) return null;  // eof
            return new String(line, 0, length);
        }catch (IOException e){
            return null;
        }
    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        //readLn();//empty line

        while(t-- > 0){
            int n = sc.nextInt();

            int[][]tricks = new int[n][52];

            for(int i = 0; i < n; i++){
                //String[]ps = readLn().split("\\s+");
                for(int j = 0; j < 52; j++){
                    tricks[i][j] = sc.nextInt()-1;
                }
                //System.out.println(i + ":" + Arrays.toString(tricks[i]));
            }

            int[] cards = new int[52];
            int[] cardsNew = new int[52];
            for(int i = 0; i < cards.length; i++){
                cards[i] = i;
            }
            sc.nextLine();
            while(sc.hasNextLine()){
                String str = sc.nextLine();
                if(str.length() == 0)break;
                int trick = Integer.parseInt(str)-1;

                for(int i = 0; i < cards.length; i++){
                    cardsNew[i] = cards[tricks[trick][i]];
                }
                for(int i = 0; i < cards.length; i++){
                    cards[i] = cardsNew[i];
                }
                //System.out.println("trick {" + trick + "} " +  Arrays.toString(cards));
            }

            for(int i = 0; i < cards.length; i++){
                String suitStr = getSuit(cards[i]);
                String valueStr = getValue(cards[i]);

                System.out.println(valueStr + " of " + suitStr);
            }

            if(t > 0){
                System.out.println();
            }

        }
    }

    private static String getValue(int card) {
        String valueStr = "";
        int value = card % 13;
        if(value <= 8){
            valueStr = String.valueOf(value+2);
        } else if(value == 9){
            valueStr = "Jack";
        } else if(value == 10){
            valueStr = "Queen";
        } else if(value == 11){
            valueStr = "King";
        } else {
            valueStr = "Ace";
        }
        return valueStr;
    }

    private static String getSuit(int card) {
        int suit = card / 13;
        String suitStr = "";
        if(suit == 0){
            suitStr = "Clubs";
        } else if(suit == 1){
            suitStr = "Diamonds";
        } else if(suit == 2){
            suitStr = "Hearts";
        } else {
            suitStr = "Spades";
        }
        return suitStr;
    }

}
