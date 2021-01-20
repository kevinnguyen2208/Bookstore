package ed.jpa;

import entity.Myuser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyuserDB {

    private EntityManager em = null;
    public MyuserDB() {
        // using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ED-EntityPU");
        em = emf.createEntityManager();
    }
    public EntityManager getEntityManger() {
        return em;
    }

    public Myuser findMyuser(String userid) {
        return em.find(Myuser.class, userid);
    }
    public boolean createMyuser(Myuser myuser) throws Exception {
        try {
            if (findMyuser(myuser.getUserid()) != null) {
                // myuser exists already
                return false;
            }
            // myuser does not exist in database
            em.getTransaction().begin();
            em.persist(myuser); // to add an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTOtoDAO(myuserDTO);
        boolean result = false;
        try {
            result = this.createMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }
    
    private Myuser myDTOtoDAO(MyuserDTO myDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myDTO.getUserid());
        myuser.setName(myDTO.getName());
        myuser.setPassword(myDTO.getPassword());
        myuser.setEmail(myDTO.getEmail());
        myuser.setPhone(myDTO.getPhone());
        myuser.setAddress(myDTO.getAddress());
        myuser.setSecqn(myDTO.getSecQn());
        myuser.setSecans(myDTO.getSecAns());
        return myuser;
    }
    
    
    public MyuserDTO getRecord(String userId) {
        
        Myuser myuser = new Myuser();
        myuser.setUserid(userId);
        
        em.getTransaction().begin();
        Myuser user = em.find(Myuser.class, userId);
        em.getTransaction().commit();
        MyuserDTO myuserDTO = new MyuserDTO(user.getUserid(), user.getName(), 
                user.getPassword(), user.getEmail(), user.getPhone(), 
                user.getAddress(), user.getSecans(), user.getSecqn());
        return myuserDTO;
        
    }
    
    public boolean updateRecord(MyuserDTO myuserDTO) {
        MyuserDTO foundUser = getRecord(myuserDTO.getUserid());
        if (foundUser == null) {
            return false;
        }
        Myuser myuser = this.myDTOtoDAO(myuserDTO);
        System.out.println("MERGING :" + myuserDTO.getName());
        em.getTransaction().begin();
        em.merge(myuser);
        em.getTransaction().commit();

        return true;
    }
    
    public boolean deleteRecord(String userId) {
        MyuserDTO foundUser = getRecord(userId);
        if (foundUser == null) {
            return false;
        }
        Myuser myuser = this.myDTOtoDAO(foundUser);
        System.out.println("DELETING: " + myuser.getName());
        em.getTransaction().begin();
        Myuser myuser2 = em.merge(myuser);
        em.remove(myuser2);
        em.getTransaction().commit();

        return true;
    }
}