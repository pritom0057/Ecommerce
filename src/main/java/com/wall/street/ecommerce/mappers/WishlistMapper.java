package com.wall.street.ecommerce.mappers;

import com.wall.street.ecommerce.domains.dtos.WishlistDTO;
import com.wall.street.ecommerce.domains.entities.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.description", target = "productDescription")
    @Mapping(source = "product.price", target = "productPrice")
    WishlistDTO toWishlistDTO(Wishlist wishlist);
}
