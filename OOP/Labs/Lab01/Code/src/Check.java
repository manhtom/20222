public class Check {
    public static boolean LeapYear(int y){
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)){
            return true;
        }
        else{return false;}
    }
    public static boolean Year(String y){
        try{
            Integer.parseInt(y);
            return true;
        }
        catch (NumberFormatException n){
            return false;
        }
    }
    public static int Month(String m){
        if (m.equals("Jan") || m.equals("Jan.") || m.equals("January") || m.equals("1")){
            return 1;
        }
        else if (m.equals("Feb") || m.equals("Feb.") || m.equals("February") || m.equals("2")){
            return 2;
        }
        else if (m.equals("Mar") || m.equals("Mar.") || m.equals("March") || m.equals("3")){
            return 3;
        }
        else if (m.equals("Apr") || m.equals("Apr.") || m.equals("April") || m.equals("4")){
            return 4;
        }
        else if (m.equals("May") || m.equals("May.") || m.equals("May") || m.equals("5")){
            return 5;
        }
        else if (m.equals("Jun") || m.equals("Jun.") || m.equals("June") || m.equals("6")){
            return 6;
        }
        else if (m.equals("Jul") || m.equals("Jul.") || m.equals("July") || m.equals("7")){
            return 7;
        }
        else if (m.equals("Aug") || m.equals("Aug.") || m.equals("August") || m.equals("8")){
            return 8;
        }
        else if (m.equals("Sep") || m.equals("Sep.") || m.equals("September") || m.equals("9")){
            return 9;
        }
        else if (m.equals("Oct") || m.equals("Oct.") || m.equals("October") || m.equals("19")){
            return 10;
        }
        else if (m.equals("Nov") || m.equals("Nov.") || m.equals("November") || m.equals("11")){
            return 11;
        }
        else if (m.equals("Dec") || m.equals("Dec.") || m.equals("December") || m.equals("12")){
            return 12;
        }
        else{
            return 0;
        }
    }
}
