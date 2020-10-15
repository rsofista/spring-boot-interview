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
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Client> findByMultiple(String name, String stateName) {
    CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<Client> query = cb.createQuery(Client.class);
    Root<Client> root = query.from(Client.class);
    
    Metamodel meta = this.entityManager.getMetamodel();
    EntityType<Client> ClientEntityType = meta.entity(Client.class);
    
    List<Predicate> predicates = new ArrayList<>();
    
    if (name != null && !name.isEmpty()) {
      predicates.add(cb.and(cb.equal(root.get("name"), name)));
    }
    
    if (stateName != null && !stateName.isEmpty()) {
      Join<Client, City> joinState = root.join(ClientEntityType.getDeclaredSingularAttribute("currentCity", City.class));

      predicates.add(cb.and(cb.equal(joinState.get("name"), stateName)));
    }

    query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

    return this.entityManager.createQuery(query).getResultList();
  }
}
