package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.customer.CreateCustomerDto;
import com.javPOL.magazineJava.dto.customer.CustomerResponseDto;
import com.javPOL.magazineJava.dto.customer.UpdateCustomerDto;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.User;
import com.javPOL.magazineJava.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4201")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/my-addresses")
    public ResponseEntity<List<CustomerResponseDto>> getMyAddresses(
            @AuthenticationPrincipal User currentUser) {
        try {
            List<CustomerResponseDto> addresses = customerService.getMyAddresses(currentUser);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getAddress(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        try {
            CustomerResponseDto address = customerService.getAddress(id, currentUser);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createAddress(
            @RequestBody CreateCustomerDto createCustomerDto,
            @AuthenticationPrincipal User currentUser) {
        try {
            CustomerResponseDto createdAddress = customerService.createAddress(currentUser, createCustomerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateAddress(
            @PathVariable Long id,
            @RequestBody UpdateCustomerDto updateCustomerDto,
            @AuthenticationPrincipal User currentUser) {
        try {
            CustomerResponseDto updatedAddress = customerService.updateAddress(id, updateCustomerDto, currentUser);
            return ResponseEntity.ok(updatedAddress);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        try {
            customerService.deleteAddress(id, currentUser);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/set-default")
    public ResponseEntity<CustomerResponseDto> setAsDefault(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        try {
            CustomerResponseDto defaultAddress = customerService.setAsDefault(id, currentUser);
            return ResponseEntity.ok(defaultAddress);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Customer>> getAllAddresses(@AuthenticationPrincipal User currentUser) {
        try {
            if (!isAdmin(currentUser)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            List<Customer> addresses = customerService.findAll();
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean isAdmin(User user) {
        return user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}