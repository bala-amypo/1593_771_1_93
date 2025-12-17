import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.InteractionRule;

public interface InteractionRuleRepository
        extends JpaRepository<InteractionRule, Long> {

    InteractionRule findRuleBetweenIngredients(Long id1, Long id2);
}