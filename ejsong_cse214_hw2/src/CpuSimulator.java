//Eric Song
//112294760
//CSE214
//HW2

import java.util.Scanner;

public class CpuSimulator {
    private CpuCore[] cores;
    private Queue<Process> processQueue;
    private int totalRuntime;
    private double positiveSteps;


    /**
     * Constructor for CpuSimulator.
     * Array of cores, cores, is created using numCores. Cores are then created in the array by using a for loop, using i as the core ID.
     * Total run time and positive steps are set to zero.
     * ProcessQueue is set to q.
     * @param numCores the amount of cores the simulator will contain
     * @param cutoff the maximum number of times a CpuCore can work on a process in a row
     * @param q the queue or processes that the simulator and CpuCores will utilize
     */
    public CpuSimulator(int numCores, int cutoff, Queue<Process> q) {
        // TODO
        // set up the cores, and process queue
        // cutoff indicates maximum number of steps working on the same process
        // consecutively

        cores = new CpuCore[numCores];
        totalRuntime = 0;
        positiveSteps = 0;
        for(int i = 0; i < cores.length;i++){
            cores[i] = new CpuCore(i, q, cutoff);

        }
        processQueue = q;


    }

    /**
     * This method returns whether or not every core in the array is done working on a process or not.
     * A for loop is used to check all of the CpuCores in CpuSimulator.
     * If a CpuCore is found containing a process, the method returns null.
     * Otherwise, the method returns true.
     * @param c the array of cores being traversed through
     * @return if all the CpuCores are done working on methods or not
     */
    public boolean allCoresFinished(CpuCore[] c){
        for(int i = 0; i < c.length;i++){
            if(c[i].currentProcess != null){ //if a core has a process, return false
                return false;
            }
        }
        return true;
    }

    /**
     * This method runs the CpuSimulator.
     * A for loop is used to traverse through the array of cores and making each of them step.
     * A while loop is used to keep the for loop going until the processQueue is empty and all of the cores are done working.
     * Another for loop is used to calculate total runtime and total positive steps used.
     */

    public void run() {
        // TODO
        // loop as long as there are still processes to finish (either in the queue or
        // on a core)
        // and do one step for each core in the cpu
        while(!processQueue.isEmpty() || !allCoresFinished(cores)){ //run as long as processQueue has processes and cores are not done
            for(int i = 0; i < cores.length;i++){
                cores[i].step();
            }

        }
        for(int i = 0; i < cores.length;i++){
            totalRuntime += cores[i].timesteps;
            positiveSteps += cores[i].positiveSteps;
        }

    }

    /**
     * This method returns the average amount of timesteps of the duration of the simulation.
     * The method returns the totalRuntime divided by the amount of cores.
     * @return the average amount of timesteps for the simulation
     */
    public int avgTurnaroundTime(){
        return totalRuntime/(cores.length+1);

    }

    /**
     * This method returns the efficiency of the simulation that was run.
     * The method returns the amount of positive steps divided by the total run time.
     * @return the efficiency of the simulation
     */
    public double getUtilization(){
        return positiveSteps/totalRuntime;
    }


    public static void main(String[] args) {

        // TODO

        // read the parameters from the command line using Scanner
        // first line = number of processes
        // second line = number of cores
        // third line = cutoff
        // then one line per process in this format
        // <process id>,<process size>

        // Create the process objects, set up the queue, create an instance of
        // CpuSimulator for this queue with the appropriate arguments

        // call run on the simulator
        Scanner scan = new Scanner(System.in);
        int numProcesses = scan.nextInt();
        int numCores = scan.nextInt();
        int cutoff = scan.nextInt();
        Queue<Process> processes = new Queue<>();

        for(int i = 0; i < numProcesses; i++){ //create cores
            String info = scan.next();
            String[] infoSplit = info.split(",");

            processes.enqueue(new Process(Integer.parseInt(infoSplit[1]),infoSplit[0]));

        }

        CpuSimulator sim = new CpuSimulator(numCores, cutoff, processes);



        sim.run();
        System.out.println(sim.avgTurnaroundTime());
        System.out.println(sim.getUtilization());

    }
}
