package com.project.ticketservice.repository;

import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TicketCustomRepositoryImpl implements TicketCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ticket> findTicketsByCriteria(TicketFilterDTO ticketFilter) {

        StringBuilder queryBuilder = new StringBuilder("SELECT t FROM Ticket t WHERE 1 = 1");
        Map<String, Object> parameters = new HashMap<>();

        if (ticketFilter.getMinCount() != null) {
            queryBuilder.append(" AND t.count >= :minCount");
            parameters.put("minCount", ticketFilter.getMinCount());
        }

        if (ticketFilter.getMaxCount() != null) {
            queryBuilder.append(" AND t.count <= :maxCount");
            parameters.put("maxCount", ticketFilter.getMaxCount());
        }

        if (ticketFilter.getMinTotalPrice() != null) {
            queryBuilder.append(" AND t.totalPrice >= :minTotalPrice");
            parameters.put("minTotalPrice", ticketFilter.getMinTotalPrice());
        }

        if (ticketFilter.getMaxTotalPrice() != null) {
            queryBuilder.append(" AND t.totalPrice <= :maxTotalPrice");
            parameters.put("maxTotalPrice", ticketFilter.getMaxTotalPrice());
        }

        if (ticketFilter.getEventId() != null) {
            queryBuilder.append(" AND t.event_id = :eventId");
            parameters.put("eventId", ticketFilter.getEventId());
        }

        if (ticketFilter.getUserId() != null) {
            queryBuilder.append(" AND t.user_id = :userId");
            parameters.put("userId", ticketFilter.getUserId());
        }

        TypedQuery<Ticket> query = entityManager.createQuery(queryBuilder.toString(), Ticket.class);

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setFirstResult((ticketFilter.getPage() - 1) * ticketFilter.getSize());
        query.setMaxResults(ticketFilter.getSize());

        return query.getResultList();
    }

}
