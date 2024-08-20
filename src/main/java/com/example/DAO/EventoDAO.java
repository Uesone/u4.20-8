import com.example.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class EventoDAO {

    private EntityManager entityManager;

    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Metodo per salvare un evento
    public void save(Evento evento) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(evento); // Salva l'evento nel database
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Errore durante il salvataggio dell'evento", e);
        }
    }


    public Evento getById(Long id) {
        return entityManager.find(Evento.class, id);
    }

    // Metodo per eliminare un evento tramite ID
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Evento evento = entityManager.find(Evento.class, id);
            if (evento != null) {
                entityManager.remove(evento);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Errore durante l'eliminazione dell'evento", e);
        }
    }
}
