package com.edge.table.controller;

import com.edge.table.entity.Product;
import com.edge.table.entity.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller to handle operations related to inputting data into the table
 */
@Controller
@RequestMapping("/input")
public class InputController {

    private final ProductRepository productRepository;

    public InputController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String navigateToInputPage(Model model) {
        model.addAttribute("productFields",
                List.of("Manufacturer", "Type-Name", "Type-Id", "Style-Name", "Style-Id", "Color-Number", "Color-Name", "Size"));
        return "input";
    }

    @PostMapping("/AddUpdate/{productId}")
    public String addOrUpdateProduct(@PathVariable int productId,
                                     @RequestBody Product newProduct) {
        if (productId == -1) {
            productRepository.save(newProduct);
        } else {
            final Optional<Product> originalProduct = productRepository.findById(productId);
            if (originalProduct.isPresent()) {
                newProduct.setId(productId);
                productRepository.save(newProduct);
            }
        }
        return "input";
    }

    @GetMapping("/edit/{productIdToEdit}")
    public String editExistingProduct(@PathVariable int productIdToEdit,
                                      Model model) {
        final Optional<Product> productToEdit = productRepository.findById(productIdToEdit);
        productToEdit.ifPresent(product -> model.addAttribute("productToEdit", product));
        return "input";
    }
}
