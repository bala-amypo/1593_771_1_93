public interface CatalogService {
    ActiveIngredient addIngredient(ActiveIngredient ingredient);
    Medication addMedication(Medication medication);
    List<Medication> getAllMedications();
}
