package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.*;
import java.util.function.Supplier;

/**
 * Interface to define the behavior of a Benchmark.
 *
 * @param <T> the underlying type which is passed into (or supplied) to the run/runFromSupplier methods.
 */
public interface Benchmark<T> {
    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    default double run(T t, int m) {
        return runFromSupplier(() -> t, m);
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    double runFromSupplier(Supplier<T> supplier, int m);

    public static void main(String[] args){
        Integer[] ordered;
        Integer[] reverse;
        Integer[] random;
        Integer[] partial;

        List<Double> orderedTimes = new ArrayList<>();
        List<Double> reverseTimes = new ArrayList<>();
        List<Double> partialTimes = new ArrayList<>();
        List<Double> randomTimes = new ArrayList<>();

        int n=50;
        for(int i=0; i<5; i++){
            n *= 2;
            ordered = new Integer[n];
            reverse = new Integer[n];
            random = new Integer[n];
            partial = new Integer[n];

            int rev= n-1;
            for(int j=0; j<n; ++j) {
                ordered[j] = j;
                reverse[n-j-1] = j;

                Random ran = new Random();
                int ind = ran.nextInt(n*2);
                while(ind<(n/2)){
                    ind = ran.nextInt(n*2);
                }

                if(j<(n/2)){
                    partial[j] = j;
                }
                else{
                    partial[j] = ind;
                }

                random[j] = ind;
            }

            //ordered
            List<Integer> ord = new ArrayList<>();
            Collections.addAll(ord, ordered);

            Benchmark<Integer[]> bm = new Benchmark_Timer<>("Insertion Sort Timer", b->{new InsertionSort().sort(ord);});
            orderedTimes.add(bm.run(ordered, 10));

            List<Integer> reve = new ArrayList<>();
            Collections.addAll(reve, reverse);

            bm = new Benchmark_Timer<>("Insertion Sort Timer", b->{new InsertionSort().sort(reve);});
            reverseTimes.add(bm.run(reverse, 10));

            List<Integer> part = new ArrayList<>();
            Collections.addAll(part, partial);

            bm = new Benchmark_Timer<>("Insertion Sort Timer", b->{new InsertionSort().sort(part);});
            partialTimes.add(bm.run(partial, 10));

            List<Integer> rand = new ArrayList<>();
            Collections.addAll(rand, random);

            bm = new Benchmark_Timer<>("Insertion Sort Timer", b->{new InsertionSort().sort(rand);});
            randomTimes.add(bm.run(random, 10));
        }

        System.out.println("size,Ordered,Reverse,Partial,Random");
        n=50;
        for(int i=0; i<orderedTimes.size(); ++i){
            n = n*2;
            System.out.println(n+","+orderedTimes.get(i)+ ","+reverseTimes.get(i)+ ","+partialTimes.get(i)+ ","+randomTimes.get(i));
//            System.out.println("Ordered," + orderedTimes.get(i)+ ","+ n);
//            System.out.println("Reverse," + reverseTimes.get(i)+ ","+ n);
//            System.out.println("Partial," + partialTimes.get(i)+ ","+ n);
//            System.out.println("Random," + randomTimes.get(i)+ ","+ n);
        }
    }
}
