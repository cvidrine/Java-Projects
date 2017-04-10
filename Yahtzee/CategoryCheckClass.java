import java.util.Arrays;
 
 
public class CategoryCheckClass implements YahtzeeConstants {
     
 
    public boolean checkCategory(int[] dice, int category){
        switch(category){
        case THREE_OF_A_KIND: return checkThreeOfAKind(dice);
        case FOUR_OF_A_KIND: return checkFourOfAKind(dice);
        case FULL_HOUSE: return checkFullHouse(dice);
        case SMALL_STRAIGHT: return checkSmallStraight(dice);
        case LARGE_STRAIGHT: return checkLargeStraight(dice);
        case YAHTZEE: return checkYahtzee(dice);
        default: return false;
        }
    }
 
    private boolean checkThreeOfAKind(int[] dice){
        Arrays.sort(dice);
        int counter = 1;
        for(int i=1; i<5; i++){
            if(dice[i-1] == dice[i]){
                counter++;
                if (counter == 3) return true;
            } else {
                counter = 1;
            }
        }
        return false;
    }
 
    private boolean checkFourOfAKind(int[] dice){
        Arrays.sort(dice);
        int counter=1;
        for(int i=1; i<5; i++){
            if(dice[i-1] == dice[i]){
                counter++;
                if (counter == 4) return true;
            } else {
                counter=1;
            }
        }
        return false;
    }
 
    private boolean checkFullHouse(int[] dice){
        Arrays.sort(dice);
        int i=1;
        while(dice[i-1] == dice[i]){
            i++;
            if(i>3) return false;
        }
        for(int j=i; j<3; j++){
            if(dice[j] != dice[j+1]){
                return false;
            }
        }
        return true;
    }
 
    private boolean checkSmallStraight(int[] dice){
        Arrays.sort(dice);
        int counter=0;
        for(int i=1; i<5; i++){
            if(dice[i-1] == dice[i]){
                counter++;
                if(counter>1)return false;
                continue;
            }
            if(dice[i-1]+1 != dice[i]){
                if(i==1) continue;
                return false;
            }
        }
        return true;
    }
 
    private boolean checkLargeStraight(int[] dice){
        Arrays.sort(dice);
        for(int i=1; i<5; i++){
            if(dice[i-1]+1 !=  dice[i]){
                return false;
            }
        }
        return true;
    }
 
    private boolean checkYahtzee(int[] dice){
        for(int i=1; i<5; i++){
            if(dice[i-1] != dice[i]){
                return false;
            }
        }
        return true;
 
    }
}
