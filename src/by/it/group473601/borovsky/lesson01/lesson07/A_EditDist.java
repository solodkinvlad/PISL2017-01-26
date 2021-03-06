package by.it.group473601.borovsky.lesson01.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;
        int[][] levensteignDistances = new int [one.length() + 1][two.length() + 1];
        int roundInfinity = 32765;
        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                levensteignDistances[i][j] = roundInfinity;
            }
        }

        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                result = editDistTD(i, j, levensteignDistances, roundInfinity, one, two);
            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private static int editDistTD(int i, int j, int[][] levensteignDistances, int infinity, String one, String two) {

        if (levensteignDistances[i][j] == infinity){
            if (i == 0){
                levensteignDistances[i][j] = j;
            }
            else {
                if (j == 0) {
                    levensteignDistances[i][j] = i;
                }
                else {
                    int needToBeInsert = editDistTD(i, j - 1, levensteignDistances, infinity, one, two) + 1;
                    int needToBeDelete = editDistTD(i - 1 , j, levensteignDistances, infinity, one, two) + 1;
                    int needToBeReplace = editDistTD(i - 1 , j - 1, levensteignDistances, infinity, one, two) + getDiff(one.charAt(i-1), two.charAt(j-1));

                    levensteignDistances[i][j] = getMin(needToBeInsert, needToBeDelete, needToBeReplace);
                }
            }
        }
        return levensteignDistances[i][j];
    }


    private static int getDiff(char one, char two){
        return one != two ? 1: 0;
    }

    private static int getMin(int one, int two, int three){
        int min = -1;
        if(two > one){
            min = one;
        }else{
            min = two;
        }
        if(three < min){
            min = three;
        }
        return min;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

