package esmeralda.projects.ligths.dao.repositories;



import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import esmeralda.projects.ligths.dao.entities.Taskdays;

public interface TasksDaysRepository extends CrudRepository<Taskdays, Integer> {

	
	public Taskdays findByDatetaskAndWeekday(Date datetask,int weekday);
	public boolean existsByDatetaskAndWeekday(Date datetask,int weekday);
	public List<Taskdays> findByIdtask(Integer idtask);
	@Transactional
	public int deleteByIdtask(Integer idtask);

}


