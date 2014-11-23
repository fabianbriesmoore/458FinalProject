package scheduler;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Schedule {
	private PriorityQueue<Task> task_set;
	private PriorityQueue<Task> periodic_task_set;
	private int current_time;
	private int total_time;
	private Integer[] generated_schedule;
	
	public Schedule(PriorityQueue<Task> periodic_task_set, int total_time) {
		current_time = 0;
		this.total_time = total_time;
		this.periodic_task_set = periodic_task_set;
		
		//add periodic task set to running task set
		task_set = new PriorityQueue<Task>();
		Iterator<Task> p_task_it = periodic_task_set.iterator();
		while(p_task_it.hasNext()) {
			task_set.add((Task) p_task_it.next().getCopy());
		}
		
		//make sure absolute deadlines are set
		Iterator<Task> it = task_set.iterator();
		while(it.hasNext()) {
			Task current_task = it.next();
			current_task.setAbsolute_deadline(current_task.getDeadline());
		}
		
		generated_schedule = new Integer[total_time];
	}
	
	public PriorityQueue<Task> getTask_set() {
		return task_set;
	}
	
	public Integer[] getGenerated_Schedule() {
		return generated_schedule;
	}
	
	public void runAll() throws UnschedulableException{
		for (int i = 0; i < total_time; ++i) {
			runOnce();
		}
	}
	
	//1)throw exception if past any deadline 
	//2)add any periodic tasks that have arrived
	//3)decrement computation time of running task if exists
	//3b)remove if no time left
	//4)increment the time by one
	public void runOnce() throws UnschedulableException {
		
		//throw exception if past any deadline, also update deadline(needed for edf)
		Iterator<Task> it = task_set.iterator();
		while(it.hasNext()) {
			Task current_task = it.next();
			if (current_time > current_task.getAbsolute_deadline()) {
				throw new UnschedulableException();
			}
			
			current_task.updateDeadline(current_time);
		}
		
		//add any periodic tasks that have arrived
		Iterator<Task> periodic_it = periodic_task_set.iterator();
		while(periodic_it.hasNext() && current_time != 0) {
			Task current_task = periodic_it.next();
			if(current_time % current_task.getDeadline() == 0) {
				Task new_task = current_task;
				new_task.addTask(task_set, new_task, current_time);
			}
		}
		
		//decrement computation time of running task if exists (Run task)
		if(task_set.iterator().hasNext()) {
			Task running_task = task_set.iterator().next();
			int time_left = running_task.decrementComputationTime();
			generated_schedule[current_time] = running_task.getTask_id();
			
			//remove if no time left
			if (time_left <= 0) {
				task_set.remove();
			}
		}

		//increment the time
		current_time++;
	}

	public void printSchedule() {
		System.out.println("Printing Schedule: ");
		for(int i = 0; i < total_time; ++i) {
			System.out.print(generated_schedule[i] + ", ");
		}
		System.out.println();
	}
	
	public void printTaskSet(PriorityQueue<Task> task) {
		System.out.println("Printing Tasks: ");
		Iterator<Task> it = task.iterator();
		while(it.hasNext()) {
			Task current_task = it.next();
			System.out.print(current_task.toString());
		}
		System.out.println();
	}

}