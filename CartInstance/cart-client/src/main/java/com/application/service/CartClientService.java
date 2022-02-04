package com.application.service;

import com.application.dto.CartRequestDto;
import com.application.dto.CartsResponseDto;
import com.application.dto.ProductRequestDto;
import com.application.proto.Cart;
import com.application.proto.CartServiceGrpc;
import com.application.proto.Carts;
import com.application.proto.Product;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartClientService {

    @GrpcClient("grpc-cart-service")
    CartServiceGrpc.CartServiceBlockingStub cartServiceBlockingStub;

    public CartRequestDto createCart(CartRequestDto cartRequestDto){
        Cart response = cartServiceBlockingStub.createCart(Cart.newBuilder()
                .setCartId(cartRequestDto.getCartId())
                .addAllProduct(buildProducts(cartRequestDto.getProductRequestDto()))
                .build());
        return buildCartRequestDto(response);
    }

    public void updateCart(CartRequestDto cartRequestDto, String id){
        cartServiceBlockingStub.updateCart(Cart.newBuilder()
                .setCartId(id)
                .setUserId(cartRequestDto.getUserId())
                .addAllProduct(buildProducts(cartRequestDto.getProductRequestDto()))
                .build());
    }

    public CartRequestDto getCart(String id){
        Cart cart = Cart.newBuilder()
                .setCartId(id)
                .build();
        Cart response = cartServiceBlockingStub.getCart(cart);
        return buildCartRequestDto(response);
    }

    public Map<Descriptors.FieldDescriptor, Object> deleteCart(String id){
        Cart cart = Cart.newBuilder()
                .setCartId(id)
                .build();
        Cart response = cartServiceBlockingStub.deleteCart(cart);
        return response.getAllFields();
    }

    public List<CartRequestDto> getAllCart(){
        return buildCartRequestDtos(cartServiceBlockingStub.getAllCart(Cart.newBuilder()
                .build()));
    }

    private List<CartRequestDto> buildCartRequestDtos(Carts response) {
        return response.getCartList().stream().map(res-> buildCartRequestDto(res)).collect(Collectors.toList());
    }

    private CartRequestDto buildCartRequestDto(Cart cart) {
        return CartRequestDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .productRequestDto(buildProductDtos(cart.getProductList()))
                .build();
    }

    private List<ProductRequestDto> buildProductDtos(List<Product> productList) {
        return productList.stream().map(product -> buildProductDto(product)).collect(Collectors.toList());
    }

    private ProductRequestDto buildProductDto(Product product) {
        return ProductRequestDto.builder()
                .productName(product.getProductName())
                .productRate(Long.parseLong(String.valueOf(product.getProductRate())))
                .productType(product.getProductType())
                .productId(product.getProductId())
                .build();
    }

    private List<Product> buildProducts(List<ProductRequestDto> productRequestDto) {
        return productRequestDto.stream().map(productRequest->buildProduct(productRequest)).collect(Collectors.toList());
    }

    private Product buildProduct(ProductRequestDto productRequest) {
        return Product.newBuilder()
                .setProductId(productRequest.getProductId())
                .setProductName(productRequest.getProductName())
                .setProductRate(Integer.parseInt(productRequest.getProductRate().toString()))
                .setProductType(productRequest.getProductType())
                .build();
    }

}
