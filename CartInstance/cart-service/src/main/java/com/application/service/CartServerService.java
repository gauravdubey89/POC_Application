package com.application.service;

import com.application.entity.Product;
import com.application.proto.Cart;
import com.application.proto.CartServiceGrpc;
import com.application.proto.Carts;
import com.application.repository.CartMyBatisRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
public class CartServerService extends CartServiceGrpc.CartServiceImplBase {

    @Autowired
    CartMyBatisRepository cartMyBatisRepository;

    @Override
    public void createCart(Cart request, StreamObserver<Cart> responseObserver) {
            cartMyBatisRepository.insert(com.application.entity.Cart.builder()
                    .cartId(UUID.randomUUID().toString())
                    .userId(request.getUserId())
                    .products(buildProducts(request.getProductList()))
                    .build());
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void updateCart(Cart request, StreamObserver<Cart> responseObserver) {
        int a = cartMyBatisRepository.update(com.application.entity.Cart.builder()
                .cartId(request.getCartId())
                .userId(request.getUserId())
                .products(buildProducts(request.getProductList()))
                .build());
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void getCart(Cart request, StreamObserver<Cart> responseObserver) {
        com.application.entity.Cart cart = cartMyBatisRepository.findById(request.getCartId());
        responseObserver.onNext(Cart.newBuilder()
                        .setCartId(cart.getCartId())
                        .addAllProduct(buildProductsDto(cart.getProducts()))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteCart(Cart request, StreamObserver<Cart> responseObserver) {
        cartMyBatisRepository.deleteById(request.getCartId());
    }

    @Override
    public void getAllCart(Cart request, StreamObserver<Carts> responseObserver) {
        List<com.application.entity.Cart> carts = cartMyBatisRepository.findAll();
        responseObserver.onNext(Carts.newBuilder().addAllCart(buildCarts(carts)).build());
        responseObserver.onCompleted();
    }

    private List<Cart> buildCarts(List<com.application.entity.Cart> carts) {
        return carts.stream().map(cart -> buildCart(cart)).collect(Collectors.toList());
    }

    private Cart buildCart(com.application.entity.Cart cart) {
        return Cart.newBuilder()
                .setCartId(cart.getCartId())
                .setUserId(Optional.ofNullable(cart.getUserId()).orElse(""))
                .addAllProduct(buildProductsDto(cart.getProducts()))
                .build();
    }

    private List<Product> buildProducts(List<com.application.proto.Product> productList) {
        return productList.stream().map(product->buildProduct(product)).collect(Collectors.toList());
    }

    private com.application.entity.Product buildProduct(com.application.proto.Product product) {
        return com.application.entity.Product.builder()
                .productId(product.getProductId())
                .productRate(Long.parseLong(String.valueOf(product.getProductRate())))
                .productName(product.getProductName())
                .productType(product.getProductType())
                .build();
    }

    private Iterable<com.application.proto.Product> buildProductsDto(List<Product> products) {
        return products.stream().map(product -> buildProductDto(product)).collect(Collectors.toList());
    }

    private com.application.proto.Product buildProductDto(Product product) {
        return com.application.proto.Product.newBuilder()
                .setProductType(product.getProductType())
                .setProductRate(Integer.parseInt(product.getProductRate().toString()))
                .setProductName(product.getProductName())
//                .setProductId(product.getProductId())
                .build();
    }

}
