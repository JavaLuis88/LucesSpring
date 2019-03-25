package esmeralda.projects.ligths.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import esmeralda.projects.ligths.dao.entities.Task;
import esmeralda.projects.ligths.dao.entities.Taskdays;
import esmeralda.projects.ligths.dao.repositories.TasksDaysRepository;
import esmeralda.projects.ligths.dao.repositories.TasksRepository;

public class TaskManager implements ITaskManager {

	@Autowired
	private TasksRepository taskrepository;

	@Autowired
	private TasksDaysRepository taskdaysrepository;

	@Override
	public Task getTaskById(Integer idtask) {
		Optional<Task> tasks;
		Task task;

		task = null;
		tasks = this.taskrepository.findById(idtask);

		if (tasks.isPresent() == true) {

			task = tasks.get();

		}

		return task;
	}

	@Override
	public Task getTaskByName(String taskname) {

		Task task;

		task = null;
		task = this.taskrepository.findByTaskname(taskname);
		return task;

	}

	@Override
	public Iterable<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return this.taskrepository.findAll();
	}

	@Override
	public boolean exitsTaskName(String taskname) {
		// TODO Auto-generated method stub
		return this.taskrepository.existsByTaskname(taskname);
	}

	@Override
	public int addTask(Task task, ArrayList<Taskdays> taskdays) {
		Task newtask;
		Taskdays taskday;
		int count;
		int retval;
		retval = 0;

		if (task == null || taskdays == null || this.testWekdays(taskdays) == false) {

			retval = 1;

		} 
		
		else if (this.existWekdays(taskdays)==true) {
			
			
			retval=3;
			
		}
		else  {
			try {
				
				if (this.exitsTaskName(task.getTaskname()) == false) {
					
					task.setId(null);
					this.taskrepository.save(task);
					newtask=this.taskrepository.findByTaskname(task.getTaskname());
					
					
					if (newtask != null) {

						count = taskdays.size();
						for (int i = 0; i < count; i++) {
							taskday = taskdays.get(i);
							if (taskday != null) {
								taskday.setId(null);
								taskday.setIdtask(newtask.getId());
								taskdays.set(i, taskday);
							}

						}

						this.taskdaysrepository.saveAll(taskdays);

					} else {

						retval = 5;

					}
					
					
				}
				else {
					
					retval=4;
					
				}
			}
			catch (Throwable e) {

				retval = 2;

			}
		
		}
		return retval;
	}

	@Override
	public int updateTask(Task task) {
	
		
	Task newtask;
		int retval;
		retval = 0;

		if (task == null) {

			retval = 1;

		} else {

			try {

				newtask = this.taskrepository.findByTaskname(task.getTaskname());
				if (newtask != null) {

					task.setId(newtask.getId());
					this.taskrepository.save(task);
				} else {

					retval = 3;

				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;

		
	}

	@Override
	public int deleteTask(Integer idtask) {
		int retval;
		retval = 0;

		if (idtask == null) {

			retval = 1;

		} else {

			try {

				if (this.deleteAllTaskDays(idtask) == 0) {
					this.taskrepository.deleteById(idtask);
				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	
	
	public Taskdays getTaskdaysById(Integer idtaskdays) {

		Optional<Taskdays> taskdays;
		Taskdays taskday;

		taskday = null;
		taskdays = this.taskdaysrepository.findById(idtaskdays);

		if (taskdays.isPresent() == true) {

			taskday = taskdays.get();

		}

		return taskday;

	}

	@Override
	public List<Taskdays> getTaskdays(Integer idtask) {

	// TODO Auto-generated method stub
		
		return this.taskdaysrepository.findByIdtask(idtask);
	}

	@Override
	public Iterable<Taskdays> getAllTasksDays() {
		// TODO Auto-generated method stub
		return this.taskdaysrepository.findAll();
	}

	@Override
	public boolean hasgotTaskDate(Date datetask, int weekday) {
		// TODO Auto-generated method stub
		return this.taskdaysrepository.existsByDatetaskAndWeekday(datetask, weekday);
	}

	@Override
	public int addTaskdays(Integer idtask, int weekday) {
		// TODO Auto-generated method stub
		
		
		List<Taskdays> taskdays;
		Taskdays taskday;
		
		taskday=null;
		int retval;
		retval=0;
		
		if (idtask == null || weekday<0 ||  weekday>6) {

			retval = 1;

		} 
		
		
		taskdays=this.getTaskdays(idtask);
		
		if (taskdays==null || taskdays.size()<1) {
			
			
			retval=3;
			
		}
		else  {
			try {
				
				
				
				if (this.taskdaysrepository.existsByDatetaskAndWeekday(taskdays.get(0).getDatetask(), weekday)==false) {
					
					
					
					taskday=new Taskdays();
					taskday.setDatetask(taskdays.get(0).getDatetask());
					taskday.setWeekday(weekday);
					taskday.setIdtask(idtask);
					this.taskdaysrepository.save(taskday);
					
				}
				else {
					
					retval=4;
					
				}
			
			
			}
			catch (Throwable e) {

				retval = 2;

			}
		
		}

		
		
		
		return retval;
		
		
		
		
	}

	@Override
	public int updateTaskDays(Integer idtask,int weekday,int newweekday) {

		
		List<Taskdays> taskdays;
		Taskdays taskday;
		
		taskday=null;
		int retval;
		retval=0;
		
		if (idtask == null || weekday<0 ||  weekday>6 || newweekday<0 || newweekday>6) {

			retval = 1;

		} 
		
		
		taskdays=this.getTaskdays(idtask);
		
		if (taskdays==null || taskdays.size()<1) {
			
			
			retval=3;
			
		}
		else  {
			try {
				
				
				
				if (this.taskdaysrepository.existsByDatetaskAndWeekday(taskdays.get(0).getDatetask(), weekday)==false) {
					
					retval=4;
					
					
				}
				else if (this.taskdaysrepository.existsByDatetaskAndWeekday(taskdays.get(0).getDatetask(), newweekday)==true) {
					
					retval=5;
					
					
				}
				
				else {
		
					taskday=new Taskdays();
					taskday.setDatetask(taskdays.get(0).getDatetask());
					taskday.setWeekday(weekday);
					taskday.setIdtask(idtask);
					this.taskdaysrepository.save(taskday);
		
				}
			
			
			}
			catch (Throwable e) {

				retval = 2;

			}
		
		}

		
		
		
		return retval;
		
		
	}

	@Override
	public int deleteTaskDays(Integer idtaskdays) {

		int retval;
		
		retval=0;
		
		if (idtaskdays == null) {

			retval = 1;

		} 
		else  {
			
	

			try {
					this.taskdaysrepository.deleteById(idtaskdays);
				
			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
			
			
	
		
	}

	@Override
	public int deleteAllTaskDays(Integer idtask) {
	
		
	int retval;
		
		retval=0;
		
		if (idtask == null) {

			retval = 1;

		} 
		else  {
			
	

			try {
				
					this.taskdaysrepository.deleteByIdtask(idtask);
				
			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
		
	}

	private boolean testWekdays(ArrayList<Taskdays> taskdays) {

		HashMap<String, String> taskdatetime;
		Date timetask;
		boolean retval;
		int i;

		timetask = new Date();
		taskdatetime = new HashMap<String, String>();
		retval = true;

		i = 0;
		while (i < taskdays.size() && retval == true) {

			if (taskdays.get(i) == null) {
				retval = false;
			}

			else if (taskdays.get(i).getDatetask() == null || taskdays.get(i).getWeekday() < 0
					|| taskdays.get(i).getWeekday() > 6) {

				retval = false;

			}

			else if (i == 0) {

				timetask = taskdays.get(i).getDatetask();
				taskdatetime.put("key" + i, Integer.toString(taskdays.get(i).getWeekday()));
				i++;

			} else if (taskdays.get(i).getDatetask().getHours() != timetask.getHours()
					|| taskdays.get(i).getDatetask().getMinutes() != timetask.getMinutes()
					|| taskdatetime.containsValue(Integer.toString(taskdays.get(i).getWeekday())) == true) {

				retval = false;

			} else {

				taskdatetime.put("key" + i, Integer.toString(taskdays.get(i).getWeekday()));
				i++;
			}

		}

		return retval;

	}

	private boolean existWekdays(ArrayList<Taskdays> taskdays) {

		boolean retval;
		int i;

		retval = true;

		i = 0;
		while (i < taskdays.size() && retval == true) {

			taskdays.get(i).getDatetask().setSeconds(0);
			retval = (this.taskdaysrepository.existsByDatetaskAndWeekday(taskdays.get(i).getDatetask(),
					taskdays.get(i).getWeekday()) == false);
			i++;
		}

		return retval;

	}

}
