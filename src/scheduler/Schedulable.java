package scheduler;

import java.util.PriorityQueue;

public interface Schedulable {

	void addTask(PriorityQueue<Task> task_set, Task t, int current_time);
	Task getCopy();
	void updateDeadline (int current_time);
}
