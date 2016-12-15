package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */
public class AtoI {

    public static void main(String [] args) {
        String str = "1234355";

        System.out.println("number is " + atoi(str));
    }

    static int atoi(String str) {
        int number = 0;
        for (char a: str.toCharArray())
            number = number*10 + (a - '0');
        return number;
    }
}



//the following program with all the error check logic
//#include <stdio.h>
//    #include <string.h>
//
//    int my_atoi(char *ato)
//    {
//    int res;
//    int sign;
//
//    res = 0;
//    sign = 1;
//    //handling space, tab, newline, form feed and carriage return
//    while (*ato == ' ' || *ato == '\t' || *ato == '\n' || *ato == '\f' || *ato == '\r')
//    ato++;
//    //handling negative numbers
//    if (*ato == '-')
//    sign = -1;
//    if (*ato == '-' || *ato == '+')
//    ato++;
//    while (*ato >= '0' && *ato <= '9')
//    {
//    res = res * 10 + *ato - '0';
//    ato++;
//    }
//    return (sign * res);
//    }
//
//    int main() {
//    printf("MINE: %d\nORIG: %d", my_atoi("      -123"), atoi("      -123"));
//    return 0;
//    }