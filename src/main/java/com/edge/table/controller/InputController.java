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

    /**
     * Appends fields (for frontend ids) and redirects user to input page
     */
    @GetMapping
    public String navigateToInputPage(Model model) {
        model.addAttribute("productFields",
                List.of("Manufacturer", "TypeName", "TypeId", "StyleName", "StyleId", "ColorNumber", "ColorName", "Size"));
        return "input";
    }

    /**
     * Creates a new product, or edits and existing one, with the data submitted from the input page
     *
     * @param productId product Id if editing and existing product, -1 otherwise
     * @param newProduct product with edited/newly added values
     */
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

    /**
     * Appends the existing product to edit if it exists. Redirect to edit page.
     *
     * @param productIdToEdit product Id of the product to edit
     */
    @GetMapping("/edit/{productIdToEdit}")
    public String editExistingProduct(@PathVariable int productIdToEdit,
                                      Model model) {
        final Optional<Product> productToEdit = productRepository.findById(productIdToEdit);
        if (productToEdit.isPresent()) {
            model.addAttribute("productToEdit", productToEdit.get());
            return "input";
        } else {
            // No such product exists, send them to display page
            return "redirect:/";
        }
    }
}
