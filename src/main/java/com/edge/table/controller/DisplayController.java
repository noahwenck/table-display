package com.edge.table.controller;

import com.edge.table.entity.Product;
import com.edge.table.entity.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Controller to handle operations related to displaying the table
 */
@Controller
public class DisplayController {

    private final ProductRepository productRepository;

    public DisplayController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Attaches any existing products to our model, and redirects the user to the displayed table.
     */
    @GetMapping
    public String display(Model model) {
        // If no data exists, add sample data
        if (productRepository.findAll().isEmpty()) {
            injectInitData();
        }

        model.addAttribute("products", productRepository.findAll());
        return "display";
    }

    private void injectInitData() {
        productRepository.saveAll(List.of(
                new Product("Armstrong", "Resilient", 1, "Standard Excelon", null, 51915, "Charcoal", "12 x 12"),
                new Product("Tarkett", "Resilient", 1, "Classic Plank", null, 3308, "Ash", "12 x 24"),
                new Product("Shaw", "Carpet", 2, "Convene", "5T269", 67537, "Dynamic Network", "24x 24"),
                new Product("Mohawk", "Carpet", 2, "Ivy Hall", "SLC46", 789, "Wordly Gray", "12' Roll Goods"),
                new Product("Daltile", "Ceramic Tile", 3, "Color Wheel", null, 1174, "Seabreeze", "4 x 8"),
                new Product("Transcermaica", "Ceramic Tile", 3, "Aster Maximum Fiandra", null, null, "Mercury", "18 x 18")
        ));
    }

    /**
     * Deletes products selected by the user. Can delete multiple at a time
     *
     * @param productIdsToDelete List of products Ids to mark the products to delete
     */
    @DeleteMapping("/delete/{productIdsToDelete}")
    public String deleteProductById(@PathVariable List<Integer> productIdsToDelete) {
        for (int productId : productIdsToDelete) {
            Optional<Product> productToDelete = productRepository.findById(productId);
            productToDelete.ifPresent(productRepository::delete);
        }
        return "display";
    }
}
