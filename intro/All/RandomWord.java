/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class RandomWord {
    public static void main(String[] args) {
        int argsSize = args.length;
        String win = "err";
//
//        for (int i = 0; i < argsSize; i++) {
//            double rnd = Math.random();
//            if (rnd * (i + 1) <= 1) {
//                win = args[i];
//            }
//        }
        System.out.println(win);

        while (StdIn.isEmpty()) {
            String next = StdIn.readString();
            System.out.println(next);
        }
    }
}
