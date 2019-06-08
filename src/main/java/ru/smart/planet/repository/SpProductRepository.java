package ru.smart.planet.repository;

import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.ektorp.support.Views;
import org.springframework.stereotype.Repository;
import ru.smart.planet.domain.SpProduct;
import ru.smart.planet.web.Category;

import java.util.List;

/**
 * Created on 08.06.19.
 */
@Repository
@Views({
        @View(name = SpProductRepository.VIEW_ALL, map = "function(doc) { if (doc.type='PRODUCT') { emit(doc._id, doc) } }"),
        @View(name = SpProductRepository.VIEW_BY_CATEGORY, map = "function(doc) { if (doc.type='PRODUCT') { emit(doc.category, doc) } }"),
        @View(name = SpProductRepository.VIEW_BY_GROUP, map = "function(doc) { if (doc.type='PRODUCT') { emit(doc.group, doc) } }")
})
public class SpProductRepository extends CouchDbRepositorySupport<SpProduct> {

    public static final String VIEW_ALL = "allProduct";
    public static final String VIEW_BY_CATEGORY = "viewByCategory";
    public static final String VIEW_BY_GROUP = "viewByGroup";

    protected SpProductRepository(CouchDbConnector db) {
        super(SpProduct.class, db);
        initStandardDesignDocument();
    }

    /**
     * Сохраняет производителя в базе
     *
     * @param spProduct
     * @return
     */
    public String save(SpProduct spProduct) {
        add(spProduct);
        return spProduct.getId();
    }

    public void attachFile(String docId, String revId, AttachmentInputStream attachmentInputStream) {
        db.createAttachment(docId, revId, attachmentInputStream);
    }

    public SpProduct findOne(String id) {
        return db.find(SpProduct.class, id);
    }

    @Override
    public List<SpProduct> getAll() {
        ViewQuery viewAll = createQuery(VIEW_ALL);
        return db.queryView(viewAll, SpProduct.class);
    }

    public List<SpProduct> getByCategory(Category category) {
        ViewQuery viewByCategory = createQuery(VIEW_BY_CATEGORY)
                .key(category);
        return db.queryView(viewByCategory, SpProduct.class);
    }

    public List<SpProduct> getByGroup(String group) {
        ViewQuery viewByGroup = createQuery(VIEW_BY_GROUP)
                .key(group);
        return db.queryView(viewByGroup, SpProduct.class);
    }
}
