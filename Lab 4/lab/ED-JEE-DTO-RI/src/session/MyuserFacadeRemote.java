package session;

import entity.MyuserDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author jonathany
 */
@Remote
public interface MyuserFacadeRemote {

    boolean createRecord(MyuserDTO myuserDTO);

    MyuserDTO getRecord(String userid);

    boolean updateRecord(MyuserDTO myuserDTO);

    boolean deleteRecord(String userId);

    ArrayList<MyuserDTO> getRecordsByAddress(String address);
}