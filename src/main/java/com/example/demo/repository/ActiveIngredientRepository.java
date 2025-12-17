import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ActiveIngredient;

public interface ActiveIngredientRepository
        extends JpaRepository<ActiveIngredient, Long> {

    ActiveIngredient findByName(String name);
    boolean existsByName(String name);
}