package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CustomerDAO.CustomerDao;
import com.javPOL.magazineJava.dao.OrderDAO.OrderDao;
import com.javPOL.magazineJava.dao.ProductDAO.ProductDao;
import com.javPOL.magazineJava.dao.ProductOrderDAO.ProductOrderDao;
import com.javPOL.magazineJava.dto.CreateOrderRequestDto;
import com.javPOL.magazineJava.dto.ProductOrderDto;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.model.ProductOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderSeviceTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private ProductOrderDao productOrderDao;

    @Mock
    private ProductDao productDao;

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testCreate() {
        Long customerId = 1L;

        ProductOrderDto productOrderDto1 = new ProductOrderDto(1L, 1, 21.99);
        ProductOrderDto productOrderDto2= new ProductOrderDto(2L, 11, 1.32);

        List<ProductOrderDto> productOrderDtos = List.of(productOrderDto1, productOrderDto2);

        CreateOrderRequestDto request = new CreateOrderRequestDto(customerId, productOrderDtos);

        Customer customer = new Customer();
        customer.setId(customerId);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(21.99);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setPrice(1.32);

        when(customerDao.findById(request.getCustomerId())).thenReturn(Optional.of(customer));
        when(productDao.findById(1L)).thenReturn(Optional.of(product1));
        when(productDao.findById(2L)).thenReturn(Optional.of(product2));

        orderService.save(request);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertEquals(customer, savedOrder.getCustomer());


        ArgumentCaptor<ProductOrder> productOrderCaptor = ArgumentCaptor.forClass(ProductOrder.class);
        verify(productOrderDao, times(2)).save(productOrderCaptor.capture());
        List<ProductOrder> savedProductOrders = productOrderCaptor.getAllValues();

        assertEquals(2, savedProductOrders.size());

        ProductOrder po1 = savedProductOrders.get(0);
        assertEquals(savedOrder, po1.getOrder());
        assertEquals(product1, po1.getProduct());
        assertEquals(product1.getPrice(), po1.getUnityValue());
        assertEquals(1, po1.getQuantity());

        ProductOrder po2 = savedProductOrders.get(1);
        assertEquals(savedOrder, po2.getOrder());
        assertEquals(product2, po2.getProduct());
        assertEquals(product2.getPrice(), po2.getUnityValue());
        assertEquals(11, po2.getQuantity());
    }

    @Test
    public void testCreate_whenCustomerNotFound() {
        Long customerId = 1L;

        CreateOrderRequestDto request = new CreateOrderRequestDto();
        request.setCustomerId(customerId);

        when(customerDao.findById(request.getCustomerId())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.save(request);
        });

        assertEquals("Customer not found", exception.getMessage());

        verify(orderDao, never()).save(any());
        verify(productOrderDao, never()).save(any());
    }

    @Test
    public void testCreate_whenProductNotFound() {
        Long customerId = 1L;

        ProductOrderDto productOrderDto1 = new ProductOrderDto(1L, 1, 21.99);
        ProductOrderDto productOrderDto2= new ProductOrderDto(2L, 11, 1.32);

        List<ProductOrderDto> productOrderDtos = List.of(productOrderDto1, productOrderDto2);

        CreateOrderRequestDto request = new CreateOrderRequestDto(customerId, productOrderDtos);

        Customer customer = new Customer();
        customer.setId(customerId);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(21.99);



        when(customerDao.findById(request.getCustomerId())).thenReturn(Optional.of(customer));
        when(productDao.findById(1L)).thenReturn(Optional.of(product1));
        when(productDao.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.save(request);
        });

        assertEquals("Product not found", exception.getMessage());

        verify(orderDao).save(any(Order.class));
        verify(productOrderDao, times(1)).save(any(ProductOrder.class));
    }

    @Test
    public void testCreate_NoProducts() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);

        CreateOrderRequestDto request = new CreateOrderRequestDto();
        request.setCustomerId(customerId);
        request.setProducts(List.of());

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));

        orderService.save(request);

        verify(orderDao).save(any(Order.class));
        verify(productOrderDao, never()).save(any(ProductOrder.class));
    }
}
