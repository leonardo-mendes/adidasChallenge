package com.adidas.subscription.repository;

import com.adidas.subscription.domain.Subscription;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    List<Subscription> findAll();

    void deleteById(Integer id);
}
