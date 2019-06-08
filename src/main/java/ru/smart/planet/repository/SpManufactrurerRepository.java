package ru.smart.planet.repository;

import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.ektorp.support.Views;
import org.springframework.stereotype.Repository;
import ru.smart.planet.domain.SpManufacturer;

import java.util.List;

/**
 * Created on 08.06.19.
 */
@Repository
@Views({
        @View(name = SpManufactrurerRepository.VIEW_ALL, map = "function(doc) { if (doc.type='MANUF') { emit(doc._id, doc) } }")
})
public class SpManufactrurerRepository extends CouchDbRepositorySupport<SpManufacturer> {

    public static final String VIEW_ALL = "allManufacturer";

    protected SpManufactrurerRepository(CouchDbConnector db) {
        super(SpManufacturer.class, db);
        initStandardDesignDocument();
    }

    /**
     * Сохраняет производителя в базе
     *
     * @param spManufacturer
     * @return
     */
    public String save(SpManufacturer spManufacturer) {
        add(spManufacturer);
        return spManufacturer.getId();
    }

    public void attachFile(String docId, String revId, AttachmentInputStream attachmentInputStream) {
        db.createAttachment(docId, revId, attachmentInputStream);
    }

    public SpManufacturer findOne(String id) {
        return db.find(SpManufacturer.class, id);
    }

    @Override
    public List<SpManufacturer> getAll() {
        ViewQuery viewByTime = createQuery(VIEW_ALL);
        return db.queryView(viewByTime, SpManufacturer.class);
    }
}
