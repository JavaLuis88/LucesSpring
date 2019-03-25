package esmeralda.projects.ligths.dao.repositories;



import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import esmeralda.projects.ligths.dao.entities.Roles;


public interface RolesRepository extends CrudRepository<Roles, Integer> {

	
	public List<Roles> findByIduser(Integer iduser);
	public boolean existsByIduserAndRole(Integer iduser,String role);
	public Roles findByIduserAndRole(Integer iduser,String role);
	@Transactional
	public int deleteByIduser(Integer iduser); 
}


