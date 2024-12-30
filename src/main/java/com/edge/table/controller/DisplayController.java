package com.edge.table.controller;

import com.edge.table.entity.Product;
import com.edge.table.entity.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        addSearchOptions(model);
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

    private void addSearchOptions(Model model) {
        model.addAttribute("searchOptions",
                List.of("Manufacturer", "TypeName", "TypeId", "StyleName", "StyleId", "ColorNumber", "ColorName", "Size"));
    }

    /**
     * Deletes products selected by the user. Can delete multiple at a time.
     *
     * NOTE: Since we inject sample data when db is empty, you can never have an empty database (but you can have an empty table via searching)
     *
     * @param productIdsToDelete List of products Ids to mark the products to delete
     */
    @DeleteMapping("/delete/{productIdsToDelete}")
    @ResponseBody
    public void deleteProductById(@PathVariable List<Integer> productIdsToDelete) {
        for (int productId : productIdsToDelete) {
            Optional<Product> productToDelete = productRepository.findById(productId);
            productToDelete.ifPresent(productRepository::delete);
        }
    }

    // If I knew how to parse a field from a string I could reduce the # of these methods from 8 to 1, but not sure how to or if its even possible.
    // I wish I didn't have this much boilerplate, but its the best I can do for now.

    /**
     * Searches and returns matches of products with a specified manufacturer
     *
     * @param manufacturer manufacturer to search by
     */
    @GetMapping("/manufacturer/{manufacturer}")
    public String searchByManufacturer(@PathVariable String manufacturer,
                                       Model model) {
        final List<Product> products = productRepository.findByManufacturer(manufacturer);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified typeName
     *
     * @param typeName typeName to search by
     */
    @GetMapping("/typename/{typeName}")
    public String searchByTypeName(@PathVariable String typeName,
                                   Model model) {
        final List<Product> products = productRepository.findByTypeName(typeName);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified typeId
     *
     * @param typeId typeId to search by
     */
    @GetMapping("/typeid/{typeId}")
    public String searchByTypeId(@PathVariable int typeId,
                                 Model model) {
        final List<Product> products = productRepository.findByTypeId(typeId);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified styleName
     *
     * @param styleName styleName to search by
     */
    @GetMapping("/stylename/{styleName}")
    public String searchByStyleName(@PathVariable String styleName,
                                       Model model) {
        final List<Product> products = productRepository.findByStyleName(styleName);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified styleId
     *
     * @param styleId styleId to search by
     */
    @GetMapping("/styleid/{styleId}")
    public String searchByStyleId(@PathVariable String styleId,
                                       Model model) {
        final List<Product> products = productRepository.findByStyleId(styleId);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified colorNumber
     *
     * @param colorNumber colorNumber to search by
     */
    @GetMapping("/colornumber/{colorNumber}")
    public String searchByColorNumber(@PathVariable int colorNumber,
                                       Model model) {
        final List<Product> products = productRepository.findByColorNumber(colorNumber);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified colorName
     *
     * @param colorName colorName to search by
     */
    @GetMapping("/colorname/{colorName}")
    public String searchByColorName(@PathVariable String colorName,
                                       Model model) {
        final List<Product> products = productRepository.findByColorName(colorName);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }

    /**
     * Searches and returns matches of products with a specified size
     *
     * @param size size to search by
     */
    @GetMapping("/size/{size}")
    public String searchBySize(@PathVariable String size,
                                    Model model) {
        final List<Product> products = productRepository.findBySize(size);
        model.addAttribute("products", products);
        addSearchOptions(model);
        return "display";
    }
}
