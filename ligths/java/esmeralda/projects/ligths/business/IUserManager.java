package esmeralda.projects.ligths.business;


import java.util.ArrayList;
import java.util.List;


import esmeralda.projects.ligths.dao.entities.Roles;
import esmeralda.projects.ligths.dao.entities.Users;

public interface IUserManager {


	
	public Users getUserById(Integer iduser);
	public Users getUserByUserName(String username);
    public Iterable<Users> getAllUsers(); 
	public boolean exitsUserName(String username);
	public int addUser(Users user,ArrayList<Roles> roles);
	public int updateUser(Users user);
	public int deleteUser(Integer iduser);
	public Roles getRolById(Integer idrol);
	public List<Roles> getUserRoles(Integer iduser);
    public Iterable<Roles> getAllRoles(); 
	public boolean hasgotUserRole(Integer iduser,String rol);
	public int addUserRol(Integer iduser,String rol);
	public int updateUserRol(Integer iduser,String rol,String newrol);
	public int deleteRol(Integer idrol);
	public int deleteAllUserRoles(Integer iduser); 



	
	
	
	
}
