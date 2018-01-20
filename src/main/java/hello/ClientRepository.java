package hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{

     boolean existsByUsername(String username);
     Client findFirstByUsername(String username);
     Client findFirstByUsernameAndPassword(String username,String password);


}
