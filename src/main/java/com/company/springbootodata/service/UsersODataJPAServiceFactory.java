package com.company.springbootodata.service;

import com.company.springbootodata.config.JerseyConfig;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class UsersODataJPAServiceFactory extends ODataJPAServiceFactory {

    public UsersODataJPAServiceFactory() {
        setDetailErrors(true);
    }

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext jpaContext = getODataJPAContext();
        ODataContext oDataContext = jpaContext.getODataContext();
        HttpServletRequest request = (HttpServletRequest) oDataContext.getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
        EntityManager entityManager = (EntityManager) request.getAttribute(JerseyConfig.EntityManagerFilter.EM_REQUEST_ATTRIBUTE);

        jpaContext.setEntityManager(new EntityManagerWrapper(entityManager));
        jpaContext.setPersistenceUnitName("default");
        jpaContext.setContainerManaged(true);

        return jpaContext;
    }

    static class EntityManagerWrapper implements EntityManager {

        private EntityManager entityManager;

        @Override
        public void persist(Object entity) {
            entityManager.persist(entity);
        }

        @Override
        public <T> T merge(T entity) {
            return entityManager.merge(entity);
        }

        @Override
        public void remove(Object entity) {
            entityManager.remove(entity);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey) {
            return entityManager.find(entityClass, primaryKey);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
            return entityManager.find(entityClass, primaryKey, properties);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
            return entityManager.find(entityClass, primaryKey, lockMode);
        }

        @Override
        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
            return entityManager.find(entityClass, primaryKey, lockMode, properties);
        }

        @Override
        public <T> T getReference(Class<T> entityClass, Object primaryKey) {
            return entityManager.getReference(entityClass, primaryKey);
        }

        @Override
        public void flush() {
            entityManager.flush();
        }

        @Override
        public void setFlushMode(FlushModeType flushMode) {
            entityManager.setFlushMode(flushMode);
        }

        @Override
        public FlushModeType getFlushMode() {
            return entityManager.getFlushMode();
        }

        @Override
        public void lock(Object entity, LockModeType lockMode) {
            entityManager.lock(entity, lockMode);
        }

        @Override
        public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
            entityManager.lock(entity, lockMode, properties);
        }

        @Override
        public void refresh(Object entity) {
            entityManager.refresh(entity);
        }

        @Override
        public void refresh(Object entity, Map<String, Object> properties) {
            entityManager.refresh(entity, properties);
        }

        @Override
        public void refresh(Object entity, LockModeType lockMode) {
            entityManager.refresh(entity, lockMode);
        }

        @Override
        public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
            entityManager.refresh(entity, lockMode, properties);
        }

        @Override
        public void clear() {
            entityManager.clear();
        }

        @Override
        public void detach(Object entity) {
            entityManager.detach(entity);
        }

        @Override
        public boolean contains(Object entity) {
            return entityManager.contains(entity);
        }

        @Override
        public LockModeType getLockMode(Object entity) {
            return entityManager.getLockMode(entity);
        }

        @Override
        public void setProperty(String propertyName, Object value) {
            entityManager.setProperty(propertyName, value);
        }

        @Override
        public Map<String, Object> getProperties() {
            return entityManager.getProperties();
        }

        @Override
        public Query createQuery(String qlString) {
            return entityManager.createQuery(qlString);
        }

        @Override
        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
            return entityManager.createQuery(criteriaQuery);
        }

        @Override
        public Query createQuery(CriteriaUpdate updateQuery) {
            return entityManager.createQuery(updateQuery);
        }

        @Override
        public Query createQuery(CriteriaDelete deleteQuery) {
            return entityManager.createQuery(deleteQuery);
        }

        @Override
        public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
            return entityManager.createQuery(qlString, resultClass);
        }

        @Override
        public Query createNamedQuery(String name) {
            return entityManager.createNamedQuery(name);
        }

        @Override
        public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
            return entityManager.createNamedQuery(name, resultClass);
        }

        @Override
        public Query createNativeQuery(String sqlString) {
            return entityManager.createNativeQuery(sqlString);
        }

        @Override
        public Query createNativeQuery(String sqlString, Class resultClass) {
            return entityManager.createNativeQuery(sqlString, resultClass);
        }

        @Override
        public Query createNativeQuery(String sqlString, String resultSetMapping) {
            return entityManager.createNativeQuery(sqlString, resultSetMapping);
        }

        @Override
        public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
            return entityManager.createNamedStoredProcedureQuery(name);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
            return entityManager.createStoredProcedureQuery(procedureName);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
            return entityManager.createStoredProcedureQuery(procedureName, resultClasses);
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
            return entityManager.createStoredProcedureQuery(procedureName, resultSetMappings);
        }

        @Override
        public void joinTransaction() {
            entityManager.joinTransaction();
        }

        @Override
        public boolean isJoinedToTransaction() {
            return entityManager.isJoinedToTransaction();
        }

        @Override
        public <T> T unwrap(Class<T> cls) {
            return entityManager.unwrap(cls);
        }

        @Override
        public Object getDelegate() {
            return entityManager.getDelegate();
        }

        @Override
        public void close() {
            entityManager.close();
        }

        @Override
        public boolean isOpen() {
            return entityManager.isOpen();
        }

        @Override
        public EntityTransaction getTransaction() {
            return entityManager.getTransaction();
        }

        @Override
        public EntityManagerFactory getEntityManagerFactory() {
            return entityManager.getEntityManagerFactory();
        }

        @Override
        public CriteriaBuilder getCriteriaBuilder() {
            return entityManager.getCriteriaBuilder();
        }

        @Override
        public Metamodel getMetamodel() {
            return entityManager.getMetamodel();
        }

        @Override
        public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
            return entityManager.createEntityGraph(rootType);
        }

        @Override
        public EntityGraph<?> createEntityGraph(String graphName) {
            return entityManager.createEntityGraph(graphName);
        }

        @Override
        public EntityGraph<?> getEntityGraph(String graphName) {
            return entityManager.getEntityGraph(graphName);
        }

        @Override
        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
            return entityManager.getEntityGraphs(entityClass);
        }

        EntityManagerWrapper(EntityManager entityManager) {
            this.entityManager = entityManager;
        }
    }
}
