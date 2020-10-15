package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.model.State;

public class CityRepositoryImpl implements CityRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<City> findByMultiple(String name, String stateName) {
    CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<City> query = cb.createQuery(City.class);
    Root<City> root = query.from(City.class);
    
    Metamodel meta = this.entityManager.getMetamodel();
    EntityType<City> cityEntityType = meta.entity(City.class);
    
    List<Predicate> predicates = new ArrayList<>();
    
    if (name != null && !name.isEmpty()) {
      predicates.add(cb.and(cb.equal(root.get("name"), name)));
    }
    
    if (stateName != null && !stateName.isEmpty()) {
      Join<City, State> joinState = root.join(cityEntityType.getDeclaredSingularAttribute("state", State.class));
      predicates.add(cb.and(cb.equal(joinState.get("name"), stateName)));
    }

    query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

    return this.entityManager.createQuery(query).getResultList();
  }
}
