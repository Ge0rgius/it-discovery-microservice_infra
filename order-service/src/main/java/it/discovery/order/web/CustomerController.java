package it.discovery.order.web;

import it.discovery.order.client.dto.CustomerDTO;
import it.discovery.order.domain.Customer;
import it.discovery.order.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public CustomerDTO save(CustomerDTO customer) {
        return toDTO(customerRepository.save(toEntity(customer)));
    }

    @GetMapping
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(this::toDTO).toList();
    }

    //TODO create separate mapping service
    private CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    private Customer toEntity(CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }
}
