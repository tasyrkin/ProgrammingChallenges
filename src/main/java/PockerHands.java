import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class PockerHands {
    
    private static char[]values = "23456789TJQKA".toCharArray();
    private static char[]suits = "CDHS".toCharArray();
    
    private static int rankCard(char suit, char value){
        for(int i = 0; i < suits.length; i++){
            if(suits[i] == suit){
                for(int j = 0; j < values.length; j++){
                    if(values[j] == value){
                        return i*values.length + j;
                    }
                }
            }
        }
        throw new IllegalArgumentException(String.format("bad suit %s or value %s", suit, value));
    }
    
    private static char getValue(int card){
        return values[card%values.length];
    }
    private static Integer getPoint(int card){
        return card%values.length;
    }
    private static char getSuit(int card){
        return suits[card/values.length];
    }

    public static class CmpByPoints implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return getPoint(o1).compareTo(getPoint(o2));
        }
    }


    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Ranger[]rangers = {new StreetFlash(),
                           new Care(), 
                           new FullHouse(), 
                           new Flash(), 
                           new Street(), 
                           new Triple(), 
                           new TwoPairs(), 
                           new Pair(), 
                           new Precedence()};

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            //String str = sc.nextLine();
            //String [] ps = str.split("\\s+");
            
            Integer[]black = new Integer[5];
            for(int i = 0; i < 5; i++){
                //black[i] = rankCard(ps[i].charAt(1), ps[i].charAt(0));
                String card = sc.next();
                black[i] = rankCard(card.charAt(1), card.charAt(0));
            }
            Integer[]white = new Integer[5];
            for(int i = 0; i < 5; i++){
                //white[i-5] = rankCard(ps[i].charAt(1), ps[i].charAt(0));
                String card = sc.next();
                white[i] = rankCard(card.charAt(1), card.charAt(0));
            }
            
            Arrays.sort(black, new CmpByPoints());
            Arrays.sort(white, new CmpByPoints());
            
            Long blackRange = null;
            Long whiteRange = null;
            
            for(int i = 0; i < rangers.length; i++){
                if(blackRange == null){
                    blackRange = rangers[i].range(black);
                    //if(blackRange != null)System.out.println("black:" + rangers[i].getClass());
                }
                if(whiteRange == null){
                    whiteRange = rangers[i].range(white);
                    //if(whiteRange != null)System.out.println("white:" + rangers[i].getClass());
                }
            }

            //System.out.println("blackRange: " + blackRange + ", whiteRange: " + whiteRange);
            
            if(blackRange > whiteRange){
                System.out.println("Black wins.");
            } else if(blackRange < whiteRange){
                System.out.println("White wins.");
            } else {
                System.out.println("Tie.");
            }

        }
    }

    private static interface Ranger {
        Long range(Integer[]cards);
    }

    private static class Precedence implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            return arrange(getPoint(cards[0]), getPoint(cards[1]), getPoint(cards[2]), getPoint(cards[3]), getPoint(cards[4]));
        }
    }
    
    private static class Pair implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            List<Integer> pairs = getSame(cards, 2);
            if(pairs.size() == 1){
                int val1 = -1;
                int val2 = -1;
                int val3 = -1;
                for(int i = 0; i < cards.length; i++){
                    int point = getPoint(cards[i]); 
                    if(!pairs.contains(point)){
                        if(val1 == -1)val1 = point;
                        else if(val2 == -1)val2 = point;
                        else if(val3 == -1)val3 = point;
                    }
                }
                return pow(11) + arrange(val1, val2, val3, pairs.get(0));
            }

            return null;
        }
    }
    
    private static class TwoPairs implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            List<Integer> pairs = getSame(cards, 2);
            if(pairs.size() == 2){
                Collections.sort(pairs);
                int rest = 0;
                for(int i = 0; i < cards.length; i++){
                    if(!pairs.contains(getPoint(cards[i]))){
                        rest = getPoint(cards[i]);
                        break;
                    }
                }
                return pow(12) + arrange(rest, pairs.get(0), pairs.get(1));
            }
            return null;
        }
    }

    private static class Triple implements Ranger{
        @Override
        public Long range(Integer[] cards) {
            List<Integer> triple = getSame(cards, 3);
            if(!triple.isEmpty()){
                return pow(13) + triple.get(0);
            }
            return null;
        }
    }

    private static class Street implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            if(!isConseq(cards)){
                return null;
            }
            return pow(14) + getPoint(cards[cards.length-1]);
        }
    }
    
    private static class Flash implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            if(!isAllSuitsSame(cards)){
                return null;
            }
            return pow(15) + getPoint(cards[cards.length-1]);
        }
    }
    
    private static class FullHouse implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            List<Integer> triple = getSame(cards, 3);
            List<Integer> pair = getSame(cards, 2);
            if(!triple.isEmpty() && !pair.isEmpty()){
                return pow(16) + triple.get(0);
            }
            return null;
        }
    }
    
    private static class Care implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            List<Integer> list = getSame(cards, 4);
            if(!list.isEmpty()){
                return pow(17) + list.get(0);
            }
            
            return null;
        }
    }
    
    private static class StreetFlash implements Ranger{

        @Override
        public Long range(Integer[] cards) {
            
            if(!isAllSuitsSame(cards) || !isConseq(cards)){
                return null;
            }
            
            return pow(18) + getPoint(cards[cards.length - 1]);
        }
    }

    private static LinkedList<Integer> getSame(Integer[]cards, int amount){
        int[] mas = new int[14];
        for(int i = 0; i < cards.length; i++){
            mas[getPoint(cards[i])]++;
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < mas.length; i++){
            if(mas[i] == amount){
                list.add(i);
            }
        }
        return list;
    }

    private static Long arrange(Integer... nums){
        long mul = 1;
        long res = 0;
        for(Integer num : nums){
            res += num*mul;
            mul *= 100;
        }
        return res;
    }
    
    private static boolean isConseq(Integer[]cards){
        for(int i = 0; i < cards.length-1; i++){
            int diff = getPoint(cards[i + 1]) - getPoint(cards[i]);
            if(diff != 1){
                return false;
            }
        }
        return true;
    }
    
    private static boolean isAllSuitsSame(Integer[]cards){
        Character suit = getSuit(cards[0]);
        for(int i = 0; i < cards.length; i++){
            if(getSuit(cards[i]) != suit){
                return false;
            }
        }
        return true;
    }
    
    private static Long pow(int place){
        long res = 1;
        while(place-- > 0){
            res *= 10;
        }
        return res;
    }
}
