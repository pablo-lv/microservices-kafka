package com.plucas.ws.products.service;

import com.plucas.ws.products.dto.ProductRequestDTO;

public interface IProductService {

    String createProduct(ProductRequestDTO product);
}
