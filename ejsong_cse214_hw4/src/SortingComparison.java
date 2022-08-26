//Eric Song
//112294760
//CSE214
//Homework 5

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.ApplicationFrame;
import javax.swing.*;
import java.awt.*;

class SortingComparison extends ApplicationFrame {
    // use this for counting comparisons done by a sort
    // a global variable is not ideal, but we can use this for every sort without
    // worrying about return types for now
    // remember to reset it to 0 before sorting
    private int comparisons;

    public SortingComparison(final String title) {

        super(title);

        // Collections that hold the series we are about to create
        final XYSeriesCollection divAndConqData = new XYSeriesCollection();
        final XYSeriesCollection quadraticData = new XYSeriesCollection();

        int maxSize = 5000;
        int dataRange = 1000;



        final XYSeries series1 = new XYSeries("quick sort k=0");
        // quick sort with no insertion sort
        // loop from 0 to maxSize
        // create an array of each size and fill it with random elements using
        // (int) (Math.random() * dataRange)
        // sort using the method for this series
        // add data point to series like series1.add(size, comparisons);
        // ...
        // ...
        // ...
        // after adding all the data points, (there should be maxSize of them) add the
        // series to the collection for this graph
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            hybridQuickSort(array,0,array.length-1,0);
            series1.add(array.length,comparisons);

        }
        divAndConqData.addSeries(series1);

