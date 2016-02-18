/*
 * File:    
 * Author:  Brandon Pearce
 * Date:    04/xx/2015
 * Purpose: 
 */
package project3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 *
 * @author Angmar
 */
public class Project3 {
    static boolean SHOW = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Data to be sorted */
        List<Integer> data = createRandomData();
        
        /* Generate a random permutation of the data.
         * This sometimes improves the performance of QuickSort
         */
        Collections.shuffle(data);
        
        if (SHOW) {
            System.out.println("Unsorted data -------------- \\/ \n");
            System.out.println(data);
        }
            
        /* Call quick sort */
        long startTime = System.nanoTime();
        List<Integer> sorted = quickSort(data);
        long finishTime = System.nanoTime() - startTime; 
        System.out.println("Time elapsed for quickSort    : " + finishTime);
        
        long startTime1 = System.nanoTime();
        List<Integer> sorted1 = quickSortOpt1(data);
        long finishTime1 = System.nanoTime() - startTime1; 
        System.out.println("Time elapsed for quickSortOpt1: " + finishTime1);
        
        long startTime2 = System.nanoTime();
        List<Integer> sorted2 = quickSortOpt2(data);
        long finishTime2 = System.nanoTime() - startTime2; 
        System.out.println("Time elapsed for quickSortOpt2: " + finishTime2);
        
        /* Print sorted data to the standard output */
        if (SHOW) {
            System.out.println();
            System.out.println("Sorted data ---------------- \\/ \n");
            System.out.println(sorted);
            System.out.println();
            System.out.println("Sorted Opt1 data ----------- \\/ \n");
            System.out.println(sorted1);
            System.out.println();
            System.out.println("Sorted Opt2 data ----------- \\/ \n");
            System.out.println(sorted2);
        }
        
    }
    
    private static final Random rand = new Random();
    
    /* Add data to be sorted to the list */
    public static List<Integer> createRandomData() {
        int len = 0;
        int max = 0;
        if (SHOW) {
            max = 9999;
            len = 100;
        } else {
            max = 999999;
            len = 100000;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<len; i++) {
            /* You can add any type that implements
             * the Comparable interface */             
            list.add(Integer.valueOf(rand.nextInt(max)));
        }
        return list;
    }
    
    public static <E extends Comparable<? super E>> List<E> quickSort(List<E> data) {
        List<E> sorted = new ArrayList<E>();
        rQuickSort(data, sorted);
        return sorted;
    }
    
    public static <E extends Comparable<? super E>> List<E> quickSortOpt1(List<E> data) {
        List<E> sorted = new ArrayList<E>();
        rQuickSortOpt1(data, sorted);
        return sorted;
    }
    
    public static <E extends Comparable<? super E>> List<E> quickSortOpt2(List<E> data) {
        List<E> sorted = new ArrayList<E>();
        rQuickSortOpt2(data, sorted);
        return sorted;
    }
    
    public static <E extends Comparable<? super E>> void rQuickSort(List<E> data, List<E> sorted) {   
        if(data.size() == 1) {
            sorted.add(data.iterator().next());
            return;
        }
        
        if(data.size() == 0) {
            return;
        }
        
        /* choose the pivot randomly */
        int pivot = rand.nextInt(data.size());
        E pivotI = data.get(pivot);
        List<E> fatPivot = new ArrayList<E>();
        List<E> left = new ArrayList<E>();
        List<E> right = new ArrayList<E>();
        
        int partition = data.size();
        /*if (SHOW)
            System.out.println("Partition = " + partition);
        */
        
        /* partition data */
        for(E next : data) {
            int compare = pivotI.compareTo(next);
            /*if (SHOW)
                System.out.println("Comparing " + next + " to " + pivotI);
            */
            if(compare < 0) {
                right.add(next);
                /*if (SHOW) {
                    System.out.println("Added to right");
                    System.out.println(right);
                }*/
            }
            else if(compare > 0) {
                left.add(next);
                /*if (SHOW) {
                    System.out.println("Added to left");
                    System.out.println(left);
                }*/
            }
            else
            {
                fatPivot.add(next);
                /*if (SHOW)
                    System.out.println("Fat pivot: " +  fatPivot);
                */
            }
        }
        
        rQuickSort(left, sorted);
        sorted.addAll(fatPivot);
        /*
        if (SHOW) {
            System.out.println("--------> Sorted Array <--------");
            System.out.println(sorted);
            System.out.println("--------------------------------");
        }*/
        rQuickSort(right, sorted);
        
    }
    public static <E extends Comparable<? super E>> void rQuickSortOpt1(List<E> data, List<E> sorted) {
        int i;
        int j;
        E tmp;
        int pivot = rand.nextInt(data.size());
        E pivotI = data.get(pivot);
        List<E> fatPivot = new ArrayList<E>();
        List<E> left = new ArrayList<E>();
        List<E> right = new ArrayList<E>();
        int partition = data.size();
        
        if (partition <= 10) {
            insertionSort(data);
        } else {
            rQuickSort(data, sorted);
        }
    }
    
    public static <E extends Comparable<? super E>> void rQuickSortOpt2(List<E> data, List<E> sorted) {
        if(data.size() == 1) {
            sorted.add(data.iterator().next());
            return;
        }
        
        if(data.size() == 0) {
            return;
        }
        
        /* choose the pivot randomly */
        int pivot = rand.nextInt(data.size());
        E pivotI = data.get(pivot);
        List<E> fatPivot = new ArrayList<E>();
        List<E> left = new ArrayList<E>();
        List<E> right = new ArrayList<E>();
        int partition = data.size();
        
        /* partition data */
        if (partition <= 10) {
            insertionSort(sorted);
        } else {
            rQuickSort(data, sorted);
        }
        
    }
    
    
    
    public static <E extends Comparable<? super E>> void insertionSort(List<E> data) {
        
        for (int i = 0; i < data.size(); i++) {
            E x = data.get(i);
            int copyNumber = (Integer) data.get(i);
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (data.get(j).compareTo(x) <=0) break;
                data.set(j +1, data.get(j));
            }
        data.set(j+1, x);
    }
}
    
}