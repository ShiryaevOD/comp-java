package hello;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByProduct(String product);
    Product findById(Integer id);

    boolean existsByProduct(String product);

}