        final XYSeries series2 = new XYSeries("quick sort k=10");
        // repeat above for quick sort with insertion sort for 10 or less elements
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            hybridQuickSort(array,0,array.length-1,10);
            series2.add(array.length,comparisons);

        }
        divAndConqData.addSeries(series2);

        final XYSeries series3 = new XYSeries("quick sort k=20");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            hybridQuickSort(array,0,array.length-1,20);
            series3.add(array.length,comparisons);

        }
        divAndConqData.addSeries(series3);

        final XYSeries series4 = new XYSeries("quick sort k=50");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            hybridQuickSort(array,0,array.length-1,50);
            series4.add(array.length,comparisons);

        }
        divAndConqData.addSeries(series4);

        final XYSeries series5 = new XYSeries("merge sort");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);
            }
            mergeSort(array,0, array.length-1);
            series5.add(array.length,comparisons);

        }
        divAndConqData.addSeries(series5);

        // max size for quadratic sorts. we needs these to be smaller so that it doesn't
        // take too long
        maxSize = 500;
        final XYSeries series6 = new XYSeries("insertion sort");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            insertionSort(array);
            series6.add(array.length,comparisons);

        }
        quadraticData.addSeries(series6);

        final XYSeries series7 = new XYSeries("selection sort");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            selectionSort(array);
            series7.add(array.length,comparisons);

        }
        quadraticData.addSeries(series7);

        final XYSeries series8 = new XYSeries("bubble sort");
        for(int i = 1; i < maxSize; i++){
            int[] array = new int[i];
            for(int j = 0; j < i;j++){
                array[j] = (int)(Math.random()*dataRange);

            }
            bubbleSort(array);
            series8.add(array.length,comparisons);

        }
        quadraticData.addSeries(series8);

        // YOU DON'T REALLY NEED TO TOUCH THIS SECTION, BUT FEEL FREE TO LOOK THROUGH IT
        // =============================================================================================================
        // takes the data sets and turns them into a moving average so things are easier
        // to see
        XYDataset ma1 = MovingAverage.createMovingAverage(divAndConqData, "", 50, 0);
        XYDataset ma2 = MovingAverage.createMovingAverage(quadraticData, "", 50, 0);
        // takes the moving averages and plots them as line charts
        // if you want to see what it looks like without the moving average you can put
        // data1 and data2 directly in here instead of ma1 and ma2
        final JFreeChart chart1 = ChartFactory.createXYLineChart("Divide and conquer sorts", "Input size n",
                "Num comparisons", ma1, PlotOrientation.VERTICAL, true, true, false);
        final JFreeChart chart2 = ChartFactory.createXYLineChart("Quadratic sorts", "Input size n", "Num comparisons",
                ma2, PlotOrientation.VERTICAL, true, true, false);
        // panel/window setup
        // don't worry about this unless you want to mess with dimensions
        final ChartPanel chartPanel1 = new ChartPanel(chart1);
        final ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel1.setPreferredSize(new java.awt.Dimension(400, 270));
        chartPanel2.setPreferredSize(new java.awt.Dimension(400, 270));
        JPanel j = new JPanel();
        j.setLayout(new GridLayout());
        j.add(chartPanel1);
        j.add(chartPanel2);
        setContentPane(j);
        // =============================================================================================================
    }

    public void selectionSort(int[] arr){
        comparisons = 0;
        for(int i = 0; i < arr.length;i++){
            int index = i;
            for(int j = i+1; j < arr.length;j++){
                comparisons++;
                if(arr[j] < arr[index]){
                    index = j;
                }
            }
            int temp = arr[index]; //swap the elements
            arr[index] = arr[i];
            arr[i] = temp;
        }

    }

    public void insertionSort(int[] arr){
        comparisons = 0;
        for(int i = 0; i < arr.length; i++){
            int x = arr[i];
            int j = i;
            while(j > 0 && arr[j-1] > x){
                comparisons++;
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = x;
        }
    }
    public void insertionSortWithK(int[] arr,int first, int last,int k){
        comparisons = 0;
        for(int i = 0; i < last-first; i++){
            int x = arr[i];
            int j = i;
            while(j > 0 && arr[j-1] > x){
                comparisons++;
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = x;
        }
    }

    public void bubbleSort(int[] arr){
        comparisons = 0;
        for(int i = 0; i < arr.length-1;i++){
            for(int j = arr.length-1; j > i; j--){
                comparisons++;
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public void mergeSort(int[] arr, int first, int last){
        if(last - first+1 == arr.length){
            comparisons = 0;
        }
       if(first < last){
           int m = (last+first)/2;
           mergeSort(arr,first,m);
           mergeSort(arr,m+1,last);
           merge(arr,first,m,last);
       }


    }
    private void merge(int[] arr,int a,int b, int c){

        int n1 = b-a+1;
        int n2 = c-b;

        int[] x = new int[n1];
        int[] y = new int[n2];

        for (int i=0; i<n1; ++i) {
            x[i] = arr[a + i];
        }
        for (int j=0; j<n2; ++j) {
            y[j] = arr[b + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = a;
        while(i < n1 && j < n2){
            comparisons++;
            if (x[i] <= y[j])
            {
                arr[k] = x[i];
                i++;
            }
            else
            {
                arr[k] = y[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = x[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = y[j];
            j++;
            k++;
        }
    }

    public void quickSort(int[] arr, int first, int last){
        if(last - first+1 == arr.length){
            comparisons = 0;
        }
        if(last-first > 0){
            int partition = partition(arr,first,last);
            quickSort(arr,first,partition-1);
            quickSort(arr,partition+1,last);
        }
    }

    public void hybridQuickSort(int[] arr, int first, int last, int k){
        if(last - first+1 == arr.length){
            comparisons = 0;
        }
        if(arr.length > k){
            int x = partition(arr,first,last);
            quickSort(arr,first,x-1);
            quickSort(arr,x+1,last);
        }
        else{
            insertionSortWithK(arr,0,arr.length-1,k);
        }
    }

    private int partition(int[] arr,int low, int high){
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            comparisons++;
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;


    }

    // Implement sorting algorithms...

    public static void main(String[] args) {
        // EXAMPLE TESTS

        // int[] arr1 = { 1, 2, 3, 4, 5, 6, 7 };
        // sort(arr1, 0, arr1.length - 1, 5);
        // for (int x: arr1)
        // System.out.println(x);
        // System.out.println();

        // int[] arr2 = { 7, 6, 5, 4, 3, 2, 1};
        // hybridQuickSort(arr2, 0, arr2.length - 1, 5);
        // for (int x: arr2)
        // System.out.println(x);
        // System.out.println();

        // int[] arr3 = { 56, 3, 78, 26, 1276, 123, 45, 34};
        // hybridQuickSort(arr3, 0, arr3.length - 1, 5);
        // for (int x: arr3)
        // System.out.println(x);
        // System.out.println();

        // call constructor for our ApplicationFrame which will set up all the test
        // sorts and plot the results
        final SortingComparison window = new SortingComparison("Sorting comparison");
        window.pack();
        window.setVisible(true);
    }
}
