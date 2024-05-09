package com.wall.street.ecommerce.services.implementation;

import com.wall.street.ecommerce.domains.dtos.TopSellingItemDTO;
import com.wall.street.ecommerce.domains.repository.OrderRepository;
import com.wall.street.ecommerce.services.TopSellingItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopSellingItemServiceImplementation implements TopSellingItemService {

    private final OrderRepository orderRepository;

    @Override
    public List<TopSellingItemDTO> getTop5SellingItems() {
        try {
            List<Object[]> topSellingItemsData = orderRepository.findTop5SellingItems();
            List<TopSellingItemDTO> topSellingItems = topSellingItemsData.stream()
                                                                         .map(row -> {
                                                                             TopSellingItemDTO dto = new TopSellingItemDTO();
                                                                             dto.setProductId((int) row[0]);
                                                                             dto.setProductName((String) row[1]);
                                                                             dto.setTotalSold((BigDecimal) row[2]);
                                                                             return dto;
                                                                         })
                                                                         .collect(Collectors.toList());
            log.info("Successfully retrieved top 5 selling items: {}", topSellingItems);
            return topSellingItems;
        }
        catch (Exception e) {
            log.error("Failed to retrieve top 5 selling items", e);
            throw e; // Propagate the exception
        }
    }

    @Override
    public List<TopSellingItemDTO> getTop5SellingItemsLastMonth() {
        try {
            List<Object[]> topSellingItemsLastMonthData = orderRepository.findTop5SellingLastMonth();
            List<TopSellingItemDTO> topSellingItemsLastMonth = topSellingItemsLastMonthData.stream()
                                                                                           .map(row -> {
                                                                                               TopSellingItemDTO dto = new TopSellingItemDTO();
                                                                                               dto.setProductId((int) row[0]);
                                                                                               dto.setProductName((String) row[1]);
                                                                                               dto.setTotalSold((BigDecimal) row[2]);
                                                                                               return dto;
                                                                                           })
                                                                                           .collect(Collectors.toList());
            log.info("Successfully retrieved top 5 selling items last month: {}", topSellingItemsLastMonth);
            return topSellingItemsLastMonth;
        }
        catch (Exception e) {
            log.error("Failed to retrieve top 5 selling items last month", e);
            throw e; // Propagate the exception
        }
    }
}
