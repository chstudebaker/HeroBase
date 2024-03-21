package persistance;

import entity.Equipment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Access equipment in the equipment table.
 */
public class EquipmentDao {

    private static final Logger logger = LogManager.getLogger(EquipmentDao.class);
    private final GenericDaoImpl<Equipment> genericDao;

    public EquipmentDao() {
        this.genericDao = new GenericDaoImpl<>(Equipment.class);
    }

    public List<Equipment> getAllEquipment() {
        return genericDao.getAll();
    }

    public Equipment getById(int equipmentId) {
        return genericDao.getById(equipmentId);
    }

    public List<Equipment> getByHeroId(int heroId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Equipment> criteriaQuery = builder.createQuery(Equipment.class);
            Root<Equipment> root = criteriaQuery.from(Equipment.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("heroId"), heroId));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving equipment by hero ID: " + heroId, e);
            throw e;
        }
    }

    public int insert(Equipment equipment) {
        return genericDao.insert(equipment);
    }

    public boolean update(Equipment equipment) {
        return genericDao.update(equipment);
    }

    public boolean delete(Equipment equipment) {
        return genericDao.delete(equipment);
    }
}
