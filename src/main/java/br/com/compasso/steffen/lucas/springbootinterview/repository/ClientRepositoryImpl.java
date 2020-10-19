package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.text.ParseException;
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

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Client> findByDto(CreateClientDto dto) throws ParseException {
    CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<Client> query = cb.createQuery(Client.class);
    Root<Client> root = query.from(Client.class);
    
    Metamodel meta = this.entityManager.getMetamodel();
    EntityType<Client> ClientEntityType = meta.entity(Client.class);
    
    List<Predicate> predicates = new ArrayList<>();
    
    if (dto.getName() != null && !dto.getName().isEmpty()) {
      predicates.add(cb.and(cb.equal(root.get("name"), dto.getName())));
    }
    
    if (dto.getBirthDate() != null) {
      predicates.add(cb.and(cb.equal(root.get("birthDate"), dto.getBirthDate())));
    }

    if (dto.getSex() != null) {
      predicates.add(cb.and(cb.equal(root.get("sex"), dto.getSex())));
    }

    if (dto.getCurrentCityId() != null) {
      Join<Client, City> joinCity = root.join(ClientEntityType.getDeclaredSingularAttribute("currentCity", City.class));

      predicates.add(cb.and(cb.equal(joinCity.get("id"), dto.getCurrentCityId())));
    }

    query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

    return this.entityManager.createQuery(query).getResultList();
  }
}
