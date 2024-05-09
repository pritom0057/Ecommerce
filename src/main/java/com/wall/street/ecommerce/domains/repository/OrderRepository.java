package com.wall.street.ecommerce.domains.repository;

import com.wall.street.ecommerce.domains.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT COALESCE(SUM(o.total_amount), 0) FROM \"order\" o WHERE o.order_date BETWEEN :startOfDay AND :endOfDay", nativeQuery = true)
    BigDecimal getTotalSaleAmountForCurrentDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query(value =
            "SELECT CAST(DATE_TRUNC('day', order_date) AS DATE) AS sale_day " +
            "FROM \"order\" " +
            "WHERE order_date BETWEEN :startDateTime AND :endDateTime " +
            "GROUP BY sale_day " +
            "ORDER BY SUM(total_amount) DESC " +
            "LIMIT 1",
            nativeQuery = true)
    LocalDate findMaxSaleDay(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query(value = "SELECT oi.product_id, p.name, SUM(oi.total_price) as totalSold "
                   + "FROM  \"order_item\" oi "
                   + "JOIN  \"product\" p ON oi.product_id = p.id "
                   + "GROUP BY oi.product_id, p.name "
                   + "ORDER BY SUM(oi.total_price) DESC "
                   + "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5SellingItems();

    @Query(value = "SELECT p.id as product_id, p.name as product_name, SUM(oi.quantity) as total_sold " +
                   "FROM order_item oi " +
                   "JOIN \"order\" o ON oi.order_id = o.id " +
                   "JOIN product p ON oi.product_id = p.id " +
                   "WHERE o.order_date >= CURRENT_DATE - INTERVAL '1 month' " +
                   "GROUP BY p.id, p.name " +
                   "ORDER BY total_sold DESC " +
                   "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5SellingLastMonth();
}

