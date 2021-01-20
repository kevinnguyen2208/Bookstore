package session;

import entity.MyuserDTO;
import entity.Myuser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class MyuserFacade implements MyuserFacadeRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-JEE-DTO-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Myuser myuser) {
        em.persist(myuser);
    }

    private void edit(Myuser myuser) {
        em.merge(myuser);
    }

    private void remove(Myuser myuser) {
        em.remove(em.merge(myuser));
    }

    private Myuser find(Object id) {
        return em.find(Myuser.class, id);
    }

    public boolean createRecord(MyuserDTO myuserDTO) {

        if (find(myuserDTO.getUserid()) != null) {
            System.out.println("user with ID already exist");
            return false;
        }

        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.create(myuser);
            return true;
        } catch (Exception e) {
            System.out.println("couldnt create user");
            return false;
        }
    }

    private Myuser myDTO2DAO(MyuserDTO myuserDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myuserDTO.getUserid());
        myuser.setName(myuserDTO.getName());
        myuser.setPassword(myuserDTO.getPassword());
        myuser.setEmail(myuserDTO.getEmail());
        myuser.setPhone(myuserDTO.getPhone());
        myuser.setAddress(myuserDTO.getAddress());
        myuser.setSecqn(myuserDTO.getSecQn());
        myuser.setSecans(myuserDTO.getSecAns());
        return myuser;
    }

    private MyuserDTO myDAO2DTO(Myuser myuser) {
        MyuserDTO myuserDTO = new MyuserDTO(myuser.getUserid(), myuser.getName(),
        myuser.getPassword(), myuser.getEmail(), myuser.getPhone(),
        myuser.getAddress(), myuser.getSecqn(), myuser.getSecans());
        return myuserDTO;
    }

    public MyuserDTO getRecord(String userId) {
        System.out.println("Inside getting record");
        Myuser myuser = this.find(userId);
        if (myuser == null) {
            System.out.println("User is null");
            return null;
        }

        MyuserDTO myuserDTO = myDAO2DTO(myuser);

        return myuserDTO;
    }

    public boolean updateRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) == null) {
            System.out.println("user with ID doesnt exist");
            return false;
        }

        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            System.out.println("About to update user");
            this.edit(myuser);
            return true;
        } catch (Exception e) {
            System.out.println("couldnt update user, error" + e);
            return false;
        }
    }

    public boolean deleteRecord(String userId) {

        MyuserDTO myuserDTO = this.getRecord(userId);
        if (myuserDTO == null) {
            return false;
        }

        Myuser myuser = myDTO2DAO(myuserDTO);

        this.remove(myuser);
        return true;
    }

    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        Query myQuery = em.createNamedQuery("Myuser.findByAddress").setParameter("address", address);
        List<Myuser> daoList = myQuery.getResultList();
        ArrayList<MyuserDTO> dtolist = new ArrayList<>();
        for (int i = 0; i < daoList.size(); i++) {
            System.out.println(daoList.get(i));

            MyuserDTO myuserdto = myDAO2DTO(daoList.get(i));
            dtolist.add(myuserdto);
        }
        return dtolist;
    }
}